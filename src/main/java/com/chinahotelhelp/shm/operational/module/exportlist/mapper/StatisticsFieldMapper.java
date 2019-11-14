package com.chinahotelhelp.shm.operational.module.exportlist.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.chinahotelhelp.shm.operational.module.exportlist.entity.StatisticsField;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface StatisticsFieldMapper extends BaseMapper<StatisticsField> {
}
