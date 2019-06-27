/**
 * Copyright (c) 2005-2019. 4PX and/or its affiliates. All rights reserved.
 * Use,Copy is subject to authorized license.
 */
package com.fpx.pds.share;

import org.apache.commons.lang.StringUtils;
import org.assertj.core.util.Lists;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: cuiwy
 * @Date: 2019/6/24 19:07
 * @version: v1.0.0
 */
public class CMDTest {

    public static void main(String[] args) throws Exception {
      /*  Runtime runtime = Runtime.getRuntime();
        String curr = System.currentTimeMillis() + ".mp4";
        String cmd = "cmd /c copy /b D:\\222\\\\temp\\*.ts D:\\222\\" + curr ;

//        String curr = System.currentTimeMillis() + ".txt";
//        String cmd = "cmd /c copy /b D:\\222\\temp\\*.txt D:\\222\\" + curr ;
        Process process = runtime.exec(cmd);
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.forName("GBK")));
        String line = null;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
//        process.waitFor();
        System.out.println("合并命令已经执行完毕，开始执行删除命令");
//        runtime.exec("cmd /c rd /s/q D:\\222\\temp");*/

        test();
    }

    private static void test() {
        String fileName = "C:\\Users\\cuiwy.4PXAD\\Desktop\\catalo.txt";
        List<Map<String, String>> mapList = resolueFile(fileName);
        System.out.println(mapList.size());
    }

    private static List<Map<String, String>> resolueFile(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));
            String item = null;
            List<Map<String, String>> result = Lists.newArrayList();
            Map<String, String> map = null;
            while ((item = reader.readLine()) != null) {
                if (StringUtils.isNotBlank(item)) {
                    map = new HashMap<>(2);
                    String[] array = item.replaceAll(" +", "#").split("#");
                    if (array.length > 2) {
                        map.put("url", array[1]);
                        map.put("path", array[2]);
                        result.add(map);
                    } else {
                        map.put("url", array[0]);
                        map.put("path", array[1]);
                        result.add(map);
                    }
                }
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return null;
    }
}
