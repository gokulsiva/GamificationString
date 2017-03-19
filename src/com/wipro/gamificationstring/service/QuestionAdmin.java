package com.wipro.gamificationstring.service;

import java.util.List;

import com.wipro.gamificationstring.bean.QuestionBean;
import com.wipro.gamificationstring.dao.QuestionDAO;

public class QuestionAdmin {
	
	
	public static List<QuestionBean> getAllQuestions(){
		return QuestionDAO.questionsList();
	}
	
	public static QuestionBean getQuestion(int id) {
		return QuestionDAO.question(id);
	}
	
	
	public static void main(String[] args) {
		List<QuestionBean> list = getAllQuestions();
		for( QuestionBean question : list){
			System.out.println(question.getQuestionId()+"\t"+question.getQuestionName());
		}
	}

}
