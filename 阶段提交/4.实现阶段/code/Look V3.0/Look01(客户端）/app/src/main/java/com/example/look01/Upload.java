package com.example.look01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class Upload extends AppCompatActivity {

    EditText id, password;
    Button loginBtn, registerBtn,forgetBtn;
    Handler handler;
    static int LOGIN_FAILED = 0;
    static int LOGIN_SUCCEEDED = 1;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        id = (EditText)findViewById(R.id.editText_userid);
        password = (EditText)findViewById(R.id.editText_psd);
        loginBtn = (Button)findViewById(R.id.button_login);
        registerBtn = (Button)findViewById(R.id.button_register);
        forgetBtn=(Button)findViewById(R.id.button_forgetpsd);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(isConnectingToInternet()){ //检查网络
                    if (id.getText().toString().equals(""))
                        Toast.makeText(Upload.this, "请输入账号", Toast.LENGTH_SHORT).show();
                    else {
                        //启动登录Thread
                        dialog = new Dialog(Upload.this);
                        dialog.setTitle("正在登录，请稍后...");
                        dialog.setCancelable(false);
                        dialog.show();
                        new LoginPostThread(id.getText().toString(),
                                password.getText().toString()).start();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),
                            "网络未连接",Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到注册Activity
                Intent intent = new Intent(Upload.this,
                        Register.class);
                startActivity(intent);
            }
        });

        forgetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Upload.this, forgetpsd.class);
                startActivity(intent);
            }
        });//完成到用户个人界面的跳转

        //Handle,Msg返回成功信息，跳转到其他Activity
        handler = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                dialog.dismiss();
                if (msg.what == 111) {  // 处理发送线程传回的消息
                    if(msg.obj.toString().equals("SUCCEEDED")){
                        //跳转
                        //Toast.makeText(Upload.this, "模拟跳转", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent();
                        intent.setClass(Upload.this, Hot.class);
                        startActivityForResult(intent, 0);//完成到首页的跳转

                    }else{
                        Toast.makeText(Upload.this, "账号和密码不匹配", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };

    }

    //登录Thread调用LoginPostService，返回Msg
    public class LoginPostThread extends Thread {
        public String id, password;

        public LoginPostThread(String id, String password) {
            this.id = id;
            this.password = password;
        }

        @Override
        public void run() {
            // Sevice传回int
            int responseInt = 0;
            if(!id.equals("")) {
                // 要发送的数据
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("id", id));
                params.add(new BasicNameValuePair("password", password));
                // 发送数据，获取对象
                responseInt = LoginPostService.send(params);
                Log.i("tag", "LoginActivity: responseInt = " + responseInt);
                // 准备发送消息
                Message msg = handler.obtainMessage();
                // 设置消息默认值
                msg.what = 111;
                // 服务器返回信息的判断和处理
                if(responseInt == LOGIN_FAILED) {
                    msg.obj = "FAILED";
                }else if(responseInt == LOGIN_SUCCEEDED) {
                    msg.obj = "SUCCEEDED";
                }
                handler.sendMessage(msg);
            }
        }
    }

    // 检测网络状态
    public boolean isConnectingToInternet() {
        ConnectivityManager connectivity = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }
        }
        return false;
    }
}
