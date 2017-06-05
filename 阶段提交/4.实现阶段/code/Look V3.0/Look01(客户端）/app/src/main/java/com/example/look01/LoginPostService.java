package com.example.look01;

/**
 * Created by 胡紫萍 on 2017/6/5.
 */

import android.util.Log;

import org.apache.http.NameValuePair;

import java.util.List;


public class LoginPostService {
    static int LOGIN_FAILED = 0;
    static int LOGIN_SUCCEEDED = 1;

    public static int send(List<NameValuePair> params) {
        // 返回值
        int responseInt = LOGIN_FAILED;
        // 定位服务器的Servlet
        String servlet = "LoginServlet";
        // 通过 POST 方式获取 HTTP 服务器数据
        String responseMsg;
        responseMsg = MyHttpPost.executeHttpPost(servlet, params);
        Log.i("tag", "LoginService: responseMsg = " + responseMsg);
        // 解析服务器数据，返回相应 Long 值
        if(responseMsg.equals("SUCCEEDED")) {
            responseInt = LOGIN_SUCCEEDED;
        }
        return responseInt;
    }
}
