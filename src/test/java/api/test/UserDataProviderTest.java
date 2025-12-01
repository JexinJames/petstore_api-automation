package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.payload.User;
import api.utilities.UserDataProvider;
import io.restassured.response.Response;

public class UserDataProviderTest {
	
	
	@Test(priority=1, dataProvider="userdata", dataProviderClass = UserDataProvider.class) // mentioning the class of dataprovider as it in different class
	public void testCreateUser(String id, String username, String firstName, String lastName, String email, String pwd, String ph) {
		
		
		User userPayload = new User();
		
		userPayload.setId(Integer.parseInt(id));
		userPayload.setUsername(username);
		userPayload.setFirstName(firstName);
		userPayload.setLastName(lastName);
		userPayload.setEmail(email);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);
		
		Response response =UserEndpoints.createUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		
	}
	
	@Test(priority=2, dataProvider="username", dataProviderClass = UserDataProvider.class)
	public void testDeleteUser(String username){
		
		
		Response response =UserEndpoints.deleteUser(username);
		
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		
	}
	
	
	

}
