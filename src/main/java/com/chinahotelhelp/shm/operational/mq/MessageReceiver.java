/*
package com.chinahotelhelp.shm.businessmanagement.mq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chinahotelhelp.shm.businessmanagement.config.RabbitConfig;
import com.chinahotelhelp.shm.businessmanagement.module.roomimg.service.RoomImgService;
import com.chinahotelhelp.shm.businessmanagement.module.sys.entity.MessageQueue;
import com.chinahotelhelp.shm.businessmanagement.module.sys.entity.MsgToLocal;
import com.chinahotelhelp.shm.businessmanagement.module.team.service.MerchantService;
import com.chinahotelhelp.shm.businessmanagement.server.WebSocketServer;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

*/
/**
 * @author Huan.Xia
 * @Title: CheckOutReceiver
 * @ProjectName merchant-management
 * @Description: TODO
 * @date 2019-01-0318:03
 *//*

@Component
public class MessageReceiver {
    @Autowired
    WebSocketServer webSocketServer;

    @Autowired
    MerchantService merchantService;

    @Autowired
    RoomImgService roomImgService;

    */
/**
     * 监听消息
     * @param message
     * @param headers
     * @param channel
     * @throws Exception
     *//*

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitConfig.QUEUE_MESSAGE, durable = "true"),
            exchange = @Exchange(value = RabbitConfig.EXCHANGE_MESSAGE, durable = "true", type = "direct"),
            key = "q.cloudtocloud.msg"
    ))
    @RabbitHandler
    public void onOrderMessage(@Payload String message,
                               @Headers Map<String, Object> headers, Message messages,
                               Channel channel) throws Exception {
        //消费者以JSONObject接收消息，再转换为对应的JavaBean
        MessageQueue messageQueue = JSONObject.parseObject(message, MessageQueue.class);
        //检查消息头部，若包含error信息，则拒绝消息，消息会被丢弃，不会重入队列
        if (headers.get("error") != null){
            System.out.println("错误信息error："+headers.get("error")+ "---"+JSONObject.toJSONString(messageQueue));
            channel.basicReject((Long)headers.get(AmqpHeaders.DELIVERY_TAG),false);
        }
        if(messageQueue != null){
            try {
                webSocketServer.sendMessageToHotel(JSONObject.toJSONString(messageQueue), messageQueue.getHotelId());
                channel.basicAck(messages.getMessageProperties().getDeliveryTag(),false);//手动确认，通知MQ消费成功
            } catch (IOException e) {
                try {//重新压入MQ
                    channel.basicRecover();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                e.printStackTrace();
            }
        }
    }

    */
/**
     * 监听自助机回传查询消息
     * @param message
     * @param headers
     * @param channel
     * @throws Exception
     *//*

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitConfig.QUEUE_LOCALTOCLOUD, durable = "true"),
            exchange = @Exchange(value = RabbitConfig.EXCHANGE_LOCALTOCLOUD, durable = "true", type = "topic")
    ))

    */
/**
     * 接收自助机向云端发送请求结果信息
     *//*

    @RabbitHandler
    public void onLocalToCloudMessage(@Payload String message,
                                      @Headers Map<String, Object> headers, Message messages,
                                      Channel channel) throws Exception {
        channel.basicAck(messages.getMessageProperties().getDeliveryTag(),false);//手动确认，通知MQ消费成功
        if(messages != null){
            try {
                String msg = new String(messages.getBody(),"utf-8");
                MsgToLocal msgToLocal = JSON.parseObject(msg, MsgToLocal.class);

                //将消息格式转换为前端约定结构
                MessageQueue messageQueue = new MessageQueue();
                messageQueue.setTag(msgToLocal.getTag());
                messageQueue.setHotelId(msgToLocal.getHotelId());
                messageQueue.setHotelName(msgToLocal.getHotelName());
                messageQueue.setData(msgToLocal.getAttachData());
                messageQueue.setUserId(msgToLocal.getUserId());
                messageQueue.setUserName(msgToLocal.getUserName());
                //团队订单入库
                com.chinahotelhelp.shm.businessmanagement.module.sys.entity.Message result = null;
                if(msgToLocal.getType().equals("TeamOrderReqeust")){
                    result = merchantService.processTeamOrderInfo(messageQueue);
                    messageQueue.setData(null);
                }else if(msgToLocal.getType().equals("RoomTypeOrderReqeust")){
                    result = roomImgService.processFindAllRoomType(messageQueue);
                    messageQueue.setData(result.getData());
                }else if(msgToLocal.getType().equals("getRoomReqeust")){
                    result = roomImgService.processFindAllRoom(messageQueue);
                    messageQueue.setData(result.getData());
                }

                //如果自助机返回查询结果有存在登录的用户则处理反之则遗弃
                messageQueue.setUserName(null);
                messageQueue.setMessage(result.getMessage());
                messageQueue.setHotelName(null);
                messageQueue.setSuccess(result.getSuccess());



                webSocketServer.sendMessageToHotelUser(messageQueue);
            } catch (IOException e) {
                e.printStackTrace();
                try {//重新压入MQ
                    channel.basicRecover();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }


    }




}
*/
