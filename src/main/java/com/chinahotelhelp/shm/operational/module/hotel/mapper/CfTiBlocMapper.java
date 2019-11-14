package com.chinahotelhelp.shm.operational.module.hotel.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.chinahotelhelp.shm.operational.module.hotel.entity.CfTiBloc;
import com.chinahotelhelp.shm.operational.module.sys.entity.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *   @author Meng.yang
 *   @ProjectName
 *   @title: CfTiBlocMapper
 *   @Description:
 *   @date 2019/10/23
*/
@Mapper
@Repository
public interface CfTiBlocMapper extends BaseMapper<CfTiBloc> {

    List<Map> execSQL(Page page);

    @Select("select next_val(#{sequenceName})")
    Long getValue(String sequenceName);
}
