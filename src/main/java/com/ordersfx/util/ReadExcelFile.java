package com.ordersfx.util;

import com.ordersfx.service.PurchaseOrder;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ReadExcelFile {


    public ReadExcelFile() {
    }

    public List<PurchaseOrder> readExcel(String path) throws IOException {
        List<PurchaseOrder> purchaseOrders = new ArrayList<>();
        Map<String, Integer> requiredHeaders = new HashMap<>();
        FileInputStream file = new FileInputStream(path);
        Workbook workbook = new XSSFWorkbook(file);
        DataFormatter formatter = new DataFormatter();
        Sheet sheet = workbook.getSheetAt(0);
        for (Cell cell : sheet.getRow(0)) {

            if (cell.getCellType() == CellType.BLANK) {
                requiredHeaders.put(formatter.formatCellValue(cell), cell.getColumnIndex());
            }
            if (cell.getCellType() == CellType.ERROR) {
                if (formatter.formatCellValue(cell).equals("#NULL!")) {
                    requiredHeaders.put("cell.getStringCellValue()", cell.getColumnIndex());
                } else {
                    requiredHeaders.put(formatter.formatCellValue(cell), cell.getColumnIndex());
                }
            }
            if (cell.getCellType() == CellType.STRING) {
                requiredHeaders.put(cell.getStringCellValue(), cell.getColumnIndex());
            }
            //requiredHeaders.put(cell.getStringCellValue(), cell.getColumnIndex());
        }

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            PurchaseOrder purchaseOrder = new PurchaseOrder(
                    formatter.formatCellValue(row.getCell(requiredHeaders.get("Дата"))),
                    formatter.formatCellValue(row.getCell(requiredHeaders.get("Номер заявки"))),
                    formatter.formatCellValue(row.getCell(requiredHeaders.get("Организация"))),
                    formatter.formatCellValue(row.getCell(requiredHeaders.get("Площадка"))),
                    formatter.formatCellValue(row.getCell(requiredHeaders.get("Ответственный"))),
                    formatter.formatCellValue(row.getCell(requiredHeaders.get("Номенклатура"))),
                    formatter.formatCellValue(row.getCell(requiredHeaders.get("КР"))),
                    formatter.formatCellValue(row.getCell(requiredHeaders.get("Количество"))),
                    formatter.formatCellValue(row.getCell(requiredHeaders.get("Единица измерения"))),
                    formatter.formatCellValue(row.getCell(requiredHeaders.get("Контрагент"))),
                    formatter.formatCellValue(row.getCell(requiredHeaders.get("Ответственный менеджер"))),
                    formatter.formatCellValue(row.getCell(requiredHeaders.get("Дата ПП"))),
                    formatter.formatCellValue(row.getCell(requiredHeaders.get("ПТУ"))),
                    formatter.formatCellValue(row.getCell(requiredHeaders.get("Дата ПТУ"))),
                    formatter.formatCellValue(row.getCell(requiredHeaders.get("Заказ поставщику"))),
                    formatter.formatCellValue(row.getCell(requiredHeaders.get("Категория"))),
                    formatter.formatCellValue(row.getCell(requiredHeaders.get("Комментарий")))
            );
            if (purchaseOrder.getDatePP().equals("#NULL!")) {
                purchaseOrder.setDatePP("");
            }
            if (purchaseOrder.getOrder().equals("#NULL!")) {
                purchaseOrder.setOrder("");
            }
            if (purchaseOrder.getProvider().equals("#NULL!")) {
                purchaseOrder.setProvider("");
            }
            if (purchaseOrder.getPty().equals("#NULL!")) {
                purchaseOrder.setPty("");
            }
            if (purchaseOrder.getManager().equals("#NULL!")) {
                purchaseOrder.setManager("");
            }
            if (purchaseOrder.getDatePty().equals("#NULL!")) {
                purchaseOrder.setDatePty("");
            }
            if (purchaseOrder.getComment().equals("#NULL!")) {
                purchaseOrder.setComment("");
            }

            purchaseOrders.add(purchaseOrder);
        }
        workbook.close();
        return purchaseOrders;
    }

}