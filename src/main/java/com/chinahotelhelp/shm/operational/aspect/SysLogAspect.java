
package com.chinahotelhelp.shm.operational.aspect;

import com.alibaba.fastjson.JSON;
import com.chinahotelhelp.shm.operational.module.sys.entity.SysLog;
import com.chinahotelhelp.shm.operational.module.sys.entity.SysUser;
import com.chinahotelhelp.shm.operational.module.sys.service.SysLogService;
import com.chinahotelhelp.shm.operational.tools.HttpContextUtils;
import com.chinahotelhelp.shm.operational.tools.IPUtils;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author YangMeng
 * @Title: SysLogAspect
 * @ProjectName merchant-management
 * @Description: (系统日志，切面处理类)
 * @date 2018/12/17
 */

@Aspect
@Component
public class SysLogAspect {

    @Autowired
    private SysLogService sysLogService;

    @Pointcut("@annotation( com.chinahotelhelp.shm.operational.aspect.AnnotationLog)")
    public void logPointCut() {

    }


    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        long takeTime = time/1000;
        //保存日志
        saveSysLog(point, takeTime);

        return result;
    }

    public void saveSysLog(ProceedingJoinPoint joinPoint,long time){

        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        //获取切入点所在的方法
        Method method = signature.getMethod();

       SysLog sysLog = new SysLog();
        //获取操作
        AnnotationLog log = method.getAnnotation(AnnotationLog.class);
        if(log != null){
            //注解上的描述
            String value = log.value();
            sysLog.setOperation(value);//保存获取的操作
        }

        //获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        //请求的方法名
        String methodName = method.getName();
        sysLog.setMethod(className + "." + methodName + "()");

        //请求的参数
        Object[] args = joinPoint.getArgs();
        //将参数所在的数组转换成json
        String params = JSON.toJSONString(args);
        if (params.length() > 200){
            StringBuilder builder = new StringBuilder();
            StringBuilder append = builder.append(params);
            String substring = append.substring(0, 200);
            //设置参数
            sysLog.setParams(substring);
        }else {

            sysLog.setParams(params);
        }


        //获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        //设置IP地址
        sysLog.setIp(IPUtils.getIpAddr(request));

        //用户名
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        if (sysUser!=null){
            String username = sysUser.getUsername();
            String userId =sysUser.getId();
            sysLog.setUname(username);
            sysLog.setUid(userId);

            sysLog.setTaketime(Integer.parseInt(String.valueOf(time)));
            //保存系统日志
            sysLogService.insert(sysLog);
        }

    }

}

