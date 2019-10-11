//package com.fpx.pds.excel.custom;
//
//import com.beust.jcommander.internal.Lists;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.poi.hssf.usermodel.*;
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.ss.util.CellRangeAddress;
//import org.apache.poi.ss.util.RegionUtil;
//
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayOutputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.List;
//
//
///**
// * 交接单自定义模板
// *
// * @Author: cuiwy
// * @Date: 2019/7/9 15:57
// * @version: v1.0.0
// */
//@Slf4j
//public class CodeBoardTemplateHelper {
//
//    public final static String SAVE_PATH = ConfigHolder.getString("excel.export.save.url");
//
//    /**
//     * 获取工作薄
//     *
//     * @return
//     */
//    public static Workbook getWorkbook() {
//        return new HSSFWorkbook();
//    }
//
//    /**
//     * 写出文件
//     *
//     * @param workbook
//     */
//    public static void writeFile(Workbook workbook) {
//        FileOutputStream out = null;
//        try {
//            out = new FileOutputStream(FileUtil.generateFolder(SAVE_PATH) + "/codeBoardTemplate.xls");
//            workbook.write(out);
//            out.flush();
//        } catch (Exception e) {
//            log.error("CodeBoardTemplateHelper writeFile error:{}", e);
//        } finally {
//            if (null != out) {
//                try {
//                    out.close();
//                } catch (IOException e) {
//                    log.error("writeFile close stream error:{}", e);
//                }
//            }
//        }
//    }
//
//    /**
//     * 一个卡板创建一个sheet
//     * （sheet里面进行分页）
//     *
//     * @param wb
//     * @param sheet
//     * @param codeBoardTemplate
//     * @param list
//     */
//    public static void createOneSheetForOneCodeBoard(Workbook wb, Sheet sheet,
//                                                     CodeBoardTemplate codeBoardTemplate, List<HwmsPalletDetail> list) {
//        //分页
//        int len = list.size();
//        int pageSize;
//        if (len % 39 == 0) {
//            pageSize = len / 39;
//        } else {
//            pageSize = len / 39 + 1;
//        }
//
//        int count = 0;
//        int rowNo = 0;
//        for (int page = 0; page < pageSize; page++) {
//            //创建头部
//            createHead(wb, sheet, rowNo, codeBoardTemplate);
//            //设置页码
//            Cell cell = sheet.getRow(rowNo).getCell(8);
//            cell.setCellValue((page + 1) + "/" + pageSize);
//            //开始写包裹数据
//            for (rowNo = rowNo + 7; count < len; rowNo++) {
//                count++;
//                content(wb, sheet, rowNo, list.get(count - 1));
//                if (count % 39 == 0) {
//                    break;
//                }
//            }
//            ++rowNo;
//        }
//    }
//
//
//    /**
//     * 一个码板交接单打印到多个sheet里面
//     *
//     * @param wb
//     * @param sheet
//     * @param parcels
//     */
//    private static void createCcontent(Workbook wb, Sheet sheet, List<HwmsPalletDetail> parcels) {
//        int len = parcels.size();
//        int pageSize;
//        if (len % 39 == 0) {
//            pageSize = len / 39;
//        } else {
//            pageSize = len / 39 + 1;
//        }
//        //设置页码
//        List<Sheet> sheetList = Lists.newArrayList();
//        Cell cell = sheet.getRow(0).getCell(8);
//        cell.setCellValue("1/" + pageSize);
//        sheetList.add(sheet);
//        Sheet tempSheet = null;
//        for (int i = 0; i < pageSize - 1; i++) {
//            tempSheet = wb.cloneSheet(0);
//            Cell tempCel = tempSheet.getRow(0).getCell(8);
//            tempCel.setCellValue((i + 2) + "/" + pageSize);
//            sheetList.add(tempSheet);
//        }
//
//        //需要处理多个sheet
//        int count = 0;
//        for (int n = 0; n < sheetList.size(); n++) {
//            for (int i = 7; count < len; i++) {
//                count++;
//                content(wb, sheetList.get(n), i, parcels.get(count - 1));
//                if (count % 39 == 0) {
//                    break;
//                }
//            }
//        }
//    }
//
//    private static void createHead(Workbook wb, Sheet sheet, int rowNo, CodeBoardTemplate codeBoardTemplate) {
//        //构建第一层
//        titleLevelOne(wb, sheet, rowNo);
//        //构建第二层
//        titleLevelTwo(wb, sheet, rowNo, codeBoardTemplate.getCodeBoard());
//        //构建第三层
//        titleLevelThree(wb, sheet, rowNo, codeBoardTemplate);
//        //构建包裹标题层
//        packageTitleLevel(wb, sheet, rowNo);
//    }
//
//    private static void content(Workbook wb, Sheet sheet, int rowNo, HwmsPalletDetail parcel) {
//        Row row = sheet.createRow(rowNo);
//        CellStyle cs01 = wb.createCellStyle();
//        cs01.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//        cs01.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//
//        Cell c0 = row.createCell(0);
//        c0.setCellStyle(cs01);
//        c0.setCellValue(parcel.getWarehouseNumber());
//
//        Cell c2 = row.createCell(2);
//        c2.setCellStyle(cs01);
//        c2.setCellValue(parcel.getWeight() / 1000);
//
//        Cell c5 = row.createCell(5);
//        c5.setCellStyle(cs01);
//        c5.setCellValue(DateUtil.formatTime(parcel.getPalletTime()));
//
//        combineCellInRowI(sheet, rowNo, wb);
//    }
//
//    private static void combineCellInRowI(Sheet sheet, int rowNo, Workbook wb) {
//        CellRangeAddress cra01 = new CellRangeAddress(rowNo, rowNo, 0, 1);
//        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, cra01, sheet, wb);
//        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, cra01, sheet, wb);
//        sheet.addMergedRegion(cra01);
//
//        CellRangeAddress cra24 = new CellRangeAddress(rowNo, rowNo, 2, 4);
//        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, cra24, sheet, wb);
//        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, cra24, sheet, wb);
//        sheet.addMergedRegion(cra24);
//
//        CellRangeAddress cra58 = new CellRangeAddress(rowNo, rowNo, 5, 8);
//        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, cra58, sheet, wb);
//        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, cra58, sheet, wb);
//        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, cra58, sheet, wb);
//        sheet.addMergedRegion(cra58);
//    }
//
//    private static void packageTitleLevel(Workbook wb, Sheet sheet, int rowNo) {
//        Row row2 = sheet.createRow(rowNo + 6);
//        row2.setHeightInPoints(20);
//
////        CellStyle cs01 = createStyleForPackageTitleLevel(wb);
//
//        Cell c0 = row2.createCell(0);
////        c0.setCellStyle(cs01);
//        c0.setCellValue("4px跟踪号");
//
//        Cell c2 = row2.createCell(2);
////        c2.setCellStyle(cs01);
//        c2.setCellValue("包裹重量(kg)");
//
//        Cell c5 = row2.createCell(5);
////        c5.setCellStyle(cs01);
//        c5.setCellValue("码板时间");
//        combineCellInRowSix(sheet, wb, rowNo);
//    }
//
//    private static CellStyle createStyleForPackageTitleLevel(Workbook wb) {
//        CellStyle cs01 = wb.createCellStyle();
//        cs01.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//        cs01.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//        Font font = wb.createFont();
//        font.setFontName("微软雅黑");
//        font.setBold(true);
//        cs01.setFont(font);
//        return cs01;
//    }
//
//    private static void combineCellInRowSix(Sheet sheet, Workbook wb, int rowNo) {
//        CellRangeAddress cra01 = new CellRangeAddress(rowNo + 6, rowNo + 6, 0, 1);
//        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, cra01, sheet, wb);
//        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, cra01, sheet, wb);
//        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, cra01, sheet, wb);
//        sheet.addMergedRegion(cra01);
//
//        CellRangeAddress cra24 = new CellRangeAddress(rowNo + 6, rowNo + 6, 2, 4);
//        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, cra24, sheet, wb);
//        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, cra24, sheet, wb);
//        sheet.addMergedRegion(cra24);
//
//        CellRangeAddress cra58 = new CellRangeAddress(rowNo + 6, rowNo + 6, 5, 8);
//        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, cra58, sheet, wb);
//        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, cra58, sheet, wb);
//        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, cra58, sheet, wb);
//        sheet.addMergedRegion(cra58);
//    }
//
//    private static void titleLevelThree(Workbook wb, Sheet sheet, int rowNo, CodeBoardTemplate codeBoardTemplate) {
//        Row row2 = sheet.createRow(rowNo + 2);
//        row2.setHeightInPoints(20);
//        CellStyle cellStyle = wb.createCellStyle();
//        CellStyle rightStyle = wb.createCellStyle();
//        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        rightStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
//
//        CellStyle boldStyle = createStyleForPackageTitleLevel(wb);
//
//        setWarehouseAndSite(codeBoardTemplate, row2, cellStyle, rightStyle,boldStyle);
//
//        setParcelNumAndWeight(sheet, rowNo, codeBoardTemplate, cellStyle, rightStyle,boldStyle);
//
//        setFinishTime(wb, sheet, rowNo, codeBoardTemplate, rightStyle,boldStyle);
//
//        combineCellInRowFour(sheet, wb, rowNo);
//    }
//
//    private static void setFinishTime(Workbook wb, Sheet sheet, int rowNo, CodeBoardTemplate codeBoardTemplate, CellStyle rightStyle,CellStyle boldStyle) {
//        Row row4 = sheet.createRow(rowNo + 4);
//        row4.setHeightInPoints(20);
//        Cell row4Cell0 = row4.createCell(0);
//        Cell row4Cell1 = row4.createCell(1);
//        Cell row4Cell8 = row4.createCell(8);
//        row4Cell8.setCellStyle(rightStyle);
//        row4Cell0.setCellValue("完成码板时间：");
//        CellStyle cellStyle1 = wb.createCellStyle();
//        cellStyle1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        cellStyle1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        row4Cell0.setCellStyle(cellStyle1);
//        sheet.setColumnWidth(0, 14 * 256);
//        row4Cell1.setCellValue(codeBoardTemplate.getFinishTime());
//        row4Cell1.setCellStyle(boldStyle);
//    }
//
//    private static void setParcelNumAndWeight(Sheet sheet, int rowNo, CodeBoardTemplate codeBoardTemplate, CellStyle cellStyle, CellStyle rightStyle,CellStyle boldStyle) {
//        Row row3 = sheet.createRow(rowNo + 3);
//        row3.setHeightInPoints(20);
//        Cell row3Cell0 = row3.createCell(0);
//        Cell row3Cell1 = row3.createCell(1);
//        Cell row3Cell5 = row3.createCell(5);
//        Cell row3Cell6 = row3.createCell(6);
//        Cell row3Cell8 = row3.createCell(8);
//        row3Cell8.setCellStyle(rightStyle);
//        row3Cell0.setCellStyle(cellStyle);
//        row3Cell0.setCellValue("包裹数量：");
//        row3Cell1.setCellValue(codeBoardTemplate.getParcelNum());
//        row3Cell1.setCellStyle(boldStyle);
//        sheet.setColumnWidth(5, 12 * 256);
//        row3Cell5.setCellValue("包裹总重量：");
//        row3Cell6.setCellValue(codeBoardTemplate.getParcelWeight() + " kg");
//        row3Cell6.setCellStyle(boldStyle);
//    }
//
//    private static void setWarehouseAndSite(CodeBoardTemplate codeBoardTemplate, Row row2, CellStyle cellStyle, CellStyle rightStyle,CellStyle boldStyle) {
//        Cell row2Cell0 = row2.createCell(0);
//        Cell row2Cell1 = row2.createCell(1);
//        Cell row2Cell5 = row2.createCell(5);
//        Cell row2Cell6 = row2.createCell(6);
//        Cell row2Cell8 = row2.createCell(8);
//        row2Cell8.setCellStyle(rightStyle);
//        row2Cell0.setCellStyle(cellStyle);
//        row2Cell0.setCellValue("分拨仓：");
//        row2Cell1.setCellValue(codeBoardTemplate.getDistributionWh());
//        row2Cell1.setCellStyle(boldStyle);
//        row2Cell5.setCellValue("目的站点：");
//        row2Cell6.setCellValue(codeBoardTemplate.getDestinationSite());
//        row2Cell6.setCellStyle(boldStyle);
//    }
//
//    private static void combineCellInRowFour(Sheet sheet, Workbook wb, int rowNo) {
//        CellRangeAddress cellAddresses12 = new CellRangeAddress(rowNo + 4, rowNo + 4, 1, 2);
//        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, cellAddresses12, sheet, wb);
//        sheet.addMergedRegion(cellAddresses12);
//        CellRangeAddress cellAddresses = new CellRangeAddress(rowNo + 4, rowNo + 4, 3, 8);
//        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, cellAddresses, sheet, wb);
//        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, cellAddresses, sheet, wb);
//        sheet.addMergedRegion(cellAddresses);
//    }
//
//    private static void titleLevelTwo(Workbook wb, Sheet sheet, int rowNo, String words) {
//        Row row = sheet.createRow(rowNo + 1);
//        row.setHeightInPoints(40);
//
//        combineCellInRowOne(sheet, wb, rowNo);
//
//        BufferedImage bufferedImage = BarCodeUtil.insertWords(BarCodeUtil.getBarCode(words), words);
//        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
//        try {
//            ImageIO.write(bufferedImage, "jpg", byteArrayOut);
//        } catch (IOException e) {
//            log.error("titleLevelTwo unknown error:{}", e);
//        }
//        Drawing drawingPatriarch = sheet.createDrawingPatriarch();
//        HSSFClientAnchor anchor = new HSSFClientAnchor(20, 7, 0, 0, (short) 0, rowNo + 1, (short) 3, rowNo + 2);
//        drawingPatriarch.createPicture(anchor, wb.addPicture(byteArrayOut
//                .toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
//    }
//
//    private static void combineCellInRowOne(Sheet sheet, Workbook wb, int rowNo) {
//        CellRangeAddress cellAddresses = new CellRangeAddress(rowNo + 1, rowNo + 1, 0, 8);
//        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, cellAddresses, sheet, wb);
//        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, cellAddresses, sheet, wb);
//        RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, cellAddresses, sheet, wb);
//        sheet.addMergedRegion(cellAddresses);
//    }
//
//    private static void titleLevelOne(Workbook wb, Sheet sheet, int rowNo) {
//        Row row = sheet.createRow(rowNo);//创建行
//        row.setHeightInPoints(30);
//        //合并1到7列 和1到2行
//        combineCellInRowZero(sheet, wb, rowNo);
//
//        createRowOneCellAndSetTitle(wb, row);
//
//        createRowOneCellAndSetPage(wb, row);
//    }
//
//    private static void createRowOneCellAndSetPage(Workbook wb, Row row) {
//        Cell cell8 = row.createCell(8);
//        CellStyle cellStyle8 = wb.createCellStyle();
//        cellStyle8.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//        cellStyle8.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//        //分页默认值
//        cell8.setCellValue("1/1");
//        cellStyle8.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        cellStyle8.setBorderRight(HSSFCellStyle.BORDER_THIN);
//        cellStyle8.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        cell8.setCellStyle(cellStyle8);
//    }
//
//    private static void createRowOneCellAndSetTitle(Workbook wb, Row row) {
//        Cell cell = row.createCell(0);//创建单元格
//        cell.setCellValue("分拨码板交接单");
//        Font font = wb.createFont();
//        font.setFontName("微软雅黑");//设置字体名称
//        font.setFontHeightInPoints((short) 18);//设置字号
//        font.setBold(true);
//        CellStyle cellStyle = wb.createCellStyle();
//        cellStyle.setFont(font);
//        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);
//        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//        cell.setCellStyle(cellStyle);
//    }
//
//    private static void combineCellInRowZero(Sheet sheet, Workbook wb, int rowNo) {
//        CellRangeAddress cellAddresses = new CellRangeAddress(rowNo, rowNo, 0, 7);
//        RegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, cellAddresses, sheet, wb);
//        RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, cellAddresses, sheet, wb);
//        RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, cellAddresses, sheet, wb);
//        sheet.addMergedRegion(cellAddresses);
//    }
//}
