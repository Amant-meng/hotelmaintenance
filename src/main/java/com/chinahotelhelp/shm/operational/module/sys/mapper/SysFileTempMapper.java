package com.chinahotelhelp.shm.operational.module.sys.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.chinahotelhelp.shm.operational.module.sys.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Huan.Xia
 * @Title: SysFileMapper
 * @ProjectName merchant-management
 * @Description: TODO
 * @date 2018/10/30/0309:52
 */
@Mapper
@Repository
public interface SysFileTempMapper extends BaseMapper<SysFileTemp> {
    /**
     * 批量插入
     * @param list
     */
    void insertBatch(List<SysFileTemp> list);

    /**
     * 获取文件列配置
     * @return
     */
    List<Map<String,Object>> getColConfig(String type);

    /**
     * 获取上传数据前10条
     * @param taskId
     * @return
     */
    List<SysFileTemp> getTempData(String taskId);

    /**
     * 动态执行SQL
     * @param page
     * @return
     */
    List<Map> execSQL(Page page);
}
