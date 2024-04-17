package com.henryschein.orderservice.customeexception;

public class CustomeException extends RuntimeException{
	private final String errorCode;
	
	public CustomeException(String errorCode , String message) {
		super(message);
		this.errorCode = errorCode ;
	}
	
	public String geterrorCode() {
		return errorCode;
	}

}
