package com.wipro.gamificationstring.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import com.wipro.gamificationstring.bean.QuestionBean;
import com.wipro.gamificationstring.dao.QuestionDAO;


/**
 * @author Sowmiya
 *
 */


public class QuestionDaoTester {
	QuestionDAO questiondao=new QuestionDAO();
	
	
	
	/*
	@Test
	public void test_create(){
		
		QuestionBean questionbean=new QuestionBean();
		
		questionbean.setQuestionName("reversing a string");
		questionbean.setExpected_1("olleh");
		questionbean.setExpected_2("OLLEH");
		questionbean.setExpected_3("iah");
		questionbean.setExplanation("reversing a string");
		questionbean.setTestCase_1("hello");
		questionbean.setTestCase_2("HELLO");
		questionbean.setTestCase_3("hai");
		assertEquals("Successfully created question.",questiondao.create(questionbean));
	}
	*/
	
	/*
	@Test
	public void test_read() {
		QuestionBean bean = questiondao.read(1);
		assertEquals("Successfully read  the question","reversing a string",bean.getQuestionName());
	}
	*/
	
	/*
	@Test
	 public void test_readAll(){
		int n = questiondao.readAll().size();
		assertEquals("successfully read all the questions",1,n);
	}
	*/
	
	
	/*
	@Test
	public void test_update(){
		QuestionBean bean = questiondao.read(1);
		bean.setQuestionName("reversing strings");
		assertEquals("Successfully updated question.",questiondao.update(bean));
	}
	
	*/
	
	
/*
	@Test
	public void test_delete(){
		QuestionBean bean = questiondao.read(1);
		assertEquals("Successfully deleted question.",questiondao.delete(bean));
		
	}
	
	*/


}

