package app.gcd.rest.config;
import java.util.Arrays;

import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import app.gcd.rest.constant.GCDServiceConstant;

/**
 * This class contains configuration details/beans for message queue
 * @Operations: N/A
 * @Developer: Singh, Sunny
 */

@Configuration
public class MessagingConfiguration {
    
	/**
	 * This returns beans for connection factory for Active queue. This takes in broker url for the connection
	 * 
	 */
	
	@Bean
    public ActiveMQConnectionFactory connectionFactory(){
    	System.out.println("ActiveMQConnectionFactory bean iniialized");
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(GCDServiceConstant.DEFAULT_BROKER_URL);
        connectionFactory.setTrustedPackages(Arrays.asList("app.gcd.rest"));
        return connectionFactory;
    }
     
	/**
	 * This returns bean of jmsTemplate which will be used to put the GCD object onto the queue
	 */
	
    @Bean
    public JmsTemplate jmsTemplate(){
    	System.out.println("JmsTemplate bean created ");
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory());
        template.setDefaultDestinationName(GCDServiceConstant.GCD_QUEUE);
        return template;
    }
   
}
