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
import com.measureModel.units.NotProportionalDerivedUnit;
import com.measureModel.units.NullUnit;
import com.measureModel.units.ProportionalDerivedUnit;
import com.measureModel.units.UnitBehavior;
import com.measureModel.units.providers.CelsiusToKelvinConversionFunction;
import com.measureModel.units.providers.KelvinToCelsiusConversionFunction;

public class NotProportionalDerivedUnitTest extends TestCase {

	private BaseUnit kelvin;

	protected void setUp() throws Exception {
		kelvin = new BaseUnit("kelvin");
	}

	// Test Accessing -------------------------------

	public void testBaseUnit() {
		NotProportionalDerivedUnit aUnit = new NotProportionalDerivedUnit(
				kelvin, new CelsiusToKelvinConversionFunction(),
				new KelvinToCelsiusConversionFunction(), "celsius", "celcius",
				"C");
		
		assertTrue(aUnit.baseUnit().equals(kelvin));
	}

	public void testDenominator() {
		NotProportionalDerivedUnit aUnit = new NotProportionalDerivedUnit(
				kelvin, new CelsiusToKelvinConversionFunction(),
				new KelvinToCelsiusConversionFunction(), "celsius", "celcius",
				"C");
		
		assertTrue(aUnit.denominator().equals(NullUnit.newInstance()));
	}

	public void testName() {
		NotProportionalDerivedUnit aUnit = new NotProportionalDerivedUnit(
				kelvin, new CelsiusToKelvinConversionFunction(),
				new KelvinToCelsiusConversionFunction(), "celsius", "celcius2",
				"C");
		assertTrue(aUnit.name().equals("celsius"));
	}

	public void testNameForMany() {
		NotProportionalDerivedUnit aUnit = new NotProportionalDerivedUnit(
				kelvin, new CelsiusToKelvinConversionFunction(),
				new KelvinToCelsiusConversionFunction(), "celsius", "celsius2",
				"C");
		NotProportionalDerivedUnit anotherUnit = new NotProportionalDerivedUnit(
				kelvin, new CelsiusToKelvinConversionFunction(),
				new KelvinToCelsiusConversionFunction(), "celsius");
		assertTrue(aUnit.nameForMany().equals("celsius2"));
		assertTrue(anotherUnit.nameForMany().equals("celsiuss"));
	}

	public void testNameForOne() {
		NotProportionalDerivedUnit aUnit = new NotProportionalDerivedUnit(
				kelvin, new CelsiusToKelvinConversionFunction(),
				new KelvinToCelsiusConversionFunction(), "celsius", "celsius2",
				"C");
		assertTrue(aUnit.nameForOne().equals("celsius"));
	}

	public void testNothingAmount() {
		NotProportionalDerivedUnit aUnit = new NotProportionalDerivedUnit(
				kelvin, new CelsiusToKelvinConversionFunction(),
				new KelvinToCelsiusConversionFunction(), "celsius", "celsius2",
				"C");
		assertTrue(aUnit.nothingAmount().equals(new Fraction(-5463,20)));
	}

	public void testNullMeasure() {
		NotProportionalDerivedUnit aUnit = new NotProportionalDerivedUnit(
				kelvin, new CelsiusToKelvinConversionFunction(),
				new KelvinToCelsiusConversionFunction(), "celsius", "celsius2",
				"C");
		assertTrue(aUnit.nullMeasure().amount().equals(new SmallInteger(0)));
		assertTrue(aUnit.nullMeasure().unit().equals(kelvin));
	}

	public void testReciprocal() {
		NotProportionalDerivedUnit aUnit = new NotProportionalDerivedUnit(
				kelvin, new CelsiusToKelvinConversionFunction(),
				new KelvinToCelsiusConversionFunction(), "celsius", "celcius2",
				"C");
		assertTrue(aUnit.reciprocal().equals(NullUnit.newInstance().divideBy(aUnit)));
	}

	public void testNumerator() {
		NotProportionalDerivedUnit aUnit = new NotProportionalDerivedUnit(
				kelvin, new CelsiusToKelvinConversionFunction(),
				new KelvinToCelsiusConversionFunction(), "celsius", "celcius2",
				"C");
		assertTrue(aUnit.numerator().equals(aUnit));
	}

	public void testSign() {
		NotProportionalDerivedUnit aUnit = new NotProportionalDerivedUnit(
				kelvin, new CelsiusToKelvinConversionFunction(),
				new KelvinToCelsiusConversionFunction(), "celsius", "celcius2",
				"C");
		NotProportionalDerivedUnit anotherUnit = new NotProportionalDerivedUnit(
				kelvin, new CelsiusToKelvinConversionFunction(),
				new KelvinToCelsiusConversionFunction(), "celsius");
		assertTrue(anotherUnit.sign().equals(NotProportionalDerivedUnit.defaultSign()));
		assertTrue(aUnit.sign().equals("C"));
	}

	// Test converting
	// --------------------------------------------------------------------------

	public void testConvertAmountToBaseUnit() {
		NotProportionalDerivedUnit aUnit = new NotProportionalDerivedUnit(
				kelvin, new CelsiusToKelvinConversionFunction(),
				new KelvinToCelsiusConversionFunction(), "celsius", "celcius2",
				"C");
		assertTrue(aUnit.convertAmountToBaseUnit(new SmallInteger(0)).equals(new Fraction(5463,20)));
		assertTrue(aUnit.convertAmountToBaseUnit(new SmallInteger(100)).equals(new Fraction(7463,20)));
	}

	public void testConvertToBaseUnit() {
		NotProportionalDerivedUnit aUnit = new NotProportionalDerivedUnit(
				kelvin, new CelsiusToKelvinConversionFunction(),
				new KelvinToCelsiusConversionFunction(), "celsius", "celcius2",
				"C");
		Measure aMeasure = new Measure(new SmallInteger(0),aUnit);
		assertTrue(aUnit.convertToBaseUnit(aMeasure).amount().equals(new Fraction(5463,20)));
		assertTrue(aUnit.convertToBaseUnit(aMeasure).unit().equals(kelvin));
	}

	/*public void testConvertAmountFromBaseUnit() {
		assertTrue(((ProportionalDerivedUnit) centavo)
				.convertAmountFromBaseUnit(new ExtendedNumber(0)).equals(
						new ExtendedNumber(0)));
		assertTrue(((ProportionalDerivedUnit) centavo)
				.convertAmountFromBaseUnit(new ExtendedNumber(1)).equals(
						new ExtendedNumber(100)));
		assertTrue(((ProportionalDerivedUnit) centavo)
				.convertAmountFromBaseUnit(new ExtendedNumber(100)).equals(
						new ExtendedNumber(10000)));
	}

	public void testConvertFromBaseUnit() {
		Measure aMeasure = new Measure(new ExtendedNumber(1),
				(UnitBehavior) centavo);
		assertTrue(((ProportionalDerivedUnit) centavo).convertFromBaseUnit(
				aMeasure).amount().equals(new ExtendedNumber(100)));
		assertTrue(((ProportionalDerivedUnit) centavo).convertFromBaseUnit(
				aMeasure).unit().equals(peso));
	}*/

	// Test Quering -----------------------------------------------------

	public void testSameDomainAs() {
		NotProportionalDerivedUnit aUnit = new NotProportionalDerivedUnit(
				kelvin, new CelsiusToKelvinConversionFunction(),
				new KelvinToCelsiusConversionFunction(), "celsius", "celcius2",
				"C");
		assertTrue(aUnit.sameDomainAs(aUnit));
		BaseUnit metro = new BaseUnit("metro");
		ProportionalDerivedUnit tenCents = new ProportionalDerivedUnit(metro,
				new Fraction(1, 10), "diez centavos");
		assertFalse(aUnit.sameDomainAs(tenCents));
		NotProportionalDerivedUnit anotherUnit = new NotProportionalDerivedUnit(
				metro, new CelsiusToKelvinConversionFunction(),
				new KelvinToCelsiusConversionFunction(), "celsius", "celcius2",
				"C");
		assertFalse(aUnit.sameDomainAs(metro));
		assertFalse(aUnit.sameDomainAs(anotherUnit));

	}
	
	public void testUnitMultiplyByMeasure() {
		NotProportionalDerivedUnit aUnit = new NotProportionalDerivedUnit(
				kelvin, new CelsiusToKelvinConversionFunction(),
				new KelvinToCelsiusConversionFunction(), "celsius", "celcius2",
				"C");
		BaseUnit metro = new BaseUnit("metro");
		assertTrue(aUnit.multiplyBy(new Measure(new SmallInteger(1),metro)).equals(new Measure(new SmallInteger(1),(UnitBehavior)aUnit.multiplyBy(metro))));
	}

}
