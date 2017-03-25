<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ page import="com.wipro.gamificationstring.service.QuestionAdmin, com.wipro.gamificationstring.bean.QuestionBean, java.util.List" %>
    
    <%
    	if(session.getAttribute("GamificationStringUserEmail") == null){
			response.sendRedirect("index.jsp?message=Please LogIn.");
			return;
		}
    %>
    
    <% 
    	List<QuestionBean> questions = QuestionAdmin.getAllQuestions(); 
    	int sno = 1;
    %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin-Questions</title>
<style type="text/css">
a{
 text-decoration:none;
 color:#cc0000;
}

.class {
    border: 1px solid black;
    vertical-align: middle;
    size: 25px;
	font-weight: bold;
	height: 40px;
	padding: 5px;
}

a.button{
 background-color:#d8d4d4;
 border:1px solid #777575;
 border-radius:5px;
 color:black;
 margin-left:5px;
 margin-right:5px;
 padding:5px 5px 5px 5px;
}

</style>

<script src="js/jquery.js"></script>

<script type="text/javascript">

function ajaxFunction (e) {
	$('#hidden_div').show();
    var link = e.getAttribute('value');
    var r = confirm("Are you sure to delete?");
    if (r == true) {
    	console.log(r);
    	$.ajax({
            url: link,
            type: 'GET',
            success: function(response) {
            	location.reload(true);
            	console.log(response);
            	$("#hidden_div").text(response);
            }
        });
    }
    
}

</script>

</head>
<body>

<jsp:include page="user-details.jsp" />

<div id="container">

<div id="hidden_div" align="center" hidden style="color: green;">Deleting data please wait.</div>

<div align="center"><font size="30px"><b>List of Questions</b></font></div>

<a href="questionForm.jsp" class="button"> <font>Create new question</font> </a>
<br><br>

<table style="border-collapse: collapse;
    margin-left: 10px;
    padding: 5px;" class="class" >

<tr class="class" >
<th align="center" class="class" >S.No</th>
<th align="center" class="class" >Name</th>
<th align="center" class="class" >Options</th>
</tr>

<% for(QuestionBean question: questions){ %>

<tr class="class" >

<td class="class" ><%= sno %></td>
<td class="class" ><%= question.getQuestionName() %></td>
<td>

<a href="question-details.jsp?id=<%= question.getQuestionId() %>" class="button"> <font>Show</font> </a>
<a href="questionForm.jsp?id=<%= question.getQuestionId() %>" class="button"> <font>Edit</font> </a>
<a href="" value="admin-delete-question.jsp?id=<%= question.getQuestionId() %>" class="button" onclick="ajaxFunction(this);"> <font>Delete</font> </a>

</td>
</tr>

<% sno++; } %>
</table>


</div>

</body>
</html>