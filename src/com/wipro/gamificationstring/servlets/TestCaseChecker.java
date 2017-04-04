package com.wipro.gamificationstring.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wipro.gamificationstring.bean.QuestionBean;
import com.wipro.gamificationstring.bean.UserBean;
import com.wipro.gamificationstring.service.QuestionAdmin;
import com.wipro.gamificationstring.service.UserAdmin;
import com.wipro.gamificationstring.util.ProcessExecutor;

/**
 * Servlet implementation class TestCaseChecker
 */
@WebServlet("/TestCaseChecker")
public class TestCaseChecker extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String filePath;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestCaseChecker() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init( ){
        // Get the file location where it would be stored.
        filePath = getServletContext().getInitParameter("file-upload"); 
     }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getSession().getAttribute("GamificationStringUserEmail") == null){
			response.sendRedirect("index.jsp?message=Please LogIn.");
			return;
		}
		
		Integer id = new Integer(request.getParameter("id"));
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		if(id == 0 || id == null){
			writer.write("Invalid page.");
			return;
		}
		QuestionBean question = QuestionAdmin.getQuestion(id);
		
		  String user = (String) request.getSession().getAttribute("GamificationStringUserEmail");
		  String dir = filePath+user+System.getProperty("file.separator");
	      String userCodeFilename = dir+"GamificationString.java";
	      String mainCodeFilename = dir+"MainClass.java";
	      
	    HashMap<String, String> userCompileOutput = new HashMap<String, String>();
	    HashMap<String, String> mainCompileOutput = new HashMap<String, String>();
		HashMap<String, String> test1 = new HashMap<String, String>();
	    HashMap<String, String> test2 = new HashMap<String, String>();
	    HashMap<String, String> test3 = new HashMap<String, String>();
	    
	    try {
	    	Arrays.stream(new File(dir).listFiles((f, p) -> p.endsWith(".class"))).forEach(File::delete);
	    	userCompileOutput = ProcessExecutor.runProcess("javac -cp "+dir+" -d "+dir+" "+userCodeFilename);
	  		mainCompileOutput = ProcessExecutor.runProcess("javac -cp "+dir+" -d "+dir+" "+mainCodeFilename);
		} catch (Exception e) {
			e.printStackTrace();
			writer.write("Unable to compile and run your code.\nPlease click compile and test before submit.");
	    	return;
		}
	    
	    if(!( userCompileOutput.get("error").equals("") || mainCompileOutput.get("error").equals("")) ) {
	    	writer.write("Unable to compile and run your code.\nPlease click compile and test before submit.");
	    	return;
	    }
		
		try {
			test1 = ProcessExecutor.runProcess("java -cp "+dir+" MainClass "+question.getTestCase_1());
			test2 = ProcessExecutor.runProcess("java -cp "+dir+" MainClass "+question.getTestCase_2());
			test3 = ProcessExecutor.runProcess("java -cp "+dir+" MainClass "+question.getTestCase_3());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		writer.write("Background testcase checking started....\n");
		System.out.println(test1.get("error"));
		System.out.println(test2.get("error"));
		System.out.println(test3.get("error"));
		if(test1.get("error").equals("") && test2.get("error").equals("") && test2.get("error").equals("")) {
			
			if (test1.get("output").equals(question.getExpected_1())) {
				writer.write("TestCase 1 passed.\n");
				if (test2.get("output").equals(question.getExpected_2())) {
					writer.write("TestCase 2 passed.\n");
					if (test3.get("output").equals(question.getExpected_3())) {
						writer.write("TestCase 3 passed.\n");
						writer.write("All testcases passed.\n");
						UserBean userBean = UserAdmin.getUser((String) request.getSession().getAttribute("GamificationStringUserEmail"));
						userBean.getSolvedQuestions().add(question.getQuestionId());
						UserAdmin.updateUser(userBean);
					} else {
						writer.write("TestCase 3 failed.\n");
					}
				} else {
					writer.write("TestCase 2 failed.\n");
				}
			} else {
				writer.write("TestCase 1 failed.\n");
			}
			
		} else {
			writer.write("Please recompile or upload your code.\n");
			writer.write("Background testcase checking failed.\n");			
			return;
		}
	}

}
