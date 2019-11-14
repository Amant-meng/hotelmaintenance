package com.chinahotelhelp.shm.operational.module.sys.controller;

import com.chinahotelhelp.shm.operational.module.sys.entity.SysUser;
import org.apache.shiro.SecurityUtils;

/**
 * @author Huan.Xia
 * @Title: BaseController
 * @ProjectName merchant-management
 * @Description: TODO
 * @date 2018/11/13/01318:42
 */
public class BaseController {
    public SysUser getCurtUser() {
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        return user;
    }
}
