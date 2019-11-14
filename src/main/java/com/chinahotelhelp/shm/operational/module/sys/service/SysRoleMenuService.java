package com.chinahotelhelp.shm.operational.module.sys.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.chinahotelhelp.shm.operational.module.sys.entity.SysRoleMenu;
import com.chinahotelhelp.shm.operational.module.sys.mapper.SysRoleMenuMapper;
import com.chinahotelhelp.shm.operational.tools.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * @Auther: 杨昌亮
 * @Date: 2018/11/23 17:48
 * @Description:
 */
@Service
public class SysRoleMenuService {
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    /**
     * 角色授权
     */
    public void prem(Integer[] menus, String rid) {
        try {
            Wrapper<SysRoleMenu> sysUserRoleWrapper = new EntityWrapper<>();
            sysUserRoleWrapper.eq("rid", rid);
            //删除角色菜单表里的信息
            sysRoleMenuMapper.delete(sysUserRoleWrapper);
            //将新信息插入到角色菜单表中
            for (Integer menu : menus) {
                String id = ShiroUtils.getUserEntity().getId();
                SysRoleMenu rm = new SysRoleMenu();
                rm.setRid(rid);
                rm.setCreateuser(id);
                rm.setMid(menu);
                rm.setCreatetime(new Date());
                sysRoleMenuMapper.insert(rm);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}