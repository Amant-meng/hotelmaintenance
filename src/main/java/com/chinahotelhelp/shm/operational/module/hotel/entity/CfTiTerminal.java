package com.chinahotelhelp.shm.operational.module.hotel.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 *   @author Meng.yang
 *   @ProjectName
 *   @title: CfTiTerminal
 *   @Description: 终端实体类
 *   @date 2019/10/23
*/
@Data
@TableName("cf_ti_terminal")
public class CfTiTerminal implements Serializable {

    private static final long serialVersionUID = 8841433872811285796L;

    /**
     * 终端ID
     */
    @Id
    private String ti_id;
    /**
     * 酒店ID
     */
    private String hi_id;
    /**
     * 设备型号ID
     */
    private String m_id;
    /**
     * 终端软件ID
     */
    private String ti_sw_id;
    /**
     * 终端编号
     */
    private String ti_code;
    /**
     * 批次编号
     */
    private String bh_id;
    /**
     * 终端类型
     */
    private String ti_type;
    /**
     * 终端上架时间
     */
    private Date ti_up_time;
    /**
     * 终端下架时间
     */
    private Date ti_down_time;
    /**
     * 维护人员ID
     */
    private String ti_user_id;
    /**
     * 维护人员名称
     */
    private String ti_user_name;
    /**
     * 设备型号EquipmentMode
     */
    private String em_mode;
    /**
     * 设备状态EquipmentStatus
     */
    private String em_status;
    private String c_user_id;
    private String c_user_name;
    private String m_user_id;
    private String m_user_name;
    private Date c_time;
    private Date m_time;
    private int del_flag;

    /**
     * 模板ID
     */
    @TableField(exist = false)
    private String temp_id;

}
