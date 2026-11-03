package com.quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.quiz.interfaces.DisplayQuizQuestions;

public class ImplDisplayQuizQuestions implements DisplayQuizQuestions{

	@Override
	public List<Question> getQuestions() throws SQLException {
		Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Question> questions = new ArrayList<>();

        try {
            con = DBConnection.getConnection();

            ps = con.prepareStatement("SELECT * FROM question_bank");

            rs = ps.executeQuery();

            while (rs.next()) {
                Question q = new Question();

                q.setId(rs.getInt("question_id"));
                q.setQuestionTest(rs.getString("question"));
                q.setOption1(rs.getString("option_a"));
                q.setOption2(rs.getString("option_b"));
                q.setOption3(rs.getString("option_c"));
                q.setOption4(rs.getString("option_d"));
                q.setCorrectOption(rs.getString("correct_answer"));

                questions.add(q);
            }

            Collections.shuffle(questions);

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            rs.close();
            ps.close();
            con.close();
        }

        return questions;
    }

    public int startQuiz() throws SQLException {

        List<Question> questions = getQuestions();

        Scanner sc = new Scanner(System.in);

        int score = 0;

        System.out.println("=== Java Quiz ===");

        int totalQuestions = Math.min(10, questions.size());

        for (int i = 0; i < totalQuestions; i++) {

            Question q = questions.get(i);

            System.out.println("\nQuestion " + (i + 1) + ":");
            System.out.println(q.getQuestionTest());

            System.out.println("a. " + q.getOption1());
            System.out.println("b. " + q.getOption2());
            System.out.println("c. " + q.getOption3());
            System.out.println("d. " + q.getOption4());

            String answer;

            while (true) {
                System.out.print("Enter your answer (a or b or c or d): ");

                answer=sc.next().toLowerCase();

                    if ((answer.equals("a")||answer.equals("b")||answer.equals("c")||answer.equals("d"))) {
                    	
                        break;
                    }
                    System.out.println("Invalid option! Enter a to d.");
                }       
            

            if (answer.equalsIgnoreCase(q.getCorrectOption().trim())) {
                score++;
            }
        }

        System.out.println("\nYour Score: " + score + " / " + totalQuestions);

        return score;
    }

    public String calculateGrade(int score) {

        if (score >= 8) {
            return "A";
        } else if (score >= 5) {
            return "B";
        } else {
            return "C";
        }
    }

    public String getFeedback(int score) {

        if (score >= 8) {
            return "Excellent";
        } else if (score >= 5) {
            return "Good";
        } else {
            return "Needs Improvement";
        }
    }

    public void saveScore( int studentId,int score, String grade)
            throws SQLException {

        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        String username=null;
        
        try {
            // Step 1 - Establish connection
            con = DBConnection.getConnection();

            // Step 2 - Create PreparedStatement
            ps1 = con.prepareStatement(
                    "select username from student where id=?");
            
            ps1.setInt(1, studentId);
            
            ResultSet rs=ps1.executeQuery();
            
            if(rs.next()) {
            	username=rs.getString("username");
            }
            
            ps = con.prepareStatement(
                "INSERT INTO student_result (id, username,score, grade) VALUES (?,?, ?, ?)");

            ps.setInt(1, studentId);
            ps.setString(2, username);
            ps.setInt(3, score);
            ps.setString(4, grade);

            // Step 3 - Execute SQL statement
            int status = ps.executeUpdate();

            System.out.println("Score saved successfully >> " + status);

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            ps.close();
            con.close();
        }
    }


	}


