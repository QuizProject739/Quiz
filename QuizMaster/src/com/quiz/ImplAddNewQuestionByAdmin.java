package com.quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import com.quiz.exceptions.InvalidChoiceException;
import com.quiz.interfaces.AddNewQuestionByAdmin;

/*User Story 3.1: Add New Questions 
 * User Story 6.1: Prevent Duplicate Questions 
 */
public class ImplAddNewQuestionByAdmin implements AddNewQuestionByAdmin{

	@Override
	public void setAddNewQuestionsByAdmin() throws Exception {
		System.out.println("Enter the admin username :");
		Scanner scanner =new Scanner(System.in);
		String uname=scanner.next();
		System.out.println("Enter the password :");
		String pword=scanner.next();
		AdminCredentials adminCredentials=new AdminCredentials();
		boolean varify=adminCredentials.getAdminVerification(uname, pword);
		//System.out.println(varify);
		if(varify==true) {
			addNewQuestion();	
		}
		else {
			boolean isNotStudent=VerificationOfUserLogin.getUserLoginVerification(uname, pword);


			if(isNotStudent==true) {
				System.out.println("Access Denied for students...");
				System.out.println("\nPlease enter \"admin\" credentials :");
				setAddNewQuestionsByAdmin();			
			}
		}
		
	}
	
/*addNewQuestion()-method is used to add Question in Database if admin credentials are verified*/	
	
	private static void addNewQuestion() throws Exception {
		System.out.println("-----Add Questions Here (Admin Only)-----\n");
		System.out.println("How many questions do you want to add?");
		int choice=0;
		boolean flag=false;
		do {
			flag=true;
			try {
				Scanner scanner=new Scanner(System.in);
				choice=scanner.nextInt();
				if(choice<0) {
					throw new InvalidChoiceException();
				}
			}catch(Exception e) {
				System.out.println("Please enter valid choice:");
				flag=false;
			}
		}while(flag==false);
		
		for(int i=0;i<choice;i++) {
			Connection connection=null;
			PreparedStatement preparedStatement =null;
			try {

				DBConnection connectionDetails=new DBConnection();
				connection=connectionDetails.getConnection();
				Scanner scanner=new Scanner(System.in);
				System.out.println("\nEnter the Question : "+(i+1));
				preparedStatement = connection.prepareStatement("insert into question_bank(question,option_a,option_b,option_c,option_d,correct_answer)values (?,?,?,?,?,?)");
				String question=scanner.nextLine();
				preparedStatement.setString(1, question);
				System.out.println("Enter option - a :");
				String option_a=scanner.nextLine();
				preparedStatement.setString(2, option_a);
				System.out.println("Enter option - b :");
				String option_b=scanner.nextLine();
				preparedStatement.setString(3, option_b);
				System.out.println("Enter option - c :");
				String option_c=scanner.nextLine();
				preparedStatement.setString(4, option_c);
				System.out.println("Enter option - d :");
				String option_d=scanner.nextLine();
				preparedStatement.setString(5, option_d);
				System.out.println("Enter correct answer :");
				String correct_answer=scanner.nextLine();
				do {	
					if(!(correct_answer.equalsIgnoreCase("a")||correct_answer.equalsIgnoreCase("b") ||correct_answer.equalsIgnoreCase("c") || correct_answer.equalsIgnoreCase("d"))) {
						System.out.println("Invalid answer (valid answer should be - a/b/c/d)....\n\n Please Enter correct answer :");
						correct_answer = scanner.next();	 
					}
				}while(!(correct_answer.equalsIgnoreCase("a")||correct_answer.equalsIgnoreCase("b") ||correct_answer.equalsIgnoreCase("c") || correct_answer.equalsIgnoreCase("d")));
				preparedStatement.setString(6, correct_answer.toLowerCase());
				preparedStatement.executeUpdate();
				System.out.println("Question added to Question Bank Successfully...");
			}
			catch (Exception e) {
				System.out.println("Unexpected Error...");
				e.printStackTrace();
			}
			finally {
				connection.close();
				preparedStatement.close();
			}
		}
	}
}
