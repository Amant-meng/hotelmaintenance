package com.chinahotelhelp.shm.operational.module.template.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.chinahotelhelp.shm.operational.module.sys.entity.Page;
import com.chinahotelhelp.shm.operational.module.template.entity.CfPtConfig;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *   @author Meng.yang
 *   @ProjectName
 *   @title: CfPtConfigMapper
 *   @Description:
 *   @date 2019/11/04
*/
@Mapper
@Repository
public interface CfPtConfigMapper extends BaseMapper<CfPtConfig> {

    List<Map> execSQL(Page page);
}
