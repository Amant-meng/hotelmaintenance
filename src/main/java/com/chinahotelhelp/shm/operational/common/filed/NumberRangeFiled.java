package com.chinahotelhelp.shm.operational.common.filed;

import java.util.Map;

/**
 * @author Huan.Xia
 * @Title: DateRangeFiled
 * @ProjectName merchant-management
 * @Description: TODO
 * @date 2018/11/14/01417:11
 */
public class NumberRangeFiled extends Filed {
    public NumberRangeFiled(String name, Map<String, Object> map) {
        super(name, map);
    }

    @Override
    public String getQuery() {
        if(this.value != null){
            String[] array = this.value.toString().split(" - ");
            String paramStartKey = "param" + super.valueMap.size();
            this.valueMap.put(paramStartKey, array[0]);
            String paramEndKey = "param" + super.valueMap.size();
            this.valueMap.put(paramEndKey, array[1]);
            return String.format(" and %s >= #{params.%s} and %s <= #{params.%s}", this.name,paramStartKey,this.name,paramEndKey);
        }else{
            return  "";
        }
    }
}
