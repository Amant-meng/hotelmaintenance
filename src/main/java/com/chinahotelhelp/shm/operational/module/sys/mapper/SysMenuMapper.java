package com.chinahotelhelp.shm.operational.module.sys.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.chinahotelhelp.shm.operational.module.sys.entity.SysMenu;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @Auther: 杨昌亮
 * @Date: 2018/11/9 09:10
 * @Description:
 */
@Mapper
@Repository
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    /**
     * 查询角色已拥有的菜单
     *
     * @param rid
     * @return
     */
    List<SysMenu> queryMenuListWithSelected(Integer rid);

}
