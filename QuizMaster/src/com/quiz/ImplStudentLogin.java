package com.quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.quiz.interfaces.StudentLogin;

/*User Story 1.2: Student Login */

public class ImplStudentLogin implements StudentLogin{

	@Override
	public boolean login(Scanner scanner) throws SQLException {
System.out.println("\n---- Student login---");
		
		System.out.println("Enter Username:");
		String loginUsername = scanner.nextLine();
		
		System.out.println("Enter Password:");
		String loginPassword = scanner.nextLine();
		
		boolean loggedIn = loginStudent(loginUsername, loginPassword);
		if(loggedIn) {
			System.out.println("Login successful!");
			
			System.out.println("Do you wish to attempt the Quiz?(yes/no)");
			String attempt = scanner.nextLine().trim();
			
			if(attempt.equalsIgnoreCase("yes") || attempt.equalsIgnoreCase("y")) {
				System.out.println("Starting the quiz...Good luck!");
				return true;
			} else {
				System.out.println("Okay, thank you");
				return false;
			}
		}else {
			System.out.println("Login Failed! Invalid username or password.");
			return false;
		}
	}

	@Override
	public boolean loginStudent(String username, String password) throws SQLException {
String loginQuery = "SELECT id FROM Student WHERE username = ? AND password = ?";
		
		DBConnection dbConnection = new DBConnection();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
		con= dbConnection.getConnection();
		
		ps = con.prepareStatement(loginQuery);
		
		ps.setString(1, username);
		ps.setString(2, password);
		
		rs = ps.executeQuery();
		if(rs.next()) {
			return true;
		}else {
			return false;
		}
		}finally {
			if( rs != null) {
				rs.close();
			}
			if(ps != null) {
				ps.close();
			}
			if(con != null) {
				con.close();
			}
			
		}
	}
	
}



