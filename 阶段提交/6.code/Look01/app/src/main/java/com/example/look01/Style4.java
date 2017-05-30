package com.example.look01;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.ImageView;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class Style4 extends AppCompatActivity {

    ImageView pic1;
    ImageView pic2;
    private WebView web_rec;
    // 代表从网络下载得到的图片
    Bitmap bitmap1;
    Bitmap bitmap2;
    Handler handler1 = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            if(msg.what == 0x123)
            {
                // 使用ImageView显示该图片
                pic1.setImageBitmap(bitmap1);
            }
        }
    };
    Handler handler2 = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            if(msg.what == 0x123)
            {
                // 使用ImageView显示该图片
                pic2.setImageBitmap(bitmap2);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_style4);
        web_rec = (WebView) findViewById(R.id.web_rec);
        web_rec.loadUrl("https://dappei.com/user/rossishen?page=4");
        pic1 = (ImageView) findViewById(R.id.pic1);
        pic2 = (ImageView) findViewById(R.id.pic2);
        new Thread()
        {
            public void run()
            {
                try
                {
                    // 定义一个URL对象
                    URL url = new URL("https://images.dappei.com/uploads/photo/image/65968/large_f25514d11c1b9386.jpg");
                    // 打开该URL对应的资源的输入流
                    InputStream is = url.openStream();
                    // 从InputStream中解析出图片
                    bitmap1 = BitmapFactory.decodeStream(is);
                    // 发送消息、通知UI组件显示该图片
                    handler1.sendEmptyMessage(0x123);
                    is.close();
                    // 再次打开URL对应的资源的输入流
                    is = url.openStream();
                    // 打开手机文件对应的输出流
                    OutputStream os = openFileOutput("20.png"
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

                    URL url2=new URL("https://images.dappei.com/uploads/photo/image/62705/large_cfd97039531c25bd.jpg");
                    InputStream is2=url2.openStream();
                    bitmap2=BitmapFactory.decodeStream(is2);
                    handler2.sendEmptyMessage(0x123);
                    is2.close();
                    is2=url2.openStream();
                    OutputStream os2=openFileOutput("21.png",MODE_PRIVATE);
                    byte[] buff2 = new byte[1024];
                    int hasRead2= 0;
                    while ((hasRead2 = is2.read(buff2))>0)
                    {
                        os2.write(buff2,0,hasRead2);
                    }
                    is2.close();
                    os2.close();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}