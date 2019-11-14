package com.chinahotelhelp.shm.operational.filter;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Huan.Xia
 * @Title: CustAuthorizationException
 * @ProjectName merchant-management
 * @Description: TODO
 * @date 2019-03-1622:46
 */
@ControllerAdvice
public class CustAuthorizationException {
    @ExceptionHandler({AuthorizationException.class})

    public void handleException(Exception e){
        e.printStackTrace();

    }
}
