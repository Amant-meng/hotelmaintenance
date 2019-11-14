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
 *   @title: CfTiHotel
 *   @Description: 酒店实体类
 *   @date 2019/10/23
*/
@Data
@TableName("cf_ti_hotel")
public class CfTiHotel implements Serializable {

    private static final long serialVersionUID = 8841433872811285796L;
    /**
     * 酒店ID
     */
    @Id
    private String hi_id;
    /**
     * 集团ID
     */
    private String bloc_id;
    /**
     * 酒店名称
     */
    private String hi_name;
    /**
     * 酒店工商法人名称
     */
    private String hi_legalperson;
    /**
     * 服务电话
     */
    private String hi_tel;
    /**
     * 酒店类型
     */
    private String hi_type;
    /**
     * 酒店地址
     */
    private String hi_address;
    /**
     * 县级行政区划
     */
    private String hi_districts_code;
    /**
     * 酒店地址经度
     */
    private Double hi_longitude;
    /**
     * 酒店地址纬度
     */
    private Double hi_latitude;
    /**
     * 是否示范酒店
     */
    private String hi_ifdemohotel;
    /**
     * 是否自动配置门锁
     */
    private String is_open_rule;
    private String c_user_id;
    private String c_user_name;
    private String m_user_id;
    private String m_user_name;
    private Date c_time;
    private Date m_time;
    private int del_flag;
    private String hi_code;

}
