<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
 background-color:#d8d4d4;
 border:1px solid #777575;
 border-radius:5px;
 color:black;
 margin-left:5px;
 margin-right:10px;
 padding:5px 5px 5px 5px;
}
</style>
</head>
<body>
<div style="background-color: yellow;">
<table style="width: 100%;
	border-collapse: collapse;
    margin-left: 10px;
    padding: 5px;
    vertical-align: middle;
    size: 25px;
	font-weight: bold;
	height: 40px;" >
<tr>
<th align="left">Hello <%=session.getAttribute("GamificationStringUserEmail") %>&nbsp;&nbsp;&nbsp;<a href="userForm.jsp?linkId=<%= session.getAttribute("GamificationStringId") %>" class="button">Edit Account</a></th>
<th align="left"></th>
<th align="right"><a href="LogOut" class="button" >LogOut</a></th>
</tr>
</table>
</div>
</body>
</html>