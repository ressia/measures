package test.measureModel.measurements;

import junit.framework.TestCase;
import test.measureModel.units.resources.UnitsTestResource;

import com.measureModel.measurements.Measure;
import com.measureModel.measurements.MeasureBag;
import com.measureModel.measurements.MeasureBehavior;
import com.measureModel.measurements.exceptions.CanNotConvertMeasureException;
import com.measureModel.measurements.exceptions.UndefinedAmountForMeasureBagException;
import com.measureModel.measurements.exceptions.UndefinedUnitForMeasureBagException;
import com.measureModel.numbers.ExtendedNumber;
import com.measureModel.numbers.FloatNumber;
import com.measureModel.numbers.Fraction;
import com.measureModel.numbers.LargeInteger;
import com.measureModel.numbers.SmallInteger;
import com.measureModel.numbers.exceptions.DivisionByZeroException;
import com.measureModel.units.NullUnit;
import com.measureModel.units.UnitBehavior;

public class MeasureTest extends TestCase {

	private UnitsTestResource units;

	public void setUp() {
		units = UnitsTestResource.newInstance();
	}

	/**
	 * Helpers
	 */

	public Measure zeroDollars() {
		return new Measure(new SmallInteger(0), dollar());
	}
	
	public Measure zeroCentimeters() {
		return new Measure(new SmallInteger(0), centimeter());
	}
	
	public Measure twentyDollars() {
		return new Measure(new SmallInteger(20), dollar());
	}
	
	public Measure twentyPesos() {
		return new Measure(new SmallInteger(20), peso());
	}
	
	public Measure tenDollars() {
		return new Measure(new SmallInteger(10), dollar());
	}
	
	public Measure oneQuarter() {
		return new Measure(new SmallInteger(1), quarter());
	}
	
	public UnitBehavior quarter() {
		return units.quarter();
	}

	public UnitBehavior dollar() {
		return units.dollar();
	}
	
	public UnitBehavior euro() {
		return units.euro();
	}

	public Measure tenEuros() {
		return new Measure(new SmallInteger(10), euro());
	}
	
	public Measure zeroPesos() {
		return new Measure(new SmallInteger(0), peso());
	}
	
	public Measure zeroMeters() {
		return new Measure(new SmallInteger(0), meter());
	}

	public Measure onePeso() {
		return new Measure(new SmallInteger(1), peso());
	}
	
	public Measure oneDollar() {
		return new Measure(new SmallInteger(1), dollar());
	}
	
	public Measure fivePesos() {
		return new Measure(new SmallInteger(1), peso());
	}

	public Measure tenPesos() {
		return new Measure(new SmallInteger(10), peso());
	}
	
	public Measure minusTenPesos() {
		return new Measure(new SmallInteger(-10), peso());
	}

	public UnitBehavior peso() {
		return units.peso();
	}

	public Measure oneMeter() {
		return new Measure(new SmallInteger(1), meter());
	}

	public Measure tenMeters() {
		return new Measure(new SmallInteger(10), meter());
	}

	public UnitBehavior meter() {
		return units.meter();
	}

	public Measure oneKilometer() {
		return new Measure(new SmallInteger(1), kilometer());
	}

	public UnitBehavior kilometer() {
		return units.kilometer();
	}

	public Measure oneCentimeter() {
		return new Measure(new SmallInteger(1), centimeter());
	}

	public UnitBehavior centimeter() {
		return units.centimeter();
	}

	public Measure zeroCelsius() {
		return new Measure(new SmallInteger(0), celsius());
	}

	public Measure zeroFahrenheit() {
		return new Measure(new SmallInteger(0), fahrenheit());
	}
	
	public UnitBehavior celsius() {
		return units.celsius();
	}
	
	public UnitBehavior fahrenheit() {
		return units.fahrenheit();
	}
	
	public UnitBehavior kelvin() {
		return units.kelvin();
	}
	
	public UnitBehavior second() {
		return units.second();
	}
	
	public UnitBehavior minute() {
		return units.minute();
	}
	
	public UnitBehavior millimeter() {
		return units.millimeter();
	}
	
	public Measure oneSecond() {
		return new Measure(new SmallInteger(1), second());
	}
	
	public Measure twoSecond() {
		return new Measure(new SmallInteger(2), second());
	}
	
	public Measure oneMinute() {
		return new Measure(new SmallInteger(1), minute());
	}
	
	public Measure thousandMillimeters() {
		return new Measure(new SmallInteger(1000), millimeter());
	} 
	
	/**
	 * End Helpers
	 */

	public void testAbs() {
		assertTrue(onePeso().abs().equals(onePeso()));
		assertTrue(onePeso().unit().with(new SmallInteger(0)).abs().equals(onePeso().unit().with(new SmallInteger(0))));
		assertTrue(onePeso().unit().with(new SmallInteger(-1)).abs().equals(onePeso().unit().with(new SmallInteger(1))));
	}
	


	/**
	 * Add Testing
	 */

	public void testAddAssociativity() {
		assertTrue(((Measure) oneMeter().plus(oneMeter().plus(oneKilometer()))).amount().equals(
				((Measure) oneMeter().plus(oneMeter()).plus(oneKilometer())).amount()));
		assertTrue(((Measure) oneMeter().plus(oneMeter().plus(oneKilometer()))).unit().equals(
				((Measure) oneMeter().plus(oneMeter()).plus(oneKilometer())).unit()));
	}
	
	public void testAddBaseUnit() {
		assertTrue(((Measure)oneMeter().plus(oneMeter())).amount().equals(new SmallInteger(2)));
		assertTrue(((Measure)oneMeter().plus(oneMeter())).unit().equals(meter()));
	}

	public void testAddBaseUnitAndDerivedUnit() {
		assertTrue(((Measure)oneKilometer().plus(oneMeter())).amount().equals(new SmallInteger(1001)));
		assertTrue(((Measure)oneKilometer().plus(oneMeter())).unit().equals(meter()));

		assertTrue(((Measure)zeroCelsius().plus(new Measure(new SmallInteger(32),fahrenheit()))).amount().equals(new Fraction(5463,10)));
		assertTrue(((Measure)zeroCelsius().plus(new Measure(new SmallInteger(32),fahrenheit()))).unit().equals(kelvin()));
	}
	
	public void testAddCommutative() {
		assertTrue(((Measure)oneMeter().plus(oneKilometer())).amount().equals(((Measure)oneKilometer().plus(oneMeter())).amount()));
		assertTrue(((Measure)oneMeter().plus(oneKilometer())).unit().equals(((Measure)oneKilometer().plus(oneMeter())).unit()));
	}
	
	public void testAddDerivedUnit() {
		assertTrue(((Measure)oneKilometer().plus(oneCentimeter())).amount().equals(new Fraction(100001,100)));
		assertTrue(((Measure)oneKilometer().plus(oneCentimeter())).unit().equals(meter()));
	}
	
	public void testAddDifferentBaseUnit() {
		Exception anException = null;
		try {
			((MeasureBag)tenPesos().plus(twentyDollars())).amount();
		} catch (UndefinedAmountForMeasureBagException ex) {
			anException = ex;
		}
		if (anException == null) {
			fail();
		}
		
		anException = null;
		try {
			((MeasureBag)tenPesos().plus(twentyDollars())).unit();
		} catch (UndefinedUnitForMeasureBagException ex) {
			anException = ex;
		}
		if (anException == null) {
			fail();
		}
		assertTrue(((MeasureBag)tenPesos().plus(twentyDollars())).numberOfMeasures() == 2);
		
		anException = null;
		try {
			((MeasureBag)tenPesos().plus(twentyDollars()).plus(tenEuros())).amount();
		} catch (UndefinedAmountForMeasureBagException ex) {
			anException = ex;
		}
		if (anException == null) {
			fail();
		}
		
		anException = null;
		try {
			((MeasureBag)tenPesos().plus(twentyDollars()).plus(tenEuros())).unit();
		} catch (UndefinedUnitForMeasureBagException ex) {
			anException = ex;
		}
		if (anException == null) {
			fail();
		}
	}
	
	public void testAddDifferentUnit() {
		MeasureBehavior a10pesosPlus20Dollars = (MeasureBehavior)tenPesos().plus(twentyDollars());
		assertTrue("Equality",a10pesosPlus20Dollars.equals(tenPesos().plus(twentyDollars())));
		assertTrue("Commutativity",a10pesosPlus20Dollars.equals(twentyDollars().plus(tenPesos())));
		assertTrue("Associativity",a10pesosPlus20Dollars.equals(tenPesos().plus(tenDollars()).plus(tenDollars())));
		assertTrue("Associativity",tenPesos().plus(twentyDollars().plus(tenEuros())).equals(tenPesos().plus(twentyDollars()).plus(tenEuros())));
		
		a10pesosPlus20Dollars.plus(tenPesos());
		assertTrue("Inmutability",a10pesosPlus20Dollars.equals(tenPesos().plus(twentyDollars())));
		assertTrue("Adding Zero",zeroPesos().plus(twentyDollars()).equals(twentyDollars()));
		assertTrue("Adding Zero to a bag should be the same bag without the nothing members",
					tenPesos().plus(twentyDollars()).plus(zeroPesos()).equals(twentyDollars().plus(tenPesos())));
		
		assertTrue("Adding Zero",zeroPesos().plus(zeroDollars()).equals(zeroDollars().plus(zeroPesos())));
	}
	
	public void testDividedMeasure() {
		assertTrue(((MeasureBag)tenMeters().plus(tenMeters().divideBy(fivePesos()))).atSameBaseUnitAs(meter()).equals(tenMeters()));
		assertTrue(((MeasureBag)tenMeters().plus(tenMeters().divideBy(fivePesos()))).atSameBaseUnitAs((UnitBehavior)meter().divideBy(peso())).equals(tenMeters().divideBy(fivePesos())));
	}
	
	public void testAddFloatWithMeasure() {
		assertTrue(((MeasureBag)(new FloatNumber(10.01)).plus(tenMeters())).atSameBaseUnitAs(meter()).equals(tenMeters()));
		assertTrue(((MeasureBag)(new FloatNumber(10.01)).plus(tenMeters())).atSameBaseUnitAs(NullUnit.newInstance()).equals(new Measure(new FloatNumber(10.01),NullUnit.newInstance())));
	}
	
	public void testAddFractionWithMeasure() {
		assertTrue(((MeasureBag)(new Fraction(1,3)).plus(tenMeters())).atSameBaseUnitAs(meter()).equals(tenMeters()));
		assertTrue(((MeasureBag)(new Fraction(1,3)).plus(tenMeters())).atSameBaseUnitAs(NullUnit.newInstance()).equals(new Measure(new Fraction(1,3),NullUnit.newInstance())));
	}
	
	public void testAddSmallIntegerWithMeasure() {
		assertTrue(((MeasureBag)(new SmallInteger(2)).plus(tenMeters())).atSameBaseUnitAs(meter()).equals(tenMeters()));
		assertTrue(((MeasureBag)(new SmallInteger(2)).plus(tenMeters())).atSameBaseUnitAs(NullUnit.newInstance()).equals(new Measure(new SmallInteger(2),NullUnit.newInstance())));
	}
	
	public void testAddLargeIntegerWithMeasure() {
		assertTrue(((MeasureBag)(new LargeInteger(2)).plus(tenMeters())).atSameBaseUnitAs(meter()).equals(tenMeters()));
		assertTrue(((MeasureBag)(new LargeInteger(2)).plus(tenMeters())).atSameBaseUnitAs(NullUnit.newInstance()).equals(new Measure(new LargeInteger(2),NullUnit.newInstance())));
	}
		
	public void testAddMultipliedMeasure() {
		assertTrue(((MeasureBag)tenMeters().plus(tenMeters().multiplyBy(fivePesos()))).atSameBaseUnitAs(meter()).equals(tenMeters()));
		assertTrue(((MeasureBag)tenMeters().plus(tenMeters().multiplyBy(fivePesos()))).atSameBaseUnitAs((UnitBehavior)meter().multiplyBy(peso())).equals(tenMeters().multiplyBy(fivePesos())));
	}
	
	public void testAddNumber() {
		assertTrue(((MeasureBag)tenMeters().plus(new SmallInteger(10))).atSameBaseUnitAs(meter()).equals(tenMeters()));
		assertTrue(((MeasureBag)tenMeters().plus(new SmallInteger(10))).atSameBaseUnitAs(NullUnit.newInstance()).equals(new Measure(new SmallInteger(10),NullUnit.newInstance())));
	}
	
	public void testSimplificationsAddingWithZero() {
		assertTrue(oneMeter().plus(new SmallInteger(0)).equals(oneMeter()));
		assertTrue(oneMeter().plus(new SmallInteger(0)).unit().equals(oneMeter().unit()));
	}
	
	/**
	 * Test Accessing
	 */

	public void testAmount() {
		assertTrue(oneMeter().amount().equals(new SmallInteger(1)));
		assertTrue(tenMeters().amount().equals(new SmallInteger(10)));
	}

	public void testBaseUnit() {
		assertTrue(oneMeter().baseUnit().equals(meter()));
		assertTrue(oneKilometer().baseUnit().equals(meter()));
		assertTrue(oneCentimeter().baseUnit().equals(meter()));
	}

	public void testConvertAmountToBaseUnit() {
		assertTrue(oneMeter().convertAmountToBaseUnit().equals(new SmallInteger(1)));
		assertTrue(oneKilometer().convertAmountToBaseUnit().equals(new SmallInteger(1000)));
		assertTrue(oneCentimeter().convertAmountToBaseUnit().equals(new Fraction(1, 100)));
	}

	public void testDenominator() {
		assertTrue(tenMeters().denominator().equals(new SmallInteger(1)));
	}

	public void testNumerator() {
		assertTrue(tenMeters().numerator().equals(tenMeters()));
		assertTrue(tenMeters().multiplyBy(tenMeters()).numerator().equals(tenMeters().multiplyBy(tenMeters())));
	}

	public void testMeasures() {
		assertTrue("Must be included only the own measure", oneMeter().measures().size() == 1);
		assertTrue("Itself must be included", oneMeter().measures().contains(oneMeter()));
	}

	public void testUnit() {
		assertTrue(onePeso().unit().equals(peso()));
		assertTrue(oneMeter().unit().equals(meter()));
	}
	
	/**
	 * Test magnitude protocol
	 */
	
	public void testBetween() {
		assertTrue(tenMeters().between(oneMeter(),new Measure(new SmallInteger(11),meter())));
		assertTrue(tenMeters().between(oneCentimeter(),oneKilometer()));
	}
	
	public void testBetweenAndNotInclusive() {
		assertTrue(tenMeters().betweenAndNotInclusive(oneMeter(),new Measure(new SmallInteger(11),meter())));
		assertTrue(tenMeters().betweenAndNotInclusive(oneCentimeter(),oneKilometer()));
	}
	
	public void testGreaterThan() {
		assertTrue(tenMeters().greaterThan(oneMeter()));
		assertTrue(oneKilometer().greaterThan(oneMeter()));
		assertFalse(oneCentimeter().greaterThan(oneMeter()));
		assertFalse(oneMeter().greaterThan(oneMeter()));
		assertFalse(thousandMillimeters().greaterThan(oneMeter()));
	}
	
	public void testGreaterOrEqualsThan() {
		assertTrue(tenMeters().greaterThanOrEqualTo(oneMeter()));
		assertTrue(oneKilometer().greaterThanOrEqualTo(oneMeter()));
		assertFalse(oneCentimeter().greaterThanOrEqualTo(oneMeter()));
		assertTrue(oneMeter().greaterThanOrEqualTo(oneMeter()));
		assertTrue(thousandMillimeters().greaterThanOrEqualTo(oneMeter()));
	}
	
	public void testLessThan() {
		assertFalse(tenMeters().lessThan(oneMeter()));
		assertFalse(oneKilometer().lessThan(oneMeter()));
		assertTrue(oneCentimeter().lessThan(oneMeter()));
		assertFalse(oneMeter().lessThan(oneMeter()));
		assertFalse(thousandMillimeters().lessThan(oneMeter()));
	}
	
	public void testLessThanOrEqualTo() {
		assertFalse(tenMeters().lessThanOrEqualTo(oneMeter()));
		assertFalse(oneKilometer().lessThanOrEqualTo(oneMeter()));
		assertTrue(oneCentimeter().lessThanOrEqualTo(oneMeter()));
		assertTrue(oneMeter().lessThanOrEqualTo(oneMeter()));
		assertTrue(thousandMillimeters().lessThanOrEqualTo(oneMeter()));
	}
	
	public void testGreaterThanNumber() {
		Exception anException = null;
		try {
			tenMeters().greaterThan(new SmallInteger(1));
		} catch (CanNotConvertMeasureException ex) {
			anException = ex;
			assertTrue(ex.sourceMeasure().equals(tenMeters()));
			assertTrue(ex.targetUnit().equals(NullUnit.newInstance()));
		}
		if (anException == null) {
			fail();
		}
		assertFalse((new Measure(new SmallInteger(1),NullUnit.newInstance())).greaterThan(new SmallInteger(1)));
		assertTrue((new Measure(new SmallInteger(2),NullUnit.newInstance())).greaterThan(new SmallInteger(1)));
	}
	
	public void testGreaterOrEqualsThanNumber() {
		Exception anException = null;
		try {
			tenMeters().greaterThanOrEqualTo(new SmallInteger(1));
		} catch (CanNotConvertMeasureException ex) {
			anException = ex;
			assertTrue(ex.sourceMeasure().equals(tenMeters()));
			assertTrue(ex.targetUnit().equals(NullUnit.newInstance()));
		}
		if (anException == null) {
			fail();
		}
		assertTrue((new Measure(new SmallInteger(1),NullUnit.newInstance())).greaterThanOrEqualTo(new SmallInteger(1)));
		assertTrue((new Measure(new SmallInteger(2),NullUnit.newInstance())).greaterThanOrEqualTo(new SmallInteger(1)));
	}
	
	public void testLessThanNumber() {
		Exception anException = null;
		try {
			tenMeters().lessThan(new SmallInteger(1));
		} catch (CanNotConvertMeasureException ex) {
			anException = ex;
			assertTrue(ex.sourceMeasure().equals(tenMeters()));
			assertTrue(ex.targetUnit().equals(NullUnit.newInstance()));
		}
		if (anException == null) {
			fail();
		}
		assertFalse((new Measure(new SmallInteger(1),NullUnit.newInstance())).lessThan(new SmallInteger(1)));
		assertTrue((new Measure(new SmallInteger(0),NullUnit.newInstance())).lessThan(new SmallInteger(1)));
	}
	
	public void testLessThanOrEqualToNumber() {
		Exception anException = null;
		try {
			tenMeters().lessThanOrEqualTo(new SmallInteger(1));
		} catch (CanNotConvertMeasureException ex) {
			anException = ex;
			assertTrue(ex.sourceMeasure().equals(tenMeters()));
			assertTrue(ex.targetUnit().equals(NullUnit.newInstance()));
		}
		if (anException == null) {
			fail();
		}
		assertTrue((new Measure(new SmallInteger(1),NullUnit.newInstance())).lessThanOrEqualTo(new SmallInteger(1)));
		assertTrue((new Measure(new SmallInteger(0),NullUnit.newInstance())).lessThanOrEqualTo(new SmallInteger(1)));
	}
	
	public void testMax() {
		assertTrue(tenMeters().max(oneMeter()).equals(tenMeters()));
		assertTrue(oneMeter().max(tenMeters()).equals(tenMeters()));
		assertTrue(tenMeters().max(oneKilometer()).equals(oneKilometer()));
		assertTrue(oneKilometer().max(tenMeters()).equals(oneKilometer()));
		assertTrue(oneCentimeter().max(oneKilometer()).equals(oneKilometer()));
		assertTrue(oneKilometer().max(oneKilometer()).equals(oneKilometer()));
	}
	
	public void testMin() {
		assertTrue(tenMeters().min(oneMeter()).equals(oneMeter()));
		assertTrue(oneMeter().min(tenMeters()).equals(oneMeter()));
		assertTrue(tenMeters().min(oneKilometer()).equals(tenMeters()));
		assertTrue(oneKilometer().min(tenMeters()).equals(tenMeters()));
		assertTrue(oneCentimeter().min(oneKilometer()).equals(oneCentimeter()));
		assertTrue(oneKilometer().min(oneKilometer()).equals(oneKilometer()));
	}
	
	public void testInvalidBetween() {
		Exception anException = null;
		try {
			tenMeters().between(oneMeter(), onePeso());
		} catch (CanNotConvertMeasureException ex) {
			anException = ex;
			assertTrue(ex.sourceMeasure().equals(tenMeters()));
			assertTrue(ex.targetUnit().equals(peso()));
		}
		if (anException == null) {
			fail();
		}
		anException = null;
		try {
			tenMeters().between(onePeso(), oneMeter());
		} catch (CanNotConvertMeasureException ex) {
			anException = ex;
			assertTrue(ex.sourceMeasure().equals(tenMeters()));
			assertTrue(ex.targetUnit().equals(peso()));
		}
		if (anException == null) {
			fail();
		}
		anException = null;
		try {
			tenMeters().between(new SmallInteger(1), new SmallInteger(2));
		} catch (CanNotConvertMeasureException ex) {
			anException = ex;
			assertTrue(ex.sourceMeasure().equals(tenMeters()));
			assertTrue(ex.targetUnit().equals(NullUnit.newInstance()));
		}
		if (anException == null) {
			fail();
		}
		
	}
	
	public void testInvalidBetweenNotInclusive() {
		Exception anException = null;
		try {
			tenMeters().betweenAndNotInclusive(oneMeter(), onePeso());
		} catch (CanNotConvertMeasureException ex) {
			anException = ex;
			assertTrue(ex.sourceMeasure().equals(tenMeters()));
			assertTrue(ex.targetUnit().equals(peso()));
		}
		if (anException == null) {
			fail();
		}
		anException = null;
		try {
			tenMeters().betweenAndNotInclusive(onePeso(), oneMeter());
		} catch (CanNotConvertMeasureException ex) {
			anException = ex;
			assertTrue(ex.sourceMeasure().equals(tenMeters()));
			assertTrue(ex.targetUnit().equals(peso()));
		}
		if (anException == null) {
			fail();
		}
		anException = null;
		try {
			tenMeters().betweenAndNotInclusive(new SmallInteger(1), new SmallInteger(2));
		} catch (CanNotConvertMeasureException ex) {
			anException = ex;
			assertTrue(ex.sourceMeasure().equals(tenMeters()));
			assertTrue(ex.targetUnit().equals(NullUnit.newInstance()));
		}
		if (anException == null) {
			fail();
		}
		
	}
	
	public void testInvalidGreaterThan() {
		Exception anException = null;
		try {
			tenMeters().greaterThan(onePeso());
		} catch (CanNotConvertMeasureException ex) {
			anException = ex;
			assertTrue(ex.sourceMeasure().equals(tenMeters()));
			assertTrue(ex.targetUnit().equals(peso()));
		}
		if (anException == null) {
			fail();
		}
	}
	
	public void testInvalidGreaterThanOrEqualTo() {
		Exception anException = null;
		try {
			tenMeters().greaterThanOrEqualTo(onePeso());
		} catch (CanNotConvertMeasureException ex) {
			anException = ex;
			assertTrue(ex.sourceMeasure().equals(tenMeters()));
			assertTrue(ex.targetUnit().equals(peso()));
		}
		if (anException == null) {
			fail();
		}
	}
	
	public void testInvalidLessThan() {
		Exception anException = null;
		try {
			tenMeters().lessThan(onePeso());
		} catch (CanNotConvertMeasureException ex) {
			anException = ex;
			assertTrue(ex.sourceMeasure().equals(tenMeters()));
			assertTrue(ex.targetUnit().equals(peso()));
		}
		if (anException == null) {
			fail();
		}
	}
	
	public void testInvalidLessThanOrEqualTo() {
		Exception anException = null;
		try {
			tenMeters().lessThanOrEqualTo(onePeso());
		} catch (CanNotConvertMeasureException ex) {
			anException = ex;
			assertTrue(ex.sourceMeasure().equals(tenMeters()));
			assertTrue(ex.targetUnit().equals(peso()));
		}
		if (anException == null) {
			fail();
		}
	}
	
	public void testInvalidMax() {
		Exception anException = null;
		try {
			tenMeters().max(onePeso());
		} catch (CanNotConvertMeasureException ex) {
			anException = ex;
			assertTrue(ex.sourceMeasure().equals(tenMeters()));
			assertTrue(ex.targetUnit().equals(peso()));
		}
		if (anException == null) {
			fail();
		}
		
		anException = null;
		try {
			tenMeters().max(new SmallInteger(1));
		} catch (CanNotConvertMeasureException ex) {
			anException = ex;
			assertTrue(ex.sourceMeasure().equals(tenMeters()));
			assertTrue(ex.targetUnit().equals(NullUnit.newInstance()));
		}
		if (anException == null) {
			fail();
		}
	}
	
	public void testInvalidMin() {
		Exception anException = null;
		try {
			tenMeters().max(onePeso());
		} catch (CanNotConvertMeasureException ex) {
			anException = ex;
			assertTrue(ex.sourceMeasure().equals(tenMeters()));
			assertTrue(ex.targetUnit().equals(peso()));
		}
		if (anException == null) {
			fail();
		}
		
		anException = null;
		try {
			tenMeters().max(new SmallInteger(1));
		} catch (CanNotConvertMeasureException ex) {
			anException = ex;
			assertTrue(ex.sourceMeasure().equals(tenMeters()));
			assertTrue(ex.targetUnit().equals(NullUnit.newInstance()));
		}
		if (anException == null) {
			fail();
		}
	}
	
	public void testInvalidNotInclusiveBetween() {
		Exception anException = null;
		try {
			tenMeters().notInclusiveBetween(oneMeter(), onePeso());
		} catch (CanNotConvertMeasureException ex) {
			anException = ex;
			assertTrue(ex.sourceMeasure().equals(tenMeters()));
			assertTrue(ex.targetUnit().equals(peso()));
		}
		if (anException == null) {
			fail();
		}
		anException = null;
		try {
			tenMeters().notInclusiveBetween(onePeso(), oneMeter());
		} catch (CanNotConvertMeasureException ex) {
			anException = ex;
			assertTrue(ex.sourceMeasure().equals(tenMeters()));
			assertTrue(ex.targetUnit().equals(peso()));
		}
		if (anException == null) {
			fail();
		}
		anException = null;
		try {
			tenMeters().notInclusiveBetween(new SmallInteger(1), new SmallInteger(2));
		} catch (CanNotConvertMeasureException ex) {
			anException = ex;
			assertTrue(ex.sourceMeasure().equals(tenMeters()));
			assertTrue(ex.targetUnit().equals(NullUnit.newInstance()));
		}
		if (anException == null) {
			fail();
		}
		
	}
	
	/**
	 * Test Converting
	 */
	
	public void testConvertNothing() {
		assertTrue(zeroMeters().convertTo(meter()).equals(zeroMeters()));
		assertTrue((new Measure(new SmallInteger(0),centimeter())).convertTo(meter()).equals(zeroMeters()));
		
	}
		
	public void testConvertTo() {
		assertTrue(oneMeter().convertTo(meter()).equals(oneMeter()));
		assertTrue(oneKilometer().convertTo(meter()).equals(new Measure(new SmallInteger(1000),meter())));
		assertTrue(oneCentimeter().convertTo(meter()).equals(new Measure(new Fraction(1,100),meter())));
		
		assertTrue(oneMeter().convertTo(kilometer()).equals(new Measure(new Fraction(1,1000),kilometer())));
		assertTrue(oneMeter().convertTo(centimeter()).equals(new Measure(new SmallInteger(100),centimeter())));
		
		assertTrue(oneCentimeter().convertTo(kilometer()).equals(new Measure(new Fraction(1,100000),kilometer())));
		assertTrue(oneKilometer().convertTo(centimeter()).equals(new Measure(new SmallInteger(100000),centimeter())));
	}
	
	public void testConvertToBaseUnit() {
		assertTrue(oneMeter().convertToBaseUnit().equals(oneMeter()));
		assertTrue(oneKilometer().convertToBaseUnit().equals(new Measure(new SmallInteger(1000),meter())));
	}
	
	public void testInvalidConvertTo() {
		Exception anException = null;
		try {
			oneMeter().convertTo(peso());
		} catch (CanNotConvertMeasureException ex) {
			anException = ex;
			assertTrue(ex.sourceMeasure().equals(oneMeter()));
			assertTrue(ex.targetUnit().equals(peso()));
		}
		if (anException == null) {
			fail();
		}
	}
	
	/**
	 * Test Multiplication
	 */

	public void testMultiplicationOfBaseUnit() {
		assertTrue(((Measure) tenMeters().multiplyBy(new SmallInteger(2))).amount().equals(new SmallInteger(20)));
		assertTrue(((Measure) tenMeters().multiplyBy(new SmallInteger(2))).unit().equals(meter()));
	}

	public void testMultiplicationOfDerivedUnit() {
		assertTrue(((Measure) oneKilometer().multiplyBy(new SmallInteger(2))).amount().equals(new SmallInteger(2)));
		assertTrue(((Measure) oneKilometer().multiplyBy(new SmallInteger(2))).unit().equals(kilometer()));
	}

	public void testMultiplyByDividedMeasure() {
		assertTrue(((Measure) onePeso().multiplyBy(tenPesos().divideBy(oneMeter()))).amount().equals(
				new SmallInteger(10)));
		assertTrue(((Measure) onePeso().multiplyBy(tenPesos().divideBy(oneMeter()))).unit().equals(
				peso().multiplyBy(peso().divideBy(meter()))));

		assertTrue(onePeso().multiplyBy(tenMeters().divideBy(onePeso())).equals(tenMeters()));
	}

	public void testMultiplyByMeasure() {
		assertTrue(((Measure) onePeso().multiplyBy(tenPesos())).amount().equals(new SmallInteger(10)));
		assertTrue(((Measure) onePeso().multiplyBy(tenPesos())).unit().equals(peso().multiplyBy(peso())));
	}

	public void testMultiplyByMeasureAssociativity() {
		assertTrue((onePeso().multiplyBy(tenPesos())).multiplyBy(tenPesos()).equals(
				onePeso().multiplyBy((tenPesos()).multiplyBy(tenPesos()))));
	}

	public void testMultiplyByMultipliedMeasure() {
		assertTrue(((Measure) onePeso().multiplyBy(tenPesos().multiplyBy(tenPesos()))).amount().equals(
				new SmallInteger(100)));
		assertTrue(((Measure) onePeso().multiplyBy(tenPesos().multiplyBy(tenPesos()))).unit().equals(
				peso().multiplyBy(peso().multiplyBy(peso()))));
	}

	public void testMultiplyByNumber() {
		assertTrue(onePeso().multiplyBy(new SmallInteger(10)).equals(tenPesos()));
		assertTrue(onePeso().multiplyBy(new SmallInteger(0)).equals(zeroPesos()));
		assertTrue(onePeso().multiplyBy(new SmallInteger(0)).equals(new SmallInteger(0)));
		assertTrue(onePeso().multiplyBy(new SmallInteger(0)).equals(zeroDollars()));
	}

	public void testMultiplyFloatByMeasure() {
		Measure aResultMeasure = (Measure) (NullUnit.newInstance().multiplyBy(peso())
				.with((ExtendedNumber) (new FloatNumber(1.01f)).multiplyBy(new SmallInteger(10))));
		assertTrue((new FloatNumber(1.01f)).multiplyBy(tenPesos()).equals(aResultMeasure));
	}

	public void testMultiplyFractionByMeasure() {
		Measure aResultMeasure = (Measure) (NullUnit.newInstance().multiplyBy(peso())
				.with((ExtendedNumber) (new Fraction(1, 3)).multiplyBy(new SmallInteger(10))));
		assertTrue((new Fraction(1, 3)).multiplyBy(tenPesos()).equals(aResultMeasure));
	}

	public void testMultiplySmallIntegerByMeasure() {
		Measure aResultMeasure = (Measure) (NullUnit.newInstance().multiplyBy(peso())
				.with((ExtendedNumber) (new SmallInteger(3)).multiplyBy(new SmallInteger(10))));
		assertTrue((new SmallInteger(3)).multiplyBy(tenPesos()).equals(aResultMeasure));
	}

	public void testMultiplyLargeIntegerByMeasure() {
		Measure aResultMeasure = (Measure) (NullUnit.newInstance().multiplyBy(peso())
				.with((ExtendedNumber) (new LargeInteger(3)).multiplyBy(new SmallInteger(10))));
		assertTrue((new LargeInteger(3)).multiplyBy(tenPesos()).equals(aResultMeasure));
	}

	/**
	 * Division Testing
	 */
	
	public void testDivideByDividedMeasure() {
		assertTrue(oneKilometer().divideBy(oneMeter().divideBy(onePeso())).equals(new Measure(new SmallInteger(1000),peso())));
		assertTrue(oneKilometer().divideBy(onePeso().divideBy(oneMeter())).equals(oneKilometer().multiplyBy(oneMeter()).divideBy(onePeso())));
	}
	
	public void testDivideByMeasureBaseUnitOverBaseUnit() {
		assertTrue(tenMeters().divideBy(tenMeters()).equals(new SmallInteger(1)));
		assertTrue(tenMeters().divideBy(tenMeters()).amount().equals(new SmallInteger(1)));
		assertTrue(tenMeters().divideBy(tenMeters()).unit().equals(NullUnit.newInstance()));
		assertTrue(tenMeters().divideBy(oneMeter()).equals(new SmallInteger(10)));
		assertTrue(tenMeters().divideBy(oneSecond()).equals(new Measure(new SmallInteger(10),(UnitBehavior)meter().divideBy(second()))));
		assertTrue(tenMeters().divideBy(twoSecond()).equals(new Measure(new SmallInteger(5),(UnitBehavior)meter().divideBy(second()))));
		assertTrue(tenMeters().divideBy(oneSecond()).numerator().equals(tenMeters()));
		assertTrue(tenMeters().divideBy(oneSecond()).denominator().equals(oneSecond()));
	}
	
	public void testDivideByMeasureBaseUnitOverDerivedUnit() {
		assertTrue(oneMeter().divideBy(oneKilometer()).equals(new Fraction(1,1000)));
		assertTrue(oneMeter().divideBy(oneCentimeter()).equals(new SmallInteger(100)));
		assertTrue(oneMeter().divideBy(oneMinute()).equals(new Measure(new Fraction(1,60),(UnitBehavior)meter().divideBy(second()))));
	}
	
	public void testDivideByMeasureDerivedUnitOverBaseUnit() {
		assertTrue(oneKilometer().divideBy(oneMeter()).equals(new SmallInteger(1000)));
		assertTrue(oneKilometer().divideBy(tenMeters()).equals(new SmallInteger(100)));
		assertTrue(oneKilometer().divideBy(oneKilometer()).equals(new SmallInteger(1)));
		assertTrue(oneKilometer().divideBy(oneSecond()).equals(new Measure(new SmallInteger(1000),(UnitBehavior)meter().divideBy(second()))));
	}
	
	public void testDivideByNumber() {
		assertTrue(zeroPesos().divideBy(new SmallInteger(1)).amount().equals(new SmallInteger(0)));
		assertTrue(tenPesos().divideBy(new SmallInteger(10)).equals(onePeso()));
	}
		
	public void testDivisionByZero() {
		Exception anException = null;
		try {
			tenPesos().divideBy(new SmallInteger(0));
		} catch (DivisionByZeroException ex) {
			anException = ex;
		}
		if (anException == null) {
			fail();
		}
	}
	
	public void testDivideFloatByMeasure() {
		assertTrue((new FloatNumber(1.01f)).divideBy(tenPesos()).equals(NullUnit.newInstance().divideBy(peso()).with(new Fraction(new FloatNumber(1.01),10))));
	}
	
	public void testDivideFractionByMeasure() {
		assertTrue((new Fraction(1,3)).divideBy(tenPesos()).equals(NullUnit.newInstance().divideBy(peso()).with(new Fraction(new Fraction(1,3),10))));
	}
	
	public void testDivideSmallIntegerByMeasure() {
		assertTrue((new SmallInteger(3)).divideBy(tenPesos()).equals(NullUnit.newInstance().divideBy(peso()).with(new Fraction(new SmallInteger(3),10))));
	}
	
	public void testDivideLargeIntegerByMeasure() {
		assertTrue((new LargeInteger(3)).divideBy(tenPesos()).equals(NullUnit.newInstance().divideBy(peso()).with(new Fraction(new LargeInteger(3),10))));
	}
	
	public void testDivisionOfBaseUnit() {
		assertTrue(tenMeters().divideBy(new SmallInteger(10)).amount().equals(new SmallInteger(1)));
		assertTrue(tenMeters().divideBy(new SmallInteger(10)).unit().equals(meter()));
	}
	
	public void testDivisionOfDerivedUnit() {
		assertTrue(oneKilometer().divideBy(new SmallInteger(10)).amount().equals(new Fraction(1,10)));
		assertTrue(oneKilometer().divideBy(new SmallInteger(10)).unit().equals(kilometer()));
	}
	
	public void testSimplificationsDivideByZero() {
		assertTrue((new SmallInteger(0)).divideBy(oneMeter()).equals(new SmallInteger(0)));
	}
	
	/**
	 * Test comparing
	 */
	
	public void testEquals() {
		assertTrue("Equal on same system",oneMeter().equals(thousandMillimeters()));
		assertTrue("Equal on same system",thousandMillimeters().equals(oneMeter()));
		
		assertTrue("Equals",tenPesos().equals(tenPesos()));
		assertFalse("Equals different amount",tenPesos().equals(twentyPesos()));
		
		assertFalse("Equals on the same type of unit but not equal measures",zeroCelsius().equals(zeroFahrenheit()));
		assertTrue(zeroCelsius().equals(new Measure(new SmallInteger(32),fahrenheit())));
	}
	
	public void testEqualDifferentBaseUnit() {
		assertFalse(tenPesos().equals(tenDollars()));
	}
	
	public void testEqualNothingWhenRepresentSameEntity() {
		assertTrue("Zeros of different units must be equivalent since they represent the same entities",zeroDollars().equals(zeroPesos()));
	}
	
	public void testEqualNumber() {
		assertFalse(oneMeter().equals(new SmallInteger(1)));
		assertFalse((new SmallInteger(1)).equals(oneMeter()));
	}
	
	public void testHash() {
		assertTrue(oneMeter().hashCode() == oneMeter().hashCode());
		assertTrue(oneMeter().hashCode() == thousandMillimeters().hashCode());
		
		assertFalse(tenPesos().hashCode() == tenDollars().hashCode());
	}
	
	public void testNotEquals() {
		assertFalse(oneMeter().equals("hello"));
		assertFalse("hello".equals(oneMeter()));
		
		assertFalse(oneMeter().equals(new Object()));
		assertFalse((new Object()).equals(oneMeter()));
	}
	
	/**
	 * Test subtract
	 */
	
	public void testNegated() {
		assertTrue(tenPesos().negated().negated().equals(tenPesos()));
		assertTrue(minusTenPesos().equals(tenPesos().negated()));
		assertTrue(zeroPesos().equals(zeroPesos()));
	}
	
	public void testSubtractAssociativity() {
		assertTrue(tenMeters().minus(oneMeter()).minus(oneMeter()).equals((tenMeters().minus(oneMeter())).minus(oneMeter())));
		assertTrue((tenMeters().minus(oneMeter())).minus(oneMeter()).equals(tenMeters().minus(oneMeter().plus(oneMeter()))));
	}
		
	public void testSubtractBaseUnit() {
		assertTrue(tenMeters().minus(oneMeter()).amount().equals(new SmallInteger(9)));
		assertTrue(tenMeters().minus(oneMeter()).unit().equals(meter()));
	}
	
	public void testSubtractDerivedUnit() {
		assertTrue(oneKilometer().minus(oneMeter()).amount().equals(new SmallInteger(999)));
		assertTrue(oneKilometer().minus(oneMeter()).unit().equals(meter()));
	}
	
	public void testSubtractDividedMeasure() {
		assertTrue(((MeasureBag)tenMeters().minus(tenMeters().divideBy(fivePesos()))).atSameBaseUnitAs(meter()).equals(tenMeters()));
		assertTrue(((MeasureBag)tenMeters().minus(tenMeters().divideBy(fivePesos()))).atSameBaseUnitAs((UnitBehavior)meter().divideBy(peso())).equals(tenMeters().negated().divideBy(fivePesos())));
		assertTrue(oneKilometer().minus(oneMeter()).unit().equals(meter()));
	}
	
	public void testSubtractFloatWithMeasure() {
		assertTrue(((MeasureBag)(new FloatNumber(10.01)).minus(tenMeters())).atSameBaseUnitAs(meter()).equals(tenMeters().negated()));
		assertTrue(((MeasureBag)(new FloatNumber(10.01)).minus(tenMeters())).atSameBaseUnitAs(NullUnit.newInstance()).equals(new Measure(new FloatNumber(10.01),NullUnit.newInstance())));
	}	
	
	public void testSubtractMeasureWithFloatNumber() {
		assertTrue(((MeasureBag)tenMeters().minus(new FloatNumber(10.01))).atSameBaseUnitAs(meter()).equals(tenMeters()));
		assertTrue(((MeasureBag)tenMeters().minus(new FloatNumber(10.01))).atSameBaseUnitAs(NullUnit.newInstance()).equals(new Measure(new FloatNumber(-10.01),NullUnit.newInstance())));
	}
	
	public void testSubtractFractionWithMeasure() {
		assertTrue(((MeasureBag)(new Fraction(1,2)).minus(tenMeters())).atSameBaseUnitAs(meter()).equals(tenMeters().negated()));
		assertTrue(((MeasureBag)(new Fraction(1,2)).minus(tenMeters())).atSameBaseUnitAs(NullUnit.newInstance()).equals(new Measure(new Fraction(1,2),NullUnit.newInstance())));
	}	
	
	public void testSubtractMeasureWithFraction() {
		assertTrue(((MeasureBag)tenMeters().minus(new Fraction(1,2))).atSameBaseUnitAs(meter()).equals(tenMeters()));
		assertTrue(((MeasureBag)tenMeters().minus(new Fraction(1,2))).atSameBaseUnitAs(NullUnit.newInstance()).equals(new Measure(new Fraction(-1,2),NullUnit.newInstance())));
	}
	
	public void testSubtractSmallIntegerWithMeasure() {
		assertTrue(((MeasureBag)(new SmallInteger(2)).minus(tenMeters())).atSameBaseUnitAs(meter()).equals(tenMeters().negated()));
		assertTrue(((MeasureBag)(new SmallInteger(2)).minus(tenMeters())).atSameBaseUnitAs(NullUnit.newInstance()).equals(new Measure(new SmallInteger(2),NullUnit.newInstance())));
	}	
	
	public void testSubtractMeasureWithSmallInteger() {
		assertTrue(((MeasureBag)tenMeters().minus(new SmallInteger(2))).atSameBaseUnitAs(meter()).equals(tenMeters()));
		assertTrue(((MeasureBag)tenMeters().minus(new SmallInteger(2))).atSameBaseUnitAs(NullUnit.newInstance()).equals(new Measure(new SmallInteger(-2),NullUnit.newInstance())));
	}	
		
	public void testSubtractLargeIntegerWithMeasure() {
		assertTrue(((MeasureBag)(new LargeInteger(2)).minus(tenMeters())).atSameBaseUnitAs(meter()).equals(tenMeters().negated()));
		assertTrue(((MeasureBag)(new LargeInteger(2)).minus(tenMeters())).atSameBaseUnitAs(NullUnit.newInstance()).equals(new Measure(new LargeInteger(2),NullUnit.newInstance())));
	}	
	
	public void testSubtractMeasureWithLargeInteger() {
		assertTrue(((MeasureBag)tenMeters().minus(new LargeInteger(2))).atSameBaseUnitAs(meter()).equals(tenMeters()));
		assertTrue(((MeasureBag)tenMeters().minus(new LargeInteger(2))).atSameBaseUnitAs(NullUnit.newInstance()).equals(new Measure(new LargeInteger(-2),NullUnit.newInstance())));
	}	
	
	public void testSubtractMultipliedMeasure() {
		assertTrue(((MeasureBag)tenMeters().minus(tenMeters().multiplyBy(fivePesos()))).atSameBaseUnitAs(meter()).equals(tenMeters()));
		assertTrue(((MeasureBag)tenMeters().minus(tenMeters().multiplyBy(fivePesos()))).atSameBaseUnitAs((UnitBehavior)meter().multiplyBy(peso())).equals(tenMeters().negated().multiplyBy(fivePesos())));
	}
	
	/**
	 * Test printing
	 */
	public void testPrintingForMany() {
		assertTrue(tenPesos().toString().equals("10 pesos"));
		assertTrue(tenDollars().toString().equals("10 dollars"));
		assertTrue(twentyDollars().toString().equals("20 dollars"));
	}
	
	public void testPrintingForOne() {
		assertTrue(onePeso().toString().equals("1 peso"));
		assertTrue(oneDollar().toString().equals("1 dollar"));
	}
	
	
	/**
	 * Test Testing
	 */
	
	public void testIsNothing() {
		assertTrue(zeroMeters().isNothing());
		assertTrue(zeroCentimeters().isNothing());
		
		assertTrue((new Measure(new SmallInteger(0),kelvin())).isNothing());
		assertTrue((new Measure(new Fraction(-5463,20),celsius())).isNothing());
		
		assertFalse((new Measure(new SmallInteger(1),kelvin())).isNothing());
	}
	
	public void testSameDomainAs() {
		assertTrue(oneMeter().sameDomainAs(oneMeter()));
		assertTrue(oneMeter().sameDomainAs(oneKilometer()));
		assertTrue(oneKilometer().sameDomainAs(oneMeter()));
		assertFalse(oneMeter().sameDomainAs(onePeso()));
		assertFalse(onePeso().sameDomainAs(oneMeter()));
	}
	
	public void testSameUnitAs() {
		assertTrue(onePeso().sameUnitAs(onePeso()));
		assertTrue(onePeso().sameUnitAs(tenPesos()));
		assertTrue(tenPesos().sameUnitAs(onePeso()));
		assertTrue(oneDollar().sameUnitAs(twentyDollars()));
		
		assertFalse(oneDollar().sameUnitAs(onePeso()));
		assertFalse(oneDollar().sameUnitAs(oneQuarter()));
		assertFalse(onePeso().sameUnitAs(new SmallInteger(1)));
	}
	
	/**
	 * Test truncated
	 */
	public void testTruncated() {
		assertTrue(meter().with(new FloatNumber(1.05)).truncated().equals(meter().with(new SmallInteger(1))));
		assertTrue(centimeter().with(new FloatNumber(1.05)).truncated().equals(centimeter().with(new SmallInteger(1))));
		assertTrue(peso().multiplyBy(meter()).with(new FloatNumber(1.05f)).truncated().equals(peso().multiplyBy(meter()).with(new SmallInteger(1))));
		assertTrue(peso().divideBy(meter()).with(new FloatNumber(1.05f)).truncated().equals(peso().divideBy(meter()).with(new SmallInteger(1))));
	}
	
}
