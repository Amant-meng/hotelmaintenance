package com.chinahotelhelp.shm.operational.module.sys.service;

import com.chinahotelhelp.shm.operational.common.filed.Filed;
import com.chinahotelhelp.shm.operational.module.sys.entity.*;
import com.chinahotelhelp.shm.operational.module.sys.mapper.SysFileMapper;
import com.chinahotelhelp.shm.operational.module.sys.mapper.SysFileTempMapper;
import com.chinahotelhelp.shm.operational.tools.POIUntils;
import com.chinahotelhelp.shm.operational.tools.QueryUntil;
import com.chinahotelhelp.shm.operational.tools.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Huan.Xia
 * @Title: SysFileService
 * @ProjectName merchant-management
 * @Description: TODO
 * @date 2019-01-1216:49
 */
@Service
@Slf4j
public class SysFileService {
    @Autowired
    private SysFileMapper sysFileMapper;
    @Autowired
    private SysFileTempMapper sysFileTempMapper;
    @Autowired
    private HttpServletResponse response;


    /**
     * 将cvs转换为file文件
     *
     * @param saveFile
     * @return
     */
    public HSSFWorkbook changeCVStoExcel(File saveFile) throws IOException {
        HSSFWorkbook hwb = new HSSFWorkbook();
        String path = saveFile.getAbsolutePath();
        ArrayList arList = null;
        ArrayList al = null;
        String thisLine;
        int count = 0;
        FileInputStream fis = new FileInputStream(saveFile);
        InputStreamReader reader = new InputStreamReader(fis, "GBK");
        BufferedReader br = new BufferedReader(reader);
        int i = 0;
        arList = new ArrayList();
        while ((thisLine = br.readLine()) != null) {
            al = new ArrayList();
            String strar[] = thisLine.split(",");
            for (int j = 0; j < strar.length; j++) {
                al.add(strar[j]);
            }
            arList.add(al);
            System.out.println();
            i++;
        }
        try {

            HSSFSheet sheet = hwb.createSheet("new sheet");
            for (int k = 0; k < arList.size(); k++) {
                ArrayList ardata = (ArrayList) arList.get(k);
                HSSFRow row = sheet.createRow((short) 0 + k);
                for (int p = 0; p < ardata.size(); p++) {
                    HSSFCell cell = row.createCell((short) p);
                    String data = ardata.get(p).toString();
                    if (data.startsWith("=")) {
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        data = data.replaceAll("\"", "");
                        data = data.replaceAll("=", "");
                        cell.setCellValue(data);
                    } else if (data.startsWith("\"")) {
                        data = data.replaceAll("\"", "");
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellValue(data);
                    } else {
                        data = data.replaceAll("\"", "");
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                        cell.setCellValue(data);
                    }
                }
                System.out.println();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return hwb;
    }

    /**
     * 文件上传
     *
     * @param file
     * @param uploadFolder
     * @return
     */
    public Message upload(MultipartFile file, String taskId, String uploadFolder) throws IOException {
        Message message = new Message();
        if (file != null && file.getSize() > 0) {
            String contentType = file.getContentType();
            if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet") || contentType.equals("application/vnd.ms-excel")) {
                String filename = file.getOriginalFilename();
                String suffix = filename.substring(filename.lastIndexOf(".") + 1);
                String id = UUID.randomUUID().toString();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
                String path = String.format("%s//%s.%s", simpleDateFormat.format(new Date()), id, suffix);
                //保存文件至服务器
                File saveFile = new File(String.format("%s//%s", uploadFolder, path));
                try {
                    //创建目录
                    if (!saveFile.getParentFile().exists()) {
                        saveFile.getParentFile().mkdirs();
                    }
                    //保存文件
                    file.transferTo(saveFile);
                    Map mapInfo = new HashMap();
                    //读取文件
                    message = readFile(saveFile);
                    if (message.isSuccess()) {
                        //获取第一张表
                        Sheet sheet = ((Workbook) message.getData()).getSheetAt(0);
                        if (suffix.equals("csv")) {
                            mapInfo = getcvsFileType(sheet);
                            message = (Message) mapInfo.get("message");
                        } else {
                            message = getFileType(sheet);
                        }


                            if (message.isSuccess()) {
                            String tag = (String) message.getData();
                            List<SysFileTemp> sysFileTempList = null;
                            SysUser sessionUser = ShiroUtils.getUserEntity();
                            if (tag.equals("UNIONCARD")) {
                                message = readUnionCard(sheet, taskId, sessionUser.getHiId());
                                if (message.isSuccess()) {
                                    sysFileTempList = (List<SysFileTemp>) message.getData();
                                }
                            } else if (tag.equals("UNIONLINE")) {
                                if (suffix.equals("csv")){
                                    message = readBank(sheet, taskId, sessionUser.getHiId());
                                }else{
                                    message = readUnionLine(sheet, taskId, sessionUser.getHiId());
                                }
                                if (message.isSuccess()) {
                                    sysFileTempList = (List<SysFileTemp>) message.getData();
                                }
                            }
                            if (sysFileTempList != null && sysFileTempList.size() > 0) {
                                sysFileTempMapper.insertBatch(sysFileTempList);
                                message.setSuccess(true);
                                Map<String, Object> dataMap = new HashMap<>();
                                if(suffix.equals("csv")){
                                    dataMap.put("cols", sysFileTempMapper.getColConfig("COMP-UPLOAD-ICBC"));
                                }else{
                                    dataMap.put("cols", sysFileTempMapper.getColConfig("COMP-UPLOAD-PREVIEW"));
                                }

                                dataMap.put("preview", sysFileTempMapper.getTempData(taskId));
                                message.setData(dataMap);

                                //保存文件
                                SysFile sysFile = new SysFile();
                                if(mapInfo.get("inputType")!=null){
                                    sysFile.setInputType(mapInfo.get("inputType").toString());
                                }
                                sysFile.setId(UUID.randomUUID().toString());
                                sysFile.setTaskId(taskId);
                                sysFile.setSuffix(suffix);
                                sysFile.setCreateusername(sessionUser.getUsername());
                                sysFile.setCreateuser(sessionUser.getId());
                                sysFile.setPath(path);
                                sysFile.setType(contentType);
                                sysFile.setDeltag("0");
                                sysFile.setName(filename);
                                sysFile.setHotelId(ShiroUtils.getUserEntity().getHiId());
                                sysFile.setCreatetime(new Date());
                                sysFileMapper.insert(sysFile);
                            }

                        }
                    }

                } catch (IOException ex) {
                    ex.printStackTrace();
                    message.setMessage("文件保存出错！");
                }

            } else {
                message.setMessage("上传文件仅支持xls和xlsx格式!");
            }
        } else {
            message.setMessage("请上传文件！");
        }
        return message;
    }

    /**
     * 获取列头
     *
     * @param sheet
     * @return
     */
    private Message getFileType(Sheet sheet) {
        Message message = new Message();
        message.setMessage("未识别对账明细，请确保对账文件正确！");
        Row titleCell = sheet.getRow(3);
        if (titleCell != null) {
            String tag = titleCell.getCell(7) == null ? null : titleCell.getCell(7).toString();
            if(tag != null){
                if (tag.equals("参考号")) {
                    message.setSuccess(true);
                    message.setData("UNIONCARD");
                } else if (tag.equals("实际支付金额")) {
                    message.setSuccess(true);
                    message.setData("UNIONLINE");
                }
            }
        }
        return message;
    }

    /**
     * 获取列头
     *
     * @param sheet
     * @return
     */
    private Map getcvsFileType(Sheet sheet) {
        Map map =  new HashMap();
        Message message = new Message();
        message.setMessage("未识别对账明细！请确保对账文件格式正确！");
        Row titleCell = sheet.getRow(2);
        if (titleCell != null) {
            if (titleCell.getCell(7).toString().equals("参考号")) {
                message.setSuccess(true);
                message.setData("UNIONCARD");
                map.put("inputType","工行对比文件类型");
                map.put("message",message);
            } else if (titleCell.getCell(15).toString().equals("订单总金额")) {
                message.setSuccess(true);
                message.setData("UNIONLINE");
                map.put("inputType","工行对比文件类型");
                map.put("message",message);
            }
        }
        return map;
    }


    /**
     * 读取文件数据
     *
     * @return
     */
    private Message readFile(File saveFile) {
        Message message = new Message();
        InputStream inputStream = null;
        try {
            //装载文件
            inputStream = new FileInputStream(saveFile.getAbsolutePath());
            String name = saveFile.getName();
            String suffix = name.substring(name.lastIndexOf(".") + 1);
            if (suffix.equals("csv")) {
                Workbook workbookcvs = changeCVStoExcel(saveFile);
                message.setSuccess(true);
                message.setData(workbookcvs);
            } else {
                //根据内容格式创建Book
                Workbook workbook = POIUntils.workBookAdaper(inputStream);
                message.setSuccess(true);
                message.setData(workbook);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            message.setMessage(ex.getMessage());

        } finally {
            try {
                if (inputStream != null) inputStream.close();
            } catch (Exception ex) {
                log.error(ex.getMessage());
            }
        }
        return message;
    }

    /**
     * 读取银联银行卡数据
     *
     * @param sheet
     * @param taskId
     * @return
     */
    private Message readUnionCard(Sheet sheet, String taskId, String hotelId) {
        Message message = new Message();
        Integer collength = 4;
        List<SysFileTemp> sysFileTempList = new ArrayList<SysFileTemp>();
        try {
            for (int i = collength; i <= sheet.getLastRowNum() - 3; i++) {
                Row row = sheet.getRow(i);
                SysFileTemp sysFileTemp = new SysFileTemp();
                sysFileTemp.setId(UUID.randomUUID().toString());
                sysFileTemp.setTaskid(taskId);
                sysFileTemp.setHotelId(hotelId);
                sysFileTemp.setC1(POIUntils.getCellValue(row.getCell(0)));
                sysFileTemp.setC2(POIUntils.getCellValue(row.getCell(1)));
                sysFileTemp.setC3(POIUntils.getCellValue(row.getCell(2)));
                sysFileTemp.setC4(POIUntils.getCellValue(row.getCell(3)));
                sysFileTemp.setC5(POIUntils.getCellValue(row.getCell(4)));
                sysFileTemp.setC6(POIUntils.getCellValue(row.getCell(5)));
                sysFileTemp.setC7(POIUntils.getCellValue(row.getCell(6)));
                sysFileTemp.setC8(POIUntils.getCellValue(row.getCell(7)));
                sysFileTemp.setC9(POIUntils.getCellValue(row.getCell(8)));
                sysFileTemp.setC10(POIUntils.getCellValue(row.getCell(9)));
                sysFileTemp.setC11(POIUntils.getCellValue(row.getCell(10)));
                sysFileTemp.setC12(POIUntils.getCellValue(row.getCell(11)));
                sysFileTemp.setC13(POIUntils.getCellValue(row.getCell(12)));
                sysFileTempList.add(sysFileTemp);
            }
            message.setSuccess(true);
            message.setData(sysFileTempList);
        } catch (Exception ex) {
            message.setMessage(ex.getMessage());
        }
        return message;
    }

    /**
     * 读取银联线上数据
     *
     * @param sheet
     * @param taskId
     * @return
     */
    private Message readUnionLine(Sheet sheet, String taskId, String hotelId) {
        Message message = new Message();
        Integer collength = 4;
        List<SysFileTemp> sysFileTempList = new ArrayList<SysFileTemp>();
        try {
            for (int i = collength; i <= sheet.getLastRowNum() - 3; i++) {
                Row row = sheet.getRow(i);
                SysFileTemp sysFileTemp = new SysFileTemp();
                sysFileTemp.setId(UUID.randomUUID().toString());
                sysFileTemp.setTaskid(taskId);
                sysFileTemp.setHotelId(hotelId);
                sysFileTemp.setC1(POIUntils.getCellValue(row.getCell(0)));
                sysFileTemp.setC2(POIUntils.getCellValue(row.getCell(1)));
                sysFileTemp.setC3(POIUntils.getCellValue(row.getCell(2)));
                sysFileTemp.setC4(POIUntils.getCellValue(row.getCell(3)));
                sysFileTemp.setC5(POIUntils.getCellValue(row.getCell(4)));
                sysFileTemp.setC6(POIUntils.getCellValue(row.getCell(5)));
                sysFileTemp.setC7(POIUntils.getCellValue(row.getCell(6)));
                sysFileTemp.setC8(POIUntils.getCellValue(row.getCell(10)));
                sysFileTemp.setC9(POIUntils.getCellValue(row.getCell(11)));
                sysFileTemp.setC10(POIUntils.getCellValue(row.getCell(8)));
                sysFileTemp.setC11(POIUntils.getCellValue(row.getCell(9)));
                sysFileTemp.setC12(POIUntils.getCellValue(row.getCell(13)));
                sysFileTemp.setC13(POIUntils.getCellValue(row.getCell(12)));
                sysFileTemp.setC14(POIUntils.getCellValue(row.getCell(14)));
                sysFileTemp.setC15(POIUntils.getCellValue(row.getCell(15)));
                sysFileTemp.setC16(POIUntils.getCellValue(row.getCell(16)));
                sysFileTemp.setC17(POIUntils.getCellValue(row.getCell(17)));
                sysFileTempList.add(sysFileTemp);
            }
            message.setSuccess(true);
            message.setData(sysFileTempList);
        } catch (Exception ex) {
            message.setMessage(ex.getMessage());
        }
        return message;
    }

    /**
     * 读取工行线上数据
     *
     * @param sheet
     * @param taskId
     * @return
     */
    private Message readBank(Sheet sheet, String taskId, String hotelId) {
        Message message = new Message();
        Integer collength = 3;
        List<SysFileTemp> sysFileTempList = new ArrayList<SysFileTemp>();
        try {
            for (int i = collength; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                SysFileTemp sysFileTemp = new SysFileTemp();
                sysFileTemp.setId(UUID.randomUUID().toString());
                sysFileTemp.setTaskid(taskId);
                sysFileTemp.setHotelId(hotelId);
                sysFileTemp.setC1(POIUntils.getCellValue(row.getCell(1)));
                sysFileTemp.setC2(POIUntils.getCellValue(row.getCell(2)));
                sysFileTemp.setC3(POIUntils.getCellValue(row.getCell(3)));
                sysFileTemp.setC4(POIUntils.getCellValue(row.getCell(4)));
                sysFileTemp.setC5(POIUntils.getCellValue(row.getCell(5)));
                sysFileTemp.setC6(POIUntils.getCellValue(row.getCell(6)));
                sysFileTemp.setC7(POIUntils.getCellValue(row.getCell(7)));
                sysFileTemp.setC8(POIUntils.getCellValue(row.getCell(8)));
                sysFileTemp.setC9(POIUntils.getCellValue(row.getCell(9)));
                sysFileTemp.setC10(POIUntils.getCellValue(row.getCell(10)));
                sysFileTemp.setC11(POIUntils.getCellValue(row.getCell(11)));
                sysFileTemp.setC12(POIUntils.getCellValue(row.getCell(12)));
                sysFileTemp.setC13(POIUntils.getCellValue(row.getCell(13)));
                sysFileTemp.setC14(POIUntils.getCellValue(row.getCell(14)));
                sysFileTemp.setC15(POIUntils.getCellValue(row.getCell(15)));
                sysFileTemp.setC16(POIUntils.getCellValue(row.getCell(16)));
                sysFileTemp.setC17(POIUntils.getCellValue(row.getCell(17)));
                sysFileTemp.setC18(POIUntils.getCellValue(row.getCell(18)));
                sysFileTemp.setC19(POIUntils.getCellValue(row.getCell(19)));
                sysFileTemp.setC20(POIUntils.getCellValue(row.getCell(20)));
                sysFileTemp.setC21(POIUntils.getCellValue(row.getCell(21)));
                sysFileTempList.add(sysFileTemp);
            }
            message.setSuccess(true);
            message.setData(sysFileTempList);
        } catch (Exception ex) {
            message.setMessage(ex.getMessage());
        }
        return message;
    }

    /**
     * 获取比对结果
     *
     * @param page
     * @return
     */
    public PageData getComparisonResult(Page page) {

        List<Filed> queryFileds = new ArrayList<Filed>();
        Map<String, Object> params = page.getParams();
        if (params != null && params.size() > 0) {
            String businesstime = (String) params.get("businesstime");
            String[] timeArray = businesstime.split(" - ");
            params.put("starttime", timeArray[0] + " 00:00:00");
            params.put("endtime", timeArray[1] + " 00:00:00");
            params.put("hotelid", ShiroUtils.getUserEntity().getHiId());
            page.setMapToParams(params);
        }
        String taskid = page.getParams().get("taskid").toString();
        SysFile sysFile = sysFileMapper.findtype(taskid);
        boolean append = !params.containsKey("all");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(SELECT\n" +
                "\tt.*,p.yhssje\n" +
                "FROM\n" +
                "\t(\n" +
                "SELECT\n" +
                "\t\t\t\tg.hi_id,\n" +
                "\t\t\t\tg.or_id,\n" +
                "\t\t\t\t##预订单号\n" +
                "\t\t\t\tg.or_checkin_id,\n" +
                "\t\t\t\t##入住单号\n" +
                "\t\t\t\tt1. NAME AS or_modename,\n" +
                "\t\t\t\tg.or_mode,\n" +
                "\t\t\t\t##入住类型\n" +
                "\t\t\t\tg.or_source,\n" +
                "\t\t\t\t##来源渠道\n" +
                "\t\t\t\tt2. NAME AS or_status,\n" +
                "\t\t\t\t##入住状态\n" +
                "\t\t\t\tg.ro_id,\n" +
                "\t\t\t\t##房间号\n" +
                "\t\t\t\tg.ro_type_name,\n" +
                "\t\t\t\t##房型\n" +
                "\t\t\t\tg.or_ci_name,\n" +
                "\t\t\t\t##姓名\n" +
                "\t\t\t\tg.or_act_arr_time,\n" +
                "\t\t\t\t##入住时间\n" +
                "\t\t\t\tg.or_act_dep_time,\n" +
                "\t\t\t\t##退房时间\n" +
                "\t\t\t\tg.or_act_day,\n" +
                "\t\t\t\t##实住天数\n" +
                "\t\t\t\tg.ro_price,\n" +
                "\t\t\t\t##房间单价\n" +
                "\t\t\t\tcase when (p.pay_isone = 1  and p.pay_trans_typebs = '0') then 0 else g.bill_prepay end as bill_prepay, \n" +
                "\t\t\t\t##预授权金额\n" +
                "\t\t\t\tb.bill_all_pay_price,\n" +
                "\t\t\t\t##支付金额\n" +
                "\t\t\t\tg.bill_deposit,\n" +
                "\t\t\t\t##押金金额\n" +
                "\t\t\t\tb.bill_all_ro_price,\n" +
                "\t\t\t\t##房费\n" +
                "\t\t\t\tb.bill_all_con_price,\n" +
                "\t\t\t\t##消费品\n" +
                "\t\t\t\tb.bill_all_other_price,\n" +
                "\t\t\t\t##其它\n" +
                "\t\t\t\tb.bill_refundable_amount,\n" +
                "\t\t\t\t##退款金额\n" +
                "\t\t\t\tb.bill_all_pay_money,\n" +
                "\t\t\t\t##消费金额\n" +
                "\t\t\t\tt3. NAME AS pay_paytypename,\n" +
                "\t\t\t\tp.pay_paytype,\n" +
                "\t\t\t\t##预付支付方式\n" +
                "\t\t\t\tt4. NAME AS pay_trans_typebsname,\n" +
                "\t\t\t\t##预付交易类型\n" +
                "\t\t\t\tp.pay_trans_typebs,\n" +
                "\t\t\t\tp.pay_refer_no,\n" +
                "\t\t\t\t##参考号\n" +
                "\t\t\t\tp.pay_trans_datetime,##预付交易时间\n" +
                "\t\t\t\tg.pk_ci_id\n" +
                "\t\t\tFROM\n" +
                "\t\t\t\tt_pms_order_guest g\n" +
                "\t\t\tLEFT OUTER JOIN sys_dict t1 ON g.or_mode = t1. CODE\n" +
                "\t\t\tAND t1.type = 'code_checkinmode'\n" +
                "\t\t\tLEFT OUTER JOIN sys_dict t2 ON g.or_status = t2. CODE\n" +
                "\t\t\tAND t2.type = 'code_checkinstatus',\n" +
                "\t\t\tt_pms_bill b,\n" +
                "\t\t\tt_pay_payment p\n" +
                "\t\t LEFT OUTER JOIN sys_dict t3 ON p.pay_paytype = t3. CODE\n" +
                "\t\tAND t3.type = 'code_payment'\n" +
                "\t\tLEFT OUTER JOIN sys_dict t4 ON p.pay_trans_typebs = t4. CODE\n" +
                "\t\tAND t4.type = 'code_trantypebs'\n" +
                "\t\tWHERE\n" +
                "\t\t\tg.pk_ci_id = p.pk_ci_id\n" +
                "\t\tAND g.pk_ci_id = b.pk_ci_id\n" +
                "\t\tAND (\n" +
                "\t\t\t(\n" +
                "\t\t\t\tp.pay_isone = 1\n" +
                "\t\t\t\tAND p.pay_trans_typebs = '0'\n" +
                "\t\t\t)\n" +
                "\t\t\tOR (p.pay_trans_typebs = '4')\n" +
                "\t\t)\n" +
                "\t\t AND g.or_act_arr_time >= str_to_date(\n" +
                "\t\t\t#{params.starttime},\n" +
                "\t\t\t'%Y-%m-%d %T'\n" +
                "\t\t) \n" +
                "\t\t AND g.or_act_arr_time <= str_to_date(\n" +
                "\t\t\t#{params.endtime},\n" +
                "\t\t\t'%Y-%m-%d %T'\n" +
                "\t\t) \n" +
                " and g.hi_id = #{params.hotelid} " +
                "\t\tORDER BY\n" +
                "\t\t\tor_act_arr_time DESC\n" +
                "\t\t) t " +
                "LEFT JOIN (\n" +
                "\tSELECT\n" +
                "\t\tsum(c6) yhssje,\n" +
                "\t\tt.pk_ci_id\n" +
                "\tFROM\n" +
                "\t\tsys_file_temp m,\n" +
                "\t\tt_pay_payment t\n" +
                "\tWHERE\n" +
                "\t\tm.taskid = #{params.taskid}\n" +
                "\tAND (t.pay_refer_no = m.c8)\n" +
                "\tGROUP BY\n" +
                "\t\tt.pk_ci_id\n" +
                ") p\n" +
                " on t.pk_ci_id = p.pk_ci_id) t  ");

        String previewType = (String) params.get("previewtype");
        String condStr = null;
        if (previewType != null && previewType.length() > 0) {
            //比对金额相等
            if (previewType.equals("e")) {
                condStr = " and bill_all_pay_money - yhssje = 0 ";
                //比对金额不相等
            } else if (previewType.equals("ne")) {
                condStr = " and bill_all_pay_money - yhssje <> 0 ";
                //未找到交易明细
            } else if (previewType.equals("n")) {
                condStr = " and yhssje is null ";
            }
        }
        PageData pageData = new PageData();
        String[] cmdSqlArray = QueryUntil.getQuerySql("*", null, condStr, stringBuilder.toString(),append);
        page.setExec_sql(cmdSqlArray[0]);

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("cols", sysFileTempMapper.getColConfig("COMP-UPLOAD-RESULT"));
        dataMap.put("preview", sysFileTempMapper.execSQL(page));
        pageData.setData(dataMap);


        page.setExec_sql(cmdSqlArray[1]);
        pageData.setRecordsTotal(Integer.parseInt(sysFileTempMapper.execSQL(page).get(0).get("count").toString()));
        pageData.setRecordsFiltered(pageData.getRecordsTotal());
        return pageData;
    }



    /**
     * 获取比对结果
     *
     * @param page
     * @return
     */
    public PageData getBillDeatilsComparisonResult(Page page) {

        List<Filed> queryFileds = new ArrayList<Filed>();
        Map<String, Object> params = page.getParams();
        if (params != null && params.size() > 0) {
            String businesstime = (String) params.get("businesstime");
            String[] timeArray = businesstime.split(" - ");
            params.put("starttime", timeArray[0] + " 00:00:00");
            params.put("endtime", timeArray[1] + " 00:00:00");
            params.put("hotelid", ShiroUtils.getUserEntity().getHiId());
            page.setMapToParams(params);
        }

        boolean append = !params.containsKey("all");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(SELECT\n" +
                "\tt.*,p.yhssje\n" +
                "FROM\n" +
                "\t(\n" +
                "SELECT\n" +
                "\tg.pk_ci_id,\n" +
                "  g.hi_id, ##酒店编号\n" +
                "  g.or_id, ##预订单号\n" +
                "  g.or_checkin_id,  ##入住单号\n" +
                "  t1.name as or_mode,##入住类型\n" +
                "\tg.or_source, ##来源渠道\n" +
                "\tg.ro_id,  ##房间号\n" +
                "\tg.ro_type_name,  ##房型\n" +
                "\tg.or_ci_name,  ##姓名\n" +
                "\tt2.name as bill_typename,##账务类型\n" +
                "\tb.bill_type,##账务类型编号\n" +
                "\tb.bill_pid,##账务条目编号\n" +
                "  t3.name as bill_pname,##账务条目\n" +
                "\tb.bill_con_money,  ##订单消费金额\n" +
                "\tb.bill_pay_money,   ##订单结算金额\n" +
                "  b.pay_shift_no,   ##原始凭证编号\n" +
                "\tb.bill_comments,  ##账单备注\n" +
                "\tb.bill_ptime,  ##账单记账时间\n" +
                "\tdate_format(bill_ptime,'%Y-%m-%d') as bill_pdate   ##账单记账日期\n" +
                "FROM\n" +
                "\tt_pms_order_guest g \n" +
                "\tLEFT OUTER JOIN sys_dict t1 on g.or_mode = t1.code and t1.type= 'code_checkinmode' ,\n" +
                "  t_pms_bill_detail b \n" +
                "\tLEFT OUTER JOIN sys_dict t2 on b.bill_type = t2.code and t2.type= 'code_accountstype'\n" +
                "  LEFT OUTER JOIN sys_dict t3 on b.bill_pid = t3.code and t3.type= 'code_accountscategory'\n" +
                "WHERE\n" +
                "g.pk_ci_id = b.pk_ci_id and b.hi_id =#{params.hotelid} and b.bill_ptime >= #{params.starttime} and b.bill_ptime <= #{params.endtime} order by bill_ptime desc\t\t) t LEFT JOIN (\n" +
                "\tSELECT\n" +
                "\tc5 yhssje,\n" +
                "\tc8\n" +
                "\tFROM\n" +
                "\t\tsys_file_temp\n" +
                "\tWHERE\n" +
                "\t\ttaskid = #{params.taskid} \n" +
                ") p\n" +
                " on t.pay_shift_no = c8"+
                "\t\t) t " );

        String previewType = (String) params.get("previewtype");
        String condStr = null;
        if (previewType != null && previewType.length() > 0) {
            //比对金额相等
            if (previewType.equals("e")) {
                condStr = " and (bill_con_money + bill_pay_money) = yhssje ";
                //比对金额不相等
            } else if (previewType.equals("ne")) {
                condStr = " and (bill_con_money + bill_pay_money) <> yhssje ";
                //未找到交易明细
            } else if (previewType.equals("n")) {
                condStr = " and yhssje is null ";
            }
        }

        if(params.get("billtype") != null){
            condStr += " and bill_type = #{params.billtype}";
        }
        if(params.get("billpid") != null){
            condStr += " and bill_pid = #{params.billpid}";
        }

        PageData pageData = new PageData();
        String[] cmdSqlArray = QueryUntil.getQuerySql("*", null, condStr, stringBuilder.toString(),append);
        page.setExec_sql(cmdSqlArray[0]);

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("cols", sysFileTempMapper.getColConfig("COMP-UPLOAD-RESULT-BILLDETAILS"));
        List<Map> result = sysFileTempMapper.execSQL(page);
        dataMap.put("preview", result);
        pageData.setData(dataMap);


        page.setExec_sql(cmdSqlArray[1]);
        pageData.setRecordsTotal(Integer.parseInt(sysFileTempMapper.execSQL(page).get(0).get("count").toString()));
        pageData.setRecordsFiltered(pageData.getRecordsTotal());

        double bill_con_money = 0;
        double bill_pay_money = 0;
        double yhssje = 0;
        if(result != null){
            for (Map<String,Object> map:
                    result) {

                if(map.get("bill_con_money") != null) {
                    bill_con_money += Double.parseDouble(map.get("bill_con_money").toString());
                }
                if(map.get("bill_pay_money") != null){
                    bill_pay_money+= Double.parseDouble(map.get("bill_pay_money").toString());
                }

                if(map.get("yhssje") != null){
                    yhssje+= Double.parseDouble(map.get("yhssje").toString());
                }

            }

        }
        Map<String,Object> attacheData = new HashMap<>();
        attacheData.put("bill_con_money", bill_con_money);
        attacheData.put("bill_pay_money", bill_pay_money);
        attacheData.put("yhssje", yhssje);

        pageData.setAttachData(attacheData);

        return pageData;
    }



    /**
     * 导出
     *
     * @param page
     */
    public void exportCompResult(Page page) {
        PageData pageData = getComparisonResult(page);
        Map<String, Object> data = (Map<String, Object>) pageData.getData();
        if (data != null) {
            //dateStr
            String dateStr = page.getParams().get("businesstime").toString();
            String[] dateArray = dateStr.split(" - ");
            //filename
            String filename = dateStr.replace(" ","") +".xls";
            //title
            String title = String.format("自助机账务汇总对账报表 （%s）", dateStr);
            //preview data
            List<Map<String, Object>> previewData = (List<Map<String, Object>>) data.get("preview");
            //统计列标题
            List<Map<String, Object>> cells = (List<Map<String, Object>>) data.get("cols");
            //头部
            List<List<String>> titleHeaderList = new ArrayList<>();
            //第1行
            List<String> l2List = new ArrayList<>();
            l2List.add(ShiroUtils.getUserEntity().getHiId_name());
            titleHeaderList.add(l2List);
            //第2行
            List<String> l3List = new ArrayList<>();
            l3List.add("营业日期");
            l3List.add(dateArray[0]);
            l3List.add("");
            l3List.add("至");
            l3List.add(dateArray[1]);
            l3List.add("");
            titleHeaderList.add(l3List);


            List<CellRangeAddress> regions = new ArrayList<>();
            regions.add(new CellRangeAddress(0, 0, 0, cells.size() - 1));
            regions.add(new CellRangeAddress(1, 1, 0, cells.size() - 1));
            regions.add(new CellRangeAddress(2, 2, 1, 2));
            regions.add(new CellRangeAddress(2, 2, 4, 5));



            //计算消费金额与结算金额
            double bill_all_pay_price = 0;
            double bill_refundable_amount = 0;
            double bill_all_pay_money = 0;
            double bill_deposit = 0;
            double yhssje = 0;
            //得到消费金额和结算金额

            List<Map<String,Object>> datalist = (List<Map<String,Object>>)data.get("preview");

            for (
                    Map<String,Object> mapInfo : datalist) {
                //支付金额
                if (mapInfo.get("bill_all_pay_price") != null) {
                    bill_all_pay_price += Double.parseDouble(mapInfo.get("bill_all_pay_price").toString());
                }
                //消费金额
                if (mapInfo.get("bill_all_pay_money") != null) {
                    bill_all_pay_money += Double.parseDouble(mapInfo.get("bill_all_pay_money").toString());
                }
                //退款金额
                if (mapInfo.get("bill_refundable_amount") != null) {
                    bill_refundable_amount += Double.parseDouble(mapInfo.get("bill_refundable_amount").toString());
                }
                //押金
                if (mapInfo.get("bill_deposit") != null) {
                    bill_deposit += Double.parseDouble(mapInfo.get("bill_deposit").toString());
                }
                //银行实收金额
                if (mapInfo.get("yhssje") != null) {
                    yhssje += Double.parseDouble(mapInfo.get("yhssje").toString());
                }
            }







            //汇总数据
            List<String> footerList = new ArrayList<>();
            footerList.add("合计：");
            footerList.add(previewData.size() + "");
            footerList.add("");
            footerList.add("");
            footerList.add("支付金额：");
            footerList.add(String.format("%.2f", bill_all_pay_price));
            footerList.add("");
            footerList.add("");
            footerList.add("押金：");
            footerList.add(String.format("%.2f", bill_deposit));
            footerList.add("");
            footerList.add("");
            footerList.add("退款金额：");
            footerList.add(String.format("%.2f", bill_refundable_amount));
            footerList.add("");
            footerList.add("");
            footerList.add("消费金额：");
            footerList.add(String.format("%.2f", bill_all_pay_money));
            footerList.add("");
            footerList.add("");
            footerList.add("银行实收金额：");
            footerList.add(String.format("%.2f", yhssje));

            POIUntils.exportWorkBook(titleHeaderList, footerList, (List<Map<String, Object>>) data.get("cols"), regions, previewData, title, filename, response);
        }

    }

    /**
     * 导出
     *
     * @param page
     */
    public void exportCompBillDetailsResult(Page page) {
        PageData pageData = getBillDeatilsComparisonResult(page);
        Map<String, Object> data = (Map<String, Object>) pageData.getData();
        if (data != null) {
            String dateRange = page.getParams().get("businesstime").toString();
            String[] dateArray = dateRange.split(" - ");

            //title
            String title = String.format("自助机账务明细对账报表 （%s）", dateRange);
            //filename
            String filename = dateRange.replace(" ","") +".xls";
            //preview data
            List<Map<String, Object>> previewData = (List<Map<String, Object>>)((Map<String, Object>) pageData.getData()).get("preview");
            //attache data
            Map<String, Object> attachData = (Map<String, Object>)pageData.getAttachData();
            //统计列标题
            List<Map<String, Object>> cells = new ArrayList<>();
            Map<String,Object> item = new HashMap<>();
            item.put("colname", "or_id");
            item.put("colcomments", "预订单");
            cells.add(item);
            item = new HashMap<>();
            item.put("colname", "or_checkin_id");
            item.put("colcomments", "入住单");
            cells.add(item);
            item = new HashMap<>();
            item.put("colname", "or_source");
            item.put("colcomments", "渠道");
            cells.add(item);
            item = new HashMap<>();
            item.put("colname", "or_mode");
            item.put("colcomments", "类型");
            cells.add(item);
            item = new HashMap<>();
            item.put("colname", "ro_id");
            item.put("colcomments", "房间号");
            cells.add(item);
            item = new HashMap<>();
            item.put("colname", "ro_type_name");
            item.put("colcomments", "房型");
            cells.add(item);
            item = new HashMap<>();
            item.put("colname", "or_ci_name");
            item.put("colcomments", "姓名");
            cells.add(item);
            item = new HashMap<>();
            item.put("colname", "bill_typename");
            item.put("colcomments", "账务类型");
            cells.add(item);
            item = new HashMap<>();
            item.put("colname", "bill_pname");
            item.put("colcomments", "账务条目");
            cells.add(item);
            item = new HashMap<>();
            item.put("colname", "bill_con_money");
            item.put("colcomments", "消费金额");
            cells.add(item);
            item = new HashMap<>();
            item.put("colname", "bill_pay_money");
            item.put("colcomments", "结算金额");
            cells.add(item);
            item = new HashMap<>();
            item.put("colname", "yhssje");
            item.put("colcomments", "银行实收金额");
            cells.add(item);
            item = new HashMap<>();
            item.put("colname", "pay_shift_no");
            item.put("colcomments", "原始凭证编号");
            cells.add(item);
            item = new HashMap<>();
            item.put("colname", "bill_comments");
            item.put("colcomments", "备注");
            cells.add(item);
            item = new HashMap<>();
            item.put("colname", "bill_pdate");
            item.put("colcomments", "营业日");
            cells.add(item);
            item = new HashMap<>();
            item.put("colname", "bill_ptime");
            item.put("colcomments", "入账时间");
            cells.add(item);
            //头部
            List<List<String>> titleHeaderList = new ArrayList<>();
            //第1行
            List<String> l2List = new ArrayList<>();
            l2List.add(ShiroUtils.getUserEntity().getHiId_name());
            titleHeaderList.add(l2List);
            //第2行
            List<String> l3List = new ArrayList<>();
            l3List.add("营业日期");
            l3List.add(dateArray[0]);
            l3List.add("");
            l3List.add("至");
            l3List.add(dateArray[1]);
            l3List.add("");
            titleHeaderList.add(l3List);


            List<CellRangeAddress> regions = new ArrayList<>();
            regions.add(new CellRangeAddress(0, 0, 0, cells.size() - 1));
            regions.add(new CellRangeAddress(1, 1, 0, cells.size() - 1));
            regions.add(new CellRangeAddress(2, 2, 1, 2));
            regions.add(new CellRangeAddress(2, 2, 4, 5));



            //汇总数据
            List<String> footerList = new ArrayList<>();
            footerList.add("合计:");
            footerList.add(previewData.size() + "");
            footerList.add("");
            footerList.add("");
            footerList.add("消费：");
            footerList.add(String.format("%.2f", attachData.get("bill_con_money")));
            footerList.add("");
            footerList.add("");
            footerList.add("结算：");
            footerList.add(String.format("%.2f", attachData.get("bill_pay_money")));
            footerList.add("");
            footerList.add("");
            footerList.add("银行实收金额：");
            footerList.add(String.format("%.2f", attachData.get("yhssje")));
            POIUntils.exportWorkBook(titleHeaderList, footerList, cells, regions, previewData, title, filename, response);

        }

    }


}
