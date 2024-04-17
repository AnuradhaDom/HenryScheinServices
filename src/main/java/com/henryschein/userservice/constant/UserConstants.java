package com.henryschein.userservice.constant;

import com.henryschein.userservice.errorcodes.UserErrorCode;

public final class UserConstants {
    private UserConstants(){
        //private constructor to prevent instantiation
    }

    // Constants for User entity error messages
    public static final String NAME_REQUIRED_MSG = "Name is required";
    public static final String NAME_LENGTH_MSG = "Name must be between 2 to 100 characters";
    public static final String EMAIL_REQUIRED_MSG = "Email is required";
    public static final String EMAIL_INVALID_MSG = "Invalid email format";
    public static final String PASSWORD_REQUIRED_MSG = "Password is required";
    public static final String PASSWORD_LENGTH_MSG = "Password must be at least 8 characters long";
    public static final String USERNAME_REQUIRED_MSG = "Username is required";
    public static final String USERNAME_LENGTH_MSG = "Username must be between 2 to 50 characters";
    public static final String ROLES_REQUIRED_MSG = "Roles are required";

}


