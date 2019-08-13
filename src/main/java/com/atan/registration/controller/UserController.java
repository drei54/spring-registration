package com.atan.registration.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UserController {
	private Logger logger = Logger.getLogger(UserController.class.getName());

	@GetMapping("/")
	public String getRegistrationPage(Model model) {
		logger.info("---------Registration Page---------");
		List<Integer> dates =  new ArrayList<Integer>();
		for(int i=1; i<=31; i++) {dates.add(i);}
		
		List<Integer> years =  new ArrayList<Integer>();
		for(int i=1950; i<=2019; i++) {years.add(i);}
		
		List<String> months =  new ArrayList<String>(Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct","Nov", "Dec"));
		
		
		model.addAttribute("dates", dates);
		model.addAttribute("months", months);
		model.addAttribute("years", years);
		model.addAttribute("title", "Registration");
		return "index";
	}
	
	@GetMapping("/login")
	public String getLoginPage(Model model) {
		logger.info("---------Login Page---------");
		model.addAttribute("title", "Login");
		return "login";
	}
	
}
