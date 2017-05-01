package com.wipro.gamificationstring.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import com.wipro.gamificationstring.bean.UserBean;
import com.wipro.gamificationstring.dao.UserDAO;
import com.wipro.gamificationstring.service.UserAdmin;


/**
 * @author Sharmila
 *
 */


public class UserAdminTester {

	UserAdmin user=new UserAdmin();
	/*
	@Test
	public void create()
	{
		UserBean userbean=new UserBean();
		userbean.setName("sharmi");
		userbean.setEmail("sharmi@gmail.com");
		userbean.setPassword("abcd");
		userbean.setAccountType("user");
		assertEquals("Successfully created user.",user.createUser(userbean));
	}
	*/
	/*
	@Test
	public void getUser()
	{
		assertEquals("sharmi",user.getUser(1).getName());
	}
	*/
	/*
	@Test
	public void update()
	{
	   UserDAO dao=new UserDAO();	
	   UserBean userbean=dao.read(1);
	   userbean.setName("sss");
	   assertEquals("Successfully updated user.",dao.update(userbean));
	}
	*/
	
	
	/*
	@Test
	public void getAllUser()
	{
		int m=user.getAllUsers().size();
		assertEquals(1,m);
	}
	*/
	/*
	@Test
	public void getUser()
	{
		assertEquals("sharmi@gmail.com",user.getUser(1).getEmail());
	}
	*/
	
    @Test
    public void deleteUser()
    {
    	UserBean bean=UserDAO.read(1);
    	assertEquals("Successfully deleted user.",user.deleteUser(bean));
    }
    
}
