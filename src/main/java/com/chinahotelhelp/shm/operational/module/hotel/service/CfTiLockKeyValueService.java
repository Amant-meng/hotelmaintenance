package com.chinahotelhelp.shm.operational.module.hotel.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.chinahotelhelp.shm.operational.module.hotel.entity.CfTiLockKeyValue;
import com.chinahotelhelp.shm.operational.module.hotel.mapper.CfTiLockKeyValueMapper;
import com.chinahotelhelp.shm.operational.module.sys.entity.Message;
import com.chinahotelhelp.shm.operational.tools.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *   @author Meng.yang
 *   @ProjectName
 *   @title: CfTiLockKeyValueService
 *   @Description: 无规则门锁配置业务处理
 *   @date 2019/10/24
*/
@Slf4j
@Service
public class CfTiLockKeyValueService {

    @Autowired
    private CfTiLockKeyValueMapper cfTiLockKeyValueMapper;

    public Message addNoRuleLock(CfTiLockKeyValue cfTiLockKeyValue){
        Message message = Message.N();
        try {

            Wrapper<CfTiLockKeyValue> lockWrapper = new EntityWrapper<>();
            lockWrapper.eq("hi_id",cfTiLockKeyValue.getHi_id());
            List<CfTiLockKeyValue> LockKeyValueList = cfTiLockKeyValueMapper.selectList(lockWrapper);
            if (LockKeyValueList.size()>0){
                cfTiLockKeyValueMapper.delete(lockWrapper);
                cfTiLockKeyValue.setC_time(new Date());
                cfTiLockKeyValue.setC_user_id(ShiroUtils.getUserEntity().getId());
                cfTiLockKeyValue.setC_user_name(ShiroUtils.getUserEntity().getTruename());
                cfTiLockKeyValueMapper.insert(cfTiLockKeyValue);

            }else {

                cfTiLockKeyValue.setC_time(new Date());
                cfTiLockKeyValue.setC_user_id(ShiroUtils.getUserEntity().getId());
                cfTiLockKeyValue.setC_user_name(ShiroUtils.getUserEntity().getTruename());
                cfTiLockKeyValueMapper.insert(cfTiLockKeyValue);

            }
            message.setSuccess(true);
            message.setMessage("门锁配置成功");

        }catch (Exception e){
            message.setSuccess(false);
            message.setMessage("门锁配置异常");
            log.info("【添加门锁失败】"+e.getMessage());
        }

        return message;
    }

    public Message addNoRuleLocks(String hi_id, String roomList){
        Message message = Message.N();
        try {
            JSONArray lists = JSONArray.parseArray(roomList);

            Wrapper<CfTiLockKeyValue> lockWrapper = new EntityWrapper<>();
            lockWrapper.eq("hi_id",hi_id);
            List<CfTiLockKeyValue> LockKeyValueList = cfTiLockKeyValueMapper.selectList(lockWrapper);
            CfTiLockKeyValue cfTiLockKeyValue = new CfTiLockKeyValue();
            if (LockKeyValueList.size()>0){
                cfTiLockKeyValueMapper.delete(lockWrapper);
                for (int i=0;i<lists.size();i++){
                    Map<String,String> map = JSON.parseObject(lists.get(i).toString(),Map.class);
                    cfTiLockKeyValue.setHi_id(hi_id);
                    cfTiLockKeyValue.setRo_id(map.get("ro_id"));
                    cfTiLockKeyValue.setLock_id(map.get("lock_id"));
                    cfTiLockKeyValue.setC_time(new Date());

                    //cfTiLockKeyValue.setC_user_id(ShiroUtils.getUserEntity().getId());
                    //cfTiLockKeyValue.setC_user_name(ShiroUtils.getUserEntity().getTruename());
                    cfTiLockKeyValueMapper.insert(cfTiLockKeyValue);
                }

            }else {
                    for (int i=0;i<lists.size();i++){
                        Map<String,String> map = JSON.parseObject(lists.get(i).toString(),Map.class);
                        cfTiLockKeyValue.setHi_id(hi_id);
                        cfTiLockKeyValue.setRo_id(map.get("ro_id"));
                        cfTiLockKeyValue.setLock_id(map.get("lock_id"));
                        cfTiLockKeyValue.setC_time(new Date());

                        //cfTiLockKeyValue.setC_user_id(ShiroUtils.getUserEntity().getId());
                        //cfTiLockKeyValue.setC_user_name(ShiroUtils.getUserEntity().getTruename());
                        cfTiLockKeyValueMapper.insert(cfTiLockKeyValue);
                    }

            }
            message.setSuccess(true);
            message.setMessage("门锁配置成功");

        }catch (Exception e){
            message.setSuccess(false);
            message.setMessage("门锁配置异常");
            log.info("【添加门锁失败】"+e.getMessage());
        }

        return message;
    }

    public List<Map> getLockList (String hi_id){
        List<Map> lockList = null;
        try{
            lockList = cfTiLockKeyValueMapper.getLockList(hi_id);

        }catch (Exception e){
            log.info("获取门锁失败"+e.getMessage());
        }

        return lockList;
    }

}
