package com.example.administrator.mytext.Database;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class CommonRequest{
    private String requestCode;
    private HashMap<String,String> requestParam;

    public CommonRequest()
    {
        requestCode = "";
        requestParam = new HashMap<>();
    }

    public void setRequestCode(String requestCode)
    {
        this.requestCode = requestCode;
    }
    public void addRequestParam(String paramKey, String paramValue)
    {
        requestParam.put(paramKey, paramValue);
    }
    public String getJsonStr()
    {
        JSONObject object = new JSONObject();
        JSONObject param = new JSONObject(requestParam);
        try {
            object.put("requestCode", requestCode);
            object.put("requestParam", param);
        } catch (JSONException e) {
        }

        return object.toString();
    }
}
