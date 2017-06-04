package com.example.administrator.mytext.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.mytext.Activity_Register_Login;
import com.example.administrator.mytext.Database.Now_user;
import com.example.administrator.mytext.MainActivity;
import com.example.administrator.mytext.R;

/**
 * Created by Administrator on 2017/3/8.
 */

public class MineFragment extends Fragment {

    private Button btn_user_login,btn_no_login;
    private TextView tv;


    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.minefragment, container, false);
        if (Now_user.User_id == "")
        {
            dialog();
        }
        btn_no_login = (Button) view.findViewById(R.id.btn_user_no_login);

        btn_no_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Now_user.User_id = "";
                Now_user.User_pwd = "";
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setTitle("提示");
                dialog.setMessage("恭喜你，注销账号成功");
                dialog.setCancelable(true);
                dialog.setPositiveButton("确认",null);
                dialog.show();
            }
        });
        return view;
    }

    public void dialog()
    {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle("提示");
        dialog.setMessage("您尚未登录！");
        dialog.setCancelable(false);
        dialog.setPositiveButton("登录", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getActivity(), Activity_Register_Login.class);
                startActivity(intent);
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        dialog.show();
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

}
