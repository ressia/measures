package com.measureModel.units;

import com.developmentExceptions.exceptions.ShouldImplementException;
import com.measureModel.arithmeticObjects.ArithmeticObject;
import com.measureModel.measurements.Measure;
import com.measureModel.measurements.MeasureBag;
import com.measureModel.numbers.ExtendedNumber;
import com.measureModel.numbers.SmallInteger;

public class BaseUnit extends SimpleUnit {

	private String nameForOne;
	
	private String nameForMany;
	
	private String sign;

	// Class methods -------------------------------------------------
	
	public BaseUnit(String aNameForOne) {
		this.initialize(aNameForOne, nameForManyFrom(aNameForOne), defaultSign());
	}

	public BaseUnit(String aNameForOne, String aNameForMany) {
		this.initialize(aNameForOne, aNameForMany, defaultSign());
	}
	
	public BaseUnit(String aNameForOne, String aNameForMany,String aSign) {
		this.initialize(aNameForOne, aNameForMany, aSign);
	}

	/**
	 * Instance Methods
	 */
	
	public void initialize(String aNameForOne, String aNameForMany,String aSign) {
		nameForOne = aNameForOne;
		nameForMany = aNameForMany;
		sign = aSign;
	}
	
	public ArithmeticObject denominator() {
		return NullUnit.newInstance(); 
	}
	
	public ArithmeticObject numerator() {
		return this; 
	}

	public String nameForOne() {
		return nameForOne;
	}

	public String nameForMany() {
		return nameForMany;
	}
	
	/*public String nameFor(ExtendedNumber aNumber) {
		if ( aNumber.toString().equals("1") || aNumber.toString().equals("-1")) {
			return nameForOne;
		}
		return nameForMany;
	}*/

	public ExtendedNumber nothingAmount() {
		return new SmallInteger(0);
	}

	public Measure nullMeasure() {
		return new Measure(nothingAmount(),this);
	}

	public String sign() {
		return sign;
	}

	public ExtendedNumber convertAmountToBaseUnit(ExtendedNumber aNumber) {
		return aNumber;
	}

	public Measure convertToBaseUnit(Measure aMeasure) {
		return aMeasure;
	}

	public UnitBehavior baseUnit() {
		return this;
	}
	
	public Measure convertFromBaseUnit(Measure aMeasure) {
		return aMeasure;
	}
	
	public String toString() {
		return nameForMany;
	}

	@Override
	public ArithmeticObject abs() {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	@Override
	public ArithmeticObject amount() {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	@Override
	public ArithmeticObject convertToBaseUnit() {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	@Override
	public ArithmeticObject divideByForMeasureBag(MeasureBag measureBag) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	@Override
	public boolean equalsMeasure(Measure measure) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	@Override
	public boolean greaterThan(ArithmeticObject anArithmeticObject) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	@Override
	public boolean greaterThanForMeasure(Measure measure) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	@Override
	public boolean greaterThanForNumber(ExtendedNumber anExtendedNumber) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	@Override
	public boolean greaterThanOrEqualTo(ArithmeticObject anArithmeticObject) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	@Override
	public boolean greaterThanOrEqualToForNumber(ExtendedNumber anExtendedNumber) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	@Override
	public boolean isNegative() {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	@Override
	public boolean isNothing() {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	@Override
	public boolean isPositive() {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	@Override
	public boolean lessThan(ArithmeticObject anArithmeticObject) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	@Override
	public boolean lessThanForMeasure(Measure measure) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	@Override
	public boolean lessThanForNumber(ExtendedNumber anExtendedNumber) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	@Override
	public ArithmeticObject multiplyByForMeasureBag(MeasureBag measureBag) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	@Override
	public ArithmeticObject plusForNumber(ExtendedNumber number) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	@Override
	public ArithmeticObject plusMeasure(ArithmeticObject measure) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	@Override
	public ArithmeticObject plusMeasureBag(MeasureBag measureBag) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	@Override
	public ArithmeticObject truncated() {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	@Override
	public UnitBehavior unit() {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}
	
	public boolean equals(Object anObject) {
		if ( !(anObject instanceof BaseUnit) ) {
			return false;
		}
		BaseUnit aBaseUnit = (BaseUnit)anObject;
		return nameForOne.equals(aBaseUnit.nameForOne()) &&
			   nameForMany.equals(aBaseUnit.nameForMany()) &&
			   sign.equals(aBaseUnit.sign());
	}
	
	public int hashCode() {
		return nameForOne.hashCode() + nameForMany.hashCode() + sign.hashCode();
	}
	


}
