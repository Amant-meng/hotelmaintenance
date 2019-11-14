package com.chinahotelhelp.shm.operational.module.terminal.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.chinahotelhelp.shm.operational.common.filed.CombFiled;
import com.chinahotelhelp.shm.operational.common.filed.Filed;
import com.chinahotelhelp.shm.operational.common.filed.TextFiled;
import com.chinahotelhelp.shm.operational.module.sys.entity.Message;
import com.chinahotelhelp.shm.operational.module.sys.entity.Page;
import com.chinahotelhelp.shm.operational.module.sys.entity.PageData;
import com.chinahotelhelp.shm.operational.module.terminal.entity.CfTiMainVersion;
import com.chinahotelhelp.shm.operational.module.terminal.mapper.CfTiMainVersionMapper;
import com.chinahotelhelp.shm.operational.tools.QueryUntil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *   @author Meng.yang
 *   @ProjectName
 *   @title: CfTiMainVersionService
 *   @Description: 版本管理处理层
 *   @date 2019/11/05
*/
@Slf4j
@Service
public class CfTiMainVersionService {

    @Autowired
    private CfTiMainVersionMapper cfTiMainVersionMapper;


    public PageData getVersionList(Page page) {
        List<Filed> queryFileds = new ArrayList<Filed>();
        Map<String, Object> params = page.getParams();
        if (params != null && params.size() > 0) {
            queryFileds.add(new TextFiled("version_no", params));
            queryFileds.add(new TextFiled("version_comments", params));
        }
        PageData pageData = new PageData();
        String[] cmdSqlArray = QueryUntil.getQuerySql("*", queryFileds, " and del_flag=0 order by c_time desc ", "cf_ti_main_version");
        page.setExec_sql(cmdSqlArray[0]);
        pageData.setData(cfTiMainVersionMapper.execSQL(page));
        page.setExec_sql(cmdSqlArray[1]);
        pageData.setRecordsTotal(Integer.parseInt(cfTiMainVersionMapper.execSQL(page).get(0).get("count").toString()));
        pageData.setRecordsFiltered(pageData.getRecordsTotal());
        return pageData;
    }


    public Message delVersion(String pk_id) {
        Message message = new Message();
        try {
            Wrapper<CfTiMainVersion> versionWrapper = new EntityWrapper<>();
            versionWrapper.eq("pk_id", pk_id);
            CfTiMainVersion cfTiMainVersion = new CfTiMainVersion();
            cfTiMainVersion.setDel_flag(1);
            cfTiMainVersionMapper.update(cfTiMainVersion,versionWrapper);
            message.setSuccess(true);
            message.setMessage("删除版本信息成功");
        }catch (Exception e){
            log.info("删除版本信息异常"+e.getMessage());
            message.setSuccess(false);
            message.setMessage("删除版本信息异常");
        }

        return message;
    }


    public Message updateVersion(CfTiMainVersion cfTiMainVersion){
        Message message = Message.N();
        try {
            Wrapper<CfTiMainVersion> versionWrapper = new EntityWrapper<>();
            versionWrapper.eq("pk_id", cfTiMainVersion.getPk_id());
            cfTiMainVersion.setM_time(new Date());
            cfTiMainVersionMapper.update(cfTiMainVersion,versionWrapper);
            message.setSuccess(true);
            message.setMessage("更新版本信息成功");
        }catch (Exception e){
            log.info("更新版本信息异常"+e.getMessage());
            message.setSuccess(false);
            message.setMessage("更新版本信息异常");
        }
        return message;
    }


    public CfTiMainVersion getVersionInfoById(String pk_id){
        CfTiMainVersion cfTiMainVersion = null;
        try {
            Wrapper<CfTiMainVersion> cfTiMainVersionWrapper = new EntityWrapper<>();
            cfTiMainVersionWrapper.eq("pk_id",pk_id);
            List<CfTiMainVersion> cfTiMainVersionList = cfTiMainVersionMapper.selectList(cfTiMainVersionWrapper);

            if(cfTiMainVersionList!=null && cfTiMainVersionList.size()>0){
                cfTiMainVersion = cfTiMainVersionList.get(0);
            }

        }catch (Exception e){
            log.error("获取版本信息异常："+e.getMessage());
        }
        return  cfTiMainVersion;
    }


    public Message addVersion(CfTiMainVersion cfTiMainVersion){
        Message message = Message.N();
        try {
            cfTiMainVersion.setC_time(new Date());
            cfTiMainVersionMapper.insert(cfTiMainVersion);
            message.setSuccess(true);
            message.setMessage("版本信息添加成功");
        }catch (Exception e){

            log.info("版本信息添加异常"+e.getMessage());
            message.setSuccess(false);
            message.setMessage("版本信息添加异常");
        }

        return message;

    }

}
