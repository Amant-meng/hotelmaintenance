package com.chinahotelhelp.shm.operational.tools;

import com.chinahotelhelp.shm.operational.module.sys.entity.PageData;

import java.util.List;
import java.util.Map;

/**
 * @className:CommonCount
 * @Description:TODO
 * @author:wengdajiang
 * @data:2019/2/1
 */
public class CommonCount {
    public PageData getPageData(List<Map> listMap) {
        double bill_con_moneyCount = 0;
        double bill_pay_moneyCount = 0;
        //得到消费金额和结算金额
        for (
                Map mapInfo : listMap) {
            if (mapInfo.get("bill_con_money").equals("")) {
                bill_con_moneyCount = 0;
            } else {
                bill_con_moneyCount += Double.parseDouble(mapInfo.get("bill_con_money").toString());
            }
            if (mapInfo.get("bill_pay_money").equals("")) {
                bill_pay_moneyCount = 0;
            } else {
                bill_pay_moneyCount += Double.parseDouble(mapInfo.get("bill_pay_money").toString());
            }
        }
        PageData pageData =  new PageData();

        return pageData;
    }
}
