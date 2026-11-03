package com.quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.quiz.interfaces.EditExistingQuestion;

/*User Story 6.2: Edit Existing Question */

public class ImplEditExistingQuestion implements EditExistingQuestion{

	@Override
	public void getEditExistingQuestion() throws Exception {
		System.out.println("Enter the admin username :");
		Scanner scanner=new Scanner(System.in);
		String uname=scanner.next();
		System.out.println("Enter the password :");
		String pword=scanner.next();
		AdminCredentials adminCredentials=new AdminCredentials();
		boolean varify=adminCredentials.getAdminVerification(uname, pword);
		if(varify==true) {
			setEditExistingQuestion() ;	
		}
		else {
			boolean isNotStudent=VerificationOfUserLogin.getUserLoginVerification(uname, pword);

			if(isNotStudent==true) {
				System.out.println("Access Denied for students...");
				System.out.println("\nPlease enter \"admin\" credentials :");
				getEditExistingQuestion();		
			}
			else {
				getEditExistingQuestion();
			}
		}
		
	}

public void setEditExistingQuestion() {
		
		System.out.println("Enter the question id to edit question:");
		Scanner scanner=new Scanner(System.in);
		int questionId=scanner.nextInt();
		
		Connection connection=null;
		PreparedStatement ps=null;
		PreparedStatement ps1=null;
		ResultSet rs=null;
		PreparedStatement ps2=null;
		boolean b=false;
		
		try {
			DBConnection connectionDetails=new DBConnection();
			connection=connectionDetails.getConnection();
			ps=connection.prepareStatement("select * from question_bank where question_id=?");
			
					    ps.setInt(1,questionId);
					    rs=ps.executeQuery(); 
					    
					   while(rs.next()) { 
						   	System.out.println("Original question - "+rs.getString("question"));
						   	System.out.println("option_a - "+rs.getString("option_a"));
						 	System.out.println("option_b - "+rs.getString("option_b"));
						 	System.out.println("option_c - "+rs.getString("option_c"));
						 	System.out.println("option_d - "+rs.getString("option_d"));
						 	System.out.println("Correct answer - "+rs.getString("correct_answer"));
					   } 
					   scanner.nextLine();
						System.out.println("\nEnter the new Question : ");
						String newQuestion=scanner.nextLine();
						
						ps1=connection.prepareStatement("select question from question_bank");
						ResultSet rs1=ps1.executeQuery();
						
						List<String> questionList=new ArrayList<String>();
						while(rs1.next()) {
							questionList.add(rs1.getString("question"));
						}
						
						for(int i=0;i<questionList.size();i++) {
							b=questionList.get(i).equals(newQuestion);
							if(b==true) {
							 break;	
							}
						}
						
						if(b==true) {
							System.out.println("Question is already present.....add another question");
						}else {
						ps2 = connection.prepareStatement("update question_bank set question = ?,option_a = ?,option_b = ?,option_c = ?,option_d = ?,correct_answer = ? where question_id = ?;");
					
						ps2.setString(1, newQuestion);
						System.out.println("Enter option - a :");
						String option_a=scanner.nextLine();
					
						ps2.setString(2, option_a);
						System.out.println("Enter option - b :");
						String option_b=scanner.nextLine();
					
						ps2.setString(3, option_b);
						System.out.println("Enter option - c :");
						String option_c=scanner.nextLine();

						ps2.setString(4, option_c);
						System.out.println("Enter option - d :");
						String option_d=scanner.nextLine();
					
						ps2.setString(5, option_d);
						System.out.println("Enter correct answer :");
						String correct_answer=scanner.nextLine();
				
						ps2.setInt(7, questionId);
						do {	
							if(!(correct_answer.equalsIgnoreCase("a")||correct_answer.equalsIgnoreCase("b") ||correct_answer.equalsIgnoreCase("c") || correct_answer.equalsIgnoreCase("d"))) {
								System.out.println("Invalid answer (valid answer should be - a/b/c/d)....\n\n Please Enter correct answer :");
								correct_answer = scanner.next();	 
							}
						}while(!(correct_answer.equalsIgnoreCase("a")||correct_answer.equalsIgnoreCase("b") ||correct_answer.equalsIgnoreCase("c") || correct_answer.equalsIgnoreCase("d")));
						ps2.setString(6, correct_answer.toLowerCase());
						ps2.executeUpdate();
						System.out.println("Question added to Question Bank Successfully...");
						}
					  } 
					   
					  catch(Exception e) { 
					   e.printStackTrace(); 
					  } finally {
						  try {
							   connection.close();
							   ps.close(); 
							   rs.close(); 
						  } catch (SQLException e) {
							e.printStackTrace();
						  } 
						   
					 }
		}
}
