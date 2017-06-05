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

public class Keywords extends AppCompatActivity {

    private Button user;//用户按钮定义
    private Button social;//社区按钮定义
    private Button search;//搜索按钮定义
    private Button hot;//首页按钮定义
    private Button usual_web;//常用网站按钮定义
    private Button keywords;//关键字按钮定义
    private SearchView sv;
    private ListView lv;
    // 自动完成的列表
    private WebView show;
    private final String[] mStrings = { "aaaaa", "bbbbbb", "cccccc" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keywords);

        keywords = (Button) findViewById(R.id.button22);
        keywords.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Keywords.this, Keywords.class);
                startActivityForResult(intent, 0);
            }
        });//完成关键字的跳转

        usual_web = (Button) findViewById(R.id.button21);
        usual_web.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Keywords.this, Usual_web.class);
                startActivityForResult(intent, 0);
            }
        });//完成分类的跳转

        user = (Button) findViewById(R.id.button23);
        user.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Keywords.this, user.class);
                startActivityForResult(intent, 0);
            }
        });//完成到用户个人界面的跳转

        social = (Button) findViewById(R.id.button24);
        social.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Keywords.this, Social.class);
                startActivityForResult(intent, 0);
            }
        });//完成到社区的跳转

        search = (Button) findViewById(R.id.button25);
        search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Keywords.this, Search.class);
                startActivityForResult(intent, 0);
            }
        });//完成到搜索的跳转

        hot = (Button) findViewById(R.id.button30);
        hot.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Keywords.this, Hot.class);
                startActivityForResult(intent, 0);
            }
        });//完成到首页热门的跳转

        show = (WebView) findViewById(R.id.show);

        lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mStrings));
        // 设置ListView启用过滤
        lv.setTextFilterEnabled(true);
        sv = (SearchView) findViewById(R.id.sv);
        // 设置该SearchView默认是否自动缩小为图标
        sv.setIconifiedByDefault(false);
        // 设置该SearchView显示搜索按钮
        sv.setSubmitButtonEnabled(true);
        // 设置该SearchView内默认显示的提示文本
        sv.setQueryHint("查找");
        // 为该SearchView组件设置事件监听器
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 用户输入字符时激发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                // 如果newText不是长度为0的字符串
                if (TextUtils.isEmpty(newText)) {
                    // 清除ListView的过滤
                    lv.clearTextFilter();
                } else {
                    // 使用用户输入的内容对ListView的列表项进行过滤
                    lv.setFilterText(newText);
                }
                return true;
            }

            // 单击搜索按钮时激发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                // 实际应用中应该在该方法内执行实际查询
                show.loadUrl(query);
                // 此处仅使用Toast显示用户输入的查询内容
                Toast.makeText(Keywords.this, "您的选择是:" + query
                        , Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}
