package com.chinahotelhelp.shm.operational.common.filed;

import java.util.Map;

/**
 * @author Huan.Xia
 * @Title: CombFiled
 * @ProjectName merchant-management
 * @Description: TODO
 * @date 2018/11/14/01417:10
 */
public class CombFiled extends Filed {
    public CombFiled(String name, Map<String, Object> map) {
        super(name, map);
    }

    @Override
    public String getQuery() {
        return (super.value == null || super.value.toString().length() <=0 )  ? "" : (" and " + this.name +" = #{params." + this.name + "}");
    }
}
