package com.measureModel.numbers;

import java.math.BigInteger;

import com.measureModel.arithmeticObjects.ArithmeticObject;
import com.measureModel.measurements.Measure;
import com.measureModel.units.UnitBehavior;

/**
 * This class models a Small Integer. 
 * 
 * @author jressia
 *
 */
public class SmallInteger extends IntegerNumber {
	
	private Number number;
	
	// Class Methods -------------------------------------
	public SmallInteger(Number aNumber) {
		number = new Integer(aNumber.intValue());
	}
	
	public SmallInteger(int anInt) {
		number = new Integer(anInt);
	}
	
	public SmallInteger(long aLong) {
		number = new Long(aLong);
	}
	
	// Instance Methods ----------------------------------

	public ArithmeticObject divideByForUnit(UnitBehavior aUnit) {
		return aUnit.with(new Fraction(1,this));
	}

	public boolean equals(Object anObject) {
		return anObject instanceof ExtendedNumber && ((ExtendedNumber) anObject).equalsSmallInteger(this);
	}
	
	public int hashCode() {
		return number.hashCode();
	}

	public String toString() {
		return number.toString();
	}

	public ArithmeticObject multiplyBy(ArithmeticObject anObject) {
		return anObject.multiplyByForNumber(this);
	}

	public ArithmeticObject multiplyByForUnit(UnitBehavior aUnit) {
		return aUnit.with(this);
	}

	public int intValue() {
		return number.intValue();
	}

	public ArithmeticObject multiplyByForNumber(SmallInteger aNumber) {
		return new SmallInteger(number.intValue() * aNumber.intValue());
	}

	public ArithmeticObject plus(ArithmeticObject anArithmeticObject) {
		return anArithmeticObject.plusForNumber(this);
	}

	public ArithmeticObject minusForNumber(SmallInteger aNumber) {
		return new SmallInteger(aNumber.intValue() - intValue());
	}

	public ArithmeticObject minusForNumber(ExtendedNumber aNumber) {
		return new SmallInteger(aNumber.intValue() - intValue());
	}

	public ArithmeticObject multiplyByForNumber(ExtendedNumber aNumber) {
		return aNumber.multiplyBySmallInteger(this);
	}

	public ArithmeticObject plusForNumber(ExtendedNumber aNumber) {
		return aNumber.plusForSmallInteger(this);
	}

	public ArithmeticObject plusForSmallInteger(SmallInteger aSmallInteger) {
		return new SmallInteger(number.intValue() + aSmallInteger.intValue());
	}

	public ArithmeticObject plusForFloatNumber(FloatNumber aFloatNumber) {
		return new FloatNumber(number.floatValue() + aFloatNumber.floatValue());
	}

	public ArithmeticObject plusForLargeInteger(LargeInteger aLargeInteger) {
		return aLargeInteger.plusForSmallInteger(this);
	}

	public ArithmeticObject minusForFloatNumber(FloatNumber aFloatNumber) {
		return new FloatNumber(aFloatNumber.floatValue() - intValue());
	}

	public ArithmeticObject minusForSmallInteger(SmallInteger aSmallInteger) {
		return new SmallInteger(aSmallInteger.intValue() - intValue());
	}

	public ArithmeticObject plusForFraction(Fraction aFraction) {
		ExtendedNumber numerator = (ExtendedNumber)((ExtendedNumber)aFraction.denominator()).multiplyBySmallInteger(this).plus(aFraction.numerator());
		return Fraction.numberNumber(numerator, (ExtendedNumber)aFraction.denominator());
	}

	public ArithmeticObject minusForLargeInteger(LargeInteger aLargeInteger) {
		return aLargeInteger.minusSmallInteger(this);
	}

	public ArithmeticObject multiplyByFloatNumber(FloatNumber aFloatNumber) {
		return new FloatNumber(aFloatNumber.floatValue() * intValue());
	}

	public ArithmeticObject multiplyBySmallInteger(SmallInteger aSmallInteger) {
		return new SmallInteger(intValue() * aSmallInteger.intValue());
	}

	public ArithmeticObject multiplyByLargeInteger(LargeInteger aLargeInteger) {
		return aLargeInteger.multiplyBySmallInteger(this);
	}

	public ExtendedNumber quotient(ExtendedNumber aNumber) {
		return aNumber.quotientForSmallInteger(this);
	}

	public ExtendedNumber quotientForSmallInteger(SmallInteger aSmallInteger) {
		return new FloatNumber(aSmallInteger.intValue() / (float)number.intValue());
	}

	public ArithmeticObject minusForFraction(Fraction aFraction) {
		return aFraction.minusSmallInteger(this);
	}

	public boolean equalsSmallInteger(SmallInteger aSmallInteger) {
		return intValue() == aSmallInteger.intValue();
	}

	public LargeInteger asLargeInteger() {
		return new LargeInteger(intValue());
	}

	public FloatNumber asFloatNumber() {
		return new FloatNumber(intValue());
	}

	public boolean equalsLargeInteger(LargeInteger aLargeInteger) {
		return aLargeInteger.equalsSmallInteger(this);
	}

	public boolean equalsFloatNumber(FloatNumber aFloatNumber) {
		return aFloatNumber.equalsSmallInteger(this);
	}

	public SmallInteger asSmallInteger() {
		return this;
	}

	public ExtendedNumber quotientForLargeInteger(LargeInteger aLargeInteger) {
		return new FloatNumber(aLargeInteger.intValue() / number.floatValue());
	}

	public ExtendedNumber quotientForFloatNumber(FloatNumber aFloatNumber) {
		return new FloatNumber(aFloatNumber.floatValue() / number.floatValue());
	}

	public ExtendedNumber quotientForFraction(Fraction aFraction) {
		return new FloatNumber(aFraction.asFloatNumber().floatValue() / number.floatValue());
	}

	public ArithmeticObject abs() {
		BigInteger anInteger = new BigInteger(number.toString());
		return new SmallInteger(anInteger.abs().intValue());
		
		//return new SmallInteger(number .intValue());
	}

	public ArithmeticObject multiplyByForMeasure(Measure aMeasure) {
		return new Measure((ExtendedNumber)this.multiplyBy(aMeasure.amount()),aMeasure.unit());
	}

	public boolean isNegative() {
		return intValue() < 0;
	}

	public boolean isPositive() {
		return intValue() > 0;
	}

	public ArithmeticObject negated() {
		return new SmallInteger(number.intValue() * -1);
	}

	public ArithmeticObject truncated() {
		return new SmallInteger(number.intValue());
	}
}
