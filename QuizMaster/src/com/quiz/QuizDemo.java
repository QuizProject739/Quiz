package com.quiz;

import java.sql.SQLException;
import java.util.Scanner;
import com.quiz.interfaces.StudentRegistration;
import com.quiz.interfaces.StudentLogin;
import com.quiz.interfaces.Validation;

public class QuizDemo {
	public static void main(String args[]) throws SQLException {
		Scanner scanner = new Scanner(System.in);
		StudentRegistration studentDao = new ImplStudentRegistration();
		StudentLogin loginDao = new ImplStudentLogin();
		Validation validation = new ImplValidation();
		
		boolean registered = false;
		boolean exit = false;
		
		System.out.println("\n======= QUIZ MASTER =======");
		System.out.println("1. Student Registration");
		System.out.println("2. Student Login");
		System.out.println("3. Exit");
		
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
				
			case "3":
				System.out.println("Thank You");
				exit = true;
				continue;
			default:
				System.out.println("Invalid Choice! please enter 1, 2 or 3.");			
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
