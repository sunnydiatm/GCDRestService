/**
 * 
 */
package app.gcd.rest.config;

/**
 * This class contains initialization of the configuration classes.
 * @Operations: N/A
 * @Developer: Singh, Sunny
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.view.JstlView;
import javax.sql.DataSource;
 
import app.gcd.rest.dao.IGCDRestDAO;
import app.gcd.rest.dao.GCDRestDAOImpl;
import app.gcd.rest.constant.GCDServiceConstant;


/**
 * This class contains initialization of the datasource bean.
 * @Operations: N/A
 * @Developer: Singh, Sunny
 */


@Configuration
@ComponentScan(basePackages = "app.gcd.rest")
@Import({MessagingConfiguration.class})
@EnableWebMvc
public class GCDServiceConfiguration extends WebMvcConfigurerAdapter {
	/**
	 * This returns datasource connection which is used to connect to the MySql Database.
	 */
	@Bean
    public DataSource getDataSource() {
		System.out.println("getDataSource bean created");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(GCDServiceConstant.DB_DRIVER_CLASS_NAME);
        dataSource.setUrl(GCDServiceConstant.DB_CONNECTION_URL);
        dataSource.setUsername(GCDServiceConstant.DB_CONNECTION_USERNAME);
        dataSource.setPassword(GCDServiceConstant.DB_CONNECTION_PASSWORD);
         
        return dataSource;
    }
	
	/**
	 * This is a instance of Data Access Object (DAO) bean.
	 */
	
	@Bean
    public IGCDRestDAO getGCDRestDAOImpl() {
        return new GCDRestDAOImpl(getDataSource());
    }

}
