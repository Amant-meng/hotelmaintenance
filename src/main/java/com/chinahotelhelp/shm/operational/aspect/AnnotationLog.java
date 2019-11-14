package com.chinahotelhelp.shm.operational.aspect;
import java.lang.annotation.*;

/**
 * @author YangMeng
 * @Title: AnnotationLog
 * @ProjectName merchant-management
 * @Description: 系统日志，自定义注解类
 * @date 2018/12/17
 */
@Target(ElementType.METHOD) //注解放置的目标位置,METHOD是可注解在方法级别上
@Retention(RetentionPolicy.RUNTIME) //注解在哪个阶段执行
@Documented //生成文档
public @interface AnnotationLog {
    String value() default "";
}