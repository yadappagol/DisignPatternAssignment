package com.te.designpatternAssignment.dao;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.te.designpatternAssignment.dto.Workers;

public class HibernateImplementation implements DesignPatternCrud {

	static EntityManagerFactory factory = null;
	static EntityManager manager = null;
	static EntityTransaction transaction = null;
	Query query;
	static Scanner scanner = new Scanner(System.in);

	public static void add() {
		while (true) {
			Workers workers = new Workers();

			System.out.print("Enter Your Wokers Id :");
			int id = scanner.nextInt();

			System.out.print("Enter Your Wokers Name :");
			String name = scanner.nextLine();

			// scanner.nextLine();
			System.out.print("Enter Your Workers Designation :");
			String design = scanner.nextLine();

			System.out.print("Enter Your Workers Salary  :");
			double salary = scanner.nextDouble();

			workers.setW_id(id);
			workers.setW_name(name);
			workers.setW_design(design);
			workers.setW_salary(salary);

			try {
				factory = Persistence.createEntityManagerFactory("DesignPattern");
				manager = factory.createEntityManager();
				transaction = manager.getTransaction();

				transaction.begin();
				manager.persist(workers);
				transaction.commit();
				System.out.println("Successfully inserted the details of the Worker Id :" + id);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (factory != null) {
					factory.close();
					if (manager != null) {
						manager.close();
					}
				}
			}

			System.out.println("Do you want to add more Workers  details : ");
			System.out.println("Type: yes :->For adding more workers details");
			System.out.println("Type: no  :->For ending the insertion process ");
			System.out.println("-------------------------------------");
			System.out.print("Enter you option :");
			String option = scanner.next();

			if (option.equalsIgnoreCase("yes")) {

				return;

			} else if (option.equalsIgnoreCase("no")) {
				System.out.println(">>>>>>>>>Visit again<<<<<<<<<<<<<");
				System.out.println("*****Thank you********");
				System.exit(0);
			} else {
				try {
					throw new WrongInputException("You entered the wrong option!! Please enter correct option");
				} catch (Exception e) {
					e.printStackTrace();
					System.err.println(e.getMessage());
				}
				System.out.println("-------------------------------------");
				System.out.print("Enter you option :");
				option = scanner.next();
				if (option.equalsIgnoreCase("yes")) {
					return;
				} else if (option.equalsIgnoreCase("no")) {
					System.out.println(">>>>>>>>>Visit again<<<<<<<<<<<<<");
					System.out.println("*****Thank you********");
					System.exit(0);
				}

			}

		}

	}

	/* For Inserting the data */
	@Override
	public void create(int id) {

		while (true) {
			add();
			System.out.println("\nDo you want to continue press : Yes");
			System.out.println("If you don't want to continue press : No\n");
			String option = scanner.next();
			if (option.equals("yes") || option.equalsIgnoreCase("Y")) {
				add();
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

		try {
			switch (choice) {
			case 1:
				factory = Persistence.createEntityManagerFactory("DesignPattern");
				manager = factory.createEntityManager();

				String findAll = "from Workers";
				query = manager.createQuery(findAll);
				List<Workers> list = query.getResultList();

				System.out.println(" This is All Data which is present in the table");

				for (Workers workers : list) {
					System.out.println(workers);
				}
				break;
			case 2:
				System.out.print("Enter Your Workers Id :");
				int id = scanner.nextInt();
				factory = Persistence.createEntityManagerFactory("DesignPattern");
				manager = factory.createEntityManager();

				String findOne = "from Workers where w_id= :idno";
				query = manager.createQuery(findOne);
				query.setParameter("idno", id);
				Workers worker_data = (Workers) query.getSingleResult();

				System.out.println(worker_data);
				break;

			default:
				System.err.println("Input is Wrong!!");

				try {
					throw new WrongInputException("Entered Input is Wrong...");
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			if (factory != null) {
				factory.close();
				if (manager != null) {
					manager.close();
				}
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

		while (true) {
			switch (choice) {

			case 1:
				System.out.print("Enter worker's new name :");
				scanner.nextLine();
				String name = scanner.nextLine();
				try {
					factory = Persistence.createEntityManagerFactory("DesignPattern");
					manager = factory.createEntityManager();
					transaction = manager.getTransaction();

					transaction.begin();
					Workers workers = manager.find(Workers.class, id);
					workers.setW_name(name);
					transaction.commit();
					System.out.println("Successfully updated the name of the Worker Id :" + id);
				} catch (Exception e) {
					e.printStackTrace();
					if (transaction != null) {
						transaction.rollback();
					}
				} finally {
					if (factory != null) {
						factory.close();
						if (manager != null) {
							manager.close();
						}
					}
				}
				break;

			case 2:
				System.out.print("Enter worker's new designation :");
				scanner.nextLine();
				String design = scanner.nextLine();
				try {
					factory = Persistence.createEntityManagerFactory("DesignPattern");
					manager = factory.createEntityManager();
					transaction = manager.getTransaction();

					transaction.begin();
					Workers workers = manager.find(Workers.class, id);
					workers.setW_design(design);
					transaction.commit();
					System.out.println("Successfully updated the designation of the Worker Id :" + id);
				} catch (Exception e) {
					e.printStackTrace();
					if (transaction != null) {
						transaction.rollback();
					}
				} finally {
					if (factory != null) {
						factory.close();
						if (manager != null) {
							manager.close();
						}
					}
				}
				break;

			case 3:
				System.out.print("Enter worker's new salary :");
				double salary = scanner.nextDouble();
				try {
					factory = Persistence.createEntityManagerFactory("DesignPattern");
					manager = factory.createEntityManager();
					transaction = manager.getTransaction();

					transaction.begin();
					Workers workers = manager.find(Workers.class, id);
					workers.setW_salary(salary);
					transaction.commit();
					System.out.println("Successfully updated the salary of the Worker Id :" + id);
				} catch (Exception e) {
					e.printStackTrace();
					if (transaction != null) {
						transaction.rollback();
					}
				} finally {
					if (factory != null) {
						factory.close();
						if (manager != null) {
							manager.close();
						}
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
				break;

			}

			System.out.println("Do want to continue the upadation operation !!");
			System.out.println("Type yes : for continue ");
			System.out.println("Type no : for continue ");

			System.out.println("-------------------------------------");
			System.out.print("Enter you option :");
			String option = scanner.next();

			if (option.equalsIgnoreCase("yes")) {
				return;
			} else if (option.equalsIgnoreCase("no")) {
				System.out.println(">>>>>>>>>Visit again<<<<<<<<<<<<<");
				System.out.println("*****Thank you********");
				System.exit(0);
			} else {
				try {
					throw new WrongInputException("You entered the wrong option!! Please enter correct option");
				} catch (Exception e) {
					e.printStackTrace();
					System.err.println(e.getMessage());
				}
				System.out.println("-------------------------------------");
				System.out.print("Enter you option :");
				option = scanner.next();
				if (option.equalsIgnoreCase("yes")) {
					return;
				} else if (option.equalsIgnoreCase("no")) {
					System.out.println(">>>>>>>>>Visit again<<<<<<<<<<<<<");
					System.out.println("*****Thank you********");
					System.exit(0);
				}

			}
		}
	}

	/* For deleting the data */
	@Override
	public void delete(int id) {
		try {
			factory = Persistence.createEntityManagerFactory("DesignPattern");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();

			transaction.begin();
			Workers workers = manager.find(Workers.class, id);
			manager.remove(workers);
			transaction.commit();

			System.out.println("Successfully deleted the details of the Worker Id :" + id);
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			if (factory != null) {
				factory.close();
				if (manager != null) {
					manager.close();
				}
			}
		}

	}

}
