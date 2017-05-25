package com.example.administrator.mytext.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.mytext.Database.Land;
import com.example.administrator.mytext.R;

import org.w3c.dom.Text;

import java.util.List;

public class Adapter_home_land extends ArrayAdapter<Land> {
    private int _resourceId;
    private Context my_context;

    public Adapter_home_land(Context context, int resourceId, List<Land> objects) {
        super(context, resourceId, objects);
        _resourceId = resourceId;
        my_context = context;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Land info = getItem(position); // 获取当前项的Land实例
        ViewHolder viewHolder;
        View view;

        if(convertView == null)
        {
            view = LayoutInflater.from(getContext()).inflate(_resourceId,null);
            viewHolder = new ViewHolder();
            viewHolder.home_image_view = (ImageView)view.findViewById(R.id.home_image_view) ;
            viewHolder.home_text_view_dizhi = (TextView)view.findViewById(R.id.home_text_view_dizhi);
            viewHolder.home_text_view_biaoti = (TextView)view.findViewById(R.id.home_text_view_biaoti);
            viewHolder.home_text_view_money = (TextView)view.findViewById(R.id.home_text_view_money);
            viewHolder.home_text_view_area = (TextView)view.findViewById(R.id.home_text_view_area);
            viewHolder.home_text_view_time = (TextView)view.findViewById(R.id.home_text_view_time);
            viewHolder.home_text_view_dizhi2 = (TextView)view.findViewById(R.id.home_text_view_dizhi2);



            view.setTag(viewHolder);
        }
        else
        {
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }
        viewHolder.home_image_view.setImageResource(info.getLand_imageId());
        viewHolder.home_text_view_dizhi.setText(info.getLand_position());
        viewHolder.home_text_view_biaoti.setText(info.getLand_id());
        viewHolder.home_text_view_money.setText("待定");
        viewHolder.home_text_view_area.setText(info.getLand_area());
        viewHolder.home_text_view_time.setText(info.getLand_time());
        viewHolder.home_text_view_dizhi2.setText(info.getLand_position());



        return view;
    }
    class ViewHolder{
        ImageView home_image_view;
        TextView home_text_view_dizhi;
        TextView home_text_view_biaoti;
        TextView home_text_view_money;
        TextView home_text_view_area;
        TextView home_text_view_time;
        TextView home_text_view_dizhi2;
    }
}
