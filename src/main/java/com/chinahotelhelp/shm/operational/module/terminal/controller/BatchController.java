package com.chinahotelhelp.shm.operational.module.terminal.controller;

import com.chinahotelhelp.shm.operational.aspect.AnnotationLog;
import com.chinahotelhelp.shm.operational.module.sys.controller.BaseController;
import com.chinahotelhelp.shm.operational.module.sys.entity.Message;
import com.chinahotelhelp.shm.operational.module.sys.entity.Page;
import com.chinahotelhelp.shm.operational.module.sys.entity.PageData;
import com.chinahotelhelp.shm.operational.module.terminal.entity.Batch;
import com.chinahotelhelp.shm.operational.module.terminal.entity.RemoteDTO;
import com.chinahotelhelp.shm.operational.module.terminal.service.BatchService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Classname BatchController
 * @Description 批次
 * @Date 2019/10/31 11:05
 * @Created by Changliang.yang
 */
@RestController
@RequestMapping("/batch")
public class BatchController extends BaseController {
    @Autowired
    private BatchService batchService;

    /**
     * 添加批次
     *
     * @param batch
     * @return
     */
    @AnnotationLog(value = "添加批次")
    @ApiOperation(value = "添加批次", notes = "添加批次")
    @RequestMapping(value = "add",method = RequestMethod.POST)
    public Message addBatch(@RequestBody Batch batch) {
        return batchService.addBatch(batch);
    }

    /**
     * 删除批次
     *
     * @param bh_id
     * @return
     */
    @AnnotationLog(value = "删除批次")
    @ApiOperation(value = "删除批次", notes = "删除批次")
    @RequestMapping(value = "del/{bh_id}",method = RequestMethod.DELETE)
    public Message delBatch(@PathVariable String bh_id) {
        return batchService.delBatch(bh_id);
    }

    /**
     * 修改批次
     *
     * @param batch
     * @return
     */
    @AnnotationLog(value = "修改批次")
    @ApiOperation(value = "修改批次", notes = "修改批次")
    @RequestMapping(value = "edit",method = RequestMethod.POST)
    public Message editBatch(@RequestBody Batch batch) {
        return batchService.editBatch(batch);
    }
    /**
     * 发送运维消息
     *
     * @param remoteDTO,terminalId
     * @return
     */
    @AnnotationLog(value = "发送运维消息")
    @ApiOperation(value = "发送运维消息", notes = "发送运维消息")
    @RequestMapping(value = "sendMessage/{terminalId}",method = RequestMethod.POST)
    public Message sendMessage(@RequestBody RemoteDTO remoteDTO,@PathVariable String terminalId) {
        return batchService.sendMassage(remoteDTO,terminalId);
    }

    /**
     * 接收接口检测消息返回
     *
     * @param remoteDTO
     * @return
     */
    @AnnotationLog(value = "接收接口检测消息返回")
    @ApiOperation(value = "接收接口检测消息返回", notes = "接收接口检测消息返回")
    @RequestMapping(value = "receveInfo",method = RequestMethod.POST)
    public Message receveInfo(@RequestBody RemoteDTO remoteDTO) {
        return batchService.receveInfo(remoteDTO);
    }

    /**
     * 查询所有批次
     * @return
     */
    @AnnotationLog(value = "查询所有批次")
    @ApiOperation(value = "查询所有批次", notes = "查询所有批次")
    @RequestMapping(value = "selectBatch",method = RequestMethod.GET)
    public PageData selectBatch(Page page) {
        return batchService.selectBatch(page);
    }
}
