package com.measureModel.measurements;

import com.developmentExceptions.exceptions.ShouldImplementException;
import com.developmentExceptions.exceptions.ShouldNotImplementException;
import com.measureModel.arithmeticObjects.ArithmeticObject;
import com.measureModel.numbers.ExtendedNumber;
import com.measureModel.units.UnitBehavior;

public abstract class MeasureBehavior extends ArithmeticObject {
	
	public boolean isZero() {
		return this.isNothing();
	}
	
	public abstract ExtendedNumber amount();
	
	public abstract UnitBehavior unit();

	public abstract boolean equalsMeasureBag(MeasureBag aMeasureBag);
	
	public ArithmeticObject minus(ArithmeticObject anArithmeticObject) {
		return anArithmeticObject.negated().plus(this);
	}
	
	public abstract ArithmeticObject convertToBaseUnit();


	public ArithmeticObject divideByForUnit(UnitBehavior aUnitBehavior) {
		throw new ShouldNotImplementException();
	}
	
	public ArithmeticObject reciprocal() {
		throw new ShouldNotImplementException();
	}
	
	public ArithmeticObject with(ExtendedNumber aNumber) {
		throw new ShouldNotImplementException();
	}
		
}
