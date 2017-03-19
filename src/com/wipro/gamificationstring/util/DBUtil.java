package com.wipro.gamificationstring.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DBUtil {
	
	private static SessionFactory sessionFactory;

	private static SessionFactory getSessionFactory() {
		if(sessionFactory == null)
		{
			sessionFactory = new Configuration().configure().buildSessionFactory();
		} 
		return sessionFactory;
	}
	
	
	public static Session getSession(){
		Session session = getSessionFactory().openSession();
		return session;
	}
	
	
	public static void closeSession(Session session){
		session.close();		
	}
	
	

}
