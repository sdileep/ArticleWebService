package com.fairfax.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fairfax.constants.ErrorConstants;

/**
 * Generic exception handler for the service
 * @author dileep
 *
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	/**
	 * Method to handle illegal agrument/state exceptions
	 * @param exception
	 * @param request
	 * @return
	 */
	@ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
	protected ResponseEntity<ErrorResponse> handleConflict(RuntimeException exception, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse();
		
		errorResponse.setErrorCode(HttpStatus.CONFLICT.value());
		errorResponse.setErrorMessage("Please check the input/s provided.");
		
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.OK);
	}

	/**
	 * Method to handle custom resource not found exception
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleUserNotFoundException(Exception exception) {
		ErrorResponse errorResponse = new ErrorResponse();
		
		errorResponse.setErrorCode(HttpStatus.NOT_FOUND.value());
		errorResponse.setErrorMessage(exception.getMessage());
		
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.OK);
	}

	/**
	 * Method to handle generic exception
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGenericException(Exception exception) {
		ErrorResponse errorResponse = new ErrorResponse();

		errorResponse.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorResponse.setErrorMessage(ErrorConstants.INTERNAL_SERVER_ERROR_MESSAGE);

		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.OK);
	}
}
