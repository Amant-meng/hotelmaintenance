package com.chinahotelhelp.shm.operational.module.terminal.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Classname Softwareinfo
 * @Description 终端软件信息表
 * @Date 2019/10/30 17:26
 * @Created by Changliang.yang
 */
@TableName("cf_ti_softwareinfo")
@Data
public class Softwareinfo implements Serializable {
    private static final long serialVersionUID = 8841433872811285796L;
    private String ti_sw_id;
    private String ti_sw_name;
    private String ti_sw_path;
    private String ti_sw_version;
    private String ti_sw_introduction;
    private String ti_sw_ar;
    private String ti_sw_comment;
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
