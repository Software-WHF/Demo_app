package com.example.administrator.mytext.Adapter;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import com.example.administrator.mytext.Database.Land2;
import com.example.administrator.mytext.R;

import java.util.List;

import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class Adapter_land extends ArrayAdapter<Land2>{
    private int _resourceId;
    private Context my_context;
    public Adapter_land(Context context, int resourceId, List<Land2> objects)
    {
        super(context, resourceId, objects);
        _resourceId = resourceId;
        my_context = context;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Land2 info = getItem(position); // 获取当前项的Land实例
        ViewHolder viewHolder;
        View view;

        if(convertView == null)
        {
            view = LayoutInflater.from(getContext()).inflate(_resourceId,null);
            viewHolder = new ViewHolder();
            viewHolder.land_id = (TextView)view.findViewById(R.id.land_name);
            viewHolder.land_image = (ImageView)view.findViewById(R.id.land_image);
            viewHolder.land_area = (TextView)view.findViewById(R.id.land_area);
            viewHolder.land_location = (TextView)view.findViewById(R.id.land_location);
            viewHolder.land_year = (TextView)view.findViewById(R.id.land_year);
            viewHolder.land_price = (TextView)view.findViewById(R.id.price);

            view.setTag(viewHolder);
        }
        else
        {
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }
        //viewHolder.land_image.setImageResource(info.getLand_imageId());
        viewHolder.land_id.setText(info.getLand_inner_id());
        viewHolder.land_year.setText(info.getLand_time());
        viewHolder.land_location.setText(info.getLand_position());
        viewHolder.land_area.setText(info.getLand_area()+"  ");
        viewHolder.land_price.setText(info.getLand_money()+"元");
        viewHolder.land_price.setTextColor(Color.rgb(255,0,0));
        //      viewHolder.land_id.setTextSize(25);
        return view;
    }
    class ViewHolder{
        ImageView land_image;
        TextView land_id;
        TextView land_area;
        TextView land_location;
        TextView land_year;
        TextView land_price;
    }

}
