package app.gcd.rest.dao;

import java.util.List;
import app.gcd.rest.model.GCD;
import app.gcd.rest.exception.GCDException;

public interface IGCDRestDAO {
	public List<GCD> fetchGCDListFromDB() throws GCDException;
	
	public String insertGCDIntoDB(GCD gcd) throws GCDException;

}
