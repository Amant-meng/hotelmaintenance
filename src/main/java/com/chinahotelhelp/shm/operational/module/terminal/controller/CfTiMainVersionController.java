package com.chinahotelhelp.shm.operational.module.terminal.controller;

import com.chinahotelhelp.shm.operational.aspect.AnnotationLog;
import com.chinahotelhelp.shm.operational.module.sys.controller.BaseController;
import com.chinahotelhelp.shm.operational.module.sys.entity.Message;
import com.chinahotelhelp.shm.operational.module.sys.entity.Page;
import com.chinahotelhelp.shm.operational.module.sys.entity.PageData;
import com.chinahotelhelp.shm.operational.module.terminal.entity.CfTiMainVersion;
import com.chinahotelhelp.shm.operational.module.terminal.service.CfTiMainVersionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *   @author Meng.yang
 *   @ProjectName
 *   @title: CfTiMainVersionController
 *   @Description: 版本管理接口
 *   @date 2019/11/05
*/
@RestController
@RequestMapping("/cfTiMainVersion")
public class CfTiMainVersionController extends BaseController {

    @Autowired
    private CfTiMainVersionService mainVersionService;

    @AnnotationLog(value = "获取版本信息列表")
    @ApiOperation(value = "获取版本信息列表", notes = "获取版本信息列表")
    @RequestMapping(value = "getVersionList",method = RequestMethod.GET)
    public PageData getVersionList(Page page){

        return mainVersionService.getVersionList(page);
    }

    @AnnotationLog(value = "删除版本信息")
    @ApiOperation(value = "删除版本信息", notes = "删除版本信息")
    @RequestMapping(value = "delVersion/{pk_id}",method = RequestMethod.DELETE)
    public Message delVersion(@PathVariable String pk_id){
        return mainVersionService.delVersion(pk_id);
    }

    @AnnotationLog(value = "更新版本信息")
    @ApiOperation(value = "更新版本信息", notes = "更新版本信息")
    @RequestMapping(value = "updateVersion",method = RequestMethod.POST)
    public Message updateVersion(@RequestBody CfTiMainVersion cfTiMainVersion){
        return mainVersionService.updateVersion(cfTiMainVersion);
    }

    @AnnotationLog(value = "通过pk_id查询版本信息")
    @ApiOperation(value = "通过pk_id查询版本信息", notes = "通过pk_id查询版本信息")
    @RequestMapping(value = "getVersionInfoById/{pk_id}",method = RequestMethod.GET)
    public Message getVersionInfoById(@PathVariable String pk_id){

        CfTiMainVersion cfTiMainVersion = mainVersionService.getVersionInfoById(pk_id);
        return Message.success(cfTiMainVersion);
    }

    @AnnotationLog(value = "添加版本信息")
    @ApiOperation(value = "添加版本信息", notes = "添加版本信息")
    @RequestMapping(value = "addVersion",method = RequestMethod.POST)
    public Message addVersion(@RequestBody CfTiMainVersion cfTiMainVersion){

        return mainVersionService.addVersion(cfTiMainVersion);
    }


}
