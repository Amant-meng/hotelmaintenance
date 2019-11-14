package com.chinahotelhelp.shm.operational.module.report.service;

import com.chinahotelhelp.shm.operational.common.filed.*;
import com.chinahotelhelp.shm.operational.module.sys.entity.Page;
import com.chinahotelhelp.shm.operational.module.sys.entity.PageData;
import com.chinahotelhelp.shm.operational.module.sys.mapper.SysUserMapper;
import com.chinahotelhelp.shm.operational.tools.POIUntils;
import com.chinahotelhelp.shm.operational.tools.QueryUntil;
import com.chinahotelhelp.shm.operational.tools.ShiroUtils;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Huan.Xia
 * @Title: ReportService
 * @ProjectName merchant-management
 * @Description: TODO
 * @date 2019-03-1414:17
 */
@Service
public class ReportService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Resource
    private HttpServletResponse httpServletResponse;

    /**
     * 获取账务明细数据
     * @param page
     * @return
     */
    public PageData getBillDetails(Page page){
        List<Filed> queryFileds = new ArrayList<Filed>();
        Map<String, Object> params = page.getParams();
        PageData pageData = new PageData();
        //导出全部标识
        boolean isall = true;
        if (params != null && params.size() > 0 && params.get("bill_ptime") != null) {
            //page.getParams().put("hi_id", ShiroUtils.getUserEntity().getHiId());
           // queryFileds.add(new CombFiled("hi_id", params));
            //预订单
            queryFileds.add(new CombFiled("or_id", params));
            //入住单
            queryFileds.add(new CombFiled("or_checkin_id", params));
            //渠道
            queryFileds.add(new TextFiled("or_source", params));
            //房号
            queryFileds.add(new CombFiled("ro_id", params));
            //房型
            queryFileds.add(new TextFiled("ro_type_name", params));
            //姓名
            queryFileds.add(new TextFiled("or_ci_name", params));
            //账务类型
            queryFileds.add(new CombFiled("bill_type", params));
            //账务条目
            queryFileds.add(new CombFiled("bill_pid", params));
            //原始凭证编号
            queryFileds.add(new TextFiled("pay_shift_no", params));
            //备注
            queryFileds.add(new TextFiled("bill_comments", params));
            //入账时间区间
            queryFileds.add(new DateRangeFiled("bill_ptime", params));

            //是否追加条件
            if(params.get("all") != null){
                isall = false;
            }

            String[] cmdSqlArray = QueryUntil.getReportQuerySql("*", queryFileds, "", "(" +
                    "select \n" +
                    "t.* from \n" +
                    "(\n" +
                    "SELECT\n" +
                    "  g.or_id, ##预订单号\n" +
                    "  g.or_checkin_id,  ##入住单号\n" +
                    " ##g.or_mode, ##入住类型\n" +
                    "  t1.name as or_mode,##入住类型\n" +
                    " g.or_source, ##来源渠道\n" +
                    " g.ro_id,  ##房间号\n" +
                    " g.ro_type_name,  ##房型\n" +
                    " g.or_ci_name,  ##姓名\n" +
                    " ##b.bill_type,  ##账务类型\n" +
                    " t2.name as bill_type,##入住类型\n" +
                    " b.bill_pname,  ##账务项目名称\n" +
                    " b.bill_con_money,  ##订单消费金额\n" +
                    " b.bill_pay_money,   ##订单结算金额\n" +
                    "  b.pay_shift_no,   ##原始凭证编号\n" +
                    " b.bill_comments,  ##账单备注\n" +
                    "  ##营业时间\n" +
                    " b.bill_ptime,  ##账单记账时间\n" +
                    " # t3.name as pay_paytype,##预付支付方式 \n" +
                    "  case when p.pay_pos = 'pos' then p.pay_refer_no else  p.pay_merchant_orderid end as pay_bill_no\n" +
                    "FROM\n" +
                    " t_pms_order_guest g \n" +
                    " LEFT OUTER JOIN sys_dict t1 on g.or_mode = t1.code and t1.type= 'code_checkinmode' ,\n" +
                    "  t_pms_bill_detail b \n" +
                    " LEFT OUTER JOIN sys_dict t2 on b.bill_type = t2.code and t2.type= 'code_accountstype',\n" +
                    "  t_pay_payment p\n" +
                    " # LEFT OUTER JOIN sys_dict t3 on p.pay_paytype = t3.code and t3.type= 'code_payment'\n" +
                    "WHERE\n" +
                    "g.pk_ci_id = b.pk_ci_id  and g.pk_ci_id  = p.pk_ci_id \n" +
                    " and b.hi_id = '"+ ShiroUtils.getUserEntity().getHiId()+"'\n" +
                    "AND p.pay_isone in (2,3)\n" +
                    "AND g.or_act_arr_time >= str_to_date(\n" +
                    " '2016-01-02 12:00:00',\n" +
                    " '%Y-%m-%d %T'\n" +
                    ") ###开始时间条件\n" +
                    "AND g.or_act_arr_time <= str_to_date(\n" +
                    " '2020-01-02 12:00:00',\n" +
                    " '%Y-%m-%d %T'\n" +
                    ")  order by  p.pay_trans_datetime desc\n" +
                    ") t\n" +
                    ", (SELECT @rownum := 0) r )q",isall);
            page.setExec_sql(cmdSqlArray[0]);
            pageData.setData(sysUserMapper.execSQL(page));
            page.setExec_sql(cmdSqlArray[1]);
            page.setStart(page.getStart()-1);
            pageData.setRecordsTotal(Integer.parseInt(sysUserMapper.execSQL(page).get(0).get("count").toString()));
            pageData.setRecordsFiltered(pageData.getRecordsTotal());


            if(pageData.getData() != null){
                List<Map> data = (List<Map>) pageData.getData();
                //计算消费金额与结算金额
                double bill_con_moneyCount = 0;
                double bill_pay_moneyCount = 0;
                //得到消费金额和结算金额
                for (
                        Map mapInfo : data) {
                    if (mapInfo.get("bill_con_money") != null) {
                        bill_con_moneyCount = 0.00;
                    }
                    if (mapInfo.get("bill_pay_money") != null) {
                        bill_pay_moneyCount += Double.parseDouble(mapInfo.get("bill_pay_money").toString());
                    }
                }
                Map<String,Object> attacheData = new HashMap<>();
                attacheData.put("bill_con_money", String.format("%.2f", bill_con_moneyCount));
                attacheData.put("bill_pay_money", String.format("%.2f", bill_pay_moneyCount));
                pageData.setAttachData(attacheData);

            }
        }



        return pageData;
    }

    /**
     * 导出账务明细
     * @param page
     * @return
     */
    public void exportBillDetails(Page page) throws Exception{


        PageData pageData = this.getBillDetails(page);
        if(pageData != null && pageData.getRecordsTotal() > 0){

            String dateRange = page.getParams().get("bill_ptime").toString();
            String[] dateArray = dateRange.split(" - ");

            //title
            String title = String.format("自助机账务明细报表 （%s）", dateRange);
            //filename
            String filename = dateRange.replace(" ","") +".xls";
            //preview data
            List<Map<String, Object>> previewData = (List<Map<String, Object>>)pageData.getData();
            //attache data
            Map<String, Object> attachData = (Map<String, Object>)pageData.getAttachData();
            //统计列标题
            List<Map<String, Object>> cells = new ArrayList<>();
            Map<String,Object> item = new HashMap<>();
            item.put("colname", "xu_id");
            item.put("colcomments", "序号");
            cells.add(item);
            item = new HashMap<>();
            item.put("colname", "or_id");
            item.put("colcomments", "预订单");
            cells.add(item);
            item = new HashMap<>();
            item.put("colname", "or_checkin_id");
            item.put("colcomments", "入住单");
            cells.add(item);
            item = new HashMap<>();
            item.put("colname", "or_source");
            item.put("colcomments", "渠道");
            cells.add(item);
            item = new HashMap<>();
            item.put("colname", "or_mode");
            item.put("colcomments", "类型");
            cells.add(item);
            item = new HashMap<>();
            item.put("colname", "ro_id");
            item.put("colcomments", "房间号");
            cells.add(item);
            item = new HashMap<>();
            item.put("colname", "ro_type_name");
            item.put("colcomments", "房型");
            cells.add(item);
            item = new HashMap<>();
            item.put("colname", "or_ci_name");
            item.put("colcomments", "姓名");
            cells.add(item);
            item = new HashMap<>();
            item.put("colname", "bill_typename");
            item.put("colcomments", "账务类型");
            cells.add(item);
            item = new HashMap<>();
            item.put("colname", "bill_pname");
            item.put("colcomments", "账务条目");
            cells.add(item);
            item = new HashMap<>();
            item.put("colname", "bill_con_money");
            item.put("colcomments", "消费金额");
            cells.add(item);
            item = new HashMap<>();
            item.put("colname", "bill_pay_money");
            item.put("colcomments", "结算金额");
            cells.add(item);
            item = new HashMap<>();
            item.put("colname", "pay_shift_no");
            item.put("colcomments", "原始凭证编号");
            cells.add(item);
            item = new HashMap<>();
            item.put("colname", "bill_comments");
            item.put("colcomments", "备注");
            cells.add(item);
            item = new HashMap<>();
            item.put("colname", "bill_pdate");
            item.put("colcomments", "营业日");
            cells.add(item);
            item = new HashMap<>();
            item.put("colname", "bill_ptime");
            item.put("colcomments", "入账时间");
            cells.add(item);
            //头部
            List<List<String>> titleHeaderList = new ArrayList<>();
            //第1行
            List<String> l2List = new ArrayList<>();
            l2List.add(ShiroUtils.getUserEntity().getHiId_name());
            titleHeaderList.add(l2List);
            //第2行
            List<String> l3List = new ArrayList<>();
            l3List.add("营业日期");
            l3List.add(dateArray[0]);
            l3List.add("");
            l3List.add("至");
            l3List.add(dateArray[1]);
            l3List.add("");
            titleHeaderList.add(l3List);


            List<CellRangeAddress> regions = new ArrayList<>();
            regions.add(new CellRangeAddress(0, 0, 0, cells.size() - 1));
            regions.add(new CellRangeAddress(1, 1, 0, cells.size() - 1));
            regions.add(new CellRangeAddress(2, 2, 1, 2));
            regions.add(new CellRangeAddress(2, 2, 4, 5));



           //汇总数据
            List<String> footerList = new ArrayList<>();
            footerList.add("合计:");
            footerList.add(previewData.size() + "");
            footerList.add("");
            footerList.add("");
            footerList.add("消费：");
            //footerList.add(String.format("%.2f", attachData.get("bill_con_money")));
            footerList.add(attachData.get("bill_con_money").toString());
            footerList.add("");
            footerList.add("");
            footerList.add("结算：");
            //footerList.add(String.format("%.2f", attachData.get("bill_pay_money")));
            footerList.add(attachData.get("bill_pay_money").toString());

            POIUntils.exportWorkBook(titleHeaderList, footerList, cells, regions, previewData, title, filename, httpServletResponse);

        }
    }

    /**
     * 获取账务汇总数据
     * @param page
     * @return
     */
    public PageData summarybillDetails(Page page){
        List<Filed> queryFileds = new ArrayList<Filed>();
        Map<String, Object> params = page.getParams();
        //导出全部标识
        boolean isall = true;
        if (params != null && params.size() > 0 && params.get("or_act_arr_time") != null) {
            //page.getParams().put("hi_id", ShiroUtils.getUserEntity().getHiId());
            // queryFileds.add(new CombFiled("hi_id", params));
            //预订单
            queryFileds.add(new CombFiled("or_id", params));
            //入住单
            queryFileds.add(new CombFiled("or_checkin_id", params));
            //入住类型
            queryFileds.add(new CombFiled("or_mode", params));
            //渠道
            queryFileds.add(new TextFiled("or_source", params));
            //房号
            queryFileds.add(new CombFiled("ro_id", params));
            //房型
            queryFileds.add(new TextFiled("ro_type_name", params));
            //姓名
            queryFileds.add(new TextFiled("or_ci_name", params));
            //入住时间
            queryFileds.add(new DateRangeFiled("or_act_arr_time", params));
            //退房时间
            queryFileds.add(new DateRangeFiled("or_act_dep_time", params));
            //预授权金额
            queryFileds.add(new NumberRangeFiled("bill_prepay", params));
            //支付金额
            queryFileds.add(new NumberRangeFiled("bill_all_pay_price", params));
            //退款金额
            queryFileds.add(new NumberRangeFiled("bill_refundable_amount", params));
            //预付支付方式
            queryFileds.add(new CombFiled("pay_paytype", params));
            //预付交易类型
            queryFileds.add(new CombFiled("pay_trans_typebs", params));
            //预付交易参考号
            queryFileds.add(new TextFiled("pay_bill_no", params));
            //预付交易时间
            queryFileds.add(new DateRangeFiled("pay_trans_datetime", params));
            //是否追加条件
            if(params.get("all") != null){
                isall = false;
            }

        }
        PageData pageData = new PageData();
        String[] cmdSqlArray = QueryUntil.getReportQuerySql("*", queryFileds, "", "(" +
                "select \n" +
                "t.* from \n" +
                "(\n" +
                "SELECT\n" +
                "  g.hi_id,\n" +
                "  g.or_id, ##预订单号\n" +
                "  g.or_checkin_id,  ##入住单号\n" +
                "  ##g.or_mode, ##入住类型\n" +
                "  t1.name as or_mode,##入住类型\n" +
                "  g.or_source, ##来源渠道\n" +
                "  ##g.or_status,  ##入住状态\n" +
                "  t2.name as or_status,##入住状态\n" +
                "  g.ro_id,  ##房间号\n" +
                "  g.ro_type_name,  ##房型\n" +
                "  g.or_ci_name,  ##姓名\n" +
                "  g.or_act_arr_time,  ##入住时间\n" +
                "  g.or_act_dep_time,  ##退房时间\n" +
                "  g.or_act_day,   ##实住天数\n" +
                "  g.ro_price,  ##房间单价\n" +
                "  g.bill_prepay,   ##预付金额\n" +
                "  b.bill_all_pay_money,   ##支付金额 包含押金\n" +
                "  g.bill_deposit,   ##押金金额\n" +
                "  b.bill_all_ro_price,  ##房费\n" +
                "  b.bill_all_con_price,  ##消费品\n" +
                "  b.bill_all_other_price,  ##其它\n" +
                "  b.bill_refundable_amount,  ##退款金额\n" +
                "  g.bill_prepay-b.bill_refundable_amount as pay_reality_money,\n" +
                "  b.bill_all_con_money,   ##消费金额\n" +
                "  ##p.pay_paytype,   ##预付支付方式\n" +
                "  t3.name as pay_paytype,##预付支付方式   显示第一次的交易方式和交易类型\n" +
                "  ##p.pay_trans_typebs,  ##预付交易类型\n" +
                "  t4.name as pay_trans_typebs,##预付交易类型 显示第一次的交易方式和交易类型\n" +
                "  case when p.pay_pos = 'pos' then p.pay_refer_no else  p.pay_merchant_orderid end as pay_bill_no,\n" +
                "  p.pay_trans_datetime  ##预付交易时间\n" +
                "FROM\n" +
                "  t_pms_order_guest g \n" +
                "LEFT OUTER JOIN sys_dict t1 on g.or_mode = t1.code and t1.type= 'code_checkinmode' \n" +
                "LEFT OUTER JOIN sys_dict t2 on g.or_status = t2.code and t2.type= 'code_checkinstatus',\n" +
                "  t_pms_bill b,\n" +
                "  t_pay_payment p\n" +
                "LEFT OUTER JOIN sys_dict t3 on p.pay_paytype = t3.code and t3.type= 'code_payment'\n" +
                "LEFT OUTER JOIN sys_dict t4 on p.pay_trans_typebs = t4.code and t4.type= 'code_trantypebs'\n" +
                "WHERE\n" +
                " g.pk_ci_id = p.pk_ci_id and  g.pk_ci_id = b.pk_ci_id\n" +
                "AND p.pay_trans_datetime >= str_to_date(\n" +
                "  '2016-01-02 12:00:00',\n" +
                "  '%Y-%m-%d %T'\n" +
                ") ###开始时间条件\n" +
                "AND p.pay_trans_datetime <= str_to_date(\n" +
                "  '2020-01-02 12:00:00',\n" +
                "  '%Y-%m-%d %T'\n" +
                ") ###开始结算条件\n" +
                "AND p.pay_isone = 1\n" +
                "and p.pay_trans_datetime = (select min(p1.pay_trans_datetime) from t_pay_payment p1 where p.pk_ci_id = p1.pk_ci_id" +
                " and hi_id = '"+ ShiroUtils.getUserEntity().getHiId()+"' )\n" +
                "order by  p.pay_trans_datetime desc\n" +
                ") t\n" +
                ", (SELECT @rownum := 0) r)q",isall);
        page.setExec_sql(cmdSqlArray[0]);
        List<Map> list = sysUserMapper.execSQL(page);
        pageData.setData(list);
        page.setExec_sql(cmdSqlArray[1]);
        page.setStart(page.getStart()-1);
        pageData.setRecordsTotal(Integer.parseInt(sysUserMapper.execSQL(page).get(0).get("count").toString()));
        pageData.setRecordsFiltered(pageData.getRecordsTotal());


        if(pageData.getData() != null){
            List<Map> data = (List<Map>) pageData.getData();
            //计算消费金额与结算金额
            double bill_all_pay_price = 0;
            double bill_prepay = 0;
            double bill_refundable_amount = 0;
            double bill_all_pay_money = 0;
            double bill_deposit = 0;
            double bill_all_con_money = 0;
            //得到消费金额和结算金额
            for (
                    Map mapInfo : data) {
                if (mapInfo.get("bill_all_pay_price") != null) {
                    bill_all_pay_price += Double.parseDouble(mapInfo.get("bill_all_pay_price").toString());
                }
                if (mapInfo.get("bill_prepay") != null) {
                    bill_prepay += Double.parseDouble(mapInfo.get("bill_prepay").toString());
                }
                if (mapInfo.get("bill_refundable_amount") != null) {
                    bill_refundable_amount += Double.parseDouble(mapInfo.get("bill_refundable_amount").toString());
                }
                if (mapInfo.get("bill_all_pay_money") != null) {
                    bill_all_pay_money += Double.parseDouble(mapInfo.get("bill_all_pay_money").toString());
                }
                if (mapInfo.get("bill_all_con_money") != null) {
                    bill_all_con_money =0.00;
                }
                if (mapInfo.get("bill_deposit") != null) {
                    bill_deposit += Double.parseDouble(mapInfo.get("bill_deposit").toString());
                }
            }
            Map<String,Object> attacheData = new HashMap<>();
            attacheData.put("bill_all_con_money", String.format("%.2f", bill_all_con_money));
            attacheData.put("bill_all_pay_price", String.format("%.2f", bill_all_pay_price));
            attacheData.put("bill_prepay", String.format("%.2f", bill_prepay));
            attacheData.put("bill_refundable_amount", String.format("%.2f", bill_refundable_amount));
            attacheData.put("bill_all_pay_money", String.format("%.2f", bill_all_pay_money));
            attacheData.put("bill_deposit", String.format("%.2f", bill_deposit));
            pageData.setAttachData(attacheData);

        }


        return pageData;
    }


    /**
     * 导出账务汇总
     * @param page
     * @return
     */
    public void exportSummaryBillDetails(Page page) throws Exception{


        PageData pageData = this.summarybillDetails(page);
        if(pageData != null && pageData.getRecordsTotal() > 0){

            String dateRange = page.getParams().get("or_act_arr_time").toString();
            String[] dateArray = dateRange.split(" - ");

            //title
            String title = String.format("自助机账务汇总报表 （%s）", dateRange);
            //filename
            String filename = dateRange.replace(" ","") +".xls";
            //preview data
            List<Map<String, Object>> previewData = (List<Map<String, Object>>)pageData.getData();
            //attache data
            Map<String, Object> attachData = (Map<String, Object>)pageData.getAttachData();
            //统计列标题
            List<Map<String, Object>> cells = new ArrayList<>();
            Map<String,Object> item = new HashMap<>();
            item.put("colname", "xu_id");
            item.put("colcomments", "序号");
            cells.add(item);
            item = new HashMap<>();
            item.put("colname", "or_id");
            item.put("colcomments", "预订单");
            cells.add(item);
            item = new HashMap<>();
            item.put("colname", "or_checkin_id");
            item.put("colcomments", "入住单");
            cells.add(item);
            item = new HashMap<>();
            item.put("colname", "or_source");
            item.put("colcomments", "渠道");
            cells.add(item);
            item = new HashMap<>();
            item.put("colname", "or_mode");
            item.put("colcomments", "类型");
            cells.add(item);
            item = new HashMap<>();
            item.put("colname", "ro_id");
            item.put("colcomments", "房间号");
            cells.add(item);
            item = new HashMap<>();
            item.put("colname", "ro_type_name");
            item.put("colcomments", "房型");
            cells.add(item);
            item = new HashMap<>();
            item.put("colname", "or_ci_name");
            item.put("colcomments", "姓名");
            cells.add(item);
            item = new HashMap<>();
            item.put("colname", "or_act_arr_time");
            item.put("colcomments", "入住时间");
            cells.add(item);
            item = new HashMap<>();
            item.put("colname", "or_act_dep_time");
            item.put("colcomments", "退房时间");
            cells.add(item);
            item = new HashMap<>();
            item.put("colname", "or_act_day");
            item.put("colcomments", "天数");
            cells.add(item);
            item = new HashMap<>();
            item.put("colname", "ro_price");
            item.put("colcomments", "房间单价");
            cells.add(item);
            item = new HashMap<>();
            item.put("colname", "bill_prepay");
            item.put("colcomments", "预付金额");
            cells.add(item);
            item = new HashMap<>();
            item.put("colname", "bill_all_pay_price");
            item.put("colcomments", "支付金额");
            cells.add(item);
            item = new HashMap<>();
            item.put("colname", "bill_deposit");
            item.put("colcomments", "押金");
            cells.add(item);
            item = new HashMap<>();
            item.put("colname", "bill_all_ro_price");
            item.put("colcomments", "房费");
            cells.add(item);
            item = new HashMap<>();
            item.put("colname", "bill_all_con_price");
            item.put("colcomments", "消费品");
            cells.add(item);
            item = new HashMap<>();
            item.put("colname", "bill_all_other_price");
            item.put("colcomments", "其它");
            cells.add(item);
            item = new HashMap<>();
            item.put("colname", "bill_refundable_amount");
            item.put("colcomments", "退款金额");
            cells.add(item);
            item = new HashMap<>();
            item.put("colname", "pay_reality_money");
            item.put("colcomments", "实收金额");
            cells.add(item);
            item = new HashMap<>();
            item.put("colname", "bill_all_con_money");
            item.put("colcomments", "消费金额");
            cells.add(item);
            item = new HashMap<>();
            item.put("colname", "pay_paytype");
            item.put("colcomments", "支付方式");
            cells.add(item);
            item = new HashMap<>();
            item.put("colname", "pay_trans_typebs");
            item.put("colcomments", "支付类型");
            cells.add(item);
            item = new HashMap<>();
            item.put("colname", "pay_bill_no");
            item.put("colcomments", "交易编号");
            cells.add(item);
            item = new HashMap<>();
            item.put("colname", "pay_trans_datetime");
            item.put("colcomments", "交易时间");
            cells.add(item);
            //头部
            List<List<String>> titleHeaderList = new ArrayList<>();
            //第1行
            List<String> l2List = new ArrayList<>();
            l2List.add(ShiroUtils.getUserEntity().getHiId_name());
            titleHeaderList.add(l2List);
            //第2行
            List<String> l3List = new ArrayList<>();
            l3List.add("营业日期");
            l3List.add(dateArray[0]);
            l3List.add("");
            l3List.add("至");
            l3List.add(dateArray[1]);
            l3List.add("");
            titleHeaderList.add(l3List);


            List<CellRangeAddress> regions = new ArrayList<>();
            regions.add(new CellRangeAddress(0, 0, 0, cells.size() - 1));
            regions.add(new CellRangeAddress(1, 1, 0, cells.size() - 1));
            regions.add(new CellRangeAddress(2, 2, 1, 2));
            regions.add(new CellRangeAddress(2, 2, 4, 5));



            //汇总数据
            List<String> footerList = new ArrayList<>();
            footerList.add("合计：");
            footerList.add(previewData.size() + "");
            footerList.add("");
            footerList.add("");
            footerList.add("预支付金额：");
            footerList.add(attachData.get("bill_prepay").toString());
            footerList.add("");
            footerList.add("");
            /*footerList.add("押金：");
            footerList.add(attachData.get("bill_deposit").toString());*/
            footerList.add("");
            footerList.add("");
            footerList.add("退款金额：");
            footerList.add(attachData.get("bill_refundable_amount").toString());
            footerList.add("");
            footerList.add("");
            footerList.add("消费金额：");
            footerList.add(attachData.get("bill_all_con_money").toString());

            POIUntils.exportWorkBook(titleHeaderList, footerList, cells, regions, previewData, title, filename, httpServletResponse);

        }
    }

}
