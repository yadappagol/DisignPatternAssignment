package com.te.designpatternAssignment.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import javax.persistence.Persistence;

import org.hibernate.internal.build.AllowSysOut;

import com.te.designpatternAssignment.dto.Workers;

public class JdbcImplementation implements DesignPatternCrud {
	
	static Scanner scanner = new Scanner(System.in);
	
	static Connection connection = null;
	static PreparedStatement preparedStatement = null;
	static ResultSet resultSet = null;
	static String dburl = "jdbc:mysql://localhost:3306/designpattern?user=root&password=root";
	
	public static void insertloop() {
		System.out.println("Enter Your Wokers Id :");
		int id = scanner.nextInt();
		
//		scanner.nextLine();
		System.out.print("Enter Your Wokers Name :");
		String name = scanner.nextLine();

//		scanner.nextLine();
		System.out.print("Enter Your Workers Designation :");
		String design = scanner.nextLine();

		System.out.print("Enter Your Workers Salary  :");
		double salary = scanner.nextDouble();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(dburl);
			String query = "insert into workers values(?,?,?,?)";
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, design);
			preparedStatement.setString(3, name);
			preparedStatement.setDouble(4, salary);

			preparedStatement.executeUpdate();

			System.out.println("Your data inserted Successfully.. for the w_ID :" + id);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
					if (preparedStatement != null) {
						preparedStatement.close();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	

	/* For Inserting the data */
	@Override
	public void create(int id) {
		
		while (true) {
			insertloop();
			System.out.println("\nDo you want to continue press : Yes");
			System.out.println("If you don't want to continue press : No\n");
			String option = scanner.next();
			if (option.equals("yes") || option.equalsIgnoreCase("Y")) {
				insertloop();
			} else if (option.equals("no") || option.equalsIgnoreCase("N")) {
				System.out.println("****************Thank You***************");
				System.exit(0);
			} else {
				System.err.println("Please enter a valid option...!!");
				scanner.next();
				if (option.equals("yes") || option.equalsIgnoreCase("Y")) {
					continue;
				}
				System.err.println("--------**Please start the process agian**----------");
				break;
			}

		}
		scanner.close();

	}

	

	/* For Fetching the data */
	@Override
	public void retreive() {
		System.out.println("Press 1 :For fetching all data from table ");
		System.out.println("Press 2 :For fetching single id information from table ");

		System.out.print("Enter Your Choice :");
		int choice = scanner.nextInt();

		switch (choice) {
		case 1:
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(dburl);
				String query2 = "select * from workers ";
				preparedStatement = connection.prepareStatement(query2);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					System.out.println("Id    : " + resultSet.getInt(1));
					System.out.println("Name  : " + resultSet.getString(2));
					System.out.println("Place : " + resultSet.getString(3));
					System.out.println("Salary: " + resultSet.getString(4));
					System.out.println("-----------------------------------");
				}
				System.out.println("Data Fetched Successfully..!!");
			} catch (Exception e) {
				e.printStackTrace();
			}

			finally {
				try {
					if (connection != null) {
						connection.close();
						if (preparedStatement != null) {
							preparedStatement.close();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			break;
		case 2:
			System.out.println("Enter your worker's Id :");
			int id = scanner.nextInt();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(dburl);
				String query2 = "select w_id,w_design,w_name,w_salary from workers where w_id=? ";
				preparedStatement = connection.prepareStatement(query2);
				preparedStatement.setInt(1, id);

				resultSet = preparedStatement.executeQuery();

				while (resultSet.next()) {
					System.out.println("Id    : " + resultSet.getInt(1));
					System.out.println("Name  : " + resultSet.getString(2));
					System.out.println("Place : " + resultSet.getString(3));
					System.out.println("Salary: " + resultSet.getDouble(4));
					System.out.println("-----------------------------------");
				}
				System.out.println("Data Fetched Successfully..!!");
			} catch (Exception e) {
				e.printStackTrace();
			}

			finally {
				try {
					if (connection != null) {
						connection.close();
						if (preparedStatement != null) {
							preparedStatement.close();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			break;
		default:
			try {
				throw new WrongInputException("Wrong Input ! Please Enter correct input");
			} catch (Exception e) {
				// e.printStackTrace();
				System.err.println(e.getMessage());
			}

		}

	}

	/* For updating the data */
	@Override
	public void update(int id) {
		System.out.println("Press 1 : For upadating the name ");
		System.out.println("Press 2 : For upadating the designation ");
		System.out.println("Press 3 : For upadating the salary ");

		System.out.println("Enter your choice :");
		int choice = scanner.nextInt();

		switch (choice) {

		case 1:
			System.out.print("Enter worker's new name :");
			scanner.nextLine();
			String name = scanner.nextLine();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(dburl);
				String query2 = "update workers set w_name=?  where w_id=? ";
				preparedStatement = connection.prepareStatement(query2);
				preparedStatement.setString(1, name);
				preparedStatement.setInt(2, id);

				preparedStatement.executeUpdate();

				System.out.println("Data updated Successfully.. for Worker's Id :" + id);
			} catch (Exception e) {
				e.printStackTrace();
			}

			finally {
				try {
					if (connection != null) {
						connection.close();
						if (preparedStatement != null) {
							preparedStatement.close();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			break;

		case 2:
			System.out.print("Enter worker's new Designation :");
			scanner.nextLine();
			String design = scanner.nextLine();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(dburl);
				String query2 = "update workers set w_design=?  where w_id=? ";
				preparedStatement = connection.prepareStatement(query2);
				preparedStatement.setString(1, design);
				preparedStatement.setInt(2, id);

				preparedStatement.executeUpdate();

				System.out.println("Data updated Successfully.. for Worker's Id :" + id);
			} catch (Exception e) {
				e.printStackTrace();
			}

			finally {
				try {
					if (connection != null) {
						connection.close();
						if (preparedStatement != null) {
							preparedStatement.close();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			break;

		case 3:
			System.out.print("Enter worker's new Salary :");
			scanner.nextLine();
			double salary = scanner.nextDouble();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(dburl);
				String query2 = "update workers set w_salary=?  where w_id=? ";
				preparedStatement = connection.prepareStatement(query2);
				preparedStatement.setDouble(1, salary);
				preparedStatement.setInt(2, id);

				preparedStatement.executeUpdate();

				System.out.println("Data updated Successfully.. for Worker's Id :" + id);
			} catch (Exception e) {
				e.printStackTrace();
			}

			finally {
				try {
					if (connection != null) {
						connection.close();
						if (preparedStatement != null) {
							preparedStatement.close();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			break;

		default:
			try {
				throw new WrongInputException("Entered Choice is Wrong!!");
			} catch (Exception e) {
				e.printStackTrace();
				// System.out.println(e.getMessage());
			}

		}

	}

	/* For deleting the data */
	@Override
	public void delete(int id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(dburl);
			String query = "delete from workers where w_id=?";

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, id);

			preparedStatement.executeUpdate();

			System.out.println("Data deleted successfully... for Worker's Id  :" + id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			try {
				if (connection != null) {
					connection.close();
					if (preparedStatement != null) {
						preparedStatement.close();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
