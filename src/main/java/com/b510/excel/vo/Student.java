/**
 * 
 */
package com.b510.excel.vo;

/**
 * Student
 * 
 * @author Hongten
 * @created 2014-5-18
 */
public class Student {
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 学号
	 */
	private String no;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 学院
	 */
	private int age;
	/**
	 * 成绩
	 */
	private float score;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

}
