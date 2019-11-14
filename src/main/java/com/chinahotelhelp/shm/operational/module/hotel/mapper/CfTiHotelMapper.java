package com.chinahotelhelp.shm.operational.module.hotel.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.chinahotelhelp.shm.operational.module.hotel.entity.CfTiHotel;
import com.chinahotelhelp.shm.operational.module.sys.entity.Page;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *   @author Meng.yang
 *   @ProjectName
 *   @title: CfTiHotelMapper
 *   @Description:
 *   @date 2019/10/23
*/
@Mapper
@Repository
public interface CfTiHotelMapper extends BaseMapper<CfTiHotel> {

    /**
     * 通过集团id获取酒店列表
     * @param bloc_id
     * @return
     */
    List<CfTiHotel> getHotelListByBolcId(String bloc_id);

    List<Map> execSQL(Page page);
}
