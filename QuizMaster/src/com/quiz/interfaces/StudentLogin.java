package com.quiz.interfaces;

import java.sql.SQLException;
import java.util.Scanner;

public interface StudentLogin {

	public boolean login(Scanner scanner)throws SQLException;
	
	public boolean loginStudent(String username, String password) throws SQLException;
}
