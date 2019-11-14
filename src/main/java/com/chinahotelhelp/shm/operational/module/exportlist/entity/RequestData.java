package com.chinahotelhelp.shm.operational.module.exportlist.entity;

import java.util.List;

/**
 * @className:RequestData
 * @Description:TODO
 * @author:wengdajiang
 * @data:2018/12/29
 */
public class RequestData {
    private List<String> createtime;
    private String top_id;
    private String type;
    private String hotel;

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }


    public String getHotel() {
        return hotel;
    }


    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setTop_id(String top_id) {
        this.top_id = top_id;
    }

    public String getTop_id() {
        return top_id;
    }

    public void setCreatetime(List<String> createtime) {
        this.createtime = createtime;
    }

    public List<String> getCreatetime() {
        return createtime;
    }

}
