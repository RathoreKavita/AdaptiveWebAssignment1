package com.adaptiveWeb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.adaptiveWeb.model.BarChartData;
import com.adaptiveWeb.model.BubblePlotData;
import com.adaptiveWeb.model.LoggedData;
import com.adaptiveWeb.model.PieChartData;
import com.adaptiveWeb.model.ScatterPlotData;
import com.adaptiveWeb.model.StackoverflowData;

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

	

	
	public List<PieChartData> getPieChart(String username) {
		String sql= "SELECT eventType, COUNT(*) as count FROM eventinfo WHERE username=? GROUP BY eventType";
		List<PieChartData> data =jdbcTemplate.query(sql, new Object[]{username}, new donutMapper()) ;
		return data.size()>0? data:null;
	}

	public List<BarChartData> getbarChart(String username) {
		String sql= "select eventType, sum(case when username  ='" + username + "' then 1 else 0 end) userCount,"
				+ " sum(case when username <> '" + username + "' then 1 else 0 end) otherCount,"
				+ "sum(case when username in ('aaa', 'bbb', 'ccc','ddd', 'eee') then 1 else 0 end)/5 average"
				+ " from eventinfo group by eventType ";
		List<BarChartData> data =jdbcTemplate.query(sql, new barMapper()) ;
		return data.size()>0? data:null;
		
		
	}

	public List<ScatterPlotData> getScatterPlot(String username) {
		//String sql1="CREATE VIEW table1 AS (SELECT username, count(eventType)as interactions FROM eventinfo GROUP BY username)"; 
		//String sql2="CREATE VIEW table2 AS (SELECT username, eventType, count(*) as totalcout from logininfo where eventType= 'Log In' GROUP BY username, eventType )";
		String sql3="SELECT table1.username, table2.totalcout, table1.interactions FROM table1, table2 WHERE table1.username=table2.username";
		//jdbcTemplate.execute(sql1);
		//jdbcTemplate.execute(sql2);
		List<ScatterPlotData> data=jdbcTemplate.query(sql3, new scatterMapper());

		return data;
	}

	public List<BubblePlotData> getBubblePlot(String username1) {
		String sql="SELECT eventType, count(username) as usercount, count(eventType) as eventcount, sum(case when username ='" + username1 + "' then 1 else 0 end)/(SELECT count(*) from eventinfo where username='" + username1 + "' and eventType REGEXP 'Tag:.*') as avguser, sum(case when username <>'" + username1 + "' then 1 else 0 end)/(SELECT count(*) from eventinfo where username <> '" + username1 + "' and eventType REGEXP 'Tag:.*') as avgother from eventinfo where (eventType REGEXP 'Tag:.*')GROUP by eventType";
		List<BubblePlotData> data=jdbcTemplate.query(sql, new bubbleMapper());
		return data;
	}




	



	public void updateRows() {
		// TODO stub
		String sql="DELETE FROM `eventinfo` WHERE eventType ='mouse idle'";
		jdbcTemplate.execute(sql);
		
	}

}


class scatterMapper implements RowMapper<ScatterPlotData> {
	public ScatterPlotData mapRow(ResultSet rs, int arg1) throws SQLException {
		ScatterPlotData scatterData = new ScatterPlotData();
		scatterData.setUsername(rs.getString("username"));
		scatterData.setNoOfInteractions(rs.getInt("interactions"));
		scatterData.setNoOfLogins(rs.getInt("totalcout"));
		return scatterData;
	}
}

class bubbleMapper implements RowMapper<BubblePlotData> {
	public BubblePlotData mapRow(ResultSet rs, int arg1) throws SQLException {
		BubblePlotData bubbleData = new BubblePlotData();
		bubbleData.setUsercount(rs.getInt("usercount"));
		bubbleData.setEventcount(rs.getInt("eventcount"));
		bubbleData.setAvguser(rs.getFloat("avguser"));
		bubbleData.setAvgother(rs.getFloat("avgother"));
		bubbleData.setEventname(rs.getString("eventType"));
		return bubbleData;
	}
}


class barMapper implements RowMapper<BarChartData> {
	public BarChartData mapRow(ResultSet rs, int arg1) throws SQLException {
		BarChartData barData = new BarChartData();
		barData.setEvent(rs.getString("eventType"));
		barData.setUsercount(rs.getInt("userCount"));
		barData.setOthercount(rs.getInt("otherCount"));
		barData.setAverage(rs.getFloat("average"));
		return barData;
	}


}


class eventinfoMapper1 implements RowMapper<StackoverflowData> {
	public StackoverflowData mapRow(ResultSet rs, int arg1) throws SQLException {
		StackoverflowData eventData = new StackoverflowData();
		eventData.setUsername(rs.getString("username"));
		eventData.setTimestamp(rs.getString("timestampp"));
		eventData.setEvent(rs.getString("eventType"));
		eventData.setParent(rs.getInt("parent"));
		eventData.setFlag(rs.getString("flag"));
		eventData.setIndex(rs.getInt("id"));

		return eventData;
	}


}

class donutMapper implements RowMapper<PieChartData> {
	public PieChartData mapRow(ResultSet rs, int arg1) throws SQLException {
		PieChartData pieChart = new PieChartData();
		pieChart.setCount(rs.getInt("count"));
		pieChart.setEventType(rs.getString("eventType"));

		return pieChart;
	}


}
