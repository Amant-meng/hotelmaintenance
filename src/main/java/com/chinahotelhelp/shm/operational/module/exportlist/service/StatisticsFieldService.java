package com.chinahotelhelp.shm.operational.module.exportlist.service;

import com.chinahotelhelp.shm.operational.module.exportlist.entity.StatisticsField;
import com.chinahotelhelp.shm.operational.module.exportlist.mapper.StatisticsFieldMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsFieldService {
    @Autowired
    private StatisticsFieldMapper statisticsFieldMapper;


    public int addStatisticsField(StatisticsField statisticsField) {
       int resultInt =  statisticsFieldMapper.insert(statisticsField);
       return resultInt;
    }
}
