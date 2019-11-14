package com.chinahotelhelp.shm.operational.module.hotel.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 *   @author Meng.yang
 *   @ProjectName
 *   @title: CfTiLockConfiguration
 *   @Description: 有规则门锁配置
 *   @date 2019/10/24
*/
@Data
@TableName("cf_ti_lock_configuration")
public class CfTiLockConfiguration implements Serializable {

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
     * 锁号连接符
     */
    private String joiner;
    /**
     * 锁号长度
     */
    private String max_num;
    /**
     * 楼层号规则
     */
    private String fo_rule;
    /**
     * 楼层号是否首尾补充字符
     */
    private String is_fobechar_add;
    /**
     * 楼层号需要补充的首字符
     */
    private String fo_add_begchar;
    /**
     * 楼层号需要补充的尾字符
     */
    private String fo_add_endchar;
    /**
     * 楼层号是否需要截取房间号
     */
    private String is_fobechar_red;
    /**
     * 楼层号截取房间号的开始下标
     */
    private String fo_red_begchar;
    /**
     * 楼层号截取房间号的结尾下标
     */
    private String fo_red_endchar;
    /**
     * 房间号规则
     */
    private String ro_rule;
    /**
     * 房间号是否首尾补充字符
     */
    private String is_robechar;
    /**
     * 房间号需要补充的首字符
     */
    private String ro_add_begchar;
    /**
     * 房间号需要补充的尾字符
     */
    private String ro_add_endchar;
    /**
     * 楼栋号规则
     */
    private String bu_rule;
    /**
     * 是否首尾补充字符
     */
    private String is_bubechar;
    /**
     * 需要补充的首字符
     */
    private String bu_add_begchar;
    /**
     * 需要补充的尾字符
     */
    private String bu_add_endchar;
    /**
     * 楼栋号是否需要截取房间号
     */
    private String is_bubechar_red;
    /**
     * 楼栋号截取房间号的开始下标
     */
    private Integer bu_red_begchar;
    /**
     * 楼栋号截取房间号的结尾下标
     */
    private Integer bu_red_endchar;
    /**
     * 锁号替换
     */
    private String lock_replace;
    /**
     * 是否开启锁号替换
     */
    private String is_lock_replace;
    /**
     * 替换的键值对
     */
    private String re_keyvalue;
    private String c_user_id;
    private String c_user_name;
    private String m_user_id;
    private String m_user_name;
    private Date c_time;
    private Date m_time;
    private Integer del_flag;


}
