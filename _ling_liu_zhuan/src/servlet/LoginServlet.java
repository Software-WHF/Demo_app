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



@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String user_id = request.getParameter("user_id");
		String user_password = request.getParameter("user_password");
		String answer = "";
		Connection conn = (Connection) My_connection.getConnect();
		 try
	        { 
	            Statement statement = conn.createStatement(); 
	            String sql = "select * from " + My_connection.TABLE_USER + " where user_id = "+"'"+user_id+"' "+"and user_password = '"+user_password+"'";
	            ResultSet result = statement.executeQuery(sql);
	            if(result.next())
	            {
	            	answer = "0001";
	            }
	            else
	            {
	            	answer = "0000";
	            }
	        }

	        catch (SQLException e) 
	        {  
	            e.printStackTrace();  
	        } 
		 response.setContentType("text/html;charset=utf-8");
		 response.getWriter().append(answer);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
