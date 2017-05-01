package com.wipro.gamificationstring.service;

import java.util.List;

import com.wipro.gamificationstring.bean.QuestionBean;
import com.wipro.gamificationstring.dao.QuestionDAO;


/**
 * @author Revathi
 *
 */



public class QuestionAdmin {
	
	public static String createQuestion(QuestionBean question) {
		String status = "";
		if (!(question.getQuestionName().equals("") || question.getExpected_1().equals("") || question.getExpected_2().equals("") || question.getExpected_3().equals("") || question.getExplanation().equals("") || question.getTestCase_1().equals("") || question.getTestCase_2().equals("") || question.getTestCase_3().equals(""))) {
			status = QuestionDAO.create(question);
		} else {
			status = "Invalid fields please fill all fields";
		}
		return status;
	}
	
	public static String updateQuestion(QuestionBean question) {
		String status = "";
		if (!(question.getQuestionId() == 0 || question.getQuestionName().equals("") || question.getExpected_1().equals("") || question.getExpected_2().equals("") || question.getExpected_3().equals("") || question.getExplanation().equals("") || question.getTestCase_1().equals("") || question.getTestCase_2().equals("") || question.getTestCase_3().equals(""))) {
			status = QuestionDAO.update(question);
		} else {
			status = "Invalid object";
		}
		return status;
	}
	
	public static List<QuestionBean> getAllQuestions(){
		return QuestionDAO.readAll();
	}
	
	public static QuestionBean getQuestion(int id) {
		return QuestionDAO.read(id);
	}
	
	public static String deleteQuestion(QuestionBean question) {
		return QuestionDAO.delete(question);
	}
	
	
	public static void main(String[] args) {
		QuestionBean question = new QuestionBean();
		question.setQuestionId(36);
		question.setQuestionName("Demo question 1");
		question.setExplanation("demo explanation");
		question.setTestCase_1("demo");
		question.setTestCase_2("demo");
		question.setTestCase_3("demo");
		question.setExpected_1("demo");
		question.setExpected_2("demo");
		question.setExpected_3("demo");
		System.out.println(deleteQuestion(question));
	}

}
