package test.measureModel.numbers;

import java.lang.reflect.Field;
import java.math.BigInteger;

import com.measureModel.numbers.ExtendedNumber;
import com.measureModel.numbers.FloatNumber;
import com.measureModel.numbers.Fraction;
import com.measureModel.numbers.LargeInteger;
import com.measureModel.numbers.SmallInteger;
import com.measureModel.numbers.exceptions.DivisionByZeroException;


import junit.framework.TestCase;

public class LargeIntegerTest extends TestCase {
	
	public void testIntegerWithInt() {
		ExtendedNumber anInteger = new LargeInteger(1);
		try {
			Field aField = anInteger.getClass().getDeclaredField("number");
			aField.setAccessible(true);
			assertTrue(aField.get(anInteger).equals(new BigInteger("1")));
		} catch (SecurityException e) {
			fail();
		} catch (IllegalArgumentException e) { 
			fail();
		} catch (IllegalAccessException e) {
			fail();
		} catch (NoSuchFieldException e) {
			fail();
		} 
	}

	public void testIntegerWithInteger() {
		ExtendedNumber anInteger = new LargeInteger(new BigInteger("1"));
		try {
			Field aField = anInteger.getClass().getDeclaredField("number");
			aField.setAccessible(true);
			assertTrue(aField.get(anInteger).equals(new BigInteger("1")));
		} catch (SecurityException e) {
			fail();
		} catch (IllegalArgumentException e) { 
			fail();
		} catch (IllegalAccessException e) {
			fail();
		} catch (NoSuchFieldException e) {
			fail();
		} 
	}
	
	public void testIntegerEquality() {
		ExtendedNumber anotherInteger = new LargeInteger(1);
		ExtendedNumber anIntInteger = new LargeInteger(new Integer(1));
		assertTrue(anotherInteger.equals(anIntInteger));
		assertTrue(anIntInteger.equals(anotherInteger));
	}
	
	public void testDenominator() {
		ExtendedNumber aLargeInteger = new LargeInteger(3);
		assertTrue(aLargeInteger.denominator().equals(new SmallInteger(1)));
	}
	
	public void testNumerator() {
		ExtendedNumber aSmallInteger = new LargeInteger(3);
		assertTrue(aSmallInteger.numerator().equals(new LargeInteger(3)));
	}
	
	public void testReciprocal() {
		ExtendedNumber aLargeInteger = new LargeInteger(3);
		ExtendedNumber anotherSmallInteger = new LargeInteger(1);
		ExtendedNumber zeroSmallInteger = new LargeInteger(0);
		assertTrue(aLargeInteger.reciprocal().equals(new Fraction(1,aLargeInteger)));
		assertTrue(anotherSmallInteger.reciprocal().equals(anotherSmallInteger));
		assertTrue(zeroSmallInteger.reciprocal().equals(zeroSmallInteger));
	}
	
	public void testIsOne() {
		ExtendedNumber aLargeInteger = new LargeInteger(3);
		ExtendedNumber anotherSmallInteger = new LargeInteger(1);
		ExtendedNumber zeroSmallInteger = new LargeInteger(0);
		assertFalse(aLargeInteger.isOne());
		assertTrue(anotherSmallInteger.isOne());
		assertFalse(zeroSmallInteger.isOne());
	}
	
	public void testIsZero() {
		ExtendedNumber aLargeInteger = new LargeInteger(3);
		ExtendedNumber anotherSmallInteger = new LargeInteger(1);
		ExtendedNumber zeroSmallInteger = new LargeInteger(0);
		assertFalse(aLargeInteger.isZero());
		assertFalse(anotherSmallInteger.isZero());
		assertTrue(zeroSmallInteger.isZero());
	}
	
	public void testIsNothing() {
		ExtendedNumber aLargeInteger = new LargeInteger(3);
		ExtendedNumber anotherSmallInteger = new LargeInteger(1);
		ExtendedNumber zeroSmallInteger = new LargeInteger(0);
		assertFalse(aLargeInteger.isNothing());
		assertFalse(anotherSmallInteger.isNothing());
		assertTrue(zeroSmallInteger.isNothing());
	}


	/**
	 * Transformations
	 */
	public void testAsSmallInteger() {
		LargeInteger aLargeInteger = new LargeInteger(1);
		assertTrue(aLargeInteger.asSmallInteger().equals(aLargeInteger));
	}
	
	public void testAsLargeInteger() {
		LargeInteger aLargeInteger = new LargeInteger(1);
		LargeInteger anotherLargeInteger = new LargeInteger(1);
		assertTrue(aLargeInteger.asLargeInteger().equals(anotherLargeInteger));
	}
	
	public void testAsFloatNumber() {
		LargeInteger aLargeInteger = new LargeInteger(1);
		FloatNumber aFloatNumber = new FloatNumber(1);
		assertTrue(aLargeInteger.asFloatNumber().equals(aFloatNumber));
	}
	
	public void testAsFraction() {
		LargeInteger aLargeInteger = new LargeInteger(2);
		Fraction aFraction = new Fraction(2,1);
		assertTrue(aLargeInteger.asFraction().equals(aFraction));
	}
	
	/**
	 * Equality
	 */
	
	public void testSmallIntegerEquality() {
		LargeInteger aLargeInteger = new LargeInteger(3);
		SmallInteger anotherSmallInteger = new SmallInteger(2);
		assertFalse(aLargeInteger.equals(anotherSmallInteger));

		anotherSmallInteger = new SmallInteger(3);
		assertTrue(aLargeInteger.equals(anotherSmallInteger));
	}	
	
	public void testLargeIntegerEquality() {
		LargeInteger aLargeInteger = new LargeInteger(3);
		LargeInteger anotherLargeInteger = new LargeInteger(2);
		assertFalse(aLargeInteger.equals(anotherLargeInteger));

		anotherLargeInteger = new LargeInteger(3);
		assertTrue(aLargeInteger.equals(anotherLargeInteger));
	}
	
	public void testFloatNumberEquality() {
		LargeInteger aLargeInteger = new LargeInteger(3);
		FloatNumber aFloatNumber = new FloatNumber(2);
		assertFalse(aLargeInteger.equals(aFloatNumber));

		aFloatNumber = new FloatNumber(3);
		assertTrue(aLargeInteger.equals(aFloatNumber));
	}
	
	public void testFractionEquality() {
		LargeInteger aLargeInteger = new LargeInteger(3);
		Fraction aFraction = new Fraction(1,2);
		assertFalse(aLargeInteger.equals(aFraction));

		aFraction = new Fraction(3,1);
		assertTrue(aLargeInteger.equals(aFraction));
	}
	
	/**
	 * TODO: The idea is to go on testing all other methods specially the arithmetic operations 
	 * where the operation goes out of range. And more!!
	 */
	
	/**
	 * Addition testing
	 */
	
	public void testPlusSmallInteger() {
		ExtendedNumber anInteger = new LargeInteger(1);
		SmallInteger aSmallInteger = new SmallInteger(2);
		Object result = anInteger.plus(aSmallInteger);
		assertTrue(result.equals(new LargeInteger(3)));
	}

	public void testPlusLargeInteger() {
		ExtendedNumber anInteger = new LargeInteger(1);
		ExtendedNumber anotherInteger = new LargeInteger(2);
		Object result = anInteger.plus(anotherInteger);
		assertTrue(result.equals(new LargeInteger(3)));
	}
	
	public void testPlusFraction() {
		ExtendedNumber anInteger = new LargeInteger(1);
		Fraction aFraction = new Fraction(1,2);
		Object result = anInteger.plus(aFraction);
		assertTrue(result.equals(new Fraction(new LargeInteger(3),new SmallInteger(2))));
	}
	
	public void testPlusFloatNumber() {
		ExtendedNumber anInteger = new LargeInteger(1);
		FloatNumber aFloatNumber = new FloatNumber(2.1f);
		Object result = anInteger.plus(aFloatNumber);
		assertTrue(result.equals(new FloatNumber(3.1f)));
	}
	
	/**
	 * Subtracting testing
	 */
	
	public void testMinusSmallInteger() {
		ExtendedNumber anInteger = new LargeInteger(2);
		SmallInteger aSmallInteger = new SmallInteger(1);
		Object result = anInteger.minus(aSmallInteger);
		assertTrue(result.equals(new LargeInteger(1)));
	}

	public void testMinusLargeInteger() {
		ExtendedNumber anInteger = new LargeInteger(2);
		ExtendedNumber anotherInteger = new LargeInteger(1);
		Object result = anInteger.minus(anotherInteger);
		assertTrue(result.equals(new LargeInteger(1)));
	}
	
	public void testMinusFraction() {
		ExtendedNumber anInteger = new LargeInteger(1);
		Fraction aFraction = new Fraction(1,2);
		Object result = anInteger.minus(aFraction);
		assertTrue(result.equals(new Fraction(new LargeInteger(1),new SmallInteger(2))));
	}
	
	public void testMinusFloatNumber() {
		ExtendedNumber anInteger = new LargeInteger(1);
		FloatNumber aFloatNumber = new FloatNumber(0.1f);
		Object result = anInteger.minus(aFloatNumber);
		assertTrue(result.equals(new FloatNumber(0.9f)));
	}
	
	/**
	 * Multiplying testing
	 */
	public void testMultiplyBySmallInteger() {
		ExtendedNumber anInteger = new LargeInteger(2);
		SmallInteger aSmallInteger = new SmallInteger(2);
		Object result = anInteger.multiplyBy(aSmallInteger);
		assertTrue(result.equals(new LargeInteger(4)));
		
		aSmallInteger = new SmallInteger(0);
		result = anInteger.multiplyBy(aSmallInteger);
		assertTrue(result.equals(new LargeInteger(0)));
		
		aSmallInteger = new SmallInteger(1);
		result = anInteger.multiplyBy(aSmallInteger);
		assertTrue(result.equals(new LargeInteger(2)));
	}

	public void testMultiplyByLargeInteger() {
		ExtendedNumber anInteger = new LargeInteger(2);
		ExtendedNumber anotherInteger = new LargeInteger(2);
		Object result = anInteger.multiplyBy(anotherInteger);
		assertTrue(result.equals(new LargeInteger(4)));
		
		anotherInteger = new LargeInteger(0);
		result = anInteger.multiplyBy(anotherInteger);
		assertTrue(result.equals(new LargeInteger(0)));
		
		anotherInteger = new LargeInteger(1);
		result = anInteger.multiplyBy(anotherInteger);
		assertTrue(result.equals(new LargeInteger(2)));
	}
	
	public void testMultiplyByFraction() {
		ExtendedNumber anInteger = new LargeInteger(2);
		Fraction aFraction = new Fraction(1,3);
		Object result = anInteger.multiplyBy(aFraction);
		assertTrue(result.equals(new Fraction(new LargeInteger(2),new SmallInteger(3))));
		
		aFraction = new Fraction(1,2);
		result = anInteger.multiplyBy(aFraction);
		assertTrue(result.equals(new Fraction(new LargeInteger(2),new SmallInteger(2))));
	}
	
	public void testMultiplyByFloatNumber() {
		ExtendedNumber anInteger = new LargeInteger(2);
		FloatNumber aFloatNumber = new FloatNumber(0.1f);
		Object result = anInteger.multiplyBy(aFloatNumber);
		assertTrue(result.equals(new FloatNumber(0.2f)));
		
		aFloatNumber = new FloatNumber(0);
		result = anInteger.multiplyBy(aFloatNumber);
		assertTrue(result.equals(new FloatNumber(0)));
		
		aFloatNumber = new FloatNumber(1);
		result = anInteger.multiplyBy(aFloatNumber);
		assertTrue(result.equals(new FloatNumber(2)));
	}
	
	/**
	 * Quotient Testing 
	 */
	
	public void testQuotientSmallInteger() {
		LargeInteger anInteger = new LargeInteger(1);
		SmallInteger anotherInteger = new SmallInteger(2);
		Object result = anInteger.quotient(anotherInteger);
		assertTrue(result.equals(new FloatNumber(0.5f)));
		
		anotherInteger = new SmallInteger(1);
		result = anInteger.quotient(anotherInteger);
		assertTrue(result.equals(new FloatNumber(1f)));
	}
	
	public void testQuotientLargeInteger() {
		LargeInteger anInteger = new LargeInteger(1);
		LargeInteger anotherInteger = new LargeInteger(2);
		Object result = anInteger.quotient(anotherInteger);
		assertTrue(result.equals(new FloatNumber(0.5f)));
		
		anotherInteger = new LargeInteger(1);
		result = anInteger.quotient(anotherInteger);
		assertTrue(result.equals(new FloatNumber(1f)));
	}
	
	public void testQuotientFloatNumber() {
		LargeInteger anInteger = new LargeInteger(1);
		FloatNumber aFloatNumber = new FloatNumber(2);
		Object result = anInteger.quotient(aFloatNumber);
		assertTrue(result.equals(new FloatNumber(0.5f)));
		
		aFloatNumber = new FloatNumber(1);
		result = anInteger.quotient(aFloatNumber);
		assertTrue(result.equals(new FloatNumber(1f)));
	}
	
	public void testQuotientFraction() {
		LargeInteger anInteger = new LargeInteger(1);
		Fraction aFraction = new Fraction(1,2);
		Object result = anInteger.quotient(aFraction);
		assertTrue(result.equals(new FloatNumber(2f)));
		
		aFraction = new Fraction(2,1);
		result = anInteger.quotient(aFraction);
		assertTrue(result.equals(new FloatNumber(0.5f)));
	}
	
	/**
	 * Division Testing 
	 */
	
	public void testDivideBySmallInteger() {
		LargeInteger anInteger = new LargeInteger(2);
		SmallInteger anotherInteger = new SmallInteger(1);
		Object result = anInteger.divideBy(anotherInteger);
		assertTrue(result.equals(new SmallInteger(2)));
		
		anotherInteger = new SmallInteger(2);
		result = anInteger.divideBy(anotherInteger);
		assertTrue(result.equals(new SmallInteger(1)));
		
		anotherInteger = new SmallInteger(3);
		result = anInteger.divideBy(anotherInteger);
		assertTrue(result.equals(new Fraction(2,3)));
		
		anotherInteger = new SmallInteger(0);
		Exception anException = null;
		try {
			result = anInteger.divideBy(anotherInteger);
		} catch (DivisionByZeroException ex) {
			anException = ex;
		}
		if (anException == null) {
			fail();
		}
	}

	public void testDivideByLargeInteger() {
		LargeInteger anInteger = new LargeInteger(2);
		LargeInteger anotherInteger = new LargeInteger(1);
		Object result = anInteger.divideBy(anotherInteger);
		assertTrue(result.equals(new SmallInteger(2)));
		
		anotherInteger = new LargeInteger(2);
		result = anInteger.divideBy(anotherInteger);
		assertTrue(result.equals(new SmallInteger(1)));
		
		anotherInteger = new LargeInteger(3);
		result = anInteger.divideBy(anotherInteger);
		assertTrue(result.equals(new Fraction(2,3)));
		
		anotherInteger = new LargeInteger(0);
		Exception anException = null;
		try {
			result = anInteger.divideBy(anotherInteger);
		} catch (DivisionByZeroException ex) {
			anException = ex;
		}
		if (anException == null) {
			fail();
		}
	}
	
	public void testDivideByFloatNumber() {
		LargeInteger anInteger = new LargeInteger(2);
		FloatNumber aFloatNumber = new FloatNumber(1);
		Object result = anInteger.divideBy(aFloatNumber);
		assertTrue(result.equals(new SmallInteger(2)));
		
		aFloatNumber = new FloatNumber(2);
		result = anInteger.divideBy(aFloatNumber);
		assertTrue(result.equals(new SmallInteger(1)));
		
		aFloatNumber = new FloatNumber(3);
		result = anInteger.divideBy(aFloatNumber);
		assertTrue(result.equals(new Fraction(2,3)));
		
		aFloatNumber = new FloatNumber(0);
		Exception anException = null;
		try {
			result = anInteger.divideBy(aFloatNumber);
		} catch (DivisionByZeroException ex) {
			anException = ex;
		}
		if (anException == null) {
			fail();
		}
	}
	
	public void testDivideByFraction() {
		LargeInteger anInteger = new LargeInteger(2);
		Fraction aFraction = new Fraction(1,2);
		Object result = anInteger.divideBy(aFraction);
		assertTrue(result.equals(new SmallInteger(4)));
		
		aFraction = new Fraction(2,1);
		result = anInteger.divideBy(aFraction);
		assertTrue(result.equals(new SmallInteger(1)));
		
		aFraction = new Fraction(3,1);
		result = anInteger.divideBy(aFraction);
		assertTrue(result.equals(new Fraction(2,3)));
		
		aFraction = new Fraction(0,1);
		Exception anException = null;
		try {
			result = anInteger.divideBy(aFraction);
		} catch (DivisionByZeroException ex) {
			anException = ex;
		}
		if (anException == null) {
			fail();
		}
	}
	
	/**
	 * abs Testing 
	 */
	
	public void testAbs() {
		assertTrue((new LargeInteger(2)).abs().equals(new LargeInteger(2)));
		assertTrue((new LargeInteger(0)).abs().equals(new LargeInteger(0)));
		assertTrue((new LargeInteger(-1)).abs().equals(new LargeInteger(1)));
	}
	
	/**
	 * Testing Magnitude Protocol
	 */
	
	
	// >
	public void testGreaterThanSmallInteger() {
		LargeInteger anInteger = new LargeInteger(2);
		SmallInteger anotherInteger = new SmallInteger(1);
		assertTrue(anInteger.greaterThan(anotherInteger));
		assertFalse(anotherInteger.greaterThan(anInteger));
		assertFalse(anInteger.greaterThan(anInteger));
	}

	public void testGreaterThanLargeInteger() {
		LargeInteger anInteger = new LargeInteger(2);
		LargeInteger aLargeInteger = new LargeInteger(1);
		LargeInteger anotherLargeInteger = new LargeInteger(3);
		LargeInteger sameLargeInteger = new LargeInteger(2);
		assertTrue(anInteger.greaterThan(aLargeInteger));
		assertFalse(anInteger.greaterThan(anotherLargeInteger));
		assertFalse(anInteger.greaterThan(sameLargeInteger));
	}
	
	public void testGreaterThanFraction() {
		LargeInteger anInteger = new LargeInteger(2);
		Fraction aFraction = new Fraction(1,2);
		Fraction anotherFraction = new Fraction(5,2);
		Fraction sameFraction = new Fraction(4,2);
		assertTrue(anInteger.greaterThan(aFraction));
		assertFalse(anInteger.greaterThan(anotherFraction));
		assertFalse(anInteger.greaterThan(sameFraction));
	}
	
	public void testGreaterThanFloatNumber() {
		LargeInteger anInteger = new LargeInteger(2);
		FloatNumber aFloat = new FloatNumber(1);
		FloatNumber anotherFloat = new FloatNumber(3.2f);
		FloatNumber sameFloat = new FloatNumber(2);
		assertTrue(anInteger.greaterThan(aFloat));
		assertFalse(anInteger.greaterThan(anotherFloat));
		assertFalse(anInteger.greaterThan(sameFloat));
	}
	
	// >=
	public void testGreaterOrEqualThanSmallInteger() {
		LargeInteger anInteger = new LargeInteger(2);
		SmallInteger anotherInteger = new SmallInteger(1);
		assertTrue(anInteger.greaterThanOrEqualTo(anotherInteger));
		assertFalse(anotherInteger.greaterThanOrEqualTo(anInteger));
		assertTrue(anInteger.greaterThanOrEqualTo(anInteger));
	}

	public void testGreaterOrEqualThanLargeInteger() {
		LargeInteger anInteger = new LargeInteger(2);
		LargeInteger aLargeInteger = new LargeInteger(1);
		LargeInteger anotherLargeInteger = new LargeInteger(3);
		LargeInteger sameLargeInteger = new LargeInteger(2);
		assertTrue(anInteger.greaterThanOrEqualTo(aLargeInteger));
		assertFalse(anInteger.greaterThanOrEqualTo(anotherLargeInteger));
		assertTrue(anInteger.greaterThanOrEqualTo(sameLargeInteger));
	}
	
	public void testGreaterOrEqualThanFraction() {
		LargeInteger anInteger = new LargeInteger(2);
		Fraction aFraction = new Fraction(1,2);
		Fraction anotherFraction = new Fraction(5,2);
		Fraction sameFraction = new Fraction(4,2);
		assertTrue(anInteger.greaterThanOrEqualTo(aFraction));
		assertFalse(anInteger.greaterThanOrEqualTo(anotherFraction));
		assertTrue(anInteger.greaterThanOrEqualTo(sameFraction));
	}
	
	public void testGreaterOrEqualThanFloatNumber() {
		LargeInteger anInteger = new LargeInteger(2);
		FloatNumber aFloat = new FloatNumber(1);
		FloatNumber anotherFloat = new FloatNumber(3.2f);
		FloatNumber sameFloat = new FloatNumber(2);
		assertTrue(anInteger.greaterThanOrEqualTo(aFloat));
		assertFalse(anInteger.greaterThanOrEqualTo(anotherFloat));
		assertTrue(anInteger.greaterThanOrEqualTo(sameFloat));
	}
	
	// <
	public void testLessThanSmallInteger() {
		LargeInteger anInteger = new LargeInteger(2);
		SmallInteger anotherInteger = new SmallInteger(1);
		assertFalse(anInteger.lessThan(anotherInteger));
		assertTrue(anotherInteger.lessThan(anInteger));
		assertFalse(anInteger.lessThan(anInteger));
	}

	public void testLessThanLargeInteger() {
		LargeInteger anInteger = new LargeInteger(2);
		LargeInteger aLargeInteger = new LargeInteger(1);
		LargeInteger anotherLargeInteger = new LargeInteger(3);
		LargeInteger sameLargeInteger = new LargeInteger(2);
		assertFalse(anInteger.lessThan(aLargeInteger));
		assertTrue(anInteger.lessThan(anotherLargeInteger));
		assertFalse(anInteger.lessThan(sameLargeInteger));
	}
	
	public void testLessThanFraction() {
		LargeInteger anInteger = new LargeInteger(2);
		Fraction aFraction = new Fraction(1,2);
		Fraction anotherFraction = new Fraction(5,2);
		Fraction sameFraction = new Fraction(4,2);
		assertFalse(anInteger.lessThan(aFraction));
		assertTrue(anInteger.lessThan(anotherFraction));
		assertFalse(anInteger.lessThan(sameFraction));
	}
	
	public void testLessThanFloatNumber() {
		LargeInteger anInteger = new LargeInteger(2);
		FloatNumber aFloat = new FloatNumber(1);
		FloatNumber anotherFloat = new FloatNumber(3.2f);
		FloatNumber sameFloat = new FloatNumber(2);
		assertFalse(anInteger.lessThan(aFloat));
		assertTrue(anInteger.lessThan(anotherFloat));
		assertFalse(anInteger.lessThan(sameFloat));
	}
	
	// <=
	public void testLessOrEqualThanSmallInteger() {
		LargeInteger anInteger = new LargeInteger(2);
		SmallInteger anotherInteger = new SmallInteger(1);
		assertFalse(anInteger.lessThanOrEqualTo(anotherInteger));
		assertTrue(anotherInteger.lessThanOrEqualTo(anInteger));
		assertTrue(anInteger.lessThanOrEqualTo(anInteger));
	}

	public void testLessOrEqualThanLargeInteger() {
		LargeInteger anInteger = new LargeInteger(2);
		LargeInteger aLargeInteger = new LargeInteger(1);
		LargeInteger anotherLargeInteger = new LargeInteger(3);
		LargeInteger sameLargeInteger = new LargeInteger(2);
		assertFalse(anInteger.lessThanOrEqualTo(aLargeInteger));
		assertTrue(anInteger.lessThanOrEqualTo(anotherLargeInteger));
		assertTrue(anInteger.lessThanOrEqualTo(sameLargeInteger));
	}
	
	public void testLessOrEqualThanFraction() {
		LargeInteger anInteger = new LargeInteger(2);
		Fraction aFraction = new Fraction(1,2);
		Fraction anotherFraction = new Fraction(5,2);
		Fraction sameFraction = new Fraction(4,2);
		assertFalse(anInteger.lessThanOrEqualTo(aFraction));
		assertTrue(anInteger.lessThanOrEqualTo(anotherFraction));
		assertTrue(anInteger.lessThanOrEqualTo(sameFraction));
	}
	
	public void testLessOrEqualThanFloatNumber() {
		LargeInteger anInteger = new LargeInteger(2);
		FloatNumber aFloat = new FloatNumber(1);
		FloatNumber anotherFloat = new FloatNumber(3.2f);
		FloatNumber sameFloat = new FloatNumber(2);
		assertFalse(anInteger.lessThanOrEqualTo(aFloat));
		assertTrue(anInteger.lessThanOrEqualTo(anotherFloat));
		assertTrue(anInteger.lessThanOrEqualTo(sameFloat));
	}
	
	public void testNegated() {
		LargeInteger anInteger = new LargeInteger(1);
		LargeInteger anotherInteger = new LargeInteger(-1);
		LargeInteger zeroInteger = new LargeInteger(0);
		assertTrue(anInteger.negated().equals(anotherInteger));
		assertTrue(anotherInteger.negated().equals(anInteger));
		assertTrue(zeroInteger.negated().equals(zeroInteger));
	}
	
	public void testTruncated() {
		assertTrue((new LargeInteger(1)).truncated().equals(new SmallInteger(1)));
		assertTrue((new LargeInteger(1)).truncated().equals(new SmallInteger(1)));
		
		assertTrue((new LargeInteger(2)).truncated().equals(new SmallInteger(2)));
		assertTrue((new LargeInteger(2)).truncated().equals(new SmallInteger(2)));
	}
	
	public void testSameUnitAs() {
		assertTrue((new LargeInteger(1)).sameUnitAs(new LargeInteger(1)));
		assertTrue((new LargeInteger(2)).sameUnitAs(new SmallInteger(1)));
		assertTrue((new LargeInteger(2)).sameUnitAs(new LargeInteger(1)));
		assertTrue((new LargeInteger(2)).sameUnitAs(new Fraction(1,2)));
		assertTrue((new LargeInteger(2)).sameUnitAs(new FloatNumber(1.05f)));
	}
	
}
