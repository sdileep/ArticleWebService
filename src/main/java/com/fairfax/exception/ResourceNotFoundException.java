package com.fairfax.exception;

/**
 * Class to denote resource (example article, tag, etc.,) not found exception
 * @author dileep
 *
 */
public class ResourceNotFoundException extends Exception {
	private static final long serialVersionUID = 1055811451532655686L;
	private String errorMessage;
	 
    public ResourceNotFoundException() {
        super();
    }
 
    public ResourceNotFoundException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
 
    public String getErrorMessage() {
        return errorMessage;
    }
 
}
