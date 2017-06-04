package servlet;

public class Land {
    private String land_id;                            //土地编号(name)
    private String land_position;                    //土地位置
    private String land_money;                          //土地价钱
    private String land_use;                           //土地用途
    private String land_area;                        //土地面积
    private String land_time;                        //使用年限
    private String land_introduction;                //土地介绍
    private String land_detail;                      //土地细节
    private String land_release_time;                //土地发布时间
    private String land_view_count;                //土地浏览次数
    private String land_inner_id;                //土地标题
    private String land_sale_state;                 //土地出售状况
    private String user_id;
    private String user_name;
    private String user_phone;

    public Land(String land_id1,String land_position1,String land_money1,String land_use1,String land_area1,String land_time1,String land_introduction1,String land_detail1,String land_release_time1,String land_view_count1,String land_inner_id1,String land_sale_state1,String user_id1,String user_name1,String user_phone1)
    {
        land_id = land_id1;
        land_position = land_position1;
        land_money = land_money1;
        land_use = land_use1;
        land_area = land_area1;
        land_time = land_time1;
        land_introduction = land_introduction1;
        land_detail = land_detail1;
        land_release_time = land_release_time1;
        land_view_count = land_view_count1;
        land_inner_id = land_inner_id1;
        land_sale_state = land_sale_state1;
        user_id = user_id1;
        user_name = user_name1;
        user_phone = user_phone1;
    }
    public Land()
    {
    	
    	
    }
    public String getLand_id() {
        return land_id;
    }

    public String getLand_position() {
        return land_position;
    }

    public String getLand_introduction() {
        return land_introduction;
    }

    public String getLand_detail() {
        return land_detail;
    }

    public String getLand_money()
    {
        return land_money;

    }

    public String getLand_use()
    {
        return land_use;
    }

    public String getLand_area() {
        return land_area;
    }

    public String getLand_time() {
        return land_time;
    }



    public String gelLand_release_time() {
        return land_release_time;
    }

    public String getLand_view_count() {
        return land_view_count;
    }

    public String getLand_inner_id() {
        return land_inner_id;
    }

    public String getLand_sale_state() {
        return land_sale_state;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_phone() {
        return user_phone;
    }

	
}
