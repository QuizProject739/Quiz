package com.quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.quiz.interfaces.DeleteQuestionByAdmin;

/*User Story 6.3: Delete a Question */

public class ImplDeleteQuestionByAdmin implements DeleteQuestionByAdmin{

	@Override
	public void setDeleteQuestionByAdmin() throws Exception {
		System.out.println("Enter the admin username :");
		Scanner scanner=new Scanner(System.in);
		String uname=scanner.next();
		System.out.println("Enter the password :");
		String pword=scanner.next();
		AdminCredentials adminCredentials=new AdminCredentials();
		boolean varify=adminCredentials.getAdminVerification(uname, pword);
		if(varify==true) {
			deleteQuestion();	
		}
		else {
			boolean isNotStudent=VerificationOfUserLogin.getUserLoginVerification(uname, pword);
			if(isNotStudent==true) {
				System.out.println("Access Denied for students...");
				System.out.println("\nPlease enter \"admin\" credentials :");
				setDeleteQuestionByAdmin();
			}
			else {
				setDeleteQuestionByAdmin();
			}
		}
		
	}
	
	public void deleteQuestion() {

		Connection connection = null;
		PreparedStatement ps = null;

		try {
			DBConnection connectionDetails = new DBConnection();
			connection = connectionDetails.getConnection();

			ps = connection.prepareStatement("delete from question_bank where question_id=?");
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter the id to delete : ");
			int questionNo = scanner.nextInt();
			ps.setInt(1, questionNo);
			int i = ps.executeUpdate();

			System.out.println("The question "+questionNo+" is deleted successfully\nplease check the database");
		}

		catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

}
