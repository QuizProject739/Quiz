package com.quiz.interfaces;

import java.sql.SQLException;
import java.util.Scanner;

import com.quiz.Student;

public interface StudentRegistration {
	public Student getStudentInput(Scanner scanner);
	
	public boolean registerStudent(Student s) throws SQLException;
}
