package com.chinahotelhelp.shm.operational.module.sys.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.chinahotelhelp.shm.operational.module.sys.entity.SysFile;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Huan.Xia
 * @Title: SysFileMapper
 * @ProjectName merchant-management
 * @Description: TODO
 * @date 2018/10/30/0309:52
 */
@Mapper
@Repository
public interface SysFileMapper extends BaseMapper<SysFile> {

    SysFile findtype(String taskid);
}
