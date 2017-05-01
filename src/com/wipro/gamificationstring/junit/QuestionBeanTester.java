package com.wipro.gamificationstring.junit;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.wipro.gamificationstring.bean.QuestionBean;


/**
 * @author Gokul
 *
 */


public class QuestionBeanTester {

	@Test
	public void test() {
	}
	@Test
	public void test_questionBean(){
		QuestionBean questionbean=new QuestionBean();
		questionbean.setQuestionId(1);
		questionbean.setQuestionName("ToLowerCase");
		questionbean.setExpected_1("hello");
		questionbean.setExpected_2("testing");
		questionbean.setExpected_3("junit");
		questionbean.setTestCase_1("hello");
		questionbean.setTestCase_2("testing");
		questionbean.setTestCase_3("junit");
		questionbean.setExplanation("To change the given string to lowercase");
		assertEquals(1, questionbean.getQuestionId());
		assertEquals("ToLowerCase", questionbean.getQuestionName());
		assertEquals("hello", questionbean.getExpected_1());
		assertEquals("testing", questionbean.getExpected_2());
		assertEquals("junit", questionbean.getExpected_3());
		assertEquals("hello", questionbean.getTestCase_1());
		assertEquals("testing", questionbean.getTestCase_2());
		assertEquals("junit", questionbean.getTestCase_3());
		assertEquals("To change the given string to lowercase",questionbean.getExplanation());
		
		
	}

	
}
