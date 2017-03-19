package com.wipro.gamificationstring.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wipro.gamificationstring.bean.QuestionBean;
import com.wipro.gamificationstring.service.QuestionAdmin;
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
		
		Integer id = new Integer(request.getParameter("id"));
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		if(id == 0 || id == null){
			writer.write("Invalid page.");
			return;
		}
		QuestionBean question = QuestionAdmin.getQuestion(id);
		
		String dir = filePath+"user"+System.getProperty("file.separator");
		
		HashMap<String, String> test1 = new HashMap<String, String>();
	    HashMap<String, String> test2 = new HashMap<String, String>();
	    HashMap<String, String> test3 = new HashMap<String, String>();
		
		try {
			test1 = ProcessExecutor.runProcess("java -cp "+dir+" MainClass "+question.getTestCase_1());
			test2 = ProcessExecutor.runProcess("java -cp "+dir+" MainClass "+question.getTestCase_2());
			test3 = ProcessExecutor.runProcess("java -cp "+dir+" MainClass "+question.getTestCase_3());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		writer.write("Background testcase checking started....\n");
		if(test1.get("error").equals("") && test2.get("error").equals("") && test2.get("error").equals("")) {
			
			if (test1.get("output").equals(question.getExpected_1())) {
				writer.write("TestCase 1 passed.\n");
				if (test2.get("output").equals(question.getExpected_2())) {
					writer.write("TestCase 2 passed.\n");
					if (test3.get("output").equals(question.getExpected_3())) {
						writer.write("TestCase 3 passed.\n");
						writer.write("All testcases passed.\n");
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
