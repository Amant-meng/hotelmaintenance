package com.chinahotelhelp.shm.operational.module.hotel.controller;

import com.chinahotelhelp.shm.operational.aspect.AnnotationLog;
import com.chinahotelhelp.shm.operational.module.hotel.entity.CfTiBloc;
import com.chinahotelhelp.shm.operational.module.hotel.service.CfTiBlocService;
import com.chinahotelhelp.shm.operational.module.sys.controller.BaseController;
import com.chinahotelhelp.shm.operational.module.sys.entity.Message;
import com.chinahotelhelp.shm.operational.module.sys.entity.Page;
import com.chinahotelhelp.shm.operational.module.sys.entity.PageData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *   @author Meng.yang
 *   @ProjectName
 *   @title: CfTiBlocController
 *   @Description: 集团信息接口
 *   @date 2019/10/23
*/

@Api(description = "集团信息接口")
@RequestMapping("/cfTiBloc")
@RestController
public class CfTiBlocController extends BaseController {

    @Autowired
    private CfTiBlocService cfTiBlocService;

    @AnnotationLog(value = "获取集团列表")
    @ApiOperation(value = "获取集团列表", notes = "分页获取集团列表")
    @RequestMapping(value = "getBlocList",method = RequestMethod.GET)
    public PageData getBlocList(Page page){

        return cfTiBlocService.getBlocList(page);
    }

    @AnnotationLog(value = "删除集团信息")
    @ApiOperation(value = "删除集团信息", notes = "删除集团信息")
    @RequestMapping(value = "delBloc/{bloc_id}",method = RequestMethod.DELETE)
    public Message delBloc(@PathVariable String bloc_id){
        cfTiBlocService.delBloc(bloc_id);
        return Message.success();
    }

    @AnnotationLog(value = "更新集团信息")
    @ApiOperation(value = "更新集团信息", notes = "更新集团信息")
    @RequestMapping(value = "updateBloc",method = RequestMethod.POST)
    public Message updateBloc(@RequestBody CfTiBloc cfTiBloc){
        cfTiBlocService.updateBloc(cfTiBloc);
        return Message.success();
    }

    @AnnotationLog(value = "通过集团ID查询集团信息")
    @ApiOperation(value = "通过集团ID查询集团信息", notes = "通过集团ID查询集团信息")
    @RequestMapping(value = "getBlocInfoById/{bloc_id}",method = RequestMethod.GET)
    public Message getBlocInfoById(@PathVariable String bloc_id){

        CfTiBloc cfTiBloc = cfTiBlocService.getBlocInfoById(bloc_id);
        return Message.success(cfTiBloc);
    }

    @AnnotationLog(value = "添加集团信息")
    @ApiOperation(value = "添加集团信息", notes = "添加集团信息")
    @RequestMapping(value = "addBloc",method = RequestMethod.POST)
    public Message addBloc(@RequestBody CfTiBloc cfTiBloc){

        return cfTiBlocService.addBloc(cfTiBloc);
    }

}
