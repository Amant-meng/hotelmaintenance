package com.chinahotelhelp.shm.operational.module.sys.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.chinahotelhelp.shm.operational.module.sys.entity.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Auther: 杨昌亮
 * @Date: 2018/11/23 17:45
 * @Description:
 */
@Mapper
@Repository
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {
}
