package com.adaptiveWeb.DTO;

import java.util.List;

import com.adaptiveWeb.model.BarChartData;

public class BarChartDTO {
	
	 private List<BarChartData> barChartlist;

		public List<BarChartData> getbarChartlist() {
			return barChartlist;
		}

		public void setbarChartlist(List<BarChartData> barChartlist) {
			this.barChartlist = barChartlist;
		}

}
