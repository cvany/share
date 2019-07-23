package com.fpx.pds.excel.custom;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.assertj.core.util.Lists;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * 交接单自定义模板
 *
 * @Author: cuiwy
 * @Date: 2019/7/9 15:57
 * @version: v1.0.0
 */
@Slf4j
public class HandoverListTemplate {
    public static void main(String[] args) throws IOException {
        Workbook wb = new HSSFWorkbook();//创建工作薄
        Sheet sheet = wb.createSheet();//创建sheet页
        CodeBoardTemplate codeBoardTemplate = new CodeBoardTemplate();
        codeBoardTemplate.setCodeBoard("CNHKGF2019071001");
        codeBoardTemplate.setDestinationSite("4PX-鴨脷洲金發大廈 HK604點");
        codeBoardTemplate.setDistributionWh("元朗中转仓");
        codeBoardTemplate.setFinishTime("2019-07-10 12:12");
        codeBoardTemplate.setParcelNum("1000");
        codeBoardTemplate.setParcelWeight("500");
        //造数据
        List<Parcel> parcels = Lists.newArrayList();
        Parcel parcel = null;
        for (int i = 0; i < 100; i++) {
            parcel = new Parcel("90010710" + i, Double.valueOf(i + ""), (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
            parcels.add(parcel);
        }

        createOneSheetForOneCodeBoard(wb,sheet,codeBoardTemplate,parcels);
        //创建头部
//        createHead(wb, sheet, rowNo, codeBoardTemplate);
//        //创建包裹数据
//        createCcontent(wb, sheet, parcels);


        FileOutputStream out = new FileOutputStream("D://单元格合并.xls");
        wb.write(out);
        out.close();
    }

    /**
     * 一个卡板创建一个sheet
     * （sheet里面进行分页）
     *
     * @param wb
     * @param sheet
     * @param codeBoardTemplate
     * @param list
     */
    public static void createOneSheetForOneCodeBoard(Workbook wb, Sheet sheet,
                                                     CodeBoardTemplate codeBoardTemplate, List<Parcel> list) {
        //分页
        int len = list.size();
        int pageSize;
        if (len % 39 == 0) {
            pageSize = len / 39;
        } else {
            pageSize = len / 39 + 1;
        }

        int count = 0;
        int rowNo = 0;
        for (int page = 0; page < pageSize; page++) {
            //创建头部
            createHead(wb, sheet, rowNo, codeBoardTemplate);
            //设置页码
            Cell cell = sheet.getRow(rowNo).getCell(8);
            cell.setCellValue((page + 1) + "/" + pageSize);
            //开始写包裹数据
            for (rowNo = rowNo + 7; count < len; rowNo++) {
                count++;
                content(wb, sheet, rowNo, list.get(count - 1));
                if (count % 39 == 0) {
                    break;
                }
            }
            ++rowNo;
        }
    }


    /**
     * 一个码板交接单打印到多个sheet里面
     *
     * @param wb
     * @param sheet
     * @param parcels
     */
    private static void createCcontent(Workbook wb, Sheet sheet, List<Parcel> parcels) {
        int len = parcels.size();
        int pageSize;
        if (len % 39 == 0) {
            pageSize = len / 39;
        } else {
            pageSize = len / 39 + 1;
        }
        //设置页码
        List<Sheet> sheetList = Lists.newArrayList();
        Cell cell = sheet.getRow(0).getCell(8);
        cell.setCellValue("1/" + pageSize);
        sheetList.add(sheet);
        Sheet tempSheet = null;
        for (int i = 0; i < pageSize - 1; i++) {
            tempSheet = wb.cloneSheet(0);
            Cell tempCel = tempSheet.getRow(0).getCell(8);
            tempCel.setCellValue((i + 2) + "/" + pageSize);
            sheetList.add(tempSheet);
        }

        //需要处理多个sheet
        int count = 0;
        for (int n = 0; n < sheetList.size(); n++) {
            for (int i = 7; count < len; i++) {
                count++;
                content(wb, sheetList.get(n), i, parcels.get(count - 1));
                if (count % 39 == 0) {
                    break;
                }
            }
        }
    }

    private static void createHead(Workbook wb, Sheet sheet, int rowNo, CodeBoardTemplate codeBoardTemplate) {
        //构建第一层
        titleLevelOne(wb, sheet, rowNo);
        //构建第二层
        titleLevelTwo(wb, sheet, rowNo, codeBoardTemplate.getCodeBoard());
        //构建第三层
        titleLevelThree(wb, sheet, codeBoardTemplate);
        //构建包裹标题层
        packageTitleLevel(wb, sheet);
    }

    private static void content(Workbook wb, Sheet sheet, int rowNo, Parcel parcel) {
        Row row = sheet.createRow(rowNo);
        CellStyle cs01 = wb.createCellStyle();
        cs01.setAlignment(HorizontalAlignment.CENTER);
        cs01.setVerticalAlignment(VerticalAlignment.CENTER);

        Cell c0 = row.createCell(0);
        c0.setCellStyle(cs01);
        c0.setCellValue(parcel.getShippingNo());

        Cell c2 = row.createCell(2);
        c2.setCellStyle(cs01);
        c2.setCellValue(parcel.getWeight());

        Cell c5 = row.createCell(5);
        c5.setCellStyle(cs01);
        c5.setCellValue(parcel.getFinishTime());

        combineCellInRowI(sheet, rowNo);
    }

    private static void combineCellInRowI(Sheet sheet, int rowNo) {
        CellRangeAddress cra01 = new CellRangeAddress(rowNo, rowNo, 0, 1);
        RegionUtil.setBorderLeft(BorderStyle.THIN, cra01, sheet);
        RegionUtil.setBorderBottom(BorderStyle.THIN, cra01, sheet);
        sheet.addMergedRegion(cra01);

        CellRangeAddress cra24 = new CellRangeAddress(rowNo, rowNo, 2, 4);
        RegionUtil.setBorderLeft(BorderStyle.THIN, cra24, sheet);
        RegionUtil.setBorderBottom(BorderStyle.THIN, cra24, sheet);
        sheet.addMergedRegion(cra24);

        CellRangeAddress cra58 = new CellRangeAddress(rowNo, rowNo, 5, 8);
        RegionUtil.setBorderLeft(BorderStyle.THIN, cra58, sheet);
        RegionUtil.setBorderBottom(BorderStyle.THIN, cra58, sheet);
        RegionUtil.setBorderRight(BorderStyle.THIN, cra58, sheet);
        sheet.addMergedRegion(cra58);
    }

    private static void packageTitleLevel(Workbook wb, Sheet sheet) {
        Row row2 = sheet.createRow(6);
        row2.setHeightInPoints(20);

        CellStyle cs01 = createStyleForPackageTitleLevel(wb);

        Cell c0 = row2.createCell(0);
        c0.setCellStyle(cs01);
        c0.setCellValue("4px跟踪号");

        Cell c2 = row2.createCell(2);
        c2.setCellStyle(cs01);
        c2.setCellValue("包裹重量(kg)");

        Cell c5 = row2.createCell(5);
        c5.setCellStyle(cs01);
        c5.setCellValue("码板时间");
        combineCellInRowSix(sheet);
    }

    private static CellStyle createStyleForPackageTitleLevel(Workbook wb) {
        CellStyle cs01 = wb.createCellStyle();
        cs01.setAlignment(HorizontalAlignment.CENTER);
        cs01.setVerticalAlignment(VerticalAlignment.CENTER);
        Font font = wb.createFont();
        font.setFontName("微软雅黑");
        font.setBold(true);
        cs01.setFont(font);
        return cs01;
    }

    private static void combineCellInRowSix(Sheet sheet) {
        CellRangeAddress cra01 = new CellRangeAddress(6, 6, 0, 1);
        RegionUtil.setBorderTop(BorderStyle.THIN, cra01, sheet);
        RegionUtil.setBorderLeft(BorderStyle.THIN, cra01, sheet);
        RegionUtil.setBorderBottom(BorderStyle.THIN, cra01, sheet);
        sheet.addMergedRegion(cra01);

        CellRangeAddress cra24 = new CellRangeAddress(6, 6, 2, 4);
        RegionUtil.setBorderTop(BorderStyle.THIN, cra24, sheet);
        RegionUtil.setBorderBottom(BorderStyle.THIN, cra24, sheet);
        sheet.addMergedRegion(cra24);

        CellRangeAddress cra58 = new CellRangeAddress(6, 6, 5, 8);
        RegionUtil.setBorderTop(BorderStyle.THIN, cra58, sheet);
        RegionUtil.setBorderBottom(BorderStyle.THIN, cra58, sheet);
        RegionUtil.setBorderRight(BorderStyle.THIN, cra58, sheet);
        sheet.addMergedRegion(cra58);
    }

    private static void titleLevelThree(Workbook wb, Sheet sheet, CodeBoardTemplate codeBoardTemplate) {
        Row row2 = sheet.createRow(2);
        row2.setHeightInPoints(20);
        CellStyle cellStyle = wb.createCellStyle();
        CellStyle rightStyle = wb.createCellStyle();
        cellStyle.setBorderLeft(BorderStyle.THIN);
        rightStyle.setBorderRight(BorderStyle.THIN);

        setWarehouseAndSite(codeBoardTemplate, row2, cellStyle, rightStyle);

        setParcelNumAndWeight(sheet, codeBoardTemplate, cellStyle, rightStyle);

        setFinishTime(wb, sheet, codeBoardTemplate, rightStyle);

        combineCellInRowFour(sheet);
    }

    private static void setFinishTime(Workbook wb, Sheet sheet, CodeBoardTemplate codeBoardTemplate, CellStyle rightStyle) {
        Row row4 = sheet.createRow(4);
        row4.setHeightInPoints(20);
        Cell row4Cell0 = row4.createCell(0);
        Cell row4Cell1 = row4.createCell(1);
        Cell row4Cell8 = row4.createCell(8);
        row4Cell8.setCellStyle(rightStyle);
        row4Cell0.setCellValue("完成码板时间：");
        CellStyle cellStyle1 = wb.createCellStyle();
        cellStyle1.setBorderBottom(BorderStyle.THIN);
        cellStyle1.setBorderLeft(BorderStyle.THIN);
        row4Cell0.setCellStyle(cellStyle1);
        sheet.setColumnWidth(0, 14 * 256);
        row4Cell1.setCellValue(codeBoardTemplate.getFinishTime());
    }

    private static void setParcelNumAndWeight(Sheet sheet, CodeBoardTemplate codeBoardTemplate, CellStyle cellStyle, CellStyle rightStyle) {
        Row row3 = sheet.createRow(3);
        row3.setHeightInPoints(20);
        Cell row3Cell0 = row3.createCell(0);
        Cell row3Cell1 = row3.createCell(1);
        Cell row3Cell5 = row3.createCell(5);
        Cell row3Cell6 = row3.createCell(6);
        Cell row3Cell8 = row3.createCell(8);
        row3Cell8.setCellStyle(rightStyle);
        row3Cell0.setCellStyle(cellStyle);
        row3Cell0.setCellValue("包裹数量：");
        row3Cell1.setCellValue(codeBoardTemplate.getParcelNum());
        sheet.setColumnWidth(5, 12 * 256);
        row3Cell5.setCellValue("包裹总重量：");
        row3Cell6.setCellValue(codeBoardTemplate.getParcelWeight() + " kg");
    }

    private static void setWarehouseAndSite(CodeBoardTemplate codeBoardTemplate, Row row2, CellStyle cellStyle, CellStyle rightStyle) {
        Cell row2Cell0 = row2.createCell(0);
        Cell row2Cell1 = row2.createCell(1);
        Cell row2Cell5 = row2.createCell(5);
        Cell row2Cell6 = row2.createCell(6);
        Cell row2Cell8 = row2.createCell(8);
        row2Cell8.setCellStyle(rightStyle);
        row2Cell0.setCellStyle(cellStyle);
        row2Cell0.setCellValue("分拨仓：");
        row2Cell1.setCellValue(codeBoardTemplate.getDistributionWh());
        row2Cell5.setCellValue("目的站点：");
        row2Cell6.setCellValue(codeBoardTemplate.getDestinationSite());
    }

    private static void combineCellInRowFour(Sheet sheet) {
        CellRangeAddress cellAddresses12 = new CellRangeAddress(4, 4, 1, 2);
        RegionUtil.setBorderBottom(BorderStyle.THIN, cellAddresses12, sheet);
        sheet.addMergedRegion(cellAddresses12);
        CellRangeAddress cellAddresses = new CellRangeAddress(4, 4, 3, 8);
        RegionUtil.setBorderBottom(BorderStyle.THIN, cellAddresses, sheet);
        RegionUtil.setBorderRight(BorderStyle.THIN, cellAddresses, sheet);
        sheet.addMergedRegion(cellAddresses);
    }

    private static void titleLevelTwo(Workbook wb, Sheet sheet, int rowNo, String words) {
        Row row = sheet.createRow(rowNo + 1);
        row.setHeightInPoints(40);

        combineCellInRowOne(sheet);

        BufferedImage bufferedImage = BarCodeUtil.insertWords(BarCodeUtil.getBarCode(words), words);
        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "jpg", byteArrayOut);
        } catch (IOException e) {
            log.error("titleLevelTwo unknown error:{}", e);
        }
        Drawing<?> drawingPatriarch = sheet.createDrawingPatriarch();
        HSSFClientAnchor anchor = new HSSFClientAnchor(20, 7, 0, 0, (short) 0, 1, (short) 3, 2);
        drawingPatriarch.createPicture(anchor, wb.addPicture(byteArrayOut
                .toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
    }

    private static void combineCellInRowOne(Sheet sheet) {
        CellRangeAddress cellAddresses = new CellRangeAddress(1, 1, 0, 8);
        RegionUtil.setBorderBottom(BorderStyle.THIN, cellAddresses, sheet);
        RegionUtil.setBorderLeft(BorderStyle.THIN, cellAddresses, sheet);
        RegionUtil.setBorderRight(BorderStyle.THIN, cellAddresses, sheet);
        sheet.addMergedRegion(cellAddresses);
    }

    private static void titleLevelOne(Workbook wb, Sheet sheet, int rowNo) {
        Row row = sheet.createRow(rowNo);//创建行
        row.setHeightInPoints(30);
        //合并1到7列 和1到2行
        combineCellInRowZero(sheet);

        createRowOneCellAndSetTitle(wb, row);

        createRowOneCellAndSetPage(wb, row);
    }

    private static void createRowOneCellAndSetPage(Workbook wb, Row row) {
        Cell cell8 = row.createCell(8);
        CellStyle cellStyle8 = wb.createCellStyle();
        cellStyle8.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle8.setAlignment(HorizontalAlignment.CENTER);
        //分页默认值
        cell8.setCellValue("1/1");
        cellStyle8.setBorderTop(BorderStyle.THIN);
        cellStyle8.setBorderRight(BorderStyle.THIN);
        cellStyle8.setBorderBottom(BorderStyle.THIN);
        cell8.setCellStyle(cellStyle8);
    }

    private static void createRowOneCellAndSetTitle(Workbook wb, Row row) {
        Cell cell = row.createCell(0);//创建单元格
        cell.setCellValue("分拨码板交接单");
        Font font = wb.createFont();
        font.setFontName("微软雅黑");//设置字体名称
        font.setFontHeightInPoints((short) 18);//设置字号
        font.setBold(true);
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cell.setCellStyle(cellStyle);
    }

    private static void combineCellInRowZero(Sheet sheet) {
        CellRangeAddress cellAddresses = new CellRangeAddress(0, 0, 0, 7);
        RegionUtil.setBorderBottom(BorderStyle.THIN, cellAddresses, sheet);
        RegionUtil.setBorderLeft(BorderStyle.THIN, cellAddresses, sheet);
        RegionUtil.setBorderTop(BorderStyle.THIN, cellAddresses, sheet);
        sheet.addMergedRegion(cellAddresses);
    }
}
