package com.te.designpatternAssignment.service;

import com.te.designpatternAssignment.dao.DesignPatternCrud;
import com.te.designpatternAssignment.dao.JdbcImplementation;
import com.te.designpatternAssignment.dao.WrongInputException;

public class Workers_Service_JDBC_Implementation implements Workers_Service {

	DesignPatternCrud designPatternCrud = new JdbcImplementation();

	public static Workers_Service getService() {
		return new Workers_Service_JDBC_Implementation();
	}

	private Workers_Service_JDBC_Implementation() {

	}

	@Override
	public void create(int id) {
		if (id <= 0) {
			try {
				throw new WrongInputException("Worker's ID cannot be zero!! and cannot be negative!! ");
			} catch (Exception e) {
				e.printStackTrace();
//				System.err.println(e.getMessage());
			}
		} else {
			designPatternCrud.create(id);
		}
	}

	@Override
	public void retreive() {
		designPatternCrud.retreive();
	}

	@Override
	public void update(int id) {
		if (id <= 0) {
			try {
				throw new WrongInputException("Worker's ID cannot be zero!! and cannot be negative!! ");
			} catch (Exception e) {
				e.printStackTrace();
//				System.err.println(e.getMessage());
			}
		} else {
			designPatternCrud.update(id);
		}

	}

	@Override
	public void delete(int id) {
		if (id <= 0) {
			try {
				throw new WrongInputException("Worker's ID cannot be zero!! and cannot be negative!! ");
			} catch (Exception e) {
				e.printStackTrace();
//				System.err.println(e.getMessage());
			}
		} else {
			designPatternCrud.delete(id);
		}

	}

}
