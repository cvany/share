/**
 * Copyright (c) 2005-2018. 4PX and/or its affiliates. All rights reserved.
 * Use,Copy is subject to authorized license.
 */
package com.fpx.pds.utils;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @Author: cuiwy
 * @Date: 2018/12/12 17:45
 * @version: v1.0.0
 */
public class HttpUtil {
    private final static RestTemplate restTemplate = new RestTemplate();

    static {
        //解决响应内容出现乱码问题
        reInitMessageConverter(restTemplate);
    }

    private static void reInitMessageConverter(RestTemplate restTemplate) {
        List<HttpMessageConverter<?>> converterList = restTemplate.getMessageConverters();
        HttpMessageConverter<?> converterTarget = null;
        for (HttpMessageConverter<?> item : converterList) {
            if (item.getClass() == StringHttpMessageConverter.class) {
                converterTarget = item;
                break;
            }
        }
        if (converterTarget != null) {
            converterList.remove(converterTarget);
        }
        HttpMessageConverter<?> converter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        converterList.add(converter);
    }

    /**
     * GET请求
     *
     * @param url
     * @param param
     */
    public static String get(String url, String param) {
        return restTemplate.getForObject(url, String.class);
    }

    /**
     * POST请求
     *
     * @param url
     * @param param
     */
    public static void post(String url, String param) {

    }
}
