package com.chinahotelhelp.shm.operational.module.sys.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.chinahotelhelp.shm.operational.common.filed.CombFiled;
import com.chinahotelhelp.shm.operational.common.filed.DateRangeFiled;
import com.chinahotelhelp.shm.operational.common.filed.Filed;
import com.chinahotelhelp.shm.operational.common.filed.TextFiled;
import com.chinahotelhelp.shm.operational.module.sys.entity.Page;
import com.chinahotelhelp.shm.operational.module.sys.entity.PageData;
import com.chinahotelhelp.shm.operational.module.sys.entity.SysRole;
import com.chinahotelhelp.shm.operational.module.sys.mapper.SysRoleMapper;
import com.chinahotelhelp.shm.operational.tools.QueryUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Auther: 杨昌亮
 * @Date: 2018/11/20
 * @Description: 角色操作
 */
@Service
public class SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    /**
     * 查询所有的角色列表
     *
     * @return 角色列表
     */
    public PageData getRoleList(Page page) {
        List<Filed> queryFileds = new ArrayList<Filed>();
        Map<String, Object> params = page.getParams();
        if (params != null && params.size() > 0) {
            //设置的查询参数可以和数据库字段一样，实体类的别名不影响
            queryFileds.add(new TextFiled("name", params));
            queryFileds.add(new TextFiled("remark", params));
            queryFileds.add(new TextFiled("createuser_name", params));
            queryFileds.add(new TextFiled("modifyuser_name", params));
            queryFileds.add(new DateRangeFiled("createtime", params));
            queryFileds.add(new DateRangeFiled("modifytime", params));
            queryFileds.add(new TextFiled("issystem_name", params));
            queryFileds.add(new CombFiled("issystem", params));
        }
        PageData pageData = new PageData();
        String[] cmdSqlArray = QueryUntil.getQuerySql("id,name,remark,createtime,createuser,modifytime," +
                "modifyuser,deltag,createuser_name,modifyuser_name,issystem,issystem_name", queryFileds, " and deltag='0' ", "sys_role");
        page.setExec_sql(cmdSqlArray[0]);
        pageData.setData(sysRoleMapper.execSQL(page));
        page.setExec_sql(cmdSqlArray[1]);
        pageData.setRecordsTotal(Integer.parseInt(sysRoleMapper.execSQL(page).get(0).get("count").toString()));
        pageData.setRecordsFiltered(pageData.getRecordsTotal());
        return pageData;
    }

    /**
     * 根据id获取角色
     *
     * @param id 角色id
     * @return 角色信息
     */
    @Cacheable(value = "sysrole", key = "#id")
    public SysRole getRoleById(Integer id) {
        Wrapper<SysRole> sysRoleWrapper = new EntityWrapper<SysRole>();
        sysRoleWrapper.eq("id", id);
        List<SysRole> list = sysRoleMapper.selectList(sysRoleWrapper);
        SysRole sysRole = null;
        if (list != null && list.size() > 0) {
            sysRole = list.get(0);
        }
        return sysRole;
    }

    /**
     * 获取用户已拥有的角色和未拥有的角色用于授权
     *
     * @return
     */
    public List<SysRole> queryRoleListWithSelected(String uid) {
        return sysRoleMapper.queryRoleListWithSelected(uid);
    }

    /**
     * 添加角色
     *
     * @param sysRole 角色信息
     */
    @CacheEvict(value = "sysrole", key = "#sysRole.id")
    public void addRole(SysRole sysRole) {
        try {
            sysRoleMapper.insert(sysRole);
        } catch (Exception e) {
            throw new RuntimeException("添加角色失败，请检查输入的数据");
        }
    }

    /**
     * 修改角色
     *
     * @param sysRole
     */
    @CacheEvict(value = "sysrole", key = "#sysRole.id")
    public void updateRole(SysRole sysRole) {
        try {
            sysRoleMapper.updateById(sysRole);
        } catch (Exception e) {
            throw new RuntimeException("修改角色失败，请检查输入的数据");
        }
    }

    /**
     * 删除角色
     *
     * @param id
     */
    @CacheEvict(value = "sysrole", key = "#id")
    public void delRole(Integer id) {
        Wrapper<SysRole> sysUserWrapper = new EntityWrapper<>();
        sysUserWrapper.eq("id", id);
        SysRole sysRole = new SysRole();
        sysRole.setDeltag("1");
        sysRoleMapper.update(sysRole, sysUserWrapper);
    }
}
