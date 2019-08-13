package com.atan.registration.dao;

import static org.junit.Assert.assertNotNull;

import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.atan.registration.dto.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {
	private Logger logger = Logger.getLogger(UserDaoTest.class.getName());
	
	@Autowired
	private UserDao userDao;
    
    private User user;
    
    @Before
    public void init() {
    	user = new User();
    	user.setUserId(1L);
		user.setMobileNumber("+62100010001");
		user.setFirstName("Firstname");
		user.setLastName("Lastname");
		user.setEmail("test@gmail.com");
    }
    
	@Test
	public void test1SaveUser() {
		logger.info("-- Email: " + user.getEmail());
		logger.info("-- MobileNumber: " + user.getMobileNumber());
		User userSaved = userDao.save(user);
		assertNotNull(userSaved);
	}
	
	@Test
	public void test2CheckEmail() {
		logger.info("--- Email: " + user.getEmail());
		User userFound = userDao.findByEmail(user.getEmail());
		assertNotNull(userFound);
	}
	
	@Test
	public void test3CheckMobileNumberTest() {
		logger.info("--- MobileNumber: " + user.getMobileNumber());
		User userFound = userDao.findByMobileNumber(user.getMobileNumber());
		assertNotNull(userFound);
	}

}
