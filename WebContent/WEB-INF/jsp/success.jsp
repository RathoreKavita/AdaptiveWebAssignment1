
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
					href="https://stackoverflow.com/questions/tagged/java?sort=frequent&pageSize=15">Direct
					to Stackoverflow</a>
			</p>
		</div>
		<button id="myButton" type="button" class="btn btn-primary">See
			Your Log-In History</button>
		<button id="logoutbutton" type="button" class="btn btn-primary">Log
			Out</button>
		<button id="question" type="button" class="btn btn-primary">Why
			do you decide to log these actions?</button>
		<button id="stackoverflow" type="button" class="btn btn-primary">Stackoverflow Event Log</button>
			
		<p class="container">${answer}
		</p>

	</div>


	<div align="center">
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
		<table border="1" cellpadding="5">
			<caption>
				<h2 id="title">${title}</h2>
			</caption>
			<tr>
				<th id="column1">${column1}</th>
				<th id="column2">${column2}</th>


			</tr>

			<c:forEach var="event" items="${eventData}">
				<tr>
					<td>${event.timestamp}</td>

					<td>${event.eventType}</td>

				</tr>
			</c:forEach>


		</table>
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
	</script>


</body>
</html>