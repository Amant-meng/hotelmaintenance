package com.chinahotelhelp.shm.operational.module.terminal.controller;

import com.chinahotelhelp.shm.operational.aspect.AnnotationLog;
import com.chinahotelhelp.shm.operational.module.sys.controller.BaseController;
import com.chinahotelhelp.shm.operational.module.sys.entity.Message;
import com.chinahotelhelp.shm.operational.module.sys.entity.Page;
import com.chinahotelhelp.shm.operational.module.sys.entity.PageData;
import com.chinahotelhelp.shm.operational.module.terminal.entity.Batch;
import com.chinahotelhelp.shm.operational.module.terminal.entity.Softwareinfo;
import com.chinahotelhelp.shm.operational.module.terminal.service.BatchService;
import com.chinahotelhelp.shm.operational.module.terminal.service.SoftwareinfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Classname SoftwareinfoController
 * @Description TODO
 * @Date 2019/10/31 11:08
 * @Created by Changliang.yang
 */
@RestController
@RequestMapping("/softwareinfo")
public class SoftwareinfoController extends BaseController {
    @Autowired
    private SoftwareinfoService softwareinfoService;

    /**
     * 添加终端软件信息
     *
     * @param softwareinfo
     * @return
     */
    @AnnotationLog(value = "添加终端软件信息")
    @ApiOperation(value = "添加终端软件信息", notes = "添加终端软件信息")
    @RequestMapping(value = "add",method = RequestMethod.POST)
    public Message addBatch(@RequestBody Softwareinfo softwareinfo) {
        return softwareinfoService.add(softwareinfo);
    }

    /**
     * 删除终端软件信息
     *
     * @param ti_sw_id
     * @return
     */
    @AnnotationLog(value = "删除终端软件信息")
    @ApiOperation(value = "删除终端软件信息", notes = "删除终端软件信息")
    @RequestMapping(value = "del/{ti_sw_id}",method = RequestMethod.DELETE)
    public Message delBatch(@PathVariable String ti_sw_id) {
        return softwareinfoService.delBatch(ti_sw_id);
    }

    /**
     * 修改终端软件信息
     *
     * @param softwareinfo
     * @return
     */
    @AnnotationLog(value = "修改终端软件信息")
    @ApiOperation(value = "修改终端软件信息", notes = "修改终端软件信息")
    @RequestMapping(value = "edit",method = RequestMethod.POST)
    public Message editBatch(@RequestBody Softwareinfo softwareinfo) {
        return softwareinfoService.edit(softwareinfo);
    }

    /**
     * 查询所有终端软件信息
     * @return
     */
    @AnnotationLog(value = "查询所有终端软件信息")
    @ApiOperation(value = "查询所有终端软件信息", notes = "查询所有终端软件信息")
    @RequestMapping(value = "select",method = RequestMethod.GET)
    public PageData selectBatch(Page page) {
        return softwareinfoService.select(page);
    }
}
