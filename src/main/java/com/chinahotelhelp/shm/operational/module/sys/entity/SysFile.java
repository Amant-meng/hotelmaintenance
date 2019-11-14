package com.chinahotelhelp.shm.operational.module.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author Huan.Xia
 * @Title: SysFile
 * @ProjectName merchant-management
 * @Description: 文件实体
 * @date 2019-01-1217:06
 */
@Getter
@Setter
/**
 * 文件实体
 */
@TableName("sys_file")
public class SysFile {
    private String inputType;
    private String taskId;
    private String hotelId;
    private String id;
    private String name;
    private String type;
    private String path;
    private String suffix;
    private Date createtime;
    private String createuser;
    @TableField(value = "createuser_name")
    private String createusername;
    private Date modifytime;
    private String modifyuser;
    @TableField(value = "modifyuser_name")
    private String modifyusername;
    private String deltag;
}
