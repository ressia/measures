package test.measureModel.units;

import java.util.Iterator;

import junit.framework.TestCase;

import com.measureModel.arithmeticObjects.ArithmeticObject;
import com.measureModel.measurements.Measure;
import com.measureModel.numbers.ExtendedNumber;
import com.measureModel.numbers.SmallInteger;
import com.measureModel.numbers.Fraction;
import com.measureModel.units.BaseUnit;
import com.measureModel.units.MultipliedUnit;
import com.measureModel.units.NullUnit;
import com.measureModel.units.ProportionalDerivedUnit;
import com.measureModel.units.UnitBehavior;

public class NullUnitTest extends TestCase {

	private NullUnit nullUnit;
	
    protected void setUp() throws Exception {
    	nullUnit = NullUnit.newInstance();
    }
	
	// Test Accessing -------------------------------
	public void testBaseUnit() {
		assertTrue(nullUnit.equals(nullUnit.baseUnit()));
	}
	
	public void testDenominator() {
		assertTrue(nullUnit.denominator().equals(nullUnit));
	}
	
	public void testName() {
		assertTrue(nullUnit.name().equals(""));
	}
	
	public void testNameForMany() {
		assertTrue(nullUnit.nameForMany().equals(""));
	}
	
	public void testNameForOne() {
		assertTrue(nullUnit.nameForOne().equals(""));
	}
	
	public void testReciprocal() {
		assertTrue(nullUnit.reciprocal().equals(NullUnit.newInstance()));
	}
	
	public void testNumerator() {
		assertTrue(nullUnit.numerator().equals(nullUnit));
	}
	
	public void testSign() {
		assertTrue(nullUnit.sign().equals(NullUnit.defaultSign()));
	}
	
	// Test converting --------------------------------------------------------------------------
	
	public void testConvertAmountToBaseUnit() {
		assertTrue(nullUnit.convertAmountToBaseUnit(new SmallInteger(0)).equals(new SmallInteger(0)));
		assertTrue(nullUnit.convertAmountToBaseUnit(new SmallInteger(10)).equals(new SmallInteger(0)));
	}
	
	public void testConvertToBaseUnit() {
		BaseUnit meter = new BaseUnit("meter");
		Measure aMeasure = new Measure(new SmallInteger(10),(UnitBehavior)meter);
		assertTrue(nullUnit.convertToBaseUnit(aMeasure).equals(aMeasure));
	}
	
	// Test arithmetic Operations -------------------------------------------------------------------
	
	public void testDivideByDividedUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit hour = new BaseUnit("hour");
		
		assertTrue(nullUnit.divideBy(meter.divideBy(hour)).numerator().equals(hour));
		assertTrue(nullUnit.divideBy(meter.divideBy(hour)).denominator().equals(meter));
	}
	
	public void testDivideBySimpleUnit() {
		BaseUnit meter = new BaseUnit("meter");
	
		assertTrue(nullUnit.divideBy(meter).numerator().equals(nullUnit));
		assertTrue(nullUnit.divideBy(meter).denominator().equals(meter));
	}
	
	public void testDivideByMultipliedUnit() {
		BaseUnit meter = new BaseUnit("meter");

		assertTrue(nullUnit.divideBy(meter.multiplyBy(meter)).numerator().equals(nullUnit));
		assertTrue(nullUnit.divideBy(meter.multiplyBy(meter)).denominator().equals(meter.multiplyBy(meter)));
	}
	
	public void testDivideByNullUnit() {
		assertTrue(nullUnit.divideBy(nullUnit).equals(nullUnit));
	}
	
	public void testMultiplyByDividedUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
	
		assertTrue(nullUnit.multiplyBy(meter.divideBy(second)).numerator().equals(meter));
		assertTrue(nullUnit.multiplyBy(meter.divideBy(second)).denominator().equals(second));
	}
	
	public void testMultiplyByMultipliedUnit() {
		BaseUnit second = new BaseUnit("second");
		BaseUnit kelvin = new BaseUnit("kelvin");
	
		assertTrue(((MultipliedUnit)nullUnit.multiplyBy(second.multiplyBy(kelvin))).factorsSize() == 2);
		assertTrue(((MultipliedUnit)nullUnit.multiplyBy(second.multiplyBy(kelvin))).factors().contains(second));
		assertTrue(((MultipliedUnit)nullUnit.multiplyBy(second.multiplyBy(kelvin))).factors().contains(kelvin));
	}
	
	public void testMultiplyByNullUnit() {
		assertTrue(nullUnit.multiplyBy(nullUnit).equals(nullUnit));
	}
	
	public void testMultiplyBySimpleUnit() {
		BaseUnit meter = new BaseUnit("meter");

		assertTrue(nullUnit.multiplyBy(meter).equals(meter));
	}
	
	// Test Comparing ---------------------------------------------------------
	
	public void testEqualBaseUnit() {
		BaseUnit meter = new BaseUnit("meter");
	
		assertFalse(nullUnit.equals(meter));
	}
	
	public void testEqualDerivedUnit() {
		BaseUnit meter = new BaseUnit("meter");
		ProportionalDerivedUnit centimeter = new ProportionalDerivedUnit(meter,new Fraction(1,100),"centimeter");		
		assertFalse(nullUnit.equals(centimeter));
	}
	
	public void testEqualMultipliedUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit hour = new BaseUnit("hour");
		
		assertFalse(nullUnit.equals(meter.multiplyBy(hour)));
	}
	
	public void testEqualDividedUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit hour = new BaseUnit("hour");
		
		assertFalse(nullUnit.equals(meter.divideBy(hour)));
	}
	
	public void testEqualNullUnit() {
	
		assertTrue(nullUnit.equals(NullUnit.newInstance()));
	}
		
	// Test measurement creation ---------------------------------------

	public void testMeasurementCreation() {
		assertTrue(nullUnit.with(new SmallInteger(10)).equals(new SmallInteger(10)));
	}
	
	// Test Quering -----------------------------------------------------
	
	public void testSameDomainAsBaseUnit() {
		BaseUnit meter = new BaseUnit("meter");
				
		assertFalse(nullUnit.sameDomainAs(meter));
	}
	
	public void testSameDomainAsDerivedUnit() {
		BaseUnit meter = new BaseUnit("meter");
		ProportionalDerivedUnit centimeter = new ProportionalDerivedUnit(meter,new Fraction(1,100),"centimeter");				
		assertFalse(nullUnit.sameDomainAs(centimeter));
	}
	
	public void testSameDomainAsMultipliedUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit hour = new BaseUnit("hour");
				
		assertFalse(nullUnit.sameDomainAs(meter.multiplyBy(hour)));
	}
	
	public void testSameDomainAsDividedUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit hour = new BaseUnit("hour");
				
		assertFalse(nullUnit.sameDomainAs(meter.divideBy(hour)));
	}
	
	public void testSameDomainAsNullUnit() {
		assertTrue(nullUnit.sameDomainAs(NullUnit.newInstance()));
	}
	
}

