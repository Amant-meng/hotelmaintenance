package com.chinahotelhelp.shm.operational.module.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Huan.Xia
 * @Title: SysMessage
 * @ProjectName merchant-management
 * @Description: TODO
 * @date 2018-12-2811:10
 */
@TableName("sys_message")
public class SysMessage  implements Serializable {
    private static final long serialVersionUID = -6451812593150428369L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String hi_id;
    private String type;
    private String title;
    private String content;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date pushDate;
    private String tag;
    private String url;
    private String state;
    private String params;
    private String typeName;
    private String order;

    /**
     * 消息id
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHi_id() {
        return hi_id;
    }

    public void setHi_id(String hi_id) {
        this.hi_id = hi_id;
    }

    /**
     * 消息类型
     */
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * 标题
     */
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 内容
     */
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 推送时间
     */
    public Date getPushDate() {
        return pushDate;
    }

    public void setPushDate(Date pushDate) {
        this.pushDate = pushDate;
    }

    /**
     * 消息标识
     */
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * 消息地址
     */
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 消息状态
     */
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    /**
     * 消息类型名称
     *
     */
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /**
     * 消息附属信息
     */
    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
