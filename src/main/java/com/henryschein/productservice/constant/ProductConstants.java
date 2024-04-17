package com.henryschein.productservice.constant;

import com.henryschein.productservice.errorcodes.ErrorCodes;

public class ProductConstants {
	public static final int MIN_NAME_LENGTH = 2;
    public static final int MAX_NAME_LENGTH = 100;
    public static final int MAX_DESCRIPTION_LENGTH = 255;
    public static final String DEFAULT_PAGE_SIZE = "10";
    public static final String PRODUCT_CREATED = "Product created ";
    public static final String PRODUCT_FETCHING = "Fetching all products";

    
    //Error Codes
    public static final String PRODUCT_NOT_FOUND = ErrorCodes.PRODUCT_NOT_FOUND.name();
    public static final String INVALID_PRODUCT_NAME = ErrorCodes.INVALID_PRODUCT_NAME.name();
    public static final String INVALID_PRODUCT_PRICE = ErrorCodes.INVALID_PRODUCT_PRICE.name();
    public static final String INVALID_PRODUCT_DESCRIPTION = ErrorCodes.INVALID_PRODUCT_DESCRIPTION.name();

}
