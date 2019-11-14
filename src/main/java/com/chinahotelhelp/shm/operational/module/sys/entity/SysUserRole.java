package com.chinahotelhelp.shm.operational.module.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: 杨昌亮
 * @Date: 2018/11/22 17:38
 * @Description:
 */
@TableName("sys_user_role")
public class SysUserRole implements Serializable {
    private static final long serialVersionUID = 8841433872811285796L;
    @TableId(value = "pk_id", type = IdType.AUTO)
    private Integer pkId;
    private Integer rid;
    private String uid;
    private Date createtime;
    private String createuser;

    public Integer getPkId() {
        return pkId;
    }

    public void setPkId(Integer pkId) {
        this.pkId = pkId;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }
}