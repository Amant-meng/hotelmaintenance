package com.chinahotelhelp.shm.operational.module.sys.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.chinahotelhelp.shm.operational.module.sys.entity.Page;
import com.chinahotelhelp.shm.operational.module.sys.entity.SysDict;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * @author Huan.Xia
 * @Title: SysDictMapper
 * @ProjectName merchant-management
 * @Description: TODO
 * @date 2018/11/14/01417:40
 */
@Mapper
@Repository
public interface SysDictMapper extends BaseMapper<SysDict> {
    List<Map> execSQL(Page page);

    List<Map> getConcat();

    List<Map> getConcatType();
}
