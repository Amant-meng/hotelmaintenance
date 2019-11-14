package com.chinahotelhelp.shm.operational.module.terminal.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Classname Batch
 * @Description 批次信息表
 * @Date 2019/10/30 17:29
 * @Created by Changliang.yang
 */
@Data
@TableName("cf_ti_batch")
public class Batch implements Serializable {
    private static final long serialVersionUID = 8841433872811285796L;
    private String bh_id;
    private String bh_name;
    private String  bh_number;
    private String bh_count;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date bh_time;
    private String com_name;
    private String com_ci_name;
    private String com_time;
    private String c_user_id;
    private String c_user_name;
    private String m_user_id;
    private String  m_user_name;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date c_time;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date m_time;
    private Integer del_flag;

}
