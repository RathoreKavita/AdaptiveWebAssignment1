<%@ page contentType = "text/html; charset = UTF-8" %>
<html>
  <title>Login</title>
  
  <!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
  
  <style type="text/css">
 
            body {font-family:Arial, Sans-Serif;}
 
            #container {width:300px; margin:0 auto;}
 
            /* Nicely lines up the labels. */
            form label {display:inline-block; width:140px;}
 
            /* You could add a class to all the input boxes instead, if you like. That would be safer, and more backwards-compatible */
            form input[type="text"],
            form input[type="password"],
            form input[type="email"] {width:160px;}
 
            form .line {clear:both;}
            form .line.submit {text-align:right;}
 
        </style>
        
  <body>
  <div id="container" class="container">
  <form name="MY Form" action="${pageContext.request.contextPath}/login" method="post">
  <br>
   <div class="line"> UserName  <input type="text"name="username" id="username">  </div>
  <div class="line"><br>Password      <input type="password" name="password" id="password"></div>
  <br>
  <input type="submit"name="button1"value="login" class="btn btn-primary">
  <!--  input type="submit"name="button2"value="signup"-->
  
  <button id="myButton" type="button" class="btn btn-primary">Sign Up</button>
  
   </div>
  
  <script type="text/javascript">
    document.getElementById("myButton").onclick = function () {
    	var ctx = "${pageContext.request.contextPath}"
        location.href = ctx+"/registration";
    };
</script>

  </form>
  </body>
</html>