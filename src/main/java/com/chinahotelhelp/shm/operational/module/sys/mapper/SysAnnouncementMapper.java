package com.chinahotelhelp.shm.operational.module.sys.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.chinahotelhelp.shm.operational.module.sys.entity.Page;
import com.chinahotelhelp.shm.operational.module.sys.entity.SysAnnouncement;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author YangMeng
 * @Title: SysAnnouncementMapper
 * @ProjectName merchant-management
 * @Description: 系统通告
 * @date 2019/03/01
 */

@Mapper
@Repository
public interface SysAnnouncementMapper extends BaseMapper<SysAnnouncement> {

    List<Map> execSQL(Page page);
}
