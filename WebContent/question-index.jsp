<!-- author : Sowmiya -->


<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@page import="com.wipro.gamificationstring.service.UserAdmin"%>
<%@page import="com.wipro.gamificationstring.bean.UserBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="com.wipro.gamificationstring.service.QuestionAdmin, com.wipro.gamificationstring.bean.QuestionBean, java.util.List" %>
    
    <%
    	if(session.getAttribute("GamificationStringUserEmail") == null){
			response.sendRedirect("index.jsp?message=Please LogIn.");
			return;
		}
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
    response.setDateHeader("Expires", 0); // Proxies.
    %>
    
    <% List<QuestionBean> questions = QuestionAdmin.getAllQuestions(); 
    	int sno = 1;
    	System.out.println((String) request.getSession().getAttribute("GamificationStringId"));
    	HashSet<Integer> userSolved = UserAdmin.getUser(new Integer((String) request.getSession().getAttribute("GamificationStringId"))).getSolvedQuestions();
    	System.out.println(userSolved);
    %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Question's Index</title>
<style type="text/css">

body {
	background: lightgrey;
}

.class {
    border: 1px solid black;
    border-collapse: collapse;
    vertical-align: middle;
    size: 25px;
	font-weight: bold;
	height: 40px;
	padding: 5px;
	text-align: center;
}

.table {
	font-family: sans-serif;
}

.tr{
	background: #ECECEA;
}

.th{
	background: #74AFAD;
}


tr:hover td {
    background: #dddddd;
    font-family: cursive;
}

a.button:hover {
	background: #577084;
	color: white;
}

</style>
</head>
<body>

<jsp:include page="user-details.jsp" />

<h3 align="center">List of Questions</h3>
<table width="96%" align="center" class="class table">
<tr class="class">
<th class="class th">S.No</th>
<th class="class th">Name</th>
<th class="class th">Status</th>
</tr>


<% for(QuestionBean question : questions){ %>
<tr class="class tr" style="cursor:pointer" onclick="document.location.href='editor.jsp?id=<%= question.getQuestionId() %>'">
	<td class="class td"><%= sno %></td>
	<td class="class td"><%= question.getQuestionName() %></td>
	<td class="class td"><%= (userSolved.contains(question.getQuestionId()))?"Solved":"<font style=\"color: red;\">Try It</font>" %></td>
</tr>	
	<% sno++;} %>

</table>
</body>
</html>