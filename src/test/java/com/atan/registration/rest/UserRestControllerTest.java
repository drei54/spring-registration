package com.atan.registration.rest;

import static org.junit.Assert.assertEquals;

import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.atan.registration.dao.UserDaoTest;
import com.atan.registration.dto.Response;
import com.atan.registration.dto.User;
import com.atan.registration.util.ResponseCode;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRestControllerTest extends AbstractTest{

	private Logger logger = Logger.getLogger(UserDaoTest.class.getName());
	
	private User user;
	
	@Override
	@Before
	public void setUp() {
		super.setUp();
		user = new User();
    	user.setUserId(1L);
		user.setMobileNumber("+62100010001");
		user.setFirstName("Firstname");
		user.setLastName("Lastname");
		user.setEmail("test@gmail.com");
	}
		  
	@Test
	public void saveUserPost() throws Exception {
		String uri = "/registration";
		logger.info("--- URL registration: " + uri);
	    String inputJson = super.mapToJson(user);
	    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
	         .contentType(MediaType.APPLICATION_JSON_VALUE)
	         .content(inputJson)).andReturn();
	      
	    int status = mvcResult.getResponse().getStatus();
	    assertEquals(200, status);
	    String content = mvcResult.getResponse().getContentAsString();
	    Response response = super.mapFromJson(content, Response.class);
	    assertEquals(response.getMessage(), ResponseCode.SUCCESS.toString());
		
	}
	
	
	@Test
	public void checkEmailExist() throws Exception {
		String uri = "/registration/check-email?email="+user.getEmail();
		logger.info("--- URL check email: " + uri);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		   
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		
		String content = mvcResult.getResponse().getContentAsString();
		Response response = super.mapFromJson(content, Response.class);
	    assertEquals(response.getMessage(), ResponseCode.FAILED.toString());
		
	}
	
	@Test
	public void checkMobileNumberExist() throws Exception {
		String uri = "/registration/check-number?mobileNumber="+user.getMobileNumber().replace("+", "");
		logger.info("--- URL mobile number exist: " + uri);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		   
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		
		String content = mvcResult.getResponse().getContentAsString();
		Response response = super.mapFromJson(content, Response.class);
	    assertEquals(response.getMessage(), ResponseCode.FAILED.toString());
		
	}
	
}
