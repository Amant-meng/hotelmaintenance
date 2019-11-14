package com.chinahotelhelp.shm.operational.module.sys.entity;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Auther: 杨昌亮
 * @Date: 2018/12/18 20:55
 * @Description:
 */
@Getter
@Configuration
@PropertySource(value = "classpath:config/config.properties")
public class ConfigPath {
    //1.1获取房型
    @Value("${config.getRoomType}")
    private String getRoomType;
    //1.2获取房间信息（房型、房间、房价）
    @Value("${config.getRoomInfo}")
    private String getRoomInfo;
    //1.3查询可用房
    @Value("${config.getRoom}")
    private String getRoom;
    //1.4查询未来房态
    @Value("${config.futureRoomStatus}")
    private String futureRoomStatus;
    //1.5查询房间状态
    @Value("${config.getRoomState}")
    private String getRoomState;
    //2.1查询是否入住
    @Value("${config.getCheckInStatusByCardNo}")
    private String getCheckInStatusByCardNo;
    //2.2获取入住信息（入住订单、会员、房价体系）
    @Value("${config.getCheckInInfo}")
    private String getCheckInInfo;
    //2.3排房（添加订单、排房；直接锁房）
    @Value("${config.lockRoom}")
    private String lockRoom;
    //2.4取消排房（取消排房、取消订单；解锁房）
    @Value("${config.unlockRoom}")
    private String unlockRoom;
    //2.5入住（入住、添加同住人、添加账务）
    @Value("${config.checkIn}")
    private String checkIn;
    //2.6查询预订单（姓名、身份证号、手机号、预订单号）
    @Value("${config.getPreOrderInfo}")
    private String getPreOrderInfo;
    //2.7预订单转入住（预订单转入住，添加同住人、添加账务）
    @Value("${config.preCheckIn}")
    private String preCheckIn;
    //2.8添加同住人
    @Value("${config.recordOcipants}")
    private String recordOcipants;
    //2.9续住
    @Value("${config.keepRoom}")
    private String keepRoom;
    //2.10退房
    @Value("${config.getCheckOut}")
    private String getCheckOut;
    //3.1添加账务
    @Value("${config.addBill}")
    private String addBill;
    //3.2添加账务
    @Value("${config.getRoomBill}")
    private String getRoomBill;
    //4.1查询会员信息
    @Value("${config.checkIfVip}")
    private String checkIfVip;
    //4.1退款重试
    @Value("${config.refund}")
    private String refund;
    //4.2团住订单查询
    @Value("${config.queryTeamOrderInfo}")
    private String queryTeamOrderInfo;
    //4.3团住订单查询
    @Value("${config.qrcode}")
    private String qrcode;

    //2019-07-10 zhangjinyi  Create
    @Value("${config.teamPath}")
    private String teamPath;

    @Value("${config.wxOpenUrl}")
    public  String wxOpenUrl;

    @Value("${config.roomType}")
    public String roomType;

    @Value("${config.maintainAPI}")
    public String maintainAPI;

    @Value("${config.hotelLogo}")
    public String hotelLogo;

/*    @Value("${config.pmsID}")
    public String pmsID;*/
}
