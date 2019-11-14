package com.chinahotelhelp.shm.operational.module.sys.controller;

import com.chinahotelhelp.shm.operational.aspect.AnnotationLog;
import com.chinahotelhelp.shm.operational.module.sys.entity.Message;
import com.chinahotelhelp.shm.operational.module.sys.entity.Page;
import com.chinahotelhelp.shm.operational.module.sys.entity.PageData;
import com.chinahotelhelp.shm.operational.module.sys.entity.SysMessage;
import com.chinahotelhelp.shm.operational.module.sys.service.SysMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author YangMeng
 * @Title: SysMessageController
 * @ProjectName merchant-management
 * @Description: 消息提醒
 * @date 2018/12/14
 */
@Api(description = "消息提醒接口")
@RequestMapping("/message")
@RestController
public class SysMessageController extends BaseController {

    @Autowired
    private SysMessageService sysMessageService;


    /**
     * 根据传入参数查询消息列表
     * @param page
     * @return
     */
    @AnnotationLog(value = "分页查询消息提醒信息")
    @ApiOperation(value = "消息提醒", notes = "分页查询消息提醒信息")
    @RequestMapping(value = "sysMessageList",method = RequestMethod.GET)
    @RequiresPermissions("sys:message:list")
    public PageData sysMessageList(Page page) {

        return sysMessageService.getSysMessage(page);
    }

    /**
     * 更新消息状态
     * @param sysMessage
     * @return
     */
    @AnnotationLog(value = "更新消息状态")
    @ApiOperation(value = "更新消息状态", notes = "更新消息状态")
    @RequestMapping(value = "update",method = RequestMethod.POST)
    @RequiresPermissions("sys:message:update")
    public Message update(@RequestBody SysMessage sysMessage){
        sysMessageService.updateMessageStatus(sysMessage);
        return Message.success();
    }

    /**
     * 更新消息全部为已读
     * @return
     */
    @AnnotationLog(value = "更新消息全部为已读")
    @ApiOperation(value = "更新消息全部为已读", notes = "更新消息全部为已读")
    @RequestMapping(value = "updatelist",method = RequestMethod.POST)
    @RequiresPermissions("sys:message:update")
    public Message update(){
        sysMessageService.updateMessageList();
        return Message.success();
    }

}
