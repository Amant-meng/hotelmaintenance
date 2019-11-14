package com.chinahotelhelp.shm.operational.module.hotel.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.chinahotelhelp.shm.operational.module.hotel.entity.CfTiTerminal;
import com.chinahotelhelp.shm.operational.module.sys.entity.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *   @author Meng.yang
 *   @ProjectName
 *   @title: CfTiTerminalMapper
 *   @Description:
 *   @date 2019/10/23
*/
@Mapper
@Repository
public interface CfTiTerminalMapper extends BaseMapper<CfTiTerminal> {

    List<Map> execSQL(Page page);

    List<Map> getTemplateByTiId(String ti_id);

    List<Map> getTemplateParasByTiId(@Param(value = "ti_id") String ti_id, @Param(value = "temp_id") String temp_id);

    List<Map> getTemplateValueByTiId(@Param(value = "ti_id") String ti_id, @Param(value = "temp_id") String temp_id);

}
