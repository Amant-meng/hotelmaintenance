package com.chinahotelhelp.shm.operational.module.exportlist.entity;

import com.baomidou.mybatisplus.annotations.TableName;

import java.util.List;

/**
 * @className:sys_top
 * @Description:TODO
 * @author:wengdajiang
 * @data:2018/12/28
 */
@TableName("sys_statistics")
public class SysTop {

    private int id;
    private String label;
    private String prop;
    private String width;
    private List<SysChildren> list;

    public void setList(List<SysChildren> list) {
        this.list = list;
    }

    public List<SysChildren> getList() {
        return list;
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

    public void setWidth(String width) {
        this.width = width;
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

    public String getWidth() {
        return width;
    }

}
