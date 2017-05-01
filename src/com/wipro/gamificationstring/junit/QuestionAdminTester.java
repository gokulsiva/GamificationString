package com.wipro.gamificationstring.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import com.wipro.gamificationstring.bean.QuestionBean;
import com.wipro.gamificationstring.dao.QuestionDAO;
import com.wipro.gamificationstring.dao.UserDAO;
import com.wipro.gamificationstring.service.QuestionAdmin;


/**
 * @author Revathi
 *
 */


public class QuestionAdminTester {

		QuestionAdmin qs=new QuestionAdmin();	
	
	
	/*@Test
	public void createtest()
	{
		
		QuestionBean ques=new QuestionBean();
		ques.setQuestionName("Demo question 1");
		ques.setExplanation("demo explanation");
		ques.setTestCase_1("demo");
		ques.setTestCase_2("demo");
		ques.setTestCase_3("demo");
		ques.setExpected_1("demo");
		ques.setExpected_2("demo");
		ques.setExpected_3("demo");
		assertEquals("Successfully created question.",qs.createQuestion(ques));
		
	}*/

	/*@Test
	public void updatetest()
	
	{   QuestionDAO dao=new QuestionDAO();
		QuestionBean bean=new QuestionBean();
		bean=dao.read(1);
		QuestionAdmin admin=new QuestionAdmin();
		bean.setQuestionName("new");
		assertEquals("Successfully updated question.",admin.updateQuestion(bean));
		
	}*/
	/*@Test
	public void getquestiontest()
	{
		

		assertEquals("new",QuestionAdmin.getQuestion(1).getQuestionName());
	}*/
	
	/*@Test
	public void getallquestiontest()
	{
		int n=qs.getAllQuestions().size();
		assertEquals(1,n);
	}*/
	
	@Test
	public void deleteQuestion()
	{
		QuestionBean bean=QuestionDAO.read(1);
		assertEquals("Successfully deleted question.",qs.deleteQuestion(bean));
	}
												

}
