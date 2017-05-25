package com.example.administrator.mytext.Fragment.FragmentForOutflow;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.mytext.Activity_outflow_publish;
import com.example.administrator.mytext.R;

/**
 * Created by Administrator on 2017/3/18.
 */

public class OutflowFragment extends Fragment {
    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.outflow_fragment, container, false);
        RelativeLayout rl = (RelativeLayout)view.findViewById(R.id.outflow_fragment_RL1);
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Activity_outflow_publish.class);
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
