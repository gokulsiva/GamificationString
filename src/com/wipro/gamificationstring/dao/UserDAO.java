package com.wipro.gamificationstring.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.wipro.gamificationstring.bean.UserBean;
import com.wipro.gamificationstring.util.DBUtil;


/**
 * @author Surya
 *
 */


public class UserDAO {

	public static String create(UserBean user) {
		String status = "";
		Session session = DBUtil.getSession();
		session.beginTransaction();
		try {
			
			session.save(user);
			session.getTransaction().commit();
			status = "Successfully created user.";
			
		} catch (PersistenceException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			if(e.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
				status = "User name already exists try different name.";
		      } else {
		    	  status = "Problem saving user. Unable to save.";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			status = "Problem saving user. Unable to save.";
		} finally {
			DBUtil.closeSession(session);
		}
		
		return status;
	}
	
	
	public static String update(UserBean user) {
		String status = "";
		Session session = DBUtil.getSession();
		session.beginTransaction();
		try {
			
			session.update(user);
			session.getTransaction().commit();
			status = "Successfully updated user.";
			
		} catch (OptimisticLockException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			if(e.getCause() instanceof org.hibernate.StaleStateException) {
				status = "No such object to update";
		      } else {
		    	  status = "Problem saving user. Unable to save.";
			}
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			status = "Problem saving user. Unable to save.";
		} finally {
			DBUtil.closeSession(session);
		}
		
		return status;
	}
	
	
	public static UserBean read(int id) {
		Session session = DBUtil.getSession();
		session.beginTransaction();
		UserBean userBean = session.get(UserBean.class, id);
		session.getTransaction().commit();
		DBUtil.closeSession(session);
		return userBean;
	}
	
	
	public static UserBean read(String email){
		Session session = DBUtil.getSession();
		UserBean userBean = new UserBean();
		session.beginTransaction();
		try {
			
			TypedQuery<UserBean> query = session.createQuery("from UserBean where email = :email", UserBean.class);
			query.setParameter("email", email);
			userBean = query.getSingleResult();
			
		} catch (NoResultException e) {
			userBean = null;
		}
		session.getTransaction().commit();
		DBUtil.closeSession(session);
		return userBean;
	}
	
	
	public static List<UserBean> readAll(){
		Session session = DBUtil.getSession();
		session.beginTransaction();
		TypedQuery<UserBean> query = session.createQuery("from UserBean order by userId asc", UserBean.class);
		List<UserBean> users = query.getResultList();
		session.getTransaction().commit();
		DBUtil.closeSession(session);
		return users;
	}
	
	public static String delete(UserBean user) {
		String status = "";
		Session session = DBUtil.getSession();
		session.beginTransaction();
		try {
			
			session.delete(user);
			session.getTransaction().commit();
			status = "Successfully deleted user.";
			
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
		UserBean bean = read("admins");
		System.out.println(bean);
	}
	
}
