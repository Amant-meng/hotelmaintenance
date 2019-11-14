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
import com.chinahotelhelp.shm.operational.module.terminal.mapper.SoftwareinfoMapper;
import com.chinahotelhelp.shm.operational.tools.QueryUntil;
import com.chinahotelhelp.shm.operational.tools.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Classname Softwareinfo
 * @Description 终端软件信息
 * @Date 2019/10/31 9:38
 * @Created by Changliang.yang
 */
@Service
public class SoftwareinfoService {
    @Autowired
    SoftwareinfoMapper softwareinfoMapper;
    public Message add(Softwareinfo softwareinfo) {
        Message message = Message.N();
        message.setSuccess(false);
        message.setMessage("失败");
        try {
            softwareinfo.setTi_sw_id(UUID.randomUUID().toString().replaceAll("-", ""));
            softwareinfo.setC_time(new Date());
            softwareinfo.setC_user_id(ShiroUtils.getUserId());
            softwareinfo.setC_user_name(ShiroUtils.getUserEntity().getUsername());
            softwareinfo.setDel_flag(0);
            softwareinfoMapper.insert(softwareinfo);
            message.setSuccess(true);
            message.setMessage("成功");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return message;
    }

    public Message delBatch(String ti_sw_id) {
        Message message = Message.N();
        message.setSuccess(false);
        message.setMessage("失败");
        try {
            Wrapper<Mirror_image> mirror_imageWrapper = new EntityWrapper<>();
            mirror_imageWrapper.eq("ti_sw_id",ti_sw_id);
            Softwareinfo softwareinfo = softwareinfoMapper.selectById(ti_sw_id);
            softwareinfo.setDel_flag(1);
            softwareinfo.setM_time(new Date());
            softwareinfo.setM_user_id(ShiroUtils.getUserId());
            softwareinfo.setM_user_name(ShiroUtils.getUserEntity().getUsername());
            softwareinfoMapper.updateById(softwareinfo);
            message.setSuccess(true);
            message.setMessage("成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    public Message edit(Softwareinfo softwareinfo) {
        Message message = Message.N();
        message.setSuccess(false);
        message.setMessage("修改失败");
        try {
            Wrapper<Softwareinfo> batchWrapper = new EntityWrapper<>();
            batchWrapper.eq("ti_sw_id", softwareinfo.getTi_sw_id());
            softwareinfo.setM_time(new Date());
            softwareinfo.setM_user_id(ShiroUtils.getUserId());
            softwareinfo.setM_user_name(ShiroUtils.getUserEntity().getUsername());
            softwareinfoMapper.update(softwareinfo, batchWrapper);
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
            queryFileds.add(new TextFiled("ti_sw_id", params));
            queryFileds.add(new TextFiled("ti_sw_name", params));
            queryFileds.add(new TextFiled("ti_sw_path", params));
            queryFileds.add(new TextFiled("ti_sw_version", params));
            queryFileds.add(new TextFiled("ti_sw_introduction", params));
            queryFileds.add(new TextFiled("ti_sw_ar", params));
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
        String[] cmdSqlArray = QueryUntil.getQuerySql("*", queryFileds, " and del_flag='0' ", "cf_ti_softwareinfo");
        page.setExec_sql(cmdSqlArray[0]);
        pageData.setData(softwareinfoMapper.execSQL(page));
        page.setExec_sql(cmdSqlArray[1]);
        pageData.setRecordsTotal(Integer.parseInt(softwareinfoMapper.execSQL(page).get(0).get("count").toString()));
        pageData.setRecordsFiltered(pageData.getRecordsTotal());
        return pageData;
    }
}
