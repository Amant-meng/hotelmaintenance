package com.chinahotelhelp.shm.operational.module.exportlist.controller;

import com.chinahotelhelp.shm.operational.module.exportlist.entity.*;
import com.chinahotelhelp.shm.operational.module.exportlist.service.StatisticsColumnService;
import com.chinahotelhelp.shm.operational.module.exportlist.service.StatisticsTopService;
import com.chinahotelhelp.shm.operational.module.exportlist.service.StatisticsFieldService;
import com.chinahotelhelp.shm.operational.module.exportlist.service.StatisticsListService;
import com.chinahotelhelp.shm.operational.module.exportlist.entity.SysThree;
import com.chinahotelhelp.shm.operational.module.sys.controller.BaseController;
import com.chinahotelhelp.shm.operational.module.sys.entity.Page;
import com.chinahotelhelp.shm.operational.module.sys.entity.PageData;
import com.chinahotelhelp.shm.operational.tools.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("export1")
public class ExportStatisticsController extends BaseController {
    @Autowired
    private StatisticsTopService statisticsTopService;
    @Autowired
    private StatisticsFieldService statisticsFieldService;
    @Autowired
    private StatisticsListService statisticsListService;
    @Autowired
    private StatisticsColumnService statisticsColumnService;

    /**
     * 字段行表添加
     *
     * @param
     * @return
     */
    @RequestMapping(value = "addQueryCriteria", method = RequestMethod.POST)
    @ResponseBody
    public Message addQueryCriteria(@RequestBody StatisticsTop statisticsTop) {
        Message message = new Message();
        message.setMessage("非法请求！");
        message.setSuccess(false);
        statisticsTop.setOrder_count(statisticsTop.getOrder_count() + 1);
        statisticsTop.setTop_id(UUID.randomUUID().toString());
        int resultInt = statisticsTopService.addStatisticsTop(statisticsTop);
        if (resultInt > 0) {
            message.setMessage("添加成功！");
            message.setSuccess(true);
            return message;
        } else {
            message.setMessage("添加失败！");
            message.setSuccess(false);
            return message;
        }
    }

    /**
     * 字段列表添加
     *
     * @param
     * @return
     */
    @RequestMapping(value = "addStatisticsField", method = RequestMethod.POST)
    @ResponseBody
    public void addStatisticsField(@RequestBody StatisticsField statisticsField) {
        Message message = new Message();
        message.setMessage("非法请求！");
        message.setSuccess(false);
        statisticsField.setField_id(UUID.randomUUID().toString());
        statisticsField.setOrder_count(statisticsField.getOrder_count() + 1);
        int resultInt = statisticsFieldService.addStatisticsField(statisticsField);

    }

    /**
     * 统计表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "addStatisticsColumn", method = RequestMethod.POST)
    @ResponseBody
    public void addStatisticsColumn(@RequestBody StatisticsColumn statisticsColumn) {
        Message message = new Message();
        message.setMessage("非法请求！");
        message.setSuccess(false);
        statisticsColumn.setStatistics_id(UUID.randomUUID().toString());
        int resultInt = statisticsColumnService.addStatisticsColumn(statisticsColumn);

    }

    /**
     * 分组字段表添加
     *
     * @param
     * @return
     */
    @RequestMapping(value = "addStatisticsList", method = RequestMethod.POST)
    @ResponseBody
    public void addStatisticsList(@RequestBody StatisticsList statisticsList) {
        Message message = new Message();
        message.setMessage("非法请求！");
        message.setSuccess(false);
        statisticsList.setStatistics_id(UUID.randomUUID().toString());
        int resultInt = statisticsListService.addStatisticsList(statisticsList);
    }

    /**
     * @Author: wengdajiang
     * @Description:获得配置列表
     * @Date: 2019/1/14
     * @package : com.chinahotelhelp.shm.businessmanagement.module.exportlist.controller
     */
    @RequestMapping(value = "getCollectRefoundList", method = RequestMethod.GET)
    @ResponseBody
    public Map getCollectRefoundList(Page page) {
        //获得当前酒店ID
        String hi_ids = "";
        String hi_id = "";
        if(page.getParams().get("hiId") != null && !"".equals(page.getParams().get("hiId"))){
            hi_id = page.getParams().get("hiId").toString();
            hi_ids = hi_id;
        }else{
            if(page.getParams().get("hi_ids") != null && !"".equals(page.getParams().get("hi_ids"))){
                hi_ids = page.getParams().get("hi_ids").toString();
            }
        }
        if(hi_ids.equals("")  && hi_id.equals("" )){
            hi_ids = ShiroUtils.getUserEntity().getHiId();
        }
        String top_id = page.getParams().get("top_id").toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获得前端的统计表ID
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_MONTH,0);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        Date dayStart = calendar.getTime();
        String startTime = sdf.format(dayStart);

        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        calendar.set(Calendar.MILLISECOND,999);
        Date dayEnd = calendar.getTime();
        String endTime = sdf.format(dayEnd);
        String sql = "select d.pay_paytype as groupName,d.pay_paytype as groupId from t_pms_bill_detail d group by d.pay_paytype";

        Map resultMap = getDetailListCommon(sql,startTime,endTime,hi_ids,top_id);
        return resultMap;
    }


    /**
     * @Author: wengdajiang
     * @Description:获得配置列表
     * @Date: 2019/1/14
     * @package : com.chinahotelhelp.shm.businessmanagement.module.exportlist.controller
     */
    @RequestMapping(value = "getStatisticsList", method = RequestMethod.POST)
    @ResponseBody
    public Map getStatisticsList(@RequestBody RequestData requestData) {
        //获得前端的类型
        String hotel = requestData.getHotel();
        //获得前端的统计表ID
        String top_id = requestData.getTop_id();
        //获得前端的时间数组
        List<String> timeList = requestData.getCreatetime();
        String startTime = "";
        String endTime = "";
        if (timeList != null) {
            startTime = timeList.get(0);
            endTime = timeList.get(1);
        }
        String sql = "select bill_paytype as groupName,bill_paytype as groupId from t_pms_bill_detail group by bill_paytype";
        Map resultMap = getDetailListCommon(sql,startTime,endTime,hotel,top_id);
        return resultMap;
    }

    public Map getDetailListCommon(String sql,String startTime,String endTime,String hi_ids,String top_id){
        //获得列表Number
        List<Map<String, Object>> tstudentList = statisticsListService.findStudentGradeList(sql);
        List<Map> stringList = new ArrayList<>();
        //循环得到分组Nubmer
        for (Map<String, Object> mapsql : tstudentList) {
            String mapListColumn = "";
            Map mapproName = new HashMap();
            //查出每列对应的sql
            List<StatisticsColumn> statisticsColumnList = statisticsColumnService.queryStatisticsColumnSql(top_id);
            mapproName.put("group", mapsql.get("groupName"));
            for (StatisticsColumn statisticsColumnsql : statisticsColumnList
            ) {

                //将查出的sql和分组ID传给Map
                Map mapParmter = new HashMap();
                //给查询出来的sql赋值给Map
                mapParmter.put("exec_sql", statisticsColumnsql.getColumn_sql());
                //如果前端没有传类型，则不给类型赋值
//                if (hotel != null && !"".equals(hotel)) {
//                    mapParmter.put("hotel", hotel);
//                }
                //如果前端没有传时间区间，则不给赋值
                if (startTime != null && !"".equals(startTime) && endTime != null && !"".equals(endTime)) {
                    mapParmter.put("startTime", startTime);
                    mapParmter.put("endTime", endTime);
                }
                if (hi_ids != null && !"".equals(hi_ids)) {
                    mapParmter.put("hi_ids", hi_ids);
                }
                //通过KEY值得到对应的value值
                for (String key : mapsql.keySet()) {
                    mapParmter.put(key, mapsql.get(key));
                }
                //判断有没有sql.
                if (statisticsColumnsql.getIfsql() == 1) {
                    //执行每列的SQL语句
                    mapListColumn = statisticsListService.findStatisticsListData(mapParmter);
                    //得到的值赋给Map
                    mapproName.put(statisticsColumnsql.getProperty(), mapListColumn);
                    //将得到的结果放到对应的List中
                } else {
                    mapproName.put(statisticsColumnsql.getProperty(), "");
                }

            }
            //将每次循环结束的结果放到另一个List中
            stringList.add(mapproName);

        }
        Map resultMap = new HashMap();
        resultMap.put("stringList", stringList);
        return resultMap;
    }
    /**
     * @Author: wengdajiang
     * @Description:获取表头数据
     * @Date: 2019/1/14
     * @package : com.chinahotelhelp.shm.businessmanagement.module.exportlist.controller
     */
    @RequestMapping(value = "getTopData", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, List> getTopData(Page page) {
        Map<String, Object> mapInfo = page.getParams();
        Map<String, List> tableConfig = new HashMap();
        List<SysTop> mapListTop = new ArrayList<>();
        SysTop sysTop = statisticsListService.getTopOneData(mapInfo.get("top_id").toString());
        //获得主表信息
        List<SysChildren> sysChildrenList = statisticsListService.getchildrenData(Integer.valueOf(mapInfo.get("top_id").toString()));
        sysTop.setList(sysChildrenList);
        for (SysChildren sysChildren : sysChildrenList
        ) {
            //获得二级表头信息
            List<SysThree> sysThreeList = statisticsListService.getysThreeData(sysChildren.getId());
            for (SysThree sysThree : sysThreeList
            ) {
                if (sysThree.getOrder_count() == 1) {
                    sysThree.setStatistics_column_sql("");
                }
            }
            sysChildren.setList(sysThreeList);
        }
        mapListTop.add(sysTop);
        tableConfig.put("tableConfig", mapListTop);
        return tableConfig;
    }

    /**
     * @Author: wengdajiang
     * @Description:获得列表信息
     * @Date: 2019/1/5
     * @package : com.chinahotelhelp.shm.businessmanagement.module.exportlist.controller
     */
    @RequestMapping(value = "getDetailedList", method = RequestMethod.GET)
    @ResponseBody
    public PageData getDetailedList(Page page) {
        PageData pageData = statisticsListService.getDetailedList(page);
        return pageData;
    }

    /**
     * @Author: wengdajiang
     * @Description:获得账务明细列表
     * @Date: 2019/1/15
     * @package : com.chinahotelhelp.shm.businessmanagement.module.exportlist.controller
     */
    @RequestMapping(value = "getAccountingDetailedList", method = RequestMethod.GET)
    @ResponseBody
    public PageData getAccountingDetailedList(Page page) {
        PageParam pageParam = new PageParam();
        pageParam.setStart(page.getStart());
        pageParam.setLength(page.getLength());
        Map<String, Object> params = page.getParams();
        //pageParam.setDraw(page.getDraw());
        //获取前端的时间查询条件
        pageParam.setCreatetime(params.get("createtime").toString());
        //判断支付方式是否为空，如果为空就不给赋值
        if(params.get("payWay")!=null && !"".equals(params.get("payWay")!=null)){
            pageParam.setPayWay(params.get("payWay").toString());
        }
        //判断账务名称是否为空，如果为空就不给赋值
        if(params.get("bill_name")!=null && !"".equals(params.get("bill_name")!=null)){
            pageParam.setBill_name(params.get("bill_name").toString());
        }
        //判断账务类型是否为空，如果为空就不给赋值
        if(params.get("bill_type")!=null && !"".equals(params.get("bill_type")!=null)){
            pageParam.setBill_type(params.get("bill_type").toString());
        }
        //得到列表数据
        PageData pageData = statisticsListService.getAccountingDetailedList(pageParam);
        return pageData;
    }

    /**
     * @Author: wengdajiang
     * @Description: 查询分页数据
     * @Date: 2019/1/5
     * @package : com.chinahotelhelp.shm.businessmanagement.module.exportlist.controller
     */
    @RequestMapping(value = "getDetailedAllList", method = RequestMethod.GET)
    @ResponseBody
    public PageData getDetailedAllList() {
        //获得列表数据
        List<Map> list = statisticsListService.getAllDetailedList();
        PageData pageData = new PageData();
        pageData.setData(list);
        return pageData;
    }

    /**
     * @Author: wengdajiang
     * @Description:获取PDF导出文件流
     * @Date: 2019/1/14
     * @package : com.chinahotelhelp.shm.businessmanagement.module.exportlist.controller
     */
    @RequestMapping(value = "excel2pdf", method = RequestMethod.POST)
    public void excel2pdf(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //引入输出PDF表格的类
        PdfTools exportUtils = new PdfTools();
        //获得pdf方法对应参数
        int draw = Integer.parseInt(request.getParameter("draw"));
        int start = Integer.parseInt(request.getParameter("start"));
        int length = Integer.parseInt(request.getParameter("length"));
        String createtime = request.getParameter("createtime");
        String payWay = "";
        //如果支付方式为空。就不给赋值
        if (!request.getParameter("payWay").equals("undefined")) {
            payWay = (request.getParameter("payWay"));
        }
        //给入参赋值
        PageParam page = new PageParam();
        page.setLength(length);
        page.setStart(start);
        page.setCreatetime(createtime);
        page.setPayWay(payWay);
        page.setDraw(draw);
        //查出对应详情数据
        PageData pageData = statisticsListService.exportListData(page);
        //给返回数据赋值
        List<Map> listMap = pageData.getListMap();
        int count = pageData.getCount();
        //执行得到PDF表格的方法
        exportUtils.download(request, response, listMap, createtime, count);
    }
    /**
    * @Author: wengdajiang
    * @Description:账务明细统计PDF输出流
    * @Date: 2019/1/18
    * @package : com.chinahotelhelp.shm.businessmanagement.module.exportlist.controller
    */
    @RequestMapping(value = "excelAccounting2pdf", method = RequestMethod.POST)
    public void excelAccounting2pdf(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //查询总条数
        //int count = statisticsListService.getAccountingDetailedListCount();
        //引入输出PDF表格的类
        PdfAccountingTools pdfAccountingTools = new PdfAccountingTools();
        //获得pdf方法对应参数
        int draw = Integer.parseInt(request.getParameter("draw"));
        int start = Integer.parseInt(request.getParameter("start"));
        int length = Integer.parseInt(request.getParameter("length"));
        String createtime = request.getParameter("createtime");
        String payWay = "";
        //如果支付方式为空，就不给赋值
        if(draw==2){
            start = (start-1) * length;
        }
        if (!request.getParameter("payWay").equals("undefined")) {
            payWay = (request.getParameter("payWay"));
        }
        //如果账务类型为空，就不给赋值
        String bill_type = "";
        if (!request.getParameter("payWay").equals("undefined")) {
            bill_type = request.getParameter("bill_type");
        }
        //如果账务名称为空，就不给赋值
        String bill_name = "";
        if (!request.getParameter("payWay").equals("undefined")) {
            bill_name = request.getParameter("bill_name");
        }
        //拼接入参
        PageParam page = new PageParam();
        page.setLength(length);
        page.setStart(start);
        page.setBill_name(bill_name);
        page.setBill_type(bill_type);
        page.setCreatetime(createtime);
        page.setPayWay(payWay);
        page.setDraw(draw);
        //得到导出PDF列表数据
        PageData pageData = statisticsListService.getAccountingDetailedList(page);
        List<Map> listMap = pageData.getListMap();
        //得到总条数
        int count = pageData.getCount();
        //执行得到PDF表格的方法
        pdfAccountingTools.download(request, response, listMap, createtime, count);
    }

    /**
     * @Author: wengdajiang
     * @Description:获得excel数据流
     * @Date: 2019/1/14
     * @package : com.chinahotelhelp.shm.businessmanagement.module.exportlist.controller
     */
    @RequestMapping(value = "exportExcel", method = RequestMethod.POST)
    public void exportExcel(HttpServletResponse response, HttpServletRequest request) throws Exception {
        //引入输出PDF表格的类
        ExportTools exportTools = new ExportTools();
        //获得pdf方法对应参数
        //获得pdf方法对应参数
        int draw = Integer.parseInt(request.getParameter("draw"));
        int start = Integer.parseInt(request.getParameter("start"));
        int length = Integer.parseInt(request.getParameter("length"));
        String createtime = request.getParameter("createtime");
        //支付方式为空，不给其赋值
        String payWay = "";
        if (!request.getParameter("payWay").equals("undefined")) {
            payWay = request.getParameter("payWay");
        }
        //拼接入参
        PageParam page = new PageParam();
        page.setLength(length);
        page.setStart(start);
        page.setCreatetime(createtime);
        page.setPayWay(payWay);
        page.setDraw(draw);
        //查出对应详情数据
        PageData pageData = statisticsListService.exportListData(page);
        //给返回列表赋值
        List<Map> listMap = pageData.getListMap();
        //得到总条数
        int count = pageData.getCount();
        //得到输出流
        exportTools.exportExcel(listMap, response, createtime, count);
    }

    /**
     * @Author: wengdajiang
     * @Description:获得excel数据流
     * @Date: 2019/1/14
     * @package : com.chinahotelhelp.shm.businessmanagement.module.exportlist.controller
     */
    @RequestMapping(value = "exportAccountingExcel", method = RequestMethod.POST)
    public void exportAccountingExcel(HttpServletResponse response, HttpServletRequest request) throws Exception {
        //引入输出PDF表格的类
        AccountingExportTools accountingExportTools = new AccountingExportTools();
        //获得pdf方法对应参数
        int draw = Integer.parseInt(request.getParameter("draw"));
         int start = Integer.parseInt(request.getParameter("start"));

        int length = Integer.parseInt(request.getParameter("length"));
        String createtime = request.getParameter("createtime");
        //如果支付方式为空，不给赋值
        String payWay = "";
        if(draw==2){
            start = (start-1) * length;
        }
        if (!request.getParameter("payWay").equals("undefined")) {
            payWay = request.getParameter("payWay");
        }
        //如果账务类型为空，不给赋值
        String bill_type = "";
        if (!request.getParameter("bill_type").equals("undefined")) {
            bill_type = request.getParameter("bill_type");
        }
        //如果账务名称为空，不给赋值
        String bill_name = "";
        if (!request.getParameter("bill_type").equals("undefined")) {
            bill_name = request.getParameter("bill_name");
        }
        //拼接入参
        PageParam page = new PageParam();
        page.setLength(length);
        page.setStart(start);
        page.setBill_name(bill_name);
        page.setBill_type(bill_type);
        page.setCreatetime(createtime);
        page.setPayWay(payWay);
        page.setDraw(draw);
        //查出对应详情数据
        PageData pageData = statisticsListService.getAccountingDetailedList(page);
        //返回列表赋值
        List<Map> listMap = pageData.getListMap();
        //查询总条数
        int count = pageData.getCount();
        //执行得到excel表格的方法
        accountingExportTools.exportExcel(listMap, response, createtime, count);
    }

}


