package com.chinahotelhelp.shm.operational.module.sys.controller;

import com.chinahotelhelp.shm.operational.aspect.AnnotationLog;
import com.chinahotelhelp.shm.operational.module.sys.entity.Message;
import com.chinahotelhelp.shm.operational.module.sys.entity.Page;
import com.chinahotelhelp.shm.operational.module.sys.entity.PageData;
import com.chinahotelhelp.shm.operational.module.sys.entity.SysLog;
import com.chinahotelhelp.shm.operational.module.sys.service.SysLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YangMeng
 * @Title: SysLogController
 * @ProjectName merchant-management
 * @Description: 系统日志
 * @date 2018/12/14
 */
@Api(description = "系统日志接口")
@RequestMapping("/log")
@RestController
public class SysLogController extends BaseController{

    @Autowired
    private SysLogService sysLogService;

    /**
     * 获取系统日志信息
     * @param page
     * @return
     */
    @AnnotationLog(value = "分页查询系统日志信息")
    @ApiOperation(value = "系统日志", notes = "分页查询系统日志信息")
    @RequestMapping(value = "list",method = RequestMethod.GET)
    @RequiresPermissions("sys:log")
    public PageData list(Page page){

        return sysLogService.getLogList(page);
    }

    /**
     * 通过id查询日志详情
     * @param id
     * @return
     */
    @AnnotationLog(value = "通过id查询日志详情")
    @RequestMapping(value = "info/{id}",method = RequestMethod.GET)
    @RequiresPermissions("sys:log:info")
    public  Message info(@PathVariable String id){
        SysLog sysLog = sysLogService.getLogDetailsById(id);
        return Message.success(sysLog);
    }

}
