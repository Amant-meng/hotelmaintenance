package com.chinahotelhelp.shm.operational.module.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Huan.Xia
 * @Title: SysMenu
 * @ProjectName merchant-management
 * @Description: TODO
 * @date 2018/10/30/0309:53
 */
@TableName("sys_menu")
public class SysMenu implements Serializable {
    private static final long serialVersionUID = 8841433872811285796L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer pid;
    private String name;
    private String premission;
    private String remark;
    private Date createtime;
    private String createuser;
    private Date modifytime;
    private String orderid;
    private String modifyuser;
    private String deltag;
    @TableField(exist = false)
    private List<SysMenu> subs;
    private String isfunc;
    private String icon;
    private String url;
    private String pidname;
    private String component;
    @TableField(exist = false)
    private Integer selected;

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPremission() {
        return premission;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public void setPremission(String premission) {
        this.premission = premission;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    public String getModifyuser() {
        return modifyuser;
    }

    public void setModifyuser(String modifyuser) {
        this.modifyuser = modifyuser;
    }

    public String getDeltag() {
        return deltag;
    }

    public void setDeltag(String deltag) {
        this.deltag = deltag;
    }

    public List<SysMenu> getSubs() {
        return subs;
    }

    public void setSubs(List<SysMenu> subs) {
        this.subs = subs;
    }

    public String getIsfunc() {
        return isfunc;
    }

    public void setIsfunc(String isfunc) {
        this.isfunc = isfunc;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSelected() {
        return selected;
    }

    public void setSelected(Integer selected) {
        this.selected = selected;
    }

    public String getPidname() {
        return pidname;
    }

    public void setPidname(String pidname) {
        this.pidname = pidname;
    }
}
