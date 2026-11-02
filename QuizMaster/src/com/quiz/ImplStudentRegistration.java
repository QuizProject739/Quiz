package com.quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.quiz.interfaces.StudentRegistration;

/*User Story 1.1: Student Registration
 * User Story 4.1: Validate User Inputs 
 * User Story 4.2: Prevent Duplicate Usernames */

public class ImplStudentRegistration implements StudentRegistration {

	@Override
	public Student getStudentInput(Scanner scanner) {
		System.out.println("Enter First Name:");
		String firstName = scanner.nextLine();
		
		System.out.println("Enter Last Name:");
		String lastName = scanner.nextLine();
		
		System.out.println("Enter username:");
		String username = scanner.nextLine();
		
		System.out.println("Enter Password:");
		String password = scanner.nextLine();
		
		System.out.println("Enter City:");
		String city = scanner.nextLine();
		
		System.out.println("Enter Email ID:");
		String email = scanner.nextLine();
		
		System.out.println("Enter Mobile Number:");
		String mobile = scanner.nextLine();
		
		return new Student(firstName, lastName, username, password, city, email, mobile);
	}

	@Override
	public boolean registerStudent(Student s) throws SQLException {
		String checkQuery = "SELECT id FROM Student WHERE username = ?";
		String insertQuery = ("INSERT INTO Student(first_name, last_name, username, password, city, email, mobile)VALUES(?, ?, ?, ?, ?, ?, ?)");
		
		DBConnection dbConnection = new DBConnection();
		
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement checkPs = null;
		ResultSet rs = null;
		try {
		con = dbConnection.getConnection();
		//check duplicate username
		checkPs =con.prepareStatement(checkQuery);
		checkPs.setString(1, s.getUserName());
		rs = checkPs.executeQuery();
		if(rs.next()) {
			System.out.println("Username already exists!");
			return false;
		}
		//Insert Student details
		ps =con.prepareStatement(insertQuery);
		
		ps.setString(1, s.getFirstName());
		ps.setString(2, s.getLastName());
		ps.setString(3, s.getUserName());
		ps.setString(4, s.getPassword());
		ps.setString(5, s.getCity());
		ps.setString(6, s.getEmail());
		ps.setString(7, s.getMobile());
		
		int rows = ps.executeUpdate();
		if(rows > 0) {
			return true;
			}
		}
		catch(Exception e) {
			e.printStackTrace();	
		} finally {
			if(rs != null) {
				rs.close();
			}
			if(checkPs != null) {
				checkPs.close();
			}
			if(ps != null) {
				ps.close();
			}
			if(con != null) {
				con.close();
			}
		}
		return false;	
	}

}


