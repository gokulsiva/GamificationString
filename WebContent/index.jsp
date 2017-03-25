<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%
    
    	String email = (String) request.getParameter("email");
    	String message = (String) request.getParameter("message");
    	if(email == null){
    		email = "";
    	}
    	if(message == null){
    		message = "";
    	}
    
    %>
    
    
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LogIn</title>

<style type="text/css">

::-webkit-input-placeholder {
   text-align: center;
}

:-moz-placeholder { /* Firefox 18- */
   text-align: center;  
}

::-moz-placeholder {  /* Firefox 19+ */
   text-align: center;  
}

:-ms-input-placeholder {  
   text-align: center; 
}

input {
width: 20%;
height: 3em;
-webkit-border-radius:20px;
-moz-border-radius:20px;
-border-radius:20px;
text-align: center;
}

body {
	 background-color: #AFEEEE;
}

</style>


</head>
<body>

<div align="center" style="width: 100%; color: red; font-size: 20px;"><%= message %></div>

<div id="container" align="center" style="text-align: center; height: 100%; width: 100%; margin-top: 20%;">
<div style="font-weight: bold; color: red;">Admin/User Login</div><br>

	<form action="LogIn" method="post">
		<input required type="text" name="email" placeholder="Email" value="<%= email %>">
		<br><br>
		<input required type="password" name="password" placeholder="Password">
		<br><br>
		<input required type="submit" value="LogIn" style="width: 10%;">
	</form>
	<br><br><br><br><br><br><br>
	<a href="userForm.jsp">Need to SignUp? Click Here.</a>

</div>

</body>
</html>