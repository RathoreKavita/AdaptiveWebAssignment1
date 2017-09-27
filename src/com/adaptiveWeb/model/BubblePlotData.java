package com.adaptiveWeb.model;

public class BubblePlotData {
	
	private String eventname;
	private int usercount;
	private int eventcount;
	private float avguser;
	private float avgother;
	public int getUsercount() {
		return usercount;
	}
	public void setUsercount(int usercount) {
		this.usercount = usercount;
	}
	public int getEventcount() {
		return eventcount;
	}
	public void setEventcount(int eventcount) {
		this.eventcount = eventcount;
	}
	public float getAvguser() {
		return avguser;
	}
	public void setAvguser(float avguser) {
		this.avguser = avguser;
	}
	public float getAvgother() {
		return avgother;
	}
	public void setAvgother(float avgother) {
		this.avgother = avgother;
	}
	
	public String getEventname() {
		return eventname;
	}
	public void setEventname(String eventname) {
		this.eventname = eventname;
	}

}
