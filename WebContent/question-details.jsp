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
    String indexUrl = "<< Back to index.";
    QuestionBean question = QuestionAdmin.getQuestion(new Integer(id));
    String name = question.getQuestionName();
    String explanation = question.getExplanation();
    String test1 = question.getTestCase_1();
    String expected1 = question.getExpected_1();
    String test2 = question.getTestCase_2();
    String expected2 = question.getExpected_2();
    String test3 = question.getTestCase_3();
    String expected3 = question.getExpected_3();    
    %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%= name %></title>
<style type="text/css">
textarea {
  overflow-wrap: normal;
  overflow-x: scroll;
  resize: none;
}

.class {
	vertical-align: top;
}
</style>
</head>
<body>

<jsp:include page="user-details.jsp" />

<a href="admin-question-index.jsp" style="text-decoration: none; margin-left: 10px;"><b><%= indexUrl %></b></a><br>

<table class="class">
<tr><th></th></tr>
<tr>
<td class="class" >Question name :</td>
<td class="class" ><%= name %></td>
</tr>
<tr>
<td class="class" >Question explanation :</td>
<td class="class" ><textarea name="explanation" rows="6" cols="120" readonly="readonly" ><%= explanation %></textarea></td>
</tr>
<tr>
<td class="class" >Test case 1 :</td>
<td class="class" ><%= test1 %></td>
</tr>
<tr>
<td class="class" >Expected output 1 :</td>
<td class="class"><%= expected1 %></td>
</tr>
<tr>
<td class="class">Test case 2 :</td>
<td class="class"><%= test2 %></td>
</tr>
<tr>
<td class="class">Expected output 2 :</td>
<td class="class"><%= expected2 %></td>
</tr>
<tr>
<td class="class">Test case 3 :</td>
<td class="class"><%= test3 %></td>
</tr>
<tr>
<td class="class">Expected output 3 :</td>
<td class="class"><%= expected3 %></td>
</tr>
</table>
<a href="admin-question-index.jsp" style="text-decoration: none; margin-left: 10px;"><b><%= indexUrl %></b></a>
</body>
</html>