package com.chinahotelhelp.shm.operational.module.terminal.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Classname Maintain
 * @Description 终端维修记录表
 * @Date 2019/10/30 17:23
 * @Created by Changliang.yang
 */
@Data
@TableName("cf_ti_maintain")
public class Maintain implements Serializable {
    private static final long serialVersionUID = 8841433872811285796L;
    @TableId(value = "pk_id",type = IdType.AUTO)
    private Integer pk_id;
    private String ti_id;
    private String hi_id;
    private String maintain_name;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date maintain_time;
    private String maintain_comment;
    private String c_user_id;
    private String c_user_name;
    private String m_user_id;
    private String m_user_name;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date c_time;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date m_time;



}
