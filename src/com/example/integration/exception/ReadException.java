package com.example.integration.exception;

public class ReadException extends RuntimeException{
	
	public ReadException(String message) {
		super(message);
	}
	
	public ReadException(String message, Throwable cause) {	
		super(message, cause);
	}
	
}