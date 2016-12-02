package com.measureModel.numbers;

import com.measureModel.arithmeticObjects.ArithmeticObject;
import com.measureModel.numbers.exceptions.DivisionByZeroException;

/**
 * This class models a Fraction.
 * 
 * @author jressia
 * 
 */
public class Fraction extends ExtendedNumber {

	private ExtendedNumber numerator;

	private ExtendedNumber denominator;

	// Class Methods -------------------------------------
	public Fraction(ExtendedNumber aNumber, ExtendedNumber anotherNumber) {
		initializeFraction(aNumber, anotherNumber);
	}

	public Fraction(int aInt, ExtendedNumber aNumber) {
		initializeFraction(new SmallInteger(aInt), aNumber);
	}
	
	public Fraction(ExtendedNumber aNumber, int anInt) {
		initializeFraction(aNumber,new SmallInteger(anInt));
	}

	public Fraction(int aInt, int anotherInt) {
		initializeFraction(new SmallInteger(aInt), new SmallInteger(anotherInt));
	}

	private void initializeFraction(ExtendedNumber aNumber, ExtendedNumber anotherNumber) {
		validateDenominator(anotherNumber);
		numerator = aNumber;
		denominator = anotherNumber;
	}
	
	private void validateDenominator(ExtendedNumber aNumber) {
		if (aNumber.isZero()) {
			throw new DivisionByZeroException();
		}
	}

	public static ArithmeticObject numberNumber(ExtendedNumber numerator, ExtendedNumber denominator) {
		if (numerator.isZero()) {
			return new SmallInteger(0);
		} else if (denominator.isZero()) {
			throw new DivisionByZeroException();
		} else if (denominator.isOne()) {
			return numerator;
		} else if (numerator.equals(denominator)) {
			return new SmallInteger(1);
		}
		return new Fraction(numerator, denominator);
	}

	// Instance Methods ----------------------------------
	public ArithmeticObject plus(ArithmeticObject anObject) {
		return anObject.plusForNumber(this);
	}

	public ArithmeticObject denominator() {
		return denominator;
	}
	
	public ArithmeticObject numerator() {
		return numerator;
	}

	public ArithmeticObject multiplyByForNumber(ExtendedNumber aNumber) {
		return aNumber.multiplyBy(numerator).divideBy(denominator);
	}

	public boolean equals(Object anObject) {
		return anObject instanceof ExtendedNumber && ((ExtendedNumber) anObject).equalsFraction(this);
	}

	public int hashCode() {
		return numerator.hashCode() + denominator.hashCode();
	}
	
	public String toString() {
		return numerator.toString() + "/" + denominator.toString();
	}

	public ArithmeticObject minusForNumber(ExtendedNumber aNumber) {
		return new Fraction((ExtendedNumber) aNumber.multiplyBy(denominator()).minus(numerator()),
				(ExtendedNumber) denominator());
	}

	public ArithmeticObject plusForNumber(ExtendedNumber aNumber) {
		return new Fraction((ExtendedNumber) aNumber.multiplyBy(denominator()).plus(numerator()),
				(ExtendedNumber) denominator());
	}

	public ArithmeticObject plusForSmallInteger(SmallInteger smallInteger) {
		return plusForNumber(smallInteger);
	}

	public ArithmeticObject plusForFloatNumber(FloatNumber aFloatNumber) {
		return new Fraction((ExtendedNumber) aFloatNumber.multiplyBy(denominator()).plus(numerator()),
				(ExtendedNumber) denominator());
	}

	public ArithmeticObject plusForLargeInteger(LargeInteger aLargeInteger) {
		return new Fraction((ExtendedNumber) aLargeInteger.multiplyBy(denominator()).plus(numerator()),
				(ExtendedNumber) denominator());
	}

	public ArithmeticObject minusForFloatNumber(FloatNumber aFloatNumber) {
		return new Fraction((ExtendedNumber) aFloatNumber.multiplyBy(denominator()).minus(numerator()),
				(ExtendedNumber) denominator());
	}

	public ArithmeticObject minusForSmallInteger(SmallInteger aSmallInteger) {
		return new Fraction((ExtendedNumber) aSmallInteger.multiplyBy(denominator()).minus(numerator()),
				(ExtendedNumber) denominator());
	}

	/**
	 * TODO: This is not totally correct, we should use maximum divisor etc
	 */
	public ArithmeticObject plusForFraction(Fraction aFraction) {
		ExtendedNumber aDenominator = (ExtendedNumber) denominator.multiplyBy(aFraction.denominator());
		ExtendedNumber aNumeratorFactor = (ExtendedNumber) aDenominator.quotient(denominator).multiplyBy(numerator);
		ExtendedNumber anotherNumeratorFactor = (ExtendedNumber) aDenominator.quotient(aFraction.denominator)
				.multiplyBy(aFraction.numerator);
		return Fraction.numberNumber((ExtendedNumber) aNumeratorFactor.plus(anotherNumeratorFactor), aDenominator);
	}

	public ArithmeticObject minusForFraction(Fraction aFraction) {
		ExtendedNumber aDenominator = (ExtendedNumber) denominator.multiplyBy(aFraction.denominator());
		ExtendedNumber aNumeratorFactor = (ExtendedNumber) aDenominator.quotient(denominator).multiplyBy(numerator);
		ExtendedNumber anotherNumeratorFactor = (ExtendedNumber) aDenominator.quotient(aFraction.denominator)
				.multiplyBy(aFraction.numerator);
		return Fraction.numberNumber((ExtendedNumber) anotherNumeratorFactor.minus(aNumeratorFactor), aDenominator);
	}

	public ArithmeticObject minusForLargeInteger(LargeInteger aLargeInteger) {
		return new Fraction((ExtendedNumber) aLargeInteger.multiplyBy(denominator()).minus(numerator()),
				(ExtendedNumber) denominator());
	}

	public ArithmeticObject multiplyByFloatNumber(FloatNumber aFloatNumber) {
		return Fraction.numberNumber((ExtendedNumber) numerator.multiplyByFloatNumber(aFloatNumber), denominator);
	}

	public ArithmeticObject multiplyByLargeInteger(LargeInteger aLargeInteger) {
		return Fraction.numberNumber((ExtendedNumber) numerator.multiplyByLargeInteger(aLargeInteger), denominator);
	}

	public ArithmeticObject multiplyBySmallInteger(SmallInteger aSmallInteger) {
		return Fraction.numberNumber((ExtendedNumber) numerator.multiplyBySmallInteger(aSmallInteger), denominator);
	}

	public ExtendedNumber quotient(ExtendedNumber aNumber) {
		return aNumber.quotientForFraction(this);
	}

	public ExtendedNumber quotientForSmallInteger(SmallInteger aSmallInteger) {
		return new FloatNumber(aSmallInteger.intValue() / asFloatNumber().floatValue());
	}

	public ArithmeticObject minusLargeInteger(ExtendedNumber aLargeInteger) {
		return new Fraction((ExtendedNumber) numerator.minus((ExtendedNumber) aLargeInteger.multiplyBy(denominator())),
				(ExtendedNumber) denominator());
	}

	public ArithmeticObject minusSmallInteger(SmallInteger aSmallInteger) {
		return new Fraction((ExtendedNumber) numerator.minus((ExtendedNumber) aSmallInteger.multiplyBy(denominator())),
				(ExtendedNumber) denominator());
	}

	public ArithmeticObject minusFloatNumber(FloatNumber aFloatNumber) {
		return new Fraction((ExtendedNumber) numerator.minus((ExtendedNumber) aFloatNumber.multiplyBy(denominator())),
				(ExtendedNumber) denominator());
	}

	public boolean equalsFraction(Fraction aFraction) {
		return numerator.multiplyBy(aFraction.denominator()).equals(denominator.multiplyBy(aFraction.numerator()));
	}

	public ArithmeticObject reciprocal() {
		return new Fraction(denominator,numerator);
	}

	public boolean equalsSmallInteger(SmallInteger aSmallInteger) {
		return equalsFraction(aSmallInteger.asFraction());
	}

	public boolean equalsLargeInteger(LargeInteger aLargeInteger) {
		return equalsFraction(aLargeInteger.asFraction());
	}

	public boolean equalsFloatNumber(FloatNumber aFloatNumber) {
		return equalsFraction(aFloatNumber.asFraction());
	}

	public SmallInteger asSmallInteger() {
		return ((ExtendedNumber)numerator.quotient(denominator)).asSmallInteger();
	}

	public LargeInteger asLargeInteger() {
		return ((ExtendedNumber)numerator.quotient(denominator)).asLargeInteger();
	}

	public FloatNumber asFloatNumber() {
		return ((ExtendedNumber)numerator.quotient(denominator)).asFloatNumber();
	}

	public ExtendedNumber quotientForFloatNumber(FloatNumber aFloatNumber) {
		return new FloatNumber(aFloatNumber.floatValue() / asFloatNumber().floatValue()); 
	}

	public ExtendedNumber quotientForLargeInteger(LargeInteger aLargeInteger) {
		return new FloatNumber(aLargeInteger.intValue() / asFloatNumber().floatValue());
	}

	public ExtendedNumber quotientForFraction(Fraction aFraction) {
		return new FloatNumber(aFraction.asFloatNumber().floatValue() / asFloatNumber().floatValue());
	}	
	
	public boolean isZero() {
		return numerator.isZero();
	}
	
	public boolean isOne() {
		return numerator.equals(denominator);
	}

	public ArithmeticObject abs() {
		return new Fraction((ExtendedNumber)numerator.abs(),(ExtendedNumber)denominator.abs());
	}


	public boolean isNegative() {
		return denominator.quotient(numerator).isNegative();
	}

	public boolean isPositive() {
		return numerator.quotient(denominator).isPositive();
	}

	public ArithmeticObject negated() {
		return new Fraction((ExtendedNumber)numerator.negated(),denominator);
	}

	public ArithmeticObject truncated() {
		return numerator.quotient(denominator).truncated();
	}

}
