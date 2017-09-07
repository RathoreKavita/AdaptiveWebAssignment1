package com.tutorialspoint.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.tutorialspoint.model.LoggedData;
import com.tutorialspoint.model.User;

public class UserDaoImpl implements UserDao{
	
	@Autowired
	  DataSource datasource;
	  @Autowired
	  JdbcTemplate jdbcTemplate;

	@Override
	public void register(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User validateUser(String username, String password) {
		// TODO Auto-generated method stub
		String sql = "select * from userinfo where username='" + username + "' and password='" + password
	    + "'";
		List<User> users = jdbcTemplate.query(sql, new UserMapper());
	    return users.size() > 0 ? users.get(0) : null;
	}

	@Override
	public void registerUser(String username, String password, String firstname, String lastname, String dateofbirth,
			String email, String address, String phone, String postcode) {
		
		String sql = "insert into userinfo values(?,?,?,?,?,?,?,?,?)";
		System.out.println("afterSQL");
	    int result= jdbcTemplate.update(sql, new Object[] { username, password, firstname, lastname,dateofbirth, email,phone , address,  postcode});
		System.out.println(result);

	    
	}

	@Override
	public List<LoggedData> getLogData(String username) {
		
		String sql = "select * from logininfo where username = ?";
		List<LoggedData> data =jdbcTemplate.query(sql, new Object[]{username}, new logininfoMapper()) ;
		return data.size()>0? data:null;
	}
	
}


class logininfoMapper implements RowMapper<LoggedData> {
	  public LoggedData mapRow(ResultSet rs, int arg1) throws SQLException {
	    LoggedData loggeDatas = new LoggedData();
	    loggeDatas.setUsername(rs.getString("username"));
	    loggeDatas.setTimestamp(rs.getString("timestampp"));
	    loggeDatas.setEventType(rs.getString("eventType"));
	    return loggeDatas;
	  }
	  
	  
	}




class UserMapper implements RowMapper<User> {
	  public User mapRow(ResultSet rs, int arg1) throws SQLException {
	    User user = new User();
	    user.setUsername(rs.getString("username"));
	    user.setPassword(rs.getString("password"));
	    user.setFirstname(rs.getString("Firstname"));
	    user.setLastname(rs.getString("lastname"));
	    user.setDateofbirth(rs.getString("dateofbirth"));
	    user.setEmail(rs.getString("email"));
	    user.setAddress(rs.getString("address"));
	    user.setPhone(rs.getInt("phone"));
	    user.setPostcode(rs.getString("postcode"));

	    return user;
	  }
	  
	  
	}
