package test.measureModel.numbers;

import java.lang.reflect.Field;

import junit.framework.TestCase;

import com.measureModel.numbers.ExtendedNumber;
import com.measureModel.numbers.FloatNumber;
import com.measureModel.numbers.Fraction;
import com.measureModel.numbers.LargeInteger;
import com.measureModel.numbers.SmallInteger;
import com.measureModel.numbers.exceptions.DivisionByZeroException;

public class FloatNumberTest extends TestCase {
	
	public void testFloatWithFloat() {
		FloatNumber aFloatNumber = new FloatNumber(new Float(1));
		try {
			Field aField = aFloatNumber.getClass().getDeclaredField("number");
			aField.setAccessible(true);
			assertTrue(aField.get(aFloatNumber).equals(new Float(1)));
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

	public void testFloatWithFloatWrapper() {
		FloatNumber aFloatNumber = new FloatNumber(1.2f);
		try {
			Field aField = aFloatNumber.getClass().getDeclaredField("number");
			aField.setAccessible(true);
			assertTrue(aField.get(aFloatNumber).equals(new Float(1.2f)));
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
	
	public void testFloatWithDouble() {
		FloatNumber aFloatNumber = new FloatNumber(1.2);
		try {
			Field aField = aFloatNumber.getClass().getDeclaredField("number");
			aField.setAccessible(true);
			assertTrue(aField.get(aFloatNumber).equals(new Float(1.2)));
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
	
	public void testFloatEquality() {
		FloatNumber aFloatNumber = new FloatNumber(1);
		FloatNumber anotherFloatNumber = new FloatNumber(new Float(1));
		assertTrue(aFloatNumber.equals(anotherFloatNumber));
		assertTrue(anotherFloatNumber.equals(aFloatNumber));
		
		anotherFloatNumber = new FloatNumber(new Float(2));
		assertFalse(aFloatNumber.equals(anotherFloatNumber));
		assertFalse(anotherFloatNumber.equals(aFloatNumber));
	}
	
	public void testDenominator() {
		FloatNumber aFloatNumber = new FloatNumber(1.2f);
		assertTrue(aFloatNumber.denominator().equals(new SmallInteger(1)));
	}
	
	public void testNumerator() {
		FloatNumber aFloatNumber = new FloatNumber(1.2f);
		assertTrue(aFloatNumber.numerator().equals(aFloatNumber));
	}
	
	public void testReciprocal() {
		FloatNumber aFloatNumber = new FloatNumber(3.2f);
		FloatNumber anotherFloatNumber = new FloatNumber(1);
		FloatNumber zeroFloatNumber = new FloatNumber(0);
		assertTrue(aFloatNumber.reciprocal().equals(new Fraction(1,aFloatNumber)));
		assertTrue(anotherFloatNumber.reciprocal().equals(anotherFloatNumber));
		assertTrue(zeroFloatNumber.reciprocal().equals(zeroFloatNumber));
	}
	
	public void testIsOne() {
		FloatNumber aFloatNumber = new FloatNumber(3.2f);
		FloatNumber anotherFloatNumber = new FloatNumber(1);
		FloatNumber zeroFloatNumber = new FloatNumber(0);
		assertFalse(aFloatNumber.isOne());
		assertTrue(anotherFloatNumber.isOne());
		assertFalse(zeroFloatNumber.isOne());
	}
	
	public void testIsZero() {
		FloatNumber aFloatNumber = new FloatNumber(3.2f);
		FloatNumber anotherFloatNumber = new FloatNumber(1);
		FloatNumber zeroFloatNumber = new FloatNumber(0);
		assertFalse(aFloatNumber.isZero());
		assertFalse(anotherFloatNumber.isZero());
		assertTrue(zeroFloatNumber.isZero());
	}
	
	public void testIsNothing() {
		FloatNumber aFloatNumber = new FloatNumber(3.2f);
		FloatNumber anotherFloatNumber = new FloatNumber(1);
		FloatNumber zeroFloatNumber = new FloatNumber(0);
		assertFalse(aFloatNumber.isNothing());
		assertFalse(anotherFloatNumber.isNothing());
		assertTrue(zeroFloatNumber.isNothing());
	}
	
	/**
	 * Transformations
	 */
	public void testAsSmallInteger() {
		FloatNumber aFloatNumber = new FloatNumber(1);
		assertTrue(aFloatNumber.asSmallInteger().equals(aFloatNumber));
	}
	
	public void testAsLargeInteger() {
		FloatNumber aFloatNumber = new FloatNumber(1);
		LargeInteger aLargeInteger = new LargeInteger(1);
		assertTrue(aFloatNumber.asLargeInteger().equals(aLargeInteger));
	}
	
	public void testAsFloatNumber() {
		FloatNumber aFloatNumber = new FloatNumber(1);
		FloatNumber anotherFloatNumber = new FloatNumber(1);
		assertTrue(aFloatNumber.asFloatNumber().equals(anotherFloatNumber));
	}
	
	public void testAsFraction() {
		FloatNumber aFloatNumber = new FloatNumber(2);
		Fraction aFraction = new Fraction(2,1);
		assertTrue(aFloatNumber.asFraction().equals(aFraction));
	}
	
	/**
	 * Equality
	 */
	
	public void testSmallIntegerEquality() {
		FloatNumber aFloatNumber = new FloatNumber(3);
		SmallInteger anotherSmallInteger = new SmallInteger(2);
		assertFalse(aFloatNumber.equals(anotherSmallInteger));

		anotherSmallInteger = new SmallInteger(3);
		assertTrue(aFloatNumber.equals(anotherSmallInteger));
	}	
	
	public void testLargeIntegerEquality() {
		FloatNumber aFloatNumber = new FloatNumber(3);
		LargeInteger anotherLargeInteger = new LargeInteger(2);
		assertFalse(aFloatNumber.equals(anotherLargeInteger));

		anotherLargeInteger = new LargeInteger(3);
		assertTrue(aFloatNumber.equals(anotherLargeInteger));
	}
	
	public void testFloatNumberEquality() {
		FloatNumber aFloatNumber = new FloatNumber(3);
		FloatNumber anotherFloatNumber = new FloatNumber(2);
		assertFalse(aFloatNumber.equals(anotherFloatNumber));

		anotherFloatNumber = new FloatNumber(3);
		assertTrue(aFloatNumber.equals(anotherFloatNumber));
	}
	
	public void testFractionEquality() {
		FloatNumber aFloatNumber = new FloatNumber(3);
		Fraction aFraction = new Fraction(1,2);
		assertFalse(aFloatNumber.equals(aFraction));

		aFraction = new Fraction(3,1);
		assertTrue(aFloatNumber.equals(aFraction));
	}
	
	/**
	 * TODO: The idea is to go on testing all other methods specially the arithmetic operations 
	 * where the operation goes out of range. And more!!
	 */
	
	/**
	 * Addition testing
	 */
	
	public void testPlusFloatNumber() {
		FloatNumber aFloatNumber = new FloatNumber(1.2f);
		FloatNumber anotherFloatNumber = new FloatNumber(1.3f);
		Object result = aFloatNumber.plus(anotherFloatNumber);
		assertTrue(result.equals(new FloatNumber(2.5f)));
	}
	
	public void testPlusSmallInteger() {
		FloatNumber aFloatNumber = new FloatNumber(1.2f);
		SmallInteger anotherInteger = new SmallInteger(2);
		Object result = aFloatNumber.plus(anotherInteger);
		assertTrue(result.equals(new FloatNumber(3.2f)));
	}

	public void testPlusLargeInteger() {
		FloatNumber aFloatNumber = new FloatNumber(1.2f);
		ExtendedNumber anotherInteger = new LargeInteger(2);
		Object result = aFloatNumber.plus(anotherInteger);
		assertTrue(result.equals(new FloatNumber(3.2f)));
	}
	
	public void testPlusFraction() {
		FloatNumber aFloatNumber = new FloatNumber(1.5f);
		Fraction aFraction = new Fraction(1,2);
		Object result = aFloatNumber.plus(aFraction);
		assertTrue(result.equals(new Fraction(new FloatNumber(4.0f),new SmallInteger(2))));
	}
	
	/**
	 * Subtracting testing
	 */
	
	public void testMinusFloatNumber() {
		FloatNumber aFloatNumber = new FloatNumber(1.5f);
		FloatNumber anotherFloatNumber = new FloatNumber(1.0f);
		Object result = aFloatNumber.minus(anotherFloatNumber);
		assertTrue(result.equals(new FloatNumber(0.5f)));
	}
	
	public void testMinusSmallInteger() {
		FloatNumber anInteger = new FloatNumber(1.5f);
		SmallInteger anotherInteger = new SmallInteger(1);
		Object result = anInteger.minus(anotherInteger);
		assertTrue(result.equals(new FloatNumber(0.5f)));
	}

	public void testMinusLargeInteger() {
		FloatNumber aFloatNumber = new FloatNumber(1.5f);
		ExtendedNumber anotherInteger = new LargeInteger(1);
		Object result = aFloatNumber.minus(anotherInteger);
		assertTrue(result.equals(new FloatNumber(0.5f)));
	}
	
	public void testMinusFraction() {
		FloatNumber anInteger = new FloatNumber(1.5f);
		Fraction aFraction = new Fraction(1,2);
		Object result = anInteger.minus(aFraction);
		assertTrue(result.equals(new Fraction(new FloatNumber(2.0f),new SmallInteger(2))));
	}
	
	/**
	 * Multiplying testing
	 */
	public void testMultiplyByFloatNumber() {
		FloatNumber aFloatNumber = new FloatNumber(1.5f);
		FloatNumber anotherFloatNumber = new FloatNumber(2.0f);
		Object result = aFloatNumber.multiplyBy(anotherFloatNumber);
		assertTrue(result.equals(new FloatNumber(3.0f)));
		
		anotherFloatNumber = new FloatNumber(0);
		result = aFloatNumber.multiplyBy(anotherFloatNumber);
		assertTrue(result.equals(new FloatNumber(0)));
		
		anotherFloatNumber = new FloatNumber(1);
		result = aFloatNumber.multiplyBy(anotherFloatNumber);
		assertTrue(result.equals(aFloatNumber));
	}
	
	public void testMultiplyBySmallInteger() {
		FloatNumber aFloatNumber = new FloatNumber(1.5f);
		SmallInteger anotherInteger = new SmallInteger(2);
		Object result = aFloatNumber.multiplyBy(anotherInteger);
		assertTrue(result.equals(new FloatNumber(3.0f)));
		
		anotherInteger = new SmallInteger(0);
		result = aFloatNumber.multiplyBy(anotherInteger);
		assertTrue(result.equals(new FloatNumber(0)));
		
		anotherInteger = new SmallInteger(1);
		result = aFloatNumber.multiplyBy(anotherInteger);
		assertTrue(result.equals(aFloatNumber));
	}

	public void testMultiplyByLargeInteger() {
		FloatNumber aFloatNumber = new FloatNumber(1.5f);
		ExtendedNumber anotherInteger = new LargeInteger(2);
		Object result = aFloatNumber.multiplyBy(anotherInteger);
		assertTrue(result.equals(new FloatNumber(3.0f)));
		
		anotherInteger = new LargeInteger(0);
		result = aFloatNumber.multiplyBy(anotherInteger);
		assertTrue(result.equals(new FloatNumber(0)));
		
		anotherInteger = new LargeInteger(1);
		result = aFloatNumber.multiplyBy(anotherInteger);
		assertTrue(result.equals(aFloatNumber));
	}
	
	public void testMultiplyByFraction() {
		FloatNumber aFloatNumber = new FloatNumber(0.1f);
		Fraction aFraction = new Fraction(1,2);
		Object result = aFloatNumber.multiplyBy(aFraction);
		assertTrue(result.equals(new Fraction(new FloatNumber(0.1f),new SmallInteger(2))));
		
		aFraction = new Fraction(1,aFloatNumber);
		result = aFloatNumber.multiplyBy(aFraction);
		assertTrue(result.equals(new SmallInteger(1)));
	}
	
	/**
	 * Quotient Testing 
	 */
	
	public void testQuotientSmallInteger() {
		FloatNumber aFloatNumber = new FloatNumber(1);
		SmallInteger anotherInteger = new SmallInteger(2);
		Object result = aFloatNumber.quotient(anotherInteger);
		assertTrue(result.equals(new FloatNumber(0.5f)));
		
		anotherInteger = new SmallInteger(1);
		result = aFloatNumber.quotient(anotherInteger);
		assertTrue(result.equals(new FloatNumber(1f)));
	}
	
	public void testQuotientLargeInteger() {
		FloatNumber aFloatNumber = new FloatNumber(1);
		LargeInteger anotherInteger = new LargeInteger(2);
		Object result = aFloatNumber.quotient(anotherInteger);
		assertTrue(result.equals(new FloatNumber(0.5f)));
		
		anotherInteger = new LargeInteger(1);
		result = aFloatNumber.quotient(anotherInteger);
		assertTrue(result.equals(new FloatNumber(1f)));
	}
	
	public void testQuotientFloatNumber() {
		FloatNumber aFloatNumber = new FloatNumber(1);
		FloatNumber anotherFloatNumber = new FloatNumber(2);
		Object result = aFloatNumber.quotient(anotherFloatNumber);
		assertTrue(result.equals(new FloatNumber(0.5f)));
		
		anotherFloatNumber = new FloatNumber(1);
		result = aFloatNumber.quotient(anotherFloatNumber);
		assertTrue(result.equals(new FloatNumber(1f)));
	}
	
	public void testQuotientFraction() {
		FloatNumber aFloatNumber = new FloatNumber(1);
		Fraction aFraction = new Fraction(1,2);
		Object result = aFloatNumber.quotient(aFraction);
		assertTrue(result.equals(new FloatNumber(2f)));
		
		aFraction = new Fraction(2,1);
		result = aFloatNumber.quotient(aFraction);
		assertTrue(result.equals(new FloatNumber(0.5f)));
	}
	
	/**
	 * Division Testing 
	 */
	
	public void testDivideBySmallInteger() {
		FloatNumber aFloatNumber = new FloatNumber(2);
		SmallInteger anotherInteger = new SmallInteger(1);
		Object result = aFloatNumber.divideBy(anotherInteger);
		assertTrue(result.equals(new SmallInteger(2)));
		
		anotherInteger = new SmallInteger(2);
		result = aFloatNumber.divideBy(anotherInteger);
		assertTrue(result.equals(new SmallInteger(1)));
		
		anotherInteger = new SmallInteger(3);
		result = aFloatNumber.divideBy(anotherInteger);
		assertTrue(result.equals(new Fraction(2,3)));
		
		anotherInteger = new SmallInteger(0);
		Exception anException = null;
		try {
			result = aFloatNumber.divideBy(anotherInteger);
		} catch (DivisionByZeroException ex) {
			anException = ex;
		}
		if (anException == null) {
			fail();
		}
	}

	public void testDivideByLargeInteger() {
		FloatNumber aFloatNumber = new FloatNumber(2);
		LargeInteger anotherInteger = new LargeInteger(1);
		Object result = aFloatNumber.divideBy(anotherInteger);
		assertTrue(result.equals(new SmallInteger(2)));
		
		anotherInteger = new LargeInteger(2);
		result = aFloatNumber.divideBy(anotherInteger);
		assertTrue(result.equals(new SmallInteger(1)));
		
		anotherInteger = new LargeInteger(3);
		result = aFloatNumber.divideBy(anotherInteger);
		assertTrue(result.equals(new Fraction(2,3)));
		
		anotherInteger = new LargeInteger(0);
		Exception anException = null;
		try {
			result = aFloatNumber.divideBy(anotherInteger);
		} catch (DivisionByZeroException ex) {
			anException = ex;
		}
		if (anException == null) {
			fail();
		}
	}
	
	public void testDivideByFloatNumber() {
		FloatNumber aFloatNumber = new FloatNumber(2);
		FloatNumber anotherFloatNumber = new FloatNumber(1);
		Object result = aFloatNumber.divideBy(anotherFloatNumber);
		assertTrue(result.equals(new SmallInteger(2)));
		
		anotherFloatNumber = new FloatNumber(2);
		result = aFloatNumber.divideBy(anotherFloatNumber);
		assertTrue(result.equals(new SmallInteger(1)));
		
		anotherFloatNumber = new FloatNumber(3);
		result = aFloatNumber.divideBy(anotherFloatNumber);
		assertTrue(result.equals(new Fraction(2,3)));
		
		anotherFloatNumber = new FloatNumber(0);
		Exception anException = null;
		try {
			result = aFloatNumber.divideBy(anotherFloatNumber);
		} catch (DivisionByZeroException ex) {
			anException = ex;
		}
		if (anException == null) {
			fail();
		}
	}
	
	public void testDivideByFraction() {
		FloatNumber aFloatNumber = new FloatNumber(2);
		Fraction aFraction = new Fraction(1,2);
		Object result = aFloatNumber.divideBy(aFraction);
		assertTrue(result.equals(new SmallInteger(4)));
		
		aFraction = new Fraction(2,1);
		result = aFloatNumber.divideBy(aFraction);
		assertTrue(result.equals(new SmallInteger(1)));
		
		aFraction = new Fraction(3,1);
		result = aFloatNumber.divideBy(aFraction);
		assertTrue(result.equals(new Fraction(2,3)));
		
		aFraction = new Fraction(0,1);
		Exception anException = null;
		try {
			result = aFloatNumber.divideBy(aFraction);
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
		assertTrue((new FloatNumber(2)).abs().equals(new FloatNumber(2)));
		assertTrue((new FloatNumber(0)).abs().equals(new FloatNumber(0)));
		assertTrue((new FloatNumber(-1)).abs().equals(new FloatNumber(1)));
	}
	
	/**
	 * Testing Magnitude Protocol
	 */
	
	
	// >
	public void testGreaterThanSmallInteger() {
		FloatNumber aFloat = new FloatNumber(2);
		SmallInteger anotherInteger = new SmallInteger(1);
		assertTrue(aFloat.greaterThan(anotherInteger));
		assertFalse(anotherInteger.greaterThan(aFloat));
		assertFalse(aFloat.greaterThan(aFloat));
	}

	public void testGreaterThanLargeInteger() {
		FloatNumber aFloat = new FloatNumber(2);
		LargeInteger aLargeInteger = new LargeInteger(1);
		LargeInteger anotherLargeInteger = new LargeInteger(3);
		LargeInteger sameLargeInteger = new LargeInteger(2);
		assertTrue(aFloat.greaterThan(aLargeInteger));
		assertFalse(aFloat.greaterThan(anotherLargeInteger));
		assertFalse(aFloat.greaterThan(sameLargeInteger));
	}
	
	public void testGreaterThanFraction() {
		FloatNumber aFloat = new FloatNumber(2);
		Fraction aFraction = new Fraction(1,2);
		Fraction anotherFraction = new Fraction(5,2);
		Fraction sameFraction = new Fraction(4,2);
		assertTrue(aFloat.greaterThan(aFraction));
		assertFalse(aFloat.greaterThan(anotherFraction));
		assertFalse(aFloat.greaterThan(sameFraction));
	}
	
	public void testGreaterThanFloatNumber() {
		FloatNumber aFloat = new FloatNumber(2);
		FloatNumber aFloat2 = new FloatNumber(1);
		FloatNumber anotherFloat = new FloatNumber(3.2f);
		FloatNumber sameFloat = new FloatNumber(2);
		assertTrue(aFloat.greaterThan(aFloat2));
		assertFalse(aFloat.greaterThan(anotherFloat));
		assertFalse(aFloat.greaterThan(sameFloat));
	}
	
	// >=
	public void testGreaterOrEqualThanSmallInteger() {
		FloatNumber aFloat = new FloatNumber(2);
		SmallInteger anotherInteger = new SmallInteger(1);
		assertTrue(aFloat.greaterThanOrEqualTo(anotherInteger));
		assertFalse(anotherInteger.greaterThanOrEqualTo(aFloat));
		assertTrue(aFloat.greaterThanOrEqualTo(aFloat));
	}

	public void testGreaterOrEqualThanLargeInteger() {
		FloatNumber aFloat = new FloatNumber(2);
		LargeInteger aLargeInteger = new LargeInteger(1);
		LargeInteger anotherLargeInteger = new LargeInteger(3);
		LargeInteger sameLargeInteger = new LargeInteger(2);
		assertTrue(aFloat.greaterThanOrEqualTo(aLargeInteger));
		assertFalse(aFloat.greaterThanOrEqualTo(anotherLargeInteger));
		assertTrue(aFloat.greaterThanOrEqualTo(sameLargeInteger));
	}
	
	public void testGreaterOrEqualThanFraction() {
		FloatNumber aFloat = new FloatNumber(2);
		Fraction aFraction = new Fraction(1,2);
		Fraction anotherFraction = new Fraction(5,2);
		Fraction sameFraction = new Fraction(4,2);
		assertTrue(aFloat.greaterThanOrEqualTo(aFraction));
		assertFalse(aFloat.greaterThanOrEqualTo(anotherFraction));
		assertTrue(aFloat.greaterThanOrEqualTo(sameFraction));
	}
	
	public void testGreaterOrEqualThanFloatNumber() {
		FloatNumber aFloat = new FloatNumber(2);
		FloatNumber aFloat2 = new FloatNumber(1);
		FloatNumber anotherFloat = new FloatNumber(3.2f);
		FloatNumber sameFloat = new FloatNumber(2);
		assertTrue(aFloat.greaterThanOrEqualTo(aFloat2));
		assertFalse(aFloat.greaterThanOrEqualTo(anotherFloat));
		assertTrue(aFloat.greaterThanOrEqualTo(sameFloat));
	}
	
	// <
	public void testLessThanSmallInteger() {
		FloatNumber aFloat = new FloatNumber(2);
		SmallInteger anotherInteger = new SmallInteger(1);
		assertFalse(aFloat.lessThan(anotherInteger));
		assertTrue(anotherInteger.lessThan(aFloat));
		assertFalse(aFloat.lessThan(aFloat));
	}

	public void testLessThanLargeInteger() {
		FloatNumber aFloat = new FloatNumber(2);
		LargeInteger aLargeInteger = new LargeInteger(1);
		LargeInteger anotherLargeInteger = new LargeInteger(3);
		LargeInteger sameLargeInteger = new LargeInteger(2);
		assertFalse(aFloat.lessThan(aLargeInteger));
		assertTrue(aFloat.lessThan(anotherLargeInteger));
		assertFalse(aFloat.lessThan(sameLargeInteger));
	}
	
	public void testLessThanFraction() {
		FloatNumber aFloat = new FloatNumber(2);
		Fraction aFraction = new Fraction(1,2);
		Fraction anotherFraction = new Fraction(5,2);
		Fraction sameFraction = new Fraction(4,2);
		assertFalse(aFloat.lessThan(aFraction));
		assertTrue(aFloat.lessThan(anotherFraction));
		assertFalse(aFloat.lessThan(sameFraction));
	}
	
	public void testLessThanFloatNumber() {
		FloatNumber aFloat = new FloatNumber(2);
		FloatNumber aFloat2 = new FloatNumber(1);
		FloatNumber anotherFloat = new FloatNumber(3.2f);
		FloatNumber sameFloat = new FloatNumber(2);
		assertFalse(aFloat.lessThan(aFloat2));
		assertTrue(aFloat.lessThan(anotherFloat));
		assertFalse(aFloat.lessThan(sameFloat));
	}
	
	// <=
	public void testLessOrEqualThanSmallInteger() {
		FloatNumber aFloat = new FloatNumber(2);
		SmallInteger anotherInteger = new SmallInteger(1);
		assertFalse(aFloat.lessThanOrEqualTo(anotherInteger));
		assertTrue(anotherInteger.lessThanOrEqualTo(aFloat));
		assertTrue(aFloat.lessThanOrEqualTo(aFloat));
	}

	public void testLessOrEqualThanLargeInteger() {
		FloatNumber aFloat = new FloatNumber(2);
		LargeInteger aLargeInteger = new LargeInteger(1);
		LargeInteger anotherLargeInteger = new LargeInteger(3);
		LargeInteger sameLargeInteger = new LargeInteger(2);
		assertFalse(aFloat.lessThanOrEqualTo(aLargeInteger));
		assertTrue(aFloat.lessThanOrEqualTo(anotherLargeInteger));
		assertTrue(aFloat.lessThanOrEqualTo(sameLargeInteger));
	}
	
	public void testLessOrEqualThanFraction() {
		FloatNumber aFloat = new FloatNumber(2);
		Fraction aFraction = new Fraction(1,2);
		Fraction anotherFraction = new Fraction(5,2);
		Fraction sameFraction = new Fraction(4,2);
		assertFalse(aFloat.lessThanOrEqualTo(aFraction));
		assertTrue(aFloat.lessThanOrEqualTo(anotherFraction));
		assertTrue(aFloat.lessThanOrEqualTo(sameFraction));
	}
	
	public void testLessOrEqualThanFloatNumber() {
		FloatNumber aFloat = new FloatNumber(2);
		FloatNumber aFloat2 = new FloatNumber(1);
		FloatNumber anotherFloat = new FloatNumber(3.2f);
		FloatNumber sameFloat = new FloatNumber(2);
		assertFalse(aFloat.lessThanOrEqualTo(aFloat2));
		assertTrue(aFloat.lessThanOrEqualTo(anotherFloat));
		assertTrue(aFloat.lessThanOrEqualTo(sameFloat));
	}
	
	public void testNegated() {
		FloatNumber aFloat = new FloatNumber(1);
		FloatNumber anotherFloat = new FloatNumber(-1);
		FloatNumber zeroFloat = new FloatNumber(0);
		assertTrue(aFloat.negated().equals(anotherFloat));
		assertTrue(anotherFloat.negated().equals(aFloat));
		assertTrue(zeroFloat.negated().equals(zeroFloat));
	}
	
	public void testTruncated() {
		assertTrue((new FloatNumber(1.05)).truncated().equals(new SmallInteger(1)));
		assertTrue((new FloatNumber(1.05)).truncated().equals(new FloatNumber(1)));
		
		assertTrue((new FloatNumber(1)).truncated().equals(new SmallInteger(1)));
		assertTrue((new FloatNumber(1)).truncated().equals(new FloatNumber(1)));
	}
		
	public void testSameUnitAs() {
		assertTrue((new FloatNumber(1)).sameUnitAs(new FloatNumber(1)));
		assertTrue((new FloatNumber(2)).sameUnitAs(new SmallInteger(1)));
		assertTrue((new FloatNumber(2)).sameUnitAs(new LargeInteger(1)));
		assertTrue((new FloatNumber(2)).sameUnitAs(new Fraction(1,2)));
		assertTrue((new FloatNumber(2)).sameUnitAs(new FloatNumber(1.05f)));
	}
}
