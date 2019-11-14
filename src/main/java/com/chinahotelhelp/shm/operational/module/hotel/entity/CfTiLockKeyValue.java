package com.chinahotelhelp.shm.operational.module.hotel.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 *   @author Meng.yang
 *   @ProjectName
 *   @title: CfTiLockKeyValue
 *   @Description: 无规则门锁配置
 *   @date 2019/10/24
*/
@Data
@TableName("cf_ti_lock_keyvalue")
public class CfTiLockKeyValue implements Serializable {

    private static final long serialVersionUID = 8841433872811285796L;

    /**
     * 主键
     */
    @Id
    private Integer pk_id;
    /**
     * 酒店ID
     */
    private String hi_id;
    /**
     * 房间号
     */
    private String ro_id;
    /**
     * 锁号
     */
    private String lock_id;
    private String c_user_id;
    private String c_user_name;
    private String m_user_id;
    private String m_user_name;
    private Date c_time;
    private Date m_time;
    private int del_flag;

}
