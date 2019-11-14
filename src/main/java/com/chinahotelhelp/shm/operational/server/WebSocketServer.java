package com.chinahotelhelp.shm.operational.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chinahotelhelp.shm.operational.config.WebSocketConfig;
import com.chinahotelhelp.shm.operational.module.sys.entity.MessageQueue;
import com.chinahotelhelp.shm.operational.module.sys.entity.SysMessage;
import com.chinahotelhelp.shm.operational.module.sys.entity.SysUser;
import com.chinahotelhelp.shm.operational.module.sys.service.SysMessageService;
import com.chinahotelhelp.shm.operational.module.terminal.entity.RemoteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author Huan.Xia
 * @Title: WebSocketServer
 * @ProjectName merchant-management
 * @Description: TODO
 * @date 2018-11-2916:27
 */


@ServerEndpoint(value = "/websocket", configurator = WebSocketConfig.class)
@Component
public class WebSocketServer {
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    //登录用户信息
    private SysUser sysUser;
    //酒店集团信息
    private String groupId;
    //酒店信息
    private String hotelId;
    @Autowired
    private SysMessageService sysMessageService;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);     //加入set中
        //log.info("有新连接加入！当前在线人数为" + getOnlineCount());
        sysUser = (SysUser) session.getUserProperties().get("user");
        groupId = sysUser.getBlocId();
        hotelId = sysUser.getHiId();


    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        //log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param messge 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String messge, Session session) {
        //log.info("来自客户端的消息:" + message);

       /* //群发消息
        for (WebSocketServer item : webSocketSet) {
            item.sendMessage(sysMessage);
        }*/
    }

    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        //log.error("发生错误");
        error.printStackTrace();
    }


    public void sendMessage(String message) {
        try {
            this.session.getBasicRemote().sendText(message);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void sendByuser(RemoteDTO remoteDTO){
        try {
            if (webSocketSet.size() != 0) {
                for (WebSocketServer item : webSocketSet) {
                    if (item.hotelId.equals(hotelId) && item.sysUser.getId().equals(remoteDTO.getUserId())) {
                        //消息发送
                        System.out.println("消息开始发送到指定用户！");
                        item.sendMessage(JSONObject.toJSONString(remoteDTO));
                        System.out.println("消息发送到指定用户成功！");
                    }else {
                        System.out.println("没有匹配的用户："+JSONObject.toJSONString(remoteDTO));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
