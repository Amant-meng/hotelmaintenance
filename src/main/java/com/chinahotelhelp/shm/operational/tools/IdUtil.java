package com.chinahotelhelp.shm.operational.tools;

/**
 *   @author Meng.yang
 *   @ProjectName
 *   @title: IdUtil
 *   @Description: 集团，酒店，终端ID生成规则工具类
 *   @date 2019/10/25
*/
public class IdUtil {

    /**
     *
     * @param countryPhoneCode 国家电话区号
     * @param districtsCode 国内行政区划编码
     * @param blocType 集团类型
     * @param sequence 自增序列
     * @return
     */
    public static String genBlocid(String countryPhoneCode,String  districtsCode,String blocType,Long sequence) {
        StringBuilder blocId = new StringBuilder();
        blocId.append(countryPhoneCode);
        blocId.append(districtsCode);
        blocId.append(blocType);
        blocId.append(String.format("%05d", sequence));
        return blocId.toString();
    }

    /**
     *
     * @param districtsCode 行政区划
     * @param sequence 自增序列 3位交付序号
     * @return
     */
    public static String genHotelId(String districtsCode,Long sequence) {
        StringBuilder hotelId = new StringBuilder();
        hotelId.append(districtsCode);
        hotelId.append(String.format("%03d", sequence));
        return hotelId.toString();

    }

    /**
     * 终端id生成
     * @param hotelId 酒店ID
     * @param sequence 自增序列  2位终端序号
     * @return
     */
    public static String genTerminalId(String hotelId,Long sequence) {
        StringBuilder terminalId = new StringBuilder();
        terminalId.append(hotelId);
        terminalId.append(String.format("%02d", sequence));
        return terminalId.toString();
    }
}
