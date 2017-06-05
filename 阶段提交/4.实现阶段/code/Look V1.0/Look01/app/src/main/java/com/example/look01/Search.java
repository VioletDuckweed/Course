package com.example.look01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;

public class Search extends AppCompatActivity {

    private Button scene;//分类按钮定义
    private Button keywords;//关键字按钮定义
    private Button user;//用户按钮定义
    private Button social;//社区按钮定义
    private Button search;//搜索按钮定义
    private Button hot;//首页按钮定义
    private Button age0;//0-15岁的按钮定义
    private Button age1;//15-20岁的按钮定义
    private Button age2;//20-30岁的按钮定义
    private Button age3;//30-40岁的按钮定义
    private Button age4;//40-50岁的按钮定义
    private Button age5;//50岁以上的按钮定义
    private Button sty1;
    private Button sty2;
    private Button sty3;
    private Button sty4;
    private Button sty5;
    private Button sty6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        scene = (Button) findViewById(R.id.button19);
        scene.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Search.this, Search.class);
                startActivityForResult(intent, 0);
            }
        });//完成分类的跳转

        keywords = (Button) findViewById(R.id.button20);
        keywords.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Search.this, Keywords.class);
                startActivityForResult(intent, 0);
            }
        });//完成关键字的跳转

        user = (Button) findViewById(R.id.button29);
        user.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Search.this, user.class);
                startActivityForResult(intent, 0);
            }
        });//完成到用户个人界面的跳转

        social = (Button) findViewById(R.id.button28);
        social.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Search.this, Social.class);
                startActivityForResult(intent, 0);
            }
        });//完成到社区的跳转

        search = (Button) findViewById(R.id.button27);
        search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Search.this, Search.class);
                startActivityForResult(intent, 0);
            }
        });//完成到搜索的跳转

        hot = (Button) findViewById(R.id.button26);
        hot.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Search.this, Hot.class);
                startActivityForResult(intent, 0);
            }
        });//完成到首页热门的跳转

        age0 = (Button) findViewById(R.id.button34);
        age0.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Search.this, Example.class);
                startActivityForResult(intent, 0);
            }
        });//完成到场景界面0-15岁

        age1 = (Button) findViewById(R.id.button33);
        age1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Search.this, Example2.class);
                startActivityForResult(intent, 0);
            }
        });//完成到场景界面15-20岁

        age2 = (Button) findViewById(R.id.button32);
        age2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Search.this, Example3.class);
                startActivityForResult(intent, 0);
            }
        });//完成到场景界面20-30岁

        age3 = (Button) findViewById(R.id.button36);
        age3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Search.this, Example4.class);
                startActivityForResult(intent, 0);
            }
        });//完成到场景界面30-40岁

        age4 = (Button) findViewById(R.id.button35);
        age4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Search.this, Example5.class);
                startActivityForResult(intent, 0);
            }
        });//完成到场景界面40-50岁

        age5 = (Button) findViewById(R.id.button37);
        age5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Search.this, Example6.class);
                startActivityForResult(intent, 0);
            }
        });//完成到场景界面50岁以上

        sty1 = (Button) findViewById(R.id.button40);
        sty1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Search.this, Style1.class);
                startActivityForResult(intent, 0);
            }
        });

        sty2 = (Button) findViewById(R.id.button39);
        sty2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Search.this, Style2.class);
                startActivityForResult(intent, 0);
            }
        });

        sty3 = (Button) findViewById(R.id.button38);
        sty3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Search.this, Style3.class);
                startActivityForResult(intent, 0);
            }
        });

        sty4 = (Button) findViewById(R.id.button43);
        sty4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Search.this, Style4.class);
                startActivityForResult(intent, 0);
            }
        });

        sty5 = (Button) findViewById(R.id.button42);
        sty5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Search.this, Style5.class);
                startActivityForResult(intent, 0);
            }
        });

        sty6 = (Button) findViewById(R.id.button41);
        sty6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Search.this, Style6.class);
                startActivityForResult(intent, 0);
            }
        });
    }
}
