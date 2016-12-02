package com.measureModel.numbers;

import com.measureModel.arithmeticObjects.ArithmeticObject;
import com.measureModel.units.NullUnit;
import com.measureModel.units.UnitBehavior;


/**
 * This class models an Integer Number. 
 * 
 * @author jressia
 *
 */
public abstract class IntegerNumber extends ExtendedNumber {
	
	// Class Methods -------------------------------------
	
	// Instance Methods ----------------------------------
	public ArithmeticObject divideByForUnit(UnitBehavior aUnit) {
		return aUnit.with(new Fraction(1,this));
	}
	
	public UnitBehavior unit() {
		return NullUnit.newInstance();
	}
	
	public boolean isZero() {
		return intValue() == 0;
	}
	
	public boolean isOne() {
		return intValue() == 1;
	}
	


}
