package com.example.administrator.mytext.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.administrator.mytext.Activity_Register_Login;
import com.example.administrator.mytext.Activity_outflow;
import com.example.administrator.mytext.Database.Now_user;
import com.example.administrator.mytext.MainActivity;
import com.example.administrator.mytext.R;

/**
 * Created by Administrator on 2017/3/12.
 */

public class SmallFragment2 extends Fragment{

    private Button btn_contact,btn_forest_assess;
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
//		System.out.println("ThreeFragment onCreateView");
        view = inflater.inflate(R.layout.viewpager_button2, container, false);
        Button btn_wylc = (Button)view.findViewById(R.id.btn_wylc);
        btn_contact = (Button)view.findViewById(R.id.btn_6);
        btn_wylc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_outflow.class);
                startActivity(intent);
            }
        });
        btn_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setTitle("提示");
                dialog.setMessage("拨打客服电话:17826876397");
                dialog.setCancelable(false);
                dialog.setPositiveButton("确认",new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:17826876397"));
                        startActivity(intent);
                    }
                });
                dialog.setNegativeButton("取消",null);
                dialog.show();

            }
        });
        btn_forest_assess = (Button)view.findViewById(R.id.btn_forest_assess);
        btn_forest_assess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setTitle("提示");
                dialog.setMessage("该功能正在开发中......");
                dialog.setCancelable(false);
                dialog.setPositiveButton("确认",null);
                dialog.show();
            }
        });
        return view;
    }



    @Override
    public void onPause() {
//		System.out.println("ThreeFragment  onPause");
        // TODO Auto-generated method stub
        super.onPause();
    }

    @Override
    public void onResume() {
//		System.out.println("ThreeFragment  onResume");
        // TODO Auto-generated method stub
        super.onResume();
    }

    @Override
    public void onDestroy() {
//		System.out.println("ThreeFragment  onDestroy");
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
//		System.out.println("ThreeFragment  onDestroyView");
        // TODO Auto-generated method stub
        super.onDestroyView();
    }

    @Override
    public void onStop() {
//		System.out.println("ThreeFragment  onStop");
        // TODO Auto-generated method stub
        super.onStop();
    }

    @Override
    public void onStart() {
//		System.out.println("ThreeFragment  onStart");
        // TODO Auto-generated method stub
        super.onStart();
    }
}
