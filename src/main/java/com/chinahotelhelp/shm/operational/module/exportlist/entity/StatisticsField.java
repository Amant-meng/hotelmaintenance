package com.chinahotelhelp.shm.operational.module.exportlist.entity;

import com.baomidou.mybatisplus.annotations.TableName;

@TableName("sys_statistics_field")
public class StatisticsField {
    //字段编号
    private String field_id;
    //统计表ID
    private String statistics_id;
    //统计字段sql
    private String field_sql;
    //统计字段前端名称
    private String field_front_name;

    private int order_count;

    public void setField_id(String field_id) {
        this.field_id = field_id;
    }

    public void setStatistics_id(String statistics_id) {
        this.statistics_id = statistics_id;
    }

    public void setField_sql(String field_sql) {
        this.field_sql = field_sql;
    }

    public void setField_front_name(String field_front_name) {
        this.field_front_name = field_front_name;
    }

    public void setOrder_count(int order_count) {
        this.order_count = order_count;
    }

    public String getField_id() {
        return field_id;
    }

    public String getStatistics_id() {
        return statistics_id;
    }

    public String getField_sql() {
        return field_sql;
    }

    public String getField_front_name() {
        return field_front_name;
    }

    public int getOrder_count() {
        return order_count;
    }
}
