package com.wipro.gamificationstring.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.wipro.gamificationstring.bean.QuestionBean;
import com.wipro.gamificationstring.util.DBUtil;

public class QuestionDAO {
	
	
	public static List<QuestionBean> questionsList(){
		Session session = DBUtil.getSession();
		TypedQuery<QuestionBean> query = session.createQuery("from QuestionBean", QuestionBean.class);
		List<QuestionBean> questions = query.getResultList();
		return questions;
	}
	
	public static QuestionBean question(int id) {
		Session session = DBUtil.getSession();
		QuestionBean questionBean = session.get(QuestionBean.class, id);
		session.close();
		return questionBean;
	}
	
	public static void main(String[] args) {
		List<QuestionBean> list = questionsList();
		for( QuestionBean question : list){
			System.out.println(question.getQuestionId()+"\t"+question.getQuestionName());
		}
		System.out.println("Test using id :");
		QuestionBean bean = question(23);
		System.out.println(bean.getQuestionName());
	}

}
