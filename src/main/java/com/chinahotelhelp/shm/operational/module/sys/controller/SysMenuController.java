package com.chinahotelhelp.shm.operational.module.sys.controller;

import com.chinahotelhelp.shm.operational.aspect.AnnotationLog;
import com.chinahotelhelp.shm.operational.module.sys.entity.Message;
import com.chinahotelhelp.shm.operational.module.sys.entity.SysMenu;
import com.chinahotelhelp.shm.operational.module.sys.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Auther: 杨昌亮
 * @Date: 2018/11/9 09:21
 * @Description:
 */
@Api(description = "菜单接口")
@RestController
@RequestMapping("menu")
public class SysMenuController extends BaseController {
    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 编辑菜单
     *
     * @param sysMenu
     * @return
     */
    @AnnotationLog(value = "修改菜单")
    @ApiOperation(value = "修改菜单", notes = "修改当前菜单信息")
    @RequestMapping(value = "edit",method = RequestMethod.POST)
    @RequiresPermissions("sys:menu:edit")
    public Message editMenu(@RequestBody SysMenu sysMenu) {
        sysMenu.setModifytime(new Date());
        sysMenu.setModifyuser(super.getCurtUser().getUsername());
        sysMenuService.updateMenu(sysMenu);
        return Message.success();
    }

    /**
     * 删除菜单
     *
     * @param id
     * @return
     */
    @AnnotationLog(value = "删除菜单")
    @ApiOperation(value = "删除菜单", notes = "根据id删除菜单")
    @RequestMapping(value = "del/{id}",method = RequestMethod.DELETE)
    @RequiresPermissions("sys:menu:del")
    public Message delMenu(@PathVariable Integer id) {
        sysMenuService.delMenu(id);
        return Message.success();
    }

    /**
     * 获取菜单列表
     *
     * @return
     */
    @AnnotationLog(value = "获取菜单列表")
    @ApiOperation(value = "菜单列表", notes = "获取所有的菜单列表")
    @RequestMapping(value = "list",method = RequestMethod.GET)
    @RequiresPermissions("sys:menu:list")
    public List<SysMenu> getMenuList() {
        return sysMenuService.getMenuList();
    }

    /**
     * 添加菜单
     *
     * @return
     */
    @AnnotationLog(value = "添加菜单")
    @ApiOperation(value = "添加菜单", notes = "添加菜单")
    @RequestMapping(value = "add",method = RequestMethod.POST)
    @RequiresPermissions("sys:menu:add")
    public Message addMenu(@RequestBody SysMenu sysMenu) {
        sysMenu.setCreatetime(new Date());
        sysMenu.setCreateuser(super.getCurtUser().getUsername());
        sysMenu.setModifyuser(super.getCurtUser().getUsername());
        sysMenu.setModifytime(new Date());
        sysMenuService.addMenu(sysMenu);
        return Message.success();
    }

    /**
     * 菜单信息
     *
     * @param id id
     * @return
     */
    @AnnotationLog(value = "根据id获取菜单详细信息")
    @ApiOperation(value = "菜单详情", notes = "根据id获取菜单详细信息")
    @RequestMapping(value = "info/{id}",method = RequestMethod.GET)
    @RequiresPermissions("sys:menu:info")
    public Message info(@PathVariable Integer id) {
        SysMenu sysMenu = sysMenuService.getMenuInfo(id);
        return Message.success(sysMenu);
    }

    /**
     * 获取菜单用于角色的授权
     */
    @AnnotationLog(value = "获取菜单用于角色的授权")
    @ApiOperation(value = "获取选中菜单", notes = "根据角色id获取菜单用于角色的菜单授权，selected：0未选中 1已选中")
    @RequestMapping(value = "role/{rid}",method = RequestMethod.GET)
    @RequiresPermissions("sys:menu:list")
    public Message menusWithSelected(@PathVariable Integer rid) {
        List<SysMenu> sysMenus = sysMenuService.queryMenuListWithSelected(rid);
        return Message.success(sysMenus);
    }
}