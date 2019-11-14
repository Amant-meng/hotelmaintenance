package com.chinahotelhelp.shm.operational.module.sys.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.chinahotelhelp.shm.operational.module.sys.entity.Page;
import com.chinahotelhelp.shm.operational.module.sys.entity.SysMenu;
import com.chinahotelhelp.shm.operational.module.sys.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Huan.Xia
 * @Title: SysUserMapper
 * @ProjectName merchant-management
 * @Description: TODO
 * @date 2018/10/30/0309:52
 */
@Mapper
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * 获取用户权限
     *
     * @param uid
     * @return
     */
    List<SysMenu> getUserPermissionById(String uid);

    List<Map> execSQL(Page page);
}
