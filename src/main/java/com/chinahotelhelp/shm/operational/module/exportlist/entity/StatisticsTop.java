package com.chinahotelhelp.shm.operational.module.exportlist.entity;

import com.baomidou.mybatisplus.annotations.TableName;

@TableName("sys_statistics_top")
public class StatisticsTop {
    //表头编号
    private String top_id;
    //
    private String total_header_id;
    //表头字段sql
    private String top_sql;
    //表头前端名称
    private String top_name;
    //排列字段
    private int order_count;
    //表头属性名
    private String top_property;


    public void setTotal_header_id(String total_header_id) {
        this.total_header_id = total_header_id;
    }

    public String getTotal_header_id() {
        return total_header_id;
    }

    public void setTop_id(String top_id) {
        this.top_id = top_id;
    }

    public void setTop_sql(String top_sql) {
        this.top_sql = top_sql;
    }

    public void setTop_name(String top_name) {
        this.top_name = top_name;
    }

    public void setOrder_count(int order_count) {
        this.order_count = order_count;
    }

    public void setTop_property(String top_property) {
        this.top_property = top_property;
    }

    public String getTop_id() {
        return top_id;
    }

    public String getTop_sql() {
        return top_sql;
    }

    public String getTop_name() {
        return top_name;
    }

    public int getOrder_count() {
        return order_count;
    }

    public String getTop_property() {
        return top_property;
    }
}
