package com.chinahotelhelp.shm.operational.filter;

import com.chinahotelhelp.shm.operational.server.WebSocketServer;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;


import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Huan.Xia
 * @Title: ShiroLoginFilter
 * @ProjectName merchant-management
 * @Description: TODO
 * @date 2018-12-2915:55
 */
public class ShiroLoginFilter extends FormAuthenticationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        httpServletResponse.getWriter().write("{status:403}");
        // 从webSocketSet中移除当前用户的set
        WebSocketServer webSocketServer = new WebSocketServer();
        webSocketServer.onClose();
        return false;
    }



}
