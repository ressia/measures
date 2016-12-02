package test.measureModel.units;

import com.developmentExceptions.exceptions.ShouldNotImplementException;
import com.measureModel.measurements.Measure;
import com.measureModel.measurements.exceptions.CanNotConvertMeasureException;
import com.measureModel.numbers.ExtendedNumber;
import com.measureModel.numbers.FloatNumber;
import com.measureModel.numbers.SmallInteger;
import com.measureModel.numbers.Fraction;
import com.measureModel.units.BaseUnit;
import com.measureModel.units.MultipliedUnit;
import com.measureModel.units.NullUnit;
import com.measureModel.units.ProportionalDerivedUnit;
import com.measureModel.units.UnitBehavior;

import junit.framework.TestCase;

public class BaseUnitTest extends TestCase {
		
	public void testCreationWithName() {
		String aName = "peso";
		BaseUnit aBaseUnit = new BaseUnit(aName);
		assertTrue(aBaseUnit.name().equals(aName));
		assertTrue(aBaseUnit.nameForOne().equals(aName));
		assertTrue(aBaseUnit.nameForMany().equals(aName + "s"));
		assertTrue(aBaseUnit.sign().equals(BaseUnit.defaultSign()));
	}
	
	public void testCreationWithNameForOneAndMany() {
		String aName = "peso";
		BaseUnit aBaseUnit = new BaseUnit(aName,aName + "s");
		assertTrue(aBaseUnit.name().equals(aName));
		assertTrue(aBaseUnit.nameForOne().equals(aName));
		assertTrue(aBaseUnit.nameForMany().equals(aName + "s"));
		assertTrue(aBaseUnit.sign().equals(BaseUnit.defaultSign()));
	}

	public void testCreationWithNameForOneAndManyAndSign() {
		String aName = "peso";
		BaseUnit aBaseUnit = new BaseUnit(aName,aName + "s","$");
		assertTrue(aBaseUnit.name().equals(aName));
		assertTrue(aBaseUnit.nameForOne().equals(aName));
		assertTrue(aBaseUnit.nameForMany().equals(aName + "s"));
		assertTrue(aBaseUnit.sign().equals("$"));
	}
	
	// Test Accessing -------------------------------
	
	public void testDenominator() {
		String aNameForOne = "peso";
		String aNameForMany = "pe";
		UnitBehavior aBaseUnit = new BaseUnit(aNameForOne,aNameForMany);
		assertTrue(aBaseUnit.denominator().equals(NullUnit.newInstance()));
	}
	
	public void testName() {
		BaseUnit aBaseUnit = new BaseUnit("peso");
		assertTrue(aBaseUnit.name().equals("peso"));
	}
	
	public void testNameFor() {
		BaseUnit aUnit = new BaseUnit("peso1","peso2");
		String aNameForOne = aUnit.nameForOne();
		String aNameForMany = aUnit.nameForMany();
				
		assertTrue(aUnit.nameFor(new SmallInteger(0)).equals(aNameForMany));
		assertTrue(aUnit.nameFor(new SmallInteger(1)).equals(aNameForOne));
		assertTrue(aUnit.nameFor(new SmallInteger(2)).equals(aNameForMany));
		assertTrue(aUnit.nameFor(new FloatNumber(1.87f)).equals(aNameForMany));
		assertTrue(aUnit.nameFor(new FloatNumber(0.11f)).equals(aNameForMany));
	}
	
	public void testNameForMany() {
		assertTrue((new BaseUnit("peso")).nameForMany().equals("pesos"));
		assertTrue((new BaseUnit("peso","pepe")).nameForMany().equals("pepe"));
		assertTrue((new BaseUnit("peso","pepe","$")).nameForMany().equals("pepe"));
	}
	
	public void testNameForOne() {
		BaseUnit aUnit = new BaseUnit("peso");
		assertTrue(aUnit.name().equals(aUnit.nameForOne()));
	}
	
	public void testNothingAmount() {
		assertTrue((new BaseUnit("peso")).nothingAmount().equals(new SmallInteger(0)));
	}
	
	public void testNullMeasure() {
		BaseUnit aUnit = new BaseUnit("peso");
		assertTrue(aUnit.nullMeasure().amount().equals(new SmallInteger(0)));
		assertTrue(aUnit.nullMeasure().unit().equals(aUnit));
	}
	
	public void testNumerator() {
		UnitBehavior aUnit = new BaseUnit("peso","pepe");
		assertTrue(aUnit.numerator().equals(aUnit));
	}
	
	public void testReciprocal() {
		UnitBehavior aUnit = new BaseUnit("peso");
		assertTrue(aUnit.reciprocal().equals(NullUnit.newInstance().divideBy(aUnit)));
	}
	
	public void testSign() {
		assertTrue((new BaseUnit("peso")).sign().equals(BaseUnit.defaultSign()));
		assertTrue((new BaseUnit("peso","pepe","$")).sign().equals("$"));
	}
	
	// test Converting --------------------------------
	public void testConvertAmountToBaseUnit() {
		BaseUnit aUnit = new BaseUnit("peso");
		assertTrue(aUnit.convertAmountToBaseUnit(new SmallInteger(0)).equals(new SmallInteger(0)));
		assertTrue(aUnit.convertAmountToBaseUnit(new SmallInteger(10)).equals(new SmallInteger(10)));
	}
		
	public void testConvertToBaseUnit() {
		BaseUnit aUnit = new BaseUnit("peso");
		Measure aMeasure = new Measure(new SmallInteger(10),aUnit);
		assertTrue(aUnit.convertToBaseUnit(aMeasure).equals(aMeasure));
	}
	
	// test arithmetic operations ------------------------------------------
	
	public void testDivideByDifferentBaseUnit() {
		UnitBehavior numerator = new BaseUnit("meter");
		UnitBehavior denominator = new BaseUnit("second");
		assertTrue(numerator.divideBy(denominator).numerator().equals(numerator));
		assertTrue(numerator.divideBy(denominator).denominator().equals(denominator));
	}
	
	public void testDivideByDifferentDomainDerivedUnit() {
		UnitBehavior numerator = new BaseUnit("meter");
		UnitBehavior hour = new BaseUnit("hour");
		ProportionalDerivedUnit denominator = new ProportionalDerivedUnit(hour,new Fraction(1,60),"minutes");
		
		assertTrue(numerator.divideBy(denominator).numerator().equals(numerator));
		assertTrue(numerator.divideBy(denominator).denominator().equals(denominator));
	}
	
	public void testDivideByDivideUnitSameBaseUnitNumerator() {
		UnitBehavior numerator = new BaseUnit("meter");
		UnitBehavior denominator = new BaseUnit("hour");
		assertTrue(numerator.divideBy(numerator.divideBy(denominator)).numerator().equals(denominator));
	}
	
	public void testDivideByDivideUnitSameDomainDerivedUnitNumerator() {
		UnitBehavior meter = new BaseUnit("meter");
		UnitBehavior centimeter = new ProportionalDerivedUnit(meter,new Fraction(1,100),"centimeter");
		UnitBehavior hour = new BaseUnit("hour");
		
		assertTrue(meter.divideBy(centimeter.divideBy(hour)).numerator().equals(meter.times(hour)));
		assertTrue(meter.divideBy(centimeter.divideBy(hour)).denominator().equals(centimeter));
	}
	
	public void testDivideByMultipliedUnit() {
		UnitBehavior numerator = new BaseUnit("meter");
		UnitBehavior denominator = new BaseUnit("hour");
		
		assertTrue(numerator.divideBy(numerator.multiplyBy(denominator)).numerator().equals(NullUnit.newInstance()));
		assertTrue(numerator.divideBy(numerator.multiplyBy(denominator)).denominator().equals(denominator));
		
		assertTrue(numerator.divideBy(denominator.multiplyBy(denominator)).numerator().equals(numerator));
		assertTrue(numerator.divideBy(denominator.multiplyBy(denominator)).denominator().equals(denominator.multiplyBy(denominator)));
	}

	public void testDivideByNullUnit() {
		UnitBehavior meter = new BaseUnit("meter");
		assertTrue(meter.divideBy(NullUnit.newInstance()).equals(meter));
	}
	
	public void testDivideByNumber() {
		BaseUnit kelvin = new BaseUnit("kelvin");
		
		assertTrue(kelvin.divideBy(new SmallInteger(5)).equals(kelvin.with(new Fraction(1,5))));
		assertTrue(kelvin.divideBy(new SmallInteger(5)).numerator().equals(kelvin.with(new SmallInteger(1))));
		assertTrue(kelvin.divideBy(new SmallInteger(5)).denominator().equals(new SmallInteger(5)));

		assertTrue(kelvin.divideBy(new SmallInteger(5.5)).equals(kelvin.with(new Fraction(1,new SmallInteger(5.5)))));
		assertTrue(kelvin.divideBy(new SmallInteger(5.5)).numerator().equals(kelvin.with(new Fraction(1,new SmallInteger(5.5))).numerator()));
		assertTrue(kelvin.divideBy(new SmallInteger(5.5)).denominator().equals(kelvin.with(new Fraction(1,new SmallInteger(5.5))).denominator()));
	}
	
	public void testDivideBySameBaseUnit() {
		UnitBehavior numerator = new BaseUnit("peso");
		assertTrue(numerator.divideBy(numerator).equals(NullUnit.newInstance()));
	}
	
	public void testDivideBySameDomainDerivedUnit() {
		UnitBehavior numerator = new BaseUnit("meter");
		UnitBehavior denominator = new ProportionalDerivedUnit(numerator,new Fraction(1,100),"centimeter");
		
		assertTrue(numerator.divideBy(denominator).numerator().equals(numerator));
		assertTrue(numerator.divideBy(denominator).denominator().equals(denominator));
	}
	
	public void testMultiplyByDividedUnit() {
		UnitBehavior meter = new BaseUnit("meter");
		UnitBehavior second = new BaseUnit("second");
		UnitBehavior kelvin = new BaseUnit("kelvin");
		
		assertTrue(meter.multiplyBy(second.divideBy(kelvin)).numerator().equals(meter.multiplyBy(second)));
		assertTrue(meter.multiplyBy(second.divideBy(kelvin)).denominator().equals(kelvin));
	}
	
	public void testMultiplyByMultipliedUnit() {
		UnitBehavior meter = new BaseUnit("meter");
		UnitBehavior second = new BaseUnit("second");
		UnitBehavior kelvin = new BaseUnit("kelvin");
		
		assertTrue(((MultipliedUnit)meter.multiplyBy(second.multiplyBy(kelvin))).factorsSize() == 3);
		assertTrue(((MultipliedUnit)meter.multiplyBy(second.multiplyBy(kelvin))).factors().contains(meter));
		assertTrue(((MultipliedUnit)meter.multiplyBy(second.multiplyBy(kelvin))).factors().contains(second));
		assertTrue(((MultipliedUnit)meter.multiplyBy(second.multiplyBy(kelvin))).factors().contains(kelvin));
	}
	
	public void testMultiplyByNullUnit() {
		UnitBehavior meter = new BaseUnit("meter");
		assertTrue(meter.multiplyBy(NullUnit.newInstance()).equals(meter));
	}
	
	public void testMultiplyByNumber() {
		BaseUnit kelvin = new BaseUnit("kelvin");
		
		assertTrue(kelvin.multiplyBy(new SmallInteger(5)).equals(kelvin.with(new SmallInteger(5))));
		
		assertTrue(kelvin.multiplyBy(new SmallInteger(5)).numerator().equals(kelvin.with(new SmallInteger(5))));
		assertTrue(kelvin.multiplyBy(new SmallInteger(1)).denominator().equals(new SmallInteger(1)));
		assertTrue(kelvin.multiplyBy(new SmallInteger(5)).denominator().equals(new SmallInteger(1)));
		
		assertTrue(kelvin.multiplyBy(new SmallInteger(5.5)).equals(kelvin.with(new SmallInteger(5.5))));
		assertTrue(kelvin.multiplyBy(new SmallInteger(5.5)).numerator().equals(kelvin.with(new SmallInteger(5.5)).numerator()));
		assertTrue(kelvin.multiplyBy(new SmallInteger(5.5)).denominator().equals(kelvin.with(new SmallInteger(5.5)).denominator()));
	}
	
	public void testMultiplyBySimpleUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit second = new BaseUnit("second");
		
		assertTrue(((MultipliedUnit)meter.multiplyBy(second)).factorsSize() == 2);
		assertTrue(((MultipliedUnit)meter.multiplyBy(second)).factors().contains(meter));
		assertTrue(((MultipliedUnit)meter.multiplyBy(second)).factors().contains(second));
	}
	
	public void testNumberDividedByUnit() {
		BaseUnit kelvin = new BaseUnit("kelvin");
		
		Measure aMeasure = new Measure(new SmallInteger(5),(UnitBehavior)NullUnit.newInstance().divideBy(kelvin)); 
		assertTrue((new SmallInteger(5)).divideBy(kelvin).equals(new Measure(new SmallInteger(5),(UnitBehavior)NullUnit.newInstance().divideBy(kelvin))));
		assertTrue((new SmallInteger(5)).divideBy(kelvin.with(new SmallInteger(1))).equals(new Measure(new SmallInteger(5),(UnitBehavior)NullUnit.newInstance().divideBy(kelvin))));
		assertTrue((new SmallInteger(5)).divideBy(kelvin).equals(aMeasure));
		assertTrue((new SmallInteger(5)).divideBy(kelvin).numerator().equals(new SmallInteger(5)));
		assertTrue((new SmallInteger(5)).divideBy(kelvin).denominator().equals(kelvin.with(new SmallInteger(1))));
		assertTrue((new SmallInteger(5)).divideBy(kelvin.with(new SmallInteger(1))).numerator().equals(new SmallInteger(5)));
		assertTrue((new SmallInteger(5)).divideBy(kelvin.with(new SmallInteger(1))).denominator().equals(kelvin.with(new SmallInteger(1))));
		
		assertTrue((new FloatNumber(5.5)).divideBy(kelvin.with(new SmallInteger(1))).equals(new Measure(new FloatNumber(5.5),(UnitBehavior)NullUnit.newInstance().divideBy(kelvin))));
		assertTrue((new FloatNumber(5.5)).divideBy(kelvin).numerator().equals(new FloatNumber(5.5)));
		assertTrue((new FloatNumber(5.5)).divideBy(kelvin).denominator().equals(kelvin.with(new SmallInteger(1)))); 

		
	}
	
	public void testNumberDividedByUnitTwice() {
		BaseUnit kelvin = new BaseUnit("kelvin");
		BaseUnit meter = new BaseUnit("meter");
		
		Measure aMeasure = new Measure(new SmallInteger(5),(UnitBehavior)NullUnit.newInstance().divideBy(kelvin.multiplyBy(meter))); 
		
		assertTrue((new SmallInteger(5)).divideBy(kelvin).divideBy(meter).equals(aMeasure));
		assertTrue((new SmallInteger(5)).divideBy(kelvin).divideBy(meter).numerator().equals(new SmallInteger(5)));
		assertTrue((new SmallInteger(5)).divideBy(kelvin).divideBy(meter).denominator().equals(kelvin.multiplyBy(meter).with(new SmallInteger(1))));
		
		assertTrue((new FloatNumber(5.5f)).divideBy(kelvin).divideBy(meter).equals((new FloatNumber(5.5f)).divideBy(kelvin.multiplyBy(meter).with(new SmallInteger(1)))));
		assertTrue((new FloatNumber(5.5f)).divideBy(kelvin).divideBy(meter).numerator().equals(new FloatNumber(5.5f)));
		assertTrue((new FloatNumber(5.5f)).divideBy(kelvin).divideBy(meter).denominator().equals(kelvin.multiplyBy(meter).with(new SmallInteger(1))));
		
	}
	
	public void testNumberMultipliedByUnit() {
		BaseUnit kelvin = new BaseUnit("kelvin");
		
		assertTrue((new SmallInteger(5)).multiplyBy(kelvin).equals(kelvin.with(new SmallInteger(5))));
		assertTrue((new SmallInteger(5)).multiplyBy(kelvin).numerator().equals(kelvin.with(new SmallInteger(5))));
		assertTrue((new SmallInteger(5)).multiplyBy(kelvin).denominator().equals(new SmallInteger(1)));
		assertTrue((new SmallInteger(5)).multiplyBy(kelvin.with(new SmallInteger(1))).numerator().equals(kelvin.with(new SmallInteger(5))));
		assertTrue((new SmallInteger(5)).multiplyBy(kelvin.with(new SmallInteger(1))).denominator().equals(new SmallInteger(1)));
		
		assertTrue((new FloatNumber(5.5)).multiplyBy(kelvin).equals(kelvin.with(new FloatNumber(5.5))));
		assertTrue((new FloatNumber(5.5)).multiplyBy(kelvin).numerator().equals(kelvin.with(new FloatNumber(5.5)).numerator()));
		assertTrue((new FloatNumber(5.5)).multiplyBy(kelvin).denominator().equals(kelvin.with(new FloatNumber(5.5)).denominator()));
	}
	
	public void testNumberMultipliedByUnitTwice() {
		BaseUnit kelvin = new BaseUnit("kelvin");
		BaseUnit meter = new BaseUnit("meter");

		assertTrue((new SmallInteger(5)).multiplyBy(kelvin).multiplyBy(meter).equals(kelvin.multiplyBy(meter).with(new SmallInteger(5))));
		assertTrue((new SmallInteger(5)).multiplyBy(kelvin).multiplyBy(meter).numerator().equals(kelvin.multiplyBy(meter).with(new SmallInteger(5))));
		assertTrue((new SmallInteger(5)).multiplyBy(kelvin).multiplyBy(meter).denominator().equals(new SmallInteger(1)));
			
		assertTrue((new FloatNumber(5.5)).multiplyBy(kelvin).multiplyBy(meter).equals(kelvin.multiplyBy(meter).with(new FloatNumber(5.5))));
		assertTrue((new FloatNumber(5.5)).multiplyBy(kelvin).multiplyBy(meter).numerator().equals(kelvin.multiplyBy(meter).with(new FloatNumber(5.5)).numerator()));
		assertTrue((new FloatNumber(5.5)).multiplyBy(kelvin).multiplyBy(meter).denominator().equals(kelvin.multiplyBy(meter).with(new FloatNumber(5.5)).denominator()));
	}
	
	public void testUnitMultiplyByMeasure() {
		BaseUnit kelvin = new BaseUnit("kelvin");
		assertTrue(kelvin.multiplyBy(new Measure(new SmallInteger(1),kelvin)).equals(new Measure(new SmallInteger(1),(UnitBehavior)kelvin.multiplyBy(kelvin))));
	}
	
	// test arithmetic operations -----------------------------------------------
	
	public void testEqualDerivedSameBaseUnit() {
		BaseUnit meter = new BaseUnit("meter");
		ProportionalDerivedUnit centimeter = new ProportionalDerivedUnit(meter,new Fraction(1,100),"centimeter");
		assertFalse(meter.equals(centimeter));
	}
	
	public void testEqualDifferentBaseUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit hour = new BaseUnit("hour");
		assertFalse(meter.equals(hour));
	}
	
	public void testEqualDividedUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit hour = new BaseUnit("hour");
		assertFalse(meter.equals(meter.divideBy(hour)));
	}
	
	public void testEqualMultipliedUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit hour = new BaseUnit("hour");
		assertFalse(meter.equals(meter.multiplyBy(hour)));
	}
	
	public void testEqualNullUnit() {
		BaseUnit meter = new BaseUnit("meter");
		assertFalse(meter.equals(NullUnit.newInstance()));
	}
	
	public void testEqualSameBaseUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit hour = new BaseUnit("hour");
		BaseUnit anotherMeter = new BaseUnit("meter");
		assertTrue(meter.equals(meter));
		assertTrue(meter.equals(anotherMeter));
		assertTrue(anotherMeter.equals(meter));
		assertFalse(hour.equals(meter));
		assertFalse(meter.equals(hour));
	}
	
	// test mesurement creation ---------------------------
	
	public void testMeasureCreation() {
		BaseUnit meter = new BaseUnit("meter");
		Measure aMeasure = new Measure(new SmallInteger(10),meter);
		assertTrue(meter.with(new SmallInteger(10)).equals(aMeasure));
	}
	
	// test -----------------------------------------------
	
	public void testNameForUndefinedAmount() {
		BaseUnit aBaseUnit = new BaseUnit("meter","meters");
		assertTrue(aBaseUnit.nameForUndefinedAmount().equals(aBaseUnit.nameForMany()));
	}
	
	// test Quering ---------------------------------------
	
	public void testSameDomainAsDerivedUnit() {
		BaseUnit meter = new BaseUnit("meter");
		ProportionalDerivedUnit centimeter = new ProportionalDerivedUnit(meter,new Fraction(1,100),"centimeter");
		
		assertTrue(meter.sameDomainAs(centimeter));
	}
	
	public void testSameDomainAsDerivedUnitOfOtherDomain() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit hour = new BaseUnit("hour");
		ProportionalDerivedUnit minute = new ProportionalDerivedUnit(hour,new Fraction(1,60),"minute");
		
		assertFalse(meter.sameDomainAs(minute));
	} 
	
	public void testSameDomainAsDifferentBaseUnit() {
		BaseUnit peso = new BaseUnit("peso");
		BaseUnit meter = new BaseUnit("meter");
	
		assertFalse(peso.sameDomainAs(meter));
	}
	
	public void testSameDomainAsDividedUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit hour = new BaseUnit("hour");
	
		assertFalse(meter.sameDomainAs(meter.divideBy(hour)));
	}
	
	public void testSameDomainAsMultipliedUnit() {
		BaseUnit meter = new BaseUnit("meter");
		BaseUnit hour = new BaseUnit("hour");
	
		assertFalse(meter.sameDomainAs(meter.multiplyBy(hour)));
	}
	
	public void testSameDomainAsNullUnit() {
		BaseUnit meter = new BaseUnit("meter");
	
		assertFalse(meter.sameDomainAs(NullUnit.newInstance()));
	}
	
	public void testSameDomainAsSameBaseUnit() {
		BaseUnit meter = new BaseUnit("meter");
	
		assertTrue(meter.sameDomainAs(meter));
	}
	
}

