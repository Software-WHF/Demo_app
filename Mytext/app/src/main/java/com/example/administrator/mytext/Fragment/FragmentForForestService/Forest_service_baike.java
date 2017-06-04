package com.example.administrator.mytext.Fragment.FragmentForForestService;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.administrator.mytext.R;

/**
 * Created by Administrator on 2017/5/13.
 */

public class Forest_service_baike extends Fragment{

    private RelativeLayout rl_attention;
    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.forest_service_baike_fragment, container, false);

        rl_attention = (RelativeLayout)view.findViewById(R.id.rl_attention);
        rl_attention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Activity_attention.class);
                startActivity(intent);
            }
        });
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }
}
