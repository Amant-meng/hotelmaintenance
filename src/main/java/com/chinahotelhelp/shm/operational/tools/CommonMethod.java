package com.chinahotelhelp.shm.operational.tools;

import com.chinahotelhelp.shm.operational.module.sys.entity.PageData;

import java.util.List;
import java.util.Map;

/**
 * @className:CommonMethod
 * @Description:TODO
 * @author:wengdajiang
 * @data:2019/1/14
 */
public class CommonMethod {
    public PageData getPageData(List<Map> listMap) {
        //预付金额
        double tatlebill_prepay = 0;
        //消费金额
        double tatalall_price = 0;
        //退款金额
        double totalrefundable_amount = 0;
        //结算金额
        double totalall_pay_money = 0;
        //构建汇总数据
        for (Map mapTemp : listMap) {
            double tatlebill;
            double ro_all_price;
            double con_all_price;
            double other_all_price;
            double refundable_amount;
            double bill_all_pay_money;
            if (mapTemp.get("bill_refundable_amount").equals("")) {
                refundable_amount = 0;
            } else {
                refundable_amount = Double.parseDouble(mapTemp.get("bill_refundable_amount").toString());
            }
            if (mapTemp.get("bill_all_pay_money").equals("")) {
                bill_all_pay_money = 0;
            } else {
                bill_all_pay_money = Double.parseDouble(mapTemp.get("bill_all_pay_money").toString());
            }
            if (mapTemp.get("bill_prepay").equals("")) {
                tatlebill = 0;
            } else {
                tatlebill = Double.parseDouble(mapTemp.get("bill_prepay").toString());
            }


            if (mapTemp.get("bill_all_ro_price").equals("")) {
                ro_all_price = 0;
            } else {
                ro_all_price = Double.parseDouble(mapTemp.get("bill_all_ro_price").toString());
            }
            if (mapTemp.get("bill_all_con_price").equals("")) {
                con_all_price = 0;
            } else {
                con_all_price = Double.parseDouble(mapTemp.get("bill_all_con_price").toString());
            }
            if (mapTemp.get("bill_all_other_price").equals("")) {
                other_all_price = 0;
            } else {
                other_all_price = Double.parseDouble(mapTemp.get("bill_all_other_price").toString());

            }
            tatlebill_prepay += tatlebill;
            totalrefundable_amount += refundable_amount;
            tatalall_price += ro_all_price;
            tatalall_price += con_all_price;
            tatalall_price += other_all_price;
            totalall_pay_money += bill_all_pay_money;
        }

        PageData pageData = new PageData();

        return pageData;
    }
}
