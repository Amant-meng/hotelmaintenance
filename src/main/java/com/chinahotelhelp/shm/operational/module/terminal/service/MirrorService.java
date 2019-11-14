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
import com.chinahotelhelp.shm.operational.module.terminal.entity.Batch;
import com.chinahotelhelp.shm.operational.module.terminal.entity.Mirror_image;
import com.chinahotelhelp.shm.operational.module.terminal.mapper.MirrorMapper;
import com.chinahotelhelp.shm.operational.tools.QueryUntil;
import com.chinahotelhelp.shm.operational.tools.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Classname MirrorService
 * @Description 镜像
 * @Date 2019/10/31 9:36
 * @Created by Changliang.yang
 */
@Service
public class MirrorService {
    @Autowired
    private MirrorMapper mirrorMapper;
    public Message addMirror(Mirror_image mirror_image) {
        Message message = Message.N();
        message.setSuccess(false);
        message.setMessage("失败");
        try {
            mirror_image.setMi_id(UUID.randomUUID().toString().replaceAll("-", ""));
            mirror_image.setC_time(new Date());
            mirror_image.setC_user_id(ShiroUtils.getUserId());
            mirror_image.setC_user_name(ShiroUtils.getUserEntity().getUsername());
            mirror_image.setDel_flag(0);
            mirrorMapper.insert(mirror_image);
            message.setSuccess(true);
            message.setMessage("成功");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return message;
    }

    public Message delMirror(String mi_id) {
        Message message = Message.N();
        message.setSuccess(false);
        message.setMessage("失败");
        try {
            Wrapper<Mirror_image> mirror_imageWrapper = new EntityWrapper<>();
            mirror_imageWrapper.eq("mi_id",mi_id);
            Mirror_image mirror_image = mirrorMapper.selectById(mi_id);
            mirror_image.setDel_flag(1);
            mirror_image.setM_time(new Date());
            mirror_image.setM_user_id(ShiroUtils.getUserId());
            mirror_image.setM_user_name(ShiroUtils.getUserEntity().getUsername());
            mirrorMapper.updateById(mirror_image);
            message.setSuccess(true);
            message.setMessage("成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    public Message editMirror(Mirror_image mirror_image) {
        Message message = Message.N();
        message.setSuccess(false);
        message.setMessage("修改失败");
        try {
            Wrapper<Mirror_image> batchWrapper = new EntityWrapper<>();
            batchWrapper.eq("mi_id", mirror_image.getMi_id());
            mirror_image.setM_time(new Date());
            mirror_image.setM_user_id(ShiroUtils.getUserId());
            mirror_image.setM_user_name(ShiroUtils.getUserEntity().getUsername());
            mirrorMapper.update(mirror_image, batchWrapper);
            message.setMessage("修改成功");
            message.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    public PageData selectMirror(Page page) {
        List<Filed> queryFileds = new ArrayList<Filed>();
        Map<String, Object> params = page.getParams();
        if (params != null && params.size() > 0) {
            //设置的查询参数可以和数据库字段一样，实体类的别名不影响
            queryFileds.add(new TextFiled("mi_id", params));
            queryFileds.add(new TextFiled("mi_name", params));
            queryFileds.add(new TextFiled("mi_size", params));
            queryFileds.add(new TextFiled("mi_url", params));
            queryFileds.add(new TextFiled("mi_type", params));
            queryFileds.add(new TextFiled("c_user_id", params));
            queryFileds.add(new TextFiled("c_user_name", params));
            queryFileds.add(new TextFiled("m_user_id", params));
            queryFileds.add(new TextFiled("m_user_name", params));
            queryFileds.add(new DateRangeFiled("c_time", params));
            queryFileds.add(new DateRangeFiled("m_time", params));
            queryFileds.add(new CombFiled("del_flag", params));
        }
        PageData pageData = new PageData();
        String[] cmdSqlArray = QueryUntil.getQuerySql("*", queryFileds, " and del_flag='0' ", "cf_ti_mirror_image");
        page.setExec_sql(cmdSqlArray[0]);
        pageData.setData(mirrorMapper.execSQL(page));
        page.setExec_sql(cmdSqlArray[1]);
        pageData.setRecordsTotal(Integer.parseInt(mirrorMapper.execSQL(page).get(0).get("count").toString()));
        pageData.setRecordsFiltered(pageData.getRecordsTotal());
        return pageData;
    }
}
