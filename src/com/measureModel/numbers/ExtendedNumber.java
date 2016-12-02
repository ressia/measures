package com.measureModel.numbers;

import com.developmentExceptions.exceptions.SubclassResponsibilityException;
import com.measureModel.arithmeticObjects.ArithmeticObject;
import com.measureModel.measurements.Measure;
import com.measureModel.measurements.MeasureBag;
import com.measureModel.units.NullUnit;
import com.measureModel.units.UnitBehavior;

/**
 * This class models a Number. It was created to avoid the 
 * problems in the default java Number abstraction. 
 * 
 * We use the word extended to denote that is an abstraction
 * extended with the messages the previous model did not have.
 * Since this abstraction is outside the java base library we
 * can further extend it at will. 
 * 
 * @author jressia
 *
 */
public abstract class ExtendedNumber extends ArithmeticObject {
	
	// Class Methods -------------------------------------
	
	// Instance Methods ----------------------------------
	public ArithmeticObject denominator() {
		return new SmallInteger(1);
	}
	
	public ArithmeticObject numerator() {
		return this;
	}

	public ArithmeticObject divideBy(ArithmeticObject anObject) {
		return anObject.divideByForNumber(this);
	}
	
	public ArithmeticObject divideByForNumber(ExtendedNumber aNumber) {
		return Fraction.numberNumber(aNumber, this);
	}

	public ArithmeticObject divideByForUnit(UnitBehavior aUnit) {
		return aUnit.with(new Fraction(1,this));
	}

	public String toString() {
		throw new SubclassResponsibilityException();
	}

	public ArithmeticObject multiplyBy(ArithmeticObject anObject) {
		return anObject.multiplyByForNumber(this);
	}

	public ArithmeticObject multiplyByForUnit(UnitBehavior aUnit) {
		return aUnit.with(this);
	}

	public ArithmeticObject with(ExtendedNumber aNumber) {
		throw new SubclassResponsibilityException();
	}

	public UnitBehavior unit() {
		return baseUnit();
	}

	public UnitBehavior baseUnit() {
		return NullUnit.newInstance();
	}

	public int intValue() {
		throw new SubclassResponsibilityException();
	}

	public ArithmeticObject divideByFraction(Fraction aFraction) {
		return this.multiplyBy(aFraction.denominator()).divideBy(aFraction.numerator());
	}

	public ArithmeticObject plus(ArithmeticObject anObject) {
		return anObject.plusForNumber(this);
	}
	
	public ArithmeticObject plusMeasure(ArithmeticObject aMeasure) {
		return aMeasure.plusForNumber(this);
	}
	
	public ArithmeticObject plusMeasureBag(MeasureBag aMeasureBag) {
		return aMeasureBag.plusForNumber(this);
	}

	public abstract ArithmeticObject plusForSmallInteger(SmallInteger aSmallInteger);

	public abstract ArithmeticObject plusForFloatNumber(FloatNumber aFloatNumber);

	public abstract ArithmeticObject plusForLargeInteger(LargeInteger aLargeInteger);

	public abstract ArithmeticObject plusForFraction(Fraction aFraction);

	public abstract ArithmeticObject minusForFloatNumber(FloatNumber aFloatNumber);

	public abstract ArithmeticObject minusForSmallInteger(SmallInteger aSmallInteger);

	public abstract ArithmeticObject minusForLargeInteger(LargeInteger aLargeInteger);
	
	public abstract ArithmeticObject minusForFraction(Fraction aFraction);

	public abstract ArithmeticObject multiplyByFloatNumber(FloatNumber aFloatNumber);

	public abstract ArithmeticObject multiplyBySmallInteger(SmallInteger aSmallInteger);

	public abstract ArithmeticObject multiplyByLargeInteger(LargeInteger aLargeInteger);
	
	public ArithmeticObject divideByForMeasure(Measure aMeasure) {
		return aMeasure.divideByNumber(this);
	}

	public abstract ExtendedNumber quotient(ExtendedNumber aNumber);
	
	public abstract ExtendedNumber quotientForSmallInteger(SmallInteger aSmallInteger);
	
	public abstract ExtendedNumber quotientForLargeInteger(LargeInteger largeInteger);
	
	public abstract ExtendedNumber quotientForFloatNumber(FloatNumber aFloatNumber);
	
	public abstract ExtendedNumber quotientForFraction(Fraction aFraction);

	public boolean isZero() {
		return false;
	}
	
	public boolean isNothing() {
		return isZero();
	}
	
	public boolean isOne() {
		return false;
	}

	public boolean equalsFraction(Fraction aFraction) {
		return aFraction.equals(asFraction());
	}
	
	public abstract boolean equalsSmallInteger(SmallInteger aSmallInteger);
	
	public abstract boolean equalsLargeInteger(LargeInteger aLargeInteger);
	
	public abstract boolean equalsFloatNumber(FloatNumber aFloatNumber);

	public Fraction asFraction() {
		return new Fraction(this,new SmallInteger(1));
	}

	public abstract SmallInteger asSmallInteger();
	
	public abstract LargeInteger asLargeInteger();
	
	public abstract FloatNumber asFloatNumber();
	
	public ArithmeticObject reciprocal() {
		if (isZero() || isOne() ) {
			return this;
		}
		return Fraction.numberNumber(new SmallInteger(1),this);
	}
	
	public boolean equalsMeasure(Measure aMeasure) {
		return isNothing() && aMeasure.isNothing();
	}
	
	public ArithmeticObject amount() {
		return this;
	}
	
	/**
	 * Magnitude Protocol
	 */
	
	public boolean greaterThan(ArithmeticObject anArithmeticObject) {
		return anArithmeticObject.greaterThanForNumber(this);
	}
	
	public boolean greaterThanForNumber(ExtendedNumber anExtendedNumber) {
		return anExtendedNumber.minus(this).isPositive();
	}
	
	public boolean greaterThanForMeasure(Measure aMeasure) {
		if (isNothing()) {
			return aMeasure.greaterThan(aMeasure.unit().nullMeasure());
		} else {
			return aMeasure.convertTo(baseUnit()).amount().greaterThan(this);
		}
	}
	
	public boolean greaterThanOrEqualTo(ArithmeticObject anArithmeticObject) {
		return anArithmeticObject.greaterThanOrEqualToForNumber(this);
	}

	public boolean greaterThanOrEqualToForNumber(ExtendedNumber anExtendedNumber) {
		return anExtendedNumber.equals(this) || this.greaterThanForNumber(anExtendedNumber);
	}
	
	public boolean lessThan(ArithmeticObject anArithmeticObject) {
		return anArithmeticObject.lessThanForNumber(this);
	}
	
	public boolean lessThanForNumber(ExtendedNumber anExtendedNumber) {
		return anExtendedNumber.minus(this).isNegative();
	}
	
	public boolean lessThanForMeasure(Measure aMeasure) {
		if (isNothing()) {
			return aMeasure.lessThan(aMeasure.unit().nullMeasure());
		} else {
			return aMeasure.convertTo(baseUnit()).amount().lessThan(this);
		}
	}

	public ArithmeticObject multiplyByForMeasureBag(MeasureBag aMeasureBag) {
		return aMeasureBag.multiplyByForNumber(this);
	}
	
	public ArithmeticObject multiplyByForMeasure(Measure aMeasure) {
		return aMeasure.multiplyByForNumber(this);
	}
	
	public ArithmeticObject divideByForMeasureBag(MeasureBag aMeasureBag) {
		return aMeasureBag.divideByNumber(this);
	}
	
	public ArithmeticObject convertToBaseUnit() {
		return this;
	}


}
