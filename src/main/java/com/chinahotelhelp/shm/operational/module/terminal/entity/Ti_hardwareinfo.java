package com.chinahotelhelp.shm.operational.module.terminal.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Classname Ti_hardwareinfo
 * @Description 硬件信息表
 * @Date 2019/10/31 9:29
 * @Created by Changliang.yang
 */
@TableName("cf_ti_hardwareinfo")
@Data
public class Ti_hardwareinfo implements Serializable {
    private static final long serialVersionUID = 8841433872811285796L;
    private String hw_id;
    private String m_id;
    private String hw_type;
    private String  hw_name;
    private String  hw_manufacturer;
    private String  hw_tel;
    private String hw_model;
    private String  hw_comment;
    private String  hw_ar;
    private String  c_user_id;
    private String c_user_name;
    private String  m_user_id;
    private String m_user_name;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date c_time;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date m_time;


}
