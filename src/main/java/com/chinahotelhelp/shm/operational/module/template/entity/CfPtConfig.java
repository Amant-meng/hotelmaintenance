package com.chinahotelhelp.shm.operational.module.template.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wuyang on 2019/10/23 9:18
 * 参数配置实体 cf_pt_config
 */
@Data
@TableName("cf_pt_config")
public class CfPtConfig implements Serializable {

    private static final long serialVersionUID = 8841433872811285796L;

    /**
     * 主键
     */
    @Id
    private String pk_id;
    /**
     * 终端ID
     */
    private String ti_id;
    /**
     * 模板ID
     */
    private String temp_id;
    /**
     * 参数ID
     */
    private int p_id;
    /**
     * 参数值
     */
    private String p_value;
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
     * 删除标识
     */
    private int del_flag;


}
