package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

import net.sf.json.JSONObject;


@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BufferedReader read = request.getReader();
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = read.readLine()) != null) {
			sb.append(line);
		}
		String req = sb.toString();
		Connection conn = (Connection) My_connection.getConnect();
		String sql = "select * from land";
		Statement statement;
		CommonResponse res = new CommonResponse();
		try {
			statement = conn.createStatement();
			 ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				String land_id = result.getString("land_id");
				String land_position = result.getString("land_position");
				String land_money = result.getString("land_money");
				String land_use = result.getString("land_use");
				String land_area = result.getString("land_area");
				
				String land_way = result.getString("land_way");
				String land_credential = result.getString("land_credential");
				String land_equipment = result.getString("land_equipment");
				String land_environment = result.getString("land_environment");
				String land_mangement = result.getString("land_mangement");
				String land_policy = result.getString("land_policy");
			
				
				String land_time = result.getString("land_time");
				String land_introduction = result.getString("land_introduction");
				String land_detail = result.getString("land_detail");
				String land_release_time = result.getString("land_release_time");
				String land_view_count = result.getString("land_view_count");
				String land_inner_id = result.getString("land_inner_id");
				String land_sale_state = result.getString("land_sale_state");
				String user_id = result.getString("user_id");
				String user_name = result.getString("user_name");
				String user_phone = result.getString("user_phone");
				String land_vr = result.getString("land_vr");
				//Land new_land = new Land(land_id,land_position,land_money,land_use,land_area,land_time,land_introduction,land_detail,land_release_time,land_view_count,land_inner_id,land_sale_state,user_id,user_name,user_phone);
				//res.addListItem(new_land);
				HashMap<String, String> map = new HashMap<>();
				map.put("land_id", land_id);
				map.put("land_position", land_position);
				map.put("land_money", land_money);
				map.put("land_use", land_use);
				map.put("land_area", land_area);
				map.put("land_time", land_time);
				map.put("land_introduction", land_introduction);
				map.put("land_detail", land_detail);
				map.put("land_release_time", land_release_time);
				map.put("land_view_count", land_view_count);
				map.put("land_inner_id", land_inner_id);
				map.put("land_sale_state", land_sale_state);
				map.put("user_id", user_id);
				map.put("user_name", user_name);
				map.put("user_phone", user_phone);
			
				map.put("land_way", land_way);
				map.put("land_credential", land_credential);
				map.put("land_equipment", land_equipment);
				map.put("land_environment", land_environment);
				map.put("land_mangement", land_mangement);
				map.put("land_policy", land_policy);
				map.put("land_vr",land_vr);
				res.addListItem(map);
				res.setResCode("0");
				}
		} catch (SQLException e) {
			res.setResult("300", "封装失败!");
			e.printStackTrace();
		}
		String resStr = JSONObject.fromObject(res).toString();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().append(resStr).flush();
       
	}

}
