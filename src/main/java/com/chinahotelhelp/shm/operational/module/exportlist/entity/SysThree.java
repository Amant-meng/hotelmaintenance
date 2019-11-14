package com.chinahotelhelp.shm.operational.module.exportlist.entity;

import com.baomidou.mybatisplus.annotations.TableName;

/**
 * @className:SysThree
 * @Description:TODO
 * @author:wengdajiang
 * @data:2018/12/28
 */
@TableName("sys_three")
public class SysThree {
    private int id;
    private String label;
    private String prop;
    private int width;
    private int children;
    private int order_count;
    private String statistics_column_sql;

    public void setOrder_count(int order_count) {
        this.order_count = order_count;
    }

    public void setStatistics_column_sql(String statistics_column_sql) {
        this.statistics_column_sql = statistics_column_sql;
    }

    public int getOrder_count() {
        return order_count;
    }

    public String getStatistics_column_sql() {
        return statistics_column_sql;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public String getProp() {
        return prop;
    }

    public int getWidth() {
        return width;
    }

    public int getChildren() {
        return children;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setProp(String prop) {
        this.prop = prop;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setChildren(int children) {
        this.children = children;
    }
}
