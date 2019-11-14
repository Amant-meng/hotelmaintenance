package com.chinahotelhelp.shm.operational.module.exportlist.service;

import com.chinahotelhelp.shm.operational.module.exportlist.entity.StatisticsTop;
import com.chinahotelhelp.shm.operational.module.exportlist.mapper.StatisticsTopMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsTopService {
    @Autowired
    private StatisticsTopMapper statisticsTopMapper;

    public int addStatisticsTop(StatisticsTop statisticsTop) {
       int resultInt =  statisticsTopMapper.insert(statisticsTop);
       return resultInt;
    }

    public List<StatisticsTop> findStatisticsTop(String statistics_id) {
        return statisticsTopMapper.findStatisticsTop(statistics_id);
    }
}
