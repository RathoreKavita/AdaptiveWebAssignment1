package com.adaptiveWeb.controller;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.adaptiveWeb.dao.UserDaoImpl;
import com.adaptiveWeb.model.User;

@Controller
public class RegistrationController {
	
	@Autowired
	UserDaoImpl userDoaImpl;
	
	@RequestMapping(value="/register", method = RequestMethod.POST)
	   public  String addUser(HttpServletRequest request, HttpServletResponse response) {
		
		
		userDoaImpl.registerUser(request.getParameter("username"), request.getParameter("password"), request.getParameter("firstname"),request.getParameter("lastname"),
				request.getParameter("dateofbirth"),request.getParameter("email"),request.getParameter("address"),request.getParameter("postcode"));
	   
		return "index";
	
	}
	
	
	
}
