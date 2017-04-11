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
 border:1px solid #777575;
 border-radius:5px;
 color:black;
 margin-left:5px;
 margin-right:10px;
 padding:5px 5px 5px 5px;
 resize: none;
}
body {
	background: #DFDCE3;
}
.font-class {
	font-family: cursive;
}
input[type=text]{
 width: 250px;
 border:1px solid #777575;
 border-radius:5px;
 color:black;
 margin-left:5px;
 margin-right:10px;
 padding:5px 5px 5px 5px;
}

a:hover {
	color: red;
}

</style>
</head>
<body>

<jsp:include page="user-details.jsp" />

<a href="admin-question-index.jsp" style="text-decoration: none; margin-left: 10px;"><b><%= indexUrl %></b></a><br><br>

<table>
<tr><th></th></tr>
<tr>
<td class="font-class">Enter question name </td>
<td>:</td>
<td><input type="text" name="name" value="<%= name %>" readonly></td>
</tr>
<tr>
<td class="font-class">Enter question explanation </td>
<td>:</td>
<td><textarea name="explanation" rows="6" cols="120" readonly ><%= explanation %></textarea></td>
</tr>
<tr>
<td class="font-class">Enter primary test case </td>
<td>:</td>
<td><input type="text" name="testcase1" value="<%= test1 %>" readonly ></td>
</tr>
<tr>
<td class="font-class">Enter primary expected output </td>
<td>:</td>
<td><input type="text" name="expected1" value="<%= expected1 %>" readonly ></td>
</tr>
<tr>
<td class="font-class">Enter test case 2 </td>
<td>:</td>
<td><input type="text" name="testcase2" value="<%= test2 %>" readonly ></td>
</tr>
<tr>
<td class="font-class">Enter expected output 2 </td>
<td>:</td>
<td><input type="text" name="expected2" value="<%= expected2 %>" readonly ></td>
</tr>
<tr>
<td class="font-class">Enter test case 3 </td>
<td>:</td>
<td><input type="text" name="testcase3" value="<%= test3 %>" readonly ></td>
</tr>
<tr>
<td class="font-class">Enter expected output 3 </td>
<td>:</td>
<td><input type="text" name="expected3" value="<%= expected3 %>" readonly ></td>
</tr>
<tr>
<td  style="padding-top: 20px;"><a href="admin-question-index.jsp" style="text-decoration: none; margin-left: 10px;"><b><%= indexUrl %></b></a></td>
<td></td>
</tr>
</table>
</body>
</html>