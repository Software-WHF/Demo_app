package com.example.administrator.mytext.Database;


public class Land {

    private String land_id;                            //土地编号(name)
    private String land_position;                    //土地位置
    private String land_use;                           //土地用途
    private String land_area;                        //土地面积
    private String land_time;                        //使用年限
    private String land_purchase_method;            //购买方式
    private String land_introduction;                //土地介绍
    private String land_credential;                    //土地证件
    private String land_soil_condition;                //土壤条件
    private String land_equipment;                    //设施
    private String land_environment;                //周边环境
    private String land_management;                    //经营现状
    private String land_policy;                        //政策
    private int land_imageID;                            //土地图片信息
//缺少上传者，发布时间，浏览次数，价格,具体ID，交易状态
    public Land(String land_id1, String land_position1, String land_use1,
                String land_area1,
                String land_time1, String land_purchase_method1, String land_introduction1,
                String land_credential1, String land_soil_condition1, String land_equipment1,
                String land_environment1, String land_management1, String land_policy1,
                int land_imageId1) {
        land_id = land_id1;
        land_position = land_position1;
        land_use = land_use1;
        land_area = land_area1;
        land_time = land_time1;
        land_purchase_method = land_purchase_method1;
        land_introduction = land_introduction1;
        land_credential = land_credential1;
        land_soil_condition = land_soil_condition1;
        land_equipment = land_equipment1;
        land_environment = land_environment1;
        land_management = land_management1;
        land_policy = land_policy1;
        land_imageID = land_imageId1;

    }

    public Land() {

    }


    public String getLand_id() {
        return land_id;
    }

    public String getLand_position() {
        return land_position;
    }

    public String getLand_area() {
        return land_area;
    }

    public String getLand_time() {
        return land_time;
    }

    public String getLand_purchase_method() {
        return land_purchase_method;
    }

    public String getLand_introduction() {
        return land_introduction;
    }

    public String getLand_credential() {
        return land_credential;
    }

    public String getLand_soil_condition() {
        return land_soil_condition;
    }

    public String getLand_equipment() {
        return land_equipment;
    }

    public String getLand_environment() {
        return land_environment;
    }

    public String getLand_management() {
        return land_management;
    }

    public String getLand_policy() {
        return land_policy;
    }

    public int getLand_imageId() {
        return land_imageID;
    }

    public String getLand_use()
    {
        return land_use;
    }

}