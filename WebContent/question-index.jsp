<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="com.wipro.gamificationstring.service.QuestionAdmin, com.wipro.gamificationstring.bean.QuestionBean, java.util.List" %>
    
    <%
    	if(session.getAttribute("GamificationStringUserEmail") == null){
			response.sendRedirect("index.jsp?message=Please LogIn.");
			return;
		}
    %>
    
    <% List<QuestionBean> questions = QuestionAdmin.getAllQuestions(); %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Question's Index</title>
</head>
<body>

<jsp:include page="user-details.jsp" />

<h3>List of Questions :</h3>

<ul>

<% for(QuestionBean question : questions){ %>

	<li><a href="editor.jsp?id=<%= question.getQuestionId() %>"><%= question.getQuestionName() %></a></li>

<% }  %>
</ul>

</body>
</html>