package com.chinahotelhelp.shm.operational.module.terminal.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.chinahotelhelp.shm.operational.module.sys.entity.Page;
import com.chinahotelhelp.shm.operational.module.terminal.entity.Batch;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Classname BatchMapper
 * @Description 批次
 * @Date 2019/10/31 9:45
 * @Created by Changliang.yang
 */
@Mapper
@Repository
public interface BatchMapper extends BaseMapper<Batch> {
    List<Map> execSQL(Page page);
}
