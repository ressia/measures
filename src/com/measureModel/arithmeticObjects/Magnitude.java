package com.measureModel.arithmeticObjects;


public interface Magnitude {
	
	public boolean greaterThan(ArithmeticObject anArithmeticObject);
	
	public boolean greaterThanOrEqualTo(ArithmeticObject anArithmeticObject);
	
	public boolean lessThan(ArithmeticObject anArithmeticObject);
	
	public boolean lessThanOrEqualTo(ArithmeticObject anArithmeticObject);
	
	public boolean between(ArithmeticObject minimun, ArithmeticObject maximun);
	
	public boolean betweenAndNotInclusive(ArithmeticObject minimun, ArithmeticObject maximun);
	
	public boolean notInclusiveBetween(ArithmeticObject minimun, ArithmeticObject maximun);

}
