package com.atan.registration.rest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.atan.registration.dao.UserDao;
import com.atan.registration.dto.Response;
import com.atan.registration.dto.User;
import com.atan.registration.util.ResponseCode;

@RestController
public class UserRestController {
	private Logger logger = Logger.getLogger(UserRestController.class.getName());

	
	@Autowired
	private UserDao userDao;

	@RequestMapping(value="/registration", method = RequestMethod.POST)
	@ResponseBody
	public Response saveUser(@RequestBody User user) {
		logger.info(user.toString());
		Response resp =  new Response(ResponseCode.FAILED);
		if(user != null){
			user.setDateOfBirth(getDateOfBirth(user));
			User saveRegistration = userDao.save(user);
			resp = new Response(ResponseCode.SUCCESS);
		}
		return resp;
	}
	
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	@ResponseBody
	public Page<User> findAllUser(
			@PageableDefault(size = 10) Pageable pageable) {

		Page<User> registrations = userDao.findAll(pageable);
		
		return registrations;
	}
	
	@RequestMapping(value="/registration/check-email", method = RequestMethod.GET)
	@ResponseBody
	public Response checkEmailUser(
			@RequestParam(value = "email", required = true) final String email) {

		logger.info("email:"+email);
		Response resp =  new Response(ResponseCode.FAILED);
		if(email != null) {
			User user = userDao.findByEmail(email);
			if(user == null) {
				resp = new Response(ResponseCode.SUCCESS);
			}
		}
		
		return resp;
	}
	
	
	@RequestMapping(value="/registration/check-number", method = RequestMethod.GET)
	@ResponseBody
	public Response checkMobileNumberUser(
			@RequestParam(value = "mobileNumber", required = true) String mobileNumber) {
		mobileNumber = "+"+mobileNumber.trim();
		logger.info("mobileNumber:"+mobileNumber);
		Response resp =  new Response(ResponseCode.FAILED);
		if(mobileNumber != null) {
			User user = userDao.findByMobileNumber(mobileNumber);
			if(user == null) {
				resp = new Response(ResponseCode.SUCCESS);
			}
		}
		
		return resp;
	}

	private Date getDateOfBirth(User user) {
		Date dateOfBirth = null;
		try {
		if(!user.getMonth().isEmpty() && !user.getDate().isEmpty() && !user.getYear().isEmpty()) {
			String dateStr = user.getDate()+"-"+user.getMonth()+"-"+user.getYear();
			dateOfBirth = getDate(dateStr);
		}
		}catch(Exception e) {
			logger.info("ERROR on [getDateOfBirth]");
		}
		
		return dateOfBirth;
		
	}
	
	private Date getDate(String date){
		Date dt = null;
		try{
			if(date != null){
		        SimpleDateFormat df2 = new SimpleDateFormat("dd-MMM-yyyy");
		        dt = df2.parse(date);
			}
		}catch(Exception e){
			logger.info("ERROR on [getDate]");
		}
        return dt;
	}

}
