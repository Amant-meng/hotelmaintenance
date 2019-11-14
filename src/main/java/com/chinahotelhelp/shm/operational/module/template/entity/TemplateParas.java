package com.chinahotelhelp.shm.operational.module.template.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wuyang on 2019/10/23 9:12
 * 模板参数对应实体 cf_pt_template_paras
 */
@Data
@TableName("cf_pt_template_paras")
public class TemplateParas implements Serializable {

    /**
     * 参数ID
     */
    private String p_id;
    /**
     * 模板ID
     */
    private String temp_id;
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
}
