package com.example.look01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

import android.app.Dialog;
import android.content.Context;
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

public class forgetpsd2 extends AppCompatActivity {

    EditText id;
    Button confirmBtn;
    EditText password,password2;
    Dialog dialog;
    Handler handler;
    static int LOGIN_FAILED = 0;
    static int LOGIN_SUCCEEDED = 1;
    static int REGISTER_FAILED = 2;
    static int REGISTER_SUCCEEDED = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpsd2);
        id = (EditText)findViewById(R.id.forgetpsd2_userid);
        password = (EditText)findViewById(R.id.forgetpsd2_password);
        password2 = (EditText)findViewById(R.id.forgetpsd2_password2);
        confirmBtn = (Button)findViewById(R.id.forgetpsd2_confirm);
        //returnBtn = (Button)findViewById(R.id.returnBtn);

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkEdit()){//检查注册信息
                if(isConnectingToInternet()){ //检查网络
                if (id.getText().toString().equals(""))
                    Toast.makeText(forgetpsd2.this, "请输入账号", Toast.LENGTH_SHORT).show();
                else {
                    //启动登录Thread
                    dialog = new Dialog(forgetpsd2.this);
                    dialog.setTitle("正在修改，请稍后...");
                    dialog.setCancelable(false);
                    dialog.show();
                    new RegisterPostThread(id.getText().toString(),
                            password.getText().toString()).start();
                }
                 }else{
                Toast.makeText(getApplicationContext(),
                "网络未连接",Toast.LENGTH_SHORT).show();
                 }
                 }
            }
        });

        //Handle,Msg返回成功信息，跳转到其他Activity
        handler = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                dialog.dismiss();
                if (msg.what == 222) {  // 处理发送线程传回的消息
                    if(msg.obj.toString().equals("SUCCEEDED")){
                        //Log.i("tag", "注册模拟跳转");
                        //跳转
                        Toast.makeText(forgetpsd2.this, "修改成功", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent();
                        intent.setClass(forgetpsd2.this, Hot.class);
                        startActivityForResult(intent, 0);//完成到首页的跳转

                    }else{
                        Toast.makeText(forgetpsd2.this, "密码修改失败", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };

        /*
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //回到登录Activity
                Intent intent = new Intent(RegisterActivity.this,
                        LoginActivity.class);
                startActivity(intent);
            }
        });*/
    }

    //注册Thread调用RegisterPostService，返回Msg
    public class RegisterPostThread extends Thread {
        public String id,password;

        public RegisterPostThread(String id, String password) {
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
                responseInt = RegisterPostService.send(params);
                Log.i("tag", "RegisterActivity: responseInt = " + responseInt);
                // 准备发送消息
                Message msg = handler.obtainMessage();
                // 设置消息默认值
                msg.what = 222;
                // 服务器返回信息的判断和处理
                if(responseInt == REGISTER_FAILED) {
                    msg.obj = "FAILED";
                }else if(responseInt == REGISTER_SUCCEEDED) {
                    msg.obj = "SUCCEEDED";
                }
                handler.sendMessage(msg);
            }
        }
    }

    //检查注册信息
    public boolean checkEdit(){
        if(id.getText().toString().equals("")){
            Toast.makeText(forgetpsd2.this, "账户不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password.getText().toString().equals("")){
            Toast.makeText(forgetpsd2.this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!password2.getText().toString().equals(password.getText().toString())){
            Toast.makeText(forgetpsd2.this, "两次输入的密码不同", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    // 检测网络状态：函数成功
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


