package com.ordersfx.util;

import com.ordersfx.service.PurchaseOrder;
import com.ordersfx.service.Stats;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class WriteExcelFileStats {

    private final List<String> HEADERS = List.of(
            "Ответственный менеджер", "Кол-во по полю Заявка", "Кол-во в Заказе Поставщику", "Неотработано");

    public void writeExcelFile(List<Stats> stats, String filePath, String name) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(name);
        int rowNum = 1;
        writeFirstRow(sheet);
        for (Stats stat : stats) {
            Row row = sheet.createRow(rowNum++);
            writeStats(row, stat);
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
        for (int i = 1; i < sheet.getLastRowNum(); i++) {
            for (int j = 0; j < HEADERS.size(); j++) {
                sheet.getRow(i).getCell(j).setCellStyle(style);
            }
        }
    }

    private void writeStats(Row row, Stats stats) {
        Cell cell = row.createCell(0);
        cell.setCellValue(stats.getManager());

        cell = row.createCell(1, CellType.NUMERIC);
        cell.setCellValue(stats.getPurchaseOrders());

        cell = row.createCell(2,  CellType.NUMERIC);
        cell.setCellValue(stats.getOrders());

        cell = row.createCell(3, CellType.NUMERIC);
        cell.setCellValue(stats.getNotPosted());
    }

    public List<String> getHEADERS() {
        return HEADERS;
    }
}
