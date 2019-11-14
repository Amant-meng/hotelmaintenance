package com.chinahotelhelp.shm.operational.module.sys.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.chinahotelhelp.shm.operational.common.filed.Filed;
import com.chinahotelhelp.shm.operational.common.filed.CombFiled;
import com.chinahotelhelp.shm.operational.common.filed.TextFiled;
import com.chinahotelhelp.shm.operational.module.sys.entity.Page;
import com.chinahotelhelp.shm.operational.module.sys.entity.PageData;
import com.chinahotelhelp.shm.operational.module.sys.entity.SysMessage;
import com.chinahotelhelp.shm.operational.module.sys.mapper.SysMessageMapper;
import com.chinahotelhelp.shm.operational.tools.QueryUntil;
import com.chinahotelhelp.shm.operational.tools.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author YangMeng
 * @Title: SysMessageService
 * @ProjectName merchant-management
 * @Description: 系统消息
 * @date 2018/12/29
 */
@Slf4j
@Service
public class SysMessageService {

    @Autowired
    private SysMessageMapper sysMessageMapper;


    /**
     * 根据传入参数查询消息列表
     * @param page
     * @return
     */
    public PageData getSysMessage(Page page){
        List<Filed> queryFileds = new ArrayList<Filed>();
        Map<String,Object> params = page.getParams();
        String hiId = ShiroUtils.getUserEntity().getHiId();
        params.put("hi_id",hiId);
        if(params != null && params.size() > 0){
            queryFileds.add(new CombFiled("type",params));
            queryFileds.add(new TextFiled("title",params));
            queryFileds.add(new TextFiled("content",params));
            queryFileds.add(new TextFiled("pushdate",params));
            queryFileds.add(new CombFiled("tag",params));
            queryFileds.add(new TextFiled("url",params));
            queryFileds.add(new TextFiled("params",params));
            queryFileds.add(new CombFiled("state",params));
            queryFileds.add(new CombFiled("hi_id",params));
        }
        PageData pageData = new PageData();
        String[] cmdSqlArray = QueryUntil.getQuerySql("id,type,title,content,pushdate,tag,state,url,params",queryFileds," order by pushdate desc","sys_message");
        page.setExec_sql(cmdSqlArray[0]);
        pageData.setData(sysMessageMapper.execSQL(page));
        page.setExec_sql(cmdSqlArray[1]);
        pageData.setRecordsTotal(Integer.parseInt(sysMessageMapper.execSQL(page).get(0).get("count").toString()));
        pageData.setRecordsFiltered(pageData.getRecordsTotal());
        return pageData;

    }

    /**
     * 更新消息状态
     * @param sysMessage
     */
    public void updateMessageStatus(SysMessage sysMessage){
        sysMessage.setState("1");
        sysMessageMapper.updateById(sysMessage);
    }

    /**
     * 把消息全部设置为已读
     */
    public void updateMessageList(){
        Wrapper<SysMessage> sysMessageWrapper = new EntityWrapper<>();
        sysMessageWrapper.eq("state","0");
        String hiId = ShiroUtils.getUserEntity().getHiId();
        sysMessageWrapper.and("hi_id={0}",hiId);
        SysMessage message = new SysMessage();
        message.setState("1");
        sysMessageMapper.update(message,sysMessageWrapper);
    }

    /**
     * 添加系统推送消息
     * @param sysMessage
     */
    public void addSysMessage(SysMessage sysMessage){
        log.info("addSysMessage:" + sysMessage.getContent() );
        System.out.println(sysMessage.getContent());
        try {
            if(!sysMessage.getContent().isEmpty()){//消息不等于空值插入
                sysMessage.setPushDate(new Date());
                sysMessageMapper.insert(sysMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
