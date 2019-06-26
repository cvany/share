
package com.fpx.pds.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Author: cuiwy
 * @Date: 2018/12/12 17:45
 * @version: v1.0.0
 */
@Slf4j
public class HttpUtil {
    private static RestTemplate restTemplate = null;

    static {
        try {
            restTemplate = new RestTemplate(generateHttpRequestFactory());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
    }

    private final static HttpClient httpClient = new DefaultHttpClient();

    static {
        //解决响应内容出现乱码问题
//        reInitMessageConverter(restTemplate);
    }

    private static HttpComponentsClientHttpRequestFactory generateHttpRequestFactory()
            throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException {
        TrustStrategy acceptingTrustStrategy = (x509Certificates, authType) -> true;
        SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
        SSLConnectionSocketFactory connectionSocketFactory = new SSLConnectionSocketFactory(sslContext,
                new NoopHostnameVerifier());

        HttpClientBuilder httpClientBuilder = HttpClients.custom();
        httpClientBuilder.setSSLSocketFactory(connectionSocketFactory);
        CloseableHttpClient httpClient = httpClientBuilder.build();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setHttpClient(httpClient);
        return factory;
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
//        HttpMessageConverter<?> converter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        HttpMessageConverter<?> converter = new FastJsonHttpMessageConverter();
        converterList.add(converter);
    }

    /**
     * 根据url地址获取二进制流
     *
     * @param url
     * @return
     */
    public static InputStream get(String url) {
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<byte[]> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<byte[]>(headers), byte[].class);
        byte[] result = response.getBody();
        return new ByteArrayInputStream(result);
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
     * @param headers
     */
    public static String post(String url, String json, HttpHeaders headers) {
        HttpEntity httpEntity = new HttpEntity<>(json, headers);
        JSONObject response = restTemplate.postForObject(url, httpEntity, JSONObject.class);
        return JSON.toJSONString(response);
    }

    public static String postJsonWithHeaders(String url, Object params, Map<String, String> headers) throws HttpClientErrorException {
        HttpPost httpPost = new HttpPost(url);
        httpPost = (HttpPost) setHeaders(httpPost, headers);
        httpPost.addHeader("Content-Type", "application/json");
        if (params != null) {
            String paramStr = JSON.toJSONString(params);
            httpPost.setEntity(new StringEntity(paramStr, Charset.forName("UTF-8")));
        }

        CloseableHttpResponse response = null;

        String var5 = "";
        try {
            response = (CloseableHttpResponse) httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new HttpClientErrorException(HttpStatus.valueOf(response.getStatusLine().getStatusCode()));
            }

            var5 = EntityUtils.toString(response.getEntity());
        } catch (IOException var14) {
            log.error("response exception:{}", var14);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException var13) {
                var13.printStackTrace();
            }

        }

        return var5;
    }

    private static HttpRequestBase setHeaders(HttpRequestBase request, Map<String, String> headers) {
        Iterator<String> keyIterator = headers.keySet().iterator();
        String key = null;

        while (keyIterator.hasNext()) {
            key = (String) keyIterator.next();
            request.addHeader(key, (String) headers.get(key));
        }

        return request;
    }
}
