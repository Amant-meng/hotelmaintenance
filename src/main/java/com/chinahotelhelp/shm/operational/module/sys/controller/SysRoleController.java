package com.chinahotelhelp.shm.operational.module.sys.controller;

import com.chinahotelhelp.shm.operational.aspect.AnnotationLog;
import com.chinahotelhelp.shm.operational.module.sys.entity.Message;
import com.chinahotelhelp.shm.operational.module.sys.entity.Page;
import com.chinahotelhelp.shm.operational.module.sys.entity.PageData;
import com.chinahotelhelp.shm.operational.module.sys.entity.SysRole;
import com.chinahotelhelp.shm.operational.module.sys.service.SysRoleMenuService;
import com.chinahotelhelp.shm.operational.module.sys.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Auther: 杨昌亮
 * @Date: 2018/11/8 20:28
 * @Description:
 */
@Api(description = "角色接口")
@RestController
@RequestMapping("role")
public class SysRoleController extends BaseController {
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    /**
     * 获取角色列表
     *
     * @return 分页信息
     */
    @AnnotationLog(value = "获取角色列表")
    @ApiOperation(value = "角色列表", notes = "分页查询角色信息")
    @RequestMapping(value = "list",method = RequestMethod.GET)
    @RequiresPermissions("sys:role:list")
    public PageData getRoleList(Page page) {
        return sysRoleService.getRoleList(page);
    }

    /**
     * 添加角色
     *
     * @param sysRole 角色对象
     * @return
     */
    @AnnotationLog(value = "添加角色")
    @ApiOperation(value = "添加角色", notes = "添加角色")
    @RequestMapping(value = "add",method = RequestMethod.POST)
    @RequiresPermissions("sys:role:add")
    public Message addRole(@RequestBody SysRole sysRole) {
        sysRole.setCreateuser(super.getCurtUser().getId());
        sysRole.setCreateusername(super.getCurtUser().getUsername());
        sysRole.setModifyusername(super.getCurtUser().getUsername());
        sysRole.setCreatetime(new Date());
        sysRole.setModifyuser(super.getCurtUser().getId());
        sysRole.setModifytime(new Date());
        sysRoleService.addRole(sysRole);
        return Message.success();
    }

    /**
     * 编辑角色
     *
     * @param sysRole 角色对象
     * @return
     */
    @AnnotationLog(value = "修改角色信息")
    @ApiOperation(value = "修改角色", notes = "修改角色信息")
    @RequestMapping(value = "edit",method = RequestMethod.POST)
    @RequiresPermissions("sys:role:edit")
    public Message editRole(@RequestBody SysRole sysRole) {
        sysRole.setModifytime(new Date());
        sysRole.setModifyuser(super.getCurtUser().getId());
        sysRole.setModifyusername(super.getCurtUser().getUsername());
        sysRoleService.updateRole(sysRole);
        return Message.success();
    }

    /**
     * 删除角色
     *
     * @param id 角色id
     * @return
     */
    @AnnotationLog(value = "根据id删除角色")
    @ApiOperation(value = "删除角色", notes = "根据id删除角色")
    @RequestMapping(value = "del/{id}",method = RequestMethod.DELETE)
    @RequiresPermissions("sys:role:del")
    public Message delRole(@PathVariable Integer id) {
        sysRoleService.delRole(id);
        return Message.success();
    }

    /**
     * 获取角色信息
     *
     * @param id 角色id
     * @return
     */
    @AnnotationLog(value = "根据id获取当前角色详细信息")
    @ApiOperation(value = "角色详情", notes = "根据id获取当前角色详细信息")
    @RequestMapping(value = "info/{id}",method = RequestMethod.GET)
    @RequiresPermissions("sys:role:info")
    public Message info(@PathVariable Integer id) {
        SysRole role = sysRoleService.getRoleById(id);
        return Message.success(role);
    }

    /**
     * 获取角色用于用户的角色授权
     */
    @AnnotationLog(value = "获取角色用于用户的角色授权")
    @ApiOperation(value = "获取选中角色", notes = "根据用户id获取角色用于用户的角色授权，selected：0未选中 1已选中")
    @RequestMapping(value = "user/{uid}",method = RequestMethod.GET)
    @RequiresPermissions("sys:role:list")
    public Message rolesWithSelected(@PathVariable String uid) {
        List<SysRole> sysRoles = sysRoleService.queryRoleListWithSelected(uid);
        return Message.success(sysRoles);
    }

    /**
     * 角色授菜单权限
     */
    @AnnotationLog(value = "角色授菜单权限")
    @ApiOperation(value = "角色授权", notes = "对角色授予菜单功能")
    @RequestMapping(value = "prem",method = RequestMethod.POST)
    @RequiresPermissions("sys:role:prem")
    public Message prem(@RequestParam(value = "menus[]") Integer[] menus, @RequestParam(value = "rid") String rid) {
        sysRoleMenuService.prem(menus, rid);
        return Message.success();
    }
}