package com.wipro.gamificationstring.dao;

import java.util.List;

import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.wipro.gamificationstring.bean.QuestionBean;
import com.wipro.gamificationstring.util.DBUtil;

public class QuestionDAO {
	
	public static String create(QuestionBean question) {
		String status = "";
		Session session = DBUtil.getSession();
		session.beginTransaction();
		try {
			
			session.save(question);
			session.getTransaction().commit();
			status = "Successfully created question.";
			
		} catch (PersistenceException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			if(e.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
				status = "Program name already exists try different name.";
		      } else {
		    	  status = "Problem saving question. Unable to save.";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			status = "Problem saving question. Unable to save.";
		} finally {
			DBUtil.closeSession(session);
		}
		
		return status;
	}
	
	
	public static String update(QuestionBean question) {
		String status = "";
		Session session = DBUtil.getSession();
		session.beginTransaction();
		try {
			
			session.update(question);
			session.getTransaction().commit();
			status = "Successfully updated question.";
			
		} catch (OptimisticLockException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			if(e.getCause() instanceof org.hibernate.StaleStateException) {
				status = "No such object to update";
		      } else {
		    	  status = "Problem saving question. Unable to save.";
			}
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			status = "Problem saving question. Unable to save.";
		} finally {
			DBUtil.closeSession(session);
		}
		
		return status;
	}
	
	
	public static QuestionBean read(int id) {
		Session session = DBUtil.getSession();
		session.beginTransaction();
		QuestionBean questionBean = session.get(QuestionBean.class, id);
		session.getTransaction().commit();
		DBUtil.closeSession(session);
		return questionBean;
	}
	
	
	public static List<QuestionBean> readAll(){
		Session session = DBUtil.getSession();
		session.beginTransaction();
		TypedQuery<QuestionBean> query = session.createQuery("from QuestionBean order by questionId asc", QuestionBean.class);
		List<QuestionBean> questions = query.getResultList();
		session.getTransaction().commit();
		DBUtil.closeSession(session);
		return questions;
	}
	
	public static String delete(QuestionBean question) {
		String status = "";
		Session session = DBUtil.getSession();
		session.beginTransaction();
		try {
			
			session.delete(question);
			session.getTransaction().commit();
			status = "Successfully deleted question.";
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			status = "Deletion failed";
		} finally {
			DBUtil.closeSession(session);
		}
		return status;
	}
	
	
	public static void main(String[] args) {
		QuestionBean bean = new QuestionBean();
		bean.setQuestionId(35);
		bean.setQuestionName("Demo question 2");
		bean.setExplanation("Simple explanation");
		bean.setTestCase_1("demo1");
		bean.setExpected_1("demo1");
		bean.setTestCase_2("demo2");
		bean.setExpected_2("demo2");
		bean.setTestCase_3("demo3");
		bean.setExpected_3("demo3");
		System.out.println(update(bean));
	}
}
