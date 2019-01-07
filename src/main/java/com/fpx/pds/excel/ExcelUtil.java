/**
 * Copyright (c) 2005-2018. 4PX and/or its affiliates. All rights reserved.
 * Use,Copy is subject to authorized license.
 */
package com.fpx.pds.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: cuiwy
 * @Date: 2018/12/12 16:48
 * @version: v1.0.0
 */
public class ExcelUtil {

    /**
     * @param filePath 文件绝对地址 "F:\\Download\\500002020template.xls"
     * @return
     */
    public static List<Object[]> resolveExcel(String filePath) {
        List<Object[]> returnList = new ArrayList<Object[]>();
        try {
            InputStream instream = new FileInputStream(filePath);
            String fileType = filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length());
            Workbook readwb = null;
            if (fileType.equals("xls")) {
                readwb = new HSSFWorkbook(instream);
            } else if (fileType.equals("xlsx")) {
                readwb = new XSSFWorkbook(instream);
            }
            if (readwb != null) {
                Sheet readsheet = readwb.getSheetAt(0);
                int rsColumns = readsheet.getRow(0).getPhysicalNumberOfCells();
                int rsRows = readsheet.getLastRowNum();
                for (int i = 1; i <= rsRows; i++) {
                    Object[] obj = new Object[rsColumns];
                    Row row = readsheet.getRow(i);
                    if (null == row) continue;
                    Iterator<Cell> iterator = row.cellIterator();
                    while (iterator.hasNext()) {
                        Cell cell = iterator.next();
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                    }
                    //回收内存
                    iterator = null;
                    for (int j = 0; j < rsColumns; j++) {
                        Cell cell = row.getCell(j);
                        obj[j] = cell.getStringCellValue();
                    }
                    returnList.add(obj);
                }
                instream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnList;
    }
}
