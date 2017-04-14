package app.gcd.rest.exception;

/**
 * This class contains Error Response for GCD Service
 * @Operations: N/A
 * @Developer: Singh, Sunny
 */


public class ErrorResponse {

	  private int errorCode;
	  private String message;

	  public int getErrorCode() {
	      return errorCode;
	  }

	  public void setErrorCode(int errorCode) {
	      this.errorCode = errorCode;
	  }

	  public String getMessage() {
	      return message;
	  }

	  public void setMessage(String message) {
	      this.message = message;
	  }

}
