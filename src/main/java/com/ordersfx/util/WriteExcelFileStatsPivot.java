package com.ordersfx.util;

import com.ordersfx.service.StatsPivot;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class WriteExcelFileStatsPivot {

    private final List<String> HEADERS = List.of(
            "Площадка", "Всего заявок", "Заказы", "Выполнено", "В пути", "В работе");

    public void writeExcelFile(List<StatsPivot> statsPivots, String filePath, String name) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(name);
        int rowNum = 1;
        writeFirstRow(sheet);
        for (StatsPivot statsPivot : statsPivots) {
            Row row = sheet.createRow(rowNum++);
            writeStatsPivot(row, statsPivot);
        }

        autoResizeColumnWidth(sheet);
        bordersDrawn(sheet);

        try {
            FileOutputStream outputStream = new FileOutputStream(filePath);
            workbook.write(outputStream);
            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeFirstRow(Sheet sheet) {
        Row row = sheet.createRow(0);
        for (int i = 0; i < HEADERS.size(); i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(HEADERS.get(i));
        }
    }

    private void autoResizeColumnWidth(Sheet sheet) {
        for (int i = 0; i < HEADERS.size(); i++) {
            sheet.autoSizeColumn(i);
        }
    }

    private void bordersDrawn(Sheet sheet) {
        CellStyle style = sheet.getWorkbook().createCellStyle();
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        for (int i = 0; i < HEADERS.size(); i++) {
            sheet.getRow(0).getCell(i).setCellStyle(style);
        }
        for (int i = 1; i < sheet.getLastRowNum()+1; i++) {
            for (int j = 0; j < HEADERS.size(); j++) {
                sheet.getRow(i).getCell(j).setCellStyle(style);
            }
        }
    }

    private void writeStatsPivot(Row row, StatsPivot statsPivot) {
        Cell cell = row.createCell(0);
        cell.setCellValue(statsPivot.getSquare());

        cell = row.createCell(1, CellType.NUMERIC);
        cell.setCellValue(statsPivot.getPurchaseOrders());

        cell = row.createCell(2,  CellType.NUMERIC);
        cell.setCellValue(statsPivot.getOrders());

        cell = row.createCell(3, CellType.NUMERIC);
        cell.setCellValue(statsPivot.getCompletedOrders());

        cell = row.createCell(4, CellType.NUMERIC);
        cell.setCellValue(statsPivot.getGoing());

        cell = row.createCell(5, CellType.NUMERIC);
        cell.setCellValue(statsPivot.getInWork());
    }

    public List<String> getHEADERS() {
        return HEADERS;
    }
}
