/**
 * Copyright (c) 2005-2019. 4PX and/or its affiliates. All rights reserved.
 * Use,Copy is subject to authorized license.
 */
package com.fpx.pds.download;

import com.fpx.pds.utils.HttpUtil;

import java.io.*;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * 下载线程
 *
 * @Author: cuiwy
 * @Date: 2019/6/24 11:25
 * @version: v1.0.0
 */
public class DownloadThread implements Runnable {

    private CountDownLatch countDownLatch;
    private String url;
    private String saveCatalog;
    private String fileName;

    public DownloadThread(String saveCatalog, String fileName, CountDownLatch countDownLatch, String url) {
        this.countDownLatch = countDownLatch;
        this.url = url;
        this.saveCatalog = saveCatalog;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        Thread thread = Thread.currentThread();
        System.out.println("【" + countDownLatch.getCount() + "】Thread name:" + thread.getName() + " url:" + this.url);
        //请求资源
        try {
            InputStream inputStream = null;
            int count = 1;

            while (count <= 10 && null == inputStream) {
                try {
                    inputStream = HttpUtil.get(url);
                } catch (Exception e) {
                    e.printStackTrace();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    System.out.println("第" + count + "次尝试重连,url:" + this.url + " 对应本地文件名：" + this.fileName);
                    count++;
                }
            }
            //保存到本地文件夹
            save(inputStream);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            countDownLatch.countDown();
        }
    }

    private void save(InputStream inputStream) {
        if (null == inputStream) return;
        String dir = createDir();
        File file = new File(dir + File.separator + this.fileName);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            byte[] b = new byte[1024];
            while (inputStream.read(b) != -1) {
                fileOutputStream.write(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != fileOutputStream) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String createDir() {
        Date date = new Date();
        String monthDay = String.format("%tm", date) + String.format("%td", date);
        String path = this.saveCatalog + File.separator + monthDay + File.separator + "temp" + File.separator;
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return path;
    }
}
