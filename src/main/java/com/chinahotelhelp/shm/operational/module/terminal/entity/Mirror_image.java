package com.chinahotelhelp.shm.operational.module.terminal.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Classname Mirror_image
 * @Description 镜像表
 * @Date 2019/10/30 17:44
 * @Created by Changliang.yang
 */
@TableName("cf_ti_mirror_image")
@Data
public class Mirror_image implements Serializable {
    private static final long serialVersionUID = 8841433872811285796L;
    private String mi_id;
    private String mi_name;
    private String mi_size;
    private String mi_url;
    private String mi_type;
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
