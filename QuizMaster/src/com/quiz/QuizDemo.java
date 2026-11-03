package com.quiz;

import java.sql.SQLException;
import java.util.Scanner;
import com.quiz.interfaces.StudentRegistration;
import com.quiz.interfaces.StudentScoreById;
import com.quiz.interfaces.AddNewQuestionByAdmin;
import com.quiz.interfaces.DeleteQuestionByAdmin;
import com.quiz.interfaces.EditExistingQuestion;
import com.quiz.interfaces.StudentLogin;
import com.quiz.interfaces.Validation;
import com.quiz.interfaces.ViewAllStudentScoreAscendingOrder;
import com.quiz.interfaces.ViewTopScorer;

public class QuizDemo {
	public static void main(String args[]) throws Exception {
		Scanner scanner = new Scanner(System.in);
		StudentRegistration studentDao = new ImplStudentRegistration();
		StudentLogin loginDao = new ImplStudentLogin();
		Validation validation = new ImplValidation();
		
		boolean registered = false;
		boolean exit = false;
		
		System.out.println("\n======= QUIZ MASTER =======");
		System.out.println("1. Student Registration");
		System.out.println("2. Student Login");
		System.out.println("\nAdmin Operation:");
		System.out.println("6. Add question with 4 options into database");
		System.out.println("7. Display all students score as per ascending order");
		System.out.println("8. Fetch student score by using id");
		System.out.println("9. Delete the question");
		System.out.println("10. Fetch the maximum score");
		System.out.println("11. Edit existing questions");
		System.out.println("12. Exit");
		
		while(!exit) {
		System.out.println("Enter Choice: ");
			String Choice = scanner.nextLine().trim();
			
			switch(Choice) {
			
			case "1":
				Student s = studentDao.getStudentInput(scanner);
				//Validation first
				if(!validation.validateStudent(s)) {
					System.out.println("Registration Failed! Please correct the details.");
					break;
				}
				
				boolean result = studentDao.registerStudent(s);
				if(result) {
					System.out.println("Registration successful! Now Please login.");
					registered = true;
				}else {
					System.out.println("Registration Failed!");
					break;
				}
				
				case "2":
				//Registration first
					if(!registered) {
						System.out.println("Please register first.");
						break;
					}
					if(loginDao.login(scanner)) {
						exit = true;
					}
					break;
				
			case "6":
				AddNewQuestionByAdmin addNewQuestionByAdmin=new ImplAddNewQuestionByAdmin();
				addNewQuestionByAdmin.setAddNewQuestionsByAdmin();
				break;
				
			case "7":
				ViewAllStudentScoreAscendingOrder viewAllStudentScoreAscendingOrder=new ImplViewAllStudentScoreAscendingOrder();
				viewAllStudentScoreAscendingOrder.getStudentScoreByAscendingOrder();
				break;
				
			case "8":
				StudentScoreById studentScoreById=new ImplStudentScoreById();
				studentScoreById.getStudentScoreById();
				break;
				
			case "9":
				DeleteQuestionByAdmin deleteQuestionByAdmin=new ImplDeleteQuestionByAdmin();
				deleteQuestionByAdmin.setDeleteQuestionByAdmin();
				break;
				
			case "10":
				ViewTopScorer viewTopScorer=new ImplViewTopScorer();
				viewTopScorer.getTopScorer();
				break;
				
			case "11":
				EditExistingQuestion editExistingQuestion = new ImplEditExistingQuestion();
				editExistingQuestion.getEditExistingQuestion();
				break;
			case "12":
				System.out.println("Thank You");
				exit = true;
				continue;
			default:
				System.out.println("Invalid Choice! please enter between 1 to 12 only.");			
			}
			//---------------Do you want to continue-------
			System.out.println("\n Do you want to continue?(yes/no)");
			String again = scanner.nextLine().trim();
			if(!again.equalsIgnoreCase("yes") && !again.equalsIgnoreCase("y")) {
				System.out.println("Thank You");
				exit = true;
			}
		}
			scanner.close();	
	}

}
