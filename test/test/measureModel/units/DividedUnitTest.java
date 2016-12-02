package test.measureModel.units;

import junit.framework.TestCase;

import com.measureModel.arithmeticObjects.ArithmeticObject;
import com.measureModel.measurements.Measure;
import com.measureModel.numbers.ExtendedNumber;
import com.measureModel.numbers.FloatNumber;
import com.measureModel.numbers.SmallInteger;
import com.measureModel.numbers.Fraction;
import com.measureModel.units.BaseUnit;
import com.measureModel.units.NullUnit;
import com.measureModel.units.UnitBehavior;

public class DividedUnitTest extends TestCase {
	
	// Test Accessing -------------------------------
	
	public void testBaseUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterOverSecond = meter.divideBy(second);
		
		assertTrue(meterOverSecond.equals(meterOverSecond.baseUnit()));
	}
	
	public void testDenominator() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterOverSecond = meter.divideBy(second);
		
		assertTrue(meterOverSecond.denominator().equals(second));
	}
	
	public void testName() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterOverSecond = meter.divideBy(second);
		
		assertTrue(((UnitBehavior)meterOverSecond).name().equals("meter/second"));
	}
	
	public void testNameForMany() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterOverSecond = meter.divideBy(second);
		
		assertTrue(((UnitBehavior)meterOverSecond).nameForMany().equals("meters/seconds"));
	}
	
	public void testNameForOne() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterOverSecond = meter.divideBy(second);
		
		assertTrue(((UnitBehavior)meterOverSecond).nameForOne().equals("meter/second"));
	}
	
	public void testNothingAmount() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterOverSecond = meter.divideBy(second);
		
		assertTrue(((UnitBehavior)meterOverSecond).nothingAmount().equals(new SmallInteger(0)));
	}
	
	public void testNullMeasure() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterOverSecond = meter.divideBy(second);
		
		assertTrue(((UnitBehavior)meterOverSecond).nullMeasure().amount().equals(new SmallInteger(0)));
		assertTrue(((UnitBehavior)meterOverSecond).nullMeasure().unit().equals(meterOverSecond));
	}
	
	public void testReciprocal() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterOverSecond = meter.divideBy(second);
		ArithmeticObject secondOverMeter = second.divideBy(meter);
		
		assertTrue(meterOverSecond.reciprocal().equals(secondOverMeter));
	}
	
	public void testNumerator() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterOverSecond = meter.divideBy(second);
		
		assertTrue(meterOverSecond.numerator().equals(meter));
	}
	
	public void testSign() {
		BaseUnit meter = new BaseUnit("meter","meters","m");
		BaseUnit second = new BaseUnit("second","seconds","s");
		ArithmeticObject meterOverSecond = meter.divideBy(second);
		
		assertTrue(((UnitBehavior)meterOverSecond).sign().equals("m/s"));
	}
	
	// Test converting --------------------------------------------------------------------------
	
	public void testConvertAmountToBaseUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterOverSecond = meter.divideBy(second);
		
		assertTrue(((UnitBehavior)meterOverSecond).convertAmountToBaseUnit(new SmallInteger(0)).equals(new SmallInteger(0)));
		assertTrue(((UnitBehavior)meterOverSecond).convertAmountToBaseUnit(new SmallInteger(10)).equals(new SmallInteger(10)));
	}
	
	public void testConvertToBaseUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterOverSecond = meter.divideBy(second);
		Measure aMeasure = new Measure(new SmallInteger(10),(UnitBehavior)meterOverSecond);
		
		assertTrue(((UnitBehavior)meterOverSecond).convertToBaseUnit(aMeasure).equals(aMeasure));
	}
	
	// Test arithmetic Operations -------------------------------------------------------------------
	
	public void testDivideByDividedUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterOverSecond = meter.divideBy(second);
	
		assertTrue(meterOverSecond.divideBy(meterOverSecond).equals(NullUnit.newInstance()));
	}
	
	public void testDivideByNumber() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterOverSecond = meter.divideBy(second);
	
		assertTrue(meterOverSecond.divideBy(new SmallInteger(5)).equals(meterOverSecond.with(new Fraction(1,5))));
		assertTrue(meterOverSecond.divideBy(new SmallInteger(5)).numerator().equals(meter.with(new SmallInteger(1))));
		assertTrue(meterOverSecond.divideBy(new SmallInteger(5)).denominator().equals(second.with(new SmallInteger(5))));
		
		/*
		 * TODO: we have to chekc if this is correct. Perhaps a solution to this is implementing
		 * the number hierarchy. CHECKKKK
		 */
		assertTrue(meterOverSecond.divideBy(new FloatNumber(5.5)).equals(meterOverSecond.with(new Fraction(1,new FloatNumber(5.5)))));
		assertTrue(meterOverSecond.divideBy(new FloatNumber(5.5)).amount().equals(new Fraction(new SmallInteger(1),new FloatNumber(5.5))));
		assertTrue(meterOverSecond.divideBy(new FloatNumber(5.5)).numerator().equals(meter.with(new SmallInteger(1))));
		assertTrue(meterOverSecond.divideBy(new FloatNumber(5.5)).denominator().equals(second.with(new FloatNumber(5.5))));
	}
	
	public void testDivideBySimpleUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterOverSecond = meter.divideBy(second);
		BaseUnit kelvin = new BaseUnit("kelvin");
	
		assertTrue(meterOverSecond.divideBy(second).numerator().equals(meter));
		assertTrue(meterOverSecond.divideBy(second).denominator().equals(second.multiplyBy(second)));
		assertTrue(meterOverSecond.divideBy(kelvin).numerator().equals(meter));
		assertTrue(meterOverSecond.divideBy(kelvin).denominator().equals(second.multiplyBy(kelvin)));
	}
	
	public void testDivideByMultipliedUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterOverSecond = meter.divideBy(second);
		BaseUnit kelvin = new BaseUnit("kelvin");
		BaseUnit litre = new BaseUnit("litre");
	
		assertTrue(meterOverSecond.divideBy(meter.multiplyBy(second)).numerator().equals(NullUnit.newInstance()));
		assertTrue(meterOverSecond.divideBy(meter.multiplyBy(second)).denominator().equals(second.multiplyBy(second)));
		assertTrue(meterOverSecond.divideBy(kelvin.multiplyBy(litre)).numerator().equals(meter));
		assertTrue(meterOverSecond.divideBy(kelvin.multiplyBy(litre)).denominator().equals(second.multiplyBy(litre).multiplyBy(kelvin)));
	}
	
	public void testDivideByNullUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterOverSecond = meter.divideBy(second);
	
		assertTrue(meterOverSecond.divideBy(NullUnit.newInstance()).equals(meterOverSecond));
	}
	
	public void testMultiplyByDividedUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterOverSecond = meter.divideBy(second);
		BaseUnit kelvin = new BaseUnit("kelvin");
		BaseUnit litre = new BaseUnit("litre");
	
		assertTrue(meterOverSecond.multiplyBy(meterOverSecond).numerator().equals(meter.multiplyBy(meter)));
		assertTrue(meterOverSecond.multiplyBy(meterOverSecond).denominator().equals(second.multiplyBy(second)));
		assertTrue(meterOverSecond.multiplyBy(second.divideBy(meter)).equals(NullUnit.newInstance()));
		assertTrue(meterOverSecond.multiplyBy(kelvin.divideBy(litre)).numerator().equals(meter.multiplyBy(kelvin)));
		assertTrue(meterOverSecond.multiplyBy(kelvin.divideBy(litre)).denominator().equals(second.multiplyBy(litre)));

	}
	
	public void testMultiplyByMultipliedUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterOverSecond = meter.divideBy(second);
		BaseUnit kelvin = new BaseUnit("kelvin");
			
		assertTrue(meterOverSecond.multiplyBy(meter.multiplyBy(second)).equals(meter.multiplyBy(meter)));
		assertTrue(meterOverSecond.multiplyBy(kelvin.multiplyBy(kelvin)).numerator().equals(meter.multiplyBy(kelvin).multiplyBy(kelvin)));
		assertTrue(meterOverSecond.multiplyBy(kelvin.multiplyBy(kelvin)).denominator().equals(second));
	}
	
	public void testMultiplyByNullUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterOverSecond = meter.divideBy(second);
			
		assertTrue(meterOverSecond.multiplyBy(NullUnit.newInstance()).equals(meterOverSecond));
	}
	
	public void testMultiplyByNumber() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterOverSecond = meter.divideBy(second);
	
		assertTrue(meterOverSecond.multiplyBy(new SmallInteger(5)).equals(meterOverSecond.with(new SmallInteger(5))));
		assertTrue(meterOverSecond.multiplyBy(new SmallInteger(5)).numerator().equals(meter.with(new SmallInteger(5))));
		assertTrue(meterOverSecond.multiplyBy(new SmallInteger(5)).denominator().equals(second.with(new SmallInteger(1))));
		
		assertTrue(meterOverSecond.multiplyBy(new SmallInteger(5.5)).equals(meterOverSecond.with(new SmallInteger(5.5))));
		assertTrue(meterOverSecond.multiplyBy(new SmallInteger(5.5)).numerator().equals(meter.with(new SmallInteger(5.5))));
		assertTrue(meterOverSecond.multiplyBy(new SmallInteger(5.5)).denominator().equals(second.with(new SmallInteger(1))));
	}
		
	public void testMultiplyBySimpleUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterOverSecond = meter.divideBy(second);
		BaseUnit kelvin = new BaseUnit("kelvin");
	
		assertTrue(meterOverSecond.multiplyBy(second).equals(meter));
		assertTrue(meterOverSecond.multiplyBy(meter).numerator().equals(meter.multiplyBy(meter)));
		assertTrue(meterOverSecond.multiplyBy(meter).denominator().equals(second));
		
		assertTrue(meterOverSecond.multiplyBy(kelvin).numerator().equals(meter.multiplyBy(kelvin)));
		assertTrue(meterOverSecond.multiplyBy(kelvin).denominator().equals(second));
	}
	
	public void testNumberDividedByUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterOverSecond = meter.divideBy(second);
		ArithmeticObject secondOverMeter = second.divideBy(meter);
	
		assertTrue((new SmallInteger(5)).divideBy(meterOverSecond).equals(secondOverMeter.with(new SmallInteger(5))));
		assertTrue((new SmallInteger(5)).divideBy(meterOverSecond).numerator().equals(second.with(new SmallInteger(5))));
		assertTrue((new SmallInteger(5)).divideBy(meterOverSecond).denominator().equals(meter.with(new SmallInteger(1))));
		
		assertTrue((new SmallInteger(5.5)).divideBy(meterOverSecond).equals(secondOverMeter.with(new SmallInteger(5.5))));
		assertTrue((new SmallInteger(5.5)).divideBy(meterOverSecond).numerator().equals(second.with(new SmallInteger(5.5))));
		assertTrue((new SmallInteger(5.5)).divideBy(meterOverSecond).denominator().equals(meter.with(new SmallInteger(1))));

	}
	
	public void testNumberDividedByUnitTwice() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterOverSecond = meter.divideBy(second);
		ArithmeticObject secondOverMeter = second.divideBy(meter);
		BaseUnit kelvin = new BaseUnit("kelvin");
	
		// These ones fail due to the unimplemented behavior for measures
		assertTrue((new SmallInteger(5)).divideBy(kelvin).divideBy(meterOverSecond).equals(
									secondOverMeter.multiplyBy(NullUnit.newInstance().divideBy(kelvin)).with(new SmallInteger(5))));
		assertTrue((new SmallInteger(5)).divideBy(kelvin).divideBy(meterOverSecond).numerator().equals(second.with(new SmallInteger(5))));
		assertTrue((new SmallInteger(5)).divideBy(kelvin).divideBy(meterOverSecond).denominator().equals(kelvin.multiplyBy(meter).with(new SmallInteger(1))));
		
		assertTrue((new SmallInteger(5.5)).divideBy(kelvin).divideBy(meterOverSecond).equals(
				secondOverMeter.multiplyBy(NullUnit.newInstance().divideBy(kelvin)).with(new SmallInteger(5.5))));
		assertTrue((new SmallInteger(5.5)).divideBy(kelvin).divideBy(meterOverSecond).numerator().equals(second.with(new SmallInteger(5.5))));
		assertTrue((new SmallInteger(5.5)).divideBy(kelvin).divideBy(meterOverSecond).denominator().equals(kelvin.multiplyBy(meter).with(new SmallInteger(1))));

	}
	
	public void testNumberMultipliedByUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterOverSecond = meter.divideBy(second);
	
		assertTrue((new SmallInteger(5)).multiplyBy(meterOverSecond).equals(meterOverSecond.with(new SmallInteger(5))));
		assertTrue((new SmallInteger(5)).multiplyBy(meterOverSecond).numerator().equals(meter.with(new SmallInteger(5))));
		assertTrue((new SmallInteger(5)).multiplyBy(meterOverSecond).denominator().equals(second.with(new SmallInteger(1))));
		
		assertTrue((new SmallInteger(5.5)).multiplyBy(meterOverSecond).equals(meterOverSecond.with(new SmallInteger(5.5))));
		assertTrue((new SmallInteger(5.5)).multiplyBy(meterOverSecond).numerator().equals(meter.with(new SmallInteger(5.5))));
		assertTrue((new SmallInteger(5.5)).multiplyBy(meterOverSecond).denominator().equals(second.with(new SmallInteger(1))));

	}
	
	public void testNumberMultipliedByUnitTwice() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterOverSecond = meter.divideBy(second);
		BaseUnit kelvin = new BaseUnit("kelvin");
		// These ones fail due to the unimplemented behavior for measures
		assertTrue((new SmallInteger(5)).multiplyBy(kelvin).multiplyBy(meterOverSecond).equals(kelvin.multiplyBy(meterOverSecond).with(new SmallInteger(5))));
		assertTrue((new SmallInteger(5)).multiplyBy(kelvin).multiplyBy(meterOverSecond).numerator().equals(kelvin.multiplyBy(meter).with(new SmallInteger(5))));
		assertTrue((new SmallInteger(5)).multiplyBy(kelvin).multiplyBy(meterOverSecond).denominator().equals(second.with(new SmallInteger(1))));
		
		assertTrue((new SmallInteger(5.5)).multiplyBy(kelvin).multiplyBy(meterOverSecond).equals(kelvin.multiplyBy(meterOverSecond).with(new SmallInteger(5.5))));
		assertTrue((new SmallInteger(5.5)).multiplyBy(kelvin).multiplyBy(meterOverSecond).numerator().equals(kelvin.multiplyBy(meter).with(new SmallInteger(5.5))));
		assertTrue((new SmallInteger(5.5)).multiplyBy(kelvin).multiplyBy(meterOverSecond).denominator().equals(second.with(new SmallInteger(1))));
	}
	
	// Test Comparing ---------------------------------------------------------
	
	public void testEqualDividedUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterOverSecond = meter.divideBy(second);
		BaseUnit kelvin = new BaseUnit("kelvin");
		BaseUnit litre = new BaseUnit("litre");
		
		assertTrue(meterOverSecond.equals(meterOverSecond));
		assertTrue(meterOverSecond.equals(meter.divideBy(second)));
		
		assertFalse(meterOverSecond.equals(meter.divideBy(kelvin)));
		assertFalse(meterOverSecond.equals(meter.divideBy(second).divideBy(kelvin)));
		assertFalse(meterOverSecond.equals(kelvin.divideBy(litre)));
	}
	
	public void testEqualMultipliedUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterOverSecond = meter.divideBy(second);
		
		assertFalse(meterOverSecond.equals(meter.multiplyBy(second)));
	}
	
	public void testEqualNullUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterOverSecond = meter.divideBy(second);
		
		assertFalse(meterOverSecond.equals(NullUnit.newInstance()));
	}
	
	public void testEqualSimpleUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterOverSecond = meter.divideBy(second);
		BaseUnit kelvin = new BaseUnit("kelvin");
		
		assertFalse(meterOverSecond.equals(kelvin));
		assertFalse(meterOverSecond.equals(meter));
		assertFalse(meterOverSecond.equals(second));
	}
	
	// Test measurement creation ---------------------------------------

	public void testMeasurementCreation() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterOverSecond = meter.divideBy(second);
				
		assertTrue(meterOverSecond.with(new SmallInteger(10)).equals(new Measure(new SmallInteger(10),(UnitBehavior)meterOverSecond)));
	}
	
	// Test Quering -----------------------------------------------------
	
	public void testSameDomainAsDividedUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterOverSecond = meter.divideBy(second);
				
		assertTrue(((UnitBehavior)meterOverSecond).sameDomainAs(meterOverSecond));
		assertFalse(((UnitBehavior)meterOverSecond).sameDomainAs(second.divideBy(meter)));
	}
	
	public void testSameDomainAsMultipliedUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterOverSecond = meter.divideBy(second);
				
		assertFalse(((UnitBehavior)meterOverSecond).sameDomainAs(meter.multiplyBy(second)));
	}
	
	public void testSameDomainAsNullUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterOverSecond = meter.divideBy(second);
				
		assertFalse(((UnitBehavior)meterOverSecond).sameDomainAs(NullUnit.newInstance()));
	}
	
	public void testSameDomainAsSameSimpleUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterOverSecond = meter.divideBy(second);
				
		assertFalse(((UnitBehavior)meterOverSecond).sameDomainAs(meter));
	}
	
	public void testUnitMultiplyByMeasure() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterOverSecond = meter.divideBy(second);
		assertTrue(meterOverSecond.multiplyBy(new Measure(new SmallInteger(1),meter)).equals(new Measure(new SmallInteger(1),(UnitBehavior)meterOverSecond.multiplyBy(meter))));
	}
	
}

