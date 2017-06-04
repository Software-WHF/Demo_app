package com.example.administrator.mytext.Fragment.FragmentForDetail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.mytext.R;

/**
 * Created by Administrator on 2017/3/12.
 */

public class Forest_basicInfomation extends Fragment {
    @NonNull

    private TextView tv_basic_position;
    private TextView tv_basic_use;
    private TextView tv_basic_area;
    private TextView tv_basic_time;
    private TextView tv_basic_method;
    @Override
    public View onCreateView(LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.forest_basic_infomation, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        init();
        String area = getArguments().getString("area");
        String way = getArguments().getString("way");
        String position = getArguments().getString("position");
        String time = getArguments().getString("time");
        String use = getArguments().getString("use");

        tv_basic_area.setText(area);
        tv_basic_use.setText(use);
        tv_basic_time.setText(time);
        tv_basic_method.setText(way);
        tv_basic_position.setText(position);
    }
    void init()
    {
        tv_basic_area = (TextView) getView().findViewById(R.id.basic_area);
        tv_basic_method = (TextView)getView().findViewById(R.id.basic_method);
        tv_basic_position = (TextView)getView().findViewById(R.id.basic_position);
        tv_basic_time = (TextView)getView().findViewById(R.id.basic_time);
        tv_basic_use = (TextView)getView().findViewById(R.id.basic_use);
    }

}
