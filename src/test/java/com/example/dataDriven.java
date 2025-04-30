package com.example;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class dataDriven {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("C://Users//abhinav//Documents/Book1.xlsx");
        try (XSSFWorkbook wb = new XSSFWorkbook(fis)) {
            int sheets = wb.getNumberOfSheets();
            //Commit comment
            System.out.println(sheets);
            // Loop through all sheets in the workbook  
            for (int i = 0; i < sheets; i++) {
                if (wb.getSheetName(i).equalsIgnoreCase("Sheet1")) {
                    int rowCount = wb.getSheetAt(i).getPhysicalNumberOfRows();
                    for (int j = 0; j < rowCount; j++) {
                        String data = wb.getSheetAt(i).getRow(j).getCell(0).getStringCellValue();
                        System.out.println(data);
                    }
                }
            }
        }
    }
}
