package com.chinahotelhelp.shm.operational.module.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Huan.Xia
 * @Title: SysDict
 * @ProjectName merchant-management
 * @Description: TODO
 * @date 2018/11/14/01417:40
 */
@TableName("core_dict")
@Getter
@Setter
public class SysDict implements Serializable {
    private static final long serialVersionUID = 8841433872811285796L;
    @TableId(value = "ID",type = IdType.AUTO)
    private Integer ID;
    private String VALUE;
    private String NAME;
    private String TYPE;
    @TableField(value = "TYPE_NAME")
    private String type_name;
    private Integer SORT;
    private String PARENT;
    @TableField(value = "DEL_FLAG")
    private Integer del_flag;
    private String REMARK;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    @TableField(value = "CREATE_TIME")
    private Date create_time;


}
