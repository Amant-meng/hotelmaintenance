package com.chinahotelhelp.shm.operational.module.hotel.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.chinahotelhelp.shm.operational.common.filed.CombFiled;
import com.chinahotelhelp.shm.operational.common.filed.DateRangeFiled;
import com.chinahotelhelp.shm.operational.common.filed.Filed;
import com.chinahotelhelp.shm.operational.common.filed.TextFiled;
import com.chinahotelhelp.shm.operational.module.hotel.entity.CfTiHotel;
import com.chinahotelhelp.shm.operational.module.hotel.mapper.CfTiBlocMapper;
import com.chinahotelhelp.shm.operational.module.hotel.mapper.CfTiHotelMapper;
import com.chinahotelhelp.shm.operational.module.sys.entity.Message;
import com.chinahotelhelp.shm.operational.module.sys.entity.Page;
import com.chinahotelhelp.shm.operational.module.sys.entity.PageData;
import com.chinahotelhelp.shm.operational.tools.IdUtil;
import com.chinahotelhelp.shm.operational.tools.QueryUntil;
import com.chinahotelhelp.shm.operational.tools.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *   @author Meng.yang
 *   @ProjectName
 *   @title: CfTiHotelService
 *   @Description:
 *   @date 2019/10/23
*/
@Slf4j
@Service
public class CfTiHotelService {

    private final static String HOTEL_SEQUENCE_NAME = "HOTEL_SEQUENCE";

    @Autowired
    private CfTiHotelMapper cfTiHotelMapper;

    @Autowired
    private CfTiBlocMapper cfTiBlocMapper;

    /**
     * 获取酒店列表
     * @param page
     * @return
     */
    public PageData getHotelList(Page page) {
        List<Filed> queryFileds = new ArrayList<Filed>();
        Map<String, Object> params = page.getParams();
        if (params != null && params.size() > 0) {
            queryFileds.add(new CombFiled("hi_id", params));
            queryFileds.add(new CombFiled("bloc_id", params));
            queryFileds.add(new CombFiled("hi_name", params));
            queryFileds.add(new CombFiled("hi_type", params));
            queryFileds.add(new CombFiled("hi_ifdemohotel", params));
            queryFileds.add(new TextFiled("hi_address", params));
            queryFileds.add(new DateRangeFiled("c_time", params));
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(SELECT\n" +
                "\tt.hi_id,\n" +
                "\tt.bloc_id,\n" +
                "\tb.bloc_name,\n" +
                "\tt.hi_name,\n" +
                "\tt.hi_legalperson,\n" +
                "\tt.hi_tel,\n" +
                "\tt.hi_type,\n" +
                "\tt.hi_address,\n" +
                "\tt.hi_districts_code,\n" +
                "\tt.hi_longitude,\n" +
                "\tt.hi_latitude,\n" +
                "\tt.hi_ifdemohotel,\n" +
                "\tt.is_open_rule,\n" +
                "\tt.c_user_id,\n" +
                "\tt.c_user_name,\n" +
                "\tt.m_user_id,\n" +
                "\tt.m_user_name,\n" +
                "\tt.c_time,\n" +
                "\tt.m_time,\n" +
                "\tt.hi_code\n" +
                "FROM\n" +
                "\tcf_ti_hotel t,\n" +
                "\tcf_ti_bloc b\n" +
                "WHERE\n" +
                "\tt.bloc_id = b.bloc_id\n" +
                "AND t.del_flag = 0\n" +
                "ORDER BY\n" +
                "\tt.c_time DESC) t");
        PageData pageData = new PageData();
        String[] cmdSqlArray = QueryUntil.getQuerySql("*", queryFileds, "", stringBuilder.toString());
        page.setExec_sql(cmdSqlArray[0]);
        pageData.setData(cfTiHotelMapper.execSQL(page));
        page.setExec_sql(cmdSqlArray[1]);
        pageData.setRecordsTotal(Integer.parseInt(cfTiHotelMapper.execSQL(page).get(0).get("count").toString()));
        pageData.setRecordsFiltered(pageData.getRecordsTotal());
        return pageData;
    }

    /**
     * 删除酒店信息
     * @param hi_id
     */
    public Message delHotel(String hi_id) {
        Message message = new Message();
        try {

            Wrapper<CfTiHotel> cfTiHotelWrapper = new EntityWrapper<>();
            cfTiHotelWrapper.eq("hi_id", hi_id);
            CfTiHotel cfTiHotel = new CfTiHotel();
            cfTiHotel.setM_time(new Date());
            cfTiHotel.setM_user_id(ShiroUtils.getUserEntity().getId());
            cfTiHotel.setM_user_name(ShiroUtils.getUserEntity().getTruename());
            cfTiHotel.setDel_flag(1);
            cfTiHotelMapper.update(cfTiHotel, cfTiHotelWrapper);
            message.setSuccess(true);
            message.setMessage("删除酒店信息成功");
        }catch (Exception e){
            log.info("删除酒店信息异常");
            message.setSuccess(false);
            message.setMessage("删除酒店信息异常");
        }

        return message;
    }

    /**
     * 更新酒店信息
     * @param cfTiHotel
     * @return
     */
    public Message updateHotel(CfTiHotel cfTiHotel){
        Message message = Message.N();
        try {
            Wrapper<CfTiHotel> cfTiHotelWrapper = new EntityWrapper<>();
            cfTiHotelWrapper.eq("hi_id", cfTiHotel.getHi_id());
            cfTiHotel.setM_time(new Date());
            cfTiHotel.setM_user_id(ShiroUtils.getUserEntity().getId());
            cfTiHotel.setM_user_name(ShiroUtils.getUserEntity().getTruename());
            cfTiHotelMapper.update(cfTiHotel,cfTiHotelWrapper);
            message.setSuccess(true);
            message.setMessage("修改酒店信息成功");
        }catch (Exception e){
            log.info("修改酒店信息异常");
            message.setSuccess(false);
            message.setMessage("修改酒店信息异常");
        }
        return message;
    }

    /**
     * 通过酒店id查询酒店信息
     * @param hi_id
     * @return
     */
    public CfTiHotel getHotelInfoById(String hi_id){
        CfTiHotel cfTiHotel = null;
        try {
            Wrapper<CfTiHotel> cfTiHotelWrapper = new EntityWrapper<>();
            cfTiHotelWrapper.eq("hi_id",hi_id);
            cfTiHotelWrapper.and("del_flag=0");
            List<CfTiHotel> cfTiHotelList = cfTiHotelMapper.selectList(cfTiHotelWrapper);
            if(cfTiHotelList!=null && cfTiHotelList.size()>0){
                cfTiHotel = cfTiHotelList.get(0);
            }

        }catch (Exception e){
            log.error("获取酒店信息异常："+e.getMessage());
        }
        return  cfTiHotel;
    }

    /**
     * 添加酒店信息
     * @param cfTiHotel
     * @return
     */
    public Message addHotel(CfTiHotel cfTiHotel){
        Message message = Message.N();
        try {

            //县级行政区划
            String hiDistrictsCode = cfTiHotel.getHi_districts_code();
            // 自增序列
            Long sequence = cfTiBlocMapper.getValue(HOTEL_SEQUENCE_NAME);

            String hotelId = IdUtil.genHotelId(hiDistrictsCode, sequence);

            cfTiHotel.setHi_id(hotelId);
            cfTiHotel.setC_user_id(ShiroUtils.getUserEntity().getId());
            cfTiHotel.setC_user_name(ShiroUtils.getUserEntity().getTruename());
            cfTiHotel.setC_time(new Date());
            cfTiHotelMapper.insert(cfTiHotel);

            message.setSuccess(true);
            message.setMessage("保存酒店信息成功");
        }catch (Exception e){

            log.info("保存酒店信息异常");
            message.setSuccess(false);
            message.setMessage("保存酒店信息异常");
        }

        return message;

    }

    /**
     * 通过集团id获取酒店列表
     * @param bloc_id
     * @return
     */
    public List<CfTiHotel> getHotelListByBolcId(String bloc_id){
        List<CfTiHotel> cfTiHotelList = null;
        try {
            cfTiHotelList= cfTiHotelMapper.getHotelListByBolcId(bloc_id);
        }catch (Exception e){
            log.info("获取酒店信息列表异常："+e.getMessage());
        }
        return  cfTiHotelList;

    }

}
