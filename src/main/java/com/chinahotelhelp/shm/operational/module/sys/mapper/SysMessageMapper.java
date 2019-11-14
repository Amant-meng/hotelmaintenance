package com.chinahotelhelp.shm.operational.module.sys.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.chinahotelhelp.shm.operational.module.sys.entity.Page;
import com.chinahotelhelp.shm.operational.module.sys.entity.SysMessage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author YangMeng
 * @Title: SysMessageMapper
 * @ProjectName merchant-management
 * @Description: 系统消息
 * @date 2018/12/29
 */

@Mapper
@Repository
public interface SysMessageMapper extends BaseMapper<SysMessage> {
    List<Map> execSQL(Page page);

}
