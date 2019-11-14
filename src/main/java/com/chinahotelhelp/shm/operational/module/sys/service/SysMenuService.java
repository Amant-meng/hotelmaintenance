package com.chinahotelhelp.shm.operational.module.sys.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.chinahotelhelp.shm.operational.module.sys.entity.SysMenu;
import com.chinahotelhelp.shm.operational.module.sys.entity.SysRoleMenu;
import com.chinahotelhelp.shm.operational.module.sys.mapper.SysMenuMapper;
import com.chinahotelhelp.shm.operational.module.sys.mapper.SysRoleMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: 杨昌亮
 * @Date: 2018/11/9 09:15
 * @Description:
 */
@Service
public class SysMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;
    /**
     * 添加菜单
     *
     * @param sysMenu 菜单信息
     */
    @CacheEvict(value = "sysmenu", key = "#sysMenu.id")
    public void addMenu(SysMenu sysMenu) {
        try {
            sysMenuMapper.insert(sysMenu);
        } catch (Exception e) {
            throw new RuntimeException("添加菜单失败，请检查输入的数据");
        }
    }

    /**
     * 修改菜单
     *
     * @param sysMenu
     */
    @CacheEvict(value = "sysmenu", key = "#sysMenu.id")
    public void updateMenu(SysMenu sysMenu) {
        try {
            sysMenuMapper.updateById(sysMenu);
        } catch (Exception e) {
            throw new RuntimeException("修改菜单失败，请检查数据");
        }
    }

    /**
     * 删除菜单
     *
     * @param id
     */
    @CacheEvict(value = "sysmenu", key = "#id")
    public void delMenu(Integer id) {
        Wrapper<SysMenu> sysMenuWrapper = new EntityWrapper<SysMenu>();
        sysMenuWrapper.eq("id", id);
        SysMenu sysMenu = new SysMenu();
        sysMenu.setDeltag("1");
        sysMenuMapper.update(sysMenu, sysMenuWrapper);
        Wrapper<SysRoleMenu> sysMenuEntityWrapper = new EntityWrapper<SysRoleMenu>();
        sysMenuEntityWrapper.eq("mid", id);
        sysRoleMenuMapper.delete(sysMenuEntityWrapper);
    }

    /**
     * 获取菜单列表
     *
     * @param
     * @return
     */
    public List<SysMenu> getMenuList() {
        Wrapper<SysMenu> sysMenuWrapper = new EntityWrapper<>();
        sysMenuWrapper.eq("deltag","0");
        sysMenuWrapper.orderBy("orderid");
        return sysMenuMapper.selectList(sysMenuWrapper);

    }

    /**
     * 根据id获取菜单信息
     *
     * @param id
     * @return
     */
    @Cacheable(value = "sysmenu", key = "#id")
    public SysMenu getMenuInfo(Integer id) {
        Wrapper<SysMenu> sysMenuWrapper = new EntityWrapper<SysMenu>();
        sysMenuWrapper.eq("id", id);
        List<SysMenu> list = sysMenuMapper.selectList(sysMenuWrapper);
        SysMenu sysMenu = null;
        if (list != null && list.size() > 0) {
            sysMenu = list.get(0);
        }
        return sysMenu;
    }

    /**
     * 根据角色id获取菜单列表用于授权
     *
     * @param rid 角色id
     * @return 菜单列表
     */

    public List<SysMenu> queryMenuListWithSelected(Integer rid) {
        return sysMenuMapper.queryMenuListWithSelected(rid);

    }
}