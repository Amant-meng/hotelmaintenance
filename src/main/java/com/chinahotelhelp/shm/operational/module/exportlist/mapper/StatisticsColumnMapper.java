package com.chinahotelhelp.shm.operational.module.exportlist.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.chinahotelhelp.shm.operational.module.exportlist.entity.StatisticsColumn;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface StatisticsColumnMapper extends BaseMapper<StatisticsColumn> {
    /**
    * @Author: wengdajiang
    * @Description:根据top_id 查询列
    * @Date: 2019/1/18
    * @package : com.chinahotelhelp.shm.businessmanagement.module.exportlist.mapper
    */
    List<StatisticsColumn> queryStatisticsColumnByStatisticsNo(String statisticsNo);

    List<StatisticsColumn> queryStatisticsColumnSql(String statistics_id);
}
