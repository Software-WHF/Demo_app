package servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class AddlandServlet
 */
@WebServlet("/AddlandServlet")
public class AddlandServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddlandServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String land_id = request.getParameter("land_id");
		String land_position = request.getParameter("land_position");
		String land_use = request.getParameter("land_use");
		String land_area = request.getParameter("land_area");
		String land_time = request.getParameter("land_time");
		String land_money = request.getParameter("land_money");
		String land_inner_id = request.getParameter("land_inner_id");
		String land_detail = request.getParameter("land_detail");
		String land_introduction = request.getParameter("land_introduction");
		String user_id = request.getParameter("user_id");
		String user_name = request.getParameter("user_name");
		String user_phone = request.getParameter("user_phone");
		String land_release_time = request.getParameter("land_release_time");
		String land_sale_state = request.getParameter("land_sale_state");
		String land_way = request.getParameter("land_way");
		String land_credential = request.getParameter("land_credential");
		String land_equipment = request.getParameter("land_equipment");
		String land_environment = request.getParameter("land_environment");
		String land_mangement = request.getParameter("land_mangement");
		String land_policy = request.getParameter("land_policy");
		String land_vr = request.getParameter("land_vr");
		String  view_count = "0";
		int row = 0;
		Connection conn = (Connection) My_connection.getConnect();
        try
        { 
            Statement statement = conn.createStatement(); 
            String sql = "insert into " + My_connection.TABLE_LAND + " values('" + land_id + "','" + land_position + "','" + land_money + "','" + land_use + "','" + land_area + "','" + land_way + "','" + land_credential + "','" + land_equipment + "','" + land_environment + "','" + land_mangement + "','" + land_policy + "','" + land_time + "','" + land_introduction + "','" + land_detail + "','"  + land_release_time + "','" + view_count + "','" + land_inner_id + "','" + land_sale_state + "','" + user_id + "','" + user_name + "','" + user_phone + "','" + land_vr + "')";
            row = statement.executeUpdate(sql);
            
            
        }

        catch (SQLException e) 
        {  
            e.printStackTrace();  
        } 
		
		response.setContentType("text/html;charset=utf-8");
		if(row == 1)
			response.getWriter().append("1");
		else
			response.getWriter().append("0");
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
