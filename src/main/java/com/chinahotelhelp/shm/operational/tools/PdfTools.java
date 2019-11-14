package com.chinahotelhelp.shm.operational.tools;

import com.chinahotelhelp.shm.operational.module.sys.entity.PageData;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @className:pdfTools
 * @Description:生成PDF表格
 * @author:wengdajiang
 * @data:2019/1/10
 */
public class PdfTools {
    public void download(HttpServletRequest request, HttpServletResponse response, List<Map> listMap,String createtime,int count) throws Exception {
        //遍历时间区间，获得初始时间和结束时间
        String[] createArr = createtime.split("/");
        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/pdf");
        // 下载文件的默认名称
        String name = createtime.replace("/","-");
        //拼接返回字节流Header
        response.setHeader("Content-Disposition", "attachment;filename="+name+".pdf");
        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font FontChinese = new Font(bfChinese, 5, Font.NORMAL);

        BaseFont bfChinese1 = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font FontChinese1 = new Font(bfChinese1, 10, Font.NORMAL);

        //创建文档
        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        //PDF表头
        PdfPTable titleTop = new PdfPTable(1);
        PdfPCell cell1 = new PdfPCell();
        Paragraph para = new Paragraph("自助机收支明细表("+name+")",FontChinese1);
        //设置该段落为居中显示
        para.setAlignment(1);
        cell1.setPhrase(para);
        cell1.disableBorderSide(15);
        titleTop.addCell(cell1);
        document.add(titleTop);

        //PDF表头
        PdfPTable titleTop2 = new PdfPTable(2);
        PdfPCell cellgroup = new PdfPCell();
        cellgroup.disableBorderSide(15);
        Paragraph paragroup = new Paragraph("酒店名称",FontChinese);
        cellgroup.setPhrase(paragroup);
        titleTop2.addCell(cellgroup);
        document.add(titleTop2);

        PdfPCell cellgroupa= new PdfPCell();
        cellgroupa.disableBorderSide(15);
        Paragraph paragroupa = new Paragraph("酒店名称",FontChinese);
        cellgroupa.setPhrase(paragroupa);
        titleTop2.addCell(cellgroupa);
        document.add(titleTop2);


        //PDF查询条件
        PdfPTable titleTopT = new PdfPTable(13);
        PdfPCell cellgroup1 = new PdfPCell();
        cellgroup1.disableBorderSide(15);
        Paragraph paragroup1 =new Paragraph("营业时间:",FontChinese);
        cellgroup1.setPhrase(paragroup1);
        titleTopT.addCell(cellgroup1);

        PdfPCell cellgroup2 = new PdfPCell();
        cellgroup2.disableBorderSide(15);
        Paragraph paragroup2 =new Paragraph("",FontChinese);
        cellgroup2.setPhrase(paragroup2);
        titleTopT.addCell(cellgroup2);

        PdfPCell cellgroup3 = new PdfPCell();
        cellgroup3.disableBorderSide(15);
        Paragraph paragroup3 =new Paragraph(createArr[0],FontChinese);
        cellgroup3.setPhrase(paragroup3);
        titleTopT.addCell(cellgroup3);


        PdfPCell cellgroup4 = new PdfPCell();
        cellgroup4.disableBorderSide(15);
        Paragraph paragroup4 =new Paragraph("至",FontChinese);
        cellgroup4.setPhrase(paragroup4);
        titleTopT.addCell(cellgroup4);


        PdfPCell cellgroup5 = new PdfPCell();
        cellgroup5.disableBorderSide(15);
        Paragraph paragroup5 =new Paragraph(createArr[1],FontChinese);
        cellgroup5.setPhrase(paragroup5);
        titleTopT.addCell(cellgroup5);


        PdfPCell cellgroup6 = new PdfPCell();
        cellgroup6.disableBorderSide(15);
        Paragraph paragroup6 =new Paragraph("",FontChinese);
        cellgroup6.setPhrase(paragroup6);
        titleTopT.addCell(cellgroup6);

        PdfPCell cellgroup7 = new PdfPCell();
        cellgroup7.disableBorderSide(15);
        Paragraph paragroup7 =new Paragraph("",FontChinese);
        cellgroup7.setPhrase(paragroup7);
        titleTopT.addCell(cellgroup7);


        PdfPCell cellgroup8 = new PdfPCell();
        cellgroup8.disableBorderSide(15);
        Paragraph paragroup8 =new Paragraph("支付方式:",FontChinese);
        cellgroup8.setPhrase(paragroup8);
        titleTopT.addCell(cellgroup8);


        PdfPCell cellgroup9 = new PdfPCell();
        cellgroup9.disableBorderSide(15);
        Paragraph paragroup9 =new Paragraph("所有",FontChinese);
        cellgroup9.setPhrase(paragroup9);
        titleTopT.addCell(cellgroup9);


        PdfPCell cellgroup10 = new PdfPCell();
        cellgroup10.disableBorderSide(15);
        Paragraph paragroup10 =new Paragraph("",FontChinese);
        cellgroup10.setPhrase(paragroup10);
        titleTopT.addCell(cellgroup10);


        PdfPCell cellgroup11 = new PdfPCell();
        cellgroup11.disableBorderSide(15);
        Paragraph paragroup11 =new Paragraph("",FontChinese);
        cellgroup11.setPhrase(paragroup11);
        titleTopT.addCell(cellgroup11);


        PdfPCell cellgroup12 = new PdfPCell();
        cellgroup12.disableBorderSide(15);
        Paragraph paragroup12 =new Paragraph("",FontChinese);
        cellgroup12.setPhrase(paragroup12);
        titleTopT.addCell(cellgroup12);



        PdfPCell cellgroup13 = new PdfPCell();
        cellgroup13.disableBorderSide(15);
        Paragraph paragroup13 =new Paragraph("",FontChinese);
        cellgroup13.setPhrase(paragroup13);
        titleTopT.addCell(cellgroup13);
        document.add(titleTopT);


        //pdf表头
        PdfPTable title = new PdfPTable(21);
        title.addCell(new Paragraph("预订单号",FontChinese));
        title.addCell(new Paragraph("入住单号",FontChinese));
        title.addCell(new Paragraph("类型",FontChinese));
        title.addCell(new Paragraph("渠道/来源",FontChinese));
        title.addCell(new Paragraph("状态",FontChinese));
        title.addCell(new Paragraph("房间号",FontChinese));
        title.addCell(new Paragraph("房型",FontChinese));
        title.addCell(new Paragraph("姓名",FontChinese));
        title.addCell(new Paragraph("入住时间",FontChinese));
        title.addCell(new Paragraph("退房时间",FontChinese));
        title.addCell(new Paragraph("实住天数",FontChinese));
        title.addCell(new Paragraph("预付金额",FontChinese));
        title.addCell(new Paragraph("押金",FontChinese));
        title.addCell(new Paragraph("房费",FontChinese));
        title.addCell(new Paragraph("消费品",FontChinese));
        title.addCell(new Paragraph("其它",FontChinese));
        title.addCell(new Paragraph("退款金额",FontChinese));
        title.addCell(new Paragraph("结算金额",FontChinese));
        title.addCell(new Paragraph("预付方式",FontChinese));
        title.addCell(new Paragraph("预付交易类型",FontChinese));
        title.addCell(new Paragraph("预付完成时间",FontChinese));
        document.add(title);


        //获得PDF表格数据
        for (Map map : listMap) {
            PdfPTable table = new PdfPTable(21);
            PdfPCell cell = new PdfPCell();
            cell.setPhrase(new Paragraph(map.get("or_id").toString(),FontChinese));
            table.addCell(cell);
            document.add(table);

            cell = new PdfPCell();
            cell.setPhrase(new Paragraph(map.get("or_checkin_id").toString(),FontChinese));
            table.addCell(cell);
            document.add(table);

            cell = new PdfPCell();
            cell.setPhrase(new Paragraph(map.get("or_mode").toString(),FontChinese));
            table.addCell(cell);
            document.add(table);


            cell = new PdfPCell();
            cell.setPhrase(new Paragraph(map.get("or_source").toString(),FontChinese));
            table.addCell(cell);
            document.add(table);

            cell = new PdfPCell();
            cell.setPhrase(new Paragraph(map.get("or_status").toString(),FontChinese));
            table.addCell(cell);
            document.add(table);

            cell = new PdfPCell();
            cell.setPhrase(new Paragraph(map.get("ro_id").toString(),FontChinese));
            table.addCell(cell);
            document.add(table);


            cell = new PdfPCell();
            cell.setPhrase(new Paragraph(map.get("ro_type_name").toString(),FontChinese));
            table.addCell(cell);
            document.add(table);

            cell = new PdfPCell();
            cell.setPhrase(new Paragraph(map.get("or_ci_name").toString(),FontChinese));
            table.addCell(cell);
            document.add(table);

            cell = new PdfPCell();
            cell.setPhrase(new Paragraph(map.get("or_act_arr_time").toString(),FontChinese));
            table.addCell(cell);
            document.add(table);

            cell = new PdfPCell();
            cell.setPhrase(new Paragraph(map.get("or_act_dep_time").toString(),FontChinese));
            table.addCell(cell);
            document.add(table);

            cell = new PdfPCell();
            cell.setPhrase(new Paragraph(map.get("or_act_day").toString(),FontChinese));
            table.addCell(cell);
            document.add(table);

            cell = new PdfPCell();
            cell.setPhrase(new Paragraph(map.get("bill_prepay").toString(),FontChinese));
            table.addCell(cell);
            document.add(table);

            cell = new PdfPCell();
            cell.setPhrase(new Paragraph(map.get("bill_deposit").toString(),FontChinese));
            table.addCell(cell);
            document.add(table);

            cell = new PdfPCell();
            cell.setPhrase(new Paragraph(map.get("bill_all_ro_price").toString(),FontChinese));
            table.addCell(cell);
            document.add(table);


            cell = new PdfPCell();
            cell.setPhrase(new Paragraph(map.get("bill_all_con_price").toString(),FontChinese));
            table.addCell(cell);
            document.add(table);

            cell = new PdfPCell();
            cell.setPhrase(new Paragraph(map.get("bill_all_other_price").toString(),FontChinese));
            table.addCell(cell);
            document.add(table);



            cell = new PdfPCell();
            cell.setPhrase(new Paragraph(map.get("bill_refundable_amount").toString(),FontChinese));
            table.addCell(cell);
            document.add(table);

            cell = new PdfPCell();
            cell.setPhrase(new Paragraph(map.get("bill_all_pay_money").toString(),FontChinese));
            table.addCell(cell);
            document.add(table);

            cell = new PdfPCell();
            cell.setPhrase(new Paragraph(map.get("pay_paytype").toString(),FontChinese));
            table.addCell(cell);
            document.add(table);

            cell = new PdfPCell();
            cell.setPhrase(new Paragraph(map.get("pay_trans_type").toString(),FontChinese));
            table.addCell(cell);
            document.add(table);

            cell = new PdfPCell();
            cell.setPhrase(new Paragraph(map.get("pay_trans_datetime").toString(),FontChinese));
            table.addCell(cell);
            document.add(table);

        }
        //获得统计数据
        CommonMethod commonMethod = new CommonMethod();
        PageData pageData = commonMethod.getPageData(listMap);

        //创建一个Table
        PdfPTable titleTopload = new PdfPTable(14);
        PdfPCell cellgroupa1 = new PdfPCell();
        //15所有边框取消
        cellgroupa1.disableBorderSide(15);
        Paragraph paragroupa1 =new Paragraph("总计条数:",FontChinese);
        cellgroupa1.setPhrase(paragroupa1);
        titleTopload.addCell(cellgroupa1);

        PdfPCell cellgroupa2 = new PdfPCell();
        cellgroupa2.disableBorderSide(15);
        Paragraph paragroupa2 =new Paragraph(String.valueOf(count),FontChinese);
        cellgroupa2.setPhrase(paragroupa2);
        titleTopload.addCell(cellgroupa2);

        PdfPCell cellgroupa3 = new PdfPCell();
        cellgroupa3.disableBorderSide(15);
        Paragraph paragroupa3 =new Paragraph("",FontChinese);
        cellgroupa3.setPhrase(paragroupa3);
        titleTopload.addCell(cellgroupa3);


        PdfPCell cellgroupa4 = new PdfPCell();
        cellgroupa4.disableBorderSide(15);
        Paragraph paragroupa4 =new Paragraph("预付金额:",FontChinese);
        cellgroupa4.setPhrase(paragroupa4);
        titleTopload.addCell(cellgroupa4);


        PdfPCell cellgroupa5 = new PdfPCell();
        cellgroupa5.disableBorderSide(15);
        Paragraph paragroupa5 =new Paragraph(String.valueOf(1),FontChinese);
        cellgroupa5.setPhrase(paragroupa5);
        titleTopload.addCell(cellgroupa5);


        PdfPCell cellgroupa6 = new PdfPCell();
        cellgroupa6.disableBorderSide(15);
        Paragraph paragroupa6 =new Paragraph("",FontChinese);
        cellgroupa6.setPhrase(paragroupa6);
        titleTopload.addCell(cellgroupa6);

        PdfPCell cellgroupa7 = new PdfPCell();
        cellgroupa7.disableBorderSide(15);
        Paragraph paragroupa7 =new Paragraph("消费金额:",FontChinese);
        cellgroupa7.setPhrase(paragroupa7);
        titleTopload.addCell(cellgroupa7);


        PdfPCell cellgroupa8 = new PdfPCell();
        cellgroupa8.disableBorderSide(15);
        Paragraph paragroupa8 =new Paragraph(String.valueOf(1),FontChinese);
        cellgroupa8.setPhrase(paragroupa8);
        titleTopload.addCell(cellgroupa8);

        PdfPCell cellgroupa9 = new PdfPCell();
        cellgroupa9.disableBorderSide(15);
        Paragraph paragroupa9 =new Paragraph("",FontChinese);
        cellgroupa9.setPhrase(paragroupa9);
        titleTopload.addCell(cellgroupa9);

        PdfPCell cellgroupa10 = new PdfPCell();
        cellgroupa10.disableBorderSide(15);
        Paragraph paragroupa10 =new Paragraph("退款金额:",FontChinese);
        cellgroupa10.setPhrase(paragroupa10);
        titleTopload.addCell(cellgroupa10);

        PdfPCell cellgroupa11 = new PdfPCell();
        cellgroupa11.disableBorderSide(15);
        Paragraph paragroupa11 =new Paragraph(String.valueOf(1),FontChinese);
        cellgroupa11.setPhrase(paragroupa11);
        titleTopload.addCell(cellgroupa11);

        PdfPCell cellgroupa12 = new PdfPCell();
        cellgroupa12.disableBorderSide(15);
        Paragraph paragroupa12 =new Paragraph("",FontChinese);
        cellgroupa12.setPhrase(paragroupa12);
        titleTopload.addCell(cellgroupa12);

        PdfPCell cellgroupa13 = new PdfPCell();
        cellgroupa13.disableBorderSide(15);
        Paragraph paragroupa13 =new Paragraph("结算金额:",FontChinese);
        cellgroupa13.setPhrase(paragroupa13);
        titleTopload.addCell(cellgroupa13);

        PdfPCell cellgroupa14 = new PdfPCell();
        cellgroupa14.disableBorderSide(15);
        Paragraph paragroupa14 =new Paragraph(String.valueOf(1),FontChinese);
        cellgroupa14.setPhrase(paragroupa14);
        titleTopload.addCell(cellgroupa14);
        document.add(titleTopload);
        document.close();
    }
}
