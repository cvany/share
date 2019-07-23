package com.fpx.pds.excel.custom;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.fpx.pds.excel.custom.BarCodeUtil.getBarCode;
import static com.fpx.pds.excel.custom.BarCodeUtil.insertWords;

/**
 * 条形码测试
 *
 * @Author: cuiwy
 * @Date: 2019/7/9 14:30
 * @version: v1.0.0
 */
public class BarCodeTest {
    public static void main(String[] args) throws IOException {
        BufferedImage image = insertWords(getBarCode("123456789"), "123456789");
        ImageIO.write(image, "png", new File("D://abc.png"));
    }
}
