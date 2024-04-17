package com.henryschein.productservice.customeexception;

import com.henryschein.orderservice.errorcodes.ErrorCodes;

public class CustomException extends RuntimeException{
	private ErrorCodes errorCode;

    public CustomException(String message) {
        super(message);
       
    }

  
	public ErrorCodes getErrorCode() {
        return errorCode;
    }

}
