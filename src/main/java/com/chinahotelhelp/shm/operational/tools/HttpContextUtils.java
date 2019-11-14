package com.chinahotelhelp.shm.operational.tools;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @author YangMeng
 * @Title: HttpContextUtils
 * @ProjectName merchant-management
 * @Description: 获取HTTP请求工具类
 * @date 2019/01/15
 */
public class HttpContextUtils {

    /**
     *获取request请求
     * @return
     */
    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

    }

    /**
     * 获取session
     * @return
     */
    public static HttpSession getSession() {
        return getHttpServletRequest().getSession();
    }

}
