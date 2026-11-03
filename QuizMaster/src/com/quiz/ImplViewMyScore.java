
	package com.quiz;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.Scanner;

	import com.quiz.interfaces.ViewMyScore;

	public class ImplViewMyScore implements ViewMyScore {
		 // Method to view student score
	    public void viewMyScore(int studentId) throws SQLException {

	        Connection con = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;

	        try {
	            // Step 1 - Establish connection
	            con = DBConnection.getConnection();

	            // Step 2 - Create PreparedStatement
	            ps = con.prepareStatement(
	                "SELECT score, grade FROM student_result WHERE id = ?"
	            );

	            // Step 3 - Set student ID
	            ps.setInt(1, studentId);

	            // Step 4 - Execute query
	            rs = ps.executeQuery();

	            // Step 5 - Display student score
	            if (rs.next()) {
	                System.out.println("Your Score: " + rs.getInt("score"));
	                System.out.println("Your Grade: " + rs.getString("grade"));
	            } else {
	                System.out.println("No score found for this student.");
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();

	        } finally {
	            rs.close();
	            ps.close();
	            con.close();
	        }
	    }
	    

	    // Main method to test score service
	    public static void main(String[] args) throws SQLException {

	        Scanner sc = new Scanner(System.in);

	        // Accept student ID
	        System.out.print("Enter Student ID: ");
	        int studentId = sc.nextInt();

	        // Create ScoreService object
	        ImplViewMyScore service = new ImplViewMyScore();

	        // Call method to view score
	        service.viewMyScore(studentId);
	    }
	}




