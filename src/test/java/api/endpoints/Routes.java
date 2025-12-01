package api.endpoints;


/*
 * 
 * swagger URI--> https://petstore.swagger.io
 * 
 * 
 * create user (post): https://petstore.swagger.io/v2/user
 * Get user (get) : https://petstore.swagger.io/v2/user/{username}
 * update user (put): https://petstore.swagger.io/v2/user/{username}
 * delete useer (delete): https://petstore.swagger.io/v2/user/{username}
 * 
 */

public class Routes {
	
	
	public static String base_url = "https://petstore.swagger.io/v2";
	
	
	// user module
	
	public static String user_post_url = base_url+"/user";
	public static String user_get_url= base_url+"/user/{username}";
	public static String user_put_url= base_url+"/user/{username}";
	public static String user_delete_url= base_url+"/user/{username}";
	
	
	// store module
	
	
	//pet module
	
	
	
	

}
