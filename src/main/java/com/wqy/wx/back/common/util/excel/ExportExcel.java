package com.wqy.wx.back.common.util.excel;

import com.wqy.wx.back.common.Constant;
import com.wqy.wx.back.common.util.DateUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 成都微趣云网络科技有限公司
 *
 * @ClassName: ExportExcel
 * @Description: TODO
 * @Author licm
 * @Date 2020/4/11 16:42
 * @Version V1.0
 */
public class ExportExcel {
    /***
     * 构造方法
     */
    public ExportExcel() {

    }

    /***
     * 工作簿
     */
    private static HSSFWorkbook workbook;

    /***
     * sheet
     */
    private static HSSFSheet sheet;
    /***
     * 标题行开始位置
     */
    private static final int TITLE_START_POSITION = 0;

    /***
     * 时间行开始位置
     */
    private static final int DATEHEAD_START_POSITION = 1;

    /***
     * 表头行开始位置
     */
    private static final int HEAD_START_POSITION = 2;

    /***
     * 文本行开始位置
     */
    private static final int CONTENT_START_POSITION = 3;


    /**
     * @param dataList  对象集合
     * @param titleMap  表头信息（对象属性名称->要显示的标题值)[按顺序添加]
     * @param sheetName sheet名称和表头值
     */
    public String excelExport(String exclName, List<?> dataList, Map<String, String> titleMap, String sheetName) {
        // 初始化workbook
        initHSSFWorkbook(sheetName);
        // 标题行
        createTitleRow(titleMap, sheetName);
        // 时间行
        createDateHeadRow(titleMap);
        // 表头行
        createHeadRow(titleMap);
        // 文本行
        createContentRow(dataList, titleMap);
        //设置自动伸缩
//        autoSizeColumn(titleMap.size());
        // 写入处理结果
        String path = null;
        try {
            //生成UUID文件名称
            //UUID是指在一台机器上生成的数字，它保证对在同一时空中的所有机器都是唯一的。
            StringBuffer buffer = new StringBuffer();
            buffer.append(exclName).append("_");
            buffer.append(DateUtil.dateToYMDString(new Date()));
            buffer.append(".xlsx");
            path = buffer.toString();
            System.out.println("存入路径：" + Constant.FILE_PATH + path);
            //如果web项目，1、设置下载框的弹出（设置response相关参数)；2、通过httpservletresponse.getOutputStream()获取
            OutputStream out = new FileOutputStream(Constant.FILE_PATH + path);
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }
    /**
     * @param exclName  文件名
     * @param list      导出对象
     */
    public String excelExport2(String exclName,List<ExclDto> list) {
        if(CollectionUtils.isNotEmpty(list)){
            workbook = new HSSFWorkbook();
            for (ExclDto exclDto : list){
                // 初始化workbook
                createSheet(exclDto.getSheetName());
                // 标题行
                createTitleRow(exclDto.getTitleMap(), exclDto.getSheetName());
                // 时间行
                createDateHeadRow(exclDto.getTitleMap());
                // 表头行
                createHeadRow(exclDto.getTitleMap());
                // 文本行
                createContentRow(exclDto.getDataList(), exclDto.getTitleMap());
                //设置自动伸缩
//        autoSizeColumn(titleMap.size());
            }
            // 写入处理结果
            String path = null;
            try {
                //生成UUID文件名称
                //UUID是指在一台机器上生成的数字，它保证对在同一时空中的所有机器都是唯一的。
                StringBuffer buffer = new StringBuffer();
                buffer.append(exclName).append("_");
                buffer.append(DateUtil.dateToYMString(new Date()));
                buffer.append(".xlsx");
                path = buffer.toString();
                System.out.println("存入路径：" + Constant.FILE_PATH + path);
                //如果web项目，1、设置下载框的弹出（设置response相关参数)；2、通过httpservletresponse.getOutputStream()获取
                OutputStream out = new FileOutputStream(Constant.FILE_PATH + path);
                workbook.write(out);
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return path;
        }
        return null;
    }
    /***
     *
     * @param sheetName
     *        sheetName
     */
    private static void initHSSFWorkbook(String sheetName) {
        workbook = new HSSFWorkbook();
        sheet = workbook.createSheet(sheetName);
    }
    /***
     *
     * @param sheetName
     *        sheetName
     */
    private static void createSheet(String sheetName) {
        sheet = workbook.createSheet(sheetName);
    }
    /**
     * 生成标题（第零行创建）
     *
     * @param titleMap  对象属性名称->表头显示名称
     * @param sheetName sheet名称
     */
    private static void createTitleRow(Map<String, String> titleMap, String sheetName) {
        CellRangeAddress titleRange = new CellRangeAddress(0, 0, 0, titleMap.size() - 1);
        sheet.addMergedRegion(titleRange);
        HSSFRow titleRow = sheet.createRow(TITLE_START_POSITION);
        HSSFCell titleCell = titleRow.createCell(0);
        titleCell.setCellValue(sheetName);
    }

    /**
     * 创建时间行（第一行创建）
     *
     * @param titleMap 对象属性名称->表头显示名称
     */
    private static void createDateHeadRow(Map<String, String> titleMap) {
        CellRangeAddress dateRange = new CellRangeAddress(1, 1, 0, titleMap.size() - 1);
        sheet.addMergedRegion(dateRange);
        HSSFRow dateRow = sheet.createRow(DATEHEAD_START_POSITION);
        HSSFCell dateCell = dateRow.createCell(0);
        dateCell.setCellValue(new SimpleDateFormat("yyyy年MM月dd日").format(new Date()));
    }

    /**
     * 创建表头行（第二行创建）
     *
     * @param titleMap 对象属性名称->表头显示名称
     */
    private static void createHeadRow(Map<String, String> titleMap) {
        // 第1行创建
        HSSFRow headRow = sheet.createRow(HEAD_START_POSITION);
        int i = 0;
        for (String entry : titleMap.keySet()) {
            HSSFCell headCell = headRow.createCell(i);
            headCell.setCellValue(titleMap.get(entry));
            i++;
        }
    }

    /**
     * @param dataList 对象数据集合
     * @param titleMap 表头信息
     */
    private static void createContentRow(List<?> dataList, Map<String, String> titleMap) {
        try {
            int i = 0;
            for (Object obj : dataList) {
                HSSFRow textRow = sheet.createRow(CONTENT_START_POSITION + i);
                int j = 0;
                for (String entry : titleMap.keySet()) {
                    String method = "get" + entry.substring(0, 1).toUpperCase() + entry.substring(1);
                    Method m = obj.getClass().getMethod(method, null);
                    String value = m.invoke(obj, null).toString();
                    HSSFCell textcell = textRow.createCell(j);
                    textcell.setCellValue(value);
                    j++;
                }
                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 自动伸缩列（如非必要，请勿打开此方法，耗内存）
     *
     * @param size 列数
     */
    private static void autoSizeColumn(Integer size) {
        for (int j = 0; j < size; j++) {
            sheet.autoSizeColumn(j);
        }
    }

    public void exportExcel(XSSFWorkbook workbook, int sheetNum,
                            String sheetTitle, String[] headers, List<List<String>> result,
                            OutputStream out) throws Exception {
        // 生成一个表格
        XSSFSheet sheet = workbook.createSheet();
        workbook.setSheetName(sheetNum, sheetTitle);
        // 设置表格默认列宽度为20个字节
        sheet.setDefaultColumnWidth((short) 20);
        // 生成一个样式
        XSSFCellStyle style = workbook.createCellStyle();
        /* 设置这些样式 */
        ;
        style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
//        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
//        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成一个字体
        XSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 12);
        //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);

        // 指定当单元格内容显示不下时自动换行
        style.setWrapText(true);

        // 产生表格标题行
        XSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            XSSFCell cell = row.createCell((short) i);

            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text.toString());
        }
        // 遍历集合数据，产生数据行
        if (result != null) {
            int index = 1;
            for (List<String> m : result) {
                row = sheet.createRow(index);
                int cellIndex = 0;
                for (String str : m) {
                    XSSFCell cell = row.createCell((short) cellIndex);
                    cell.setCellValue(str);
                    cellIndex++;
                }
                index++;
            }
        }
    }
}


