package com.chinahotelhelp.shm.operational.module.hotel.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.chinahotelhelp.shm.operational.module.hotel.entity.CfTiLockKeyValue;
import com.chinahotelhelp.shm.operational.module.sys.entity.Page;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *   @author Meng.yang
 *   @ProjectName
 *   @title: CfTiLockKeyValueMapper
 *   @Description:
 *   @date 2019/10/24
*/
@Mapper
@Repository
public interface CfTiLockKeyValueMapper extends BaseMapper<CfTiLockKeyValue> {

    List<Map> execSQL(Page page);

    List<Map> getLockList(String hi_id);
}
