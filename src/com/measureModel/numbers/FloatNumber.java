package com.measureModel.numbers;

import java.math.BigDecimal;

import com.measureModel.arithmeticObjects.ArithmeticObject;
import com.measureModel.units.UnitBehavior;

/**
 * This class models a Small Integer. 
 * 
 * @author jressia
 *
 */
public class FloatNumber extends ExtendedNumber {
	
	private Float number;
	
	// Class Methods -------------------------------------
	private FloatNumber() {
	}
	
	public FloatNumber(Float aFloat) {
		number = aFloat;
	}
	
	public FloatNumber(float aFloat) {
		number = new Float(aFloat);
	}
	
	public FloatNumber(double aDouble) {
		number = new Float(aDouble);
	}
	// Instance Methods ----------------------------------

	public ArithmeticObject divideByForUnit(UnitBehavior aUnit) {
		return aUnit.with(new Fraction(1,this));
	}

	public boolean equals(Object anObject) {
		return anObject instanceof ExtendedNumber && ((ExtendedNumber) anObject).equalsFloatNumber(this);
	}
	
	public int hashCode() {
		return number.hashCode();
	}

	public String toString() {
		return number.toString();
	}

	public ArithmeticObject multiplyByForUnit(UnitBehavior aUnit) {
		return aUnit.with(this);
	}

	public float floatValue() {
		return number.floatValue();
	}

	public ArithmeticObject minusForNumber(FloatNumber aNumber) {
		return new FloatNumber(aNumber.intValue() - intValue());
	}

	public ArithmeticObject multiplyByForNumber(ExtendedNumber aNumber) {
		return aNumber.multiplyByFloatNumber(this);
	}

	public ArithmeticObject plusForNumber(ExtendedNumber aNumber) {
		return aNumber.plusForFloatNumber(this);
	}

	public ArithmeticObject plusIntegerNumber(IntegerNumber anIntegerNumber) {
		return new FloatNumber(number.intValue() + anIntegerNumber.intValue());
	}
	
	public ArithmeticObject plusForSmallInteger(SmallInteger aSmallInteger) {
		return new FloatNumber(number.floatValue() + aSmallInteger.intValue());
	}

	public ArithmeticObject plusForFloatNumber(FloatNumber aFloatNumber) {
		return new FloatNumber(floatValue() + aFloatNumber.floatValue());
	}

	public ArithmeticObject plusForLargeInteger(LargeInteger aLargeInteger) {
		return aLargeInteger.plusForFloatNumber(this);
	}

	public ArithmeticObject minusForFloatNumber(FloatNumber aFloatNumber) {
		return new FloatNumber(aFloatNumber.floatValue() - number.floatValue());
	}
	
	public ArithmeticObject minusForSmallInteger(SmallInteger aSmallInteger) {
		return new FloatNumber(aSmallInteger.intValue() - this.floatValue());
	}

	public ArithmeticObject plusForFraction(Fraction aFraction) {
		ExtendedNumber numerator = (ExtendedNumber)((ExtendedNumber)aFraction.denominator()).multiplyByFloatNumber(this).plus(aFraction.numerator());
		return Fraction.numberNumber(numerator, (ExtendedNumber)aFraction.denominator());
	}

	public ArithmeticObject minusForLargeInteger(LargeInteger aLargeInteger) {
		return aLargeInteger.minusFloatNumber(this);
	}

	public ArithmeticObject minusLargeInteger(LargeInteger aLargeInteger) {
		return new FloatNumber(new Double(number.doubleValue()) - aLargeInteger.doubleValue());
	}

	public ArithmeticObject multiplyByFloatNumber(FloatNumber aFloatNumber) {
		return new FloatNumber(floatValue() * aFloatNumber.floatValue());
	}

	public ArithmeticObject multiplyBySmallInteger(SmallInteger aSmallInteger) {
		return new FloatNumber(floatValue() * aSmallInteger.intValue());
	}

	public ArithmeticObject multiplyByLargeInteger(LargeInteger aLargeInteger) {
		return new FloatNumber(floatValue() * aLargeInteger.doubleValue());
	}

	public ArithmeticObject divideByFloatNumber(FloatNumber aFloatNumber) {
		return Fraction.numberNumber(this, aFloatNumber);
	}
		
	public boolean isZero() {
		return number.floatValue() == 0;
	}
	
	public boolean isOne() {
		return number.floatValue() == 1;
	}

	public ArithmeticObject minusForFraction(Fraction aFraction) {
		return aFraction.minusFloatNumber(this);
	}

	public ExtendedNumber quotient(ExtendedNumber aNumber) {
		return aNumber.quotientForFloatNumber(this);
	}

	public ExtendedNumber quotientForSmallInteger(SmallInteger aSmallInteger) {
		return new FloatNumber(aSmallInteger.intValue() / number.floatValue());
	}
	
	public boolean equalsFloatNumber(FloatNumber aFloatNumber) {
		return number.equals(aFloatNumber.number);
	}	

	public boolean equalsSmallInteger(SmallInteger aSmallInteger) {
		return this.equalsFloatNumber(aSmallInteger.asFloatNumber());
	}

	public boolean equalsLargeInteger(LargeInteger aLargeInteger) {
		return this.equalsFloatNumber(aLargeInteger.asFloatNumber());
	}

	public SmallInteger asSmallInteger() {
		return new SmallInteger(number.intValue());
	}

	public LargeInteger asLargeInteger() {
		return new LargeInteger(number.intValue()); 
	}

	public FloatNumber asFloatNumber() {
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
		BigDecimal aBigDecimal = new BigDecimal(number.doubleValue());
		return new FloatNumber(aBigDecimal.abs().floatValue());
	}

	public boolean isNegative() {
		return number.floatValue() < 0;
	}

	public boolean isPositive() {
		return number.floatValue() > 0;
	}
	
	public ArithmeticObject negated() {
		if (isZero()) {
			return this;
		} else {
			return new FloatNumber(number.floatValue() * -1);
		}
	}

	public ArithmeticObject truncated() {
		return new FloatNumber(number.intValue());
	}

}
