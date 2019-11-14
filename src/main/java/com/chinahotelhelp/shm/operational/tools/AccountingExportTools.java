package com.chinahotelhelp.shm.operational.tools;

import com.chinahotelhelp.shm.operational.module.sys.entity.PageData;
import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @className:AccountingExportTools
 * @Description:TODO
 * @author:wengdajiang
 * @data:2019/1/15
 */
public class AccountingExportTools {
    public static void exportExcel(List<Map> listMap, HttpServletResponse response, String createtime, int count) throws IOException, WriteException {
        //遍历时间区间，获得初始时间和结束时间
        String [] createArr = createtime.split("/");
        //EXCEL表格名，根据查询时间字段组成
        String filename = "";
        filename = createtime.replace("/", "-") + ".xls";//保存的文件名。
        //excel标题，工具查询时间字段组成
        String filenameTitl = createtime.replace("/", "-");//保存的文件名。
        //标题数组
        String[] title = {"预定单号", "入住单号", "类型", "渠道", "房间号", "房型", "姓名", "账务类型", "入账条目", "支付方式","消费金额", "结算金额", "原始凭证编号", "备注","入账时间"};
        //输出流
        ServletOutputStream out = response.getOutputStream();

        try {
            //创建工作簿
            WritableWorkbook workbook = Workbook.createWorkbook(out);
            WritableFont wf_title = new WritableFont(WritableFont.ARIAL, 18,//字体属性
                    WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
                    jxl.format.Colour.BLACK);
            WritableCellFormat wcf_title = new WritableCellFormat(wf_title); // 单元格定义
            wcf_title.setBackground(jxl.format.Colour.WHITE); // 设置单元格的背景颜色
            wcf_title.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式
            wcf_title.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN, jxl.format.Colour.BLACK); //设置边框


            //创建sheet页,0代表索引
            //生成excel列的长度
            WritableSheet sheet = workbook.createSheet("sheet 1", 0);
            sheet.setColumnView(0, 25);
            sheet.setColumnView(1, 25);
            sheet.setColumnView(2, 25);
            sheet.setColumnView(3, 15);
            sheet.setColumnView(4, 25);
            sheet.setColumnView(5, 20);
            sheet.setColumnView(6, 20);
            sheet.setColumnView(7, 20);
            sheet.setColumnView(8, 10);
            sheet.setColumnView(9, 10);
            sheet.setColumnView(10, 25);
            sheet.setColumnView(11, 10);
            sheet.setColumnView(12, 10);
            sheet.setColumnView(12, 10);
            sheet.setColumnView(12, 20);


            //创建总标题
            Label titleContent = new Label(0, 0, "自助机入账明细报表(" + filenameTitl + ")", wcf_title);
            //合并行和列（第一个参数合并的第一个列，第二个参数合并的第一行，第三个参数是合并第几列，第四个参数是合并的第几行）
            sheet.mergeCells(0, 0, 14, 1);
            sheet.addCell(titleContent);


            WritableFont wf_title11 = new WritableFont(WritableFont.ARIAL, 15,
                    WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
                    jxl.format.Colour.BLACK);
            WritableCellFormat wcf_title1 = new WritableCellFormat(wf_title11); // 单元格定义
            wcf_title1.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式
            //创建查询条件
            Label lab1 = new Label(0, 4, "营业日期:", wcf_title1);
            Label lab2 = new Label(1, 4, "", wcf_title1);
            Label lab3 = new Label(2, 4, createArr[0], wcf_title1);
            Label lab4 = new Label(3, 4, "至", wcf_title1);
            Label lab5 = new Label(4, 4, createArr[1], wcf_title1);
            Label lab6 = new Label(5, 4, "支付方式:", wcf_title1);
            Label lab7 = new Label(6, 4, "所有", wcf_title1);
            sheet.mergeCells(0, 4, 0, 5);
            sheet.mergeCells(1, 4, 1, 5);
            sheet.mergeCells(2, 4, 2, 5);
            sheet.mergeCells(3, 4, 3, 5);
            sheet.mergeCells(4, 4, 4, 5);
            sheet.mergeCells(5, 4, 5, 5);
            sheet.mergeCells(6, 4, 6, 5);
            sheet.mergeCells(7, 4, 14, 5);
            sheet.addCell(lab1);
            sheet.addCell(lab2);
            sheet.addCell(lab3);
            sheet.addCell(lab4);
            sheet.addCell(lab5);
            sheet.addCell(lab6);
            sheet.addCell(lab7);

            //创建对应的属性
            WritableFont wfont4 = new WritableFont(WritableFont.ARIAL, 12,
                    WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
                    jxl.format.Colour.BLACK);
            WritableCellFormat titleFormat4 = new WritableCellFormat(wfont4);
            titleFormat4.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);//给单元格加边框          titleFormat3_1.setVerticalAlignment(VerticalAlignment.CENTRE);
            titleFormat4.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式
            Label label = null;
            for (int i = 0; i < title.length; i++) {
                //i代表第几列，0代表第一行。后面是插入的数据
                label = new Label(i, 6, title[i], titleFormat4);
                sheet.addCell(label);
            }

            //创建对应的属性
            WritableFont wfont3 = new WritableFont(WritableFont.ARIAL, 9, WritableFont.NO_BOLD, false,
                    jxl.format.UnderlineStyle.NO_UNDERLINE,
                    jxl.format.Colour.AUTOMATIC);
            WritableCellFormat titleFormat3 = new WritableCellFormat(wfont3);
            titleFormat3.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式
            titleFormat3.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);//给单元格加边框          titleFormat3_1.setVerticalAlignment(VerticalAlignment.CENTRE);

            //插入表格数据
            for (int i = 0; i < listMap.size(); i++) {
                label = new Label(0, i + 7, listMap.get(i).get("or_id").toString(), titleFormat3);
                sheet.addCell(label);
                label = new Label(1, i + 7, listMap.get(i).get("or_checkin_id").toString(), titleFormat3);
                sheet.addCell(label);
                label = new Label(2, i + 7, listMap.get(i).get("or_mode").toString(), titleFormat3);
                sheet.addCell(label);
                label = new Label(3, i + 7, listMap.get(i).get("or_source").toString(), titleFormat3);
                sheet.addCell(label);
                label = new Label(4, i + 7, listMap.get(i).get("ro_id").toString(), titleFormat3);
                sheet.addCell(label);
                label = new Label(5, i + 7, listMap.get(i).get("ro_type_name").toString(), titleFormat3);
                sheet.addCell(label);
                label = new Label(6, i + 7, listMap.get(i).get("or_ci_name").toString(), titleFormat3);
                sheet.addCell(label);
                label = new Label(7, i + 7, listMap.get(i).get("bill_type").toString(), titleFormat3);
                sheet.addCell(label);
                label = new Label(8, i + 7, listMap.get(i).get("bill_pname").toString(), titleFormat3);
                sheet.addCell(label);
                label = new Label(9, i + 7, listMap.get(i).get("pay_paytype").toString(), titleFormat3);
                sheet.addCell(label);
                label = new Label(10, i + 7, listMap.get(i).get("bill_con_money").toString(), titleFormat3);
                sheet.addCell(label);
                label = new Label(11, i + 7, listMap.get(i).get("bill_pay_money").toString(), titleFormat3);
                sheet.addCell(label);
                label = new Label(12, i + 7, listMap.get(i).get("pay_shift_no").toString(), titleFormat3);
                sheet.addCell(label);
                label = new Label(13, i + 7, listMap.get(i).get("bill_comments").toString(), titleFormat3);
                sheet.addCell(label);
                label = new Label(14, i + 7, listMap.get(i).get("bill_ptime").toString(), titleFormat3);
                sheet.addCell(label);
            }

            CommonCount commonCount =  new CommonCount();
            PageData pageData = commonCount.getPageData(listMap);
            Label laba1 = new Label(0, listMap.size() + 10, "总和");
            Label laba2 = new Label(2, listMap.size() + 10, "总计条数:");
            Label laba3 = new Label(3, listMap.size() + 10, String.valueOf(count));
            Label laba4 = new Label(5, listMap.size() + 10, "消费金额:");
            Label laba5 = new Label(6, listMap.size() + 10, String.valueOf(1));
            Label laba6 = new Label(8, listMap.size() + 10, "结算金额:");
            Label laba7 = new Label(9, listMap.size() + 10, String.valueOf(1));
            sheet.addCell(laba1);
            sheet.addCell(laba2);
            sheet.addCell(laba3);
            sheet.addCell(laba4);
            sheet.addCell(laba5);
            sheet.addCell(laba6);
            sheet.addCell(laba7);
            //输出EXCEL名称
            response.setHeader("Content-disposition", "attachment; filename=" + filename);
            response.setContentType("application/ms-excel");
            workbook.write();
            workbook.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
