package com.chinahotelhelp.shm.operational.module.exportlist.entity;

import com.baomidou.mybatisplus.annotations.TableName;

import java.util.List;

/**
 * @className:SysChildren
 * @Description:TODO
 * @author:wengdajiang
 * @data:2018/12/28
 */
@TableName("sys_children")
public class SysChildren {
    private int id;
    private String label;
    private String prop;
    private String width;
    private int topid;
    private List<SysThree> list;

    public void setList(List<SysThree> list) {
        this.list = list;
    }

    public List<SysThree> getList() {
        return list;
    }

    public void setTopid(int topid) {
        this.topid = topid;
    }

    public int getTopid() {
        return topid;
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
