package com.quiz;

import com.quiz.interfaces.Validation;

public class ImplValidation implements Validation {

	@Override
	public boolean validateStudent(Student s) {
		//Empty field validation
				if(s.getFirstName() == null || s.getFirstName().trim().isEmpty()
						|| s.getLastName() == null || s.getLastName().trim().isEmpty()
						|| s.getPassword() == null || s.getPassword().trim().isEmpty()
						|| s.getUserName() == null || s.getUserName().trim().isEmpty()
						|| s.getCity() == null || s.getCity().trim().isEmpty()
						||s.getEmail() ==null || s.getEmail().trim().isEmpty()
						||s.getMobile() == null || s.getMobile().trim().isEmpty()) {
					System.out.println("Fields cannot be empty");
					return false;			
				}
				
				//Email Validation
				if(!s.getEmail().contains("@") || !s.getEmail().contains(".")) {
					System.out.println("Invalid Email");
					return false;
				}
				
				//Password Validation
				if(s.getPassword().length() < 6) {
					System.out.println("Password must be minimum 6 characters");
					return false;
				}
				
				//Mobile validation
				if(!s.getMobile().matches("[0-9]{10}")) {
					System.out.println("Mobile must be exactly 10 digits");
					return false;
				}

				System.out.println("Validation Successful!");
				return true;
			}

	}


