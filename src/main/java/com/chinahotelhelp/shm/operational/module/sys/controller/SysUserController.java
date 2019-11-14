package com.chinahotelhelp.shm.operational.module.sys.controller;


import com.chinahotelhelp.shm.operational.aspect.AnnotationLog;
import com.chinahotelhelp.shm.operational.config.RetryLimitHashedCredentialsMatcher;
import com.chinahotelhelp.shm.operational.module.sys.entity.Message;
import com.chinahotelhelp.shm.operational.module.sys.entity.Page;
import com.chinahotelhelp.shm.operational.module.sys.entity.PageData;
import com.chinahotelhelp.shm.operational.module.sys.entity.SysUser;
import com.chinahotelhelp.shm.operational.module.sys.service.SysUserRoleService;
import com.chinahotelhelp.shm.operational.module.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author Huan.Xia
 * @Title: UserController
 * @ProjectName merchant-management
 * @Description: TODO
 * @date 2018/11/14/01413:36
 */
@Api(description = "用户接口")
@RequestMapping("/user")
@RestController
public class SysUserController extends BaseController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private RetryLimitHashedCredentialsMatcher retryLimitHashedCredentialsMatcher;

    /**
     * 用户列表
     *
     * @param page
     * @return
     */
    @AnnotationLog(value = "分页查询用户信息")
    @ApiOperation(value = "用户列表", notes = "分页查询用户信息")
    @RequestMapping(value = "list",method = RequestMethod.GET)
    @RequiresPermissions("sys:user:list")
    public PageData list(Page page) {
        return sysUserService.getUserList(page);
    }

    /**
     * 用户详情
     *
     * @param username
     * @return
     */
    @AnnotationLog(value = "根据username查询用户详细信息")
    @ApiOperation(value = "用户详情", notes = "根据username查询用户详细信息")
    @RequestMapping(value = "info/{username}",method = RequestMethod.GET)
    @RequiresPermissions("sys:user:info")
    public Message info(@PathVariable String username) {
        SysUser sysUser = sysUserService.getUserByUsername(username);
        sysUser.setPassword("");
        return Message.success(sysUser);
    }

    /**
     * 添加用户
     *
     * @param sysUser
     * @return
     */
    @AnnotationLog(value = "添加用户")
    @ApiOperation(value = "添加用户", notes = "添加用户")
    @RequestMapping(value = "add",method = RequestMethod.POST)
    @RequiresPermissions("sys:user:add")
    public Message Message(@RequestBody SysUser sysUser) {
        sysUser.setCreateuser(super.getCurtUser().getId());
        sysUser.setCreateusername(super.getCurtUser().getUsername());
        sysUser.setModifyuser(super.getCurtUser().getId());
        sysUser.setModifyusername(super.getCurtUser().getUsername());

        return sysUserService.addUser(sysUser);
    }

    /**
     * 修改用户
     *
     * @param sysUser
     * @return
     */
    @AnnotationLog(value = "修改用户信息")
    @ApiOperation(value = "修改用户", notes = "修改用户信息")
    @RequestMapping(value = "update",method = RequestMethod.POST)
    @RequiresPermissions("sys:user:update")
    public Message update(@RequestBody SysUser sysUser) {
        sysUser.setModifytime(new Date());
        sysUser.setModifyuser(super.getCurtUser().getId());
        sysUser.setModifyusername(super.getCurtUser().getUsername());
        sysUserService.updateUser(sysUser);
        return Message.success();
    }
    /**
     * 修改密码
     *
     * @param password
     * @param newPassword
     * @return
     */
    @AnnotationLog(value = "修改用户密码")
    @ApiOperation(value = "修改密码", notes = "修改用户密码")
    @RequestMapping(value = "editPassword",method = RequestMethod.POST)
   // @RequiresPermissions("sys:user:update")
    public Message update(@RequestParam(value = "password") String password,@RequestParam(value = "newPassword") String newPassword) {
        return sysUserService.updatePassword(password,newPassword);
    }

    /**
     * 删除用户
     *
     * @param username
     * @return
     */
    @AnnotationLog(value = "根据username删除用户")
    @ApiOperation(value = "删除用户", notes = "根据username删除用户")
    @RequestMapping(value = "del/{username}",method = RequestMethod.DELETE)
    @RequiresPermissions("sys:user:del")
    public Message del(@PathVariable String username) {
        sysUserService.delUser(username);
        return Message.success();
    }

    /**
     * 用户授角色权限
     */
    @AnnotationLog(value = "对用户授予角色功能")
    @ApiOperation(value = "用户授权", notes = "对用户授予角色功能")
    @RequestMapping(value = "prem",method = RequestMethod.POST)
    @RequiresPermissions("sys:user:prem")
    public Message prem(@RequestParam(value = "roles[]") Integer[] roles, @RequestParam(value = "uid") String uid, @RequestParam(value = "role") String role) {
        sysUserRoleService.prem(roles, uid,role);
        return Message.success();
    }
    /**
     * 根据用户名解锁用户
     *
     * @param username
     * @return
     */
    @AnnotationLog(value = "根据username解锁用户")
    @ApiOperation(value = "根据用户名解锁用户", notes = "根据username解锁用户")
    @RequestMapping(value = "unlock/{username}",method = RequestMethod.POST)
    @RequiresPermissions("sys:user:update")
    public Message unlockAccount(@PathVariable String username) {
        return retryLimitHashedCredentialsMatcher.unlockAccount(username);
    }
}
