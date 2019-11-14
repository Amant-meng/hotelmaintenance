package com.chinahotelhelp.shm.operational.module.exportlist.service;

import com.chinahotelhelp.shm.operational.module.exportlist.entity.StatisticsColumn;
import com.chinahotelhelp.shm.operational.module.exportlist.mapper.StatisticsColumnMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsColumnService {
    @Autowired
    private StatisticsColumnMapper statisticsColumnMapper;


    public int addStatisticsColumn(StatisticsColumn statisticsColumn) {
        int resultInt = statisticsColumnMapper.insert(statisticsColumn);
        return resultInt;
    }

    public List<StatisticsColumn> queryStatisticsColumnByStatisticsNo(String statisticsNo) {
        return statisticsColumnMapper.queryStatisticsColumnByStatisticsNo(statisticsNo);
    }

    public List<StatisticsColumn> queryStatisticsColumnSql(String statistics_id) {
        return statisticsColumnMapper.queryStatisticsColumnSql(statistics_id);
    }
}
