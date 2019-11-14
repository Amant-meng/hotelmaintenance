package com.chinahotelhelp.shm.operational.module.sys.entity;

import lombok.Data;

/**
 * @author Huan.Xia
 * @Title: MsgToLocal
 * @ProjectName merchant-management
 * @Description: TODO
 * @date 2019-08-1319:26
 */
@Data
public class MsgToLocal {
    //请求地址
    private String url;
    //请求数据
    private Object data;
    //请求标识
    private String tag;
    //请求返回数据
    private Object attachData;
    //集团编号
    private String groupId;
    //酒店编号
    private String hotelId;
    //酒店名称
    private String hotelName;
    //终端编号
    private String teminalId;
    //消息类型（message,task）
    private String type;
    //用户id
    private String userId;
    //用户名
    private String userName;

}
