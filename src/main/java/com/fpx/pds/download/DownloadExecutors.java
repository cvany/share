/**
 * Copyright (c) 2005-2019. 4PX and/or its affiliates. All rights reserved.
 * Use,Copy is subject to authorized license.
 */
package com.fpx.pds.download;

import java.util.concurrent.*;

/**
 * 下载线程池
 *
 * @Author: cuiwy
 * @Date: 2019/6/24 11:21
 * @version: v1.0.0
 */
public class DownloadExecutors {

    private static ExecutorService execute = null;

    static {
        execute = new ThreadPoolExecutor(15, 30,
                60L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
    }

    public static ExecutorService getExecutorService() {
        return execute;
    }

    public static void execute(Runnable run) {
        execute.execute(run);
    }
}
