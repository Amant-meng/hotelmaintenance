package com.chinahotelhelp.shm.operational.module.report.controller;

import com.chinahotelhelp.shm.operational.module.report.service.ReportService;
import com.chinahotelhelp.shm.operational.module.sys.controller.BaseController;
import com.chinahotelhelp.shm.operational.module.sys.entity.Page;
import com.chinahotelhelp.shm.operational.module.sys.entity.PageData;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Huan.Xia
 * @Title: ReportController
 * @ProjectName merchant-management
 * @Description: TODO
 * @date 2019-03-1414:15
 */
@Api(description = "报表接口")
@RequestMapping("/report")
@Controller
public class ReportController  extends BaseController {
    @Autowired
    private ReportService reportService;
    /**
     * 获取账务明细报表数据
     *
     * @param page
     * @return
     */
    @RequiresPermissions("sys:report:billDetails")
    @RequestMapping(value = "billDetails",method = RequestMethod.GET)
    @ResponseBody
    public PageData billDetails(Page page) {
        return reportService.getBillDetails(page);
    }
    /**
     * 账务明细报表导出
     *
     * @param page
     * @return
     */
    @RequiresPermissions("sys:report:exportBillDetails")
    @RequestMapping(value = "exportBillDetails",method = RequestMethod.POST)
    @ResponseBody
    public void exportBillDetails(Page page) {
        try {
            reportService.exportBillDetails(page);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    /**
     * 获取账务汇总报表数据
     *
     * @param page
     * @return
     */
    @RequiresPermissions("sys:report:summarybillDetails")
    @RequestMapping(value = "summarybillDetails",method = RequestMethod.GET)
    @ResponseBody
    public PageData summarybillDetails(Page page) {
        return reportService.summarybillDetails(page);
    }
    /**
     * 账务汇总报表导出
     *
     * @param page
     * @return
     */
    @RequiresPermissions("sys:report:exportSummaryBillDetails")
    @RequestMapping(value = "exportSummaryBillDetails",method = RequestMethod.POST)
    @ResponseBody
    public void exportSummaryBillDetails(Page page) {
        try {
            reportService.exportSummaryBillDetails(page);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
