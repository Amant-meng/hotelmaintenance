package com.chinahotelhelp.shm.operational.module.log.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by wuyang on 2019/11/5 14:12
 */
@Getter
@Setter
public class LogData {


    private String id;
    private String indexName;
    private String ti_id;
    private String hi_name;
    private String sourceFile;
    private String content;
    private String logTime;
}
