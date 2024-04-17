package com.henryschein.userservice.customeException;


import com.henryschein.userservice.errorcodes.UserErrorCode;

public class CustomException extends RuntimeException{

    public CustomException(String message) {
        super(message);
    }

}


