package com.chinahotelhelp.shm.operational.module.sys.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.chinahotelhelp.shm.operational.module.sys.entity.SysUser;
import com.chinahotelhelp.shm.operational.module.sys.entity.SysUserRole;
import com.chinahotelhelp.shm.operational.module.sys.mapper.SysUserMapper;
import com.chinahotelhelp.shm.operational.module.sys.mapper.SysUserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: 杨昌亮
 * @Date: 2018/11/23 11:40
 * @Description:
 */
@Service
public class SysUserRoleService {
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 用户授权
     */
    @Transactional
    public void prem(Integer[] roles, String uid, String role) {

        Wrapper<SysUserRole> sysUserRoleWrapper = new EntityWrapper<>();
        sysUserRoleWrapper.eq("uid", uid);
        //删除用户角色表里的信息
        sysUserRoleMapper.delete(sysUserRoleWrapper);
        //将新信息插入到用户角色表中
        for (Integer roleId : roles) {
            SysUserRole urole = new SysUserRole();
            urole.setUid(uid);
            urole.setRid(roleId);
            sysUserRoleMapper.insert(urole);
        }
        SysUser user = sysUserService.getUserById(uid);
        user.setRole(role);
        sysUserMapper.updateById(user);

    }
}