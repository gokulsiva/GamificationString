package com.wipro.gamificationstring.bean;

import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author Gokul
 *
 */


@Entity
@Table(name = "Users")
public class UserBean {
	
	@Id
	@GeneratedValue
	private int userId;
	@Column(nullable = false)
	private String name;
	@Column(unique=true, nullable=false)
	private String email;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String accountType;
	private HashSet<Integer> solvedQuestions = new HashSet<Integer>();
	
	
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public int getUserId() {
		return userId;
	}
	public HashSet<Integer> getSolvedQuestions() {
		return solvedQuestions;
	}
	public void setSolvedQuestions(HashSet<Integer> solvedQuestions) {
		this.solvedQuestions = solvedQuestions;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}	

}
