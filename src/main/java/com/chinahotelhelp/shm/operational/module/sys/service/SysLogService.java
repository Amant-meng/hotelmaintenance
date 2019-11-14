package com.chinahotelhelp.shm.operational.module.sys.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.chinahotelhelp.shm.operational.common.filed.DateRangeFiled;
import com.chinahotelhelp.shm.operational.common.filed.Filed;
import com.chinahotelhelp.shm.operational.common.filed.TextFiled;
import com.chinahotelhelp.shm.operational.module.sys.entity.Page;
import com.chinahotelhelp.shm.operational.module.sys.entity.PageData;
import com.chinahotelhelp.shm.operational.module.sys.entity.SysLog;
import com.chinahotelhelp.shm.operational.module.sys.mapper.SysLogMapper;
import com.chinahotelhelp.shm.operational.tools.QueryUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author YangMeng
 * @Title: SysLogService
 * @ProjectName merchant-management
 * @Description: 系统日志
 * @date 2018/12/14
 */
@Service
public class SysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;

    /**
     *获取日志信息
     * @param page
     * @return
     */
    public PageData getLogList(Page page) {
        List<Filed> queryFileds = new ArrayList<Filed>();
        Map<String,Object> params = page.getParams();
        if(params != null && params.size() > 0){
            queryFileds.add(new TextFiled("uid",params));
            queryFileds.add(new TextFiled("uname",params));
            queryFileds.add(new TextFiled("operation",params));
            queryFileds.add(new TextFiled("method",params));
            queryFileds.add(new TextFiled("params",params));
            queryFileds.add(new TextFiled("ip",params));
            queryFileds.add(new TextFiled("taketime",params));
            queryFileds.add(new DateRangeFiled("createtime",params));
        }
        PageData pageData = new PageData();
        String[] cmdSqlArray = QueryUntil.getQuerySql("uid,uname,operation,method,params,ip,taketime,createtime",
                queryFileds," and deltag='0' order by createtime desc ","sys_log");
        page.setExec_sql(cmdSqlArray[0]);
        pageData.setData(sysLogMapper.execSQL(page));
        page.setExec_sql(cmdSqlArray[1]);
        pageData.setRecordsTotal(Integer.parseInt(sysLogMapper.execSQL(page).get(0).get("count").toString()));
        pageData.setRecordsFiltered(pageData.getRecordsTotal());
        return pageData;

    }

    /**
     * 通过id获取日志详情
     * @param id
     * @return
     */
    public SysLog getLogDetailsById(String id){
        Wrapper<SysLog> sysLogWrapper = new EntityWrapper<SysLog>();
        sysLogWrapper.eq("id", id);
        List<SysLog> list = sysLogMapper.selectList(sysLogWrapper);
        SysLog sysLog = null;
        if(list !=null && list.size()>0){
            sysLog = list.get(0);
        }
        return sysLog;
    }

    /**
     * 添加操作日志
     * @param sysLog
     */
    public void insert(SysLog sysLog){
        sysLog.setCreatetime(new Date());
        sysLogMapper.insert(sysLog);
    }
}
