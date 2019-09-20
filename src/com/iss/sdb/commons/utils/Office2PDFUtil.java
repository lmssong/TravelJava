package com.iss.sdb.commons.utils;

import java.io.File;
import java.util.Date;

import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;

/**
 * 将Office文档转换为PDF文档
 * 
 * @author Kingt.W
 * 
 */
public class Office2PDFUtil {

    /**
     * LibOffice安装目录
     */
    // LINUX 版本
    private static String OpenOffice_HOME = "/opt/libreoffice5.2";
    // WINDOWS 版本
//    private static String OpenOffice_HOME = "D:/Program Files/LibreOffice 5";

    /**
     * @param sourceFile
     *            源文件, 绝对路径. 可以是Office2003-2007全部格式的文档, Office2010的没测试. 包括.doc,
     *            .docx, .xls, .xlsx, .ppt, .pptx等. 示例: F:\\office\\source.doc
     * @param destFile
     *            目标文件. 绝对路径. 示例: F:\\pdf\\dest.pdf
     * @return 操作成功与否的提示信息. 如果返回 -1, 表示找不到源文件, 或url.properties配置错误; 如果返回 0,
     *         则表示操作成功; 返回1, 则表示转换失败
     */
    public static String office2PDF(String sourceFile, String destFile) {
        File inputFile = new File(sourceFile);
        // File inputFile = new File("d:/gif.gif");
        System.out.println("libreOffice开始转换..............................");
        Long startTime = new Date().getTime();
        // 此类在jodconverter中3版本中存在，在2.2.2版本中不存在
        DefaultOfficeManagerConfiguration configuration = new DefaultOfficeManagerConfiguration();
        // libreOffice的安装目录
        configuration.setOfficeHome(new File(OpenOffice_HOME));
        // 端口号
        configuration.setPortNumber(8100);
        // configuration.setTaskExecutionTimeout(1000 * 60 * 5L);//
        // 设置任务执行超时为5分钟
        // configuration.setTaskQueueTimeout(1000 * 60 * 60 * 24L);//
        // 设置任务队列超时为24小时
        OfficeManager officeManager = configuration.buildOfficeManager();
        officeManager.start();
        System.out.println("...start.....");
        OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);
        File outputFile = new File(destFile);
        converter.convert(inputFile, outputFile);
        // converter.convert(inputFile, stw, outputFile, pdf);
        // 转换结束
        officeManager.stop();
        System.out.println("转换结束。。。。。");
        String pdfPath = outputFile.getPath();
        long endTime = new Date().getTime();
        long time = endTime - startTime;
        System.out.println("libreOffice转换所用时间为：" + time);
        return pdfPath;
    }
}
