package servlet;

public class Collection {
	private String user_id;
	private String land_id;
	
	public Collection(String user_id1,String land_id1)
	{
		user_id = user_id1;
		land_id = land_id1;	
	}

	public String getUser_id() {
		return user_id;
	}
	public String getLand_id() {
		return land_id;
	}

	

}
