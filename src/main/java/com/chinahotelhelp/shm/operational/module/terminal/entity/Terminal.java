package com.chinahotelhelp.shm.operational.module.terminal.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @Classname Terminal
 * @Description 终端信息表
 * @Date 2019/10/30 17:18
 * @Created by Changliang.yang
 */
@Getter
@Setter
@TableName("cf_ti_terminal")
public class Terminal implements Serializable {
    private static final long serialVersionUID = 8841433872811285796L;
    private String ti_id;
    private String hi_id;
    private String ti_sw_id;
    private String ti_code;
    private String bh_id;
    private String ti_type;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date ti_up_time;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date ti_down_time;
    private String ti_user_id;
    private String ti_user_name;
    private String em_mode;
    private String em_status;
    private String c_user_id;
    private String c_user_name;
    private String m_user_id;
    private String m_user_name;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date c_time;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date m_time;
    private Integer del_flag;

}
