package com.example.administrator.mytext.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;



/**
 * Created by Administrator on 2017/5/21.
 */

public class CommonResponse {
    private String resCode = "";

    private String resMsg = "";

    private Land2 my_land = null;

    private ArrayList<Land2> landList;
    /**
     * 通用报文返回构造函数
     *
     * @param responseString Json格式的返回字符串
     */
    public CommonResponse(String responseString) {
        // 日志输出原始应答报文
        landList = new ArrayList<Land2>();
        try {
            JSONObject root = new JSONObject(responseString);
            resCode = root.getString("resCode");
            resMsg = root.optString("resMsg");
            JSONObject property = root.optJSONObject("property");
            if (property != null) {
                my_land = parseProperty(property);
            }
            JSONArray list = root.optJSONArray("list");
            if (list != null) {
                parseList(list);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private Land2 parseProperty(JSONObject property) {
        Land2 new_land = null;
        try {
            String land_id = property.getString("land_id");
            String land_position = property.getString("land_position");
            String land_money = property.getString("land_money");
            String land_use = property.getString("land_use");
            String land_area = property.getString("land_area");

            String land_way = property.getString("land_way");
            String land_credential = property.getString("land_credential");
            String land_equipment = property.getString("land_equipment");
            String land_environment = property.getString("land_environment");
            String land_mangement = property.getString("land_mangement");
            String land_policy = property.getString("land_policy");

            String land_time = property.getString("land_time");
            String land_introduction = property.getString("land_introduction");
            String land_detail = property.getString("land_detail");
            String land_release_time = property.getString("land_release_time");
            String land_view_count = property.getString("land_view_count");
            String land_inner_id = property.getString("land_inner_id");
            String land_sale_state = property.getString("land_sale_state");
            String user_id = property.getString("user_id");
            String user_name = property.getString("user_name");
            String user_phone = property.getString("user_phone");
            String land_vr = property.getString("land_vr");
            new_land = new Land2(land_id,land_position,land_money,land_use,land_area,land_way,land_credential,land_equipment,land_environment,land_mangement,land_policy,land_time,land_introduction,land_detail,land_release_time,land_view_count,land_inner_id,land_sale_state,user_id,user_name,user_phone,land_vr);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new_land;
    }

    private void parseList(JSONArray list)
    {
        for(int i=0;i<list.length();i++)
        {
            Land2 new_land = null;
            try {
                JSONObject object = (JSONObject)list.get(i);
                new_land = parseProperty(object);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            landList.add(new_land);
        }
    }

    public String getResCode() {
        return resCode;
    }

    public String getResMsg() {
        return resMsg;
    }

    public Land2 getPropertyMap() {
        return my_land;
    }

    public ArrayList<Land2> getDataList() {
        return landList;
    }
}
