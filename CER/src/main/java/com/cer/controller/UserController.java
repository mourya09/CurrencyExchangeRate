/**
 * 
 */
package com.cer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Praveen Kumar
 * This controller will take care of the user login as well as the user roles.
 *
 */
@Controller
public class UserController {
	protected final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping("/")
	public ModelAndView login()
	{
		ModelAndView model=new ModelAndView("login");
		
		
		return model;
	}
	
	
}
