package com.chinahotelhelp.shm.operational.module.terminal.service;

import com.alibaba.fastjson.JSONObject;
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
import com.chinahotelhelp.shm.operational.module.terminal.entity.RemoteDTO;
import com.chinahotelhelp.shm.operational.module.terminal.mapper.BatchMapper;
import com.chinahotelhelp.shm.operational.mq.MessageSender;
import com.chinahotelhelp.shm.operational.server.WebSocketServer;
import com.chinahotelhelp.shm.operational.tools.QueryUntil;
import com.chinahotelhelp.shm.operational.tools.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Classname BatchService
 * @Description 批次
 * @Date 2019/10/31 9:37
 * @Created by Changliang.yang
 */
@Service
public class BatchService {
    @Autowired
    private BatchMapper batchMapper;
    @Autowired
    private MessageSender messageSender;
    @Autowired
    private WebSocketServer webSocketServer;
    public Message addBatch(Batch batch) {
        Message message = Message.N();
        message.setSuccess(false);
        message.setMessage("失败");
        try {
            batch.setBh_id(UUID.randomUUID().toString().replaceAll("-", ""));
            batch.setC_time(new Date());
            batch.setC_user_id(ShiroUtils.getUserId());
            batch.setC_user_name(ShiroUtils.getUserEntity().getUsername());
            batch.setBh_time(new Date());
            batch.setDel_flag(0);
            batchMapper.insert(batch);
            message.setMessage("成功");
            message.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return message;
    }

    public Message delBatch(String bh_id) {
        Message message = new Message();
        message.setMessage("失败");
        message.setSuccess(false);
        try {
            Batch batch = batchMapper.selectById(bh_id);
            if (batch != null) {
                batch.setDel_flag(1);
                batch.setM_time(new Date());
                batch.setM_user_id(ShiroUtils.getUserId());
                batch.setM_user_name(ShiroUtils.getUserEntity().getUsername());
                batchMapper.updateById(batch);
                message.setSuccess(true);
                message.setMessage("删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return message;
    }

    public Message editBatch(Batch batch) {
        Message message = Message.N();
        message.setSuccess(false);
        message.setMessage("修改失败");
        try {
            Wrapper<Batch> batchWrapper = new EntityWrapper<>();
            batchWrapper.eq("bh_id", batch.getBh_id());
            batch.setM_time(new Date());
            batch.setM_user_id(ShiroUtils.getUserId());
            batch.setM_user_name(ShiroUtils.getUserEntity().getUsername());
            batchMapper.update(batch, batchWrapper);
            message.setMessage("修改成功");
            message.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    public PageData selectBatch(Page page) {
        List<Filed> queryFileds = new ArrayList<Filed>();
        Map<String, Object> params = page.getParams();
        if (params != null && params.size() > 0) {
            //设置的查询参数可以和数据库字段一样，实体类的别名不影响
            queryFileds.add(new CombFiled("bh_id", params));
            queryFileds.add(new TextFiled("bh_name", params));
            queryFileds.add(new TextFiled("bh_number", params));
            queryFileds.add(new TextFiled("bh_count", params));
            queryFileds.add(new DateRangeFiled("bh_time", params));
            queryFileds.add(new TextFiled("com_name", params));
            queryFileds.add(new TextFiled("com_ci_name", params));
            queryFileds.add(new DateRangeFiled("com_time", params));
            queryFileds.add(new TextFiled("c_user_id", params));
            queryFileds.add(new TextFiled("c_user_name", params));
            queryFileds.add(new TextFiled("m_user_id", params));
            queryFileds.add(new TextFiled("m_user_name", params));
            queryFileds.add(new DateRangeFiled("c_time", params));
            queryFileds.add(new DateRangeFiled("m_time", params));
            queryFileds.add(new CombFiled("del_flag", params));
        }
        PageData pageData = new PageData();
        String[] cmdSqlArray = QueryUntil.getQuerySql("*", queryFileds, " and del_flag='0' ", "cf_ti_batch");
        page.setExec_sql(cmdSqlArray[0]);
        pageData.setData(batchMapper.execSQL(page));
        page.setExec_sql(cmdSqlArray[1]);
        pageData.setRecordsTotal(Integer.parseInt(batchMapper.execSQL(page).get(0).get("count").toString()));
        pageData.setRecordsFiltered(pageData.getRecordsTotal());
        return pageData;
    }

    public Message sendMassage(RemoteDTO remoteDTO, String terminalId){
        Message message = Message.N();
        try {
            Message message1 = messageSender.ServerMessage(remoteDTO, terminalId);
            message.setMessage(message1.getMessage());
            message.setSuccess(message1.getSuccess());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return message;
    }

    public Message receveInfo(RemoteDTO remoteDTO) {
        Message message1 = Message.N();
        message1.setSuccess(false);
        message1.setMessage("发送失败");
        try {
            if (remoteDTO !=null && remoteDTO.getUserId() != null && remoteDTO.getStatus() ==1)
                webSocketServer.sendByuser(remoteDTO);
            message1.setMessage("推送成功");
            message1.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message1;
    }
}
