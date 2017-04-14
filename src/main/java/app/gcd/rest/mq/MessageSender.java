package app.gcd.rest.mq;

import java.util.logging.Logger;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import java.util.logging.Level;
import java.util.logging.Logger;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
import app.gcd.rest.model.GCD;


/**
 * This class contains method to put the GCD object into queue.
 * @Operations: sendMessage
 * @Developer: Singh, Sunny
 */


@Component
public class MessageSender {
	
	private static final Logger logger = Logger.getLogger(MessageSender.class.getName());
	
	@Autowired
    private JmsTemplate jmsTemplate;
 
	/**
	 * The following operation return put the GCD object onto head of the GCD_QUEUE
	 * @Input - gcd 
	 * @Response - List of GCD numbers in the order of insertion
	 * @Exception - this method returns exception inside service context
	 */
	    
    public void sendMessage(final GCD gcd) {
    	
    	if(logger.isLoggable(Level.INFO)){
			logger.info("MessageSender->sendMessage-> request received  ");
		}
    	
    	/**
		 * Sending the GCD object onto the GCD_QUEUE qeueue  
		 */
    	
    	jmsTemplate.convertAndSend(gcd);
    	
    	if(logger.isLoggable(Level.INFO)){
			logger.info("MessageSender->sendMessage-> returning ");
		}
    	
    	
    }
    
}
