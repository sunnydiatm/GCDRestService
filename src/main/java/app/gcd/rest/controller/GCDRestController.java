package app.gcd.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import app.gcd.rest.mq.MessageSender;
import org.springframework.stereotype.Component;
import app.gcd.rest.exception.GCDException;
import app.gcd.rest.model.GCD;
import app.gcd.rest.dao.IGCDRestDAO;
import app.gcd.rest.constant.GCDServiceConstant;

/**
 * This class contains all the logic for handling GCD applications
 * @Operations: pushGCD, fetchGCDList
 * @Developer: Singh, Sunny
 */

@Component
public class GCDRestController {
	private static final Logger logger = Logger.getLogger(GCDRestController.class.getName());
	
	@Autowired
    private MessageSender messageSender;
	
	@Autowired
    private IGCDRestDAO dao;
	
	/**
	 * The following operation fetch list of GCD number inserted into Database.
	 * @Input - N/A
	 * @Response - list of GCD
	 * @Exception - this method returns exception inside service context
	 */	
	
	public List<GCD> fetchGCDList()throws GCDException{
		
		List<GCD> list = new ArrayList<GCD>();
		if(logger.isLoggable(Level.INFO)){
			logger.info("GCDRestController->fetchGCDList request");
		} 
		
		/**
		 * Calling Database to fetch the list of GCD numbers  
		 */
    	list = dao.fetchGCDListFromDB();
    	
    	if(logger.isLoggable(Level.INFO)){
			logger.info("GCDRestController->fetchGCDList response");
		}
    	return list;
		
	}
		
	/**
	 * The following operation insert the GCD into Database as well as Queue
	 * @Input - gcd
	 * @Response - Failure/Success message
	 * @Exception - this method returns exception inside service context
	 */	
	
	public String pushGCD(GCD gcd)throws GCDException {
		String response = null;
		
		if(logger.isLoggable(Level.INFO)){
			logger.info("GCDRestController->pushGCD request");
		} 
		
		if(logger.isLoggable(Level.FINER)){
			logger.finer("GCDRestController->pushGCD->  firstOperand= "+gcd.getFirstOperand()+" & secondOperand= "+ gcd.getSecondOperand());
		}
		
		try{
			if(dao!=null){
				if(logger.isLoggable(Level.INFO)){
					logger.info("GCDRestController->pushGCD->Calling database now");
				} 
				
				/**
	    		 * Calling dataase to insert the GCD values  
	    		 */
				
				response = dao.insertGCDIntoDB(gcd);
				
				if(logger.isLoggable(Level.INFO)){
					logger.info("GCDRestController->pushGCD->Calling Database was successful ");
				}
			}else{
				logger.log(Level.SEVERE, "GCDRestController->pushGCD-> Error occurred while fetching DAO Object -> null DAO : " +dao);
				throw new GCDException(GCDServiceConstant.DATA_OBJECT_UNAVAILABLE_EXCEPTION);
			}
			
			if(messageSender!=null){
				if(logger.isLoggable(Level.INFO)){
					logger.info("GCDRestController->pushGCD->Calling Queue to put GCD Object");
				} 
				
				/**
	    		 * Calling message sender to put the GCD object into queue  
	    		 */
				
				messageSender.sendMessage(gcd);
				
				if(logger.isLoggable(Level.INFO)){
					logger.info("GCDRestController->pushGCD->Calling Queue was successful ");
				}
			}else {
				logger.log(Level.SEVERE, "GCDRestController->pushGCD-> Error occurred while calling Queue Message Sender Object -> null messageSender : " +messageSender);
				throw new GCDException(GCDServiceConstant.JMS_QUEUE_UNAVAILABLE_EXCEPTION);
			}
		}catch(Exception ex){ 
			logger.log(Level.SEVERE, "GCDRestController->pushGCD-> Generic Exception occured "+ex.getMessage());
			throw new GCDException(GCDServiceConstant.GENERIC_EXCEPTION);
		}
		
		return response;
	}
	
}
