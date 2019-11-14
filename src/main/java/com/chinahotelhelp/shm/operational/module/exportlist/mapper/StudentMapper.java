package com.chinahotelhelp.shm.operational.module.exportlist.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.chinahotelhelp.shm.operational.module.exportlist.entity.Tstudent;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Mapper
@Repository
public interface StudentMapper extends BaseMapper<Tstudent> {


    List<Map> findStudentList(String statisticsSQL);

    List<Map<String,Object>> findStudentGradeList(String sql);

    String findStatisticsListData(Map mapParmter);
}
