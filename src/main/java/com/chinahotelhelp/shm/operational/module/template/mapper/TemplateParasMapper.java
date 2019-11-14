package com.chinahotelhelp.shm.operational.module.template.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.chinahotelhelp.shm.operational.module.sys.entity.Page;
import com.chinahotelhelp.shm.operational.module.template.entity.TemplateParas;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by wuyang on 2019/10/24 17:12
 */
@Mapper
@Repository
public interface TemplateParasMapper extends BaseMapper<TemplateParas> {

    List<Map> execSQL(Page page);
}
