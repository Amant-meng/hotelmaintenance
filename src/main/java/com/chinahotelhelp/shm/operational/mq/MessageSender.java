
package com.chinahotelhelp.shm.operational.mq;


import com.alibaba.fastjson.JSONObject;
import com.chinahotelhelp.shm.operational.config.RabbitConfig;
import com.chinahotelhelp.shm.operational.module.sys.entity.Message;
import com.chinahotelhelp.shm.operational.module.terminal.entity.RemoteDTO;
import com.chinahotelhelp.shm.operational.tools.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Classname
 * @Description 消息发送
 * @Date 2019/11/8 10:14
 * @Created by Changliang.yang
 */

@Component
@Slf4j

public class MessageSender implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    private Logger logger = LoggerFactory.getLogger("远程运维-----");

    /***
     * @Description 消息发送
     * @Param [remoteDTO]
     * @return void
     * @date 2019/4/16 17:14
     */

    public Message ServerMessage(RemoteDTO remoteDTO, String terminalId) {
        Message message = Message.N();
        message.setSuccess(false);
        message.setMessage("发送失败");
        try {
            remoteDTO.setHotelId(ShiroUtils.getUserEntity().getHiId());
            remoteDTO.setUserId(ShiroUtils.getUserEntity().getId());
            rabbitTemplate.setConfirmCallback(this);
            rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_REMOTE,
                    String.format("%s.%s.%s",
                            "064",
                            terminalId, "remote"), JSONObject.toJSONString(remoteDTO));
            logger.info("远程运维指令：{}", JSONObject.toJSONString(remoteDTO));
            message.setSuccess(true);
            message.setMessage("发送成功");
        } catch (AmqpException e) {
            e.printStackTrace();
        }
        return message;
    }


    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {

    }


    @Override
    public void returnedMessage(org.springframework.amqp.core.Message message, int i, String s, String s1, String s2) {

    }
}
