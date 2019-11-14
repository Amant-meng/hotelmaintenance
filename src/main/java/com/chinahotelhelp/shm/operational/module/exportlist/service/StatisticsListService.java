package com.chinahotelhelp.shm.operational.module.exportlist.service;

import com.chinahotelhelp.shm.operational.common.filed.Filed;
import com.chinahotelhelp.shm.operational.module.exportlist.entity.*;
import com.chinahotelhelp.shm.operational.module.exportlist.mapper.StatisticsListMapper;
import com.chinahotelhelp.shm.operational.module.exportlist.mapper.StudentMapper;
import com.chinahotelhelp.shm.operational.module.sys.entity.Page;
import com.chinahotelhelp.shm.operational.module.sys.entity.PageData;
import com.chinahotelhelp.shm.operational.tools.CommonCount;
import com.chinahotelhelp.shm.operational.tools.CommonMethod;
import com.chinahotelhelp.shm.operational.tools.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @className:CommonMethod
 * @Description:统计及其他
 * @author:wengdajiang
 * @data:2019/1/14
 */
@Service
public class StatisticsListService {
    @Autowired
    private StatisticsListMapper statisticsListMapper;

    @Autowired
    private StudentMapper studentMapper;


    /**
     * @Author: wengdajiang
     * @Description:统计主表新增
     * @Date: 2019/1/14
     * @package : com.chinahotelhelp.shm.businessmanagement.module.exportlist.service
     */
    public int addStatisticsList(StatisticsList statisticsList) {
        int resultInt = statisticsListMapper.insert(statisticsList);
        return resultInt;
    }

    /**
     * @Author: wengdajiang
     * @Description:根据主表ID查询统计表数据
     * @Date: 2019/1/14
     * @package : com.chinahotelhelp.shm.businessmanagement.module.exportlist.service
     */
    public StatisticsList findStatisticsList(String statisticsNo) {
        return statisticsListMapper.findStatisticsList(statisticsNo);

    }

    /**
     * @Author: wengdajiang
     * @Description:获得汇总数据列表
     * @Date: 2019/1/18
     * @package : com.chinahotelhelp.shm.businessmanagement.module.exportlist.service
     */
    public List<List<String>> findStudentList(String statisticsSQL) {
        List<Filed> queryFileds = new ArrayList<Filed>();
        List<Map> mapList = studentMapper.findStudentList(statisticsSQL);
        String format = "0.00";
        String chinesea = "0.00%";
        String mathb = "0.00%";
        String englishc = "0.00%";
        String historyd = "0.00%";
        DecimalFormat dec = new DecimalFormat(format);
        List<List<String>> listInfo = new ArrayList<>();
        for (Map mapInfo : mapList
        ) {
            List<String> listString = new ArrayList<>();
            Map mapTemp = new HashMap();
            int chineseInfo = Integer.parseInt(mapInfo.get("chinese").toString());
            int mathInfo = Integer.parseInt(mapInfo.get("math").toString());
            int englishInfo = Integer.parseInt(mapInfo.get("english").toString());
            int historyInfo = Integer.parseInt(mapInfo.get("history").toString());
            int countInfo = Integer.parseInt(mapInfo.get("count").toString());
            chinesea = dec.format((double) chineseInfo / countInfo * 100) + "%";
            mathb = dec.format((double) mathInfo / countInfo * 100) + "%";
            englishc = dec.format((double) englishInfo / countInfo * 100) + "%";
            historyd = dec.format((double) historyInfo / countInfo * 100) + "%";
            listString.add(mapInfo.get("student_class").toString());
            listString.add(chinesea);
            listString.add(mathb);
            listString.add(englishc);
            listString.add(historyd);
            listInfo.add(listString);
        }
        return listInfo;
    }

    public String findStatisticsSqlList(String query_id) {
        return statisticsListMapper.findStatisticsChineseList(query_id);
    }

    public List<Map> findStatisticsChineseDataList(String chineseSql) {
        return statisticsListMapper.findStatisticsChineseDataList(chineseSql);
    }

    public List<Map> findStatisticsMathDataList(String mathSql) {
        return statisticsListMapper.findStatisticsMathDataList(mathSql);
    }

    public List<Map> findStatisticsEnglishDataList(String ehglishSql) {
        return statisticsListMapper.findStatisticsEnglishDataList(ehglishSql);
    }

    public List<Map> findStatisticsHistoryDataList(String historySql) {
        return statisticsListMapper.findStatisticsHistoryDataList(historySql);
    }


    public List<Map<String, Object>> findStudentGradeList(String sql) {
        return studentMapper.findStudentGradeList(sql);
    }

    public String findStatisticsListData(Map mapParmter) {
        return studentMapper.findStatisticsListData(mapParmter);
    }

    public List<SysTop> getTopData() {
        return statisticsListMapper.getTopData();
    }

    public List<SysChildren> getchildrenData(int id) {
        return statisticsListMapper.getchildrenData(id);
    }

    public List<SysThree> getysThreeData(int id) {
        return statisticsListMapper.getysThreeData(id);
    }

    /**
     * @Author: wengdajiang
     * @Description:
     * @Date: 2019/1/5
     * @package : com.chinahotelhelp.shm.businessmanagement.module.exportlist.service
     */
    public PageData getDetailedList(Page pagedata) {
        String hi_id = ShiroUtils.getUserEntity().getHiId();
        //引入公共方法类
        CommonMethod commonMethod = new CommonMethod();
        //创建返回的封装类
        PageData pageData = new PageData();
        //获得每页长度
        int length = pagedata.getLength();
        int start = pagedata.getStart();
        //获得查询条件时间区间
        String createtime[] = pagedata.getParams().get("createtime").toString().split("/");
        String startDate = createtime[0];
        String endDate = createtime[1];
        //初始SQL语句
        String exec_sql = "SELECT IFNULL(g.or_id, \"\" ) AS or_id,IFNULL(g.or_checkin_id, \"\" ) AS or_checkin_id,IFNULL(g.or_mode, \"\" ) AS or_mode,IFNULL(g.or_source, \"\" ) AS or_source,IFNULL(g.or_status, \"\" ) AS or_status,IFNULL(g.ro_id, \"\" ) AS ro_id,IFNULL(g.ro_type_name, \"\" ) AS ro_type_name,IFNULL(g.or_ci_name, \"\" ) AS or_ci_name,IFNULL(g.or_act_arr_time, \"\" ) AS or_act_arr_time,IFNULL(g.or_act_dep_time, \"\" ) AS or_act_dep_time,IFNULL(g.or_act_day, \"\" ) AS or_act_day,IFNULL(g.bill_prepay, \"\" ) AS bill_prepay,IFNULL(g.bill_deposit, \"\" ) AS bill_deposit,IFNULL(b.bill_all_ro_price, \"\" ) AS bill_all_ro_price,IFNULL(b.bill_all_con_price, \"\" ) AS bill_all_con_price,IFNULL(b.bill_all_other_price, \"\" ) AS bill_all_other_price,IFNULL(b.bill_refundable_amount, \"\" ) AS bill_refundable_amount,IFNULL(b.bill_all_pay_money, \"\" ) AS bill_all_pay_money,IFNULL(p.pay_paytype, \"\" ) AS pay_paytype,IFNULL(p.pay_trans_type  , \"\" ) AS pay_trans_type,IFNULL(p.pay_trans_datetime , \"\" ) AS pay_trans_datetime FROM t_pms_order_guest g,t_pms_bill b,t_pay_payment p  WHERE g.pk_ci_id = b.pk_ci_id AND b.pk_ci_id = p.pk_ci_id AND g.or_act_arr_time >= str_to_date(#{startDate},'%Y-%m-%d %T')  AND g.or_act_arr_time <= str_to_date(#{endDate},'%Y-%m-%d %T') AND ((p.pay_isone = 1  and p.pay_trans_type = '消费') or (p.pay_trans_type = '预授权完成')) ";
        String exec_sql_count = "SELECT count(*) FROM t_pms_order_guest g,t_pms_bill b,t_pay_payment p  WHERE g.pk_ci_id = b.pk_ci_id AND b.pk_ci_id = p.pk_ci_id AND g.or_act_arr_time >= str_to_date(#{startDate},'%Y-%m-%d %T')  AND g.or_act_arr_time <= str_to_date(#{endDate},'%Y-%m-%d %T') AND ((p.pay_isone = 1  and p.pay_trans_type = '消费') or (p.pay_trans_type = '预授权完成')) ";
        //获得查询条件支付方式
        Map map = new HashMap();
        String payWay = "";
        Map mapCount = new HashMap();
        if (pagedata.getParams().get("payWay") != null && !"".equals(pagedata.getParams().get("payWay"))) {
            payWay = pagedata.getParams().get("payWay").toString();
        }
        //过滤酒店ID
        if (hi_id != null && !"".equals(hi_id)) {
            map.put("hi_id", hi_id);
            mapCount.put("hi_id", hi_id);
            exec_sql += " AND g.hi_id = #{hi_id}";
            exec_sql_count += " AND g.hi_id = #{hi_id}";
        }
        if (payWay != null && !"".equals(payWay)) {
            map.put("payWay", payWay);
            mapCount.put("payWay", payWay);
            exec_sql += " AND p.pay_paytype = #{payWay}";
            exec_sql_count += " AND p.pay_paytype = #{payWay}";
        }
        exec_sql += " LIMIT #{start},#{length}";
        //将Map作为参数，分别组装了sql,分页长度，分页的那一页，查询条件时间和支付方式
        map.put("exec_sql", exec_sql);
        map.put("length", length);
        map.put("start", start);
        map.put("startDate", startDate);
        map.put("endDate", endDate);

        mapCount.put("exec_sql_count", exec_sql_count);
        mapCount.put("startDate", startDate);
        mapCount.put("endDate", endDate);

        //执行sql返回List<Map>
        //获取数据库总书记
        int count = statisticsListMapper.getCount(mapCount);
        List<Map> MapTemp = statisticsListMapper.getDate(map);
        //获得公共方法得到的对应数据
        PageData pageDataTemp = commonMethod.getPageData(MapTemp);
        //将返回的数据放在pageData
        pageData.setData(MapTemp);
        pageData.setRecordsTotal(count);
        pageData.setRecordsFiltered(pageData.getRecordsTotal() + 00);

        return pageData;
    }


    /**
     * @Author: wengdajiang
     * @Description:
     * @Date: 2019/1/14
     * @package : com.chinahotelhelp.shm.businessmanagement.module.exportlist.service
     */
    public List<Map> getAllDetailedList() {
        return statisticsListMapper.getAllDetailedList();
    }

    public PageData exportListData(PageParam page) {
        //获得酒店ID
        String hi_id = ShiroUtils.getUserEntity().getHiId();
        PageData pageData = new PageData();
        //获得查询条件时间区间
        String createtArr[] = page.getCreatetime().split("/");
        String startDate = createtArr[0];
        String endDate = createtArr[1];
        //执行的初始SQL语句
        String exec_sql = "SELECT IFNULL(g.or_id, \"\" ) AS or_id,IFNULL(g.or_checkin_id, \"\" ) AS or_checkin_id,IFNULL(g.or_mode, \"\" ) AS or_mode,IFNULL(g.or_source, \"\" ) AS or_source,IFNULL(g.or_status, \"\" ) AS or_status,IFNULL(g.ro_id, \"\" ) AS ro_id,IFNULL(g.ro_type_name, \"\" ) AS ro_type_name,IFNULL(g.or_ci_name, \"\" ) AS or_ci_name,IFNULL(g.or_act_arr_time, \"\" ) AS or_act_arr_time,IFNULL(g.or_act_dep_time, \"\" ) AS or_act_dep_time,IFNULL(g.or_act_day, \"\" ) AS or_act_day,IFNULL(g.bill_prepay, \"\" ) AS bill_prepay,IFNULL(g.bill_deposit, \"\" ) AS bill_deposit,IFNULL(b.bill_all_ro_price, \"\" ) AS bill_all_ro_price,IFNULL(b.bill_all_con_price, \"\" ) AS bill_all_con_price,IFNULL(b.bill_all_other_price, \"\" ) AS bill_all_other_price,IFNULL(b.bill_refundable_amount, \"\" ) AS bill_refundable_amount,IFNULL(b.bill_all_pay_money, \"\" ) AS bill_all_pay_money,IFNULL(p.pay_paytype, \"\" ) AS pay_paytype,IFNULL(p.pay_trans_type  , \"\" ) AS pay_trans_type,IFNULL(p.pay_trans_datetime , \"\" ) AS pay_trans_datetime FROM t_pms_order_guest g,t_pms_bill b,t_pay_payment p  WHERE g.pk_ci_id = b.pk_ci_id AND b.pk_ci_id = p.pk_ci_id AND g.or_act_arr_time >= str_to_date(#{startDate},'%Y-%m-%d %T')  AND g.or_act_arr_time <= str_to_date(#{endDate},'%Y-%m-%d %T') AND ((p.pay_isone = 1  and p.pay_trans_type = '消费') or (p.pay_trans_type = '预授权完成')) ";
        //判断前端传来的对应参数，如果是2就不拼接SQL,查询所有数据，2就拼接数据，拼接分页参数
        Map map = new HashMap();
        int start = page.getLength() * (page.getStart() - 1);
        exec_sql += " AND g.hi_id = #{hi_id}";
        if (page.getPayWay() != null && !"".equals(page.getPayWay())) {
            exec_sql += "AND p.pay_paytype = #{payWay}";
            map.put("payWay", page.getPayWay());
        }
        if (page.getDraw() == 2) {
            exec_sql += " LIMIT  #{start},#{length}";
            map.put("length", page.getLength());
            map.put("start", start);
        }
        //对Map参数添加对应属性
        map.put("exec_sql", exec_sql);
        map.put("hi_id", hi_id);
        map.put("startDate", startDate);
        map.put("endDate", endDate);

        //获得数据总条数
        List<Map> listMap = statisticsListMapper.getDate(map);
        pageData.setRecordsTotal(listMap.size());
        pageData.getRecordsFiltered();
        pageData.setListMap(listMap);
        pageData.setCount(listMap.size());
        return pageData;
    }

    /**
     * @Author: wengdajiang
     * @Description:账务信息表
     * @Date: 2019/1/15
     * @package : com.chinahotelhelp.shm.businessmanagement.module.exportlist.service
     */
    public PageData getAccountingDetailedList(PageParam page) {
        String hi_id = ShiroUtils.getUserEntity().getHiId();
        //引入公共方法类
        CommonMethod commonMethod = new CommonMethod();
        //创建返回的封装类
        PageData pageData = new PageData();
        //获取数据库总数据
        int length = page.getLength();
        //求得分页页数
        //获得每页长度
        String draw = String.valueOf(page.getDraw());
        int start = page.getStart();
        String createtime[] = page.getCreatetime().split("/");
        String startDate = createtime[0];
        String endDate = createtime[1];
        //获得查询条件支付方式
        String payWay = page.getPayWay();
        Map map = new HashMap();
        Map mapCount = new HashMap();
        String bill_name = page.getBill_name();
        String bill_type = page.getBill_type();
        //初始SQL语句
        String exec_sql = "SELECT IFNULL( g.or_id, \"\" ) as or_id,IFNULL( g.or_checkin_id, \"\" ) as or_checkin_id,IFNULL( g.or_mode, \"\" ) as or_mode,IFNULL( g.or_source, \"\" ) as or_source,IFNULL( g.ro_id, \"\" ) as ro_id, IFNULL( g.ro_type_name, \"\" ) as ro_type_name,IFNULL( g.or_ci_name, \"\" ) as or_ci_name,IFNULL( b.bill_type, \"\" ) as bill_type,IFNULL( b.bill_pname, \"\" ) as bill_pname,IFNULL( b.bill_pay_money, \"\" ) as bill_pay_money,IFNULL( b.pay_paytype, \"\" ) as pay_paytype,IFNULL( b.bill_con_money, \"\" ) as bill_con_money,IFNULL( b.bill_pay_money, \"\" ) as bill_pay_money,IFNULL( b.pay_shift_no, \"\" ) as pay_shift_no,IFNULL( b.bill_comments, \"\" ) as bill_comments,IFNULL( b.bill_ptime, \"\" ) as bill_ptime FROM t_pms_order_guest g,t_pms_bill_detail b WHERE g.pk_ci_id = b.pk_ci_id AND g.or_act_arr_time >= str_to_date(#{startDate},'%Y-%m-%d %T') AND g.or_act_arr_time <= str_to_date(#{endDate},'%Y-%m-%d %T')";
        String exec_sql_count = "SELECT count(*) FROM t_pms_order_guest g,t_pms_bill_detail b WHERE g.pk_ci_id = b.pk_ci_id AND g.or_act_arr_time >= str_to_date(#{startDate},'%Y-%m-%d %T') AND g.or_act_arr_time <= str_to_date(#{endDate},'%Y-%m-%d %T')";
        //将Map作为参数，分别组装了sql,分页长度，分页的那一页，查询条件时间和支付方式
        // AND d.bill_pname = #{pay_paytype} AND d.bill_type = #{bill_type} LIMIT #{start},#{length}
        if (payWay != null && !"".equals(payWay)) {
            map.put("payWay", payWay);
            exec_sql += "AND d.pay_paytype = #{payWay}";
            exec_sql_count += "AND d.pay_paytype = #{payWay}";
        }
        //过滤酒店ID
        if (hi_id != null && !"".equals(hi_id)) {
            map.put("hi_id", hi_id);
            mapCount.put("hi_id", hi_id);
            exec_sql += " AND g.hi_id = #{hi_id}";
            exec_sql_count += " AND g.hi_id = #{hi_id}";
        }
        if (bill_name != null && !"".equals(bill_name)) {
            map.put("bill_name", bill_name);
            exec_sql += "AND d.bill_pname = #{bill_name}";
            exec_sql_count += "AND d.bill_pname = #{bill_name}";
        }
        if (bill_type != null && !"".equals(bill_type)) {
            map.put("bill_type", bill_type);
            exec_sql += "AND d.bill_type = #{bill_type}";
            exec_sql_count += "AND d.bill_type = #{bill_type}";
        }
        if (draw.equals("1")) {
            exec_sql += "";
        } else {
            exec_sql += "LIMIT #{start},#{length}";
        }
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        map.put("exec_sql", exec_sql);
        map.put("length", length);
        map.put("start", start);
        map.put("bill_type", bill_type);
        map.put("payWay", payWay);
        map.put("bill_name", bill_name);
        map.put("hi_id", hi_id);


        mapCount.put("startDate", startDate);
        mapCount.put("endDate", endDate);
        mapCount.put("exec_sql_count", exec_sql_count);
        mapCount.put("bill_type", bill_type);
        mapCount.put("hi_id", hi_id);
        mapCount.put("length", length);
        mapCount.put("start", start);
        mapCount.put("payWay", payWay);
        mapCount.put("bill_name", bill_name);
        //执行sql返回List<Map>
        List<Map> MapTemp = statisticsListMapper.getDate(map);
        int count1 = statisticsListMapper.getCount(mapCount);
        CommonCount commonCount = new CommonCount();
        PageData pageData1 = commonCount.getPageData(MapTemp);
        //将返回的数据放在pageData
        pageData.setListMap(MapTemp);
        pageData.setCount(MapTemp.size());
        pageData.setData(MapTemp);
        pageData.setRecordsTotal(count1);
        pageData.setRecordsFiltered(count1);

        return pageData;
    }

    public SysTop getTopOneData(String top_id) {
        return statisticsListMapper.getTopOneData(top_id);
    }
}
