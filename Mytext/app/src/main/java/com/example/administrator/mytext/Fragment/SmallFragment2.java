package com.example.administrator.mytext.Fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.administrator.mytext.Activity_outflow;
import com.example.administrator.mytext.R;

/**
 * Created by Administrator on 2017/3/12.
 */

public class SmallFragment2 extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
//		System.out.println("ThreeFragment onCreateView");
        View view = inflater.inflate(R.layout.viewpager_button2, container, false);
        Button btn_wylc = (Button)view.findViewById(R.id.btn_wylc);
        btn_wylc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_outflow.class);
                startActivity(intent);
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
