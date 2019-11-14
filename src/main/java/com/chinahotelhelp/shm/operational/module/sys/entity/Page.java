package com.chinahotelhelp.shm.operational.module.sys.entity;


import com.chinahotelhelp.shm.operational.tools.JSONUtil;

import java.util.Map;

/**
 * @author Huan.Xia
 * @Title: Page
 * @ProjectName merchant-management
 * @Description: TODO
 * @date 2018/11/14/01413:41
 */
public class Page {
    private Integer draw;
    private Integer start;
    private Integer length;
    private Map<String, Object> params;
    private String exec_sql;


    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        start = (start > 0 ?  start : 1);
        this.start = (start - 1) * length;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(String params) {

        this.params = JSONUtil.parseMap(params);

    }
    public void setMapToParams(Map<String, Object> params){
        this.params = params;
    }

    public String getExec_sql() {
        return exec_sql;
    }

    public void setExec_sql(String exec_sql) {
        this.exec_sql = exec_sql;
    }
}
