package com.adaptiveWeb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.adaptiveWeb.model.LoggedData;
import com.adaptiveWeb.model.StackoverflowData;
import com.adaptiveWeb.model.User;

public class UserDaoImpl implements UserDao{
	
	@Autowired
	  DataSource datasource;
	  @Autowired
	  JdbcTemplate jdbcTemplate;

	  @Override
	public void register(User user) {
		// TODO Auto-generated method stub
		
	}
	  
	  
	/*public void createTables() {
			// TODO Auto-generated method stub
			String sql1 =
		"CREATE TABLE logininfo ( username varchar(255) DEFAULT NULL, timestampp varchar(255) DEFAULT NULL,eventType varchar(255) DEFAULT NULL)";
			String sql2="CREATE TABLE userinfo (username varchar(255) DEFAULT NULL,password varchar(255) DEFAULT NULL,firstname varchar(255) DEFAULT NULL,lastname varchar(255) DEFAULT NULL,dateofbirth date DEFAULT NULL,email varchar(255) DEFAULT NULL,address varchar(255) DEFAULT NULL,postcode varchar(255) DEFAULT NULL)";
			jdbcTemplate.execute(sql1);
			jdbcTemplate.execute(sql2);
	}*/

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
			String email, String address, String postcode) {
		
		String sql = "insert into userinfo values(?,?,?,?,?,?,?,?)";
		System.out.println("afterSQL");
	    int result= jdbcTemplate.update(sql, new Object[] { username, password, firstname, lastname,dateofbirth, email, address,  postcode});
		System.out.println(result);

	    
	}

	@Override
	public List<LoggedData> getLogData(String username) {
		
		String sql = "select * from logininfo where username = ?";
		List<LoggedData> data =jdbcTemplate.query(sql, new Object[]{username}, new logininfoMapper()) ;
		return data.size()>0? data:null;
	}


	public List<StackoverflowData> getStackoverflowData(String username) {
		
		String sql = "select * from eventinfo where username = ?";
		List<StackoverflowData> data =jdbcTemplate.query(sql, new Object[]{username}, new eventinfoMapper()) ;
		return data.size()>0? data:null;
		
	}


	
	
}


class eventinfoMapper implements RowMapper<StackoverflowData> {
	  public StackoverflowData mapRow(ResultSet rs, int arg1) throws SQLException {
		  StackoverflowData eventData = new StackoverflowData();
		  eventData.setUsername(rs.getString("username"));
		  eventData.setTimestamp(rs.getString("timestampp"));
		  eventData.setEvent(rs.getString("eventType"));
		  eventData.setParent(rs.getInt("parent"));
		  eventData.setFlag(rs.getString("flag"));

	    return eventData;
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
	    user.setPostcode(rs.getString("postcode"));

	    return user;
	  }
	  
	  
	}
