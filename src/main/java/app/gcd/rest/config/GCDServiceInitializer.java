package app.gcd.rest.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * This class contains initialization of the configuration classes.
 * @Operations: N/A
 * @Developer: Singh, Sunny
 */


public class GCDServiceInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

			@Override
		    protected Class<?>[] getRootConfigClasses() {
			 System.out.println("getRootConfigClasses() class called");
		        return new Class[] { GCDServiceConfiguration.class };
		    }
		  
		    @Override
		    protected Class<?>[] getServletConfigClasses() {
		        return null;
		    }
		  
		    @Override
		    protected String[] getServletMappings() {
		        return new String[] { "/" };
		    }
	
}
