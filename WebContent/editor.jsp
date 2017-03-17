<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<%@ page import="com.wipro.gamificationstring.util.ProgramStrings" %>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Editor</title>
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
           frm.submit();
        }
    	} else {
    	frm.enctype = 'application/x-www-form-urlencoded';
        frm.action = 'Compiler';
        frm.submit();
    	}
    }
</script>
<style type="text/css">

.wrap {
    width: 100%;
    overflow:auto;
}

.fleft {
    float:left; 
    width: 24.5%;
	background:lightblue;
    height: 400px;
}

.fcenter{
    float:left;
    width: 50%;
    background:lightgreen;
    height:400px;
    margin-left:0.25%;
}

.fright {
float: right;
    background:pink;
    height: 400px;
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

<div style="width: 100%; height: 100%;">

	<div id="explanation" style="width: 100%;">
		<h3>Explanation:</h3><br>
		<textarea spellcheck="false" readonly style="width: 100%;" rows="5"></textarea>
	</div>
	
	<div class="wrap">
	    <div class="fleft">
	    	<h3>Expected output:</h3><br><br>
	    	<textarea name="expected" spellcheck="false" form="code_form" readonly style="width: 98%;" rows="10">hi</textarea>
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
	<input id="useFile" type="checkbox" name="File" value="file"/>Use java file instead. &nbsp;
	<input id="FileUploader" type="file" name="file" accept=".java" size="100">
	<input type="button" value="Compile & Execute" onclick="Validate()"/>
	</form>
	</div>

</div>


</body>
</html>