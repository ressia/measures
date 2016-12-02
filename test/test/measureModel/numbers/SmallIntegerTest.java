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

public class SmallIntegerTest extends TestCase {
	
	public void testIntegerWithInt() {
		SmallInteger anInteger = new SmallInteger(1);
		try {
			Field aField = anInteger.getClass().getDeclaredField("number");
			aField.setAccessible(true);
			assertTrue(aField.get(anInteger).equals(new Integer(1)));
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
		SmallInteger anInteger = new SmallInteger(new Integer(1));
		try {
			Field aField = anInteger.getClass().getDeclaredField("number");
			aField.setAccessible(true);
			assertTrue(aField.get(anInteger).equals(new Integer(1)));
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
		SmallInteger anotherInteger = new SmallInteger(1);
		SmallInteger anIntInteger = new SmallInteger(new Integer(1));
		assertTrue(anotherInteger.equals(anIntInteger));
		assertTrue(anIntInteger.equals(anotherInteger));
	}
	
	public void testDenominator() {
		SmallInteger aSmallInteger = new SmallInteger(3);
		assertTrue(aSmallInteger.denominator().equals(new SmallInteger(1)));
	}
	
	public void testNumerator() {
		SmallInteger aSmallInteger = new SmallInteger(3);
		assertTrue(aSmallInteger.numerator().equals(new SmallInteger(3)));
	}
	
	public void testReciprocal() {
		SmallInteger aSmallInteger = new SmallInteger(3);
		SmallInteger anotherSmallInteger = new SmallInteger(1);
		SmallInteger zeroSmallInteger = new SmallInteger(0);
		assertTrue(aSmallInteger.reciprocal().equals(new Fraction(1,3)));
		assertTrue(anotherSmallInteger.reciprocal().equals(anotherSmallInteger));
		assertTrue(zeroSmallInteger.reciprocal().equals(zeroSmallInteger));
	}
	
	public void testIsOne() {
		SmallInteger aSmallInteger = new SmallInteger(3);
		SmallInteger anotherSmallInteger = new SmallInteger(1);
		SmallInteger zeroSmallInteger = new SmallInteger(0);
		assertFalse(aSmallInteger.isOne());
		assertTrue(anotherSmallInteger.isOne());
		assertFalse(zeroSmallInteger.isOne());
	}
	
	public void testIsZero() {
		SmallInteger aSmallInteger = new SmallInteger(3);
		SmallInteger anotherSmallInteger = new SmallInteger(1);
		SmallInteger zeroSmallInteger = new SmallInteger(0);
		assertFalse(aSmallInteger.isZero());
		assertFalse(anotherSmallInteger.isZero());
		assertTrue(zeroSmallInteger.isZero());
	}	
	
	public void testIsNothing() {
		SmallInteger aSmallInteger = new SmallInteger(3);
		SmallInteger anotherSmallInteger = new SmallInteger(1);
		SmallInteger zeroSmallInteger = new SmallInteger(0);
		assertFalse(aSmallInteger.isNothing());
		assertFalse(anotherSmallInteger.isNothing());
		assertTrue(zeroSmallInteger.isNothing());
	}	
	
	public void testSameUnitAs() {
		assertTrue((new SmallInteger(1)).sameUnitAs(new SmallInteger(1)));
		assertTrue((new SmallInteger(2)).sameUnitAs(new SmallInteger(1)));
		assertTrue((new SmallInteger(2)).sameUnitAs(new LargeInteger(1)));
		assertTrue((new SmallInteger(2)).sameUnitAs(new Fraction(1,2)));
		assertTrue((new SmallInteger(2)).sameUnitAs(new FloatNumber(1.05f)));
	}
	
	/**
	 * Transformations
	 */
	public void testAsSmallInteger() {
		SmallInteger aSmallInteger = new SmallInteger(1);
		assertTrue(aSmallInteger.asSmallInteger().equals(aSmallInteger));
	}
	
	public void testAsLargeInteger() {
		SmallInteger aSmallInteger = new SmallInteger(1);
		LargeInteger aLargeInteger = new LargeInteger(1);
		assertTrue(aSmallInteger.asLargeInteger().equals(aLargeInteger));
	}
	
	public void testAsFloatNumber() {
		SmallInteger aSmallInteger = new SmallInteger(1);
		FloatNumber aFloatNumber = new FloatNumber(1);
		assertTrue(aSmallInteger.asFloatNumber().equals(aFloatNumber));
	}
	
	public void testAsFraction() {
		SmallInteger aSmallInteger = new SmallInteger(2);
		Fraction aFraction = new Fraction(2,1);
		assertTrue(aSmallInteger.asFraction().equals(aFraction));
	}
	
	/**
	 * Equality
	 */
	
	public void testSmallIntegerEquality() {
		SmallInteger aSmallInteger = new SmallInteger(3);
		SmallInteger anotherSmallInteger = new SmallInteger(2);
		assertFalse(aSmallInteger.equals(anotherSmallInteger));

		anotherSmallInteger = new SmallInteger(3);
		assertTrue(aSmallInteger.equals(anotherSmallInteger));
	}	
	
	public void testLargeIntegerEquality() {
		SmallInteger aSmallInteger = new SmallInteger(3);
		LargeInteger aLargeInteger = new LargeInteger(2);
		assertFalse(aSmallInteger.equals(aLargeInteger));

		aLargeInteger = new LargeInteger(3);
		assertTrue(aSmallInteger.equals(aLargeInteger));
	}
	
	public void testFloatNumberEquality() {
		SmallInteger aSmallInteger = new SmallInteger(3);
		FloatNumber aFloatNumber = new FloatNumber(2);
		assertFalse(aSmallInteger.equals(aFloatNumber));

		aFloatNumber = new FloatNumber(3);
		assertTrue(aSmallInteger.equals(aFloatNumber));
	}
	
	public void testFractionEquality() {
		SmallInteger aSmallInteger = new SmallInteger(3);
		Fraction aFraction = new Fraction(1,2);
		assertFalse(aSmallInteger.equals(aFraction));

		aFraction = new Fraction(3,1);
		assertTrue(aSmallInteger.equals(aFraction));
	}
	
	/**
	 * Addition testing
	 */
	
	public void testPlusSmallInteger() {
		SmallInteger anInteger = new SmallInteger(1);
		SmallInteger anotherInteger = new SmallInteger(2);
		Object result = anInteger.plus(anotherInteger);
		assertTrue(result.equals(new SmallInteger(3)));
	}

	public void testPlusLargeInteger() {
		SmallInteger anInteger = new SmallInteger(1);
		ExtendedNumber anotherInteger = new LargeInteger(2);
		Object result = anInteger.plus(anotherInteger);
		assertTrue(result.equals(new LargeInteger(3)));
	}
	
	public void testPlusFraction() {
		SmallInteger anInteger = new SmallInteger(1);
		Fraction aFraction = new Fraction(1,2);
		Object result = anInteger.plus(aFraction);
		assertTrue(result.equals(new Fraction(3,2)));
	}
	
	public void testPlusFloatNumber() {
		SmallInteger anInteger = new SmallInteger(1);
		FloatNumber aFloatNumber = new FloatNumber(2.1f);
		Object result = anInteger.plus(aFloatNumber);
		assertTrue(result.equals(new FloatNumber(3.1f)));
	}
	
	/**
	 * Subtraction Testing 
	 */
	
	public void testMinusSmallInteger() {
		SmallInteger anInteger = new SmallInteger(2);
		SmallInteger anotherInteger = new SmallInteger(1);
		Object result = anInteger.minus(anotherInteger);
		assertTrue(result.equals(new SmallInteger(1)));
	}

	public void testMinusLargeInteger() {
		SmallInteger anInteger = new SmallInteger(2);
		ExtendedNumber anotherInteger = new LargeInteger(1);
		Object result = anInteger.minus(anotherInteger);
		assertTrue(result.equals(new LargeInteger(1)));
	}
	
	public void testMinusFraction() {
		SmallInteger anInteger = new SmallInteger(1);
		Fraction aFraction = new Fraction(1,2);
		Object result = anInteger.minus(aFraction);
		assertTrue(result.equals(new Fraction(1,2)));
	}
	
	public void testMinusFloatNumber() {
		SmallInteger anInteger = new SmallInteger(1);
		FloatNumber aFloatNumber = new FloatNumber(0.1f);
		Object result = anInteger.minus(aFloatNumber);
		assertTrue(result.equals(new FloatNumber(0.9f)));
	}
	
	/**
	 * Multiplication Testing 
	 */
	
	public void testMultiplyBySmallInteger() {
		SmallInteger anInteger = new SmallInteger(2);
		SmallInteger anotherInteger = new SmallInteger(1);
		Object result = anInteger.multiplyBy(anotherInteger);
		assertTrue(result.equals(new SmallInteger(2)));
	}

	public void testMultiplyByLargeInteger() {
		SmallInteger anInteger = new SmallInteger(2);
		ExtendedNumber anotherInteger = new LargeInteger(1);
		Object result = anInteger.multiplyBy(anotherInteger);
		assertTrue(result.equals(new LargeInteger(2)));
	}
	
	public void testMultiplyByFraction() {
		SmallInteger anInteger = new SmallInteger(3);
		Fraction aFraction = new Fraction(1,2);
		Object result = anInteger.multiplyBy(aFraction);
		assertTrue(result.equals(new Fraction(3,2)));
	}
	
	public void testMultiplyFloatNumber() {
		SmallInteger anInteger = new SmallInteger(2);
		FloatNumber aFloatNumber = new FloatNumber(0.1f);
		Object result = anInteger.multiplyBy(aFloatNumber);
		assertTrue(result.equals(new FloatNumber(0.2f)));
	}
	
	/**
	 * Quotient Testing 
	 */
	
	public void testQuotientSmallInteger() {
		SmallInteger anInteger = new SmallInteger(1);
		SmallInteger anotherInteger = new SmallInteger(2);
		Object result = anInteger.quotient(anotherInteger);
		assertTrue(result.equals(new FloatNumber(0.5f)));
		
		anotherInteger = new SmallInteger(1);
		result = anInteger.quotient(anotherInteger);
		assertTrue(result.equals(new FloatNumber(1f)));
	}
	
	public void testQuotientLargeInteger() {
		SmallInteger anInteger = new SmallInteger(1);
		LargeInteger anotherInteger = new LargeInteger(2);
		Object result = anInteger.quotient(anotherInteger);
		assertTrue(result.equals(new FloatNumber(0.5f)));
		
		anotherInteger = new LargeInteger(1);
		result = anInteger.quotient(anotherInteger);
		assertTrue(result.equals(new FloatNumber(1f)));
	}
	
	public void testQuotientFloatNumber() {
		SmallInteger anInteger = new SmallInteger(1);
		FloatNumber aFloatNumber = new FloatNumber(2);
		Object result = anInteger.quotient(aFloatNumber);
		assertTrue(result.equals(new FloatNumber(0.5f)));
		
		aFloatNumber = new FloatNumber(1);
		result = anInteger.quotient(aFloatNumber);
		assertTrue(result.equals(new FloatNumber(1f)));
	}
	
	public void testQuotientFraction() {
		SmallInteger anInteger = new SmallInteger(1);
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
		SmallInteger anInteger = new SmallInteger(2);
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
		SmallInteger anInteger = new SmallInteger(2);
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
		SmallInteger anInteger = new SmallInteger(2);
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
		SmallInteger anInteger = new SmallInteger(2);
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
		assertTrue((new SmallInteger(2)).abs().equals(new SmallInteger(2)));
		assertTrue((new SmallInteger(0)).abs().equals(new SmallInteger(0)));
		assertTrue((new SmallInteger(-1)).abs().equals(new SmallInteger(1)));
	}
	
	/**
	 * Testing Magnitude Protocol
	 */
	
	
	// >
	public void testGreaterThanSmallInteger() {
		SmallInteger anInteger = new SmallInteger(2);
		SmallInteger anotherInteger = new SmallInteger(1);
		assertTrue(anInteger.greaterThan(anotherInteger));
		assertFalse(anotherInteger.greaterThan(anInteger));
		assertFalse(anInteger.greaterThan(anInteger));
	}

	public void testGreaterThanLargeInteger() {
		SmallInteger anInteger = new SmallInteger(2);
		LargeInteger aLargeInteger = new LargeInteger(1);
		LargeInteger anotherLargeInteger = new LargeInteger(3);
		LargeInteger sameLargeInteger = new LargeInteger(2);
		assertTrue(anInteger.greaterThan(aLargeInteger));
		assertFalse(anInteger.greaterThan(anotherLargeInteger));
		assertFalse(anInteger.greaterThan(sameLargeInteger));
	}
	
	public void testGreaterThanFraction() {
		SmallInteger anInteger = new SmallInteger(2);
		Fraction aFraction = new Fraction(1,2);
		Fraction anotherFraction = new Fraction(5,2);
		Fraction sameFraction = new Fraction(4,2);
		assertTrue(anInteger.greaterThan(aFraction));
		assertFalse(anInteger.greaterThan(anotherFraction));
		assertFalse(anInteger.greaterThan(sameFraction));
	}
	
	public void testGreaterThanFloatNumber() {
		SmallInteger anInteger = new SmallInteger(2);
		FloatNumber aFloat = new FloatNumber(1);
		FloatNumber anotherFloat = new FloatNumber(3.2f);
		FloatNumber sameFloat = new FloatNumber(2);
		assertTrue(anInteger.greaterThan(aFloat));
		assertFalse(anInteger.greaterThan(anotherFloat));
		assertFalse(anInteger.greaterThan(sameFloat));
	}
	
	// >=
	public void testGreaterOrEqualThanSmallInteger() {
		SmallInteger anInteger = new SmallInteger(2);
		SmallInteger anotherInteger = new SmallInteger(1);
		assertTrue(anInteger.greaterThanOrEqualTo(anotherInteger));
		assertFalse(anotherInteger.greaterThanOrEqualTo(anInteger));
		assertTrue(anInteger.greaterThanOrEqualTo(anInteger));
	}

	public void testGreaterOrEqualThanLargeInteger() {
		SmallInteger anInteger = new SmallInteger(2);
		LargeInteger aLargeInteger = new LargeInteger(1);
		LargeInteger anotherLargeInteger = new LargeInteger(3);
		LargeInteger sameLargeInteger = new LargeInteger(2);
		assertTrue(anInteger.greaterThanOrEqualTo(aLargeInteger));
		assertFalse(anInteger.greaterThanOrEqualTo(anotherLargeInteger));
		assertTrue(anInteger.greaterThanOrEqualTo(sameLargeInteger));
	}
	
	public void testGreaterOrEqualThanFraction() {
		SmallInteger anInteger = new SmallInteger(2);
		Fraction aFraction = new Fraction(1,2);
		Fraction anotherFraction = new Fraction(5,2);
		Fraction sameFraction = new Fraction(4,2);
		assertTrue(anInteger.greaterThanOrEqualTo(aFraction));
		assertFalse(anInteger.greaterThanOrEqualTo(anotherFraction));
		assertTrue(anInteger.greaterThanOrEqualTo(sameFraction));
	}
	
	public void testGreaterOrEqualThanFloatNumber() {
		SmallInteger anInteger = new SmallInteger(2);
		FloatNumber aFloat = new FloatNumber(1);
		FloatNumber anotherFloat = new FloatNumber(3.2f);
		FloatNumber sameFloat = new FloatNumber(2);
		assertTrue(anInteger.greaterThanOrEqualTo(aFloat));
		assertFalse(anInteger.greaterThanOrEqualTo(anotherFloat));
		assertTrue(anInteger.greaterThanOrEqualTo(sameFloat));
	}
	
	// <
	public void testLessThanSmallInteger() {
		SmallInteger anInteger = new SmallInteger(2);
		SmallInteger anotherInteger = new SmallInteger(1);
		assertFalse(anInteger.lessThan(anotherInteger));
		assertTrue(anotherInteger.lessThan(anInteger));
		assertFalse(anInteger.lessThan(anInteger));
	}

	public void testLessThanLargeInteger() {
		SmallInteger anInteger = new SmallInteger(2);
		LargeInteger aLargeInteger = new LargeInteger(1);
		LargeInteger anotherLargeInteger = new LargeInteger(3);
		LargeInteger sameLargeInteger = new LargeInteger(2);
		assertFalse(anInteger.lessThan(aLargeInteger));
		assertTrue(anInteger.lessThan(anotherLargeInteger));
		assertFalse(anInteger.lessThan(sameLargeInteger));
	}
	
	public void testLessThanFraction() {
		SmallInteger anInteger = new SmallInteger(2);
		Fraction aFraction = new Fraction(1,2);
		Fraction anotherFraction = new Fraction(5,2);
		Fraction sameFraction = new Fraction(4,2);
		assertFalse(anInteger.lessThan(aFraction));
		assertTrue(anInteger.lessThan(anotherFraction));
		assertFalse(anInteger.lessThan(sameFraction));
	}
	
	public void testLessThanFloatNumber() {
		SmallInteger anInteger = new SmallInteger(2);
		FloatNumber aFloat = new FloatNumber(1);
		FloatNumber anotherFloat = new FloatNumber(3.2f);
		FloatNumber sameFloat = new FloatNumber(2);
		assertFalse(anInteger.lessThan(aFloat));
		assertTrue(anInteger.lessThan(anotherFloat));
		assertFalse(anInteger.lessThan(sameFloat));
	}
	
	// <=
	public void testLessOrEqualThanSmallInteger() {
		SmallInteger anInteger = new SmallInteger(2);
		SmallInteger anotherInteger = new SmallInteger(1);
		assertFalse(anInteger.lessThanOrEqualTo(anotherInteger));
		assertTrue(anotherInteger.lessThanOrEqualTo(anInteger));
		assertTrue(anInteger.lessThanOrEqualTo(anInteger));
	}

	public void testLessOrEqualThanLargeInteger() {
		SmallInteger anInteger = new SmallInteger(2);
		LargeInteger aLargeInteger = new LargeInteger(1);
		LargeInteger anotherLargeInteger = new LargeInteger(3);
		LargeInteger sameLargeInteger = new LargeInteger(2);
		assertFalse(anInteger.lessThanOrEqualTo(aLargeInteger));
		assertTrue(anInteger.lessThanOrEqualTo(anotherLargeInteger));
		assertTrue(anInteger.lessThanOrEqualTo(sameLargeInteger));
	}
	
	public void testLessOrEqualThanFraction() {
		SmallInteger anInteger = new SmallInteger(2);
		Fraction aFraction = new Fraction(1,2);
		Fraction anotherFraction = new Fraction(5,2);
		Fraction sameFraction = new Fraction(4,2);
		assertFalse(anInteger.lessThanOrEqualTo(aFraction));
		assertTrue(anInteger.lessThanOrEqualTo(anotherFraction));
		assertTrue(anInteger.lessThanOrEqualTo(sameFraction));
	}
	
	public void testLessOrEqualThanFloatNumber() {
		SmallInteger anInteger = new SmallInteger(2);
		FloatNumber aFloat = new FloatNumber(1);
		FloatNumber anotherFloat = new FloatNumber(3.2f);
		FloatNumber sameFloat = new FloatNumber(2);
		assertFalse(anInteger.lessThanOrEqualTo(aFloat));
		assertTrue(anInteger.lessThanOrEqualTo(anotherFloat));
		assertTrue(anInteger.lessThanOrEqualTo(sameFloat));
	}
	
	public void testNegated() {
		SmallInteger anInteger = new SmallInteger(1);
		SmallInteger anotherInteger = new SmallInteger(-1);
		SmallInteger zeroInteger = new SmallInteger(0);
		assertTrue(anInteger.negated().equals(anotherInteger));
		assertTrue(anotherInteger.negated().equals(anInteger));
		assertTrue(zeroInteger.negated().equals(zeroInteger));
	}
	
	public void testTruncated() {
		assertTrue((new SmallInteger(1)).truncated().equals(new SmallInteger(1)));
		assertTrue((new SmallInteger(2)).truncated().equals(new SmallInteger(2)));
	}
	
}
