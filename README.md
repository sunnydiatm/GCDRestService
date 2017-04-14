# GCDRestService
This is a Restful service for inserting GCD values in JMS Queue

The RESTful service expose two methods:

public String push(int i1, int i2);

which returns the status of the request to the caller as a String. The two parameters will be added to a JMS queue.

public List<Integer> list();

which returns a list of all the elements ever added to the queue from a database in the order added as a JSON structure.
