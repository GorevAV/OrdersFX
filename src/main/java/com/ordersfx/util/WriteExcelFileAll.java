package com.ordersfx.util;

import com.ordersfx.service.PurchaseOrder;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class WriteExcelFileAll {

    private final List<String> HEADERS = List.of(
            "Дата", "Номер заявки", "Организация", "Площадка", "Ответственный", "Номенклатура", "КР",
            "Количество", "Единица измерения", "Контрагент", "Ответственный менеджер", "Дата ПП", "ПТУ", "Дата ПТУ",
            "Заказ поставщику", "Комментарий", "Категория");

    public void writeExcelFile(Set<PurchaseOrder> purchaseOrders, String filePath, String name) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(name);
        int rowNum = 1;
        writeFirstRow(sheet);
        for (PurchaseOrder purchaseOrder : purchaseOrders) {
            Row row = sheet.createRow(rowNum++);
            writePurchaseOrder(row, purchaseOrder);
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

    private void writePurchaseOrder(Row row, PurchaseOrder purchaseOrder) {
        Cell cell = row.createCell(0);
        cell.setCellValue(purchaseOrder.getDate());

        cell = row.createCell(1);
        cell.setCellValue(purchaseOrder.getNumberApplication());

        cell = row.createCell(2);
        cell.setCellValue(purchaseOrder.getOrganisation());

        cell = row.createCell(3);
        cell.setCellValue(purchaseOrder.getSquare());

        cell = row.createCell(4);
        cell.setCellValue(purchaseOrder.getCustomer());

        cell = row.createCell(5);
        cell.setCellValue(purchaseOrder.getItemName());

        cell = row.createCell(6);
        cell.setCellValue(purchaseOrder.getKr());

        cell = row.createCell(7, CellType.NUMERIC);
        cell.setCellValue(purchaseOrder.getCount());

        cell = row.createCell(8);
        cell.setCellValue(purchaseOrder.getUnit());

        cell = row.createCell(9);
        cell.setCellValue(purchaseOrder.getProvider());

        cell = row.createCell(10);
        cell.setCellValue(purchaseOrder.getManager());

        cell = row.createCell(11);
        cell.setCellValue(purchaseOrder.getDatePP());

        cell = row.createCell(12);
        cell.setCellValue(purchaseOrder.getPty());

        cell = row.createCell(13);
        cell.setCellValue(purchaseOrder.getDatePty());

        cell = row.createCell(14);
        cell.setCellValue(purchaseOrder.getOrder());

        cell = row.createCell(15);
        cell.setCellValue(purchaseOrder.getComment());

        cell = row.createCell(16);
        cell.setCellValue(purchaseOrder.getCategory());
    }

    public List<String> getHEADERS() {
        return HEADERS;
    }
}
