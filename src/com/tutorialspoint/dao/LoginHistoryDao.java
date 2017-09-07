package com.tutorialspoint.dao;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class LoginHistoryDao {
	
	@Autowired
	  DataSource datasource;
	  @Autowired
	  JdbcTemplate jdbcTemplate;

	public void addlog(String username, String event) {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now)); 
		
		String sql = "insert into logininfo values(?,?,?)";
		System.out.println("afterSQL");
	    int result= jdbcTemplate.update(sql, new Object[] { username, dtf.format(now), event});
		System.out.println(result);
		
	}
	
	

}
