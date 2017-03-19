package com.wipro.beantesters;

import org.hibernate.Session;

import com.wipro.gamificationstring.bean.QuestionBean;
import com.wipro.gamificationstring.util.DBUtil;

public class QuestionBeanTester {
	
	public static void main(String[] args) {
		
		QuestionBean question1 = new QuestionBean();
		question1.setQuestionName("To UpperCase Question");
		question1.setExplanation("You are provided with \"hi\" string you have to change it as \"HI\" and return it from the static method. If you wish to upload it as code you have to copy the class and method structure in your code file.");
		question1.setTestCase_1("hi");
		question1.setTestCase_2("hello");
		question1.setTestCase_3("good");
		question1.setExpected_1("HI");
		question1.setExpected_2("HELLO");
		question1.setExpected_3("GOOD");
		
		Session session = DBUtil.getSession();
		session.save(question1);
		DBUtil.closeSession(session);
		
	}

}
