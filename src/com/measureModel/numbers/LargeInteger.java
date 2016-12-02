package com.measureModel.numbers;

import java.math.BigInteger;

import com.developmentExceptions.exceptions.ShouldImplementException;
import com.measureModel.arithmeticObjects.ArithmeticObject;
import com.measureModel.measurements.Measure;
import com.measureModel.measurements.MeasureBag;
import com.measureModel.numbers.exceptions.DivisionByZeroException;
import com.measureModel.units.NullUnit;
import com.measureModel.units.UnitBehavior;

/**
 * This class models a Large Integer. 
 * 
 * @author jressia
 *
 */
public class LargeInteger extends IntegerNumber {
	
	private BigInteger number;
	
	// Class Methods -------------------------------------
	public LargeInteger(BigInteger aNumber) {
		number = aNumber;
	}
	
	public LargeInteger(int anInt) {
		number = new BigInteger(String.valueOf(anInt));
	}
	
	public LargeInteger(long aLong) {
		number = new BigInteger(String.valueOf(aLong));
	}
	
	// Instance Methods ----------------------------------

	public ArithmeticObject divideBy(ArithmeticObject anObject) {
		return anObject.divideByForNumber(this);
	}

	public boolean equals(Object anObject) {
		return anObject instanceof ExtendedNumber && ((ExtendedNumber) anObject).equalsLargeInteger(this);
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

	public double doubleValue() {
		return number.doubleValue();
	}
	
	public ArithmeticObject plus(ArithmeticObject anArithmeticObject) {
		return anArithmeticObject.plusForNumber(this);
	}

	public ArithmeticObject minusForNumber(ExtendedNumber aNumber) {
		return new LargeInteger(aNumber.intValue() - intValue());
	}

	public ArithmeticObject multiplyByForNumber(ExtendedNumber aNumber) {
		return aNumber.multiplyByLargeInteger(this);
	}

	public ArithmeticObject plusForNumber(ExtendedNumber aNumber) {
		return aNumber.plusForLargeInteger(this);
	}

	public ArithmeticObject plusForSmallInteger(SmallInteger aSmallInteger) {
		return new LargeInteger(number.add(new BigInteger(aSmallInteger.toString())));
	}
	
	/**
	 * TODO: Check what happens when the representation goes out of scale.
	 */
	public ArithmeticObject plusForFloatNumber(FloatNumber aFloatNumber) {
		return (new FloatNumber((new Float(this.toString())))).plusForFloatNumber(aFloatNumber);
	}

	public ArithmeticObject plusForLargeInteger(LargeInteger aLargeInteger) {
		return new LargeInteger(number.add(aLargeInteger.number));
	}

	public ArithmeticObject plusForFraction(Fraction aFraction) {
		return aFraction.plusForLargeInteger(this);
	}

	public ArithmeticObject minusForFloatNumber(FloatNumber aFloatNumber) {
		return aFloatNumber.minusLargeInteger(this);
	}

	public ArithmeticObject minusForSmallInteger(SmallInteger aSmallInteger) {
		BigInteger aBigInteger = new BigInteger(aSmallInteger.toString());
		return new LargeInteger(aBigInteger.subtract(number));
	}

	public ArithmeticObject minusForLargeInteger(LargeInteger aLargeInteger) {
		return new LargeInteger(aLargeInteger.number.subtract(number));
	}

	public ArithmeticObject minusSmallInteger(SmallInteger aSmallInteger) {
		BigInteger aBigInteger = new BigInteger(aSmallInteger.toString());
		return new LargeInteger(number.subtract(aBigInteger));
	}

	public ArithmeticObject minusFloatNumber(FloatNumber aFloatNumber) {
		/**
		 * TODO: this does not contemplate the overflow case
		 */
		return new FloatNumber(new Double(number.doubleValue() - aFloatNumber.floatValue()));
	}

	public ArithmeticObject multiplyByFloatNumber(FloatNumber aFloatNumber) {
		/**
		 * TODO: this does not contemplate the overflow case
		 */
		return new FloatNumber(new Double(number.doubleValue() * aFloatNumber.floatValue())); 
	}

	public ArithmeticObject multiplyBySmallInteger(SmallInteger aSmallInteger) {
		BigInteger aBigInteger = new BigInteger(aSmallInteger.toString());
		return new LargeInteger(number.multiply(aBigInteger));
	}

	public ArithmeticObject multiplyByLargeInteger(LargeInteger aLargeInteger) {
		return new LargeInteger(number.multiply(aLargeInteger.number));
	}

	public ArithmeticObject minusForFraction(Fraction aFraction) {
		return aFraction.minusLargeInteger(this);
	}

	public ExtendedNumber quotient(ExtendedNumber aNumber) {
		return aNumber.quotientForLargeInteger(this);
	}

	public ExtendedNumber quotientForSmallInteger(SmallInteger aSmallInteger) {
		return new FloatNumber(aSmallInteger.intValue() / number.floatValue());
	}

	public boolean equalsSmallInteger(SmallInteger aSmallInteger) {
		return this.equalsLargeInteger(aSmallInteger.asLargeInteger());
	}

	public boolean equalsLargeInteger(LargeInteger aLargeInteger) {
		return number.equals(aLargeInteger.number);
	}
	
	public boolean equalsFloatNumber(FloatNumber aFloatNumber) {
		return aFloatNumber.equalsLargeInteger(this);
	}

	/**
	 * TODO: Fix might loose pressition
	 * @return
	 */
	public FloatNumber asFloatNumber() {
		return new FloatNumber(new Float(number.floatValue()));
	}

	public SmallInteger asSmallInteger() {
		return new SmallInteger(number.intValue());
	}

	public LargeInteger asLargeInteger() {
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
		return new LargeInteger(number.abs());
	}

	public boolean isNegative() {
		return number.signum() == -1;
	}

	public boolean isPositive() {
		return number.signum() == 1;
		
	}

	public ArithmeticObject negated() {
		return new LargeInteger(number.negate());
	}

	public ArithmeticObject truncated() {
		return new LargeInteger(number.intValue());
	}

}
