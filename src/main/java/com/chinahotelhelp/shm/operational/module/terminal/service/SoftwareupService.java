package com.chinahotelhelp.shm.operational.module.terminal.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.chinahotelhelp.shm.operational.common.filed.CombFiled;
import com.chinahotelhelp.shm.operational.common.filed.DateRangeFiled;
import com.chinahotelhelp.shm.operational.common.filed.Filed;
import com.chinahotelhelp.shm.operational.common.filed.TextFiled;
import com.chinahotelhelp.shm.operational.module.sys.entity.Message;
import com.chinahotelhelp.shm.operational.module.sys.entity.Page;
import com.chinahotelhelp.shm.operational.module.sys.entity.PageData;
import com.chinahotelhelp.shm.operational.module.terminal.entity.Mirror_image;
import com.chinahotelhelp.shm.operational.module.terminal.entity.Softwareinfo;
import com.chinahotelhelp.shm.operational.module.terminal.entity.Ti_softwareup;
import com.chinahotelhelp.shm.operational.module.terminal.mapper.SoftwareupMapper;
import com.chinahotelhelp.shm.operational.tools.QueryUntil;
import com.chinahotelhelp.shm.operational.tools.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Classname Softwareup
 * @Description 终端软件升级
 * @Date 2019/10/31 9:43
 * @Created by Changliang.yang
 */
@Service
public class SoftwareupService {
    @Autowired
    SoftwareupMapper softwareupMapper;
    public Message add(Ti_softwareup ti_softwareup) {
        Message message = Message.N();
        message.setSuccess(false);
        message.setMessage("失败");
        try {
            ti_softwareup.setPk_id(UUID.randomUUID().toString().replaceAll("-", ""));
            ti_softwareup.setC_time(new Date());
            ti_softwareup.setC_user_id(ShiroUtils.getUserId());
            ti_softwareup.setC_user_name(ShiroUtils.getUserEntity().getUsername());
            ti_softwareup.setDel_flag(0);
            softwareupMapper.insert(ti_softwareup);
            message.setSuccess(true);
            message.setMessage("成功");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return message;
    }

    public Message del(String pk_id) {
        Message message = Message.N();
        message.setSuccess(false);
        message.setMessage("失败");
        try {
            Wrapper<Mirror_image> mirror_imageWrapper = new EntityWrapper<>();
            mirror_imageWrapper.eq("pk_id",pk_id);
            Ti_softwareup ti_softwareup = softwareupMapper.selectById(pk_id);
            ti_softwareup.setDel_flag(1);
            ti_softwareup.setM_time(new Date());
            ti_softwareup.setM_user_id(ShiroUtils.getUserId());
            ti_softwareup.setM_user_name(ShiroUtils.getUserEntity().getUsername());
            softwareupMapper.updateById(ti_softwareup);
            message.setSuccess(true);
            message.setMessage("成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    public Message edit(Ti_softwareup ti_softwareup) {
        Message message = Message.N();
        message.setSuccess(false);
        message.setMessage("修改失败");
        try {
            Wrapper<Ti_softwareup> softwareupEntityWrapper = new EntityWrapper<>();
            softwareupEntityWrapper.eq("pk_id", ti_softwareup.getPk_id());
            ti_softwareup.setM_time(new Date());
            ti_softwareup.setM_user_id(ShiroUtils.getUserId());
            ti_softwareup.setM_user_name(ShiroUtils.getUserEntity().getUsername());
            softwareupMapper.update(ti_softwareup, softwareupEntityWrapper);
            message.setMessage("修改成功");
            message.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    public PageData select(Page page) {
        List<Filed> queryFileds = new ArrayList<Filed>();
        Map<String, Object> params = page.getParams();
        if (params != null && params.size() > 0) {
            //设置的查询参数可以和数据库字段一样，实体类的别名不影响
            queryFileds.add(new TextFiled("pk_id", params));
            queryFileds.add(new TextFiled("ti_id", params));
            queryFileds.add(new TextFiled("ti_sw_version_now", params));
            queryFileds.add(new TextFiled("ti_sw_version_up", params));
            queryFileds.add(new TextFiled("ti_sw_status", params));
            queryFileds.add(new DateRangeFiled("ti_sw_open_date", params));
            queryFileds.add(new DateRangeFiled("ti_sw_finish_date", params));
            queryFileds.add(new TextFiled("ti_sw_comment", params));
            queryFileds.add(new TextFiled("c_user_id", params));
            queryFileds.add(new TextFiled("c_user_name", params));
            queryFileds.add(new TextFiled("m_user_id", params));
            queryFileds.add(new TextFiled("m_user_name", params));
            queryFileds.add(new DateRangeFiled("c_time", params));
            queryFileds.add(new DateRangeFiled("m_time", params));
            queryFileds.add(new CombFiled("del_flag", params));
        }
        PageData pageData = new PageData();
        String[] cmdSqlArray = QueryUntil.getQuerySql("*", queryFileds, " and del_flag='0' ", "cf_ti_softwareup");
        page.setExec_sql(cmdSqlArray[0]);
        pageData.setData(softwareupMapper.execSQL(page));
        page.setExec_sql(cmdSqlArray[1]);
        pageData.setRecordsTotal(Integer.parseInt(softwareupMapper.execSQL(page).get(0).get("count").toString()));
        pageData.setRecordsFiltered(pageData.getRecordsTotal());
        return pageData;
    }
}
