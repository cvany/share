/**
 * Copyright (c) 2005-2019. 4PX and/or its affiliates. All rights reserved.
 * Use,Copy is subject to authorized license.
 */
package com.fpx.pds.share;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

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

    private static void test(){
        int count =0;

        while (count<5){
            try {
                System.out.println("请求url"+count);
                int i =1/0;
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                count++;
                System.out.println(count+"次尝试");
            }
        }
    }
}
