package com.siping.smartone.common;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

/**
 * fileName title标题 workSheet sheet名称 startRowIndex 启始行数 startColIndex 列数 list
 * 数据源 colName 对应数据库中列字段
 * @author zengt
 */
@SuppressWarnings("deprecation")
public class ExportExcel {
    public static void exportToExcel(String titleName, String fileName, String workSheet, List<Map<String, Object>> list, HttpServletResponse response, String[] colName, String[] colRelName) {
        // 1.创建一个 workbook
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 2.创建一个 worksheet
        HSSFSheet worksheet = workbook.createSheet(workSheet);
        // 4.创建title,data,headers
        buildWhConclusion(titleName, worksheet, 0, colName.length, colRelName);
        // 5.填充数据
        ExportExcel.fillWhConclusionSum(worksheet, 0, colName.length, list, colName);
        // 6.设置reponse参数
        // 进行转码，使其支持中文文件名
        try {
            fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("Content-Disposition", "inline; filename=" + fileName + ".xls");
        // 确保发送的当前文本格式
        response.setContentType("application/vnd.ms-excel");

        // 7. 输出流
        ExportExcel.write(response, worksheet);
    }

    private static void fillWhConclusionSum(HSSFSheet worksheet, int startRowIndex, int startColIndex, List<Map<String, Object>> datasource, String[] colName) {

        // Row offset
        startRowIndex += 2;

        // Create cell style for the body
        HSSFCellStyle bodyCellStyle = worksheet.getWorkbook().createCellStyle();
        bodyCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        bodyCellStyle.setWrapText(false); // 是否自动换行.
        bodyCellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        bodyCellStyle.setBorderRight(CellStyle.BORDER_THIN);
        bodyCellStyle.setBorderTop(CellStyle.BORDER_THIN);
        bodyCellStyle.setBorderBottom(CellStyle.BORDER_THIN);

        // Create body
        for (int i = startRowIndex; i + startRowIndex - 2 < datasource.size() + 2; i++) {
            // Create a new row
            HSSFRow row = worksheet.createRow((short) i + 1);
            Map dataMap = datasource.get(i - 2);
            for (int j = 0; j < colName.length; j++) {
                HSSFCell cellNum = row.createCell(j);
                if (null == dataMap.get(colName[j]) || "".equals(dataMap.get(colName[j]))) {
                    cellNum.setCellValue("");
                } else {
                    cellNum.setCellValue(dataMap.get(colName[j]).toString());
                }
                cellNum.setCellStyle(bodyCellStyle);
            }
        }
    }

    public static void buildWhConclusion(String titleName, HSSFSheet worksheet, int startRowIndex, int startColIndex, String[] colRelName) {
        // 设置列数和列的宽度
        for (int i = 0; i < startColIndex; i++) {
            worksheet.setColumnWidth(i, 5000);
        }
        buildWhConclusionTitle(worksheet, startRowIndex, startColIndex, colRelName);
        buildWhConclusionHeaders(titleName, worksheet, startRowIndex, startColIndex);

    }

    private static void buildWhConclusionTitle(HSSFSheet worksheet, int startRowIndex, int startColIndex, String[] colRelName) {
        // Header字体
        Font font = worksheet.getWorkbook().createFont();

        font.setBoldweight(Font.BOLDWEIGHT_BOLD);

        // 单元格样式
        HSSFCellStyle headerCellStyle = worksheet.getWorkbook().createCellStyle();
        headerCellStyle.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
        headerCellStyle.setFillPattern(CellStyle.NO_FILL);
        headerCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        headerCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        headerCellStyle.setWrapText(true);
        headerCellStyle.setFont((HSSFFont) font);
        headerCellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        headerCellStyle.setBorderRight(CellStyle.BORDER_THIN);
        headerCellStyle.setBorderTop(CellStyle.BORDER_THIN);
        headerCellStyle.setBorderBottom(CellStyle.BORDER_THIN);

        // 创建字段标题
        HSSFRow rowHeader = worksheet.createRow((short) startRowIndex + 2);
        rowHeader.setHeight((short) 500);
        for (int i = 0; i < colRelName.length; i++) {
            HSSFCell cell = rowHeader.createCell(i);
            cell.setCellValue(colRelName[i]);
            cell.setCellStyle(headerCellStyle);
        }
    }

    private static void buildWhConclusionHeaders(String titleName, HSSFSheet worksheet, int startRowIndex, int startColIndex) {
        // 设置报表标题字体
        Font fontTitle = worksheet.getWorkbook().createFont();
        fontTitle.setBoldweight(Font.BOLDWEIGHT_BOLD);
        fontTitle.setFontHeight((short) 280);

        // 标题单元格样式
        HSSFCellStyle cellStyleTitle = worksheet.getWorkbook().createCellStyle();
        cellStyleTitle.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyleTitle.setWrapText(true);
        cellStyleTitle.setFont((HSSFFont) fontTitle);

        // 报表标题
        HSSFRow rowTitle = worksheet.createRow((short) startRowIndex);
        rowTitle.setHeight((short) 500);
        HSSFCell cellTitle = rowTitle.createCell(0);
        cellTitle.setCellValue(titleName);
        cellTitle.setCellStyle(cellStyleTitle);

        // 合并区域内的报告标题
        worksheet.addMergedRegion(new CellRangeAddress(0, 0, 0, startColIndex - 1));

        // Date header
        HSSFRow dateTitle = worksheet.createRow((short) startRowIndex + 1);
        HSSFCell cellDate = dateTitle.createCell(0);
        cellDate.setCellValue("Creation Time: " + DateFormat.getDateTimeInstance());
        worksheet.addMergedRegion(new CellRangeAddress(1, 1, 0, startColIndex - 1));
    }

    private static void write(HttpServletResponse response, HSSFSheet worksheet) {

        try {
            // Retrieve the output stream
            ServletOutputStream outputStream = response.getOutputStream();
            // Write to the output stream
            worksheet.getWorkbook().write(outputStream);
            // 清除缓存
            outputStream.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
