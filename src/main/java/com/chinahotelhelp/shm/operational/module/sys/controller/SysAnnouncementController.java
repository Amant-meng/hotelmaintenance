package com.chinahotelhelp.shm.operational.module.sys.controller;

import com.chinahotelhelp.shm.operational.aspect.AnnotationLog;
import com.chinahotelhelp.shm.operational.module.sys.entity.Message;
import com.chinahotelhelp.shm.operational.module.sys.entity.Page;
import com.chinahotelhelp.shm.operational.module.sys.entity.PageData;
import com.chinahotelhelp.shm.operational.module.sys.entity.SysAnnouncement;
import com.chinahotelhelp.shm.operational.module.sys.service.SysAnnouncementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author YangMeng
 * @Title: SysAnnouncementController
 * @ProjectName merchant-management
 * @Description: 系统通告
 * @date 2019/03/01
 */
@Api(description = "系统通告接口")
@RequestMapping("/SysAnnouncement")
@RestController
public class SysAnnouncementController extends BaseController{

    @Autowired
    private SysAnnouncementService sysAnnouncementService;

    /**
     * 查询系统通告
     * @param page
     * @return
     */
    @AnnotationLog(value = "查询系统通告")
    @ApiOperation(value = "查询系统通告", notes = "查询系统通告")
    @RequestMapping(value = "sysAnnouncementList", method = RequestMethod.GET)
    @RequiresPermissions("sys:announcement:list")
    public PageData getSysAnnouncementList(Page page){
        return sysAnnouncementService.getSysAnnouncementList(page);
    }


    /**
     * 通过id查询系统通告
     * @param id
     * @return
     */
    @ApiOperation(value = "通过id查询系统通告", notes = "通过id查询系统通告")
    @RequestMapping(value = "info/{id}", method = RequestMethod.GET)
    @RequiresPermissions("sys:announcement:info")
    public Message info(@PathVariable String id){
        SysAnnouncement sysAnnouncement = sysAnnouncementService.getSysAnnouncementId(id);
        return Message.success(sysAnnouncement);
    }

    /**
     * 添加系统通告
     * @param sysAnnouncement
     * @return
     */
    @AnnotationLog(value = "添加系统通告")
    @ApiOperation(value = "添加系统通告", notes = "添加系统通告")
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @RequiresPermissions("sys:announcement:add")
    public Message add(@RequestBody SysAnnouncement sysAnnouncement){

        sysAnnouncementService.addSysAnnouncement(sysAnnouncement);
        return Message.success();
    }

    /**
     * 删除系统通告
     * @param id
     * @return
     */
    @AnnotationLog(value = "删除系统通告")
    @ApiOperation(value = "查询系统通告", notes = "查询系统通告")
    @RequestMapping(value = "del/{id}", method = RequestMethod.DELETE)
    @RequiresPermissions("sys:announcement:del")
    public Message delete(@PathVariable String id){

        sysAnnouncementService.delSysAnnouncement(id);
        return Message.success();
    }

    /**
     * 修改系统通告
     * @param sysAnnouncement
     * @return
     */
    @AnnotationLog(value = "修改系统通告")
    @ApiOperation(value = "修改系统通告", notes = "修改系统通告")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @RequiresPermissions("sys:announcement:update")
    public Message update(@RequestBody SysAnnouncement sysAnnouncement){
        sysAnnouncementService.updateSysAnnouncement(sysAnnouncement);
        return Message.success();
    }
}
