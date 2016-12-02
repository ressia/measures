package test.measureModel.numbers;

import com.measureModel.numbers.ExtendedNumber;
import com.measureModel.numbers.FloatNumber;
import com.measureModel.numbers.SmallInteger;
import com.measureModel.numbers.LargeInteger;
import com.measureModel.numbers.Fraction;
import com.measureModel.numbers.exceptions.DivisionByZeroException;

import junit.framework.TestCase;

public class FractionTest extends TestCase {
	
	public void testFractionWithSmallIntegers() {
		ExtendedNumber aNumber = new SmallInteger(1);
		ExtendedNumber anotherNumber = new SmallInteger(2);
		Fraction aFraction = new Fraction(aNumber,anotherNumber);
		
		assertTrue(aFraction.numerator().equals(aNumber));
		assertTrue(aFraction.denominator().equals(anotherNumber));
	}
	
	public void testFractionWithLargeIntegers() {
		ExtendedNumber aNumber = new LargeInteger(1);
		ExtendedNumber anotherNumber = new LargeInteger(2);
		Fraction aFraction = new Fraction(aNumber,anotherNumber);
		
		assertTrue(aFraction.numerator().equals(aNumber));
		assertTrue(aFraction.denominator().equals(anotherNumber));
	}

	public void testFractionWithInts() {
		ExtendedNumber aNumber = new SmallInteger(1);
		ExtendedNumber anotherNumber = new SmallInteger(2);
		Fraction aFraction = new Fraction(1,2);
		
		assertTrue(aFraction.numerator().equals(aNumber));
		assertTrue(aFraction.denominator().equals(anotherNumber));
	}
	
	public void testFractionDivisionByZero() {
		Exception anException = null;
		try {
			new Fraction(1,0);
		} catch (DivisionByZeroException ex) {
			anException = ex;
		}
		if (anException == null) {
			fail();
		}
	}
	
	public void testFractionNumberNumber() {
		SmallInteger anInteger = new SmallInteger(1);
		SmallInteger anotherInteger = new SmallInteger(2);
		
		ExtendedNumber aNumber = (ExtendedNumber)Fraction.numberNumber(anInteger, anotherInteger);
		assertTrue(aNumber.equals(new Fraction(1,2)));
		
		aNumber = (ExtendedNumber)Fraction.numberNumber(anInteger, anInteger);
		assertTrue(aNumber.equals(new SmallInteger(1)));
		
		aNumber = (ExtendedNumber)Fraction.numberNumber(new SmallInteger(0), anInteger);
		assertTrue(aNumber.equals(new SmallInteger(0)));
		
		aNumber = (ExtendedNumber)Fraction.numberNumber(anotherInteger, anInteger);
		assertTrue(aNumber.equals(new SmallInteger(2)));
		Exception anException = null;
		try {
			Fraction.numberNumber(anInteger,new SmallInteger(0));
		} catch (DivisionByZeroException ex) {
			anException = ex;
		}
		if (anException == null) {
			fail();
		}
	}
	
	public void testDenominator() {
		Fraction aFraction = new Fraction(1,2);
		assertTrue(aFraction.denominator().equals(new SmallInteger(2)));
	}
	
	public void testNumerator() {
		Fraction aFraction = new Fraction(1,2);
		assertTrue(aFraction.numerator().equals(new SmallInteger(1)));
	}
	
	public void testReciprocal() {
		Fraction aFraction = new Fraction(3,2);
		Fraction anotherFraction = new Fraction(2,3);
		assertTrue(aFraction.reciprocal().equals(anotherFraction));
	}
	
	/**
	 * Transformations
	 */
	public void testAsSmallInteger() {
		Fraction aFraction = new Fraction(2,1);
		SmallInteger aSmallInteger = new SmallInteger(2);
		assertTrue(aFraction.asSmallInteger().equals(aSmallInteger));
		
		aFraction = new Fraction(1,2);
		aSmallInteger = new SmallInteger(0);
		assertTrue(aFraction.asSmallInteger().equals(aSmallInteger));
		
		aFraction = new Fraction(3,2);
		aSmallInteger = new SmallInteger(1);
		assertTrue(aFraction.asSmallInteger().equals(aSmallInteger));
	}
	
	public void testAsLargeInteger() {
		Fraction aFraction = new Fraction(2,1);
		LargeInteger aLargeInteger = new LargeInteger(2);
		assertTrue(aFraction.asLargeInteger().equals(aLargeInteger));
		
		aFraction = new Fraction(1,2);
		aLargeInteger = new LargeInteger(0);
		assertTrue(aFraction.asLargeInteger().equals(aLargeInteger));
		
		aFraction = new Fraction(3,2);
		aLargeInteger = new LargeInteger(1);
		assertTrue(aFraction.asLargeInteger().equals(aLargeInteger));
	}
	
	public void testAsFloatNumber() {
		Fraction aFraction = new Fraction(1,2);
		FloatNumber aFloatNumber = new FloatNumber(0.5f);
		assertTrue(aFraction.asFloatNumber().equals(aFloatNumber));
		
		aFraction = new Fraction(2,1);
		aFloatNumber = new FloatNumber(2);
		assertTrue(aFraction.asFloatNumber().equals(aFloatNumber));
		
		aFraction = new Fraction(3,2);
		aFloatNumber = new FloatNumber(1.5f);
		assertTrue(aFraction.asFloatNumber().equals(aFloatNumber));
	}
	
	public void testAsFraction() {
		Fraction aFraction = new Fraction(1,2);
		assertTrue(aFraction.asFraction().equals(aFraction));
	}	
	
	/**
	 * Equality
	 */
	public void testFractionEquality() {
		Fraction aFraction = new Fraction(1,2);
		Fraction anotherFraction = new Fraction(2,4);
		assertTrue(aFraction.equals(anotherFraction));
		
		anotherFraction = new Fraction(2,5);
		assertFalse(aFraction.equals(anotherFraction));
	}
	
	public void testSmallIntegerEquality() {
		Fraction aFraction = new Fraction(1,2);
		SmallInteger aSmallInteger = new SmallInteger(1);
		assertFalse(aFraction.equals(aSmallInteger));
		
		aFraction = new Fraction(2,2);
		assertTrue(aFraction.equals(aSmallInteger));
	}
	
	public void testLargeIntegerEquality() {
		Fraction aFraction = new Fraction(1,2);
		ExtendedNumber aLargeInteger = new LargeInteger(1);
		assertFalse(aFraction.equals(aLargeInteger));
		
		aFraction = new Fraction(2,2);
		assertTrue(aFraction.equals(aLargeInteger));
	}
	
	public void testFloatNumberEquality() {
		Fraction aFraction = new Fraction(1,2);
		FloatNumber aFloatNumber = new FloatNumber(1);
		assertFalse(aFraction.equals(aFloatNumber));
		
		aFraction = new Fraction(2,2);
		assertTrue(aFraction.equals(aFloatNumber));
	}
	
	/**
	 * Quering testing
	 */
	
	public void testIsOne() {
		Fraction aFraction = new Fraction(1,2);
		assertFalse(aFraction.isOne());
		
		aFraction = new Fraction(2,2);
		assertTrue(aFraction.isOne());
	}
	
	public void testIsZero() {
		Fraction aFraction = new Fraction(1,2);
		assertFalse(aFraction.isZero());
		
		aFraction = new Fraction(0,2);
		assertTrue(aFraction.isZero());
	}
	
	public void testIsNothing() {
		Fraction aFraction = new Fraction(1,2);
		assertFalse(aFraction.isNothing());
		
		aFraction = new Fraction(0,2);
		assertTrue(aFraction.isNothing());
	}
	
	/**
	 * Addition testing
	 */
	
	public void testPlusLargeInteger() {
		ExtendedNumber anInteger = new LargeInteger(1);
		Fraction aFraction = new Fraction(1,2);
		Object result = aFraction.plus(anInteger);
		assertTrue(result.equals(new Fraction(new LargeInteger(3),new SmallInteger(2))));
	}
	
	public void testPlusSmallInteger() {
		SmallInteger anInteger = new SmallInteger(1);
		Fraction aFraction = new Fraction(1,2);
		Object result = aFraction.plus(anInteger);
		assertTrue(result.equals(new Fraction(3,2)));
	}
	
	public void testPlusFloatNumber() {
		FloatNumber anInteger = new FloatNumber(1.2f);
		Fraction aFraction = new Fraction(1,2);
		Object result = aFraction.plus(anInteger);
		assertTrue(result.equals(new Fraction(new FloatNumber(3.4f),new SmallInteger(2))));
	}
	
	public void testPlusFraction() {
		Fraction aFraction = new Fraction(1,2);
		Fraction anotherFraction = new Fraction(1,3);
		Object result = aFraction.plus(anotherFraction);
		assertTrue(result.equals(new Fraction(5,6)));
	}
	
	/**
	 * Subtraction Testing 
	 */
	
	public void testMinusLargeInteger() {
		ExtendedNumber anInteger = new LargeInteger(1);
		Fraction aFraction = new Fraction(3,2);
		Object result = aFraction.minus(anInteger);
		assertTrue(result.equals(new Fraction(new LargeInteger(1),new SmallInteger(2))));
	}
	
	public void testMinusSmallInteger() {
		SmallInteger anInteger = new SmallInteger(1);
		Fraction aFraction = new Fraction(3,2);
		Object result = aFraction.minus(anInteger);
		assertTrue(result.equals(new Fraction(1,2)));
	}
	
	public void testMinusFloatNumber() {
		FloatNumber anInteger = new FloatNumber(0.5f);
		Fraction aFraction = new Fraction(4,3);
		Object result = aFraction.minus(anInteger);
		assertTrue(result.equals(new Fraction(new FloatNumber(2.5f),new SmallInteger(3))));
	}
	
	public void testMinusFraction() {
		Fraction aFraction = new Fraction(1,3);
		Fraction anotherFraction = new Fraction(1,2);
		Object result = aFraction.minus(anotherFraction);
		assertTrue(result.equals(new Fraction(-1,6)));
		assertTrue(result.equals(new Fraction(1,-6)));
	}
	
	/**
	 * Multiplying testing
	 */
	
	public void testMultiplyBySmallInteger() {
		Fraction aFraction = new Fraction(1,2);
		SmallInteger aSmallInteger = new SmallInteger(2);
		Object result = aFraction.multiplyBy(aSmallInteger);
		assertTrue(result.equals(new Fraction(2,2)));
		
		aSmallInteger = new SmallInteger(3);
		result = aFraction.multiplyBy(aSmallInteger);
		assertTrue(result.equals(new Fraction(3,2)));
		
		aSmallInteger = new SmallInteger(0);
		result = aFraction.multiplyBy(aSmallInteger);
		assertTrue(result.equals(new SmallInteger(0)));
		
		aSmallInteger = new SmallInteger(1);
		result = aFraction.multiplyBy(aSmallInteger);
		assertTrue(result.equals(aFraction));
	}
	
	public void testMultiplyByLargeInteger() {
		Fraction aFraction = new Fraction(1,2);
		LargeInteger anInteger = new LargeInteger(2);
		Object result = aFraction.multiplyBy(anInteger);
		assertTrue(result.equals(new Fraction(2,2)));
		
		anInteger = new LargeInteger(3);
		result = aFraction.multiplyBy(anInteger);
		assertTrue(result.equals(new Fraction(3,2)));
		
		anInteger = new LargeInteger(0);
		result = aFraction.multiplyBy(anInteger);
		assertTrue(result.equals(new SmallInteger(0)));
		
		anInteger = new LargeInteger(1);
		result = aFraction.multiplyBy(anInteger);
		assertTrue(result.equals(aFraction));
	}
	
	public void testMultiplyByFloatNumber() {
		Fraction aFraction = new Fraction(1,2);
		FloatNumber aFloatNumber = new FloatNumber(2);
		Object result = aFraction.multiplyBy(aFloatNumber);
		assertTrue(result.equals(new Fraction(2,2)));
		
		aFloatNumber = new FloatNumber(3.5f);
		result = aFraction.multiplyBy(aFloatNumber);
		assertTrue(result.equals(new Fraction(new FloatNumber(3.5f),2)));
		
		aFloatNumber = new FloatNumber(0);
		result = aFraction.multiplyBy(aFloatNumber);
		assertTrue(result.equals(new SmallInteger(0)));
		
		aFloatNumber = new FloatNumber(1);
		result = aFraction.multiplyBy(aFloatNumber);
		assertTrue(result.equals(aFraction));
	}
	
	public void testMultiplyByFraction() {
		Fraction aFraction = new Fraction(1,2);
		Fraction anotherFraction = new Fraction(1,2);
		Object result = aFraction.multiplyBy(anotherFraction);
		assertTrue(result.equals(new Fraction(1,4)));
		
		anotherFraction = new Fraction(2,1);
		result = aFraction.multiplyBy(anotherFraction);
		assertTrue(result.equals(new SmallInteger(1)));
		
		anotherFraction = new Fraction(0,1);
		result = aFraction.multiplyBy(anotherFraction);
		assertTrue(result.equals(new SmallInteger(0)));

	}
	
	/**
	 * Quotient Testing 
	 */
	
	public void testQuotientSmallInteger() {
		Fraction aFraction = new Fraction(1,2);
		SmallInteger anotherInteger = new SmallInteger(1);
		Object result = aFraction.quotient(anotherInteger);
		assertTrue(result.equals(new FloatNumber(0.5f)));
		
		anotherInteger = new SmallInteger(2);
		result = aFraction.quotient(anotherInteger);
		assertTrue(result.equals(new FloatNumber(0.25f)));
	}
	
	public void testQuotientLargeInteger() {
		Fraction aFraction = new Fraction(1,2);
		LargeInteger anInteger = new LargeInteger(1);
		Object result = aFraction.quotient(anInteger);
		assertTrue(result.equals(new FloatNumber(0.5f)));
		
		anInteger = new LargeInteger(2);
		result = aFraction.quotient(anInteger);
		assertTrue(result.equals(new FloatNumber(0.25f)));
	}
	
	public void testQuotientFloatNumber() {
		Fraction aFraction = new Fraction(1,2);
		FloatNumber aFloatNumber = new FloatNumber(1);
		Object result = aFraction.quotient(aFloatNumber);
		assertTrue(result.equals(new FloatNumber(0.5f)));
		
		aFloatNumber = new FloatNumber(2);
		result = aFraction.quotient(aFloatNumber);
		assertTrue(result.equals(new FloatNumber(0.25f)));
	}
	
	public void testQuotientFraction() {
		Fraction aFraction = new Fraction(1,2);
		Fraction anotherFraction = new Fraction(1,2);
		Object result = aFraction.quotient(anotherFraction);
		assertTrue(result.equals(new FloatNumber(1f)));
		
		anotherFraction = new Fraction(2,1);
		result = aFraction.quotient(anotherFraction);
		assertTrue(result.equals(new FloatNumber(0.25f)));
	}
	
	/**
	 * Division Testing 
	 */
	
	public void testDivideBySmallInteger() {
		Fraction aFraction = new Fraction(1,2);
		SmallInteger anotherInteger = new SmallInteger(1);
		Object result = aFraction.divideBy(anotherInteger);
		assertTrue(result.equals(aFraction));
		
		anotherInteger = new SmallInteger(2);
		result = aFraction.divideBy(anotherInteger);
		assertTrue(result.equals(new Fraction(1,4)));
		
		anotherInteger = new SmallInteger(3);
		result = aFraction.divideBy(anotherInteger);
		assertTrue(result.equals(new Fraction(1,6)));
		
		anotherInteger = new SmallInteger(0);
		Exception anException = null;
		try {
			result = aFraction.divideBy(anotherInteger);
		} catch (DivisionByZeroException ex) {
			anException = ex;
		}
		if (anException == null) {
			fail();
		}
	}

	public void testDivideByLargeInteger() {
		Fraction aFraction = new Fraction(1,2);
		LargeInteger anotherInteger = new LargeInteger(1);
		Object result = aFraction.divideBy(anotherInteger);
		assertTrue(result.equals(aFraction));
		
		anotherInteger = new LargeInteger(2);
		result = aFraction.divideBy(anotherInteger);
		assertTrue(result.equals(new Fraction(1,4)));
		
		anotherInteger = new LargeInteger(3);
		result = aFraction.divideBy(anotherInteger);
		assertTrue(result.equals(new Fraction(1,6)));
		
		anotherInteger = new LargeInteger(0);
		Exception anException = null;
		try {
			result = aFraction.divideBy(anotherInteger);
		} catch (DivisionByZeroException ex) {
			anException = ex;
		}
		if (anException == null) {
			fail();
		}
	}
	
	public void testDivideByFloatNumber() {
		Fraction aFraction = new Fraction(1,2);
		FloatNumber anotherFloatNumber = new FloatNumber(1);
		Object result = aFraction.divideBy(anotherFloatNumber);
		assertTrue(result.equals(aFraction));
		
		anotherFloatNumber = new FloatNumber(2);
		result = aFraction.divideBy(anotherFloatNumber);
		assertTrue(result.equals(new Fraction(1,4)));
		
		anotherFloatNumber = new FloatNumber(3);
		result = aFraction.divideBy(anotherFloatNumber);
		assertTrue(result.equals(new Fraction(1,6)));
		
		anotherFloatNumber = new FloatNumber(0);
		Exception anException = null;
		try {
			result = aFraction.divideBy(anotherFloatNumber);
		} catch (DivisionByZeroException ex) {
			anException = ex;
		}
		if (anException == null) {
			fail();
		}
	}
	
	public void testDivideByFraction() {
		Fraction aFraction = new Fraction(1,2);
		Fraction anotherFraction = new Fraction(1,2);
		Object result = aFraction.divideBy(anotherFraction);
		assertTrue(result.equals(new SmallInteger(1)));
		
		anotherFraction = new Fraction(2,1);
		result = aFraction.divideBy(anotherFraction);
		assertTrue(result.equals(new Fraction(1,4)));
		
		anotherFraction = new Fraction(3,1);
		result = aFraction.divideBy(anotherFraction);
		assertTrue(result.equals(new Fraction(1,6)));
		
		anotherFraction = new Fraction(0,1);
		Exception anException = null;
		try {
			result = aFraction.divideBy(anotherFraction);
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
		assertTrue((new Fraction(1,2)).abs().equals(new Fraction(1,2)));
		assertTrue((new Fraction(0,1)).abs().equals(new Fraction(0,1)));
		assertTrue((new Fraction(-1,2)).abs().equals(new Fraction(1,2)));
	}
	
	/**
	 * Testing Magnitude Protocol
	 */
	
	
	// >
	public void testGreaterThanSmallInteger() {
		Fraction aFraction = new Fraction(4,2);
		SmallInteger anotherInteger = new SmallInteger(1);
		assertTrue(aFraction.greaterThan(anotherInteger));
		assertFalse(anotherInteger.greaterThan(aFraction));
		assertFalse(aFraction.greaterThan(aFraction));
	}

	public void testGreaterThanLargeInteger() {
		Fraction aFraction = new Fraction(4,2);
		LargeInteger aLargeInteger = new LargeInteger(1);
		LargeInteger anotherLargeInteger = new LargeInteger(3);
		LargeInteger sameLargeInteger = new LargeInteger(2);
		assertTrue(aFraction.greaterThan(aLargeInteger));
		assertFalse(aFraction.greaterThan(anotherLargeInteger));
		assertFalse(aFraction.greaterThan(sameLargeInteger));
	}
	
	public void testGreaterThanFraction() {
		Fraction aFraction = new Fraction(4,2);
		Fraction aFraction2 = new Fraction(1,2);
		Fraction anotherFraction = new Fraction(5,2);
		Fraction sameFraction = new Fraction(8,4);
		assertTrue(aFraction.greaterThan(aFraction2));
		assertFalse(aFraction.greaterThan(anotherFraction));
		assertFalse(aFraction.greaterThan(sameFraction));
	}
	
	public void testGreaterThanFloatNumber() {
		Fraction aFraction = new Fraction(4,2);
		FloatNumber aFloat = new FloatNumber(1);
		FloatNumber anotherFloat = new FloatNumber(3.2f);
		FloatNumber sameFloat = new FloatNumber(2);
		assertTrue(aFraction.greaterThan(aFloat));
		assertFalse(aFraction.greaterThan(anotherFloat));
		assertFalse(aFraction.greaterThan(sameFloat));
	}
	
	// >=
	public void testGreaterOrEqualThanSmallInteger() {
		Fraction aFraction = new Fraction(4,2);
		SmallInteger anotherInteger = new SmallInteger(1);
		assertTrue(aFraction.greaterThanOrEqualTo(anotherInteger));
		assertFalse(anotherInteger.greaterThanOrEqualTo(aFraction));
		assertTrue(aFraction.greaterThanOrEqualTo(aFraction));
	}

	public void testGreaterOrEqualThanLargeInteger() {
		Fraction aFraction = new Fraction(4,2);
		LargeInteger aLargeInteger = new LargeInteger(1);
		LargeInteger anotherLargeInteger = new LargeInteger(3);
		LargeInteger sameLargeInteger = new LargeInteger(2);
		assertTrue(aFraction.greaterThanOrEqualTo(aLargeInteger));
		assertFalse(aFraction.greaterThanOrEqualTo(anotherLargeInteger));
		assertTrue(aFraction.greaterThanOrEqualTo(sameLargeInteger));
	}
	
	public void testGreaterOrEqualThanFraction() {
		Fraction aFraction = new Fraction(4,2);
		Fraction aFraction2 = new Fraction(1,2);
		Fraction anotherFraction = new Fraction(5,2);
		Fraction sameFraction = new Fraction(8,4);
		assertTrue(aFraction.greaterThanOrEqualTo(aFraction2));
		assertFalse(aFraction.greaterThanOrEqualTo(anotherFraction));
		assertTrue(aFraction.greaterThanOrEqualTo(sameFraction));
	}
	
	public void testGreaterOrEqualThanFloatNumber() {
		Fraction aFraction = new Fraction(4,2);
		FloatNumber aFloat = new FloatNumber(1);
		FloatNumber anotherFloat = new FloatNumber(3.2f);
		FloatNumber sameFloat = new FloatNumber(2);
		assertTrue(aFraction.greaterThanOrEqualTo(aFloat));
		assertFalse(aFraction.greaterThanOrEqualTo(anotherFloat));
		assertTrue(aFraction.greaterThanOrEqualTo(sameFloat));
	}
	
	// <
	public void testLessThanSmallInteger() {
		Fraction aFraction = new Fraction(4,2);
		SmallInteger anotherInteger = new SmallInteger(1);
		assertFalse(aFraction.lessThan(anotherInteger));
		assertTrue(anotherInteger.lessThan(aFraction));
		assertFalse(aFraction.lessThan(aFraction));
	}

	public void testLessThanLargeInteger() {
		Fraction aFraction = new Fraction(4,2);
		LargeInteger aLargeInteger = new LargeInteger(1);
		LargeInteger anotherLargeInteger = new LargeInteger(3);
		LargeInteger sameLargeInteger = new LargeInteger(2);
		assertFalse(aFraction.lessThan(aLargeInteger));
		assertTrue(aFraction.lessThan(anotherLargeInteger));
		assertFalse(aFraction.lessThan(sameLargeInteger));
	}
	
	public void testLessThanFraction() {
		Fraction aFraction = new Fraction(4,2);
		Fraction aFraction2 = new Fraction(1,2);
		Fraction anotherFraction = new Fraction(5,2);
		Fraction sameFraction = new Fraction(8,4);
		assertFalse(aFraction.lessThan(aFraction2));
		assertTrue(aFraction.lessThan(anotherFraction));
		assertFalse(aFraction.lessThan(sameFraction));
	}
	
	public void testLessThanFloatNumber() {
		Fraction aFraction = new Fraction(4,2);
		FloatNumber aFloat = new FloatNumber(1);
		FloatNumber anotherFloat = new FloatNumber(3.2f);
		FloatNumber sameFloat = new FloatNumber(2);
		assertFalse(aFraction.lessThan(aFloat));
		assertTrue(aFraction.lessThan(anotherFloat));
		assertFalse(aFraction.lessThan(sameFloat));
	}
	
	// <=
	public void testLessOrEqualThanSmallInteger() {
		Fraction aFraction = new Fraction(4,2);
		SmallInteger anotherInteger = new SmallInteger(1);
		assertFalse(aFraction.lessThanOrEqualTo(anotherInteger));
		assertTrue(anotherInteger.lessThanOrEqualTo(aFraction));
		assertTrue(aFraction.lessThanOrEqualTo(aFraction));
	}

	public void testLessOrEqualThanLargeInteger() {
		Fraction aFraction = new Fraction(4,2);
		LargeInteger aLargeInteger = new LargeInteger(1);
		LargeInteger anotherLargeInteger = new LargeInteger(3);
		LargeInteger sameLargeInteger = new LargeInteger(2);
		assertFalse(aFraction.lessThanOrEqualTo(aLargeInteger));
		assertTrue(aFraction.lessThanOrEqualTo(anotherLargeInteger));
		assertTrue(aFraction.lessThanOrEqualTo(sameLargeInteger));
	}
	
	public void testLessOrEqualThanFraction() {
		Fraction aFraction = new Fraction(4,2);
		Fraction aFraction2 = new Fraction(1,2);
		Fraction anotherFraction = new Fraction(5,2);
		Fraction sameFraction = new Fraction(8,4);
		assertFalse(aFraction.lessThanOrEqualTo(aFraction2));
		assertTrue(aFraction.lessThanOrEqualTo(anotherFraction));
		assertTrue(aFraction.lessThanOrEqualTo(sameFraction));
	}
	
	public void testLessOrEqualThanFloatNumber() {
		Fraction aFraction = new Fraction(4,2);
		FloatNumber aFloat = new FloatNumber(1);
		FloatNumber anotherFloat = new FloatNumber(3.2f);
		FloatNumber sameFloat = new FloatNumber(2);
		assertFalse(aFraction.lessThanOrEqualTo(aFloat));
		assertTrue(aFraction.lessThanOrEqualTo(anotherFloat));
		assertTrue(aFraction.lessThanOrEqualTo(sameFloat));
	}
	
	public void testNegated() {
		Fraction aFraction = new Fraction(1,2);
		Fraction anotherFraction = new Fraction(-1,2);
		Fraction zeroFraction = new Fraction(0,1);
		assertTrue(aFraction.negated().equals(anotherFraction));
		assertTrue(anotherFraction.negated().equals(aFraction));
		assertTrue(zeroFraction.negated().equals(zeroFraction));
	}
	
	public void testTruncated() {
		assertTrue((new Fraction(3,2)).truncated().equals(new SmallInteger(1)));
		assertTrue((new Fraction(2,2)).truncated().equals(new SmallInteger(1)));
		assertTrue((new Fraction(4,2)).truncated().equals(new SmallInteger(2)));
	}
	
	public void testSameUnitAs() {
		assertTrue((new Fraction(1,1)).sameUnitAs(new Fraction(1,1)));
		assertTrue((new Fraction(2,1)).sameUnitAs(new SmallInteger(1)));
		assertTrue((new Fraction(2,1)).sameUnitAs(new LargeInteger(1)));
		assertTrue((new Fraction(2,1)).sameUnitAs(new Fraction(2,1)));
		assertTrue((new Fraction(2,1)).sameUnitAs(new FloatNumber(1.05f)));
	}
	
}
