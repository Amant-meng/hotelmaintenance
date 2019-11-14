package com.chinahotelhelp.shm.operational.tools;

/**
 * @className:Param
 * @Description:TODO
 * @author:wengdajiang
 * @data:2019/1/8
 */
public class Param {
    //资源文件路径
    private String resourceDir;
    //字体文件名称
    private String fontName;
    //FTL模板文件名称
    private String ftlName;
    //FTL模板文件字符集
    private String charset;
    //输出PDF文件完整路径
    private String outputDir;

    public Param(String resourceDir, String fontName, String ftlName, String charset, String outputDir) {
        this.resourceDir = resourceDir;
        this.fontName = fontName;
        this.ftlName = ftlName;
        this.charset = charset;
        this.outputDir = outputDir;
    }

    public String getResourceDir() {
        return resourceDir;
    }

    public String getFontName() {
        return fontName;
    }

    public String getFtlName() {
        return ftlName;
    }

    public String getCharset() {
        return charset;
    }

    public String getOutputDir() {
        return outputDir;
    }

    public String getFontPath() {
        return resourceDir + fontName;
    }

    public String getFtlPath() {
        return resourceDir + ftlName;
    }

    public String getImagePath() {
        return "file:/" + resourceDir;
    }

}
