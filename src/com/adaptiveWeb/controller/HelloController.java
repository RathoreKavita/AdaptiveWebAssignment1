package com.adaptiveWeb.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.adaptiveWeb.DTO.BarChartDTO;
import com.adaptiveWeb.DTO.BubbleDTO;
import com.adaptiveWeb.DTO.PieChartDTO;
import com.adaptiveWeb.DTO.ScatterDTO;
import com.adaptiveWeb.DTO.stackOverflowDTO;
import com.adaptiveWeb.dao.EventDao;
import com.adaptiveWeb.dao.LoginHistoryDao;
import com.adaptiveWeb.dao.UserDaoImpl;
import com.adaptiveWeb.model.BarChartData;
import com.adaptiveWeb.model.BubblePlotData;
import com.adaptiveWeb.model.LoggedData;
import com.adaptiveWeb.model.PieChartData;
import com.adaptiveWeb.model.ScatterPlotData;
import com.adaptiveWeb.model.StackoverflowData;
import com.adaptiveWeb.model.User;

import net.sf.json.JSONObject;

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

	@Autowired
	EventDao eventDao;


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
			session.setAttribute("parentNo", -2);
			session.setAttribute("index", -1);

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
		// changing 1 to 2
		//changing 0 to 1
		eventDao.updateRows();
		session.invalidate();
		return "index";
	}


	@RequestMapping(value="/answer", method = RequestMethod.GET)
	public String getAnswer(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session= request.getSession();
		String username=(String) session.getAttribute("username");
		model.addAttribute("message", username);
		model.addAttribute("answer1", "1. SCROLL-UP and SCROLL DOWN denote the "
				+ "user's browsing habit and average time he spends on the page");
		model.addAttribute("answer2","2. MOUSE IDLE time Capturing - Can capture the average idle time for a user, and amount of time they require"
				+ "to read and analyze an answer");
		model.addAttribute("answer3", "3 THUMB UP, THUMB DOWN Capturing - "
				+ "SHows the answers the user is interested in or things he/she dislikes");
		model.addAttribute("answer4", "4 COMMENTS Capturing - This displays which kind of topics user is more interested in"
				+ "and how active they are on forum");
		model.addAttribute("answer5", "5 QUESTION HYPERLINK Capturing - This shows that how user goes aroud a topic, whether he/she is explores more or"
				+ "just interested only to a single topic also we can pn an average how many qustions they visit before getting "
				+ "a satisfactory annswer. It also leads in deciding problem domain and relevant issues");
		model.addAttribute("answer6", "6 FAVAORITE Question/Answer Capturing - This can help us in suggesting more relevant anwers and questions"
				+ "based on list of favourites");
		model.addAttribute("answer7", "7 TAG Capturing - Capturing the tags on which user clicks simply provides list of keywords they are interested in."
				+"Later we can show WORD CLOUDS customized for each user instead of tags which are spread out");
		model.addAttribute("answer8", "In General, we can use all above data to profile user behavious at an individual level"
				+ "we can determine the knowlege level of user based on his/her searches and show incremental suggeestions based "
				+ "on difficulty of subject and user ability to grasp");

		return "success";
	}
	
	
	@RequestMapping(value="/findings", method = RequestMethod.GET)
	public String findings(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session= request.getSession();
		String username=(String) session.getAttribute("username");
		model.addAttribute("message", username);
		model.addAttribute("answer1", "1. PIE-CHART helps in comparing different actions of the given user, by this we can make an "
				+ "individualistic user model which tells about user's generic goals/interests and we can make some features like tag more visible"
				+ " to user if their USER-MODEL suggests good use of tags. It also provides SLIDER to adjust the frequency and accordingly"
				+ "pie chart will change");
		model.addAttribute("answer2","2. BAR-PLOT helps the user to compare their set of interactions with all other users as well as "
				+ "GREEN line shows the avg of all the users. This will help in clustering the users into various clusters of similar "
				+ "users based on their bar graphs. GREEN LINE RUNS and helps in easily indentifying whether user is ABOVE_AVERAGE, BELOW_AVERAGE");
		model.addAttribute("answer3", "3. BUBBLE-PLOT is to analyse the use of TAGS provided on stackoverflow. X-AXIS is the total "
				+ "number of times that tag is clicked (frequency), Y-AXIS shows that how many users out of total users have used that "
				+ "tag. SIZE is controlled by the Average use of that tag among rest of the users. COLOR is governed by the avg of the "
				+ "logged in user. More the tag is used by the user more it will be darker in green. SIZE and COLOR changes as per the user "
				+ "who is logged-in (as it is user dependent)");
		model.addAttribute("answer5", "4. BUBBLE-PLOT can easily helps us in finding the MOST-USED PREVAILING tags. If a tag is higher"
				+ " on y-axis but less in size suggests that particular tag is highly used by other users as compared to the user logged-in"
				+ "(say aaa). By looking at the Bubble plot of all the users we can easily find out the set of users who have similar"
				+ "searching behaviour on Stackoverflow");
		model.addAttribute("answer6", "5. SCATTER Plot, plots all the users as small circles.This is very useful in clustering the users"
				+ "and that also very easily. We can identity some outliers also (different users from mass) within the blink of eye");
		model.addAttribute("answer8", "In General, we can use all above data to profile user behaviour at an individual level as well as on a cluster level"
				+ " and can change recommendation of questions and tags as per thier USER-MODEL");

		return "visualization";
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
		model.addAttribute("title", "Stack-Overflow");
		return "successtack";
	}

	

	@RequestMapping(value="/pieChart", method = RequestMethod.GET)
	public String pieChart(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session= request.getSession();
		String username=(String) session.getAttribute("username");
		List<PieChartData> pieChartData=eventDao.getPieChart(username);
		PieChartDTO dto=new PieChartDTO();
		dto.setPieChartlist(pieChartData);
		JSONObject jsonObject = JSONObject.fromObject(dto);
		String jsonStr = jsonObject.toString();
		model.addAttribute("dataJson", jsonStr);
		model.addAttribute("message", username);
		return "pieChart";
	}
	
	
	@RequestMapping(value="/barChart", method = RequestMethod.GET)
	public String barChart(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session= request.getSession();
		String username=(String) session.getAttribute("username");
		List<BarChartData> barChartData=eventDao.getbarChart(username);
		BarChartDTO dto=new BarChartDTO();
		dto.setbarChartlist(barChartData);
		JSONObject jsonObject = JSONObject.fromObject(dto);
		String jsonStr = jsonObject.toString();
		model.addAttribute("dataJson", jsonStr); 
		model.addAttribute("message", username);
		return "barChart";
	}
	
	
	@RequestMapping(value="/scatterPlot", method = RequestMethod.GET)
	public String scatterPlot(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session= request.getSession();
		String username=(String) session.getAttribute("username");
		List<ScatterPlotData> scatterData=eventDao.getScatterPlot(username);
		ScatterDTO dto=new ScatterDTO();
		dto.setScatterList(scatterData);
		JSONObject jsonObject = JSONObject.fromObject(dto);
		String jsonStr = jsonObject.toString();
		model.addAttribute("dataJson", jsonStr);
		model.addAttribute("message", username);
		return "scatterPlot";
	}
	
	
	@RequestMapping(value="/bubblePlot", method = RequestMethod.GET)
	public String bubblePlot(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session= request.getSession();
		String username=(String) session.getAttribute("username");
		List<BubblePlotData> bubbleData=eventDao.getBubblePlot(username);
		BubbleDTO dto=new BubbleDTO();
		dto.setBubbleList(bubbleData);
		JSONObject jsonObject = JSONObject.fromObject(dto);
		String jsonStr = jsonObject.toString();
		model.addAttribute("dataJson", jsonStr);  
		model.addAttribute("message", username);
		return "bubblePlot";
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
	
	@RequestMapping(value="/visualization", method = RequestMethod.GET)
	public String visualization(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
		HttpSession session= request.getSession();
		String username=(String) session.getAttribute("username");
		List<LoggedData> loggeddata=userDoaImpl.getLogData(username);
		model.addAttribute("logData",loggeddata );
		model.addAttribute("message", username);
		model.addAttribute("column1", "Time");
		model.addAttribute("column2", "Event");
		model.addAttribute("title", "Log-In History");
		return "visualization";
	}








}