package com.chinahotelhelp.shm.operational.module.template.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.chinahotelhelp.shm.operational.common.filed.CombFiled;
import com.chinahotelhelp.shm.operational.common.filed.DateRangeFiled;
import com.chinahotelhelp.shm.operational.common.filed.Filed;
import com.chinahotelhelp.shm.operational.common.filed.TextFiled;
import com.chinahotelhelp.shm.operational.module.hotel.entity.CfTiBloc;
import com.chinahotelhelp.shm.operational.module.sys.entity.Message;
import com.chinahotelhelp.shm.operational.module.sys.entity.Page;
import com.chinahotelhelp.shm.operational.module.sys.entity.PageData;
import com.chinahotelhelp.shm.operational.module.template.entity.Template;
import com.chinahotelhelp.shm.operational.module.template.entity.TemplateParas;
import com.chinahotelhelp.shm.operational.module.template.mapper.TemplateMapper;
import com.chinahotelhelp.shm.operational.module.template.mapper.TemplateParasMapper;
import com.chinahotelhelp.shm.operational.tools.QueryUntil;
import com.chinahotelhelp.shm.operational.tools.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by wuyang on 2019/10/23 11:07
 */
@Service
public class TemplateService {

    @Autowired
    private TemplateMapper templateMapper;

    @Autowired
    private TemplateParasMapper templateParasMapper;

    public Message add(Template template){
        Message message= Message.N();
        if(template==null)
        {
            message.setSuccess(false);
            message.setMessage("模板信息为空");
            return message;
        }
        String templateId=UUID.randomUUID().toString().replaceAll("-", "");
        template.setTemp_id(templateId);
        template.setC_user_name(ShiroUtils.getUserEntity().getUsername());
        template.setC_user_id(ShiroUtils.getUserId());
        template.setC_time(new Date());
        template.setM_time(new Date());
        int result=templateMapper.insert(template);
        if(result>0){
            if(template.getParasIds()!=null){
                addTemplateParas(template.getParasIds(),templateId);
            }
            message.setSuccess(true);
            message.setMessage("模板添加成功");
        }else{
            message.setSuccess(false);
            message.setMessage("模板添加失败");
        }
        return message;
    }

    public Message modify(Template template){
        Message message= Message.N();
        if(template==null)
        {
            message.setSuccess(false);
            message.setMessage("模板信息为空");
            return message;
        }
        template.setM_user_name(ShiroUtils.getUserEntity().getUsername());
        template.setM_user_id(ShiroUtils.getUserId());
        Wrapper<Template> templateWrapper = new EntityWrapper<>();
        templateWrapper.eq("temp_id", template.getTemp_id());
        template.setM_time(new Date());
        int result=templateMapper.update(template,templateWrapper);
        if(result>0){
            message.setSuccess(true);
            message.setMessage("模板修改成功");
            if(template.getParasIds() != null){
                Wrapper<TemplateParas> templateParasWrapper = new EntityWrapper<>();
                templateParasWrapper.eq("temp_id", template.getTemp_id());
                TemplateParas tp=new TemplateParas();
                tp.setDel_flag(1);
                result = templateParasMapper.update(tp,templateParasWrapper);
                if(result>0){
                    addTemplateParas(template.getParasIds(),template.getTemp_id());
                }else{
                    message.setSuccess(false);
                    message.setMessage("模板参数修改失败");
                }
            }
        }else
        {
            message.setSuccess(false);
            message.setMessage("模板修改失败");
        }
        return message;
    }

    public Message delete(String templateId){
        Message message= Message.N();
        message.setSuccess(true);
        message.setMessage("模板已删除");
        if(!"".equals(templateId)){
            Wrapper<Template> templateWrapper = new EntityWrapper<>();
            templateWrapper.eq("temp_id", templateId);
            Template template=new Template();
            template.setDel_flag(1);
            int result=templateMapper.update(template,templateWrapper);
            Wrapper<TemplateParas> templateParasWrapper = new EntityWrapper<>();
            templateWrapper.eq("temp_id", templateId);
            TemplateParas tp=new TemplateParas();
            tp.setDel_flag(1);
            templateParasMapper.update(tp,templateParasWrapper);
             if(result<=0){
                 message.setSuccess(false);
                 message.setMessage("模板删除失败");
             }
        }else{
            message.setSuccess(false);
            message.setMessage("模板ID为空");
        }
        return message;
    }

    public void addTemplateParas(String p_id,String templateId){
        String[] ids=p_id.split(",");
        for (String str : ids) {
            TemplateParas tp=new TemplateParas();
            tp.setP_id(str);
            tp.setTemp_id(templateId);
            tp.setC_user_id(ShiroUtils.getUserId());
            tp.setC_user_name(ShiroUtils.getUserEntity().getUsername());
            tp.setC_time(new Date());
            templateParasMapper.insert(tp);
        }
    }

    public PageData getTemplate(Page page){
        List<Filed> queryFileds = new ArrayList<Filed>();
        Map<String, Object> params = page.getParams();
        if (params != null && params.size() > 0) {
            queryFileds.add(new TextFiled("temp_name", params));
        }
        PageData pageData = new PageData();
        String[] cmdSqlArray = QueryUntil.getQuerySql("*", queryFileds, "", "(select * from cf_pt_template where del_flag=0 order by m_time desc) a");
        page.setExec_sql(cmdSqlArray[0]);
        pageData.setData(templateMapper.execSQL(page));
        page.setExec_sql(cmdSqlArray[1]);
        pageData.setRecordsTotal(Integer.parseInt(templateMapper.execSQL(page).get(0).get("count").toString()));
        pageData.setRecordsFiltered(pageData.getRecordsTotal());
        return pageData;
    }

    public Message getTemplateById(String templateId){
        Message message= Message.N();
        message.setSuccess(true);
        message.setMessage("调用成功");
        if(!"".equals(templateId)){
            Page page=new Page();
            page.setExec_sql("select temp_id,temp_name,p_type,(SELECT GROUP_CONCAT(CONCAT('',p_id,'')) FROM cf_pt_template_paras b where b.del_flag=0 and a.temp_id=b.temp_id) parasIds from cf_pt_template a  where a.del_flag=0 and a.temp_id='"+templateId+"'");
            Object object= templateMapper.execSQL(page);
            if(object!=null && ((ArrayList) object).size()>0){
                List<Map<String,Object>> list=(ArrayList)object;
                Map<String,Object> map=list.get(0);
                if(map.containsKey("p_type")){
                    map.put("p_type",Integer.parseInt(map.get("p_type").toString()));
                }
                message.setData(map);
            }
        }else{
            message.setSuccess(false);
            message.setMessage("模板ID为空");
        }
        return message;
    }

    public Message getAllTemplate(){
        Message message= Message.N();
        message.setSuccess(true);
        message.setMessage("调用成功");
        Page page=new Page();
        page.setExec_sql("select temp_id,temp_name from cf_pt_template where del_flag = 0");
        Object object= templateMapper.execSQL(page);
        if(object!=null && ((ArrayList) object).size()>0){
            List<Map<String,Object>> list=(ArrayList)object;
            message.setData(list);
        }
        return message;
    }
}
