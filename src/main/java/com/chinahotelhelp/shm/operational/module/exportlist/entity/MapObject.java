package com.chinahotelhelp.shm.operational.module.exportlist.entity;

import java.util.List;
import java.util.Map;

public class MapObject {
    private List<Map> list;
    private List<StatisticsTop> list2;

    public void setList(List<Map> list) {
        this.list = list;
    }

    public void setList2(List<StatisticsTop> list2) {
        this.list2 = list2;
    }

    public List<Map> getList() {
        return list;
    }

    public List<StatisticsTop> getList2() {
        return list2;
    }
}
