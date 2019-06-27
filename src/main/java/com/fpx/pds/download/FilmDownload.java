/**
 * Copyright (c) 2005-2019. 4PX and/or its affiliates. All rights reserved.
 * Use,Copy is subject to authorized license.
 */
package com.fpx.pds.download;

import com.fpx.pds.utils.TimeUtil;
import org.apache.commons.lang.StringUtils;
import org.assertj.core.util.Lists;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        //是否批量下载
        Boolean isBatchDownload = false;
        String batchDownloadFilePath = "";

        //单个下载，默认方式
        String downloadUrl = "https://youku163.zuida-bofang.com/20190113/24326_e382b440/800k/hls/";
        String seedFile = "D:\\index.m3u8";
        if (isBatchDownload) {
            batchDownload(batchDownloadFilePath);
        } else {
            simpleDownload(downloadUrl, seedFile);
        }
    }

    /**
     * 批量下载（串行化：即下载完一个电影才能下载另一个）
     *
     * @param filePath
     */
    public static void batchDownload(String filePath) {
        List<Map<String, String>> mapList = resolueFile(filePath);
        if (null != mapList) {
            long start = System.currentTimeMillis();
            for (Map<String, String> map : mapList) {
                enter(map.get("url"), map.get("path"));
            }
            long end = System.currentTimeMillis();
            System.out.println("【" + mapList.size() + "】任务下载已经完成，耗时：" + TimeUtil.comsumeTime(end - start));
            DownloadExecutors.getExecutorService().shutdown();
        }
    }

    /**
     * 单个下载
     *
     * @param downloadUrl
     * @param filePath
     */
    public static void simpleDownload(String downloadUrl, String filePath) {
        long start = System.currentTimeMillis();
        enter(downloadUrl, filePath);
        long end = System.currentTimeMillis();
        System.out.println("单个任务下载已经完成，耗时：" + TimeUtil.comsumeTime(end - start));
        DownloadExecutors.getExecutorService().shutdown();
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

    /**
     * 下载入口
     *
     * @param downloadUrl
     * @param filePath
     */
    public static void enter(String downloadUrl, String filePath) {
        SOURCE = downloadUrl;
        //获取下载列表
        List<String> list = getDownloadList(filePath);
        //用线程池开启下载
        download(list);
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
