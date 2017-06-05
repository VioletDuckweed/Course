package com.example.look01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;

public class user extends AppCompatActivity {

    private Button user;//用户按钮定义
    private Button social;//社区按钮定义
    private Button search;//搜索按钮定义
    private Button hot;//首页按钮定义
    private Button back;//退出按钮定义
    private Button connect;//定义个人收藏按钮定义
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        back = (Button) findViewById(R.id.button11);
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent MyIntent = new Intent(Intent.ACTION_MAIN);
                MyIntent.addCategory(Intent.CATEGORY_HOME);
                startActivity(MyIntent);
                finish();
            }
        });//完成退出

        user = (Button) findViewById(R.id.button4);
        user.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(user.this, user.class);
                startActivityForResult(intent, 0);
            }
        });//完成到用户个人界面的跳转

        user = (Button) findViewById(R.id.button8);
        user.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(user.this, Reviseinfo.class);
                startActivityForResult(intent, 0);
            }
        });//完成到用户个人界面的跳转

        social = (Button) findViewById(R.id.button5);
        social.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(user.this, Social.class);
                startActivityForResult(intent, 0);
            }
        });//完成到社区的跳转

        search = (Button) findViewById(R.id.button6);
        search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(user.this, Search.class);
                startActivityForResult(intent, 0);
            }
        });//完成到搜索的跳转

        hot = (Button) findViewById(R.id.button7);
        hot.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(user.this, Hot.class);
                startActivityForResult(intent, 0);
            }
        });//完成到首页热门的跳转

        connect = (Button) findViewById(R.id.button9);
        connect.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(user.this, Connect.class);
                startActivityForResult(intent, 0);
            }
        });//完成到个人收藏的跳转
    }
}
