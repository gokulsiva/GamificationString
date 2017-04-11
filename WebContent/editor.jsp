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
<script src="js/animate.js"></script>
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
            	$("textarea[name='output']").html(response);  
            	var output = document.getElementById('output');
            	//animateText(output);
            	$('#hidden_div').hide();
            	
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
    
    function animateText(textArea) {
        let text = textArea.value;
        let to = text.length,
          from = 0;

        var primary = animate({
          duration: 5000,
          timing: bounce,
          draw: function(progress) {
            let result = (to - from) * progress + from;
            textArea.value = text.substr(0, Math.ceil(result))
          }
        });
    
    
        var hideDiv = function() {
        	$('#hidden_div').hide();
        }
        
        primary().then(hideDiv());
        
      }


      function bounce(timeFraction) {
        for (let a = 0, b = 1, result; 1; a += b, b /= 2) {
          if (timeFraction >= (7 - 4 * a) / 11) {
            return -Math.pow((11 - 6 * a - 11 * timeFraction) / 4, 2) + Math.pow(b, 2)
          }
        }
      }
    
</script>
<style type="text/css">

body {
	background: #CFDEF3;
}

.wrap {
    width: 100%;
    height: 330px;
    overflow:auto;
}

.fleft {
    float:left; 
    width: 24.5%;
    height: 330px;
}

.fcenter{
    float:left;
    width: 50%;
    height:330px;
    margin-left:0.25%;
}

.fright {
	float: right;
    height: 330px;
    width: 24.5%;
    
}

.container {
    width: 99.2%;
    height: 100%;
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
  overflow-x: auto;
  resize: none;
}

input:focus { 
    outline: none !important;
    border-color: #719ECE;
    box-shadow: 0 0 10px #719ECE;
}
textarea:focus { 
    outline: none !important;
    border-color: #719ECE;
    box-shadow: 0 0 10px #719ECE;
}

input[type=button] {
 font-family: cursive;
 font-weight: bold;
 background: #FFE658;
 border:1px solid #777575;
 border-radius:5px;
 color:black;
 margin-left:5px;
 margin-right:5px;
 padding:5px 5px 5px 5px;
}

code {
    font-family: monospace;
}

.font-class {
	font-family: cursive;
}

input[type="file"] {
    display: none;
}
.custom-file-upload {
    font-family: cursive;
 font-weight: bold;
 background: #FFE658;
 border:1px solid #777575;
 border-radius:5px;
 color:black;
 margin-left:5px;
 margin-right:5px;
 padding:5px 5px 5px 5px;
}
</style>
</head>
<body>
<jsp:include page="user-details.jsp" />
<div id="hidden_div" align="center" hidden style="color: green;">Compiling....Please wait....</div>


<div style="width: 100%; height: 100%;">

	<div id="explanation" style="width: 100%; padding-bottom: 10px;">
	<div style="padding-bottom: 5px;">
	<font class="font-class">
		<h3>Explanation:</h3>
	</font>
	</div>
	<b> <%= question.getQuestionName() %> </b>
	<div style="padding-top: 5px;">
		<textarea spellcheck="false" readonly style="font-family:cursive; border:solid 2px orange; width: 98%;  margin-top: 0px; background:transparent;" rows="4"><%= question.getExplanation() %></textarea>
	</div>	
	</div>
	
	<div class="wrap">
	    <div class="fleft">
	    <font class="font-class">
	    	<h3>Expected output:</h3><br><br>
	    	</font>
	    	<textarea name="expected" spellcheck="false" form="code_form" readonly style="font-family:cursive; border:solid 2px orange;background:transparent; width: 94%;" rows="8"><%= question.getExpected_1() %></textarea>
	    </div>
	    <div class="fcenter">
	    <font class="font-class">
		    <h3>Enter your code here:</h3><br>
		    </font>
		    <div class="container">
		    <code>
		    <textarea name="code" spellcheck="false" form="code_form" style="font-family:cursive; border:solid 2px orange; background:#eef2f3 ;font-family: monospace; white-space: pre-wrap;" class="fill"><%= ProgramStrings.getTemplate() %></textarea>
		    </code>
			</div>
	    </div>
	    <div class="fright">
	    <font class="font-class">
	    	<h3>Program output:</h3><br><br>
	    	</font>
	    	<textarea name="output" id="output" spellcheck="false" readonly style="font-family:cursive; border:solid 2px orange; background:transparent; width: 94%;" rows="8"></textarea>
	    </div>
	</div>
	<br>
	<div style="clear: both;" align="center">
	<form method="post" id="code_form">
	<table width="100%">
	<tr>
		<td><a href="<%= indexUrl %>" style="text-decoration: none; margin-left: 10px;"><b><%= indexUrlString %></b></a>&nbsp;&nbsp;&nbsp;</td>
		<td><input type="hidden" name="questionId" id="questionId" value="<%= question.getQuestionId()%>"></td>
		<td><input id="useFile" type="checkbox" name="File" value="file"/><font class="font-class">Use java file instead. &nbsp;</font></td>
		<td>
		<label for="FileUploader" class="custom-file-upload">
     	<font size="2px" class="font-class">Upload a java file</font>
		</label>
		<input id="FileUploader" type="file" name="file" accept=".java" size="100">
		</td>
		<td><input type="button" value="Compile & Test" onclick="Validate()"/></td>
		<td><input type="button" value="Final Submit" style="margin-right: 6em; float: right;" onclick="finalSubmission()"></td>
	</tr>
	</table>
	</form>
	</div>

</div>


</body>
</html>