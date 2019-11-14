package com.chinahotelhelp.shm.operational.module.sys.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Huan.Xia
 * @Title: MessageQueue
 * @ProjectName orderservice
 * @Description: TODO
 * @date 2019-01-0219:34
 */
@Getter
@Setter
public class MessageQueue implements Serializable {
    private static final long serialVersionUID = 8841433872811285796L;
    /**
     * 消息编号
     */
    private String id;

    /**
     * 消息标题
     */
    private String title;
    /**
     * 消息内容
     */
    private String content;

    /**
     * 消息发送时间（时间戳）
     */
    private long date;

    /**
     * 消息标识
     */
    private String tag;

    /**
     * 消息类型（message,task）
     */
    private String type;


    /**
     * 消息类型名称
     */
    private String typeName;

    /**
     * 消息附属信息
     */
    private Object data;
    /**
     * 集团编号
     */
    private String groupId;
    /**
     * 酒店编号
     */
    private String hotelId;

    /**
     *酒店名称
     */
    private String hotelName;

    /**
     * 终端编号
     */
    private String terminalId;
    /**
     * 参数
     */
    private String params;

    /**
     * 地址
     */
    private String url;
    /**
     * 地址
     */
    private String deposit;

    /**
     * 用户id
     */
    private String userId;
    /**
     * 用户名
     */
    private String userName;

    /**
     * 成功标识
     */
    private Boolean success;
    /**
     * 消息
     */
    private String message;


}
