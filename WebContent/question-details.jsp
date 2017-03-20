<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ page import="com.wipro.gamificationstring.bean.QuestionBean, com.wipro.gamificationstring.service.QuestionAdmin"%>
    
    <%
    String id = request.getParameter("id");
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

table td {
	vertical-align: top;
}
</style>
</head>
<body>
<table>
<tr><th></th></tr>
<tr>
<td>Question name :</td>
<td><%= name %></td>
</tr>
<tr>
<td>Question explanation :</td>
<td><textarea name="explanation" rows="6" cols="120" readonly="readonly" ><%= explanation %></textarea></td>
</tr>
<tr>
<td>Test case 1 :</td>
<td><%= test1 %></td>
</tr>
<tr>
<td>Expected output 1 :</td>
<td><%= expected1 %></td>
</tr>
<tr>
<td>Test case 2 :</td>
<td><%= test2 %></td>
</tr>
<tr>
<td>Expected output 2 :</td>
<td><%= expected2 %></td>
</tr>
<tr>
<td>Test case 3 :</td>
<td><%= test3 %></td>
</tr>
<tr>
<td>Expected output 3 :</td>
<td><%= expected3 %></td>
</tr>
</table>
</body>
</html>