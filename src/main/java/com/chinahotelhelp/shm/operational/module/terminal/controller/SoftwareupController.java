package com.chinahotelhelp.shm.operational.module.terminal.controller;

import com.chinahotelhelp.shm.operational.aspect.AnnotationLog;
import com.chinahotelhelp.shm.operational.module.sys.controller.BaseController;
import com.chinahotelhelp.shm.operational.module.sys.entity.Message;
import com.chinahotelhelp.shm.operational.module.sys.entity.Page;
import com.chinahotelhelp.shm.operational.module.sys.entity.PageData;
import com.chinahotelhelp.shm.operational.module.terminal.entity.Batch;
import com.chinahotelhelp.shm.operational.module.terminal.entity.Ti_softwareup;
import com.chinahotelhelp.shm.operational.module.terminal.service.BatchService;
import com.chinahotelhelp.shm.operational.module.terminal.service.SoftwareupService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Classname SoftwareupController
 * @Description 终端软件升级信息
 * @Date 2019/10/31 11:08
 * @Created by Changliang.yang
 */
@RestController
@RequestMapping("/softwareup")
public class SoftwareupController extends BaseController {
    @Autowired
    private SoftwareupService softwareupService;

    /**
     * 添加终端软件升级信息
     *
     * @param ti_softwareup
     * @return
     */
    @AnnotationLog(value = "添加终端软件升级信息")
    @ApiOperation(value = "添加终端软件升级信息", notes = "添加终端软件升级信息")
    @RequestMapping(value = "add",method = RequestMethod.POST)
    public Message add(@RequestBody Ti_softwareup ti_softwareup) {
        return softwareupService.add(ti_softwareup);
    }

    /**
     * 删除终端软件升级信息
     *
     * @param pk_id
     * @return
     */
    @AnnotationLog(value = "删除终端软件升级信息")
    @ApiOperation(value = "删除终端软件升级信息", notes = "删除终端软件升级信息")
    @RequestMapping(value = "del/{pk_id}",method = RequestMethod.DELETE)
    public Message del(@PathVariable String pk_id) {
        return softwareupService.del(pk_id);
    }

    /**
     * 修改终端软件升级信息
     *
     * @param ti_softwareup
     * @return
     */
    @AnnotationLog(value = "修改终端软件升级信息")
    @ApiOperation(value = "修改终端软件升级信息", notes = "修改终端软件升级信息")
    @RequestMapping(value = "edit",method = RequestMethod.POST)
    public Message edit(@RequestBody Ti_softwareup ti_softwareup) {
        return softwareupService.edit(ti_softwareup);
    }

    /**
     * 查询所有终端软件升级信息
     * @return
     */
    @AnnotationLog(value = "查询所有终端软件升级信息")
    @ApiOperation(value = "查询所有终端软件升级信息", notes = "查询所有终端软件升级信息")
    @RequestMapping(value = "select",method = RequestMethod.GET)
    public PageData selectBatch(Page page) {
        return softwareupService.select(page);
    }
}
