package com.chinahotelhelp.shm.operational.module.sys.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author Huan.Xia
 * @Title: SysFileColsConfig
 * @ProjectName merchant-management
 * @Description: TODO
 * @date 2019-01-1510:06
 */
@Getter
@Setter
@TableName("sys_file_colsconfig")
public class SysFileColsConfig {
    private String id;
    private String colname;
    private String colcomments;
    private Date ctime;
}
