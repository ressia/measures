package test.measureModel.units;

import java.util.Iterator;

import junit.framework.TestCase;

import com.measureModel.arithmeticObjects.ArithmeticObject;
import com.measureModel.measurements.Measure;
import com.measureModel.numbers.ExtendedNumber;
import com.measureModel.numbers.FloatNumber;
import com.measureModel.numbers.SmallInteger;
import com.measureModel.numbers.Fraction;
import com.measureModel.units.BaseUnit;
import com.measureModel.units.MultipliedUnit;
import com.measureModel.units.NullUnit;
import com.measureModel.units.UnitBehavior;

public class MultipliedUnitTest extends TestCase {
	
	// Test Accessing -------------------------------
	
	public void testBaseUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterBySecond = meter.multiplyBy(second);
		
		assertTrue(meterBySecond.equals(meterBySecond.baseUnit()));
	}
	
	public void testDenominator() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterBySecond = meter.multiplyBy(second);
		
		assertTrue(meterBySecond.denominator().equals(NullUnit.newInstance()));
	}
	
	public void testName() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterBySecond = meter.multiplyBy(second);
		
		assertTrue(((UnitBehavior)meterBySecond).name().equals("meter.second"));
	}
	
	public void testNameForMany() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterBySecond = meter.multiplyBy(second);
		
		assertTrue(((UnitBehavior)meterBySecond).nameForMany().equals("meters.seconds"));
	}
	
	public void testNameForOne() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterBySecond = meter.multiplyBy(second);
		
		assertTrue(((UnitBehavior)meterBySecond).nameForOne().equals("meter.second"));
	}
	
	public void testNothingAmount() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterBySecond = meter.multiplyBy(second);
		
		assertTrue(((UnitBehavior)meterBySecond).nothingAmount().equals(new SmallInteger(0)));
	}
	
	public void testNullMeasure() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterBySecond = meter.multiplyBy(second);
		
		assertTrue(((UnitBehavior)meterBySecond).nullMeasure().amount().equals(new SmallInteger(0)));
		assertTrue(((UnitBehavior)meterBySecond).nullMeasure().unit().equals(meterBySecond));
	}
	
	public void testReciprocal() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterBySecond = meter.multiplyBy(second);
		
		assertTrue(meterBySecond.reciprocal().equals(NullUnit.newInstance().divideBy(meterBySecond)));
	}
	
	public void testNumerator() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterBySecond = meter.multiplyBy(second);
		
		assertTrue(meterBySecond.numerator().equals(meterBySecond));
	}
	
	public void testSign() {
		BaseUnit meter = new BaseUnit("meter","meters","m");
		BaseUnit second = new BaseUnit("second","seconds","s");
		ArithmeticObject meterBySecond = meter.multiplyBy(second);
		
		assertTrue(((UnitBehavior)meterBySecond).sign().equals("m.s"));
	}
	
	// Test converting --------------------------------------------------------------------------
	
	public void testConvertAmountToBaseUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterBySecond = meter.multiplyBy(second);
		
		assertTrue(((UnitBehavior)meterBySecond).convertAmountToBaseUnit(new SmallInteger(0)).equals(new SmallInteger(0)));
		assertTrue(((UnitBehavior)meterBySecond).convertAmountToBaseUnit(new SmallInteger(10)).equals(new SmallInteger(10)));
	}
	
	public void testConvertToBaseUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterBySecond = meter.multiplyBy(second);
		Measure aMeasure = new Measure(new SmallInteger(10),(UnitBehavior)meterBySecond);
		
		assertTrue(((UnitBehavior)meterBySecond).convertToBaseUnit(aMeasure).equals(aMeasure));
	}
	
	// Test arithmetic Operations -------------------------------------------------------------------
	
	public void testDivideByDividedUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterBySecond = meter.multiplyBy(second);
		BaseUnit kelvin = new BaseUnit("kelvin");
		BaseUnit litre = new BaseUnit("litre");
		
		assertTrue(meterBySecond.divideBy(meter.divideBy(second)).equals(second.multiplyBy(second)));
		assertTrue(meterBySecond.divideBy(second.divideBy(meter)).equals(meter.multiplyBy(meter)));
		assertTrue(meterBySecond.divideBy(kelvin.divideBy(litre)).numerator().equals(meter.multiplyBy(second).multiplyBy(litre)));
		assertTrue(meterBySecond.divideBy(kelvin.divideBy(litre)).denominator().equals(kelvin));
	}
	
	public void testDivideByNumber() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterBySecond = meter.multiplyBy(second);
	
		assertTrue(meterBySecond.divideBy(new SmallInteger(5)).equals(meterBySecond.with(new Fraction(1,5))));
		assertTrue(meterBySecond.divideBy(new SmallInteger(5)).numerator().equals(meterBySecond.with(new SmallInteger(1))));
		assertTrue(meterBySecond.divideBy(new SmallInteger(5)).denominator().equals(new SmallInteger(5)));

		assertTrue(meterBySecond.divideBy(new FloatNumber(5.5)).equals(meterBySecond.with(new Fraction(1,new FloatNumber(5.5)))));
		assertTrue(meterBySecond.divideBy(new FloatNumber(5.5)).numerator().equals(meterBySecond.with(new SmallInteger(1))));
		assertTrue(meterBySecond.divideBy(new FloatNumber(5.5)).denominator().equals(new FloatNumber(5.5)));
	}
	
	public void testDivideBySimpleUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterBySecond = meter.multiplyBy(second);
		BaseUnit kelvin = new BaseUnit("kelvin");
	
		assertTrue(meterBySecond.divideBy(second).equals(meter));
		assertTrue(meterBySecond.divideBy(meter).equals(second));
		assertTrue(meterBySecond.divideBy(kelvin).numerator().equals(meterBySecond));
		assertTrue(meterBySecond.divideBy(kelvin).denominator().equals(kelvin));
	}
	
	public void testDivideByMultipliedUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterBySecond = meter.multiplyBy(second);
		BaseUnit kelvin = new BaseUnit("kelvin");
		BaseUnit litre = new BaseUnit("litre");
	
		assertTrue(meterBySecond.divideBy(meterBySecond).equals(NullUnit.newInstance()));
		assertTrue(meterBySecond.divideBy(kelvin.multiplyBy(litre)).numerator().equals(meterBySecond));
		assertTrue(meterBySecond.divideBy(kelvin.multiplyBy(litre)).denominator().equals(kelvin.multiplyBy(litre)));
	}
	
	public void testDivideByNullUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterBySecond = meter.multiplyBy(second);
	
		assertTrue(meterBySecond.divideBy(NullUnit.newInstance()).equals(meterBySecond));
	}
	
	public void testMultiplyByDividedUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterBySecond = meter.multiplyBy(second);
		BaseUnit kelvin = new BaseUnit("kelvin");
		BaseUnit litre = new BaseUnit("litre");
	
		assertTrue(meterBySecond.multiplyBy(meter.divideBy(second)).equals(meter.multiplyBy(meter)));
		assertTrue(meterBySecond.multiplyBy(second.divideBy(meter)).equals(second.multiplyBy(second)));
		assertTrue(meterBySecond.multiplyBy(kelvin.divideBy(litre)).numerator().equals(meter.multiplyBy(second).multiplyBy(kelvin)));
		assertTrue(meterBySecond.multiplyBy(kelvin.divideBy(litre)).denominator().equals(litre));

	}
	
	public void testMultiplyByMultipliedUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterBySecond = meter.multiplyBy(second);
	
		assertTrue(((MultipliedUnit)meterBySecond.multiplyBy(meterBySecond)).factorsSize() == 4);
		Iterator anIterator = ((MultipliedUnit)meterBySecond.multiplyBy(meterBySecond)).factors().iterator();
		assertTrue(((BaseUnit)anIterator.next()).equals(meter));
		assertTrue(((BaseUnit)anIterator.next()).equals(second));
		assertTrue(((BaseUnit)anIterator.next()).equals(meter));
		assertTrue(((BaseUnit)anIterator.next()).equals(second));
		
	}
	
	public void testMultiplyByNullUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterBySecond = meter.multiplyBy(second);
			
		assertTrue(meterBySecond.multiplyBy(NullUnit.newInstance()).equals(meterBySecond));
	}
	
	public void testMultiplyByNumber() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterBySecond = meter.multiplyBy(second);
	
		assertTrue(meterBySecond.multiplyBy(new SmallInteger(5)).equals(meterBySecond.with(new SmallInteger(5))));
		assertTrue(meterBySecond.multiplyBy(new SmallInteger(5)).numerator().equals(meterBySecond.with(new SmallInteger(5))));
		assertTrue(meterBySecond.multiplyBy(new SmallInteger(5)).denominator().equals(new SmallInteger(1)));
		
		assertTrue(meterBySecond.multiplyBy(new SmallInteger(5.5)).equals(meterBySecond.with(new SmallInteger(5.5))));
		assertTrue(meterBySecond.multiplyBy(new SmallInteger(5.5)).numerator().equals(meterBySecond.with(new SmallInteger(5.5))));
		assertTrue(meterBySecond.multiplyBy(new SmallInteger(5.5)).denominator().equals(new SmallInteger(1)));
	}
		
	public void testMultiplyBySimpleUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterBySecond = meter.multiplyBy(second);

		assertTrue(((MultipliedUnit)meterBySecond.multiplyBy(second)).factorsSize() == 3);
		Iterator anIterator = ((MultipliedUnit)meterBySecond.multiplyBy(second)).factors().iterator();
		assertTrue(((BaseUnit)anIterator.next()).equals(meter));
		assertTrue(((BaseUnit)anIterator.next()).equals(second));
		assertTrue(((BaseUnit)anIterator.next()).equals(second));
	}
	
	public void testNumberDividedByUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterBySecond = meter.multiplyBy(second);
	
		assertTrue((new SmallInteger(5)).divideBy(meterBySecond).equals((new SmallInteger(5)).divideBy(meterBySecond.with(new SmallInteger(1)))));
		assertTrue((new SmallInteger(5)).divideBy(meterBySecond).numerator().equals(new SmallInteger(5)));
		assertTrue((new SmallInteger(5)).divideBy(meterBySecond).denominator().equals(meterBySecond.with(new SmallInteger(1))));
		
		assertTrue((new SmallInteger(5.5)).divideBy(meterBySecond).equals((new SmallInteger(5.5)).divideBy(meterBySecond.with(new SmallInteger(1)))));
		assertTrue((new SmallInteger(5.5)).divideBy(meterBySecond).numerator().equals(new SmallInteger(5.5)));
		assertTrue((new SmallInteger(5.5)).divideBy(meterBySecond).denominator().equals(meterBySecond.with(new SmallInteger(1))));

	}
	
	public void testNumberDividedByUnitTwice() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterBySecond = meter.multiplyBy(second);
		BaseUnit kelvin = new BaseUnit("kelvin");

		assertTrue((new SmallInteger(5)).divideBy(kelvin).divideBy(meterBySecond).equals((new SmallInteger(5)).divideBy(kelvin.multiplyBy(meterBySecond.with(new SmallInteger(1))))));
		assertTrue((new SmallInteger(5)).divideBy(kelvin).divideBy(meterBySecond).numerator().equals(new SmallInteger(5)));
		assertTrue((new SmallInteger(5)).divideBy(kelvin).divideBy(meterBySecond).denominator().equals(kelvin.multiplyBy(meterBySecond.with(new SmallInteger(1)))));
		
		assertTrue((new SmallInteger(5.5)).divideBy(kelvin).divideBy(meterBySecond).equals((new SmallInteger(5.5)).divideBy(kelvin.multiplyBy(meterBySecond.with(new SmallInteger(1))))));
		assertTrue((new SmallInteger(5.5)).divideBy(kelvin).divideBy(meterBySecond).numerator().equals(new SmallInteger(5.5)));
		assertTrue((new SmallInteger(5.5)).divideBy(kelvin).divideBy(meterBySecond).denominator().equals(kelvin.multiplyBy(meterBySecond.with(new SmallInteger(1)))));

	}
	
	public void testNumberMultipliedByUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterBySecond = meter.multiplyBy(second);
	
		assertTrue((new SmallInteger(5)).multiplyBy(meterBySecond).equals(meterBySecond.with(new SmallInteger(5))));
		assertTrue((new SmallInteger(5)).multiplyBy(meterBySecond).numerator().equals(meterBySecond.with(new SmallInteger(5))));
		assertTrue((new SmallInteger(5)).multiplyBy(meterBySecond).denominator().equals(new SmallInteger(1)));
		
		assertTrue((new SmallInteger(5.5)).multiplyBy(meterBySecond).equals(meterBySecond.with(new SmallInteger(5.5))));
		assertTrue((new SmallInteger(5.5)).multiplyBy(meterBySecond).numerator().equals(meterBySecond.with(new SmallInteger(5.5))));
		assertTrue((new SmallInteger(5.5)).multiplyBy(meterBySecond).denominator().equals(new SmallInteger(1)));

	}
	
	public void testNumberMultipliedByUnitTwice() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterBySecond = meter.multiplyBy(second);
		BaseUnit kelvin = new BaseUnit("kelvin");

		assertTrue((new SmallInteger(5)).multiplyBy(kelvin).multiplyBy(meterBySecond).equals(kelvin.multiplyBy(meterBySecond.with(new SmallInteger(5)))));
		assertTrue((new SmallInteger(5)).multiplyBy(kelvin).multiplyBy(meterBySecond).numerator().equals(kelvin.multiplyBy(meterBySecond.with(new SmallInteger(5)))));
		assertTrue((new SmallInteger(5)).multiplyBy(kelvin).multiplyBy(meterBySecond).denominator().equals(new SmallInteger(1)));
		
		assertTrue((new SmallInteger(5.5)).multiplyBy(kelvin).multiplyBy(meterBySecond).equals(kelvin.multiplyBy(meterBySecond.with(new SmallInteger(5.5)))));
		assertTrue((new SmallInteger(5.5)).multiplyBy(kelvin).multiplyBy(meterBySecond).numerator().equals(kelvin.multiplyBy(meterBySecond.with(new SmallInteger(5.5)))));
		assertTrue((new SmallInteger(5.5)).multiplyBy(kelvin).multiplyBy(meterBySecond).denominator().equals(new SmallInteger(1)));
	}
	
	// Test Comparing ---------------------------------------------------------
	
	public void testEqualMultipliedUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterBySecond = meter.multiplyBy(second);
		BaseUnit kelvin = new BaseUnit("kelvin");
		BaseUnit litre = new BaseUnit("litre");
		
		assertTrue(meterBySecond.equals(meterBySecond));
		assertTrue(meterBySecond.equals(meter.multiplyBy(second)));
		
		assertFalse(meterBySecond.equals(meter.multiplyBy(kelvin)));
		assertFalse(meterBySecond.equals(meter.multiplyBy(second).multiplyBy(kelvin)));
		assertFalse(meterBySecond.equals(kelvin.multiplyBy(litre)));
	}
	
	public void testEqualMultipliedUnitTwinUnits() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterBySecond = meter.multiplyBy(second);

		
		assertTrue(meterBySecond.equals(meterBySecond));
		assertTrue(meterBySecond.equals(meter.multiplyBy(second)));
		
		assertFalse(meterBySecond.equals(meter.multiplyBy(meter)));
	}
	
	public void testEqualDividedUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterBySecond = meter.multiplyBy(second);
		
		assertFalse(meterBySecond.equals(meter.divideBy(second)));
	}
	
	public void testEqualNullUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterBySecond = meter.multiplyBy(second);
		
		assertFalse(meterBySecond.equals(NullUnit.newInstance()));
	}
	
	public void testEqualSimpleUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterBySecond = meter.multiplyBy(second);
		BaseUnit kelvin = new BaseUnit("kelvin");
		
		assertFalse(meterBySecond.equals(kelvin));
		assertFalse(meterBySecond.equals(meter));
		assertFalse(meterBySecond.equals(second));
	}
	
	// Test measurement creation ---------------------------------------

	public void testMeasurementCreation() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterBySecond = meter.multiplyBy(second);
			
		assertTrue(meterBySecond.with(new SmallInteger(10)).equals(new Measure(new SmallInteger(10),(UnitBehavior)meterBySecond)));
	}
	
	// Test Quering -----------------------------------------------------
	
	public void testSameDomainAsMultipliedUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterBySecond = meter.multiplyBy(second);
		BaseUnit litre = new BaseUnit("litre");
		
		assertTrue(((UnitBehavior)meterBySecond).sameDomainAs(meterBySecond));
		assertFalse(((UnitBehavior)meterBySecond).sameDomainAs(meter.multiplyBy(litre)));
		assertFalse(((UnitBehavior)meterBySecond).sameDomainAs(litre.multiplyBy(litre)));
	}
	
	public void testSameDomainAsDividedUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterBySecond = meter.multiplyBy(second);
				
		assertFalse(((UnitBehavior)meterBySecond).sameDomainAs(meter.divideBy(second)));
	}
	
	public void testSameDomainAsNullUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterBySecond = meter.multiplyBy(second);
				
		assertFalse(((UnitBehavior)meterBySecond).sameDomainAs(NullUnit.newInstance()));
	}
	
	public void testSameDomainAsSameSimpleUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterBySecond = meter.multiplyBy(second);
				
		assertFalse(((UnitBehavior)meterBySecond).sameDomainAs(meter));
	}
	
	public void testUnitMultiplyByMeasure() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		ArithmeticObject meterBySecond = meter.multiplyBy(second);
		assertTrue(meterBySecond.multiplyBy(new Measure(new SmallInteger(1),meter)).equals(new Measure(new SmallInteger(1),(UnitBehavior)meterBySecond.multiplyBy(meter))));
	}
	
}

