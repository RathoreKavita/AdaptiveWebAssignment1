
<%@ page contentType="text/html; charset = UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Hello World</title>
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
      <li><a href="#" id="question">Why do you decide to log these actions?</a></li>
      <li><a href="#" id="stackoverflow">Stackoverflow Event Log</a></li>
      <li><a href="#" id="visualization">Visualizations</a></li>
      </ul>
      
      <ul class="nav navbar-nav navbar-right">
      <li ><a href="#" id="logoutbutton">Log Out</a></li>
	 </ul>
      
    
  </div>
</nav>
		<!--  button id="myButton" type="button" class="btn btn-primary">See
			Your Log-In History</button-->
		
		
		
		<!--  >button id="pieChart" type="button" class="btn btn-primary">Pie Chart</button>
		<button id="barChart" type="button" class="btn btn-primary">Bar Chart</button>
		<button id="bubblePlot" type="button" class="btn btn-primary">Bubble Plot</button>
		<button id="scatterPlot" type="button" class="btn btn-primary">Scatter Plot</button-->
		
	
			
		<p class="container"></p>
			
		<p class="container">${answer1} </p>
		<p class="container">${answer2} </p>
		<p class="container">${answer3}</p>
		<p class="container">${answer4}</p>
		<p class="container">${answer5}</p>
		<p class="container">${answer6}</p>
		<p class="container">${answer7}</p>
		<p class="container">${answer8}</p>
		

	</div>


	<div align="center" id="log">
		<table border="1" cellpadding="5">
			<caption>
				<h2 id="title">${title}</h2>
			</caption>
			<tr>
				<th id="column1">${column1}</th>
				<th id="column2">${column2}</th>


			</tr>

			<c:forEach var="log" items="${logData}">
				<tr>
					<td>${log.timestamp}</td>

					<td>${log.eventType}</td>

				</tr>
			</c:forEach>


		</table>
	</div>



	<div align="center">
	<p></p>
	
			
	</div>

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
		
		
		
	/*	document.getElementById("pieChart").onclick = function() {
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
		};  */
		
		document.getElementById("visualization").onclick = function() {
			var ctx = "${pageContext.request.contextPath}"
			location.href = ctx + "/visualization";
		};

	</script>


</body>
</html>