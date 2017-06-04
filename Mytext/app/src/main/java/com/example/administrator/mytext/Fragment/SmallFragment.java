package com.example.administrator.mytext.Fragment;


import android.app.AlertDialog;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.administrator.mytext.Activity_Search;
import com.example.administrator.mytext.Activity_allForest;
import com.example.administrator.mytext.R;

public class SmallFragment extends Fragment{


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.viewpager_button, container, false);


        Button btn_all = (Button)view.findViewById(R.id.btn_all);
        btn_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_allForest.class);
                startActivity(intent);
            }
        });

        Button btn_search = (Button)view.findViewById(R.id.btn_search);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_Search.class);
                startActivity(intent);
            }
        });


        Button btn_seek_price = (Button)view.findViewById(R.id.btn_seek_price);
        btn_seek_price.setOnClickListener(new View.OnClickListener() {
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
//		System.out.println("OneFragment  onPause");
        // TODO Auto-generated method stub
        super.onPause();
    }

    @Override
    public void onResume() {
//		System.out.println("OneFragment  onResume");
        // TODO Auto-generated method stub
        super.onResume();
    }

    @Override
    public void onDestroy() {
//		System.out.println("OneFragment  onDestroy");
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
//		System.out.println("OneFragment  onDestroyView");
        // TODO Auto-generated method stub
        super.onDestroyView();
    }

    @Override
    public void onStop() {
//		System.out.println("OneFragment  onStop");
        // TODO Auto-generated method stub
        super.onStop();
    }

    @Override
    public void onStart() {
//		System.out.println("OneFragment  onStart");
        // TODO Auto-generated method stub
        super.onStart();
    }


}
