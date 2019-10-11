package com.fpx.pds.excel.batch;

import com.fpx.pds.utils.TimeUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

/**
 * @Author: cuiwy
 * @Date: 2019/8/13 14:51
 * @version: v1.0.0
 */
public class POIController {
    /**
     * 这种方式效率高一些，属于一次性写入，数据量越大越明显，4万条数据要17秒左右
     *
     * @param args
     * @throws FileNotFoundException
     * @throws InvalidFormatException
     */
    public static void main(String[] args) throws FileNotFoundException, InvalidFormatException {
        long startTime = System.currentTimeMillis();
        BufferedOutputStream outPutStream = null;
        XSSFWorkbook workbook = null;
        FileInputStream inputStream = null;
        String filePath = "E:\\111.xlsx";
        try {
            workbook = getWorkBook(filePath);
            XSSFSheet sheet = workbook.getSheetAt(0);
            for (int i = 0; i < 40; i++) {
                for (int z = 0; z < 1000; z++) {
                    XSSFRow row = sheet.createRow(i * 1000 + z);
                    for (int j = 0; j < 10; j++) {
                        row.createCell(j).setCellValue("你好：" + j);
                    }
                }
            }
            //每次要获取新的文件流对象，避免将之前写入的数据覆盖掉
            outPutStream = new BufferedOutputStream(new FileOutputStream(filePath));
            workbook.write(outPutStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outPutStream != null) {
                try {
                    outPutStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("耗时：" + TimeUtil.comsumeTime(endTime - startTime));
    }

    /**
     * 先创建一个XSSFWorkbook对象
     *
     * @param filePath
     * @return
     */
    public static XSSFWorkbook getWorkBook(String filePath) {
        XSSFWorkbook workbook = null;
        try {
            File fileXlsxPath = new File(filePath);
            BufferedOutputStream outPutStream = new BufferedOutputStream(new FileOutputStream(fileXlsxPath));
            workbook = new XSSFWorkbook();
            workbook.createSheet("测试");
            workbook.write(outPutStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workbook;
    }
}
