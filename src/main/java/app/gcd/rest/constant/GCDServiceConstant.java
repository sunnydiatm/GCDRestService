package app.gcd.rest.constant;

/**
 * This class contains configuration details constant.
 * @Operations: N/A
 * @Developer: Singh, Sunny
 */

public class GCDServiceConstant {

	//Database query
	public static final String  FETCH_GCD_LIST_QUERY = " SELECT FIRST_OPERAND, SECOND_OPERAND FROM SAKILA.GCD_TABLE ORDER BY INSERT_DATE";
	public static final String  INSERT_GCD_DETAILS_QUERY = " INSERT INTO SAKILA.GCD_TABLE (FIRST_OPERAND, SECOND_OPERAND) VALUES (?, ?) ";
	
	
	//Active Queue Configuration details
	public static final String DEFAULT_BROKER_URL = "tcp://localhost:61616";
	public static final String GCD_QUEUE = "GCD_QUEUE";
	
	//DB Configuration details
	public static final String DB_DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
	public static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/Mysql";
	public static final String DB_CONNECTION_USERNAME = "root";
	public static final String DB_CONNECTION_PASSWORD = "P@55word";
	
	//Exception details
	public static final String DATA_ACCESS_EXCEPTION = "Data Access Exception Occured while executing query";
	public static final String GENERIC_EXCEPTION = "Generic Exception Occured while executing query";
	public static final String DATA_OBJECT_UNAVAILABLE_EXCEPTION = "Data Access object not available for processing";
	public static final String JMS_QUEUE_UNAVAILABLE_EXCEPTION = "Exception occured while calling JMS Queue ";
	
	public static final String SUCCESS_RESPONSE = "SUCCESS";
	public static final String FAILURE_RESPONSE = "FAILURE";

}
