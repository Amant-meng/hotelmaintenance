package com.chinahotelhelp.shm.operational.module.exportlist.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.chinahotelhelp.shm.operational.module.exportlist.entity.StatisticsTop;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StatisticsTopMapper extends BaseMapper<StatisticsTop> {
    List<StatisticsTop> findStatisticsTop(String statistics_id);
}
