package com.chinahotelhelp.shm.operational.module.template.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wuyang on 2019/10/22 18:03
 * 模板实体 cf_pt_template
 */
@Data
@TableName("cf_pt_template")
public class Template implements Serializable {

    /**
     * 模板ID
     */
    private String temp_id;
    /**
     * 参数类型
     */
    private String p_type;
    /**
     * 模板名称
     */
    private String temp_name;
    /**
     * 创建人ID
     */
    private String c_user_id;
    /**
     * 创建人
     */
    private String c_user_name;
    /**
     * 修改人ID
     */
    private String m_user_id;
    /**
     * 修改人
     */
    private String m_user_name;
    /**
     * 创建时间
     */
    private Date c_time;
    /**
     * 修改时间
     */
    private Date m_time;
    /**
     * 删除标示
     */
    private int del_flag;

    /**
     * 参数ID
     */
    @TableField(exist = false)
    private String parasIds;
}
