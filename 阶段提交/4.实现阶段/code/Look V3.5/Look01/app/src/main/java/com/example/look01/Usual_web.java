package com.example.look01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

public class Usual_web extends AppCompatActivity {
    private Button user;//用户按钮定义
    private Button social;//社区按钮定义
    private Button search;//搜索按钮定义
    private Button hot;//首页按钮定义
    private WebView show;//显示常用网站
    private Button web1;
    private Button web2;
    private Button web3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usual_web);

        user = (Button) findViewById(R.id.button23);
        user.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Usual_web.this, user.class);
                startActivityForResult(intent, 0);
            }
        });//完成到用户个人界面的跳转

        social = (Button) findViewById(R.id.button24);
        social.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Usual_web.this, Social.class);
                startActivityForResult(intent, 0);
            }
        });//完成到社区的跳转

        search = (Button) findViewById(R.id.button25);
        search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Usual_web.this, Search.class);
                startActivityForResult(intent, 0);
            }
        });//完成到搜索的跳转

        hot = (Button) findViewById(R.id.button30);
        hot.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Usual_web.this, Hot.class);
                startActivityForResult(intent, 0);
            }
        });//完成到首页热门的跳转

        show = (WebView) findViewById(R.id.show);

        web1= (Button) findViewById(R.id.button50);
        web1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String query1="https://dappei.com/articles/2728";
                show.loadUrl(query1);
            }
        });//显示常用网站1

        web2= (Button) findViewById(R.id.button49);
        web2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String query2="http://photo.pclady.com.cn/cate/1339/1.html";
                show.loadUrl(query2);
            }
        });//显示常用网站2

        web3= (Button) findViewById(R.id.button47);
        web3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String query2="http://www.yoka.com/club/";
                show.loadUrl(query2);
            }
        });//显示常用网站3


    }
}
