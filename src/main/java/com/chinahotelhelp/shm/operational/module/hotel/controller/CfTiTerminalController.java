package com.chinahotelhelp.shm.operational.module.hotel.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chinahotelhelp.shm.operational.aspect.AnnotationLog;
import com.chinahotelhelp.shm.operational.module.hotel.entity.CfTiTerminal;
import com.chinahotelhelp.shm.operational.module.hotel.service.CfTiTerminalService;
import com.chinahotelhelp.shm.operational.module.sys.controller.BaseController;
import com.chinahotelhelp.shm.operational.module.sys.entity.Message;
import com.chinahotelhelp.shm.operational.module.sys.entity.Page;
import com.chinahotelhelp.shm.operational.module.sys.entity.PageData;
import com.chinahotelhelp.shm.operational.module.template.entity.CfPtConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *   @author Meng.yang
 *   @ProjectName
 *   @title: CfTiTerminalController
 *   @Description: 终端信息接口
 *   @date 2019/10/23
*/

@Api(description = "终端信息接口")
@RequestMapping("/cfTiTerminal")
@RestController
@Slf4j
public class CfTiTerminalController extends BaseController {

    @Autowired
    private CfTiTerminalService cfTiTerminalService;

    @AnnotationLog(value = "获取终端列表")
    @ApiOperation(value = "获取终端列表", notes = "分页获取终端列表")
    @RequestMapping(value = "getTerminalList",method = RequestMethod.GET)
    public PageData getTerminalList(Page page){

        return cfTiTerminalService.getTerminalList(page);
    }

    @AnnotationLog(value = "删除终端信息")
    @ApiOperation(value = "删除终端信息", notes = "删除终端信息")
    @RequestMapping(value = "delTerminal/{ti_id}",method = RequestMethod.DELETE)
    public Message delTerminal(@PathVariable String ti_id){
        cfTiTerminalService.delTerminal(ti_id);
        return Message.success();
    }

    @AnnotationLog(value = "更新终端信息")
    @ApiOperation(value = "更新终端信息", notes = "更新终端信息")
    @RequestMapping(value = "updateTerminal",method = RequestMethod.POST)
    public Message updateTerminal(@RequestBody CfTiTerminal cfTiTerminal){
        cfTiTerminalService.updateTerminal(cfTiTerminal);
        return Message.success();
    }

    @AnnotationLog(value = "通过终端ID获取终端信息")
    @ApiOperation(value = "通过终端ID获取终端信息", notes = "通过终端ID获取终端信息")
    @RequestMapping(value = "getTerminalInfoById/{ti_id}",method = RequestMethod.GET)
    public Message getTerminalInfoById(@PathVariable String ti_id){

        return cfTiTerminalService.getTerminalInfoById(ti_id);
    }

    @AnnotationLog(value = "添加终端信息")
    @ApiOperation(value = "添加终端信息", notes = "添加终端信息")
    @RequestMapping(value = "addTerminal",method = RequestMethod.POST)
    public Message addTerminal(@RequestBody CfTiTerminal cfTiTerminal){

        return cfTiTerminalService.addTerminal(cfTiTerminal);
    }

    @AnnotationLog(value = "复制终端信息")
    @ApiOperation(value = "复制终端信息", notes = "复制终端信息")
    @RequestMapping(value = "copyTerminal/{hi_id}/{ti_id}",method = RequestMethod.POST)
    public Message copyTerminal(@PathVariable String hi_id,@PathVariable String ti_id){
        return cfTiTerminalService.copyTerminal(hi_id,ti_id);
    }

    @AnnotationLog(value = "根据终端ID获取模板")
    @ApiOperation(value = "根据终端ID获取模板", notes = "根据终端ID获取模板")
    @RequestMapping(value = "getTemplateByTiId/{ti_id}",method = RequestMethod.GET)
    public Message getTemplateByTiId(@PathVariable String ti_id){

        List<Map> mapList = cfTiTerminalService.getTemplateByTiId(ti_id);
        return Message.success(mapList);
    }

    @AnnotationLog(value = "根据终端ID及模板ID获取模板参数")
    @ApiOperation(value = "根据终端ID及模板ID获取模板参数", notes = "根据终端ID及模板ID获取模板参数")
    @RequestMapping(value = "getTemplateParasByTiId/{ti_id}/{temp_id}",method = RequestMethod.GET)
    public Message getTemplateParasByTiId(@PathVariable String ti_id,@PathVariable String temp_id){

        List<Map> templateParasList = cfTiTerminalService.getTemplateParasByTiId(ti_id,temp_id);
        return Message.success(templateParasList);
    }

    @AnnotationLog(value = "配置终端参数值")
    @ApiOperation(value = "配置终端参数值", notes = "配置终端参数值")
    @RequestMapping(value = "addTerminalParamValue",method = RequestMethod.POST)
    public Message addTerminalParamValue(@RequestBody String cfPtConfig){
        ArrayList<CfPtConfig> configList=new ArrayList<>();

        JSONObject jsonObject = JSONObject.parseObject(cfPtConfig);
        JSONArray jsonArray = JSONArray.parseArray(jsonObject.getString("data"));

        for (Object object : jsonArray) {
            CfPtConfig config = JSONObject.parseObject(object.toString(), CfPtConfig.class);
            configList.add(config);
        }

        return cfTiTerminalService.addTerminalParamValue(configList);
    }
}
