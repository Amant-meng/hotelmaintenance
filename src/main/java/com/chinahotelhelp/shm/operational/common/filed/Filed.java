package com.chinahotelhelp.shm.operational.common.filed;

import java.util.Map;

/**
 * @author Huan.Xia
 * @Title: Filed
 * @ProjectName merchant-management
 * @Description: TODO
 * @date 2018/11/14/01414:15
 */
public abstract class Filed {
    public String name;
    public Object value;
    public Map<String,Object> valueMap;
    public Filed(String name, Map<String,Object> map){
        this.name = name;
        this.value = map.get(name);
        this.valueMap = map;
    }
    public abstract String getQuery();
}
