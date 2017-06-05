package com.example.look01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class Hot extends AppCompatActivity {
    private Button user;//用户按钮定义
    private Button social;//社区按钮定义
    private Button search;//搜索按钮定义
    private Button hot;//首页按钮定义
    private Button recommand;//推荐按钮定义
    ImageView show;
    // 代表从网络下载得到的图片
    Bitmap bitmap;
    Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            if(msg.what == 0x123)
            {
                // 使用ImageView显示该图片
                show.setImageBitmap(bitmap);
            }
        }
    };
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot);

        user = (Button) findViewById(R.id.button18);
        user.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Hot.this, user.class);
                startActivityForResult(intent, 0);
            }
        });//完成到用户个人界面的跳转

        social = (Button) findViewById(R.id.button17);
        social.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Hot.this, Social.class);
                startActivityForResult(intent, 0);
            }
        });//完成到社区的跳转

        search = (Button) findViewById(R.id.button16);
        search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Hot.this, Search.class);
                startActivityForResult(intent, 0);
            }
        });//完成到搜索的跳转

        hot = (Button) findViewById(R.id.button15);
        hot.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Hot.this, Hot.class);
                startActivityForResult(intent, 0);
            }
        });//完成到首页热门的跳转

        recommand = (Button) findViewById(R.id.button14);
        recommand.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Hot.this, Recommand_pic.class);
                startActivityForResult(intent, 0);
            }
        });//完成到推荐界面的跳转


        show = (ImageView) findViewById(R.id.show);
        new Thread()
        {
            public void run()
            {
                try
                {
                    // 定义一个URL对象
                    URL url = new URL("http://p9.yokacdn.com/pic/YOKA/2017-05-18/U10015P1TS1495098792_84208.jpg");
                    // 打开该URL对应的资源的输入流
                    InputStream is = url.openStream();
                    // 从InputStream中解析出图片
                    bitmap = BitmapFactory.decodeStream(is);
                    // 发送消息、通知UI组件显示该图片
                    handler.sendEmptyMessage(0x123);
                    is.close();
                    // 再次打开URL对应的资源的输入流
                    is = url.openStream();
                    // 打开手机文件对应的输出流
                    OutputStream os = openFileOutput("1.png"
                            , MODE_PRIVATE);
                    byte[] buff = new byte[1024];
                    int hasRead = 0;
                    // 将URL对应的资源下载到本地
                    while((hasRead = is.read(buff)) > 0)
                    {
                        os.write(buff, 0 , hasRead);
                    }
                    is.close();
                    os.close();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
