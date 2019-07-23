package com.fpx.pds.excel.custom;

import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.fpx.pds.excel.custom.BarCodeUtil.getBarCode;
import static com.fpx.pds.excel.custom.BarCodeUtil.insertWords;

/**
 * @Author: cuiwy
 * @Date: 2019/7/9 14:16
 * @version: v1.0.0
 */
public class ImgPoi {

    public static void main(String[] args) {

        FileOutputStream fileOut = null;
        BufferedImage bufferImg = null;//图片一
        BufferedImage bufferImg1 = null;//图片二
        try {
            // 先把读进来的图片放到一个ByteArrayOutputStream中，以便产生ByteArray
            ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
            ByteArrayOutputStream byteArrayOut1 = new ByteArrayOutputStream();

            //将两张图片读到BufferedImage
//            bufferImg = ImageIO.read(new File("D:/1.png"));
            bufferImg = insertWords(getBarCode("CNHKGF19070901"), "CNHKGF19070901");
            bufferImg1 = ImageIO.read(new File("D:/tiaoxingma.jpg"));
            ImageIO.write(bufferImg, "png", byteArrayOut);
            ImageIO.write(bufferImg1, "jpg", byteArrayOut1);

            // 创建一个工作薄
            HSSFWorkbook wb = new HSSFWorkbook();
            //创建一个sheet
            HSSFSheet sheet = wb.createSheet("out put excel");

            HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
            /**
             * 该构造函数有8个参数
             * 前四个参数是控制图片在单元格的位置，分别是图片距离单元格left，top，right，bottom的像素距离
             * 后四个参数，前连个表示图片左上角所在的cellNum和 rowNum，后天个参数对应的表示图片右下角所在的cellNum和 rowNum，
             * excel中的cellNum和rowNum的index都是从0开始的
             *
             */
            //图片一导出到单元格B2中
            HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0, (short) 1, 1, (short) 2, 2);
            //图片二导出到单元格C3到E5中，且图片的left和top距离边框50
            HSSFClientAnchor anchor1 = new HSSFClientAnchor(50, 50, 0, 0,
                    (short) 2, 2, (short) 5, 5);

            // 插入图片
            patriarch.createPicture(anchor1, wb.addPicture(byteArrayOut
                    .toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
            patriarch.createPicture(anchor1, wb.addPicture(byteArrayOut
                    .toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));

            fileOut = new FileOutputStream("D:/output_Excel.xls");
            // 写入excel文件
            wb.write(fileOut);
        } catch (IOException io) {
            io.printStackTrace();
            System.out.println("io erorr : " + io.getMessage());
        } finally {
            if (fileOut != null) {
                try {
                    fileOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
