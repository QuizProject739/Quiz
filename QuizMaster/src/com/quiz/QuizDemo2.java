//package com.quiz;
//
//import java.sql.SQLException;
//import java.util.Scanner;
//import com.quiz.interfaces.StudentRegistration;
//import com.quiz.interfaces.StudentScoreById;
//import com.quiz.interfaces.AddNewQuestionByAdmin;
//import com.quiz.interfaces.DeleteQuestionByAdmin;
//import com.quiz.interfaces.EditExistingQuestion;
//import com.quiz.interfaces.StudentLogin;
//import com.quiz.interfaces.Validation;
//import com.quiz.interfaces.ViewAllStudentScoreAscendingOrder;
//import com.quiz.interfaces.ViewTopScorer;
//
//public class QuizDemo2 {
//	public static void main (String args[])throws Exception{
//		Scanner scanner = new Scanner(System.in);
//		StudentRegistration studentDao = new ImplStudentRegistration();
//		StudentLogin loginDao = new ImplStudentLogin();
//		Validation validation = new ImplValidation();
//		
//		  ImplDisplayQuizQuestions service = new ImplDisplayQuizQuestions();
//	      ImplViewMyScore scoreService = new ImplViewMyScore();
//	        
//		boolean registered = false;
//        boolean loggedIn = false;
//		boolean exit = false;
//		
//        int studentId = 0;
//
//		System.out.println("\n======= QUIZ MASTER =======");
//		while(!exit) {
//
//		System.out.println("1. Student Registration");
//		System.out.println("2. Student Login");
//        System.out.println("3. Start Quiz");
//        System.out.println("4. View My Score");
//		System.out.println("\nAdmin Operation:");
//		System.out.println("5. Add question with 4 options into database");
//		System.out.println("6. Display all students score as per ascending order");
//		System.out.println("7. Fetch student score by using id");
//		System.out.println("8. Delete the question");
//		System.out.println("9. Fetch the maximum score");
//		System.out.println("10. Edit existing questions");
//		System.out.println("11. Exit");
//		
//		System.out.println("\nEnter Choice: ");
//			String Choice = scanner.nextLine().trim();
//			
//			switch(Choice) {
//			
//			case "1":
//				Student s = studentDao.getStudentInput(scanner);
//				//Validation first
//				if(!validation.validateStudent(s)) {
//					System.out.println("Registration Failed! Please correct the details.");
//					break;
//				}
//				
//				boolean result = studentDao.registerStudent(s);
//				if(result) {
//					System.out.println("Registration successful! Now Please login.");
//					registered = true;
//				}else {
//					System.out.println("Registration Failed!");
//					
//				}
//				break;
//
//				
//				case "2":
//				//Registration first
//					if(!registered) {
//						System.out.println("Please register first.");
//						break;
//					}
//					if(loginDao.login(scanner)) {
//	                    loggedIn = true;
//	                    System.out.println("Login Successful!");
//
//					}
//					break;
//					
//				 case "3":
//
//		                if (!loggedIn) {
//		                    System.out.println("Please login first.");
//		                    break;
//		                }
//		               System.out.print("Enter Student ID: ");
//		               studentId = Integer.parseInt(scanner.nextLine());
//
//		                int score = service.startQuiz();
//
//		                String grade = service.calculateGrade(score);
//
//		                System.out.println("Your Grade: " + grade);
//
//		                service.saveScore( studentId,score, grade);
//
//		                break;
//
//		            case "4":
//		            	if (!loggedIn) {
//		                    System.out.println("Please login first.");
//		                    break;
//		                }
//
//		               System.out.print("Enter Student ID: ");
//		                int id = Integer.parseInt(scanner.nextLine());
//
//		                scoreService.viewMyScore(id);
//
//		                break;
//
//                        
//				
//			case "5":
//				AddNewQuestionByAdmin addNewQuestionByAdmin=new ImplAddNewQuestionByAdmin();
//				addNewQuestionByAdmin.setAddNewQuestionsByAdmin();
//				break;
//				
//			case "6":
//				ViewAllStudentScoreAscendingOrder viewAllStudentScoreAscendingOrder=new ImplViewAllStudentScoreAscendingOrder();
//				viewAllStudentScoreAscendingOrder.getStudentScoreByAscendingOrder();
//				break;
//				
//			case "7":
//				StudentScoreById studentScoreById=new ImplStudentScoreById();
//				studentScoreById.getStudentScoreById();
//				break;
//				
//			case "8":
//				DeleteQuestionByAdmin deleteQuestionByAdmin=new ImplDeleteQuestionByAdmin();
//				deleteQuestionByAdmin.setDeleteQuestionByAdmin();
//				break;
//				
//			case "9":
//				ViewTopScorer viewTopScorer=new ImplViewTopScorer();
//				viewTopScorer.getTopScorer();
//				break;
//				
//			case "10":
//				EditExistingQuestion editExistingQuestion = new ImplEditExistingQuestion();
//				editExistingQuestion.getEditExistingQuestion();
//				break;
//			case "11":
//				System.out.println("Thank You");
//				exit = true;
//				continue;
//			default:
//				System.out.println("Invalid Choice! please enter between 1 to 12 only.");			
//			}
//			//---------------Do you want to continue-------
//			if (!exit) {
//			System.out.println("\n Do you want to continue?(yes/no)");
//			String again = scanner.nextLine().trim();
//			if(!again.equalsIgnoreCase("yes") && !again.equalsIgnoreCase("y")) {
//				System.out.println("Thank You");
//				exit = true;
//			}
//		}
//		}
//			scanner.close();	
//	}
//
//}
