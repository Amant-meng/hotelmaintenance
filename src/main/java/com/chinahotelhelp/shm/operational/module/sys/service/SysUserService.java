package com.chinahotelhelp.shm.operational.module.sys.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.chinahotelhelp.shm.operational.common.filed.CombFiled;
import com.chinahotelhelp.shm.operational.common.filed.DateRangeFiled;
import com.chinahotelhelp.shm.operational.common.filed.Filed;
import com.chinahotelhelp.shm.operational.common.filed.TextFiled;
import com.chinahotelhelp.shm.operational.config.RetryLimitHashedCredentialsMatcher;
import com.chinahotelhelp.shm.operational.module.sys.entity.*;
import com.chinahotelhelp.shm.operational.module.sys.mapper.SysUserMapper;
import com.chinahotelhelp.shm.operational.tools.QueryUntil;
import com.chinahotelhelp.shm.operational.tools.ShiroUtils;
import com.google.code.kaptcha.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Huan.Xia
 * @Title: UserService
 * @ProjectName merchant-management
 * @Description: TODO
 * @date 2018/10/30/03010:23
 */
@Slf4j
@Service
public class SysUserService {

    @Autowired
    private ConfigPath configPath;

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    RetryLimitHashedCredentialsMatcher retryLimitHashedCredentialsMatcher;
    /**
     * 登录
     *
     * @param sysUser
     * @return
     */
    public Message login(SysUser sysUser) {
        Message message = Message.N();
        try {
            Object object = ShiroUtils.getSessionAttribute(Constants.KAPTCHA_SESSION_KEY);
            if (object != null && sysUser.getImgcode() != null && object.toString().equalsIgnoreCase(sysUser.getImgcode())) {
                UsernamePasswordToken token = new UsernamePasswordToken(sysUser.getUsername(), sysUser.getPassword());
                Subject subject = ShiroUtils.getSubject();
                SysUser userByUsername = getUserByUsername(sysUser.getUsername());
                if (userByUsername == null){
                    message.setMessage("用户名不存在！");
                }else {
                    if ("0".equals(userByUsername.getEnabled())) {
                        message.setMessage("用户已被锁定,请联系管理员！");
                    } else {
                        try {
                            subject.login(token);
                            SecurityUtils.getSubject().getSession().setTimeout(-1000l);//设置session不会超时
                            ShiroUtils.getSession().setAttribute("SysUser",userByUsername);
                            userByUsername.setLast_login_time(new Date());
                            sysUserMapper.updateById(userByUsername);
                            message.setSuccess(true);
                            message.setMessage("登录成功！");
                        } catch (Exception e) {
                            message.setMessage("密码错误！");
                        }
                    }
                }
            } else {
                message.setMessage("验证码输入错误！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return message;
    }

    /**
     * 添加用户
     *
     * @param sysUser
     */
    @CacheEvict(value = "sysusers", key = "#sysUser.username")
    public Message addUser(SysUser sysUser) {
        Message message = Message.N();
        ByteSource credentialsSalt = ByteSource.Util.bytes(sysUser.getPhone());
        Object obj = new SimpleHash("MD5", sysUser.getPassword(), credentialsSalt, 1024);
        sysUser.setPassword(((SimpleHash) obj).toHex());

        //sysUser.setId(UUID.randomUUID().toString().replace("-", ""));
        SimpleDateFormat formatter = new SimpleDateFormat("HHmmssSSS");
        sysUser.setId(sysUser.getHiId()+ formatter.format(new Date()));

        try {
            sysUser.setCreatetime(new Date());
            sysUserMapper.insert(sysUser);
            SysUser userByUsername = getUserByUsername(sysUser.getUsername());
            Map<String,String> map = new HashMap<>();
            if (userByUsername != null){
                map.put("id",userByUsername.getId());
                map.put("issystem",userByUsername.getIssystem());
                message.setData(map);
            }else {
                message.setSuccess(false);
                message.setMessage("未查找到该用户！");
            }
            message.setSuccess(true);
            message.setMessage("添加成功！");
        } catch (Exception e) {
            message.setSuccess(false);
            message.setMessage("添加失败！");
        }
        return message;
    }

    /**
     * 删除用户
     *
     * @param username
     */
    @CacheEvict(value = "sysusers", key = "#username")
    public void delUser(String username) {
        Wrapper<SysUser> sysUserWrapper = new EntityWrapper<>();
        sysUserWrapper.eq("username", username);
        SysUser sysUser = new SysUser();
        sysUser.setDeltag("1");
        sysUserMapper.update(sysUser, sysUserWrapper);
    }

    /**
     * 修改用户
     *
     * @param sysUser
     */
    @CacheEvict(value = "sysusers", key = "#sysUser.username")
    public void updateUser(SysUser sysUser) {
        if (sysUser.getPassword() != null && sysUser.getPassword().length() > 0) {
            ByteSource credentialsSalt = ByteSource.Util.bytes(sysUser.getPhone());
            Object obj = new SimpleHash("MD5", sysUser.getPassword(), credentialsSalt, 1024);
            sysUser.setPassword(((SimpleHash) obj).toHex());
        } else {
            sysUser.setPassword(null);
        }
        try {
            sysUserMapper.updateById(sysUser);
            //用户启用状态修改
            if ("1".equals(sysUser.getEnabled())){

                retryLimitHashedCredentialsMatcher.unlockAccount(sysUser.getUsername());
            }
        } catch (Exception e) {
            throw new RuntimeException("修改失败，请检查输入的数据");
        }
    }
    /**
     * 修改密码
     *
     * @param password
     * @param newPassword
     */
    @CacheEvict(value = "sysusers", key = "#ShiroUtils.getUserEntity().getUsername()")
    public Message updatePassword(String password, String newPassword) {
        Message message = Message.N();
        SysUser sysUser = ShiroUtils.getUserEntity();
        if (password != null && password.length() > 0) {
            ByteSource credentialsSalt1 = ByteSource.Util.bytes(sysUser.getPhone());
            Object obj1 = new SimpleHash("MD5", password, credentialsSalt1, 1024);
            String toHex = ((SimpleHash) obj1).toHex();
            if (toHex.equals(sysUser.getPassword())) {
                ByteSource credentialsSalt = ByteSource.Util.bytes(sysUser.getPhone());
                Object obj = new SimpleHash("MD5", newPassword, credentialsSalt, 1024);
                sysUser.setPassword(((SimpleHash) obj).toHex());
                sysUser.setModifytime(new Date());
                sysUser.setModifyuser(sysUser.getId());
                sysUser.setModifyusername(sysUser.getUsername());
                try {
                    sysUserMapper.updateById(sysUser);
                    message.setSuccess(true);
                    message.setMessage("密码修改成功！");
                } catch (Exception e) {
                    e.printStackTrace();
                    message.setSuccess(false);
                    message.setMessage("密码修改失败！");
                }
            } else {
                message.setSuccess(false);
                message.setMessage("密码不正确！修改失败！");
            }
        } else {
                sysUser.setPassword(null);
            }
        return message;
    }

    /**
     * 根据用户名获取用户
     *
     * @param username
     * @return
     */
    @Cacheable(value = "sysusers", key = "#username")
    public SysUser getUserByUsername(String username) {
        Wrapper<SysUser> sysUserWrapper = new EntityWrapper<SysUser>();
        sysUserWrapper.eq("username", username);
        sysUserWrapper.and("deltag='0'");
        List<SysUser> list = sysUserMapper.selectList(sysUserWrapper);
        SysUser sysUser = null;
        if (list != null && list.size() > 0) {
            sysUser = list.get(0);

        }
        return sysUser;
    }

    /**
     * 根据用户名ID获取用户
     *
     * @param id)
     * @return
     */

    public SysUser getUserById(String id) {
        Wrapper<SysUser> sysUserWrapper = new EntityWrapper<SysUser>();
        sysUserWrapper.eq("id", id);
        List<SysUser> list = sysUserMapper.selectList(sysUserWrapper);
        SysUser sysUser = null;
        if (list != null && list.size() > 0) {
            sysUser = list.get(0);
            sysUser.setPremission(new ArrayList<>());
            sysUser.setMenus(getUserMenus(sysUser.getId(), sysUser));
        }
        return sysUser;
    }


    /**
     * 获取用户菜单列表
     *
     * @param uid
     * @return
     */
    private List<SysMenu> getUserMenus(String uid, SysUser sysUser) {
        List<SysMenu> menus = sysUserMapper.getUserPermissionById(uid);
        List<SysMenu> tree = new ArrayList<SysMenu>();
        if (menus != null && menus.size() > 0) {
            for (SysMenu menu : menus) {
                if (menu.getIsfunc().equals("0")) {
                    if (menu.getPid() == null) {
                        getSubMenu(menu, menus);
                        tree.add(menu);
                    }
                }
                if (menu.getPremission() != null && !menu.getPremission().isEmpty()) {
                    String[] tempArray = menu.getPremission().split(",");
                    for (String stritem : tempArray) {
                        sysUser.getPremission().add(stritem);
                    }
                }
            }
        }
        return tree;
    }

    /**
     * 遍历子菜单
     *
     * @param menu
     * @param menus
     */
    private void getSubMenu(SysMenu menu, List<SysMenu> menus) {
        for (SysMenu sub : menus) {
            if (sub.getPid() != null){
                if (sub.getPid().intValue() == menu.getId().intValue()) {
                    if (menu.getSubs() == null) {
                        List<SysMenu> subitems = new ArrayList<SysMenu>();
                        subitems.add(sub);
                        menu.setSubs(subitems);
                    } else {
                        menu.getSubs().add(sub);
                    }
                    getSubMenu(sub, menus);
                }
            }
        }
    }

    /**
     * 获取用户列表
     *
     * @param page
     * @return
     */

    public PageData getUserList(Page page) {
        List<Filed> queryFileds = new ArrayList<Filed>();
        Map<String, Object> params = page.getParams();
        if (params != null && params.size() > 0) {
            queryFileds.add(new CombFiled("username", params));
            queryFileds.add(new TextFiled("truename", params));
            queryFileds.add(new CombFiled("hiId", params));
            queryFileds.add(new TextFiled("hiId_name", params));
            queryFileds.add(new TextFiled("createuser_name", params));
            queryFileds.add(new TextFiled("modifyuser_name", params));
            queryFileds.add(new CombFiled("phone", params));
            queryFileds.add(new TextFiled("gender_name", params));
            queryFileds.add(new TextFiled("enabled_name", params));
            queryFileds.add(new TextFiled("issystem_name", params));
            queryFileds.add(new CombFiled("blocId", params));
            queryFileds.add(new TextFiled("blocId_name", params));
            queryFileds.add(new CombFiled("gender", params));
            queryFileds.add(new CombFiled("enabled", params));
            queryFileds.add(new CombFiled("issystem", params));
            queryFileds.add(new CombFiled("rid", params));
            queryFileds.add(new DateRangeFiled("createtime", params));
            queryFileds.add(new DateRangeFiled("modifytime", params));
            queryFileds.add(new DateRangeFiled("last_login_time", params));
        }
        PageData pageData = new PageData();
        String[] cmdSqlArray = QueryUntil.getQuerySql("id,username,password,truename,phone,gender,enabled,createtime," +
                "createuser,modifytime,modifyuser," +
                "deltag,gender_name,enabled_name,hiId,hiId_name,createuser_name,modifyuser_name," +
                "issystem,issystem_name,blocId,blocId_name,role,last_login_time", queryFileds, " and deltag='0' ORDER BY createtime", "sys_user");
        page.setExec_sql(cmdSqlArray[0]);
        pageData.setData(sysUserMapper.execSQL(page));
        page.setExec_sql(cmdSqlArray[1]);
        pageData.setRecordsTotal(Integer.parseInt(sysUserMapper.execSQL(page).get(0).get("count").toString()));
        pageData.setRecordsFiltered(pageData.getRecordsTotal());
        return pageData;
    }

    /**
     * 获取酒店用户列表
     *
     * @param page
     * @return
     */

    public PageData getHotelUserList(Page page, String hiId) {
        List<Filed> queryFileds = new ArrayList<Filed>();
        Map<String, Object> params = page.getParams();
        params.put("hiId",hiId);
        if (params != null && params.size() > 0) {
            queryFileds.add(new TextFiled("username", params));
            queryFileds.add(new TextFiled("truename", params));
            queryFileds.add(new TextFiled("hiId", params));
            queryFileds.add(new TextFiled("phone", params));
            queryFileds.add(new TextFiled("createuser_name", params));
            queryFileds.add(new TextFiled("modifyuser_name", params));
            queryFileds.add(new TextFiled("gender_name", params));
            queryFileds.add(new TextFiled("enabled_name", params));
            queryFileds.add(new TextFiled("issystem_name", params));
            queryFileds.add(new CombFiled("gender", params));
            queryFileds.add(new CombFiled("enabled", params));
            queryFileds.add(new CombFiled("issystem", params));
            queryFileds.add(new CombFiled("rid", params));
            queryFileds.add(new DateRangeFiled("createtime", params));
            queryFileds.add(new DateRangeFiled("modifytime", params));
            queryFileds.add(new TextFiled("hiId", params));
            queryFileds.add(new DateRangeFiled("last_login_time", params));
        }
        PageData pageData = new PageData();
        String[] cmdSqlArray = QueryUntil.getQuerySql("id,username,password,truename,phone,gender,enabled,createtime," +
                "createuser,modifytime,modifyuser," +
                "deltag,gender_name,enabled_name,hiId,hiId_name,createuser_name,modifyuser_name," +
                "issystem,issystem_name,blocId,blocId_name,role,last_login_time", queryFileds, " and deltag='0' ORDER BY createtime", "sys_user");
        page.setExec_sql(cmdSqlArray[0]);
        pageData.setData(sysUserMapper.execSQL(page));
        page.setExec_sql(cmdSqlArray[1]);
        pageData.setRecordsTotal(Integer.parseInt(sysUserMapper.execSQL(page).get(0).get("count").toString()));
        pageData.setRecordsFiltered(pageData.getRecordsTotal());
        return pageData;
    }
}
