package app.gcd.rest.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import java.lang.Exception;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import app.gcd.rest.dao.IGCDRestDAO;
import app.gcd.rest.constant.GCDServiceConstant;
import app.gcd.rest.exception.GCDException;

import app.gcd.rest.model.GCD;

/**
 * This class contains all methods calling database in the backend
 * @Operations: fetchGCDListFromDB, insertGCDIntoDB
 * @Developer: Singh, Sunny
 */

public class GCDRestDAOImpl implements IGCDRestDAO {
	
	private static final Logger logger = Logger.getLogger(GCDRestDAOImpl.class.getName());
	
	private JdbcTemplate jdbcTemplate;
	 
    public GCDRestDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    
    /**
	 * The following operation return list of all GCD numbers inserted to backend Database ever
	 * @Input - N/A
	 * @Response - List of GCD numbers in the order of insertion
	 * @Exception - this method returns exception inside service context
	 */
    
	public List<GCD> fetchGCDListFromDB()throws GCDException{
		
		if(logger.isLoggable(Level.INFO)){
			logger.info("GCDRestDAOImpl->fetchGCDListFromDB request");
		}
		List<GCD> gcdList = null;
		String queryString = GCDServiceConstant.FETCH_GCD_LIST_QUERY;
		
		if(logger.isLoggable(Level.FINER)){
			logger.finer("GCDRestDAOImpl->fetchGCDListFromDB-> QueryString = " +queryString);
		}
		
		/**
		 * Fetching GCD list from the database with jdbcTemplate  
		 */
		try{
			 gcdList = jdbcTemplate.query(queryString, new RowMapper<GCD>() {
		 
		        @Override
		        public GCD mapRow(ResultSet rs, int rowNum) throws SQLException {
		        	GCD gcd = new GCD();
		 
		        	gcd.setFirstOperand(rs.getInt("FIRST_OPERAND"));
		        	gcd.setSecondOperand(rs.getInt("SECOND_OPERAND"));
		        	
		            return gcd;
		        }
		 
		    });
		}catch(DataAccessException ex){
        	logger.log(Level.SEVERE, "GCDRestDAOImpl->fetchGCDListFromDB-> Data Access Exception occured while calling the Database update " +ex.getMessage());
        	throw new GCDException(GCDServiceConstant.DATA_ACCESS_EXCEPTION);
        }catch(Exception ex) {
        	logger.log(Level.SEVERE, "GCDRestDAOImpl->fetchGCDListFromDB-> Generic Exception occured while calling the Database update "+ex.getMessage());
			throw new GCDException(GCDServiceConstant.GENERIC_EXCEPTION);
		}
		
		 if(logger.isLoggable(Level.INFO)){
				logger.info("GCDRestDAOImpl->fetchGCDListFromDB returning response");
			}
	 
	    return gcdList;
	}
	
	
	/**
	 * The following operation insert the GCD into Database 
	 * @Input - gcd
	 * @Response - Failure/Success message
	 * @Exception - this method returns exception inside service context
	 */	
	
	
	public String insertGCDIntoDB(GCD gcd) throws GCDException {
		
		if(logger.isLoggable(Level.INFO)){
			logger.info("GCDRestDAOImpl->insertGCDIntoDB request");
		}
		
        String queryString = GCDServiceConstant.INSERT_GCD_DETAILS_QUERY;
        int result = 0;
                
        if(logger.isLoggable(Level.FINER)){
			logger.finer("GCDRestDAOImpl->insertGCDIntoDB-> QueryString = " +queryString +" firstOperand= "+gcd.getFirstOperand()+" & secondOperand= "+ gcd.getSecondOperand());
		}
        
        /**
		 * Inserting GCD number in the database with jdbcTemplate  
		**/
        
        try{
        	 result = jdbcTemplate.update(queryString, gcd.getFirstOperand(), gcd.getSecondOperand());
        }catch(DataAccessException ex){
        	logger.log(Level.SEVERE, "GCDRestDAOImpl->insertGCDIntoDB-> Data Access Exception occured while calling the Database update "+ex.getMessage());
        	throw new GCDException(GCDServiceConstant.DATA_ACCESS_EXCEPTION);
        }catch(Exception ex) {
        	logger.log(Level.SEVERE, "GCDRestDAOImpl->insertGCDIntoDB-> Generic Exception occured while calling the Database update "+ex.getMessage());
			throw new GCDException(GCDServiceConstant.GENERIC_EXCEPTION);
		}
        
        if(logger.isLoggable(Level.FINER)){
    		logger.finer("GCDRestDAOImpl->insertGCDIntoDB-> Result= "+result);
    	}
        
        if(logger.isLoggable(Level.INFO)){
			logger.info("GCDRestDAOImpl->insertGCDIntoDB returning response");
		}
        
		if(result == 1){
			return GCDServiceConstant.SUCCESS_RESPONSE;
		}else{
			return GCDServiceConstant.FAILURE_RESPONSE;
		}
		
	}

}
