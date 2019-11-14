package com.chinahotelhelp.shm.operational.module.hotel.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.chinahotelhelp.shm.operational.common.filed.CombFiled;
import com.chinahotelhelp.shm.operational.common.filed.Filed;
import com.chinahotelhelp.shm.operational.module.hotel.entity.CfTiTerminal;
import com.chinahotelhelp.shm.operational.module.hotel.mapper.CfTiBlocMapper;
import com.chinahotelhelp.shm.operational.module.hotel.mapper.CfTiTerminalMapper;
import com.chinahotelhelp.shm.operational.module.log.service.LogService;
import com.chinahotelhelp.shm.operational.module.sys.entity.Message;
import com.chinahotelhelp.shm.operational.module.sys.entity.Page;
import com.chinahotelhelp.shm.operational.module.sys.entity.PageData;
import com.chinahotelhelp.shm.operational.module.template.entity.CfPtConfig;
import com.chinahotelhelp.shm.operational.module.template.entity.TTConfig;
import com.chinahotelhelp.shm.operational.module.template.mapper.CfPtConfigMapper;
import com.chinahotelhelp.shm.operational.module.template.mapper.TTConfigMapper;
import com.chinahotelhelp.shm.operational.tools.IdUtil;
import com.chinahotelhelp.shm.operational.tools.QueryUntil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 *   @author Meng.yang
 *   @ProjectName
 *   @title: CfTiTerminalService
 *   @Description: 终端信息处理
 *   @date 2019/10/24
*/
@Slf4j
@Service
public class CfTiTerminalService {

    private final static String TERMINAL_SEQUENCE = "TERMINAL_SEQUENCE";

    @Autowired
    private CfTiTerminalMapper cfTiTerminalMapper; //终端mapper

    @Autowired
    private TTConfigMapper ttConfigMapper; //终端模板mapper

    @Autowired
    private CfTiBlocMapper cfTiBlocMapper;

    @Autowired
    private CfPtConfigMapper cfPtConfigMapper;

    @Autowired
    private LogService logService;

    public PageData getTerminalList(Page page) {
        List<Filed> queryFileds = new ArrayList<Filed>();
        Map<String, Object> params = page.getParams();
        if (params != null && params.size() > 0) {
            queryFileds.add(new CombFiled("ti_id", params));
            queryFileds.add(new CombFiled("ti_type", params));
            queryFileds.add(new CombFiled("hi_id", params));
        }

        Message message=logService.getTiState();
        StringBuilder stringBuilder = new StringBuilder();
        if(message!=null){
            Map<String,String> map=(HashMap)message.getData();
            stringBuilder.append("(select a.ti_id,a.hi_id,a.hi_name, a.`NAME` , \n" +
                    "(case when a.status = '2' then '未连接'\n" +
                    "      when a.status = '1' then '异常'\n" +
                    "      else  '正常' end)  as 'status'\n" +
                    "from (\n" +
                    "SELECT t.ti_id,t.hi_id,h.hi_name, c.`NAME` ,  \n" +
                    "IF(t.ti_id in ("+map.get("all")+") ,IF(t.ti_id in ("+map.get("error")+"),'1','0') ,'2')  as 'status'\n" +
                    "FROM  cf_ti_terminal t ,cf_ti_hotel h ,core_dict c \n" +
                    "where t.ti_type=c.`value` \n" +
                    "and  c.type='terminal_type' \n" +
                    "and t.hi_id = h.hi_id\n" +
                    "and t.del_flag = '0'\n" +
                    ") a order by a.status desc) b");
        }

        PageData pageData = new PageData();
        String[] cmdSqlArray = QueryUntil.getQuerySql("*", queryFileds, "", stringBuilder.toString());
        page.setExec_sql(cmdSqlArray[0]);
        pageData.setData(cfTiTerminalMapper.execSQL(page));
        page.setExec_sql(cmdSqlArray[1]);
        pageData.setRecordsTotal(Integer.parseInt(cfTiTerminalMapper.execSQL(page).get(0).get("count").toString()));
        pageData.setRecordsFiltered(pageData.getRecordsTotal());
        return pageData;
    }

    public Message delTerminal(String ti_id) {
        Message message = new Message();
        try {

            Wrapper<CfTiTerminal> cfTiTerminalWrapper = new EntityWrapper<>();
            cfTiTerminalWrapper.eq("ti_id", ti_id);
            CfTiTerminal cfTiTerminal = new CfTiTerminal();
            cfTiTerminal.setDel_flag(1);
            cfTiTerminalMapper.update(cfTiTerminal, cfTiTerminalWrapper);

            Wrapper<TTConfig> ttConfigWrapper = new EntityWrapper<>();
            ttConfigWrapper.eq("ti_id",ti_id);
            TTConfig cfPtTemplateTerminal = new TTConfig();
            cfPtTemplateTerminal.setDel_flag(1);
            ttConfigMapper.update(cfPtTemplateTerminal,ttConfigWrapper);

            message.setSuccess(true);
            message.setMessage("删除终端信息成功");
        }catch (Exception e){
            log.info("删除终端信息异常");
            message.setSuccess(false);
            message.setMessage("删除终端信息异常");
        }

        return message;
    }

    public Message updateTerminal(CfTiTerminal cfTiTerminal){
        Message message = Message.N();
        try {
            //更新终端表
            Wrapper<CfTiTerminal> terminalWrapper = new EntityWrapper<>();
            terminalWrapper.eq("ti_id",cfTiTerminal.getTi_id());
            cfTiTerminal.setM_time(new Date());
            cfTiTerminalMapper.update(cfTiTerminal,terminalWrapper);

            Wrapper<TTConfig> ttConfigWrapper = new EntityWrapper<>();
            ttConfigWrapper.eq("ti_id",cfTiTerminal.getTi_id());
            TTConfig cfPtTemplate = new TTConfig();
            cfPtTemplate.setDel_flag(1);
            ttConfigMapper.update(cfPtTemplate,ttConfigWrapper);

            //更新终端模板表
            String tempId = cfTiTerminal.getTemp_id();
            String[] tempIdList = tempId.split(",");

                for (String list : tempIdList) { //将终端对应的模板插入模板表
                    TTConfig cfPtTemplateTerminal = new TTConfig();
                    cfPtTemplateTerminal.setTi_id(cfTiTerminal.getTi_id());
                    cfPtTemplateTerminal.setTemp_id(list);
                    cfPtTemplateTerminal.setC_time(new Date());
                    ttConfigMapper.insert(cfPtTemplateTerminal);

            }

            message.setSuccess(true);
            message.setMessage("修改终端信息成功");
        }catch (Exception e){
            log.info("修改终端信息异常");
            message.setSuccess(false);
            message.setMessage("修改终端信息异常");
        }
        return message;
    }

    public Message getTerminalInfoById(String ti_id){
        Message message= Message.N();
        try{
            Page page=new Page();
            page.setExec_sql("SELECT\n" +
                    "\tti_id,\n" +
                    "\thi_id,\n" +
                    "\tm_id,\n" +
                    "\tti_sw_id,\n" +
                    "\tti_code,\n" +
                    "\tbh_id,\n" +
                    "\tti_type,\n" +
                    "\tti_up_time,\n" +
                    "\tti_down_time,\n" +
                    "\t(\n" +
                    "\t\tSELECT\n" +
                    "\t\t\tGROUP_CONCAT(CONCAT('', temp_id, ''))\n" +
                    "\t\tFROM\n" +
                    "\t\t\tcf_pt_template_terminal b\n" +
                    "\t\tWHERE\n" +
                    "\t\t\ta.ti_id = b.ti_id\n" +
                    "\t\tAND b.del_flag = 0\n" +
                    "\t) tempIds\n" +
                    "FROM\n" +
                    "\tcf_ti_terminal a\n" +
                    "WHERE\n" +
                    "\ta.del_flag = 0\n" +
                    "AND a.ti_id = '"+ti_id+"'");
            Object object= cfTiTerminalMapper.execSQL(page);
            message.setSuccess(true);
            message.setMessage("通过终端ID获取终端信息成功");
            message.setData(object);
        }catch (Exception e){
            log.info("取终端信息异常"+e.getMessage());
        }
        return message;
    }

    public Message addTerminal(CfTiTerminal cfTiTerminal){
        Message message = Message.N();
        try {

            //酒店ID
            String hotelId = cfTiTerminal.getHi_id();
            Long sequence = cfTiBlocMapper.getValue(TERMINAL_SEQUENCE);
            String tiId = IdUtil.genTerminalId(hotelId, sequence);

            cfTiTerminal.setTi_id(tiId);
            cfTiTerminal.setC_time(new Date());
            cfTiTerminalMapper.insert(cfTiTerminal);


            //获取模板ID  一个终端对应多个模板
            String tempId = cfTiTerminal.getTemp_id();
            String[] tempIdList = tempId.split(",");
            for (String list : tempIdList) { //将终端对应的模板插入模板表
                TTConfig terminalTemplate = new TTConfig();
                terminalTemplate.setTi_id(cfTiTerminal.getTi_id());
                terminalTemplate.setTemp_id(list);
                terminalTemplate.setC_time(new Date());
                ttConfigMapper.insert(terminalTemplate);
            }

            message.setSuccess(true);
            message.setMessage("保存终端信息成功");
        }catch (Exception e){

            log.info("保存终端信息异常");
            message.setSuccess(false);
            message.setMessage("保存终端信息异常");
        }

        return message;

    }

    public Message copyTerminal(String hi_id,String ti_id){
        Message message = Message.N();

        try {
            //酒店ID
            Long sequence = cfTiBlocMapper.getValue(TERMINAL_SEQUENCE);
            String tiId = IdUtil.genTerminalId(hi_id, sequence);

            List<CfTiTerminal> terminal = (List<CfTiTerminal>) getTerminalInfoById(ti_id);

            //添加新终端
            CfTiTerminal cfTiTerminal = new CfTiTerminal();
            cfTiTerminal.setTi_id(tiId);
            cfTiTerminal.setHi_id(hi_id);
            cfTiTerminal.setTi_type(terminal.get(0).getTi_type());
            cfTiTerminal.setC_time(new Date());
            cfTiTerminalMapper.insert(cfTiTerminal);

            //添加新终端模板
            Wrapper<TTConfig> ttConfigWrapper = new EntityWrapper<>();
            ttConfigWrapper.eq("ti_id",ti_id);
            ttConfigWrapper.and("del_flag=0");
            List<TTConfig> ttConfigList = ttConfigMapper.selectList(ttConfigWrapper);
            if(ttConfigList!=null && ttConfigList.size()>0){
                for(int i=0 ; i<ttConfigList.size() ; i++){
                    String temp_id = ttConfigList.get(i).getTemp_id();
                    TTConfig ttConfig = new TTConfig();
                    ttConfig.setTi_id(tiId);
                    ttConfig.setTemp_id(temp_id);
                    ttConfig.setC_time(new Date());
                    //ttConfig.setC_user_id(ShiroUtils.getUserEntity().getId());
                    ttConfigMapper.insert(ttConfig);

                    //复制参数值
                    Wrapper<CfPtConfig> cfPtConfigWrapper = new EntityWrapper<>();
                    cfPtConfigWrapper.eq("ti_id",ti_id);
                    cfPtConfigWrapper.eq("temp_id",temp_id);
                    List<CfPtConfig> ptConfigList = cfPtConfigMapper.selectList(cfPtConfigWrapper);
                    if(ttConfigList!=null && ttConfigList.size()>0){
                        for(CfPtConfig ptConfig:ptConfigList){
                            ptConfig.setC_time(new Date());
                            cfPtConfigMapper.insert(ptConfig);
                        }
                    }

                }
            }

            message.setSuccess(true);
            message.setMessage("复制终端信息成功");

        }catch(Exception e){
            log.info("【复制终端异常：】"+e.getMessage());
        }
        return message;
    }

    public List<Map> getTemplateByTiId(String ti_id){
        List<Map> map = null;
        try {
            map = cfTiTerminalMapper.getTemplateByTiId(ti_id);

        }catch (Exception e){
            log.info("【根据终端ID获取模板异常】"+ e.getMessage());
        }

        return map;
    }

    public List<Map> getTemplateParasByTiId(String ti_id,String temp_id){

        List<Map> parasList = null;
        try {
            List<Map> parasValueList = cfTiTerminalMapper.getTemplateValueByTiId(ti_id,temp_id);
            if (parasValueList.size()>0){
                parasList = parasValueList;
            }else {
                parasList = cfTiTerminalMapper.getTemplateParasByTiId(ti_id,temp_id);
            }

        }catch (Exception e){
            log.info("【根据终端ID及模板ID获取参数异常】"+ e.getMessage());
        }

        return parasList;
    }

    public Message addTerminalParamValue(List<CfPtConfig> cfPtConfigs){
        Message message = Message.N();

        try {

            for (CfPtConfig config:cfPtConfigs){

                Wrapper<CfPtConfig> cfPtConfigWrapper = new EntityWrapper<>();
                cfPtConfigWrapper.eq("ti_id",config.getTi_id());
                cfPtConfigWrapper.eq("temp_id",config.getTemp_id());
                cfPtConfigWrapper.eq("p_id",config.getP_id());
                List<CfPtConfig> cfPtConfigList = cfPtConfigMapper.selectList(cfPtConfigWrapper);

                if (cfPtConfigList.size()>0){
                    Wrapper<CfPtConfig> configWrapper = new EntityWrapper<>();
                    configWrapper.eq("ti_id",config.getTi_id());
                    configWrapper.eq("temp_id",config.getTemp_id());
                    configWrapper.eq("p_id",config.getP_id());
                    config.setM_time(new Date());
                    cfPtConfigMapper.update(config,configWrapper);

                }else {
                    config.setC_time(new Date());
                    cfPtConfigMapper.insert(config);
                }

            }
            message.setSuccess(true);
            message.setMessage("配置参数信息成功");
        }catch (Exception e){

        log.info("【配置参数异常】"+e.getMessage());
        }

        return message;
    }
}
