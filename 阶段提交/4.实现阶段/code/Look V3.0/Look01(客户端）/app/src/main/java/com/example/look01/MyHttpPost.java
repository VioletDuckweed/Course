package com.example.look01;

/**
 * Created by 胡紫萍 on 2017/6/5.
 */

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.util.List;


public class MyHttpPost {
    // 服务器地址
    private static String SERVER = "http://192.168.72.1:8080";
    // 项目地址
    private static String PROJECT = "/LoginServer/";
    // 请求超时
    private static final int REQUEST_TIMEOUT = 0;
    // 读取超时
    private static final int SO_TIMEOUT = 0;

    // 通过 POST 方式获取HTTP服务器数据
    public static String executeHttpPost(String servlet, List<NameValuePair> params) {
        String baseURL = SERVER + PROJECT + servlet;
        String responseMsg = "FAILED";
        try {
            //连接到服务器端相应的Servlet
            HttpPost request = new HttpPost(baseURL);
            request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

            BasicHttpParams httpParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParams, REQUEST_TIMEOUT);//请求超时时间
            HttpConnectionParams.setSoTimeout(httpParams, SO_TIMEOUT);//等待数据超时时间
            HttpClient client = new DefaultHttpClient(httpParams);
            HttpResponse response = client.execute(request);
            if(response.getStatusLine().getStatusCode()==200)//是否成功收取信息
                responseMsg = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("tag", "RegisterHttpPost: responseMsg = " + responseMsg);
        return responseMsg;
    }
}

