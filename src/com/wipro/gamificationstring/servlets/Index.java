package com.wipro.gamificationstring.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wipro.gamificationstring.bean.QuestionBean;
import com.wipro.gamificationstring.service.QuestionAdmin;


/**
 * Servlet implementation class Index
 */
@WebServlet("/Index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Index() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.write("<h3>List of Questions :</h3>");
		List<QuestionBean> questions = QuestionAdmin.getAllQuestions();
		writer.write("<ul>");
		for(QuestionBean question : questions){
			writer.write("<li><a href=\"editor.jsp?id="+question.getQuestionId()+"\">"+question.getQuestionName()+"</a>");
		}
		writer.flush();
		writer.close();
		
	}

}
