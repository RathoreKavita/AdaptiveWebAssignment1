package com.tutorialspoint.dao;

import java.util.List;

import com.tutorialspoint.model.LoggedData;
import com.tutorialspoint.model.User;

public interface UserDao {
	  void register(User user);
	  User validateUser(String username, String password);
	  void registerUser(String username, String password, String firstname, String lastname, String dateofbirth,
			String email, String address, String phone, String postcode);
	  List<LoggedData> getLogData(String username);
	}
	
	


