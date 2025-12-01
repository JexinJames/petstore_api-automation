package api.endpoints;

import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoints_Propertiesfile {
	
	
	static ResourceBundle getURL(){
		
		//load properties file
		ResourceBundle routes = ResourceBundle.getBundle("routes"); // mention only the name of property file. It locate under resource folder
		
		return routes;
	}
	
	
	public static Response createUser(User payload) {
		
		
		Response response=given()
							.contentType(ContentType.JSON)
							.accept(ContentType.JSON)
							.body(payload)
		
						.when()
							.post(getURL().getString("user_post_url")); // calling url from properties file using the method
		
		
		return response;
		
		

	}
	
	
	public static Response getUser(String username) {
		
		Response response = given()
								.pathParam("username", username)
								
							.when()
								.get(getURL().getString("user_get_url")); // in the url itself we have param syntax. {username}
		
		return response;
		
		

	}
	
	
	public static Response updateUser(String username, User payload) {
		
		Response response = given()
								.pathParam("username", username)
								.contentType(ContentType.JSON)
								.accept(ContentType.JSON)
								.body(payload)
								
							.when()
								.put(getURL().getString("user_put_url")); // in the url itself we have param syntax. {username}
		
		return response;
		
		

	}
		
	
	public static Response deleteUser(String username) {
		
		Response response = given()
								.pathParam("username", username)
								
							.when()
								.delete(getURL().getString("user_delete_url")); // in the url itself we have param syntax. {username}
		
		return response;
		
		

	}
	
	
	

}
