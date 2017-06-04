package servlet;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.mysql.jdbc.Connection;


@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");  //设置编码  
		//获得磁盘文件条目工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();  
        //获取文件需要上传到的路径
        String path = request.getRealPath("/upload");  
        String land_id = request.getParameter("land_id");
        File file=new File(path);
        int row = 0;
        if(!file.exists()){
        	file.mkdirs();
        }
        factory.setRepository(new File(path));  
        //设置 缓存的大小，当上传文件的容量超过该缓存时，直接放到 暂时存储室 
        factory.setSizeThreshold(1024*1024) ;  
        //高水平的API文件上传处理
        ServletFileUpload upload = new ServletFileUpload(factory);  
        try {  
        	//可以上传多个文件 
            List<FileItem> list = (List<FileItem>)upload.parseRequest(request);  
            for(FileItem item : list){  
            	//获取表单的属性名字  
                String name = item.getFieldName();  
                //如果获取的 表单信息是普通的 文本 信息  
                if(item.isFormField()){                     
                	//获取用户具体输入的字符串 ，名字起得挺好，因为表单提交过来的是 字符串类型的
                    String value = item.getString() ;  
                    request.setAttribute(name, value);  
                }else{  
                	//获取路径名 
                    String value = item.getName() ;  
                  //索引到最后一个反斜杠
                    int start = value.lastIndexOf("\\");  
                  //截取 上传文件的 字符串名字，加1是 去掉反斜杠
                    String filename = value.substring(start+1); 
                    Connection conn = (Connection) My_connection.getConnect();
                    try
                    { 
                        Statement statement = conn.createStatement(); 
                        String sql = "insert into land_image values('" + land_id + "','" + filename + "')";
                        row = statement.executeUpdate(sql);

                    }

                    catch (SQLException e) 
                    {  
                        e.printStackTrace();  
                    } 
                    request.setAttribute(name, filename);  
                  //写到磁盘上
                    item.write( new File(path,filename) );//第三方提供的 
                    System.out.println("�ϴ��ɹ���"+filename);
                    if(row == 1)
                    	response.getWriter().append("1");
                    else
                    	response.getWriter().append("0");
                }  
            }  
              
        } catch (Exception e) {  
        	System.out.println("上传失败");
        	response.getWriter().print("0");
        }  
	}

}
