package com.chinahotelhelp.shm.operational.config;

import org.apache.shiro.SecurityUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

/**
 * @author Huan.Xia
 * @Title: WebSocketConfig
 * @ProjectName merchant-management
 * @Description: TODO
 * @date 2018-11-2916:18
 */

@Configuration
public class WebSocketConfig extends ServerEndpointConfig.Configurator {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {

        sec.getUserProperties().put("user",  SecurityUtils.getSubject().getPrincipal());
        super.modifyHandshake(sec, request, response);
    }
}
