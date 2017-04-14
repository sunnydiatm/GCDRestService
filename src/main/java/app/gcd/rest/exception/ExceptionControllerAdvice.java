package app.gcd.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import app.gcd.rest.exception.ErrorResponse;

/**
 * This class contains Generic error response for GCD Service
 * @Operations: exceptionHandler
 * @Developer: Singh, Sunny
 */

@ControllerAdvice
public class ExceptionControllerAdvice {
	/**
	 * The following operation return generic error response to the consumer if anything 
	 * @Input - Exception
	 * @Response - ResponseEntity
	 * @Exception - N/A
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
	     ErrorResponse error = new ErrorResponse();
	     error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
	     error.setMessage("Generic Error occured, Please check if all the componet is up and running");
	     return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
	}

}
