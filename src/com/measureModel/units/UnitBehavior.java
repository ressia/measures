package com.measureModel.units;

import com.developmentExceptions.exceptions.ShouldImplementException;
import com.developmentExceptions.exceptions.ShouldNotImplementException;
import com.developmentExceptions.exceptions.SubclassResponsibilityException;
import com.measureModel.arithmeticObjects.ArithmeticObject;
import com.measureModel.measurements.Measure;
import com.measureModel.numbers.ExtendedNumber;
import com.measureModel.numbers.Fraction;
import com.measureModel.numbers.SmallInteger;

public abstract class UnitBehavior extends ArithmeticObject {
	
	// Class methods ------------------------------------
	
	public ArithmeticObject numerator() {
		return this;
	}
	
	public UnitBehavior reciprocal() {
		return (UnitBehavior)denominator().divideBy(numerator());
	}

	public static String nameForManyFrom(String aString) {
		StringBuffer aStringBuffer = new StringBuffer(aString);
		aStringBuffer.append(nameForManyEndingString());
		return aStringBuffer.toString();
	}
	
	public static String nameForManyEndingString() {
		return "s";
	}

	public static String defaultSign() {
		return "NOSIGN";
	}
	
	// Instance Methods -------------------------------
	
	public ArithmeticObject divideBy(ArithmeticObject aNumberOrUnit) {
		return aNumberOrUnit.divideByForUnit(this);
	}
	
	public ArithmeticObject divideByForMeasure(Measure aMeasure) {
		return NullUnit.newInstance().divideBy(this).multiplyBy(aMeasure.unit()).with(aMeasure.amount());
	}
	
	public ArithmeticObject multiplyByForMeasure(Measure aMeasure) {
		return aMeasure.unit().multiplyBy(this).with(aMeasure.amount());
	}

	public ArithmeticObject denominator() {
		return NullUnit.newInstance();
	}
	
	public ArithmeticObject minus(ArithmeticObject anObject) {
		throw new ShouldNotImplementException();
	}

	public ArithmeticObject plus(ArithmeticObject anObject) {
		throw new ShouldNotImplementException();
	}

	public ArithmeticObject numeratorOf(ExtendedNumber aNumber) {
		return numerator().with((ExtendedNumber)aNumber.numerator());
	}

	public ArithmeticObject denominatorOf(ExtendedNumber aNumber) {
		return denominator().with((ExtendedNumber)aNumber.denominator());
	}
	
	public abstract String nameForOne();
	
	public String name() {
		return nameForOne(); 
	}

	public abstract String nameForMany();
	
	public String nameForUndefinedAmount() {
		return nameForMany();
	}
	
	public abstract ArithmeticObject divideBySimpleUnit(SimpleUnit aSimpleUnit);

	public abstract ArithmeticObject multiplyByDividedUnit(CompoundUnit aDividedUnit);

	public abstract ArithmeticObject divideByDividedUnit(DividedUnit aDividedUnit);

	public abstract ArithmeticObject multiplyBySimpleUnit(SimpleUnit aSimpleUnit);

	public abstract ArithmeticObject divideByMultipliedUnit(MultipliedUnit aMultipliedUnit);

	public abstract ArithmeticObject with(ExtendedNumber aNumber);

	public abstract ArithmeticObject multiplyByForMultipliedUnit(MultipliedUnit aMultipliedUnit);
	
	public abstract UnitBehavior baseUnit();

	public boolean sameDomainAs(ArithmeticObject aUnit) {
		return baseUnit().equals(aUnit.baseUnit());
	}
	
	public abstract ExtendedNumber nothingAmount();

	public Measure nullMeasure() {
		return new Measure(nothingAmount(),(UnitBehavior)baseUnit());
	}

	public abstract String sign();

	public abstract ExtendedNumber convertAmountToBaseUnit(ExtendedNumber aNumber);

	public abstract Measure convertToBaseUnit(Measure measure);

	public abstract Measure convertFromBaseUnit(Measure aMeasure);

	public String nameFor(ExtendedNumber anAmount) {
		if (anAmount.abs().equals(new SmallInteger(1))) {
			return nameForOne();
		} else {
			return nameForMany();
		}
	}

}
