package com.lernopus.lernopus.util.api;

import com.lernopus.lernopus.exception.BadRequestException;
import com.lernopus.lernopus.util.AppConstants;


public class LaApiUtils {

	public static void validatePageNumberAndSize(int page, int size) {
        if(page < 0) {
            throw new BadRequestException("Page number cannot be less than zero.");
        }

        if(size > AppConstants.MAX_PAGE_SIZE) {
            throw new BadRequestException("Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE);
        }
    }


}
