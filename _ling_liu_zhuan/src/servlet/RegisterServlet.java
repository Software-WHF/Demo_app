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






@WebServlet(description = "注册", urlPatterns = { "/RegisterServlet" })
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String username = new String(request.getParameter("username").getBytes( "ISO-8859-1"), "UTF-8");
		String user_id = request.getParameter("user_id");
		String user_password = request.getParameter("user_password");
		String expression = "";
		
		Connection conn = (Connection) My_connection.getConnect();
		
        try
        { 
            Statement statement = conn.createStatement(); 
            String sql = "select * from " + My_connection.TABLE_USER + " where user_id = "+"'"+user_id+"' ";
            ResultSet result = statement.executeQuery(sql);  
            if(result.next())
            {
            	expression = "注册失败，账号已存在";
            }
            else
            {
            	String sql_insert = "insert into " + My_connection.TABLE_USER + "(user_id,user_password) values('"+user_id+"','"+user_password+"')";  
                int row1 = statement.executeUpdate(sql_insert);
        			if(row1==1)
        			{
        				expression = "注册成功";
        			}
        			else
        			{
        				expression = "注册失败";
        			}
            }  
        }

        catch (SQLException e) 
        {  
            e.printStackTrace();  
        }  
        response.setContentType("text/html;charset=utf-8"); // 设置响应报文的编码格式
		response.getWriter().append(expression);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
