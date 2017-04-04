package com.wipro.gamificationstring.util;

import com.wipro.gamificationstring.bean.UserBean;
import com.wipro.gamificationstring.dao.UserDAO;

public class AdminUserCreator {

	public static void main(String[] args) {
		
		
		UserBean user = new UserBean();
		user.setName("admin");
		user.setEmail("admin");
		user.setPassword("admin");
		user.setAccountType("admin");
		
		System.out.println(UserDAO.create(user));
		
	}
	
	
}
