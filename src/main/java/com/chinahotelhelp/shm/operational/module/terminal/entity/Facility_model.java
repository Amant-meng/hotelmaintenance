package com.chinahotelhelp.shm.operational.module.terminal.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Classname Facility_model
 * @Description 设备型号信息表
 * @Date 2019/10/31 9:21
 * @Created by Changliang.yang
 */
@TableName("cf_ti_facility_model")
@Data
public class Facility_model implements Serializable {
    private static final long serialVersionUID = 8841433872811285796L;
    private String m_id;
    private String mi_id;
    private String bh_id;
    private String p_name;
    private String m_name;
    private String m_code;
    private Integer m_count;
    private String m_comment;
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
