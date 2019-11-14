package com.chinahotelhelp.shm.operational.module.template.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wuyang on 2019/10/23 9:36
 * 参数结构实体 cf_pt_structure
 */
@Data
@TableName("cf_pt_structure")
public class ParasStructure implements Serializable {


    private String p_id;
    /**
     * 参数类型（字典表）
     */
    private String p_type;
    /**
     * 参数类别（字典表）
     */
    private String p_category;
    /**
     * 参数名
     */
    private String p_name;
    /**
     * 参数名中文解释
     */
    private String p_name_cn;
    /**
     * 备注说明
     */
    private String p_comment;
    /**
     * 是否字典
     */
    private int is_dict;
    /**
     * 字典类型
     */
    private String dict_type;
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
