package com.fpx.pds.excel.iexcel;


import com.fpx.pds.excel.iexcel.entity.AddressFiled;
import com.fpx.pds.map.MapUtil;
import com.fpx.pds.utils.AddressHelper;
import com.github.houbb.iexcel.core.reader.IExcelReader;
import com.github.houbb.iexcel.core.writer.IExcelWriter;
import com.github.houbb.iexcel.util.excel.ExcelUtil;

import java.io.File;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

/**
 * IExcel 帮助类
 *
 * @Author: cuiwy
 * @Date: 2019/7/24 14:14
 * @version: v1.0.0
 */
public class IExcelHelper {

    public static void main(String[] args) {
        String readFilePath = "D:\\address.xlsx";
        String writeFilePath = "D:\\writeAddress.xlsx";
        List<AddressFiled> addressFiledList = read(readFilePath, AddressFiled.class);
        //标准化处理地址
        double successCount = 0;
        int mod = 0;
        int len = addressFiledList.size();
        for (AddressFiled addressFiled : addressFiledList) {
            mod++;
            if (mod % 1000 == 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            String prettify = AddressHelper.prettify(addressFiled.getAddress());
            addressFiled.setTargetAddress(prettify);
            Map<String, String> map = MapUtil.getGeocoderLatitude(addressFiled.getAddress());
            if (null != map) {
                successCount++;
                addressFiled.setLng(map.get("lng"));
                addressFiled.setLat(map.get("lat"));
            }
        }
        DecimalFormat df = new DecimalFormat("0.0000");
        String success = df.format(successCount / len);
        System.out.println("部分示例：");
        System.out.println("==============================================");
        for (int i = 1; i <= 10; i++) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println(i + "）源地址：【" + addressFiledList.get(i).getAddress() + "】");
            System.out.println(i + "）标准化后地址：【" + addressFiledList.get(i).getTargetAddress() + "】");
            System.out.println(i + "）经纬度：【" + addressFiledList.get(i).getLng() + "," + addressFiledList.get(i).getLat() + "】");
        }
        System.out.println("============================================");
        System.out.println("测试地址来自生产环境，总数：" + len);
        System.out.println("使用源地址，获取经纬度成功率：" + Double.valueOf(success) * 100 + "%");
        write(addressFiledList, writeFilePath);

    }

    /**
     * 通用读Excel
     *
     * @param filePath
     * @param tClass
     * @return
     */
    public static <K> List<K> read(String filePath, Class<K> tClass) {
        File file = new File(filePath);
        IExcelReader<K> excelReader = ExcelUtil.getExcelReader(file);
        return excelReader.readAll(tClass);
    }

    /**
     * 通用写Excel
     *
     * @param models
     * @param filePath
     * @param <K>
     */
    public static <K> void write(List<K> models, String filePath) {
        // 对应的 excel 写入对象
        IExcelWriter excelWriter = ExcelUtil.get07ExcelWriter();
        // 只写入一次列表
        ExcelUtil.onceWriteAndFlush(excelWriter, models, filePath);
    }


}
