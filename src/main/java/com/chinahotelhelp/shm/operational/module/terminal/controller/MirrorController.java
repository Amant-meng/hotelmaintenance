package com.chinahotelhelp.shm.operational.module.terminal.controller;

import com.chinahotelhelp.shm.operational.aspect.AnnotationLog;
import com.chinahotelhelp.shm.operational.module.sys.controller.BaseController;
import com.chinahotelhelp.shm.operational.module.sys.entity.Message;
import com.chinahotelhelp.shm.operational.module.sys.entity.Page;
import com.chinahotelhelp.shm.operational.module.sys.entity.PageData;
import com.chinahotelhelp.shm.operational.module.terminal.entity.Batch;
import com.chinahotelhelp.shm.operational.module.terminal.entity.Mirror_image;
import com.chinahotelhelp.shm.operational.module.terminal.service.BatchService;
import com.chinahotelhelp.shm.operational.module.terminal.service.MirrorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Classname MirrorController
 * @Description TODO
 * @Date 2019/10/31 11:06
 * @Created by Changliang.yang
 */
@RestController
@RequestMapping("/mirror")
public class MirrorController extends BaseController {
    @Autowired
    private MirrorService mirrorService;

    /**
     * 添加镜像
     *
     * @param mirror_image
     * @return
     */
    @AnnotationLog(value = "添加镜像")
    @ApiOperation(value = "添加镜像", notes = "添加镜像")
    @RequestMapping(value = "add",method = RequestMethod.POST)
    public Message addMirror(@RequestBody Mirror_image mirror_image) {
        return mirrorService.addMirror(mirror_image);
    }

    /**
     * 删除镜像
     *
     * @param mi_id
     * @return
     */
    @AnnotationLog(value = "删除镜像")
    @ApiOperation(value = "删除镜像", notes = "删除镜像")
    @RequestMapping(value = "del/{mi_id}",method = RequestMethod.DELETE)
    public Message delMirror(@PathVariable String mi_id) {
        return mirrorService.delMirror(mi_id);
    }

    /**
     * 修改镜像
     *
     * @param mirror_image
     * @return
     */
    @AnnotationLog(value = "修改镜像")
    @ApiOperation(value = "修改镜像", notes = "修改镜像")
    @RequestMapping(value = "edit",method = RequestMethod.POST)
    public Message editMirror(@RequestBody Mirror_image mirror_image) {
        return mirrorService.editMirror(mirror_image);
    }

    /**
     * 查询所有镜像
     * @return
     */
    @AnnotationLog(value = "查询所有镜像")
    @ApiOperation(value = "查询所有镜像", notes = "查询所有镜像")
    @RequestMapping(value = "selectMirror",method = RequestMethod.GET)
    public PageData selectMirror(Page page) {
        return mirrorService.selectMirror(page);
    }

}
