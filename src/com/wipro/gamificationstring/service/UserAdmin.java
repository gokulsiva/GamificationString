package com.wipro.gamificationstring.service;

import java.util.List;

import com.wipro.gamificationstring.bean.UserBean;
import com.wipro.gamificationstring.dao.UserDAO;


/**
 * @author Sharmila
 *
 */


public class UserAdmin {
	
	public static String createUser(UserBean user) {
		String status = "";
		if (!(user.getName().equals("") || user.getEmail().equals("") || user.getPassword().equals("") )) {
			user.setAccountType("user");
			status = UserDAO.create(user);
		} else {
			status = "Invalid fields please fill all fields";
		}
		return status;
	}
	
	public static String updateUser(UserBean user) {
		String status = "";
		if (!(user.getUserId() == 0 || user.getName().equals("") || user.getPassword().equals(""))) {
			status = UserDAO.update(user);
		} else {
			status = "Invalid object";
		}
		return status;
	}
	
	public static List<UserBean> getAllUsers(){
		return UserDAO.readAll();
	}
	
	public static UserBean getUser(int id) {
		return UserDAO.read(id);
	}
	
	public static UserBean getUser(String email) {
		return UserDAO.read(email);
	}
	
	public static String deleteUser(UserBean user) {
		return UserDAO.delete(user);
	}
	
	
	public static void main(String[] args) {
		
		
		System.out.println(getUser("email"));
		
	}


}
