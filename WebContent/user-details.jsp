<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.wipro.gamificationstring.service.UserAdmin" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
a{
 text-decoration:none;
 color:#cc0000;
}
a.button{
 font-family: cursive;
 background: #FFE658;
 border:1px solid #777575;
 border-radius:5px;
 color:black;
 margin-left:5px;
 margin-right:10px;
 padding:5px 5px 5px 5px;
}

hr {
	display: block;
    height: 1px;
    border: 0;
    border-top: 1px solid #C63D0F;
    margin: 1em 0;
    padding: 0; 
}
</style>
</head>
<body>
<div>
<table style="width: 100%;
	border-collapse: collapse;
    margin-left: 10px;
    padding: 5px;
    vertical-align: middle;
    size: 25px;
	font-weight: bold;
	height: 40px;" >
<tr>
<th align="left"><font style="font-family: cursive;"> Hello <font style="color: red;"><%=session.getAttribute("GamificationStringUserEmail") %></font>&nbsp;&nbsp;&nbsp;<a href="userForm.jsp?linkId=<%= session.getAttribute("GamificationStringId") %>" class="button">Edit Account</a></font></th>
<th align="left"></th>
<th align="right"><font style="color: red; font-family: cursive;"><%= (((String) session.getAttribute("GamificationStringAccountType")).equals("user"))?"Points : "+UserAdmin.getUser((String) session.getAttribute("GamificationStringUserEmail")).getSolvedQuestions().size()*50:""%></font></th>
<th align="right" style="padding-right: 5px;"><a href="LogOut" class="button" >LogOut</a></th>
</tr>
</table>
</div>
<hr>
</body>
</html>