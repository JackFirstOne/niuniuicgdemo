package com.http.test;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HttpTest {

   /* public static void main(String[] args) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet("http://www.itcast.cn/");

        CloseableHttpResponse response = httpClient.execute(httpGet);

        if (response.getStatusLine().getStatusCode() == 200) {
            String content = EntityUtils.toString(response.getEntity(), "UTF-8");
            System.out.println(content);
        }
    }*/

    public static void main(String[] args) throws IOException {

        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();

        //    设置最大连接数
        cm.setMaxTotal(200);

        //    设置每个主机的并发数
        cm.setDefaultMaxPerRoute(20);

        doGet(cm);

        doGet(cm);

      /*  //创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //创建HttpGet请求
        //HttpGet httpGet = new HttpGet("http://www.itcast.cn/");
        //创建HttpPost带参数请求
      *//*  String uri = "http://yun.itheima.com/search?keys=Java";
        HttpGet httpGet = new HttpGet(uri);*//*
        //创建httpPost请求
        HttpPost httpPost = new HttpPost("http://www.itcast.cn/");

        //httpPost携带参数开始
            //声明存放参数的List集合
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("keys", "java"));

            //创建表单数据Entity
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, "UTF-8");

            //设置表单Entity到httpPost请求对象中
            httpPost.setEntity(formEntity);
        //httpPost携带参数结束

        CloseableHttpResponse response = null;
        try {
            //使用HttpClient发起请求
            //response = httpClient.execute(httpGet);
            response = httpClient.execute(httpPost);

            //判断响应状态码是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                //如果为200表示请求成功，获取返回数据
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                //打印数据长度
                System.out.println(content);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放连接
            if (response == null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                httpClient.close();
            }
        }*/
    }


    private static void doGet(PoolingHttpClientConnectionManager cm) {
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();

        HttpGet httpGet = new HttpGet("http://www.itcast.cn/");

        CloseableHttpResponse response = null;

        try {
            response = httpClient.execute(httpGet);

            // 判断状态码是否是200
            if (response.getStatusLine().getStatusCode() == 200) {
                // 解析数据
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                System.out.println(content.length());
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放连接
            if (response == null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //不能关闭HttpClient
                //httpClient.close();
            }
        }
    }
}
