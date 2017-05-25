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

public class Forest_Introduction extends Fragment {
    @NonNull
    private TextView forest_introduction;

    @Override
    public View onCreateView(LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.forest_introduction, container, false);

        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        forest_introduction = (TextView) getView().findViewById(R.id.forest_introduction);
        String introduction = getArguments().getString("introduction");
        forest_introduction.setText("\t\t\t\t"+introduction);
    }

}
