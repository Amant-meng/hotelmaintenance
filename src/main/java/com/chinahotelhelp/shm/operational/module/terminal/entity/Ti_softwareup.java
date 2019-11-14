package com.chinahotelhelp.shm.operational.module.terminal.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Classname Ti_softwareup
 * @Description 终端软件升级信息表
 * @Date 2019/10/31 9:27
 * @Created by Changliang.yang
 */
@TableName("cf_ti_softwareup")
@Data
public class Ti_softwareup implements Serializable {
    private static final long serialVersionUID = 8841433872811285796L;
    private String pk_id;
    private String ti_id;
    private String ti_sw_version_now;
    private String  ti_sw_version_up;
    private String ti_sw_status;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date  ti_sw_open_date;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date  ti_sw_finish_date;
    private String  ti_sw_comment;
    private String c_user_id;
    private String c_user_name;
    private String m_user_id;
    private String m_user_name;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date c_time;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date  m_time;
    private Integer del_flag;


}
