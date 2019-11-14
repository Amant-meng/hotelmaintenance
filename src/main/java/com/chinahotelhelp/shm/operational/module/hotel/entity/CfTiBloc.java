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
 *   @title: CfTiBloc
 *   @Description: 集团实体类
 *   @date 2019/10/23
*/
@Data
@TableName("cf_ti_bloc")
public class CfTiBloc implements Serializable {
    private static final long serialVersionUID = 8841433872811285796L;
    /**
     * 集团id
     */
    @Id
    private String bloc_id;
    /**
     * 酒店集团法人
     */
    private String bloc_legalperson;
    /**
     * 酒店集团名称
     */
    private String bloc_name;
    /**
     * 国家区号代码
     */
    private String bloc_country_area_code;
    /**
     * 集团类型（海外、国内）代码
     */
    private String bloc_type_code;
    /**
     * 集团总部地址
     */
    private String bloc_address;
    /**
     * 集团总部县级行政区划代码
     */
    private String bloc_districts_code;
    private String c_user_id;
    private String c_user_name;
    private String m_user_id;
    private String m_user_name;
    private Date c_time;
    private Date m_time;
    private int del_flag;

}
