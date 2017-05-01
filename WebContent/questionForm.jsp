<!-- author : Surya -->


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.wipro.gamificationstring.bean.QuestionBean, com.wipro.gamificationstring.service.QuestionAdmin" %>
    
    <%
    	if(session.getAttribute("GamificationStringUserEmail") == null){
			response.sendRedirect("index.jsp?message=Please LogIn.");
			return;
		}
    %>
    
    <%
    
    String id = "";
    String name = "";
    String explanation = "";
    String test1 = "";
    String expected1 = "";
    String test2 = "";
    String expected2 = "";
    String test3 = "";
    String expected3 = "";
    String postUrl = "CreateQuestion";
    String buttonValue = "Create Question";
    String indexUrl = "<< Back to index";
    String welcomeTitle = "Create new question";
    
    id = request.getParameter("id");
    if(!(id == null)){
    	QuestionBean question = QuestionAdmin.getQuestion(new Integer(id));
    	name = question.getQuestionName();
    	explanation = question.getExplanation();
    	test1 = question.getTestCase_1();
    	expected1 = question.getExpected_1();
    	test2 = question.getTestCase_2();
    	expected2 = question.getExpected_2();
    	test3 = question.getTestCase_3();
    	expected3 = question.getExpected_3();
    	postUrl = "UpdateQuestion";
    	buttonValue = "Update Question";   	
    	welcomeTitle = "Edit question";
    } else {
    	id = "";
    }
    
    %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Question</title>
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
input[type=submit]{
 font-family: cursive;
 font-weight: bold;
 background: #FFE658;
 border:1px solid #777575;
 border-radius:5px;
 color:black;
 margin-left:5px;
 margin-right:10px;
 padding:5px 5px 5px 5px;
}
input[type=submit]:hover {
	background: #CBE32D;
}
a:hover {
	color: red;
}
</style>
<script src="js/jquery.js"></script>
<script type="text/javascript">

$(document).ready(function() {

var $form = $('#questionForm');

$form.on('submit', function(ev){
    ev.preventDefault();
    $("#hidden_div").show();
    
    $.ajax({
        url: $form.attr("action"),
        type:   'POST',
        data: $form.serialize(),
        success: function(msg){
        	$("#hidden_div").text(msg);
        },
        error: function() {
            alert("Bad submit");
        }
    });
});
});

</script>
</head>
<body>

<jsp:include page="user-details.jsp" />

<div id="hidden_div" align="center" hidden style="color: green;">Submitting data please wait.</div>

<div align="left"><font size="30px"><b><%= welcomeTitle %></b></font></div>
<br><br>
<a href="admin-question-index.jsp" style="text-decoration: none; margin-left: 5px;"><b><%= indexUrl %></b></a>
<br><br>
<form id="questionForm" action="<%= postUrl %>" method="post">
<input type="hidden" name="id" value="<%= id %>">
<table>
<tr><th></th></tr>
<tr>
<td class="font-class">Enter question name </td>
<td>:</td>
<td><input type="text" name="name" value="<%= name %>" required ></td>
</tr>
<tr>
<td class="font-class">Enter question explanation </td>
<td>:</td>
<td><textarea name="explanation" rows="6" cols="120" required ><%= explanation %></textarea></td>
</tr>
<tr>
<td class="font-class">Enter primary test case </td>
<td>:</td>
<td><input type="text" name="testcase1" value="<%= test1 %>" required ></td>
</tr>
<tr>
<td class="font-class">Enter primary expected output </td>
<td>:</td>
<td><input type="text" name="expected1" value="<%= expected1 %>" required ></td>
</tr>
<tr>
<td class="font-class">Enter test case 2 </td>
<td>:</td>
<td><input type="text" name="testcase2" value="<%= test2 %>" required ></td>
</tr>
<tr>
<td class="font-class">Enter expected output 2 </td>
<td>:</td>
<td><input type="text" name="expected2" value="<%= expected2 %>" required ></td>
</tr>
<tr>
<td class="font-class">Enter test case 3 </td>
<td>:</td>
<td><input type="text" name="testcase3" value="<%= test3 %>" required ></td>
</tr>
<tr>
<td class="font-class">Enter expected output 3 </td>
<td>:</td>
<td><input type="text" name="expected3" value="<%= expected3 %>" required ></td>
</tr>
<tr>
<td  style="padding-top: 20px;"><input type="submit" value="<%= buttonValue %>" ></td>
<td  style="padding-top: 20px;"><a href="admin-question-index.jsp" style="text-decoration: none; margin-left: 10px;"><b><%= indexUrl %></b></a></td>
</tr>
</table>
</form>

</body>
</html>