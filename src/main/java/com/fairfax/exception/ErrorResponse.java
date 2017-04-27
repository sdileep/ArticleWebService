package com.fairfax.exception;

/**
 * Class to encapsulate error responses to clien
 * @author dileep
 *
 */
public class ErrorResponse {
	private int errorCode;
    private String errorMessage;
    
    public int getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
