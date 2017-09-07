package com.adaptiveWeb.dao;

import java.util.List;

import com.adaptiveWeb.model.LoggedData;
import com.adaptiveWeb.model.User;

public interface UserDao {
	  void register(User user);
	  User validateUser(String username, String password);
	  void registerUser(String username, String password, String firstname, String lastname, String dateofbirth,String email, String address, String postcode);
	  List<LoggedData> getLogData(String username);
	}
	
	


