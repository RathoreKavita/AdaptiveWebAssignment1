package com.adaptiveWeb.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.adaptiveWeb.dao.EventDao;
import com.adaptiveWeb.dao.UserDaoImpl;

@Controller
public class Any {
	
	@Autowired
	EventDao eventDao;
	
	@RequestMapping(value="/demo", method = RequestMethod.POST)
	   public String printHello(HttpServletRequest request, HttpServletResponse response) {
	      //model.addAttribute("message", "Hello Spring MVC Framework!" +);
		//System.out.println("Hello"+ request.getQueryString());
		System.out.println(request.getParameter("eventType")+ request.getParameter("timeStamp"));
		
		HttpSession session=request.getSession();
		String username=(String) session.getAttribute("username");
		int parent=-2;
		int index=-2;
		
		if(request.getParameter("eventType").matches("Tag:.*")){
			 parent=(Integer) session.getAttribute("parentNo");
			 index=(Integer) session.getAttribute("index");

			 
			session.setAttribute("parentNo", ++parent);
			session.setAttribute("index", ++index);

		}
		
		if(username!=null){
			eventDao.addInDatabase(request.getParameter("eventType"), request.getParameter("timeStamp"), username);
			return "hello";
		}
		else{
			return "error";
		}
		
	   }

}
