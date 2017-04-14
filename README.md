# GCDRestService
This is a Restful service for inserting GCD values in JMS Queue

The RESTful service expose two methods:

public String push(int i1, int i2);

which returns the status of the request to the caller as a String. The two parameters will be added to a JMS queue.

public List<Integer> list();

which returns a list of all the elements ever added to the queue from a database in the order added as a JSON structure.

Pre requisites

1) Connection details such as DB Connection Url, username, password should be updated in GCDServiceConstant

2) Create a table in MySql Database with following command.

CREATE TABLE  sakila.GCD_TABLE 
	(FIRST_OPERAND INT(100)  NOT NULL,
	SECOND_OPERAND INT(100) NOT NULL,
    INSERT_DATE datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
	);
  
3) ActiveMQ details needs to be updated in GCDServiceConstant e.g DEFAULT_BROKER_URL - tcp://localhost:61616
4) ActiveMQ should be up and running before making call to GCDRestService operations
