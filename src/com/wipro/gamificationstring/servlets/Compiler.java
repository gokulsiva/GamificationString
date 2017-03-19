package com.wipro.gamificationstring.servlets;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wipro.gamificationstring.bean.QuestionBean;
import com.wipro.gamificationstring.service.QuestionAdmin;
import com.wipro.gamificationstring.util.ProcessExecutor;
import com.wipro.gamificationstring.util.ProgramStrings;


/**
 * Servlet implementation class Compiler
 */
@WebServlet("/Compiler")
public class Compiler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String filePath;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Compiler() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init( ){
        // Get the file location where it would be stored.
        filePath = getServletContext().getInitParameter("file-upload"); 
     }
    

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		  response.setContentType("text/html");
	      java.io.PrintWriter out = response.getWriter( );
	      QuestionBean question = QuestionAdmin.getQuestion(new Integer(request.getParameter("questionId")));
	
		  String dir = filePath+"user"+System.getProperty("file.separator");
	      String userCodeFilename = dir+"GamificationString.java";
	      String mainCodeFilename = dir+"MainClass.java";
	      String testCase = question.getTestCase_1();
	      String result = "";
	      
	      HashMap<String, String> userCompileOutput = new HashMap<String, String>();
	      HashMap<String, String> mainCompileOutput = new HashMap<String, String>();
	      HashMap<String, String> executedOutput = new HashMap<String, String>();
		
		
	      try {
	    	  File userFile = new File(userCodeFilename);
	    	  String userProgram = request.getParameter("code");
	    	  FileWriter fileWriter = new FileWriter(userFile);
	    	  fileWriter.write(userProgram);
	    	  fileWriter.flush();
	    	  fileWriter.close();
	    	  
	    	  File mainFile = new File(mainCodeFilename);
	          String mainProgram = ProgramStrings.getMainProgram();
	          FileWriter writer = new FileWriter(mainFile);
	          writer.write(mainProgram);
	          writer.flush();
	          writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	      
	      
	      try {
	  		userCompileOutput = ProcessExecutor.runProcess("javac -cp "+dir+" -d "+dir+" "+userCodeFilename);
	  		mainCompileOutput = ProcessExecutor.runProcess("javac -cp "+dir+" -d "+dir+" "+mainCodeFilename);
	  		if(userCompileOutput.get("exitValue").equals("0"))
	  		{
	  			if(mainCompileOutput.get("exitValue").equals("0")){
	  				executedOutput = ProcessExecutor.runProcess("java -cp "+dir+" MainClass "+testCase);
	  				if (executedOutput.get("exitValue").equals("0")) {
	  					result = executedOutput.get("output").trim();
	  				} else {
	  					result = executedOutput.get("error");
	  				}
	  			} else {
	  				result = mainCompileOutput.get("error");
	  			}
	  		} else {
	  			result = userCompileOutput.get("error");
	  		}
	  			
	  	} catch (Exception e) {
	  		e.printStackTrace();
	  	}
	        result = result.substring(result.lastIndexOf('\\')+1);
	        out.write(result);
	        out.flush();
	        out.close();
	        
	     
	      
	}

}
