package app.gcd.rest.model;
import java.io.Serializable;

/**
 * This class contains GCD model which contains two property firstOperand & secondOperand
 * @Operations: N/A
 * @Developer: Singh, Sunny
 */

public class GCD implements Serializable{
	private static final long serialVersionUID = 3370367844977691546L;
	private int firstOperand;
	private int secondOperand;
	
	public GCD() {
		
	}
		
	public int getFirstOperand(){
		return firstOperand;
	}
		
	public void setFirstOperand(int firstOperand){
		this.firstOperand = firstOperand;
	}
	
	public int getSecondOperand(){
		return secondOperand;
	}
	public void setSecondOperand(int secondOperand){
		this.secondOperand = secondOperand;
	}
		
}
