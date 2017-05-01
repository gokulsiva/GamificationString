<!-- author : Surya -->

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

body {
	background: #DFDCE3;
}

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
 font-family: cursive;
 background: #FFE658;
 border:1px solid #777575;
 border-radius:5px;
 color:black;
 margin-left:5px;
 margin-right:5px;
 padding:5px 5px 5px 5px;
}

a.button:hover {
	background: #577084;
	color: white;
}

.table {
	font-family: sans-serif;
}

.tr{
	background: #ECECEA;
}

.table tr:hover{
	background: #FDF3E7;
}

.th{
	background: #74AFAD;
}

</style>

<script src="js/jquery.js"></script>

<script type="text/javascript">

function ajaxFunction (e) {
    var link = e.getAttribute('value');
    var r = confirm("Are you sure to delete?");
    if (r == true) {
    	$('#hidden_div').show();
    	console.log(r);
    	$.ajax({
            url: link,
            type: 'GET',
            success: function(response) {
            	location.reload(true);
            	console.log(response);
            	$("#hidden_div").text(response);
            	location.reload(true);
            }
        });
    }
    
}

</script>

</head>
<body>
<div>
<jsp:include page="user-details.jsp" />
</div>

<div id="container">

<div id="hidden_div" align="center" hidden style="color: green;">Deleting data please wait.</div>

<div align="center"><font size="30px"><b>List of Questions</b></font></div>

 &nbsp;<a href="questionForm.jsp" class="button"> <font style="font-weight: bold;">Create new question</font> </a>
<br><br>

<table width="94%" style="border-collapse: collapse; 
    margin-left: 10px;
    padding: 5px;" class="class table">

<tr class="class tr" >
<th align="center" class="class th" >S.No</th>
<th align="center" class="class th" >Name</th>
<th align="center" class="class th" >Options</th>
</tr>

<% for(QuestionBean question: questions){ %>

<tr class="class tr" >

<td align="center" class="class td" ><%= sno %></td>
<td align="center" class="class td" ><%= question.getQuestionName() %></td>
<td width="50%" align="center" class="td">

<a align="center" href="question-details.jsp?id=<%= question.getQuestionId() %>" class="button"> <font>Show question details</font> </a>
<a align="center" href="questionForm.jsp?id=<%= question.getQuestionId() %>" class="button"> <font>Edit question</font> </a>
<a align="center" href="" value="admin-delete-question.jsp?id=<%= question.getQuestionId() %>" class="button" onclick="ajaxFunction(this);"> <font>Delete question</font> </a>

</td>
</tr>

<% sno++; } %>
</table>


</div>

</body>
</html>