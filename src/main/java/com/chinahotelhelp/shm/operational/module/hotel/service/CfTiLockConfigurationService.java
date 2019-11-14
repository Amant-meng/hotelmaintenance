package com.chinahotelhelp.shm.operational.module.hotel.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.chinahotelhelp.shm.operational.module.hotel.entity.CfTiLockConfiguration;
import com.chinahotelhelp.shm.operational.module.hotel.mapper.CfTiLockConfigurationMapper;
import com.chinahotelhelp.shm.operational.module.sys.entity.Message;
import com.chinahotelhelp.shm.operational.tools.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *   @author Meng.yang
 *   @ProjectName
 *   @title: CfTiLockConfigurationService
 *   @Description: 有规则门锁配置业务处理层
 *   @date 2019/10/24
*/
@Slf4j
@Service
public class CfTiLockConfigurationService {

    @Autowired
    private CfTiLockConfigurationMapper configurationMapper;

    /**
     *  有规则门锁添加
     * @param lockConfiguration
     * @return
     */
    public Message addLockConfiguration(CfTiLockConfiguration lockConfiguration){
        Message message = Message.N();
        try {

            Wrapper<CfTiLockConfiguration> configurationWrapper = new EntityWrapper<>();
            configurationWrapper.eq("hi_id",lockConfiguration.getHi_id());
            List<CfTiLockConfiguration> configurations = configurationMapper.selectList(configurationWrapper);
            if(configurations.size()>0){
                configurationMapper.delete(configurationWrapper);
                lockConfiguration.setM_time(new Date());
                //lockConfiguration.setM_user_id(ShiroUtils.getUserEntity().getId());
                //lockConfiguration.setM_user_name(ShiroUtils.getUserEntity().getTruename());

            }else {

                lockConfiguration.setC_time(new Date());
                //lockConfiguration.setC_user_id(ShiroUtils.getUserEntity().getId());
                //lockConfiguration.setC_user_name(ShiroUtils.getUserEntity().getTruename());
                configurationMapper.insert(lockConfiguration);
            }
            message.setSuccess(true);
            message.setMessage("有规则门锁添加成功");

        }catch (Exception e){
            message.setSuccess(false);
            message.setMessage("有规则门锁添加异常");
            log.info("【添加门锁配置异常】："+e.getMessage());
        }

        return message;
    }

    public CfTiLockConfiguration getLockConfig (String hi_id){
        CfTiLockConfiguration lockConfig = null;
        try {
            Wrapper<CfTiLockConfiguration> configWrapper = new EntityWrapper<>();
            configWrapper.eq("hi_id",hi_id);
            List<CfTiLockConfiguration> lockConfigList =  configurationMapper.selectList(configWrapper);
            if(lockConfigList.size()>0){
                lockConfig = lockConfigList.get(0);
            }

        }catch (Exception e){
            log.info("查询有规则门锁失败"+e.getMessage());
        }

        return lockConfig;

    }


}
