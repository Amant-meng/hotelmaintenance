package com.chinahotelhelp.shm.operational.module.template.controller;

import com.chinahotelhelp.shm.operational.aspect.AnnotationLog;
import com.chinahotelhelp.shm.operational.module.sys.entity.Message;
import com.chinahotelhelp.shm.operational.module.sys.entity.Page;
import com.chinahotelhelp.shm.operational.module.sys.entity.PageData;
import com.chinahotelhelp.shm.operational.module.template.entity.ParasStructure;
import com.chinahotelhelp.shm.operational.module.template.service.ParasService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by wuyang on 2019/10/23 9:54
 */
@Api(description = "参数结构管理")
@RequestMapping("/parasStructure")
@RestController
public class ParasController {

    @Autowired
    private ParasService parasService;

    @AnnotationLog(value = "参数结构管理")
    @ApiOperation(value = "参数结构管理", notes = "添加参数结构接口")
    @RequestMapping(value = "addParas",method = RequestMethod.POST)
    public Message addParas(@RequestBody ParasStructure parasStructure){
        return parasService.addParas(parasStructure);
    }

    @AnnotationLog(value = "参数结构管理")
    @ApiOperation(value = "参数结构管理", notes = "修改参数结构接口")
    @RequestMapping(value = "modifyParas",method = RequestMethod.POST)
    public Message modifyParas(@RequestBody ParasStructure parasStructure){
        return parasService.modifyParas(parasStructure);
    }

    @AnnotationLog(value = "参数结构管理")
    @ApiOperation(value = "参数结构管理", notes = "删除参数结构接口")
    @RequestMapping(value = "deleteParas/{p_id}",method = RequestMethod.POST)
    public Message deleteParas(@PathVariable String p_id){
        return parasService.deleteParas(p_id);
    }

    @AnnotationLog(value = "参数结构管理")
    @ApiOperation(value = "参数结构管理", notes = "查询参数结构接口")
    @RequestMapping(value = "queryParas",method = RequestMethod.GET)
    public PageData queryParas(Page page){
        return parasService.getParas(page);
    }

    @AnnotationLog(value = "根据参数ID获取参数信息")
    @ApiOperation(value = "根据参数ID获取参数信息", notes = "根据参数ID获取参数信息")
    @RequestMapping(value = "getParasByid/{p_id}",method = RequestMethod.GET)
    public Message getParasByid(@PathVariable String p_id){
        return parasService.getParasByid(p_id);
    }

    @AnnotationLog(value = "根据参数类型类别获取参数信息")
    @ApiOperation(value = "根据参数类型类别获取参数信息", notes = "根据参数类型类别获取参数信息")
    @RequestMapping(value = "getParasByTC",method = RequestMethod.GET)
    public Message getParasByTC(String type,String category){
        return parasService.getParasByTC(type,category);
    }
}
