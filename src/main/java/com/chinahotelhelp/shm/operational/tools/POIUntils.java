package com.chinahotelhelp.shm.operational.tools;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * @author Huan.Xia
 * @Title: POIUntils
 * @ProjectName merchant-management
 * @Description: TODO
 * @date 2019-01-1511:41
 */
public class POIUntils {
    public final static String DATE_OUTPUT_PATTERNS = "yyyy-MM-dd HH:mm:ss";
    public final static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
            DATE_OUTPUT_PATTERNS);
    public static String getCellValue(Cell cell) {
        String ret = "";
        if (cell == null) return ret;
        int type = cell.getCellType();
        switch (type) {
            case Cell.CELL_TYPE_BLANK:
                ret = "";
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                ret = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_ERROR:
                ret = null;
                break;
            case Cell.CELL_TYPE_STRING:
                ret = cell.getRichStringCellValue().getString();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                ret = cell.toString();
                break;
            default:
                ret = "";
        }

        return ret; // 有必要自行trim
    }
    /**
     * 判断文件类型
     * @param in
     * @return
     * @throws IOException
     * @throws InvalidFormatException
     */
    public static Workbook workBookAdaper(InputStream in) throws
            IOException, InvalidFormatException {
        if (!in.markSupported()) {
            in = new PushbackInputStream(in, 8);
        }
        if (POIFSFileSystem.hasPOIFSHeader(in)) {
            return new HSSFWorkbook(in);
        }
        if (POIXMLDocument.hasOOXMLHeader(in)) {
            return new XSSFWorkbook(OPCPackage.open(in));
        }
        throw new IllegalArgumentException("文件无法解析！");
    }


    /**
     * 创建工作簿
     * @param headers
     * @param footers
     * @param cells
     * @param regions
     * @param data
     * @param title
     * @return
     */
    public static Workbook createWorkBook(List<List<String>> headers, List<String> footers, List<Map<String,Object>> cells, List<CellRangeAddress> regions,List<Map<String,Object>> data, String title){
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(title);

        //表头列字体样式
        Font boldFont = workbook.createFont(); // 字体样式
        boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);


        //表头样式
        Font titleFont = workbook.createFont(); // 字体样式
        titleFont.setFontHeightInPoints((short) 18);
        titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        HSSFCellStyle titleStyle = workbook.createCellStyle();
        titleStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中
        titleStyle.setAlignment(CellStyle.ALIGN_CENTER);//水平居中
        titleStyle.setFont(titleFont);

        //表头属性样式
        HSSFCellStyle titlePropertiesStyle = workbook.createCellStyle();
        titlePropertiesStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中

        //表尾样式
        HSSFCellStyle footerPropertiesStyle = workbook.createCellStyle();
        footerPropertiesStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中
        footerPropertiesStyle.setFont(boldFont);

        //列样式
        HSSFCellStyle colCellStyle = workbook.createCellStyle();
        colCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        colCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        colCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        colCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        colCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        colCellStyle.setAlignment(CellStyle.ALIGN_CENTER);//水平居中
        colCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中
        colCellStyle.setFont(boldFont);

        //表尾样式
        HSSFCellStyle contentSytle = workbook.createCellStyle();
        contentSytle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        contentSytle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        contentSytle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        contentSytle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        contentSytle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        contentSytle.setAlignment(CellStyle.ALIGN_CENTER);//水平居中
        contentSytle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中

        //表头
        int rowNum =0;
        HSSFRow row = sheet.createRow(rowNum);
        HSSFCell cell = row.createCell(0);
        HSSFRichTextString text = new HSSFRichTextString(title);
        cell.setCellValue(text);
        cell.setCellStyle(titleStyle);

        rowNum++;
        for (List<String> list:
             headers) {
            row = sheet.createRow(rowNum);
            //在excel表中添加表头
            for(int i=0;i<list.size();i++){
                cell = row.createCell(i);
                text = new HSSFRichTextString(list.get(i));
                cell.setCellValue(text);
                cell.setCellStyle(titlePropertiesStyle);
            }
            rowNum++;
        }

        //列头
        if(cells != null && cells.size() > 0){
            row = sheet.createRow(rowNum);
            for(int i=0;i<cells.size();i++){
                cell = row.createCell(i);
                text = new HSSFRichTextString(cells.get(i).get("colcomments").toString());
                cell.setCellValue(text);
                cell.setCellStyle(colCellStyle);

            }
            rowNum++;
        }




        Object val = null;
        //导出数据
        for (Map<String,Object> item : data){
            row = sheet.createRow(rowNum);
            for(int i=0;i<cells.size();i++){
                val = item.get(cells.get(i).get("colname"));
                cell = row.createCell(i);
                text = new HSSFRichTextString(val == null ? "" : val.toString());
                cell.setCellValue(text);
                cell.setCellStyle(contentSytle);

            }
            rowNum++;
        }


        //合并列
        if(regions != null && regions.size() > 0){
            for (CellRangeAddress cellRangeAddress : regions
            ) {
                sheet.addMergedRegion(cellRangeAddress);
            }
        }
        rowNum++;


        //底部信息
        row = sheet.createRow(rowNum);
        for(int i = 0; i < footers.size(); i++){
            cell = row.createCell(i);
            text = new HSSFRichTextString(footers.get(i));
            cell.setCellValue(text);
            cell.setCellStyle(footerPropertiesStyle);

        }


        return workbook;
    }

    /**
     * 导出WorkBook
     * @param headers
     * @param footers
     * @param cells
     * @param regions
     * @param data
     * @param title
     * @param fileName
     * @param response
     */
    public static void exportWorkBook(List<List<String>> headers, List<String> footers, List<Map<String,Object>> cells, List<CellRangeAddress> regions, List<Map<String,Object>> data, String title, String fileName,  HttpServletResponse response){
        try {

            response.setHeader("Content-disposition", "attachment; filename=" + fileName);
            response.setContentType("application/ms-excel");
            //响应到客户端
            OutputStream os = response.getOutputStream();
            createWorkBook(headers,footers, cells, regions,data, title).write(os);
            os.flush();
            os.close();
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }


    public static void exportPDF (List<List<String>> headers, List<String> footers, List<Map<String,Object>> cells, List<CellRangeAddress> regions, List<Map<String,Object>> data, String title, String fileName,  HttpServletResponse response){

    }


}
