package com.quiz;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.quiz.interfaces.ViewTopScorer;

/*User Story 5.2: View Top Scorer */

public class ImplViewTopScorer implements ViewTopScorer{

	@Override
	public void getTopScorer() throws Exception {
		
		System.out.println("Enter the admin username :");
		Scanner scanner=new Scanner(System.in);
		String uname=scanner.next();
		System.out.println("Enter the password :");
		String pword=scanner.next();
		AdminCredentials adminCredentials=new AdminCredentials();
		boolean varify=adminCredentials.getAdminVerification(uname, pword);
		if(varify==true) {
			getMaximumScoreByAdmin() ;	
		}
		else {
			boolean isNotStudent=VerificationOfUserLogin.getUserLoginVerification(uname, pword);

			if(isNotStudent==true) {
				System.out.println("Access Denied for students...");
				System.out.println("\nPlease enter \"admin\" credentials :");
				getTopScorer();		
			}
			else {
				getTopScorer();
			}
		}
		
	}

	public void getMaximumScoreByAdmin() {

		final String sql = "Select student.first_name, student.last_name, student_result.score from student inner join student_result on student.username = student_result.username where student_result.score = (select max(score) from student_result)";
		//final String sql = "select from student_data.firstname,student_data.lastname,student_result.score from student_data inner join student_result on student_data.username= student_result.username WHERE student_result.score = (SELECT MAX(score)FROM student_result);";
		Connection connection = null;
		Statement stmt = null;
		
		try {

			DBConnection connectionDetails = new DBConnection();
			connection = connectionDetails.getConnection();
			stmt = connection.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
			String maxScore = rs.getString("score");
			String firstName =rs.getString("first_name");
			//System.out.println(firstName);
			String lastName =rs.getString("last_name");
			System.out.println("\n"+"The maximum score belongs to "+firstName+" "+lastName+ " is "+maxScore);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
}
