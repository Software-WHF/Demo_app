package com.example.administrator.mytext;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.mytext.Database.Constant;
import com.example.administrator.mytext.Database.Now_user;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2017/4/23.
 */

public class Activity_Register_Login extends Activity {
    private Button btn_login, btn_register;
    private EditText user_id, user_password;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_login);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_register = (Button) findViewById(R.id.btn_register);
        user_id = (EditText) findViewById(R.id.user_id);
        user_password = (EditText) findViewById(R.id.user_password);

        handler = new Handler()
        {
            public void handleMessage(Message msg)
            {
                switch (msg.what)
                {
                    case 1:
                        if(msg.obj.equals("注册成功"))
                        {
                            AlertDialog.Builder dialog = new AlertDialog.Builder(Activity_Register_Login.this);
                            dialog.setTitle("提示");
                            dialog.setMessage("注册成功!");
                            dialog.setCancelable(false);
                            dialog.setPositiveButton("确认",new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Now_user.User_id = user_id.getText().toString().trim();
                                    Now_user.User_pwd = user_password.getText().toString().trim();
                                    Intent intent = new Intent(Activity_Register_Login.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();

                                }
                            });
                            dialog.show();
                        }
                        else
                        {
                            AlertDialog.Builder dialog = new AlertDialog.Builder(Activity_Register_Login.this);
                            dialog.setTitle("提示");
                            dialog.setMessage("注册失败,请重试!");
                            dialog.setCancelable(false);
                            dialog.setPositiveButton("确定",null);
                            dialog.show();
                        }
                        break;
                    case 2:
                        if(msg.obj.equals("0001"))
                        {
                            Now_user.User_id = user_id.getText().toString().trim();
                            Now_user.User_pwd = user_password.getText().toString().trim();
                            Intent intent = new Intent(Activity_Register_Login.this, MainActivity.class);
                            startActivity(intent);
                            finish();

                        }
                        else
                        {
                            AlertDialog.Builder dialog = new AlertDialog.Builder(Activity_Register_Login.this);
                            dialog.setTitle("提示");
                            dialog.setMessage("账号或者密码错误,请重试!");
                            dialog.setCancelable(false);
                            dialog.setPositiveButton("确定",null);
                            dialog.show();
                        }
                        break;
                }
            }

        };


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user_id.getText().toString().equals("") || user_password.getText().toString().equals(""))
                {
                    Toast.makeText(Activity_Register_Login.this,"账号和密码不能为空",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Register(user_id.getText().toString(),user_password.getText().toString());
                }
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user_id.getText().toString().equals("") || user_password.getText().toString().equals(""))
                {
                    Toast.makeText(Activity_Register_Login.this,"账号和密码不能为空",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Login(user_id.getText().toString().trim(),user_password.getText().toString().trim());
                }
            }
        });

    }

    private void Register(String user_id,String user_password)
    {
        final String[] name = {user_id};
        final String []mima = {user_password};
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    name[0] = URLEncoder.encode(name[0], "utf-8");
                    mima[0] = URLEncoder.encode(mima[0], "utf-8");
                    // 如有中文一定要加上，在接收方用相应字符转码即可
                } catch (UnsupportedEncodingException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                try
                {
                    URL url = new URL(Constant.URL_Register+"?user_id="+ name[0] +"&user_password="+mima[0]);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(80000);
                    connection.setReadTimeout(80000);
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    Message message = new Message();
                    message.what = 1;
                    message.obj = response.toString();
                    handler.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void Login(String user_id,String user_password)
    {
        final String[] name = {user_id};
        final String []mima = {user_password};
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    name[0] = URLEncoder.encode(name[0], "utf-8");
                    mima[0] = URLEncoder.encode(mima[0], "utf-8");
                    // 如有中文一定要加上，在接收方用相应字符转码即可
                } catch (UnsupportedEncodingException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                try
                {
                    URL url = new URL(Constant.URL_Login+"?user_id="+ name[0] +"&user_password="+mima[0]);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(80000);
                    connection.setReadTimeout(80000);
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    Message message = new Message();
                    message.what = 2;
                    message.obj = response.toString();
                    handler.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void back(View view)
    {
        overridePendingTransition(R.anim.push_right_in,
                R.anim.push_right_out);
        Intent intent2 = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent2);
        finish();
    }
}

