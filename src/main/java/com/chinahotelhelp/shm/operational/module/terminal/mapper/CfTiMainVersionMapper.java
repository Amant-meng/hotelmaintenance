package com.chinahotelhelp.shm.operational.module.terminal.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.chinahotelhelp.shm.operational.module.sys.entity.Page;
import com.chinahotelhelp.shm.operational.module.terminal.entity.CfTiMainVersion;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *   @author Meng.yang
 *   @ProjectName
 *   @title: CfTiMainVersionMapper
 *   @Description:
 *   @date 2019/11/05
*/
@Mapper
@Repository
public interface CfTiMainVersionMapper extends BaseMapper<CfTiMainVersion> {

    List<Map> execSQL(Page page);
}
