/**
 * Copyright (c) 2005-2019. 4PX and/or its affiliates. All rights reserved.
 * Use,Copy is subject to authorized license.
 */
package com.fpx.pds.download;

import org.assertj.core.util.Lists;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 在线电影下载
 *
 * @Author: cuiwy
 * @Date: 2019/6/24 10:41
 * @version: v1.0.0
 */
public class FilmDownload {

    private static final String SAVE_CATALOG = "D:\\download\\";
    private static String SOURCE;


    public static void main(String[] args) {
        String downloadUrl = "https://youku.com-a-youku.com/20190622/7641_a4e91bb2/1000k/hls/";
        String seedFile = "D:\\index.m3u8";
        enter(downloadUrl, seedFile);
//        combineTask();
    }

    private static void enter(String downloadUrl, String filePath) {
        SOURCE = downloadUrl;

        long start = System.currentTimeMillis();
        //获取下载列表
        List<String> list = getDownloadList(filePath);
        //用线程池开启下载
        download(list);
        long end = System.currentTimeMillis();
        System.out.println("下载已经完成，耗时：" + (end - start) / 1000 + " s");
        DownloadExecutors.getExecutorService().shutdown();
    }

    private static void download(List<String> list) {
        int len = list.size();
        CountDownLatch countDownLatch = new CountDownLatch(len);
        for (int i = 0; i < len; i++) {
            String fileName = formatName(i) + ".ts";
            DownloadExecutors.execute(new DownloadThread(SAVE_CATALOG, fileName, countDownLatch, SOURCE + list.get(i)));
        }
        try {
            countDownLatch.await();
            System.out.println("所有任务已经执行完毕！总任务：" + len);
            //开始合并任务
            combineTask();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String formatName(int i) {
        if (i < 10) {
            return "00000" + i;
        } else if ((i < 100)) {
            return "0000" + i;
        } else if (i < 1000) {
            return "000" + i;
        } else if (i < 10000) {
            return "00" + i;
        }
        return "0" + i;
    }

    private static void combineTask() {
        Date date = new Date();
        String monthDay = String.format("%tm", date) + String.format("%td", date);
        String curr = System.currentTimeMillis() + ".mp4";
        String cmd = "cmd /c copy /b " + SAVE_CATALOG + monthDay + "\\temp\\*.ts " + SAVE_CATALOG + monthDay + File.separator + curr;

        Runtime runtime = Runtime.getRuntime();
        Process process = null;
        try {
            process = runtime.exec(cmd);
            System.out.println("开始执行命令：" + cmd);
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.forName("GBK")));
            String line = null;
            //多了这一行 【已复制         1 个文件。】 所以从-1开始
            int count = -1;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                count++;
            }
            System.out.println("【" + count + "】合并命令已经执行完毕，开始执行删除命令");
            runtime.exec("cmd /c rd /s/q " + SAVE_CATALOG + monthDay + File.separator + "\\temp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<String> getDownloadList(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String item = null;
            List<String> result = Lists.newArrayList();
            while ((item = reader.readLine()) != null) {
                if (!item.startsWith("#") && item.contains(".ts")) {
                    result.add(item);
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
