package com.chinahotelhelp.shm.operational.module.log.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by wuyang on 2019/11/5 10:46
 */
@Getter
@Setter
public class LogParams {

    /**
     * 终端ID
     */
    private String ti_id="";
    /**
     * 酒店名称
     */
    private String hi_name="";
    /**
     * 日志索引
     */
    private String indexName="";
    /**
     * 按月查询
     */
    private String month="";
    /**
     * 日志类型DOC
     */
    //private String logType="";
    /**
     * 日志时间起
     */
    private String logSTime="";
    /**
     * 日志时间止
     */
    private String logETime="";
    /**
     * 内容
     */
    private String content="";

    /**
     * 日志级别（ERROR/INFO）
     */
    private String logLevel="";
    /**
     * 分页查时用，当前页
     */
    private Integer draw;
    /**
     * 每页行数
     */
    private Integer length;
}
