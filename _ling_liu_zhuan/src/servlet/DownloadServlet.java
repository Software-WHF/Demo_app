package servlet;

import java.io.IOException;
import java.io.PrintWriter;
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

/**
 * Servlet implementation class DownloadServlet
 */
@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
        String land_id = request.getParameter("land_id");
        Connection conn = (Connection) My_connection.getConnect();
        String sql = "select * from land_image where land_id='"+ land_id + "'";
        String p_id = "";
        Statement statement;
		try {
			statement = conn.createStatement();
			 ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				p_id = result.getString("land_image_id");
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        

        String imagepath = "E:\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\_ling_liu_zhuan\\upload\\" + p_id;
        String getImageStr = PicAndString.GetImageStr(imagepath);
        PrintWriter printWriter = response.getWriter();
        response.getWriter().append(getImageStr).flush();
	}

}
