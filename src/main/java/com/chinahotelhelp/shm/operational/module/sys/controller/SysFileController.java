package com.chinahotelhelp.shm.operational.module.sys.controller;

import com.chinahotelhelp.shm.operational.module.sys.entity.Message;
import com.chinahotelhelp.shm.operational.module.sys.entity.Page;
import com.chinahotelhelp.shm.operational.module.sys.entity.PageData;
import com.chinahotelhelp.shm.operational.module.sys.service.SysFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @author Huan.Xia
 * @Title: SysFileController
 * @ProjectName merchant-management
 * @Description: TODO
 * @date 2019-01-1215:45
 */
@Controller
@RequestMapping("file")
@Api(description = "对账接口")
public class SysFileController {
    /**
     * 文件服务
     */
    @Autowired
    private SysFileService sysFileService;


    //上传路径
    @Value("${file.uploadFolder}")
    private String uploadFolder;

    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    @PostMapping("upload")
    @ResponseBody
    @ApiOperation(value = "文件上传", notes = "文件上传")
    @RequiresPermissions("sys:filecompare:upload")
    public Message upload(@RequestParam("fileUpload") MultipartFile file, @RequestParam("taskid") String taskid) throws IOException {
        return sysFileService.upload(file, taskid, uploadFolder);
    }

    @RequestMapping(value = "comparisonResult", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "比对结果", notes = "比对结果")
    @RequiresPermissions("sys:filecompare:result")
    public PageData comparisonResult(Page page) {
        //taskid 前台传入任务编号
        //paytype 前台传入支付方式代码表
        //businesstime 营业日期(营业开始与营业结束)
        //previewtype （all：代表全部，e:比对金额相等，ne:比对金额不相等,n：未找到交易明细）
        //page.setParams("{\"taskid\":\"5614bf91-c3b2-48dd-97a0-854ae90553b5\",\"paytype\":\"1\",\"businesstime\":\"2019-01-01 - 2019-01-19\",\"previewtype\":\"all\"}");
        return sysFileService.getComparisonResult(page);
    }

    @RequestMapping(value = "comparisonBillDetailsResult", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "比对结果", notes = "比对结果")
    @RequiresPermissions("sys:filecompare:billdetailsresult")
    public PageData comparisonBillDetailsResult(Page page) {
        //taskid 前台传入任务编号
        //billtype 前台传入账务类型（code_accountstype）
        //billpid 账务条目类型编号（code_accountscategory）
        //businesstime 营业日期(营业开始与营业结束)
        //previewtype （all：代表全部，e:比对金额相等，ne:比对金额不相等,n：未找到交易明细）
        //page.setParams("{\"taskid\":\"5614bf91-c3b2-48dd-97a0-854ae90553b5\",\"billtype\":\"1\",\"billpid\":\"CHH_CASH_SK\",\"businesstime\":\"2019-01-01 - 2019-01-19\",\"previewtype\":\"all\"}");
        return sysFileService.getBillDeatilsComparisonResult(page);
    }



    @RequestMapping(value = "export", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "比对结果导出", notes = "比对结果导出")
    @RequiresPermissions("sys:filecompare:export")
    public void export(Page page) throws Exception {
        sysFileService.exportCompResult(page);
    }

    @RequestMapping(value = "exportBillDeatils", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "账务明细比对结果导出", notes = "账务明细比对结果导出")
    @RequiresPermissions("sys:filecompare:billdetailsexport")
    public void exportBillDeatils(Page page) throws Exception {
        sysFileService.exportCompBillDetailsResult(page);
    }
}
