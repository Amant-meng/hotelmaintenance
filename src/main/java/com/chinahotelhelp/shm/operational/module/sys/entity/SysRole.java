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
 * @Title: SysRole
 * @ProjectName merchant-management
 * @Description: TODO
 * @date 2018/10/30/0309:52
 */
@TableName("sys_role")
public class SysRole implements Serializable {
    private static final long serialVersionUID = 8841433872811285796L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private String remark;
    private Date createtime;
    private String createuser;
    @TableField(value = "createuser_name")
    private String createusername;
    @TableField(value = "modifyuser_name")
    private String modifyusername;
    private String issystem;
    @TableField(value = "issystem_name")
    private String issystem_name;
    private Date modifytime;
    private String modifyuser;
    private String deltag;
    @TableField(exist = false)
    private Integer selected;
    @TableField(exist = false)
    private List<SysMenu> menuList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCreateusername() {
        return createusername;
    }

    public void setCreateusername(String createusername) {
        this.createusername = createusername;
    }

    public String getModifyusername() {
        return modifyusername;
    }

    public void setModifyusername(String modifyusername) {
        this.modifyusername = modifyusername;
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

    public List<SysMenu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<SysMenu> menuList) {
        this.menuList = menuList;
    }

    public Integer getSelected() {
        return selected;
    }

    public void setSelected(Integer selected) {
        this.selected = selected;
    }

    public String getIssystem() {
        return issystem;
    }

    public void setIssystem(String issystem) {
        this.issystem = issystem;
    }

    public String getIssystem_name() {
        return issystem_name;
    }

    public void setIssystem_name(String issystem_name) {
        this.issystem_name = issystem_name;
    }
}
