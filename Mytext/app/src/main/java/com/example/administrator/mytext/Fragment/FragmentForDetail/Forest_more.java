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

public class Forest_more extends Fragment {
    @NonNull
    private TextView credential;
    private TextView soil_condition;
    private TextView equipment;
    private TextView environment;
    private TextView management;
    private TextView policy;


    @Override
    public View onCreateView(LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.forest_more, container, false);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        init();
        String credential1 = getArguments().getString("credential");
        String equipment1 = getArguments().getString("equipment");
        String environment1 = getArguments().getString("environment");
        String management1 = getArguments().getString("management");
        String policy1 = getArguments().getString("policy");
        String detial1 = getArguments().getString("detail");
        credential.setText(credential1);
        soil_condition.setText(detial1);
        equipment.setText(equipment1);
        environment.setText(environment1);
        management.setText(management1);
        policy.setText(policy1);
    }
    void init()
    {
        credential = (TextView)getView().findViewById(R.id.land_credential);
        soil_condition = (TextView)getView().findViewById(R.id.land_soil_condition);
        equipment = (TextView)getView().findViewById(R.id.land_equipment);
        environment = (TextView)getView().findViewById(R.id.land_environment);
        management = (TextView)getView().findViewById(R.id.land_management);
        policy = (TextView)getView().findViewById(R.id.land_policy);
    }
}
