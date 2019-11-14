package com.chinahotelhelp.shm.operational.module.log.controller;

import com.chinahotelhelp.shm.operational.aspect.AnnotationLog;
import com.chinahotelhelp.shm.operational.module.log.entity.LogParams;
import com.chinahotelhelp.shm.operational.module.log.service.LogService;
import com.chinahotelhelp.shm.operational.module.sys.entity.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wuyang on 2019/11/5 10:52
 */
@Api(description = "终端日志")
@RequestMapping("/terminalLog")
@RestController
public class LogController {

    @Autowired
    private LogService logService;

    @AnnotationLog(value = "查询终端日志")
    @ApiOperation(value = "查询终端日志", notes = "查询终端日志")
    @RequestMapping(value = "getTerminalLog",method = RequestMethod.POST)
    public Message getTerminalLog(@RequestBody LogParams logParams){
        return logService.getTerminalLog(logParams);
    }

    @AnnotationLog(value = "统计异常终端")
    @ApiOperation(value = "统计异常终端", notes = "统计异常终端")
    @RequestMapping(value = "getTiState",method = RequestMethod.GET)
    public Message getTiState(){
        return logService.getTiState();
    }
}
