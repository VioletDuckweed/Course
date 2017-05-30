package com.example.look01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;

public class Upload extends AppCompatActivity {
    private Button register;
    private Button mainpage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        register = (Button) findViewById(R.id.button2);
        register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Upload.this, Register.class);
                startActivityForResult(intent, 0);
            }
        });
        mainpage = (Button) findViewById(R.id.button);
        mainpage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Upload.this, Hot.class);
                startActivityForResult(intent, 0);
            }
        });
    }

}
