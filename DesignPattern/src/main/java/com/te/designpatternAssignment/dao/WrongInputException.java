package com.te.designpatternAssignment.dao;

public class WrongInputException extends RuntimeException {
String msg;

public WrongInputException(String msg) {
	this.msg = msg;
}
@Override
public String getMessage() {
	return this.msg;
}
}
