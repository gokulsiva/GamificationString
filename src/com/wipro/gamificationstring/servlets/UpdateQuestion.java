package com.wipro.gamificationstring.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wipro.gamificationstring.bean.QuestionBean;
import com.wipro.gamificationstring.service.QuestionAdmin;

/**
 * Servlet implementation class UpdateQuestion
 */
@WebServlet("/UpdateQuestion")
public class UpdateQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateQuestion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		QuestionBean question = new QuestionBean();
		question.setQuestionId(new Integer(request.getParameter("id")));
		question.setQuestionName(request.getParameter("name").trim());
		question.setExplanation(request.getParameter("explanation").trim());
		question.setTestCase_1(request.getParameter("testcase1").trim());
		question.setExpected_1(request.getParameter("expected1").trim());
		question.setTestCase_2(request.getParameter("testcase2").trim());
		question.setExpected_2(request.getParameter("expected2").trim());
		question.setTestCase_3(request.getParameter("testcase3").trim());
		question.setExpected_3(request.getParameter("expected3").trim());
		String status = QuestionAdmin.updateQuestion(question);
		PrintWriter writer = response.getWriter();
		writer.write(status);
		writer.flush();
		writer.close();
	}

}
