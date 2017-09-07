package com.tutorialspoint.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class EventDao {
	
	@Autowired
	  DataSource datasource;
	  @Autowired
	  JdbcTemplate jdbcTemplate;

	public void addInDatabase(String event, String timestamp, String username) {
		
		String sql = "insert into eventInfo values(?,?,?)";
		System.out.println("afterSQL");
	    int result= jdbcTemplate.update(sql, new Object[] { timestamp, event, username});
		System.out.println(result);
		
	}
	
	
	
	

}
