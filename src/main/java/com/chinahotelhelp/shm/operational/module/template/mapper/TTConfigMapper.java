package com.chinahotelhelp.shm.operational.module.template.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.chinahotelhelp.shm.operational.module.sys.entity.Page;
import com.chinahotelhelp.shm.operational.module.template.entity.TTConfig;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *   @author Meng.yang
 *   @ProjectName
 *   @title: TTConfigMapper
 *   @Description:
 *   @date 2019/10/25
*/
@Mapper
@Repository
public interface TTConfigMapper extends BaseMapper<TTConfig> {

    List<Map> execSQL(Page page);

}
