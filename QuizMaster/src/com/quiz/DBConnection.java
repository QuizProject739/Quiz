package com.quiz;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
		private static final String DB_DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
		private static final String DB_URL = "jdbc:mysql://localhost:3306/quizmaster";
		private static final String DB_USERNAME = "root";
		private static final String DB_PASSWORD = "Aditi@1019";
		
		public Connection getConnection() {
			Connection con = null;
			try {
				//loading driver
			Class.forName(DB_DRIVER_NAME);
			//establish connection
			con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			
			}catch(Exception e) {
				e.printStackTrace();
			}
			return con;
		}
	}

