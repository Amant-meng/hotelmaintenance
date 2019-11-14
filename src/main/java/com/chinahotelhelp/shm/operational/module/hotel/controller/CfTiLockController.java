package com.chinahotelhelp.shm.operational.module.hotel.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chinahotelhelp.shm.operational.aspect.AnnotationLog;
import com.chinahotelhelp.shm.operational.module.hotel.entity.CfTiLockConfiguration;
import com.chinahotelhelp.shm.operational.module.hotel.entity.CfTiLockKeyValue;
import com.chinahotelhelp.shm.operational.module.hotel.service.CfTiLockConfigurationService;
import com.chinahotelhelp.shm.operational.module.hotel.service.CfTiLockKeyValueService;
import com.chinahotelhelp.shm.operational.module.sys.controller.BaseController;
import com.chinahotelhelp.shm.operational.module.sys.entity.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 *   @author Meng.yang
 *   @ProjectName
 *   @title: CfTiLockController
 *   @Description: 门锁配置
 *   @date 2019/11/01
*/
@Api(description = "门锁配置接口")
@RequestMapping("/cfTiLock")
@RestController
public class CfTiLockController extends BaseController {

    @Autowired
    private CfTiLockConfigurationService configurationService;

    @Autowired
    private CfTiLockKeyValueService lockKeyValueService;

    @AnnotationLog(value = "添加有规则门锁配置")
    @ApiOperation(value = "添加有规则门锁配置", notes = "添加有规则门锁配置")
    @RequestMapping(value = "addLockConfig",method = RequestMethod.POST)
    public Message addLock(@RequestBody CfTiLockConfiguration lockConfiguration){

        return configurationService.addLockConfiguration(lockConfiguration);
    }


    @AnnotationLog(value = "添加无规则门锁配置")
    @ApiOperation(value = "添加无规则门锁配置", notes = "添加无规则门锁配置")
    @RequestMapping(value = "addNoRuleLock",method = RequestMethod.POST)
    public Message addNoRuleLock(@RequestBody CfTiLockKeyValue cfTiLockKeyValue){

        return lockKeyValueService.addNoRuleLock(cfTiLockKeyValue);
    }


    @AnnotationLog(value = "添加无规则门锁配置")
    @ApiOperation(value = "添加无规则门锁配置", notes = "添加无规则门锁配置")
    @RequestMapping(value = "addNoRuleLocks",method = RequestMethod.POST)
    public Message addNoRuleLocks(@RequestBody String params){

        JSONObject param = JSON.parseObject(params);

        String hi_id = param.getString("hi_id");
        String roomList = param.getString("roomLock");
        return lockKeyValueService.addNoRuleLocks(hi_id,roomList);
    }


    @AnnotationLog(value = "查询无规则门锁配置")
    @ApiOperation(value = "查询无规则门锁配置", notes = "查询无规则门锁配置")
    @RequestMapping(value = "getLockList/{hi_id}",method = RequestMethod.GET)
    public Message getLockList(@PathVariable String hi_id){

        List<Map> lockList = lockKeyValueService.getLockList(hi_id);
        return Message.success(lockList);

    }

    @AnnotationLog(value = "查询有规规则门锁配置")
    @ApiOperation(value = "查询有规规则门锁配置", notes = "查询有规规则门锁配置")
    @RequestMapping(value = "getLockConfig/{hi_id}",method = RequestMethod.GET)
    public Message getLockConfig(@PathVariable String hi_id){

        CfTiLockConfiguration lockList = configurationService.getLockConfig(hi_id);
        return Message.success(lockList);

    }

}
