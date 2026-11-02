package com.quiz;

public class Student {
	private int id;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String city;
	private String email;
	private String mobile;
	
	//Default constructor
	public Student() {
		
	}	
	//Parameterized constructor for Registration
	public Student(String firstName, String lastName, String userName, String password, String city, String email, String mobile) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.city = city;
		this.email = email;
		this.mobile = mobile;
	}	
	//Parameterized constructor for Login
	public Student(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	//Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
}
