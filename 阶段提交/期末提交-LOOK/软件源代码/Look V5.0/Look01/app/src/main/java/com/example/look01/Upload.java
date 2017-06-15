package com.example.look01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

public class Upload extends AppCompatActivity {
    private Button register;
    private Button mainpage;
    private EditText id;
    private EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        id=(EditText)findViewById(R.id.login_id);
        password=(EditText)findViewById(R.id.login_psd);
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
                String userid=id.getText().toString();
                String pas=password.getText().toString();
                if(userid.equals("123"))
                {
                    if(pas.equals("123")) {
                        Intent intent = new Intent();
                        intent.setClass(Upload.this, Hot.class);
                        startActivityForResult(intent, 0);
                    }
                    else
                        Toast.makeText(getApplicationContext(),"请输入正确密码，登录失败",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"用户不存在，登录失败",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
