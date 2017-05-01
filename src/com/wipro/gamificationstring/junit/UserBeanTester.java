package com.wipro.gamificationstring.junit;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.wipro.gamificationstring.bean.UserBean;


/**
 * @author Gokul
 *
 */


public class UserBeanTester {
	@Test
	public void test() {
	}

	public void test_userbean(){
		UserBean userbean=new UserBean();
		userbean.setAccountType("user");
		userbean.setEmail("abc@gmail.com");
		userbean.setName("newuser");
		userbean.setPassword("****");
		userbean.setUserId(1);
		HashSet<Integer> st=new HashSet<Integer>();
		st.add(1);
		st.add(2);
		assertEquals(st, userbean.getSolvedQuestions());
		assertEquals("user",userbean.getAccountType());
		assertEquals("abc@gmail.com", userbean.getEmail());
		assertEquals("newuser", userbean.getName());
		assertEquals("****", userbean.getPassword());
		assertEquals(1, userbean.getUserId());
	}
}
