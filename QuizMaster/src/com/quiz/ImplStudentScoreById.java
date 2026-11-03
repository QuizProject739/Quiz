package com.quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.quiz.exceptions.StudentIsNotRegisteredException;
import com.quiz.interfaces.StudentScoreById;

/*User Story 3.3: Search Student Score by ID */

public class ImplStudentScoreById implements StudentScoreById{

	Connection connection=null;
	PreparedStatement ps1=null;
	PreparedStatement ps2=null;
	
	@Override
	public void getStudentScoreById() throws Exception {
		System.out.println("Enter the admin username :");
		Scanner scanner=new Scanner(System.in);
		String uname=scanner.next();
		System.out.println("Enter the password :");
		String pword=scanner.next();
		AdminCredentials adminCredentials=new AdminCredentials();
		boolean varify=adminCredentials.getAdminVerification(uname, pword);
		if(varify==true) {
			getScoreByAdmin() ;	
		}
		else {
			boolean isNotStudent=VerificationOfUserLogin.getUserLoginVerification(uname, pword);

			if(isNotStudent==true) {
				System.out.println("Access Denied for students...");
				System.out.println("\nPlease enter \"admin\" credentials :");
				getStudentScoreById();		
			}
			else {
				getStudentScoreById();
			}
		}
	}

	/*getScoreByAdmin() method is used to fetch result of multiple user.*/
	public void getScoreByAdmin() throws Exception {
		System.out.println("Hellow Admin.........!!!");
		boolean i=false;
		while(i==false) {
			System.out.println("Enter the id of a student to get result:");
			Scanner scanner=new Scanner(System.in);
			int studentId=scanner.nextInt();
			//System.out.println(studentId);
			try {
				DBConnection connectionDetails = new DBConnection();
				connection = connectionDetails.getConnection();
				ps1=connection.prepareStatement("select id, username from student");
				ResultSet rs1=ps1.executeQuery();
				Map<Integer,String> listOfStudentId=new HashMap<Integer,String>();
				while(rs1.next()) {
					int idOfStudent=rs1.getInt("id");
					//System.out.println(idOfStudent);
					String usernameOfStudent=rs1.getString("username");
					listOfStudentId.put(idOfStudent,usernameOfStudent);
					//System.out.println(listOfStudentId);
				}
				Set<Integer> set=listOfStudentId.keySet();
				//System.out.println(set);
				boolean present=set.contains(studentId);  // 102
				if(present==true) {
					getScoreByStudentId(studentId);
				}else {
					try {
						throw new StudentIsNotRegisteredException("Student id : "+studentId+" does not exist......");
					}catch(Exception e) {
						e.printStackTrace();
					}
				}	

			}catch(Exception e){
				e.printStackTrace();
			}finally {
				ps1.close();
				connection.close();
			}

			System.out.println("\nDo you want to see result of other student?\nPlease enter y for yes, any other key to exit");
			char input=scanner.next().charAt(0);
			if(input=='y') {
				i=false;
				continue;
			}else {
				break;
			}
		}	
	}

	/*getScoreByStudentId() -method is used to fetch result,if user is verified*/
	public void getScoreByStudentId(int studentId) throws SQLException {
		//System.out.println("Hiii");
		try{
			DBConnection connectionDetails = new DBConnection();
			connection = connectionDetails.getConnection();
			ps2 = connection.prepareStatement("select student.id, student.first_name, student.last_name, student_result.score from student INNER JOIN student_result on student.id = student_result.id where student.id = ?");
			ps2.setInt(1,studentId);
			//System.out.println("Hiii");
			ResultSet rs2=ps2.executeQuery();
			while(rs2.next()) {
				String firstname=rs2.getString("first_name");
				//System.out.println(firstname);
				String lastname=rs2.getString("last_name");
				String score=rs2.getString("score");
				//System.out.println(score);
				System.out.println("The score of "+firstname+" "+lastname + " is "+score);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			connection.close();
			ps2.close();
		}
	}
}
