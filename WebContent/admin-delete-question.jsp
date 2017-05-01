
<!-- author : Sowmiya -->


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ page import="com.wipro.gamificationstring.bean.QuestionBean, com.wipro.gamificationstring.service.QuestionAdmin"%>
    
    <%
    	if(session.getAttribute("GamificationStringUserEmail") == null){
			response.sendRedirect("index.jsp?message=Please LogIn.");
			return;
		}
    %>
    
    <%
    	String id = request.getParameter("id");
    	QuestionBean question = QuestionAdmin.getQuestion(new Integer(id));
    	String status = QuestionAdmin.deleteQuestion(question);
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<%= status %>
</body>
</html>