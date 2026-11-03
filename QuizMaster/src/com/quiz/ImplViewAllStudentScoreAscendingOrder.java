package com.quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.quiz.interfaces.ViewAllStudentScoreAscendingOrder;

/*User Story 3.2: View All Student Scores */

public class ImplViewAllStudentScoreAscendingOrder implements ViewAllStudentScoreAscendingOrder{

	@Override
	public void getStudentScoreByAscendingOrder() throws Exception {
		System.out.println("Enter the admin username :");
		Scanner scanner=new Scanner(System.in);
		String uname=scanner.next();
		System.out.println("Enter the password :");
		String pword=scanner.next();
		AdminCredentials adminCredentials=new AdminCredentials();
		boolean varify=adminCredentials.getAdminVerification(uname, pword);
		if(varify==true) {
			displayResultByAsc();	
		}
		else {
			boolean isNotStudent=VerificationOfUserLogin.getUserLoginVerification(uname, pword);
			if(isNotStudent==true) {
				System.out.println("Access Denied for students...");
				System.out.println("\nPlease enter \"admin\" credentials :");
				getStudentScoreByAscendingOrder();
			}
			else {
				getStudentScoreByAscendingOrder();
			}
		}
		
	}

	public void displayResultByAsc() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			DBConnection cd = new DBConnection();
			con=cd.getConnection();
			System.out.println("\n"+"-------Student score by Ascending order-------"+"\n");
			ps = con.prepareStatement("select student.first_name,student.last_name,student_result.score from student inner join student_result on student.username= student_result.username ORDER BY student_result.score ASC;");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String firstname = rs.getString("first_name");
				System.out.println("Firstname - " + firstname);
				String lastname = rs.getString("last_name");
				System.out.println("Lastname - " + lastname);
				String score = rs.getString("score");
				System.out.println("Score - " + score+"\n");
			}
		} catch (Exception e) {
			e.printStackTrace();	
		}
		finally {
			con.close();
			ps.close();

		}
	}
}
