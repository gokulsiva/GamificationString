package com.wipro.gamificationstring.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import com.wipro.gamificationstring.bean.UserBean;
import com.wipro.gamificationstring.dao.UserDAO;


/**
 * @author Surya
 *
 */


public class UserDaoTester {
	
	UserDAO userdao = new UserDAO();
	
	/*
	@Test
	public void test_create(){
		UserBean userbean = new UserBean();
		userbean.setUserId(1);
		userbean.setName("abcd");
		userbean.setEmail("abcd@gmail.com");
		userbean.setPassword("aaa");
		userbean.setAccountType("user");
		assertEquals("Successfully created user.",userdao.create(userbean));
	}
	*/
	

	@Test
	public void test_read(){
		UserBean bean = userdao.read(1);
		assertEquals("abcd",bean.getName());
	}

	
	/*
	@Test
	public void test_readAll(){
		int k = userdao.readAll().size();
		assertEquals(1,k);
	}
	*/
	
/*
	@Test
	public void test_update(){
		UserBean bean = userdao.read(1);
		bean.setName("ab");
		assertEquals("Successfully updated user.", userdao.update(bean));
	}
	
	*/
	
	/*
	@Test
	public void test_delete(){
		UserBean bean = userdao.read(1);
		assertEquals("Successfully deleted user.", userdao.delete(bean));
	}
	*/
	
	
}
