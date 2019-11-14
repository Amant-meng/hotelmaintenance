package com.chinahotelhelp.shm.operational.module.template.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.chinahotelhelp.shm.operational.common.filed.CombFiled;
import com.chinahotelhelp.shm.operational.common.filed.Filed;
import com.chinahotelhelp.shm.operational.common.filed.TextFiled;
import com.chinahotelhelp.shm.operational.module.sys.entity.Message;
import com.chinahotelhelp.shm.operational.module.sys.entity.Page;
import com.chinahotelhelp.shm.operational.module.sys.entity.PageData;
import com.chinahotelhelp.shm.operational.module.template.entity.ParasStructure;
import com.chinahotelhelp.shm.operational.module.template.entity.Template;
import com.chinahotelhelp.shm.operational.module.template.mapper.ParasMapper;
import com.chinahotelhelp.shm.operational.tools.QueryUntil;
import com.chinahotelhelp.shm.operational.tools.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by wuyang on 2019/10/23 16:52
 */
@Service
public class ParasService {


    @Autowired
    private ParasMapper parasMapper;
    public Message addParas(ParasStructure parasStructure){
        Message message= Message.N();
        if(parasStructure==null)
        {
            message.setSuccess(false);
            message.setMessage("参数信息为空");
            return message;
        }
        parasStructure.setC_user_name(ShiroUtils.getUserEntity().getUsername());
        parasStructure.setC_user_id(ShiroUtils.getUserId());
        parasStructure.setC_time(new Date());
        int result=parasMapper.insert(parasStructure);
        if(result>0){
            message.setSuccess(true);
            message.setMessage("参数添加成功");
        }else{
            message.setSuccess(false);
            message.setMessage("参数添加失败");
        }
        return message;
    }

    public Message modifyParas(ParasStructure parasStructure){
        Message message= Message.N();
        if(parasStructure==null)
        {
            message.setSuccess(false);
            message.setMessage("参数信息为空");
            return message;
        }
        if(parasStructure.getIs_dict() == 0){
            parasStructure.setDict_type("");
        }
        parasStructure.setM_user_name(ShiroUtils.getUserEntity().getUsername());
        parasStructure.setM_user_id(ShiroUtils.getUserId());
        parasStructure.setM_time(new Date());
        Wrapper<ParasStructure> parasStructureWrapper = new EntityWrapper<>();
        parasStructureWrapper.eq("p_id", parasStructure.getP_id());
        int result=parasMapper.update(parasStructure,parasStructureWrapper);
        if(result>0){
            message.setSuccess(true);
            message.setMessage("参数修改成功");
        }else{
            message.setSuccess(false);
            message.setMessage("参数修改失败");
        }
        return message;
    }

    public Message deleteParas(String p_id){
        Message message= Message.N();
        message.setSuccess(true);
        message.setMessage("参数已删除");
        if(!"".equals(p_id)){
            ParasStructure parasStructure=new ParasStructure();
            parasStructure.setDel_flag(1);
            Wrapper<ParasStructure> parasStructureWrapper = new EntityWrapper<>();
            parasStructureWrapper.eq("p_id", p_id);
            int result=parasMapper.update(parasStructure,parasStructureWrapper);
            if(result<=0){
                message.setSuccess(false);
                message.setMessage("参数删除失败");
            }
        }else{
            message.setSuccess(false);
            message.setMessage("参数ID为空");
        }
        return message;
    }

    public PageData getParas(Page page){
        List<Filed> queryFileds = new ArrayList<Filed>();
        Map<String, Object> params = page.getParams();
        if (params != null && params.size() > 0) {
            queryFileds.add(new CombFiled("p_type", params));
            queryFileds.add(new CombFiled("p_category", params));
            queryFileds.add(new TextFiled("p_name", params));
            queryFileds.add(new TextFiled("p_name_cn", params));
        }
        PageData pageData = new PageData();
        String[] cmdSqlArray = QueryUntil.getQuerySql("*", queryFileds, "", "(select p_id,(select name from core_dict coredict where coredict.id= p_type) p_type_cn,p_type," +
                "(select name from core_dict coredict where coredict.id=p_category)p_category_cn,p_category,p_name,p_name_cn,p_comment," +
                "c_user_name,m_user_name,is_dict,dict_type,c_time,m_time from cf_pt_structure where del_flag=0) a");
        page.setExec_sql(cmdSqlArray[0]);
        pageData.setData(parasMapper.execSQL(page));
        page.setExec_sql(cmdSqlArray[1]);
        pageData.setRecordsTotal(Integer.parseInt(parasMapper.execSQL(page).get(0).get("count").toString()));
        pageData.setRecordsFiltered(pageData.getRecordsTotal());
        return pageData;
    }



    public Message getParasByid(String p_id){
        Message message= Message.N();
        message.setSuccess(true);
        message.setMessage("调用成功");
        if(!"".equals(p_id)){
            Page page=new Page();
            page.setExec_sql("SELECT p_id,p_type,p_category,p_name,p_name_cn,p_comment,is_dict,dict_type FROM cf_pt_structure where p_id='"+p_id+"' and del_flag=0");
            Object object= parasMapper.execSQL(page);
            if(object!=null && ((ArrayList) object).size()>0){
                List<Map<String,Object>> list=(ArrayList)object;
                Map<String,Object> map=list.get(0);
                if(map.containsKey("p_type")){
                    map.put("p_type",Integer.parseInt(map.get("p_type").toString()));
                }
                if(map.containsKey("p_category")){
                    try{
                        map.put("p_category",Integer.parseInt(map.get("p_category").toString()));
                    }catch (Exception ex){}
                }
                message.setData(map);
            }
        }else{
            message.setSuccess(false);
            message.setMessage("参数ID为空");
        }
        return message;
    }

    public Message getParasByTC(String type,String category){
        Message message= Message.N();
        message.setSuccess(true);
        message.setMessage("调用成功");
        String params="";
        if(!"".equals(type)){
            params=" and LOWER(p_type)=LOWER('"+type+"')";
        }
        if(!"".equals(category)){
            params +=" and LOWER(p_category)=LOWER('"+category+"')";
        }
        Page page=new Page();
        page.setExec_sql("select * from cf_pt_structure where 1=1 "+params+" and del_flag=0");
        Object object= parasMapper.execSQL(page);
        if(object!=null && ((ArrayList) object).size()>0){
            List<Map<String,Object>> list=(ArrayList)object;
            message.setData(list);
        }
        return message;
    }
}
