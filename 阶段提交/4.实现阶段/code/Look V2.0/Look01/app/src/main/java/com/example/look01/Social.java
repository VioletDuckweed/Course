package com.example.look01;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.os.Message;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ListView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import java.util.List;
import android.os.Handler;
import android.widget.Toast;

public class Social extends AppCompatActivity {

    private List<News> newsList;
    private NewsAdapter adapter;
    private Handler handler;
    private ListView lv;

    private Button user;//用户按钮定义
    private Button social;//社区按钮定义
    private Button search;//搜索按钮定义
    private Button hot;//首页按钮定义
    private Button chat;//社区聊天功能按钮定义

    //定义和摇一摇功能有关的
    private SensorManager sensorManager;
    private Vibrator vibrator;

    private static final String TAG = "TestSensorActivity";
    private static final int SENSOR_SHAKE = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        newsList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.news_lv);
        getNews();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    adapter = new NewsAdapter(Social.this, newsList);
                    lv.setAdapter(adapter);
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            News news = newsList.get(position);
                            Intent intent = new Intent(Social.this, NewsDisplayActvivity.class);
                            intent.putExtra("news_url", news.getNewsUrl());
                            startActivity(intent);
                        }
                    });
                }
            }
        };

        chat = (Button) findViewById(R.id.button10);
        chat.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Social.this, Chat.class);
                startActivityForResult(intent, 0);
            }
        });//完成到聊天界面的跳转

        user = (Button) findViewById(R.id.button31);
        user.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Social.this, user.class);
                startActivityForResult(intent, 0);
            }
        });//完成到用户个人界面的跳转

        social = (Button) findViewById(R.id.button44);
        social.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Social.this, Social.class);
                startActivityForResult(intent, 0);
            }
        });//完成到社区的跳转

        search = (Button) findViewById(R.id.button45);
        search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Social.this, Search.class);
                startActivityForResult(intent, 0);
            }
        });//完成到搜索的跳转

        hot = (Button) findViewById(R.id.button46);
        hot.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Social.this, Hot.class);
                startActivityForResult(intent, 0);
            }
        });//完成到首页热门的跳转
    }

    private void getNews(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    //获取虎扑新闻20页的数据，网址格式为：https://voice.hupu.com/nba/第几页
                    for(int i = 1;i<=5;i++) {

                        Document doc = Jsoup.connect("https://voice.hupu.com/nba/" + Integer.toString(i)).get();
                        // Document doc = Jsoup.connect("http://www.yoka.com/club/" + Integer.toString(i)).get();
                        Elements titleLinks = doc.select("div.list-hd");    //解析来获取每条新闻的标题与链接地址
                        // Elements descLinks = doc.select("div.list-content");//解析来获取每条新闻的简介
                        Elements timeLinks = doc.select("div.otherInfo");   //解析来获取每条新闻的时间与来源
                        Log.e("title",Integer.toString(titleLinks.size()));
                        for(int j = 0;j < titleLinks.size();j++){
                            String title = titleLinks.get(j).select("a").text();
                            String uri = titleLinks.get(j).select("a").attr("href");
                            //   String desc = descLinks.get(j).select("span").text();
                            String time = timeLinks.get(j).select("span.other-left").select("a").text();
                            News news = new News(title,uri,null,time);
                            newsList.add(news);
                        }
                    }
                    Message msg = new Message();
                    msg.what = 1;
                    handler.sendMessage(msg);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sensorManager != null) {// 注册监听器
            sensorManager.registerListener(sensorEventListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
            // 第一个参数是Listener，第二个参数是所得传感器类型，第三个参数值获取传感器信息的频率
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (sensorManager != null) {// 取消监听器
            sensorManager.unregisterListener(sensorEventListener);
        }
    }
    /**
     * 重力感应监听
     */
    private SensorEventListener sensorEventListener = new SensorEventListener() {

        @Override
        public void onSensorChanged(SensorEvent event) {
            // 传感器信息改变时执行该方法
            float[] values = event.values;
            float x = values[0]; // x轴方向的重力加速度，向右为正
            float y = values[1]; // y轴方向的重力加速度，向前为正
            float z = values[2]; // z轴方向的重力加速度，向上为正
            Log.i(TAG, "x轴方向的重力加速度" + x +  "；y轴方向的重力加速度" + y +  "；z轴方向的重力加速度" + z);
            // 一般在这三个方向的重力加速度达到40就达到了摇晃手机的状态。
            int medumValue = 19;// 三星 i9250怎么晃都不会超过20，没办法，只设置19了
            if (Math.abs(x) > medumValue || Math.abs(y) > medumValue || Math.abs(z) > medumValue) {
                vibrator.vibrate(200);
                Message msg = new Message();
                msg.what = SENSOR_SHAKE;
                handler2.sendMessage(msg);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
    Handler handler2 = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SENSOR_SHAKE:
                    Toast.makeText(Social.this, "开启相机，请开始你的表演", Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "开启相机，请开始你的表演");
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 1);
                    break;
            }
        }

    };
}
