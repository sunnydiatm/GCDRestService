package app.gcd.rest.implementation;

import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import app.gcd.rest.constant.GCDServiceConstant;
import app.gcd.rest.model.GCD;
import app.gcd.rest.controller.GCDRestController;
import app.gcd.rest.exception.GCDException;
import app.gcd.rest.exception.ErrorResponse;

/**
 * This class contains all methods exposed as restfull for GCD Service
 * @Operations: fetchGCDList, pushGCD
 * @Developer: Singh, Sunny
 */

@RestController
public class GCDRestServiceImpl {
	private static final Logger logger = Logger.getLogger(GCDRestServiceImpl.class.getName());
	
	@Autowired
    private GCDRestController controller;
		
	/**
	 * The following operation return list of all GCD numbers inserted to backend Database ever
	 * @Input - N/A
	 * @Response - List of GCD numbers in the order of insertion
	 * @Exception - this method returns GCDException exception 
	 */
	
	    @RequestMapping(value = "/gcdList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<List<GCD>> fetchGCDList() throws GCDException {
	    	if(logger.isLoggable(Level.INFO)){
				logger.info("GCDRestServiceImpl->fetchGCDList recevied request");
			}  
	    	/**
    		 * Calling controller for further processing  
    		 */
	    	List<GCD> list = controller.fetchGCDList();
	    	
	    	if(logger.isLoggable(Level.INFO)){
				logger.info("GCDRestServiceImpl->fetchGCDList controller processed the request. Going to return response");
			}  
	    	
	    	if(list.size() > 0){
	    		return new ResponseEntity<List<GCD>>(list, HttpStatus.OK);
	    	}else {
	    		logger.log(Level.SEVERE, "GCDRestServiceImpl->fetchGCDList-> Error occurred while processing request -> No details found in the Database ");
				throw new GCDException("No details found in the Database.");
	    	}
	        
	    }
	    
		/**
		 * The following operation add GCD list to the Queue and Database
		 * @Input - firstOperand, secondOperand
		 * @Response - Success/Failure response
		 * @Exception - this method returns GCDException exception
		 */
	    
		@RequestMapping(value = "/gcd/{firstOperand}/{secondOperand}", method = RequestMethod.POST, produces = MediaType.APPLICATION_XML_VALUE)
		public ResponseEntity<String> pushGCD(@PathVariable int firstOperand, @PathVariable int secondOperand) throws GCDException {
			String response = null;
			if(logger.isLoggable(Level.INFO)){
				logger.info("GCDRestServiceImpl->pushGCD recevied request");
			} 
			
			if(firstOperand >= 0 && secondOperand >= 0){
				GCD gcd = new GCD();
				gcd.setFirstOperand(firstOperand);
				gcd.setSecondOperand(secondOperand);
				response = controller.pushGCD(gcd) ;
			}else {
				logger.log(Level.SEVERE, "GCDRestServiceImpl->pushGCD-> Error occurred while processing request -> invalid input received - firstOperand : "+firstOperand+" & secondOperand : "+secondOperand);
				throw new GCDException("Invalid input received, No valid input provided.");
			}
			
			if(logger.isLoggable(Level.INFO)){
				logger.info("GCDRestServiceImpl->pushGCD controller processed the request. Going to return response");
			}
			if(response!=null && !response.equals("") && response.equals(GCDServiceConstant.SUCCESS_RESPONSE)){
				return new ResponseEntity<String>(response, HttpStatus.OK);
			}else {
				throw new GCDException("Problem encountered while calling backend systems.");
			}
		}
		
		/**
		 * The following operation handles exception thrown by GCDException
		 * @Input - Exception
		 * @Response - ResponseEntity
		 * @Exception - N/A
		 */
		
		 @ExceptionHandler(GCDException.class)
		   public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
		       ErrorResponse error = new ErrorResponse();
		       error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
		       error.setMessage(ex.getMessage());
		       return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
			 }

	}
