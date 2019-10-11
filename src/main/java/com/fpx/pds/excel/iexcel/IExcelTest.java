package com.fpx.pds.excel.iexcel;


import com.fpx.pds.excel.iexcel.entity.ExcelFieldModel;
import com.fpx.pds.map.MapUtil;
import com.fpx.pds.utils.TimeUtil;
import com.github.houbb.iexcel.core.reader.IExcelReader;
import com.github.houbb.iexcel.core.writer.IExcelWriter;
import com.github.houbb.iexcel.util.excel.ExcelUtil;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @Author: cuiwy
 * @Date: 2019/7/3 17:27
 * @version: v1.0.0
 */
public class IExcelTest {

    public static void main(String[] args) {
        String readFile = "D:\\test.xlsx";
        String writeFile = "D:\\newProduct.xlsx";
        List<ExcelFieldModel> models = read(readFile);
        //获取经纬度
        long start = System.currentTimeMillis();
        for (ExcelFieldModel model : models) {
            Map<String, String> map = MapUtil.getGeocoderLatitude(model.getAddress());
            if (null != map) {
                model.setLongitude(map.get("lng"));
                model.setLatitude(map.get("lat"));
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("获取经纬度耗时：" + TimeUtil.comsumeTime(end - start));
        //处理数据
        for (ExcelFieldModel model : models) {
            model.setRouteName("'" + model.getRouteName() + "'");
            model.setNodeName("'" + model.getNodeName() + "'");
            model.setNodeCode("'" + model.getNodeCode() + "'");
            model.setAddress("'" + model.getAddress() + "'");
            model.setLongitude("'" + model.getLongitude() + "'");
            model.setLatitude("'" + model.getLatitude() + "'");
        }
        //写入文件
        write(models, writeFile);
    }

    public static List<ExcelFieldModel> read(String filePath) {
        File file = new File(filePath);
        IExcelReader<ExcelFieldModel> excelReader = ExcelUtil.getExcelReader(file);
        List<ExcelFieldModel> models = excelReader.readAll(ExcelFieldModel.class);
        return models;
    }

    public static void write(List<ExcelFieldModel> models, String filePath) {
        // 对应的 excel 写入对象
        IExcelWriter excelWriter = ExcelUtil.get07ExcelWriter();
        // 只写入一次列表
        ExcelUtil.onceWriteAndFlush(excelWriter, models, filePath);
    }
}
