package com.wipro.gamificationstring.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class QuestionBean {
	
	@Id
	@GeneratedValue
	private int questionId;
	@Column(unique = true)
	private String questionName;
	@Lob
	private String explanation;
	private String testCase_1;
	private String testCase_2;
	private String testCase_3;
	private String expected_1;
	private String expected_2;
	private String expected_3;
	
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public String getQuestionName() {
		return questionName;
	}
	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	public String getTestCase_1() {
		return testCase_1;
	}
	public void setTestCase_1(String testCase_1) {
		this.testCase_1 = testCase_1;
	}
	public String getTestCase_2() {
		return testCase_2;
	}
	public void setTestCase_2(String testCase_2) {
		this.testCase_2 = testCase_2;
	}
	public String getTestCase_3() {
		return testCase_3;
	}
	public void setTestCase_3(String testCase_3) {
		this.testCase_3 = testCase_3;
	}
	public String getExpected_1() {
		return expected_1;
	}
	public void setExpected_1(String expected_1) {
		this.expected_1 = expected_1;
	}
	public String getExpected_2() {
		return expected_2;
	}
	public void setExpected_2(String expected_2) {
		this.expected_2 = expected_2;
	}
	public String getExpected_3() {
		return expected_3;
	}
	public void setExpected_3(String expected_3) {
		this.expected_3 = expected_3;
	}
	
	
	

}
