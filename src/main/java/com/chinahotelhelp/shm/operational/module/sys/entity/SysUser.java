package com.chinahotelhelp.shm.operational.module.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Huan.Xia
 * @Title: SysUser
 * @ProjectName merchant-management
 * @Description: 系统用户实体
 * @date 2018/10/30/0309:22
 */
@TableName("sys_user")
public class SysUser implements Serializable {
    private static final long serialVersionUID = 8841433872811285796L;
    @Id
    private String id;
    private String username;
    private String password;
    private String truename;
    private String phone;
    private String gender;
    private String enabled;
    private Date createtime;
    private Date last_login_time;
    private String createuser;
    private Date modifytime;
    private String modifyuser;
    private String deltag;
    private String issystem;
    @TableField(value = "issystem_name")
    private String issystem_name;
    @TableField(value = "gender_name")
    private String gender_name;
    @TableField(value = "enabled_name")
    private String enabled_name;
    @TableField(value = "createuser_name")
    private String createusername;
    @TableField(value = "modifyuser_name")
    private String modifyusername;
    private String hiId;
    @TableField(value = "hiId_name")
    private String hiId_name;
    private String blocId;
    @TableField(value = "blocId_name")
    private String blocId_name;
    @TableField(exist = false)
    private List<SysMenu> menus;
    @TableField(exist = false)
    private List<String> premission;
    @TableField(exist = false)
    private String imgcode;
    //private String pms_id;
    private String role;

    public Date getLast_login_time() {
        return last_login_time;
    }

    public void setLast_login_time(Date last_login_time) {
        this.last_login_time = last_login_time;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
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

    public List<SysMenu> getMenus() {
        return menus;
    }

    public void setMenus(List<SysMenu> menus) {
        this.menus = menus;
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

    public List<String> getPremission() {
        return premission;
    }

    public void setPremission(List<String> premission) {
        this.premission = premission;
    }


    public String getGender_name() {
        return gender_name;
    }

    public void setGender_name(String gender_name) {
        this.gender_name = gender_name;
    }

    public String getEnabled_name() {
        return enabled_name;
    }

    public void setEnabled_name(String enabled_name) {
        this.enabled_name = enabled_name;
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

    public String getImgcode() {
        return imgcode;
    }

    public void setImgcode(String imgcode) {
        this.imgcode = imgcode;
    }

    public String getHiId() {
        return hiId;
    }

    public void setHiId(String hiId) {
        this.hiId = hiId;
    }

    public String getHiId_name() {
        return hiId_name;
    }

    public void setHiId_name(String hiId_name) {
        this.hiId_name = hiId_name;
    }

    public String getBlocId() {
        return blocId;
    }

    public void setBlocId(String blocId) {
        this.blocId = blocId;
    }

    public String getBlocId_name() {
        return blocId_name;
    }

    public void setBlocId_name(String blocId_name) {
        this.blocId_name = blocId_name;
    }

 /*   public String getPms_id() {
        return pms_id;
    }

    public void setPms_id(String pms_id) {
        this.pms_id = pms_id;
    }*/
}
