<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.wipro.gamificationstring.bean.UserBean, com.wipro.gamificationstring.service.UserAdmin" %>
    
    
    <%
    
    String id = "";
    String name = "";
    String email = "";
	String postUrl = "CreateUser";
	String buttonValue = "Create User";
	String welcomeTitle = "Create user";
	String indexUrlString = "<< Back to LogIn";
	String indexUrl = "index.jsp";
    
    id = request.getParameter("linkId");
    if(!(id == null)){
    	UserBean user = UserAdmin.getUser(new Integer(id));
    	name = user.getName();
    	email = user.getName();
    	postUrl = "UpdateUser";
    	buttonValue = "Update User";   	
    	welcomeTitle = "Edit User";
    	indexUrlString = "<< Back to questions";
    	if(request.getSession().getAttribute("GamificationStringAccountType").equals("admin")){
    		indexUrl = "admin-question-index.jsp";
    	} else {
    		indexUrl = "question-index.jsp";
    	}
    } else {
    	id = "";
    }
    
    %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User</title>
<style type="text/css">
textarea {
  overflow-wrap: normal;
  overflow-x: scroll;
}
</style>
<script src="js/jquery.js"></script>
<script type="text/javascript">

$(document).ready(function() {

var $form = $('#questionForm');

$form.on('submit', function(ev){
    ev.preventDefault();
    var password = $("#password").val();
    var confirmPassword = $("#repassword").val();
    if (password != confirmPassword) {
        alert("Passwords do not match.");
        return false;
    }
    $("#hidden_div").show();
    
    $.ajax({
        url: $form.attr("action"),
        type:   'POST',
        data: $form.serialize(),
        success: function(msg){
        	$("#hidden_div").text(msg);
        }
    });
});
});

</script>
</head>
<body>

<% if(!(id.equals("") || id.equals(null))){ %>

<jsp:include page="user-details.jsp" />

<% } %>

<div id="hidden_div" align="center" hidden style="color: green;">Submitting data please wait.</div>

<div align="left"><font size="30px"><b><%= welcomeTitle %></b></font></div>
<br><br>
<a href="<%= indexUrl %>" style="text-decoration: none; margin-left: 5px;"><b><%= indexUrlString %></b></a>
<br><br>
<form id="questionForm" action="<%= postUrl %>" method="post">
<input type="hidden" name="id" value="<%= id %>">
<table>
<tr><th></th></tr>
<tr>
<td>Enter name :</td>
<td><input required type="text" name="name" value="<%= name %>" required ></td>
</tr>
<tr>
<td>Enter email :</td>
<td><input required type="text" name="email" value="<%= email %>" required ></td>
</tr>
<tr>
<td>Enter password :</td>
<td><input required type="password" id="password" name="password" value="" required ></td>
</tr>
<tr>
<td>Reenter password :</td>
<td><input required type="password" id="repassword" name="repassword" value="" required ></td>
</tr>
</table>
<input type="submit" value="<%= buttonValue %>" onclick="return Validate()" >
<a href="<%= indexUrl %>" style="text-decoration: none; margin-left: 10px;"><b><%= indexUrlString %></b></a>
</form>

</body>
</html>