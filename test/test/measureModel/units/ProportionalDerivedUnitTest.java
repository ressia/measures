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

public class ProportionalDerivedUnitTest extends TestCase {
	
	private ProportionalDerivedUnit centavo;
	
	private BaseUnit peso;
	
    protected void setUp() throws Exception {
    	peso = new BaseUnit("peso");
    	centavo = new ProportionalDerivedUnit(peso,new Fraction(1,100),"centavo");
    }
	// Test Accessing -------------------------------
	
	public void testBaseUnit() {
		assertTrue(centavo.baseUnit().equals(peso));
	}
	
	public void testDenominator() {
		assertTrue(centavo.denominator().equals(NullUnit.newInstance()));
	}
	
	public void testName() {
		assertTrue(((UnitBehavior)centavo).name().equals("centavo"));
	}
	
	public void testNameForMany() {
		ProportionalDerivedUnit aUnit = new ProportionalDerivedUnit(peso,new Fraction(1,100),"centavo","pepe","$");
		assertTrue(((UnitBehavior)centavo).nameForMany().equals("centavos"));
		assertTrue(((UnitBehavior)aUnit).nameForMany().equals("pepe"));
	}
	
	public void testNameForOne() {
		assertTrue(((UnitBehavior)centavo).nameForOne().equals("centavo"));
	}
	
	public void testNothingAmount() {
		assertTrue(((UnitBehavior)centavo).nothingAmount().equals(new SmallInteger(0)));
	}
	
	public void testNullMeasure() {
		assertTrue(((UnitBehavior)centavo).nullMeasure().amount().equals(new SmallInteger(0)));
		assertTrue(((UnitBehavior)centavo).nullMeasure().unit().equals(peso));
	}
	
	public void testReciprocal() {
		assertTrue(centavo.reciprocal().equals(NullUnit.newInstance().divideBy(centavo)));
	}
	
	public void testNumerator() {
		assertTrue(centavo.numerator().equals(centavo));
	}
	
	public void testSign() {
		ProportionalDerivedUnit aUnit = new ProportionalDerivedUnit(peso,new Fraction(1,100),"centavo","pepe","$");
		assertTrue(((UnitBehavior)centavo).sign().equals(ProportionalDerivedUnit.defaultSign()));
		assertTrue(((UnitBehavior)aUnit).sign().equals("$"));
	}
	
	// Test converting --------------------------------------------------------------------------
	
	public void testConversionFactor() {
		assertTrue(((ProportionalDerivedUnit)centavo).conversionFactor().equals(new Fraction(1,100)));
	}
	
	public void testConvertAmountToBaseUnit() {
		assertTrue(((UnitBehavior)centavo).convertAmountToBaseUnit(new SmallInteger(0)).equals(new SmallInteger(0)));
		assertTrue(((UnitBehavior)centavo).convertAmountToBaseUnit(new SmallInteger(1)).equals(new Fraction(1,100)));
		assertTrue(((UnitBehavior)centavo).convertAmountToBaseUnit(new SmallInteger(100)).equals(new SmallInteger(1)));
	}
	
	public void testConvertToBaseUnit() {
		Measure aMeasure = new Measure(new SmallInteger(100),(UnitBehavior)centavo);
		assertTrue(((UnitBehavior)centavo).convertToBaseUnit(aMeasure).amount().equals(new SmallInteger(1)));
		assertTrue(((UnitBehavior)centavo).convertToBaseUnit(aMeasure).unit().equals(peso));
	}
	
	public void testConvertAmountFromBaseUnit() {
		assertTrue(((ProportionalDerivedUnit)centavo).convertAmountFromBaseUnit(new SmallInteger(0)).equals(new SmallInteger(0)));
		assertTrue(((ProportionalDerivedUnit)centavo).convertAmountFromBaseUnit(new SmallInteger(1)).equals(new SmallInteger(100)));
		assertTrue(((ProportionalDerivedUnit)centavo).convertAmountFromBaseUnit(new SmallInteger(100)).equals(new SmallInteger(10000)));
	}
	
	public void testConvertFromBaseUnit() {
		Measure aMeasure = new Measure(new SmallInteger(1),(UnitBehavior)centavo);
		assertTrue(((ProportionalDerivedUnit)centavo).convertFromBaseUnit(aMeasure).amount().equals(new SmallInteger(100)));
		assertTrue(((ProportionalDerivedUnit)centavo).convertFromBaseUnit(aMeasure).unit().equals(centavo));
	}
	
	// Test arithmetic Operations -------------------------------------------------------------------
	
	public void testDivideByDifferenBaseUnit() {
		BaseUnit meter = new BaseUnit("meter");

		assertTrue(centavo.divideBy(meter).numerator().equals(centavo));
		assertTrue(centavo.divideBy(meter).denominator().equals(meter));
	}
	
	public void testDivideByDifferenDomainDerivedUnit() {
		BaseUnit hour = new BaseUnit("hour");
		ProportionalDerivedUnit minutes = new ProportionalDerivedUnit(hour,new Fraction(1,60),"minutes");
		assertTrue(centavo.divideBy(minutes).numerator().equals(centavo));
		assertTrue(centavo.divideBy(minutes).denominator().equals(minutes));
	}
	
	public void testDivideByDivideUnitSameDerivedUnitNumerator() {
		BaseUnit meter = new BaseUnit("meter");

		assertTrue(centavo.divideBy(centavo.divideBy(meter)).numerator().equals(meter));
	}
	
	public void testDivideByDividedUnitSameBaseUnitNumerator() {
		BaseUnit hour = new BaseUnit("hour");

		assertTrue(centavo.divideBy(peso.divideBy(hour)).numerator().equals(centavo.multiplyBy(hour)));
		assertTrue(centavo.divideBy(peso.divideBy(hour)).denominator().equals(peso));
	}

	public void testDivideByMultipliedUnit() {
		BaseUnit meter = new BaseUnit("meter");
		
		assertTrue(centavo.divideBy(centavo.multiplyBy(meter)).numerator().equals(NullUnit.newInstance()));
		assertTrue(centavo.divideBy(centavo.multiplyBy(meter)).denominator().equals(meter));
		
		assertTrue(centavo.divideBy(meter.multiplyBy(meter)).numerator().equals(centavo));
		assertTrue(centavo.divideBy(meter.multiplyBy(meter)).denominator().equals(meter.multiplyBy(meter)));
	}
		
	public void testDivideByNullUnit() {
		assertTrue(centavo.divideBy(NullUnit.newInstance()).equals(centavo));
	}
	
	public void testDivideBySameBaseUnit() {
		assertTrue(centavo.divideBy(centavo).equals(NullUnit.newInstance()));
	}
	
	public void testDivideBySameDomainDerivedUnit() {
		ProportionalDerivedUnit tenCents = new ProportionalDerivedUnit(peso,new Fraction(1,10),"diez centavos");
		assertTrue(centavo.divideBy(tenCents).numerator().equals(centavo));
		assertTrue(centavo.divideBy(tenCents).denominator().equals(tenCents));
	}

	public void testMultiplyByDividedUnit() {
		BaseUnit second = new BaseUnit("second");
		BaseUnit kelvin = new BaseUnit("kelvin");
	
		assertTrue(centavo.multiplyBy(second.divideBy(kelvin)).numerator().equals(centavo.multiplyBy(second)));
		assertTrue(centavo.multiplyBy(second.divideBy(kelvin)).denominator().equals(kelvin));
	}
	
	public void testMultiplyByMultipliedUnit() {
		BaseUnit second = new BaseUnit("second");
		BaseUnit kelvin = new BaseUnit("kelvin");
		
		assertTrue(((MultipliedUnit)centavo.multiplyBy(second.multiplyBy(kelvin))).factorsSize() == 3);
		assertTrue(((MultipliedUnit)centavo.multiplyBy(second.multiplyBy(kelvin))).factors().contains(centavo));
		assertTrue(((MultipliedUnit)centavo.multiplyBy(second.multiplyBy(kelvin))).factors().contains(second));
		assertTrue(((MultipliedUnit)centavo.multiplyBy(second.multiplyBy(kelvin))).factors().contains(kelvin));
	}
	
	public void testMultiplyByNullUnit() {
		assertTrue(centavo.multiplyBy(NullUnit.newInstance()).equals(centavo));
	}
	
	public void testMultiplyBySimpleUnit() {
		BaseUnit second = new BaseUnit("second");

		assertTrue(((MultipliedUnit)centavo.multiplyBy(second)).factorsSize() == 2);
		assertTrue(((MultipliedUnit)centavo.multiplyBy(second)).factors().contains(centavo));
		assertTrue(((MultipliedUnit)centavo.multiplyBy(second)).factors().contains(second));
	}

	// Test Comparing ---------------------------------------------------------
	
	public void testEqualDerivedSameBaseUnit() {
		ProportionalDerivedUnit tenCents = new ProportionalDerivedUnit(peso,new Fraction(1,10),"diez centavos");
		
		assertFalse(centavo.equals(tenCents));
	}
	
	public void testEqualDifferentBaseUnit() {
		BaseUnit meter = new BaseUnit("meter");
		assertFalse(centavo.equals(meter));
	}
	
	public void testEqualMultipliedUnit() {
		BaseUnit hour = new BaseUnit("hour");
		
		assertFalse(centavo.equals(centavo.multiplyBy(hour)));
	}
	
	public void testEqualDividedUnit() {
		BaseUnit hour = new BaseUnit("hour");
		
		assertFalse(centavo.equals(centavo.divideBy(hour)));
	}
	
	public void testEqualNullUnit() {
		assertFalse(centavo.equals(NullUnit.newInstance()));
	}
	
	public void testEqualSameBaseUnit() {
		assertFalse(centavo.equals(peso));
	}
	
	public void testEqualSameDerivedUnit() {
		assertTrue(centavo.equals(centavo));
	}
	
	
	
	// Test measurement creation ---------------------------------------

	public void testMeasurementCreation() {
		assertTrue(centavo.with(new SmallInteger(10)).equals(new Measure(new SmallInteger(10),(UnitBehavior)centavo)));
	}
	
	// Test Quering -----------------------------------------------------
	
	public void testSameDomainAsDerivedUnit() {
		ProportionalDerivedUnit tenCents = new ProportionalDerivedUnit(peso,new Fraction(1,10),"diez centavos");
				
		assertTrue(((UnitBehavior)centavo).sameDomainAs(tenCents));
		
	}
	
	public void testSameDomainAsDerivedUnitOfOtherDomain() {
		BaseUnit hour = new BaseUnit("hour");
		ProportionalDerivedUnit minute = new ProportionalDerivedUnit(hour,new Fraction(1,60),"minute");
				
		assertFalse(((UnitBehavior)centavo).sameDomainAs(minute));
	}
	
	public void testSameDomainAsDifferentBaseUnit() {
		BaseUnit meter = new BaseUnit("meter");
			
		assertFalse(((UnitBehavior)centavo).sameDomainAs(meter));
	}
	
	public void testSameDomainAsMultipliedUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit hour = new BaseUnit("hour");
		
		assertFalse(((UnitBehavior)centavo).sameDomainAs(meter.multiplyBy(hour)));
	}
	
	public void testSameDomainAsDividedUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit hour = new BaseUnit("hour");
				
		assertFalse(((UnitBehavior)centavo).sameDomainAs(meter.divideBy(hour)));
	}
	
	public void testSameDomainAsNullUnit() {
		assertFalse(((UnitBehavior)centavo).sameDomainAs(NullUnit.newInstance()));
	}
	
	public void testSameDomainAsSameBaseUnit() {
		assertTrue(((UnitBehavior)centavo).sameDomainAs(peso));
	}
	
	public void testSameDomainAsSameUnit() {
		assertTrue(((UnitBehavior)centavo).sameDomainAs(centavo));
	}
	
	public void testUnitMultiplyByMeasure() {
		BaseUnit kelvin = new BaseUnit("kelvin");
		assertTrue(centavo.multiplyBy(new Measure(new SmallInteger(1),kelvin)).equals(new Measure(new SmallInteger(1),(UnitBehavior)centavo.multiplyBy(kelvin))));
	}
}

