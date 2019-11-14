package com.chinahotelhelp.shm.operational.module.template.controller;

import com.chinahotelhelp.shm.operational.aspect.AnnotationLog;
import com.chinahotelhelp.shm.operational.module.sys.entity.Message;
import com.chinahotelhelp.shm.operational.module.sys.entity.Page;
import com.chinahotelhelp.shm.operational.module.sys.entity.PageData;
import com.chinahotelhelp.shm.operational.module.template.entity.Template;
import com.chinahotelhelp.shm.operational.module.template.service.TemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by wuyang on 2019/10/23 9:53
 */
@Api(description = "模板管理")
@RequestMapping("/template")
@RestController
public class TemplateController {


    @Autowired
    private TemplateService templateService;


    @AnnotationLog(value = "模板添加接口")
    @ApiOperation(value = "模板添加接口", notes = "模板添加接口")
    @RequestMapping(value = "addTemplate",method = RequestMethod.POST)
    public Message addTemplate(@RequestBody Template template){
        return templateService.add(template);
    }

    @AnnotationLog(value = "模板修改接口")
    @ApiOperation(value = "模板修改接口", notes = "模板修改接口")
    @RequestMapping(value = "modifyTemplate",method = RequestMethod.POST)
    public Message modifyTemplate(@RequestBody Template template){
        return templateService.modify(template);
    }

    @AnnotationLog(value = "模板删除接口")
    @ApiOperation(value = "模板删除接口", notes = "模板删除接口")
    @RequestMapping(value = "deleteTemplate/{temp_id}",method = RequestMethod.POST)
    public Message deleteTemplate(@PathVariable String temp_id){
       return templateService.delete(temp_id);
    }

    @AnnotationLog(value = "查询模板接口")
    @ApiOperation(value = "查询模板接口", notes = "查询模板接口")
    @RequestMapping(value = "queryTemplate",method = RequestMethod.GET)
    public PageData queryTemplate(Page page){
        return templateService.getTemplate(page);
    }

    @AnnotationLog(value = "根据模板ID获取模板详情")
    @ApiOperation(value = "根据模板ID获取模板详情", notes = "根据模板ID获取模板详情")
    @RequestMapping(value = "getTemplateById/{temp_id}",method = RequestMethod.GET)
    public Message getTemplateById(@PathVariable String temp_id){
        return templateService.getTemplateById(temp_id);
    }

    @AnnotationLog(value = "获取全部模板")
    @ApiOperation(value = "获取全部模板", notes = "获取全部模板")
    @RequestMapping(value = "getAllTemplate",method = RequestMethod.GET)
    public Message getAllTemplate(){
        return templateService.getAllTemplate();
    }
}
