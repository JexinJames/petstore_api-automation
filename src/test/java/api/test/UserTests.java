package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {
	
	
	Faker faker;
	User  user_payload;
	
	Logger logger;
	
	
	@BeforeClass
	public void setup() {
		
		faker = new Faker();
		user_payload = new User();
		
		user_payload.setId(faker.idNumber().hashCode());
		user_payload.setUsername(faker.name().username());
		user_payload.setFirstName(faker.name().firstName());
		user_payload.setLastName(faker.name().lastName());
		user_payload.setEmail(faker.internet().emailAddress());
		user_payload.setPhone(faker.phoneNumber().phoneNumber());
		user_payload.setPassword(faker.internet().password());
		
		logger = LogManager.getLogger(this.getClass());
		
		
		
		
	}
	
	
	@Test(priority=1)
	public void testCreateUser() {
		
		logger.info("****************** creating user ******************");
		
		Response response =UserEndpoints.createUser(user_payload);
		
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("****************** user created completed ******************");
		
		
	}
	
	
	@Test(priority=2)
	public void testGetUser() {
		
		logger.info("****************** retrieving user ******************");
		
		Response response =UserEndpoints.getUser(user_payload.getUsername());
		
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("****************** user retrieved ******************");
		
		
		
		
		
	}
	
	
	@Test(priority=3)
	public void testUpdateUser(){
		
		user_payload.setEmail(faker.internet().emailAddress());
		user_payload.setPhone(faker.phoneNumber().phoneNumber());
		
		
		logger.info("****************** updating user ******************");
		
		Response response =UserEndpoints.updateUser(user_payload.getUsername(), user_payload);
		
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("****************** user updated ******************");
		
		
	}
	
	@Test(priority=4)
	public void testDeleteUser() {
		
		logger.info("****************** deleting user ******************");
		
		Response response =UserEndpoints.deleteUser(user_payload.getUsername());
		
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("****************** user deleted ******************");
	}
}
