package servlet;

public class User {
	private String user_id;
	private String user_password;
	private String user_phone;
	public User(String user_id1,String user_password1,String user_phone1)
	{
		user_id = user_id1;
		user_password = user_password1;
		user_phone = user_phone1;
	}
	public String getUser_id() {
		return user_id;
	}

	public String getUser_password() {
		return user_password;
	}
	public String getUser_phone() {
		return user_phone;
	}

}
