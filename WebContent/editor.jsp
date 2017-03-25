<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<%@ page import="com.wipro.gamificationstring.util.ProgramStrings, com.wipro.gamificationstring.service.QuestionAdmin, com.wipro.gamificationstring.bean.QuestionBean" %>

<%
    	if(session.getAttribute("GamificationStringUserEmail") == null){
			response.sendRedirect("index.jsp?message=Please LogIn.");
			return;
		}
    %>

<%
	QuestionBean question = null;
	String indexUrl = "question-index.jsp"; 
	String indexUrlString = "<< Back to questions.";
	if(request.getParameter("id") != null) {
	question = QuestionAdmin.getQuestion(new Integer(request.getParameter("id")));
	
	} else {
		response.sendRedirect("index.jsp");
		return;
	}
%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Editor</title>
<script src="js/jquery.js"></script>
<script type="text/javascript">
    function Validate() {
    	var frm = document.getElementById('code_form') || null;
    	if(document.getElementById('useFile').checked == true)
    	{
        var allowedFiles = [".java"];
        var fileUpload = document.getElementById("FileUploader");
        var regex = new RegExp("([a-zA-Z0-9\s_\\.\-:])+(" + allowedFiles.join('|') + ")$");
        if (!regex.test(fileUpload.value.toLowerCase())) {
        	alert("Upload java files only.");
        } else {
           frm.enctype = 'multipart/form-data';
           frm.action = 'UploadServlet'; 
           $('#hidden_div').show();
           var form = $("#code_form")[0]; // You need to use standart javascript object here
           var formData = new FormData(form);
           formData.append('file', $('input[type=file]')[0].files[0]); 
           $.ajax({
       		type:"POST",
               url:'UploadServlet',
               data: formData,
               contentType: false,
               processData: false,
               success: function(response){
                   console.log(response);  
               	$('#hidden_div').hide();
               	$("textarea[name='output']").html(response);  
               }
           });     
        }
    	} else {
    	frm.enctype = 'application/x-www-form-urlencoded';
        frm.action = 'Compiler';
        $('#hidden_div').show();
        var form=$("#code_form");
    	$.ajax({
    		type:"POST",
            url:form.attr("action"),
            data:$("#code_form").serialize(),
            success: function(response){
                console.log(response);  
            	$('#hidden_div').hide();
            	$("textarea[name='output']").html(response);  
            }
        });
    	}	
    }
    
    function finalSubmission() {
    	$('#hidden_div').show();
    	var id = document.getElementById('questionId').value;
    	console.log(id);
    	$.ajax({
            url: "TestCaseChecker?id="+id,
            type: 'GET',
            success: function(response) {
            	$('#hidden_div').hide();
                console.log(response);
                var str = response.split(" ");
                $("textarea[name='output']").text(response);
                var result = str[str.length - 1];
                console.log(result);
                if(result.trim() == 'passed.') {
                    alert('Congrats you solved this problem successfully.');
                }
            }
        });
    	
    }
    
</script>
<style type="text/css">

.wrap {
    width: 100%;
    height: 350px;
    overflow:auto;
}

.fleft {
    float:left; 
    width: 24.5%;
	background:lightblue;
    height: 350px;
}

.fcenter{
    float:left;
    width: 50%;
    background:lightgreen;
    height:350px;
    margin-left:0.25%;
}

.fright {
float: right;
    background:pink;
    height: 350px;
    width: 24.5%;
    
}

.container {
    width: 99.2%;
    height: 100%;
    background-color: pink;
}

.fill {
    height: 78.9%;
    width: 100%;
}

h3 {
	padding: 0;
	margin: 0;
}

textarea {
  overflow-wrap: normal;
  overflow-x: scroll;
  resize: none;
}

code {
    font-family: monospace;
}

</style>
</head>
<body>
<jsp:include page="user-details.jsp" />
<div id="hidden_div" align="center" hidden style="color: green;">Compiling....Please wait....</div>

<a href="<%= indexUrl %>" style="text-decoration: none; margin-left: 10px;"><b><%= indexUrlString %></b></a>

<div style="width: 100%; height: 100%;">

	<div id="explanation" style="width: 100%;">
		<h3>Explanation:</h3><br>
		<b> <%= question.getQuestionName() %> </b>
		<textarea spellcheck="false" readonly style="width: 100%;  margin-top: 0px;" rows="5"><%= question.getExplanation() %></textarea>
	</div>
	
	<div class="wrap">
	    <div class="fleft">
	    	<h3>Expected output:</h3><br><br>
	    	<textarea name="expected" spellcheck="false" form="code_form" readonly style="width: 98%;" rows="10"><%= question.getExpected_1() %></textarea>
	    </div>
	    <div class="fcenter">
		    <h3>Enter your code here:</h3><br>
		    <div class="container">
		    <code>
		    <textarea name="code" spellcheck="false" form="code_form" style="font-family: monospace; white-space: pre-wrap;" class="fill"><%= ProgramStrings.getTemplate() %></textarea>
		    </code>
			</div>
	    </div>
	    <div class="fright">
	    	<h3>Program output:</h3><br><br>
	    	<textarea name="output" spellcheck="false" readonly style="width: 98%;" rows="15"></textarea>
	    </div>
	</div>
	<br>
	<div style="clear: both;" align="center">
	<form method="post" id="code_form">
	<a href="<%= indexUrl %>" style="text-decoration: none; margin-left: 10px;"><b><%= indexUrlString %></b></a>&nbsp;&nbsp;&nbsp;
	<input type="hidden" name="questionId" id="questionId" value="<%= question.getQuestionId()%>">
	<input id="useFile" type="checkbox" name="File" value="file"/>Use java file instead. &nbsp;
	<input id="FileUploader" type="file" name="file" accept=".java" size="100">
	<input type="button" value="Compile & Test" onclick="Validate()"/>
	<input type="button" value="Final Submit" style="margin-right: 6em; float: right;" onclick="finalSubmission()">
	</form>
	</div>

</div>


</body>
</html>