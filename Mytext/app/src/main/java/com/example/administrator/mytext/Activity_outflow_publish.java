package com.example.administrator.mytext;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.provider.MediaStore.Images.Media;
import android.widget.Toast;

import com.example.administrator.mytext.Database.Constant;
import com.example.administrator.mytext.Database.Now_user;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Locale;
import android.text.format.DateFormat;


import java.net.URL;

/**
 * Created by Administrator on 2017/3/19.
 */

public class Activity_outflow_publish extends Activity {
    public EditText edit_quyu,edit_dizhi,edit_yongtu,edit_mianji,edit_nianxian,edit_jiage,edit_biaoti,edit_xiangqing,edit_jiesao,edit_chengwei,edit_lian_xi_fang_shi;
    public Button btn_ok;
    public ImageView add_picture,My_picture,My_picture2,My_picture3,My_picture4;
    public String imagePath = "",result = "";
    private Handler handler = new Handler()
    {
        public void handleMessage(Message msg) {
            switch(msg.what)
            {
                case 1:
                    if(msg.obj.toString().trim().equals("1"))
                    {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(Activity_outflow_publish.this);
                        dialog.setTitle("提示");
                        dialog.setMessage("发布成功");
                        dialog.setCancelable(false);
                        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                init();
                            }
                        });
                        dialog.show();

                    }
                    else
                    {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(Activity_outflow_publish.this);
                        dialog.setTitle("提示");
                        dialog.setMessage("抱歉，添加失败");
                        dialog.setCancelable(false);
                        dialog.setPositiveButton("确定",null);
                        dialog.show();

                    }
                    break;

            }

        }


    };
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outfolw_publish);
        edit_quyu = (EditText)findViewById(R.id.edit_quyu);
        edit_dizhi = (EditText)findViewById(R.id.edit_dizhi);
        edit_yongtu = (EditText)findViewById(R.id.edit_yongtu);
        edit_mianji = (EditText)findViewById(R.id.edit_mianji);
        edit_nianxian = (EditText)findViewById(R.id.edit_liu_zhuan_nian_xian);
        edit_jiage = (EditText)findViewById(R.id.edit_jia_ge);
        edit_biaoti = (EditText)findViewById(R.id.edit_biao_ti);
        edit_xiangqing = (EditText)findViewById(R.id.edit_xiang_qing);
        edit_jiesao = (EditText)findViewById(R.id.edit_jie_sao);
        edit_chengwei = (EditText)findViewById(R.id.edit_cheng_wei);
        edit_lian_xi_fang_shi = (EditText)findViewById(R.id.edit_lian_xi_fang_shi);
        add_picture = (ImageView)findViewById(R.id.add_picture);
        My_picture = (ImageView)findViewById(R.id.my_picture);
        My_picture2 = (ImageView)findViewById(R.id.my_picture2);
        My_picture3 = (ImageView)findViewById(R.id.my_picture3);
        My_picture4 = (ImageView)findViewById(R.id.my_picture4);


        btn_ok = (Button)findViewById(R.id.btn_fabu_lindi);
        add_picture.setOnClickListener(new View.OnClickListener() {     //添加图片
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent,1);

            }
        });

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Now_user.User_id.equals(""))
                {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(Activity_outflow_publish.this);
                    dialog.setTitle("警告");
                    dialog.setMessage("你还没登录，是否跳转到登录界面？");
                    dialog.setCancelable(false);
                    dialog.setPositiveButton("确认",new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Activity_outflow_publish.this, Activity_Register_Login.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                    dialog.setNegativeButton("取消",null);
                    dialog.show();

                }
                else {

                    final String quyu = edit_quyu.getText().toString();
                    final String dizhi = edit_dizhi.getText().toString();
                    final String yongtu = edit_yongtu.getText().toString();
                    final String mianji = edit_mianji.getText().toString();
                    final String nian_xian = edit_nianxian.getText().toString();
                    final String jiage = edit_jiage.getText().toString();
                    final String biaoti = edit_biaoti.getText().toString();
                    final String xiangqing = edit_xiangqing.getText().toString();
                    final String jiesao = edit_jiesao.getText().toString();
                    final String chengwei = edit_chengwei.getText().toString();
                    final String lianxifangshi = edit_lian_xi_fang_shi.getText().toString();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            result = Upload(Constant.URL_Upload, imagePath);
                            Message message = new Message();
                            message.what = 1;
                            message.obj = result;
                            handler.sendMessage(message);
                        }
                    }).start();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String land_id = Now_user.User_id + "_" + new DateFormat().format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA));
                            String land_release_time = new DateFormat().format("yyyyMMdd", Calendar.getInstance(Locale.CHINA)) + "";
                            final String[] id = {land_id};
                            final String[] quyu1 = {quyu + "" + dizhi};
                            final String[] yongtu1 = {yongtu};
                            final String[] mianji1 = {mianji};
                            final String[] nianxian = {nian_xian};
                            final String[] jiage1 = {jiage};
                            final String[] biaoti1 = {biaoti};
                            final String[] xiangqing1 = {xiangqing};
                            final String[] jiesao1 = {jiesao};
                            final String[] chengwei1 = {chengwei};
                            final String[] lianxifangshi1 = {lianxifangshi};
                            final String[] user_id = {Now_user.User_id};
                            final String[] result1 = {result};
                            final String[] release_time1 = {land_release_time};

                            HttpURLConnection connection = null;
                            try {
                                id[0] = URLEncoder.encode(id[0], "utf-8");
                                quyu1[0] = URLEncoder.encode(quyu1[0], "utf-8");
                                yongtu1[0] = URLEncoder.encode(yongtu1[0], "utf-8");
                                mianji1[0] = URLEncoder.encode(mianji1[0], "utf-8");
                                nianxian[0] = URLEncoder.encode(nianxian[0], "utf-8");
                                jiage1[0] = URLEncoder.encode(jiage1[0], "utf-8");
                                biaoti1[0] = URLEncoder.encode(biaoti1[0], "utf-8");
                                xiangqing1[0] = URLEncoder.encode(xiangqing1[0], "utf-8");
                                jiesao1[0] = URLEncoder.encode(jiesao1[0], "utf-8");
                                chengwei1[0] = URLEncoder.encode(chengwei1[0], "utf-8");
                                lianxifangshi1[0] = URLEncoder.encode(lianxifangshi1[0], "utf-8");
                                user_id[0] = URLEncoder.encode(user_id[0], "utf-8");
                                release_time1[0] = URLEncoder.encode(release_time1[0], "utf-8");

                            } catch (UnsupportedEncodingException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                            try {
                                URL url = new URL(Constant.URL_Addland + "?land_id=" + id[0] + "&land_position=" + quyu1[0] + "&land_use=" + yongtu1[0] + "&land_area=" + mianji1[0] + "&land_time=" + nianxian[0] + "&land_money=" + jiage1[0] + "&land_inner_id=" + biaoti1[0] + "&land_detail=" + xiangqing1[0] + "&land_introduction=" + jiesao1[0] + "&user_id=" + user_id[0] + "&user_name=" + chengwei1[0] + "&user_phone=" + lianxifangshi1[0]  + "&land_release_time=" + release_time1[0]);
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

            }
        });



    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        switch(requestCode)
        {

            case 1:
                Uri uri = null;
                if (data == null) {
                    return;
                }
                if(resultCode == RESULT_OK)
                {
                    if(Build.VERSION.SDK_INT >= 19)
                    {
                        imagePath = handleImageOnKitKat(data);
                        displayImage(imagePath);

                    }
                    else
                    {
                        imagePath = handleImageBeforeKitKat(data);
                        displayImage(imagePath);
                    }


                }
                break;

        }
    }

    @TargetApi(19)
    protected String handleImageOnKitKat(Intent data)
    {
        String imagePath = null;
        Uri uri = data.getData();
        if(DocumentsContract.isDocumentUri(this, uri))
        {
            String docId =  DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority()))
            {
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID+ "=" +id;
            }
            else if("com.android.providers.downloads.documents".equals(uri.getAuthority()))
            {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri,null);

            }
        }
        else if("content".equalsIgnoreCase(uri.getScheme()))
        {
            imagePath = getImagePath(uri,null);

        }
        return imagePath;
    }

    private String getImagePath(Uri uri,String selection)
    {
        String path = null;
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if(cursor!=null)
        {
            if(cursor.moveToFirst())
            {
                path = cursor.getString( cursor.getColumnIndex(Media.DATA) );
            }
            cursor.close();
        }
        return path;
    }

    private String handleImageBeforeKitKat(Intent data)
    {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri,null);
        return imagePath;
    }

    private void displayImage(String imagePath)
    {
        if(imagePath != null)
        {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            My_picture.setImageBitmap(bitmap);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "失败了", Toast.LENGTH_SHORT).show();
        }
    }

    public String Upload(String urlStr, String filePath)
    {
        String rsp = "";
        HttpURLConnection conn = null;
        String BOUNDARY = "|";
        try {
            URL url = new URL(urlStr+"?user_id="+Now_user.User_id);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(30000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
            conn.setRequestProperty("Content-Type",
                    "multipart/form-data; boundary=" + BOUNDARY);

            OutputStream out = new DataOutputStream(conn.getOutputStream());
            File file = new File(filePath);
            String filename = file.getName();
            String contentType = "";
            if (filename.endsWith(".png")) {
                contentType = "image/png";
            }
            if (filename.endsWith(".jpg")) {
                contentType = "image/jpg";
            }
            if (filename.endsWith(".gif")) {
                contentType = "image/gif";
            }
            if (filename.endsWith(".bmp")) {
                contentType = "image/bmp";
            }
            if (contentType == null || contentType.equals("")) {
                contentType = "application/octet-stream";
            }
            StringBuffer strBuf = new StringBuffer();
            strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
            strBuf.append("Content-Disposition: form-data; name=\"" + filePath
                    + "\"; filename=\"" + filename + "\"\r\n");
            strBuf.append("Content-Type:" + contentType + "\r\n\r\n");
            out.write(strBuf.toString().getBytes());
            DataInputStream in = new DataInputStream(new FileInputStream(file));
            int bytes = 0;
            byte[] bufferOut = new byte[1024];
            while ((bytes = in.read(bufferOut)) != -1) {
                out.write(bufferOut, 0, bytes);
            }
            in.close();
            byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
            out.write(endData);
            out.flush();
            out.close();

            // 读取返回数据
            StringBuffer buffer = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line = null;
            while ((line = reader.readLine()) != null) {
                buffer.append(line).append("\n");
            }
            rsp = buffer.toString();
            reader.close();
            reader = null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
                conn = null;
            }
        }
        return rsp;
    }

    public void back(View view)
    {
        overridePendingTransition(R.anim.push_right_in,
                R.anim.push_right_out);
        finish();
    }
    public void init()
    {
        edit_quyu.setText("");
        edit_dizhi.setText("");
        edit_yongtu.setText("");
        edit_mianji.setText("");
        edit_nianxian.setText("");
        edit_jiage.setText("");
        edit_biaoti.setText("");
        edit_xiangqing.setText("");
        edit_jiesao.setText("");
        edit_chengwei.setText("");
        edit_lian_xi_fang_shi.setText("");
        My_picture.setImageDrawable(null);
    }
}
