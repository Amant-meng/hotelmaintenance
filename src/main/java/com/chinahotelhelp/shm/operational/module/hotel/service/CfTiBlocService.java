package com.chinahotelhelp.shm.operational.module.hotel.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.chinahotelhelp.shm.operational.common.filed.CombFiled;
import com.chinahotelhelp.shm.operational.common.filed.DateRangeFiled;
import com.chinahotelhelp.shm.operational.common.filed.Filed;
import com.chinahotelhelp.shm.operational.common.filed.TextFiled;
import com.chinahotelhelp.shm.operational.module.hotel.entity.CfTiBloc;
import com.chinahotelhelp.shm.operational.module.hotel.mapper.CfTiBlocMapper;
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
 *   @title: CfTiBlocService
 *   @Description:
 *   @date 2019/10/23
*/
@Slf4j
@Service
public class CfTiBlocService {

    private final static String BLOC_SEQUENCE_NAME = "BLOC_SEQUENCE";

    @Autowired
    private CfTiBlocMapper cfTiBlocMapper;

    /**
     * 获取集团列表
     *
     * @param page
     * @return
     */
    public PageData getBlocList(Page page) {
        List<Filed> queryFileds = new ArrayList<Filed>();
        Map<String, Object> params = page.getParams();
        if (params != null && params.size() > 0) {
            queryFileds.add(new CombFiled("bloc_id", params));
            queryFileds.add(new CombFiled("bloc_legalperson", params));
            queryFileds.add(new TextFiled("bloc_name", params));
            queryFileds.add(new TextFiled("bloc_address", params));
            queryFileds.add(new DateRangeFiled("c_time", params));
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(SELECT\n" +
                "\tbloc_id,\n" +
                "\tbloc_legalperson,\n" +
                "\tbloc_name,\n" +
                "\tbloc_country_area_code,\n" +
                "\tbloc_type_code,\n" +
                "\tbloc_address,\n" +
                "\tbloc_districts_code,\n" +
                "\tc_user_id,\n" +
                "\tc_user_name,\n" +
                "\tc_time,\n" +
                "\tm_time,\n" +
                "\tm_user_id,\n" +
                "\tm_user_name,\n" +
                "\t(\n" +
                "\t\tSELECT\n" +
                "\t\t\tNAME\n" +
                "\t\tFROM\n" +
                "\t\t\tcore_dict\n" +
                "\t\tWHERE\n" +
                "\t\t\ttype = 'bloc_type'\n" +
                "\t\tAND\n" +
                "\t\tVALUE\n" +
                "\t\t\t= bloc_type_code\n" +
                "\t) AS bloc_type\n" +
                "FROM\n" +
                "\tcf_ti_bloc\n" +
                "WHERE\n" +
                "\tdel_flag = 0\n" +
                "ORDER BY\n" +
                "\tc_time DESC) t");
        PageData pageData = new PageData();
        String[] cmdSqlArray = QueryUntil.getQuerySql("*", queryFileds, "", stringBuilder.toString());
        page.setExec_sql(cmdSqlArray[0]);
        pageData.setData(cfTiBlocMapper.execSQL(page));
        page.setExec_sql(cmdSqlArray[1]);
        pageData.setRecordsTotal(Integer.parseInt(cfTiBlocMapper.execSQL(page).get(0).get("count").toString()));
        pageData.setRecordsFiltered(pageData.getRecordsTotal());
        return pageData;
    }

    /**
     * 删除集团信息
     * @param bloc_id
     */
    public Message delBloc(String bloc_id) {
        Message message = new Message();
        try {
            Wrapper<CfTiBloc> cfTiBlocWrapper = new EntityWrapper<>();
            cfTiBlocWrapper.eq("bloc_id", bloc_id);
            CfTiBloc cfTiBloc = new CfTiBloc();
            cfTiBloc.setM_time(new Date());
            cfTiBloc.setM_user_id(ShiroUtils.getUserEntity().getId());
            cfTiBloc.setM_user_name(ShiroUtils.getUserEntity().getTruename());
            cfTiBloc.setDel_flag(1);
            cfTiBlocMapper.update(cfTiBloc,cfTiBlocWrapper);
            message.setSuccess(true);
            message.setMessage("删除集团信息成功");
        }catch (Exception e){
            log.info("删除集团信息异常"+e.getMessage());
            message.setSuccess(false);
            message.setMessage("删除集团信息异常");
        }

        return message;
    }

    /**
     * 更新集团信息
     * @param cfTiBloc
     * @return
     */
    public Message updateBloc(CfTiBloc cfTiBloc){
        Message message = Message.N();
        try {
            Wrapper<CfTiBloc> cfTiBlocWrapper = new EntityWrapper<>();
            cfTiBlocWrapper.eq("bloc_id", cfTiBloc.getBloc_id());
            cfTiBloc.setM_time(new Date());
            cfTiBloc.setM_user_id(ShiroUtils.getUserEntity().getId());
            cfTiBloc.setM_user_name(ShiroUtils.getUserEntity().getTruename());
            cfTiBlocMapper.update(cfTiBloc,cfTiBlocWrapper);
            message.setSuccess(true);
            message.setMessage("修改集团信息成功");
        }catch (Exception e){
            log.info("修改集团信息异常"+e.getMessage());
            message.setSuccess(false);
            message.setMessage("修改集团信息异常");
        }
        return message;
    }

    /**
     * 通过集团ID查询集团信息
     * @param bloc_id
     * @return
     */
    public CfTiBloc getBlocInfoById(String bloc_id){
        CfTiBloc cfTiBloc = null;
        try {
            Wrapper<CfTiBloc> blocWrapper = new EntityWrapper<>();
            blocWrapper.eq("bloc_id",bloc_id);
            blocWrapper.and("del_flag=0");
            List<CfTiBloc> cfTiBlocList = cfTiBlocMapper.selectList(blocWrapper);

            if(cfTiBlocList!=null && cfTiBlocList.size()>0){
                cfTiBloc = cfTiBlocList.get(0);
            }

        }catch (Exception e){
            log.error("获取集团信息异常："+e.getMessage());
        }
        return  cfTiBloc;
    }

    /**
     * 添加集团信息
     * @param cfTiBloc
     * @return
     */
    public Message addBloc(CfTiBloc cfTiBloc){
        Message message = Message.N();
        try {
            //县级行政区划代码
            String disctrictsCode = cfTiBloc.getBloc_districts_code();
            //国家区号代码
            String countryAreaCode = String.format("%04d", Integer.parseInt(cfTiBloc.getBloc_country_area_code()));
            //集团类型
            String blocType = cfTiBloc.getBloc_type_code();
            //自增序列
            Long sequence = cfTiBlocMapper.getValue(BLOC_SEQUENCE_NAME);
            //集团ID
            String blocId = IdUtil.genBlocid(countryAreaCode,disctrictsCode,blocType,sequence);

            cfTiBloc.setBloc_id(blocId);
            cfTiBloc.setC_user_id(ShiroUtils.getUserEntity().getId());
            cfTiBloc.setC_user_name(ShiroUtils.getUserEntity().getTruename());
            cfTiBloc.setC_time(new Date());
            cfTiBlocMapper.insert(cfTiBloc);

            message.setSuccess(true);
            message.setMessage("保存集团信息成功");
        }catch (Exception e){

            log.info("保存集团信息异常"+e.getMessage());
            message.setSuccess(false);
            message.setMessage("保存集团信息异常");
        }

        return message;

    }

}
