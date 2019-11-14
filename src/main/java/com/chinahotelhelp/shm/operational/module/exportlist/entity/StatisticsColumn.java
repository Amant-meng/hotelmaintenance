package com.chinahotelhelp.shm.operational.module.exportlist.entity;

import com.baomidou.mybatisplus.annotations.TableName;

@TableName("sys_statistics_column")
public class StatisticsColumn {
    //列字段编号
    private String column_id;
    //统计表ID
    private String statistics_id;
    //列字段sql
    private String column_sql;
    //列字段前端名称
    private String column_name;
   //排序字段
    private int ifsql;
    //类字段属性
    private  String property;
    private  int id;
    private  String label;
    private  String prop;

    public void setId(int id) {
        this.id = id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setProp(String prop) {
        this.prop = prop;
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

    public void setStatistics_id(String statistics_id) {
        this.statistics_id = statistics_id;
    }

    public void setColumn_id(String column_id) {
        this.column_id = column_id;
    }

    public void setColumn_sql(String column_sql) {
        this.column_sql = column_sql;
    }

    public void setColumn_name(String column_name) {
        this.column_name = column_name;
    }

    public void setIfsql(int ifsql) {
        this.ifsql = ifsql;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getColumn_id() {
        return column_id;
    }

    public String getStatistics_id() {
        return statistics_id;
    }

    public String getColumn_sql() {
        return column_sql;
    }

    public String getColumn_name() {
        return column_name;
    }

    public int getIfsql() {
        return ifsql;
    }

    public String getProperty() {
        return property;
    }
}
