package com.alien.kernel.utils;

import org.apache.commons.codec.Charsets;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: alien
 * @date: 2019/11/25 9:39
 * @description:
 */
public class HttpUtils {

    //httpClient单例
    private static CloseableHttpClient httpClient;
    private static RequestConfig requestConfig;

    //连接池
    static {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(200);
        cm.setDefaultMaxPerRoute(20);
        httpClient = HttpClients.custom().setConnectionManager(cm).build();
        requestConfig = RequestConfig
                .custom().setConnectionRequestTimeout(1000 * 10)
                .setConnectTimeout(1000 * 10)
                .setSocketTimeout(1000 * 10).build();
    }

    /**
     * POST连接,默认application/x-www-form-urlencoded提交
     *
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public static String post(String url, Map<String, Object> params) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        setPostParams(httpPost, params);
        try (CloseableHttpResponse response = httpClient.execute(httpPost, HttpClientContext.create())) {
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, Charsets.UTF_8);
            EntityUtils.consume(entity);
            return result;
        }
    }

    /**
     * POST提交，以application/json方式请求
     *
     * @param url
     * @param jsonParams json字符串参数
     * @return
     * @throws IOException
     */
    public static String postByJson(String url, String jsonParams) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        StringEntity entity = new StringEntity(jsonParams, Charsets.UTF_8);
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json;charset=utf-8");
        httpPost.setEntity(entity);
        try (CloseableHttpResponse response = httpClient.execute(httpPost, HttpClientContext.create());) {
            HttpEntity resultEntity = response.getEntity();
            String result = EntityUtils.toString(resultEntity, Charsets.UTF_8);
            EntityUtils.consume(resultEntity);
            return result;
        }
    }

    /**
     * POST提交，以application/json方式请求
     *
     * @param url
     * @param jsonParams json字符串参数
     * @return
     * @throws IOException
     */
    public static String postByJson(String url, String jsonParams, Map<String, String> headers) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        // 加入请求相关信息
        if (!CollectionUtils.isEmpty(headers)) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }
        }
        StringEntity entity = new StringEntity(jsonParams, Charsets.UTF_8);
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json;charset=utf-8");
        httpPost.setEntity(entity);
        try (CloseableHttpResponse response = httpClient.execute(httpPost, HttpClientContext.create());) {
            HttpEntity resultEntity = response.getEntity();
            String result = EntityUtils.toString(resultEntity, Charsets.UTF_8);
            EntityUtils.consume(resultEntity);
            return result;
        }
    }

    /**
     * GET连接
     *
     * @param url
     * @return
     */
    public static String get(String url) throws IOException {
        HttpGet httpget = new HttpGet(url);
        httpget.setConfig(requestConfig);
        try (CloseableHttpResponse response = httpClient.execute(httpget, HttpClientContext.create());) {
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, Charsets.UTF_8);
            EntityUtils.consume(entity);
            return result;
        }
    }

    /**
     * Get方式设置请求头
     *
     * @Param:
     * @return:
     */
    public static String getAddHeader(String url, Header header) throws IOException {
        HttpGet httpget = new HttpGet(url);
        httpget.setConfig(requestConfig);
        httpget.addHeader(header);
        try (CloseableHttpResponse response = httpClient.execute(httpget, HttpClientContext.create())) {
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, Charsets.UTF_8);
            EntityUtils.consume(entity);
            return result;
        }
    }

    /**
     * 设置连接参数
     *
     * @param httpost
     * @param params
     */
    private static void setPostParams(HttpPost httpost, Map<String, Object> params) {
        List<NameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("timeStamp",
                String.valueOf(System.currentTimeMillis() / 1000)));
        if (params != null && params.size() > 0) {
            Set<String> keySet = params.keySet();
            for (String key : keySet) {
                nvps.add(new BasicNameValuePair(key, params.get(key).toString()));
            }
        }
        httpost.setEntity(new UrlEncodedFormEntity(nvps, Charsets.UTF_8));
    }

}
