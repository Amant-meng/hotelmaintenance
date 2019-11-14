package com.chinahotelhelp.shm.operational.module.exportlist.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.chinahotelhelp.shm.operational.module.exportlist.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface StatisticsListMapper extends BaseMapper<StatisticsList> {
    StatisticsList findStatisticsList(String statisticsNo);

    String findStatisticsChineseList(String query_id);

    List<Map> findStatisticsChineseDataList(String chineseSql);

    List<Map> findStatisticsMathDataList(String mathSql);

    List<Map> findStatisticsEnglishDataList(String ehglishSql);

    List<Map> findStatisticsHistoryDataList(String historySql);

    List<SysTop> getTopData();

    List<SysChildren> getchildrenData(int id);

    List<SysThree> getysThreeData(int id);

    List<Map> getDate(Map map);

    int getDetailedListCount();

    List<Map> getAllDetailedList();


    List<Map> exportListDetail(String pk_ci_id);

    int getCount(Map mapCount);

    SysTop getTopOneData(String top_id);
}
