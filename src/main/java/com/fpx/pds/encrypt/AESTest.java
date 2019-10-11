package com.fpx.pds.encrypt;

import com.fpx.pds.utils.TimeUtil;

/**
 * @Author: cuiwy
 * @Date: 2019/7/8 18:02
 * @version: v1.0.0
 */
public class AESTest {
    public static void main(String[] args) {
        String password = "[{\"apiCode\":\"DEV01543534\",\"apiHost\":\"127.0.0.1\",\"apiName\":\"DEV01\",\"apiPort\":\"8080\",\"apiType\":\"http\",\"apiUrl\":\"/api/pmm/signIn\",\"createTime\":1569480814000,\"id\":371,\"requestMethod\":\"POST\",\"stitching\":false}]";
        System.out.println("原字符：" + password);
        System.out.println("==========================");
        long start = System.currentTimeMillis();
        String encode = AESUtil.enCode(password);
        System.out.println("加密：" + encode);
        System.out.println("===========================");
        String deCode = AESUtil.deCode(encode);
        System.out.println("解密：" + deCode);
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + TimeUtil.comsumeTime(end - start));
    }
}
