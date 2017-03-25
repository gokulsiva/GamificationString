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

.class {
    border: 1px solid black;
    border-collapse: collapse;
    vertical-align: middle;
    size: 25px;
	font-weight: bold;
	height: 40px;
	margin-left: 15%;
	padding: 5px;
}

tr:hover td {
    background: #dddddd;
}

</style>
</head>
<body>

<jsp:include page="user-details.jsp" />

<h3>List of Questions :</h3>
<table class="class">
<tr class="class">
<th class="class">S.No</th>
<th class="class">Name</th>
<th class="class">Status</th>
</tr>


<% for(QuestionBean question : questions){ %>
<tr class="class" style="cursor:pointer" onclick="document.location.href='editor.jsp?id=<%= question.getQuestionId() %>'">
	<td class="class"><%= sno %></td>
	<td class="class"><%= question.getQuestionName() %></td>
	<td class="class"><%= (userSolved.contains(question.getQuestionId()))?"Solved":"Try It" %></td>
</tr>	
	<% sno++;} %>

</table>
</body>
</html>