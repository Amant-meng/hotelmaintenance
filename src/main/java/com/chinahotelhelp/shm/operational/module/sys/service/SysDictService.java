package com.chinahotelhelp.shm.operational.module.sys.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.chinahotelhelp.shm.operational.common.filed.CombFiled;
import com.chinahotelhelp.shm.operational.common.filed.DateRangeFiled;
import com.chinahotelhelp.shm.operational.common.filed.Filed;
import com.chinahotelhelp.shm.operational.common.filed.TextFiled;
import com.chinahotelhelp.shm.operational.module.sys.entity.*;
import com.chinahotelhelp.shm.operational.module.sys.mapper.SysDictMapper;

import com.chinahotelhelp.shm.operational.tools.QueryUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Huan.Xia
 * @Title: SysDictService
 * @ProjectName merchant-management
 * @Description: TODO
 * @date 2018/11/14/01417:41
 */
@Service
public class SysDictService {
    @Autowired
    private SysDictMapper sysDictMapper;

    public List<SysDict> info() {
        Wrapper<SysDict> sysDictWrapper = new EntityWrapper<>();
        sysDictWrapper.orderBy("TYPE");
        return sysDictMapper.selectList(sysDictWrapper);
    }

    public List<SysDict> getInfo(String type) {
        Wrapper<SysDict> sysDictWrapper = new EntityWrapper<>();
        sysDictWrapper.eq("type", type);
        sysDictWrapper.orderBy("SORT");
        return sysDictMapper.selectList(sysDictWrapper);
    }

    /**
     * 修改字典表
     *
     * @param sysDict
     */
    public Message updateDict(SysDict sysDict) {
        Message message = Message.N();
        message.setSuccess(false);
        message.setMessage("修改失败");
        try {
            sysDictMapper.updateById(sysDict);
            message.setSuccess(true);
            message.setMessage("修改成功");
        } catch (Exception e) {
            throw new RuntimeException("修改失败，请检查数据");
        }
        return message;
    }

    /**
     * 获取字典表所有数据
     */
    public PageData getDict(Page page) {
        List<Filed> queryFileds = new ArrayList<Filed>();
        Map<String, Object> params = page.getParams();
        if (params != null && params.size() > 0) {
            //设置的查询参数可以和数据库字段一样，实体类的别名不影响
            queryFileds.add(new CombFiled("id", params));
            queryFileds.add(new TextFiled("value", params));
            queryFileds.add(new TextFiled("name", params));
            queryFileds.add(new TextFiled("type", params));
            queryFileds.add(new TextFiled("type_name", params));
            queryFileds.add(new CombFiled("parent", params));
        }
        PageData pageData = new PageData();
        String[] cmdSqlArray = QueryUntil.getQuerySql("id,value,name,type,type_name,sort,parent," +
                "del_flag,remark,create_time", queryFileds, " and DEL_FLAG='0' ", "core_dict");
        page.setExec_sql(cmdSqlArray[0]);
        pageData.setData(sysDictMapper.execSQL(page));
        page.setExec_sql(cmdSqlArray[1]);
        pageData.setRecordsTotal(Integer.parseInt(sysDictMapper.execSQL(page).get(0).get("count").toString()));
        pageData.setRecordsFiltered(pageData.getRecordsTotal());
        return pageData;
    }

    /**
     * 根据VALUE获取数据
     */
    public List<SysDict> getPcodeDict(String VALUE) {
        Wrapper<SysDict> sysDictWrapper = new EntityWrapper<>();
        sysDictWrapper.eq("value", VALUE);
        List<SysDict> sysDicts = sysDictMapper.selectList(sysDictWrapper);
        return sysDicts;
    }

    public Message delPcodeDict(String ID) {
        Message message = Message.N();
        message.setSuccess(false);
        message.setMessage("删除失败！");
        try {
            SysDict sysDict = sysDictMapper.selectById(ID);
            sysDict.setDel_flag(1);
            sysDictMapper.updateById(sysDict);
            message.setMessage("删除成功！");
            message.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    public Message addPcodeDict(SysDict sysDict) {
        Message message = Message.N();
        message.setSuccess(false);
        message.setMessage("添加失败！");
        try {
            Wrapper<SysDict> sysDictWrapper = new EntityWrapper<>();
            sysDictWrapper.eq("type", sysDict.getTYPE());
            sysDictWrapper.eq("value", sysDict.getVALUE());
            sysDictWrapper.and("name={0}", sysDict.getNAME());
            List<SysDict> sysDicts = sysDictMapper.selectList(sysDictWrapper);
            if (sysDicts != null && sysDicts.size() > 0) {
                message.setMessage("无法添加重复字典编码或名称");
                message.setSuccess(false);
            } else {
                sysDict.setDel_flag(0);
                sysDict.setCreate_time(new Date());
                sysDictMapper.insert(sysDict);
                message.setSuccess(true);
                message.setMessage("添加成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return message;
    }

    /**
     * 根据ID获取字典表数据详细信息
     *
     * @param ID
     * @return
     */
    public Message getDictInfo(Integer ID) {
        Message message = Message.N();
        message.setMessage("失败");
        message.setSuccess(false);
        try {
            SysDict sysDict = sysDictMapper.selectById(ID);
            message.setData(sysDict);
            message.setSuccess(true);
            message.setMessage("成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;

    }

    /**
     * 根据id获取字典表数据子集详细信息
     */
    public Message getChildInfo(String value) {
        Message message = Message.N();
        message.setSuccess(false);
        message.setMessage("失败");
        try {
            Wrapper<SysDict> sysDictWrapper = new EntityWrapper<>();
            sysDictWrapper.eq("type", "para_category");
            sysDictWrapper.eq("parent", value);
            sysDictWrapper.orderBy("SORT");
            List<SysDict> sysDicts = sysDictMapper.selectList(sysDictWrapper);
            message.setMessage("成功");
            message.setSuccess(true);
            message.setData(sysDicts);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    public Message getConcat(Integer id) {
        Message message = Message.N();
        message.setSuccess(false);
        message.setMessage("失败");
        try {
            if (id == 1) {
                List<Map> concat = sysDictMapper.getConcat();
                message.setMessage("成功");
                message.setSuccess(true);
                message.setData(concat);
            }
            if (id == 2) {
                List<Map> concatType = sysDictMapper.getConcatType();
                message.setMessage("成功");
                message.setSuccess(true);
                message.setData(concatType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    public Message getType(){
        Message message= Message.N();
        message.setSuccess(true);
        message.setMessage("调用成功");
        Page page=new Page();
        page.setExec_sql("select type,type_name from core_dict where del_flag=0 group by type,type_name");
        Object object= sysDictMapper.execSQL(page);
        if(object!=null && ((ArrayList) object).size()>0){
            List<Map<String,Object>> list=(ArrayList)object;
            message.setData(list);
        }
        return message;
    }
}
