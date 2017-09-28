<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>




<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
	google.charts.load('current', {
		'packages' : [ 'corechart' ]
	});
	google.charts.setOnLoadCallback(drawVisualization);

	function drawVisualization() {

		var data = eval('(' + '${dataJson}' + ')');
		console.log(data.barChartlist.length);
		console.log(data);

		var array = [ [] ];
		array[0] = [ 'Event', 'User', 'Other Users', 'Average' ]
		var j = 1;
		var usercount=0;
		var othercount=0;
		for (var i = 0; i < data.barChartlist.length; i++) {
			if (data.barChartlist[i].event.match("Tag:.*")) {
				usercount = usercount + data.barChartlist[i].usercount
				othercount=othercount+ data.barChartlist[i].othercount
			} else {

				array[j] = [ data.barChartlist[i].event,
						data.barChartlist[i].usercount,
						data.barChartlist[i].othercount,
						data.barChartlist[i].average ]

				j++;
			}
		}
		
		array[j] = [ "Tag clicks",usercount, othercount, (usercount+othercount)/3 ];

		// Some raw data (not necessarily accurate)
		var data = google.visualization.arrayToDataTable(

		array

		);

		var options = {
			title : 'Various Events Counts by User',
			animation:{
	            duration: 2000,
	            easing: 'in',
	            startup:'true'
	          },
			vAxis : {
				title : 'Count'
			},
			hAxis : {
				title : 'Event'
			},
			seriesType : 'bars',
			series : {
				2 : {
					type : 'line'
				}
			},
			colors : [ 'blue', 'red', 'green', 'gray' ]
		};

		var chart = new google.visualization.ComboChart(document
				.getElementById('chart_div'));
		chart.draw(data, options);
	}
</script>
</head>
<body>
	<div align="center">
		<h2>Welcome ${message}</h2>
		<div>
			<p>
				<a
					href="https://st	ackoverflow.com/questions/tagged/java?sort=frequent&pageSize=15">Direct
					to Stackoverflow</a>
			</p>
		</div>

		<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Adaptive Web</a>
			</div>
			<ul class="nav navbar-nav ">
				<li><a href="#" id="myButton">Log-In History</a></li>
				<li><a href="#" id="question">Why do you decide to log
						these actions?</a></li>
				<li><a href="#" id="stackoverflow">Stackoverflow Event Log</a></li>
				<li><a href="#" id="visualization">Visualizations</a></li>
			</ul>

			<ul class="nav navbar-nav navbar-right">
				<li><a href="#" id="logoutbutton">Log Out</a></li>
			</ul>


		</div>
		</nav>

		<!--  button id="myButton" type="button" class="btn btn-primary">See
			Your Log-In History</button-->



		<button id="pieChart" type="button" class="btn btn-primary">Pie
			Chart</button>

		<button id="barChart" type="button" class="btn btn-primary">Bar
			Chart</button>
		<button id="bubblePlot" type="button" class="btn btn-primary">Bubble
			Plot (BONUS)</button>
		<button id="scatterPlot" type="button" class="btn btn-primary">Scatter
			Plot</button>
			<button id="findings" type="button" class="btn btn-primary">Patterns/Trends</button>


	</div>


	<div id="chart_div" style="width: 900px; height: 500px;"></div>

	<script type="text/javascript">
		document.getElementById("myButton").onclick = function() {
			var ctx = "${pageContext.request.contextPath}"
			location.href = ctx + "/getInfo";
			//document.getElementById("title").innerHTML = "Log-In History";
			//document.getElementById("column1").innerHTML = "Log-In History";
			//document.getElementById("column2").innerHTML = "Log-In History";

		};

		document.getElementById("logoutbutton").onclick = function() {
			var ctx = "${pageContext.request.contextPath}"
			location.href = ctx + "/logout";
		};

		document.getElementById("question").onclick = function() {
			var ctx = "${pageContext.request.contextPath}"
			location.href = ctx + "/answer";
		};

		document.getElementById("stackoverflow").onclick = function() {
			var ctx = "${pageContext.request.contextPath}"
			location.href = ctx + "/stackoverflow";
		};

		document.getElementById("pieChart").onclick = function() {
			var ctx = "${pageContext.request.contextPath}"
			location.href = ctx + "/pieChart";
		};

		document.getElementById("barChart").onclick = function() {
			var ctx = "${pageContext.request.contextPath}"
			location.href = ctx + "/barChart";
		};

		document.getElementById("scatterPlot").onclick = function() {
			var ctx = "${pageContext.request.contextPath}"
			location.href = ctx + "/scatterPlot";
		};

		document.getElementById("bubblePlot").onclick = function() {
			var ctx = "${pageContext.request.contextPath}"
			location.href = ctx + "/bubblePlot";
		};
		
		document.getElementById("findings").onclick = function() {
			var ctx = "${pageContext.request.contextPath}"
			location.href = ctx + "/findings";
		};
	</script>

</body>
</html>