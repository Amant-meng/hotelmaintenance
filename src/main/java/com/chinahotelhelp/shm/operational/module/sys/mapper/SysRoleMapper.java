package com.chinahotelhelp.shm.operational.module.sys.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.chinahotelhelp.shm.operational.module.sys.entity.Page;
import com.chinahotelhelp.shm.operational.module.sys.entity.SysRole;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * @Auther: 杨昌亮
 * @Date: 2018/11/8 19:59
 * @Description:
 */
@Mapper
@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {
    List<Map> execSQL(Page page);

    /**
     * 查询用户已拥有的角色
     *
     * @param uid
     * @return
     */
    List<SysRole> queryRoleListWithSelected(String uid);

}
