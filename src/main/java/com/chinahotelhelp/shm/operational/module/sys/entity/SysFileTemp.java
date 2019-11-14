package com.chinahotelhelp.shm.operational.module.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Huan.Xia
 * @Title: SysFileTemp
 * @ProjectName merchant-management
 * @Description: 上传文件临时数据表
 * @date 2019-01-1416:33
 */
@Getter
@Setter
@TableName("sys_file_temp")
public class SysFileTemp {
    @TableId(value = "id", type = IdType.AUTO)
    private String id;
    private String taskid;
    private String hotelId;
    private String c1;
    private String c2;
    private String c3;
    private String c4;
    private String c5;
    private String c6;
    private String c7;
    private String c8;
    private String c9;
    private String c10;
    private String c11;
    private String c12;
    private String c13;
    private String c14;
    private String c15;
    private String c16;
    private String c17;
    private String c18;
    private String c19;
    private String c20;
    private String c21;
}
