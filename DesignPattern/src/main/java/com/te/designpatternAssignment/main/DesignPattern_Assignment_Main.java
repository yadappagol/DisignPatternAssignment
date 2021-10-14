package com.te.designpatternAssignment.main;

import java.util.Scanner;

import com.te.designpatternAssignment.dao.WrongInputException;
import com.te.designpatternAssignment.service.Workers_Service;

public class DesignPattern_Assignment_Main {
	
	public static void constantData() {
		FactoryDesignPattern_AssignmentClass designpattern = new FactoryDesignPattern_AssignmentClass();
		Workers_Service main = designpattern.driver();
		
		System.out.println("\n************************Start your CRUD operations************************\n");

		System.out.println("Press 1 : For Creating or Adding the Workers details ");
		System.out.println("Press 2 : For Retreiving or Fetching the Workers details ");
		System.out.println("Press 3 : For Updating Workers details ");
		System.out.println("Press 4 : For Deleting the Workers details ");
		System.out.println("Press 5 : For Exit ");

		Scanner scanner = new Scanner(System.in);
		

		System.out.print("Enter Your Option :");
		int option = scanner.nextInt();
		int w_id;

		switch (option) {

		case 1:
			System.out.println("Enter Your Workers Id :");
			w_id = scanner.nextInt();
			main.create(w_id);
			break;
		case 2:
			main.retreive();
			break;
		case 3:
			System.out.println("Enter Your Workers Id :");
			w_id = scanner.nextInt();
			main.update(w_id);
			break;
		case 4:
			System.out.println("Enter Your Workers Id :");
			w_id = scanner.nextInt();
			main.delete(w_id);
			
			break;
		default:
			try {
				throw new WrongInputException("Wrong Entry!! Please do correct entry!!");
			} catch (Exception e) {
//				e.printStackTrace();
				System.err.println(e.getMessage());
			}
			
		}
	}
	
	
	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		while (true) {
			constantData();
			System.out.println("\nDo you want to continue press : Yes");
			System.out.println("If you don't want to continue press : No\n");
			String option = scanner.next();
			if (option.equals("yes") || option.equalsIgnoreCase("Y")) {
				constantData();
			} else if (option.equals("no") || option.equalsIgnoreCase("N")) {
				System.out.println("****************Thank You***************");
				System.out.println("*******Welcome Again**************");
				System.exit(0);
			} else {
				System.err.println("Please enter a valid option...!!");
				scanner.next();
				if (option.equals("yes") || option.equalsIgnoreCase("Y")) {
					continue;
				}
				if (option.equals("no") || option.equalsIgnoreCase("N")) {
					System.out.println("*******Thank You**************");
					System.out.println("*******Welcome Again**************");
					System.exit(0);
				}
				System.err.println("--------**Please start the process agian**----------");
				break;
			}

		}
		scanner.close();

	}

}
