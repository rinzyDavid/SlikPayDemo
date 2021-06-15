package com.slikpay.dataManager;

import java.io.IOException;
import java.net.SocketTimeoutException;

import retrofit2.HttpException;

/**
 * Handles all network related errors and translates it to Human readable string
 *
 */
public class ApiException {

    private Throwable throwable;

    public ApiException(Throwable throwable){
        this.throwable = throwable;
    }

    private String handleError(){

        if(throwable == null)
            return "";

        if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException)throwable;
            int statusCode = httpException.code();
                return getErrorStatusMessage(statusCode);
        } else if (throwable instanceof SocketTimeoutException) {
            return "Network time out, please try again";
        } else if (throwable instanceof IOException) {

            return "No network connection, please check your network";
        }

        return throwable.getLocalizedMessage();
    }

    private String getErrorStatusMessage(int code){
        switch (code) {
            case 403:
                return "Forbidden request, try again";
            case 404:
                return "Resource not found, try again later";
            case 500:
            case 502:
                return "Sorry we encountered a server error, we are fixing it";
            default:
                return "Network error occurred, try again later";
        }
    }

    public String getError(){

        return handleError();
    }
}
