package com.chinahotelhelp.shm.operational.module.sys.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.chinahotelhelp.shm.operational.module.sys.entity.Page;
import com.chinahotelhelp.shm.operational.module.sys.entity.SysLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author YangMeng
 * @Title: SysLogMapper
 * @ProjectName merchant-management
 * @Description: 系统日志
 * @date 2018/12/14
 */
@Mapper
@Repository
public interface SysLogMapper extends BaseMapper<SysLog> {
    List<Map> execSQL(Page page);
}
