package com.chinahotelhelp.shm.operational.module.exportlist.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("sys_statistics_list")
public class StatisticsList {
    @TableField("statistics_id")
    private String statistics_id;
    @TableField("statistics_sql")
    private String statistics_sql;
    @TableField("statistics_name")
    private String statistics_name;
    @TableField("order_count")
    private int order_count;


    public void setStatistics_id(String statistics_id) {
        this.statistics_id = statistics_id;
    }

    public void setStatistics_sql(String statistics_sql) {
        this.statistics_sql = statistics_sql;
    }

    public void setStatistics_name(String statistics_name) {
        this.statistics_name = statistics_name;
    }

    public void setOrder_count(int order_count) {
        this.order_count = order_count;
    }

    public String getStatistics_id() {
        return statistics_id;
    }

    public String getStatistics_sql() {
        return statistics_sql;
    }

    public String getStatistics_name() {
        return statistics_name;
    }

    public int getOrder_count() {
        return order_count;
    }
}
