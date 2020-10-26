package com.perfectcherry.exception;

public class NoActiveUserFoundException extends RuntimeException{

	private static final long serialVersionUID = 6136880049038926761L;
	
	public NoActiveUserFoundException(String message) {
		super(message);
	}

}
