package com.adaptiveWeb.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.adaptiveWeb.dao.LoginHistoryDao;
import com.adaptiveWeb.dao.UserDaoImpl;
import com.adaptiveWeb.model.LoggedData;
import com.adaptiveWeb.model.StackoverflowData;
import com.adaptiveWeb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;

@Controller
//@RequestMapping("/hello")
public class HelloController {
	
	@Autowired
	UserDaoImpl userDoaImpl;
	
	@Autowired
	LoginHistoryDao loginHistory;
	
	User user1;
	
	
	/*@RequestMapping(value="/", method = RequestMethod.GET)
	   public String gotoLogin(ModelMap model) {
	      model.addAttribute("message", "Hello Spring MVC Framework!");
	      return "index";
	   } */
	
   @RequestMapping(value="/", method = RequestMethod.GET)
   public String getLogin(ModelMap model) {
      model.addAttribute("message", "Hello Spring MVC Framework!");
      return "index";
   }
   
   @RequestMapping(value="/login", method = RequestMethod.POST)
   public String login(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
	   
	   //userDoaImpl.createTables();
	   
	   User user=(User) userDoaImpl.validateUser(request.getParameter("username"), request.getParameter("password"));
	   user1=user;
	   if(user!=null){
		   model.addAttribute("message",  user.getUsername());
		   HttpSession session= request.getSession();
		   session.setAttribute("username",user.getUsername() );
		   // add in login history
		   loginHistory.addlog(user.getUsername(), "Log In");
		   return "success";
	   }
	   else{
		   model.addAttribute("message","User Not Found");
		   return "error";
	   }
	      
	   }
   
   @RequestMapping(value="/registration", method = RequestMethod.GET)
   public String getRegistration(ModelMap model) {
      model.addAttribute("message", "Hello Spring MVC Framework!");
      return "registration";
   }
   
   
   @RequestMapping(value="/logout", method = RequestMethod.GET)
   public String logout(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
	   HttpSession session= request.getSession();
	   String username=(String) session.getAttribute("username");
	   loginHistory.addlog(username, "Log out");
	   session.invalidate();
	   return "index";
   }
   
   
   @RequestMapping(value="/answer", method = RequestMethod.GET)
   public String getAnswer(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
	   HttpSession session= request.getSession();
	   String username=(String) session.getAttribute("username");
	   model.addAttribute("message", username);
	   model.addAttribute("answer1", "1. Scroll-up and Scroll Down denote the "
	   		+ "user's browsing habit and average time he spends on the page");
	   model.addAttribute("answer2","2. Mouse Idle time Capturing - Can capture the average idle time for a user, and amount of time they require"
	   		+ "to read and analyze an answer");
       model.addAttribute("answer3", "3 Thumb up, Thumb down Capturing - "
       		+ "SHows the answers the user is interested in or things he/she dislikes");
       model.addAttribute("answer4", "4 Comments Capturing - This displays which kind of topics user is more interested in"
       		+ "and how active they are on forum");
       model.addAttribute("answer5", "5 Questions hyperlink Capturing - This shows that how user goes aroud a topic, whether he/she is explores more or"
       		+ "just interested only to a single topic also we can pn an average how many qustions they visit before getting "
       		+ "a satisfactory annswer");
       model.addAttribute("answer6", "6 Favorite Question/Answer Capturing - This can help us in suggesting more relevant anwers and questions"
       		+ "based on list of favourites");
       model.addAttribute("answer7", "In General, we can use all above data to profile user behavious at an individual level"
       		+ "we can determine the knowlege level of user based on his/her searches and show incremental suggeestions based "
       		+ "on difficulty of subject and user ability to grasp");

	   return "success";
   }
   
   @RequestMapping(value="/stackoverflow", method = RequestMethod.GET)
   public String getStackovrflow(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
	   HttpSession session= request.getSession();
	   String username=(String) session.getAttribute("username");
	   List<StackoverflowData> eventdata=userDoaImpl.getStackoverflowData(username);
	   System.out.println(eventdata.get(0));
	   model.addAttribute("eventData",eventdata );
	   model.addAttribute("message", username);
	   model.addAttribute("column1", "Time");
	   model.addAttribute("column2", "Event");
	   model.addAttribute("title", "Stack Overflow");
	return "successtack";
   }
  
   
   
   @RequestMapping(value="/getInfo", method = RequestMethod.GET)
   public String getLogindata(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
	   HttpSession session= request.getSession();
	   String username=(String) session.getAttribute("username");
	   List<LoggedData> loggeddata=userDoaImpl.getLogData(username);
	   model.addAttribute("logData",loggeddata );
	   model.addAttribute("message", username);
	   model.addAttribute("column1", "Time");
	   model.addAttribute("column2", "Event");
	   model.addAttribute("title", "Log-In History");

	   return "success";
   }
   
   
   
   
   
   
   
   
}