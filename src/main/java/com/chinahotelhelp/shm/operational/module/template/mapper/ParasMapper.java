package com.chinahotelhelp.shm.operational.module.template.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.chinahotelhelp.shm.operational.module.sys.entity.Page;
import com.chinahotelhelp.shm.operational.module.template.entity.ParasStructure;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by wuyang on 2019/10/23 17:17
 */
@Mapper
@Repository
public interface ParasMapper extends BaseMapper<ParasStructure> {

    int addParas(ParasStructure parasStructure);

    int modifyParas(ParasStructure parasStructure);

    int deleteParas(String p_id);

    List<Map> execSQL(Page page);
}
