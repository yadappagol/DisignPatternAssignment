package com.te.designpatternAssignment.main;

import java.util.Scanner;

import com.te.designpatternAssignment.dao.WrongInputException;
import com.te.designpatternAssignment.service.Workers_Service;
import com.te.designpatternAssignment.service.Workers_Service_Hibernate_Implementation;
import com.te.designpatternAssignment.service.Workers_Service_JDBC_Implementation;

public class FactoryDesignPattern_AssignmentClass {
	
	
	public Workers_Service driver() {
		Scanner scanner=new Scanner(System.in);
		
		System.out.println("\n************************WELCOME TO DESIGN_PATTERN WORLD************************\n");
		
		System.out.println("Press 1 : For Performing the 'C-R-U-D' operation on Workers details Using JDBC  ");
		System.out.println("Press 2 : For Performing the 'C-R-U-D' operation on Workers details Using Hibernate \n ");
		
		System.out.println("Enter your choice :\n");
		int choice= scanner.nextInt();

		
		if(choice==1) {
			return Workers_Service_JDBC_Implementation.getService();
		}else if (choice==2) {
			return Workers_Service_Hibernate_Implementation.getService();
		}else {
			try {
				throw new WrongInputException("Invalid Option!! Please  enter correct option");
			} catch (Exception e) {
//				e.printStackTrace();
				System.err.println(e.getMessage());
			} 
		}
		
		return null;
		
		
	}

}
