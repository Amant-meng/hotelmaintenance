package com.chinahotelhelp.shm.operational.module.exportlist.entity;

/**
 * @className:PageParam
 * @Description:TODO
 * @author:wengdajiang
 * @data:2019/1/17
 */
public class PageParam {
    private Integer draw;
    private String exec_sql;
    private String createtime;
    private String payWay;
    private String bill_name;
    private String bill_type;
    private String top_id;
    private int length;
    private int start;

    public void setLength(int length) {
        this.length = length;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return length;
    }

    public int getStart() {
        return start;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public void setExec_sql(String exec_sql) {
        this.exec_sql = exec_sql;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public void setBill_name(String bill_name) {
        this.bill_name = bill_name;
    }

    public void setBill_type(String bill_type) {
        this.bill_type = bill_type;
    }

    public void setTop_id(String top_id) {
        this.top_id = top_id;
    }

    public Integer getDraw() {
        return draw;
    }

    public String getExec_sql() {
        return exec_sql;
    }

    public String getCreatetime() {
        return createtime;
    }

    public String getPayWay() {
        return payWay;
    }

    public String getBill_name() {
        return bill_name;
    }

    public String getBill_type() {
        return bill_type;
    }

    public String getTop_id() {
        return top_id;
    }
}
