package com.chinahotelhelp.shm.operational.tools;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;

/**
 * @className:SetStyleUtils
 * @Description:TODO
 * @author:wengdajiang
 * @data:2019/1/11
 */
public class SetStyleUtils {
    /**
     * 无背景颜色普通字体
     *
     * @param workbook
     * @return
     */
    public static HSSFCellStyle setStyleNoColor(HSSFWorkbook workbook) {
        HSSFCellStyle styleNoColor = workbook.createCellStyle();
        styleNoColor.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        styleNoColor.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        styleNoColor.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleNoColor.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleNoColor.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleNoColor.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleNoColor.setWrapText(true);
        return styleNoColor;
    }

    /**
     * LightYellow背景颜色，普通字体
     *
     * @param workbook
     * @return
     */
    public static HSSFCellStyle setStyleLightYellow(HSSFWorkbook workbook) {
        HSSFCellStyle styleLightYellow = workbook.createCellStyle();
        styleLightYellow.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        styleLightYellow.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        styleLightYellow.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleLightYellow.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleLightYellow.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleLightYellow.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleLightYellow.setFillForegroundColor(IndexedColors.LIGHT_YELLOW
                .getIndex());
        styleLightYellow.setFillPattern(CellStyle.SOLID_FOREGROUND);
        styleLightYellow.setWrapText(true);
        return styleLightYellow;
    }

    /**
     * LightYellow背景颜色，加粗字体
     *
     * @param workbook
     * @return
     */
    public static HSSFCellStyle setStyleLightYellowStrong(HSSFWorkbook workbook) {
        HSSFCellStyle styleLightYellowStrong = workbook.createCellStyle();
        styleLightYellowStrong.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        styleLightYellowStrong
                .setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        styleLightYellowStrong.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleLightYellowStrong.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleLightYellowStrong.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleLightYellowStrong.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleLightYellowStrong
                .setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        styleLightYellowStrong.setFillPattern(CellStyle.SOLID_FOREGROUND);
        HSSFFont fontStrong = workbook.createFont();
        fontStrong.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        styleLightYellowStrong.setFont(fontStrong);
        styleLightYellowStrong.setWrapText(true);
        return styleLightYellowStrong;
    }

    /**
     * LightYellow背景颜色，红色字体
     *
     * @param workbook
     * @return
     */
    public static HSSFCellStyle setStyleLightYellowRed(HSSFWorkbook workbook) {
        HSSFCellStyle styleLightYellowRed = workbook.createCellStyle();
        styleLightYellowRed.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        styleLightYellowRed.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        styleLightYellowRed.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleLightYellowRed.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleLightYellowRed.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleLightYellowRed.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleLightYellowRed.setFillForegroundColor(IndexedColors.LIGHT_YELLOW
                .getIndex());
        styleLightYellowRed.setFillPattern(CellStyle.SOLID_FOREGROUND);
        HSSFFont fontRed = workbook.createFont();
        fontRed.setColor(IndexedColors.RED.getIndex());
        styleLightYellowRed.setFont(fontRed);
        styleLightYellowRed.setWrapText(true);
        return styleLightYellowRed;
    }

}
