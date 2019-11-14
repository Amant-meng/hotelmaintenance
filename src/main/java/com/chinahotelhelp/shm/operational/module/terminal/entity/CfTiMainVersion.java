package com.chinahotelhelp.shm.operational.module.terminal.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 *   @author Meng.yang
 *   @ProjectName
 *   @title: CfTiMainVersion
 *   @Description: 版本管理信息表
 *   @date 2019/11/05
*/
@Data
@TableName("cf_ti_main_version")
public class CfTiMainVersion implements Serializable {
    private static final long serialVersionUID = 8841433872811285796L;

    /**
     * 主键
     */
    @Id
    private Integer pk_id;
    /**
     * 版本号
     */
    private String version_no;
    /**
     * 备注
     */
    private String version_comments;
    /**
     * 创建时间
     */
    private Date c_time;
    /**
     * 创建时间
     */
    private Date m_time;
    /**
     * 删除标识
     */
    private Integer del_flag;

}
