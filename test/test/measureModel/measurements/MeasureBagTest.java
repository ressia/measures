package test.measureModel.measurements;

import java.util.ArrayList;
import java.util.Collection;

import junit.framework.TestCase;
import test.measureModel.units.resources.UnitsTestResource;

import com.measureModel.arithmeticObjects.ArithmeticObject;
import com.measureModel.measurements.Measure;
import com.measureModel.measurements.MeasureBag;
import com.measureModel.measurements.NullMeasure;
import com.measureModel.measurements.exceptions.InvalidBinaryOperationException;
import com.measureModel.measurements.exceptions.InvalidUnaryOperationException;
import com.measureModel.measurements.exceptions.UndefinedUnitForMeasureBagException;
import com.measureModel.numbers.FloatNumber;
import com.measureModel.numbers.Fraction;
import com.measureModel.numbers.LargeInteger;
import com.measureModel.numbers.SmallInteger;
import com.measureModel.numbers.exceptions.DivisionByZeroException;
import com.measureModel.units.NullUnit;
import com.measureModel.units.UnitBehavior;

public class MeasureBagTest extends TestCase {

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
	
	public Measure twoDollars() {
		return new Measure(new SmallInteger(2), dollar());
	}
	
	public Measure zeroCentimeters() {
		return new Measure(new SmallInteger(0), centimeter());
	}
	
	public Measure twentyDollars() {
		return new Measure(new SmallInteger(20), dollar());
	}
	
	public Measure twentyEuros() {
		return new Measure(new SmallInteger(20), euro());
	}
	
	public Measure tenDollars() {
		return new Measure(new SmallInteger(10), dollar());
	}

	public Measure oneDollar() {
		return new Measure(new SmallInteger(1), dollar());
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
	
	public Measure fivePesos() {
		return new Measure(new SmallInteger(5), peso());
	}

	public Measure tenPesos() {
		return new Measure(new SmallInteger(10), peso());
	}
	
	public Measure twentyPesos() {
		return new Measure(new SmallInteger(20), peso());
	}
	
	public Measure minusTenPesos() {
		return new Measure(new SmallInteger(-10), peso());
	}
	
	public Measure minusTenDollars() {
		return new Measure(new SmallInteger(-10), dollar());
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
	
	public UnitBehavior cent() {
		return units.cent();
	}
	
	public UnitBehavior quarter() {
		return units.quarter();
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
	
	public Measure tenCents() {
		return new Measure(new SmallInteger(10), cent());
	} 
		
	public Measure oneQuarter() {
		return new Measure(new SmallInteger(1), quarter());
	}
	
	/**
	 * End Helpers
	 */

	/**
	 * Add Testing
	 */
	
	public void testAdd() {
		MeasureBag aMeasureBag = (MeasureBag)tenPesos().plus(twentyDollars());
		assertTrue(aMeasureBag.numberOfMeasures() == 2);
		assertTrue(aMeasureBag.atSameBaseUnitAs(peso()).equals(tenPesos()));
		assertTrue(aMeasureBag.atSameBaseUnitAs(dollar()).equals(twentyDollars()));
		
		aMeasureBag = (MeasureBag)tenPesos().plus(twentyDollars()).plus(tenEuros());
		assertTrue(aMeasureBag.numberOfMeasures() == 3);
		assertTrue(aMeasureBag.atSameBaseUnitAs(peso()).equals(tenPesos()));
		assertTrue(aMeasureBag.atSameBaseUnitAs(dollar()).equals(twentyDollars()));
		assertTrue(aMeasureBag.atSameBaseUnitAs(euro()).equals(tenEuros()));
	}

	public void testAddAssociativity() {
		assertTrue(((MeasureBag) tenPesos().plus(twentyDollars().plus(tenEuros()))).equals(
				((MeasureBag) tenPesos().plus(twentyDollars()).plus(tenEuros()))));
	}
	
	public void testAddBags() {
		MeasureBag aMeasureBag = (MeasureBag)tenPesos().plus(twentyDollars()).plus(tenEuros().plus(tenPesos()));
		assertTrue(aMeasureBag.numberOfMeasures() == 3);
		assertTrue(aMeasureBag.atSameBaseUnitAs(peso()).equals(twentyPesos()));
		assertTrue(aMeasureBag.atSameBaseUnitAs(dollar()).equals(twentyDollars()));
		assertTrue(aMeasureBag.atSameBaseUnitAs(euro()).equals(tenEuros()));
		
		aMeasureBag = (MeasureBag)tenPesos().plus(twentyDollars()).plus(tenEuros());
		assertTrue(aMeasureBag.numberOfMeasures() == 3);
		assertTrue(aMeasureBag.atSameBaseUnitAs(peso()).equals(tenPesos()));
		assertTrue(aMeasureBag.atSameBaseUnitAs(dollar()).equals(twentyDollars()));
		assertTrue(aMeasureBag.atSameBaseUnitAs(euro()).equals(tenEuros()));
	}
	
	public void testAddCommutative() {
		assertTrue(twentyDollars().plus(tenPesos()).equals(tenPesos().plus(twentyDollars())));
		assertTrue(tenPesos().plus(twentyDollars()).plus(tenEuros()).equals(tenPesos().plus(twentyDollars()).plus(tenEuros())));
		assertTrue(tenPesos().plus(twentyDollars()).plus(tenEuros()).equals(twentyDollars().plus(tenPesos()).plus(tenEuros())));
		assertTrue(tenPesos().plus(twentyDollars()).plus(tenEuros()).equals(twentyDollars().plus(tenEuros()).plus(tenPesos())));
	}
	
	public void testAddEquality() {
		assertTrue(tenPesos().plus(twentyDollars()).equals(tenPesos().plus(twentyDollars())));
		assertTrue(tenPesos().plus(twentyDollars()).plus(tenEuros()).equals(tenPesos().plus(twentyDollars()).plus(tenEuros())));
		assertFalse(tenPesos().plus(twentyDollars()).plus(tenEuros()).equals(tenPesos().plus(twentyDollars())));
	}
		
	public void testAddExistingUnit() {
		MeasureBag aMeasureBag = (MeasureBag)tenPesos().plus(twentyDollars());
		aMeasureBag = (MeasureBag)aMeasureBag.plus(tenPesos());
		
		assertTrue(aMeasureBag.numberOfMeasures() == 2);
		assertTrue(aMeasureBag.atSameBaseUnitAs(peso()).equals(twentyPesos()));
		assertTrue(aMeasureBag.atSameBaseUnitAs(dollar()).equals(twentyDollars()));
		
	}

	public void testAddFloatWithMeasure() {
		assertTrue(((MeasureBag)(new FloatNumber(10.01)).plus(tenMeters()).plus(tenPesos())).atSameBaseUnitAs(meter()).equals(tenMeters()));
		assertTrue(((MeasureBag)(new FloatNumber(10.01)).plus(tenMeters()).plus(tenPesos())).atSameBaseUnitAs(peso()).equals(tenPesos()));
		assertTrue(((MeasureBag)(new FloatNumber(10.01)).plus(tenMeters()).plus(tenPesos())).atSameBaseUnitAs(NullUnit.newInstance()).equals(new Measure(new FloatNumber(10.01),NullUnit.newInstance())));
	}
	
	public void testAddMeasureBagWithFloat() {
		assertTrue(((MeasureBag)tenMeters().plus(tenPesos()).plus((new FloatNumber(10.01)))).atSameBaseUnitAs(meter()).equals(tenMeters()));
		assertTrue(((MeasureBag)tenMeters().plus(tenPesos()).plus((new FloatNumber(10.01)))).atSameBaseUnitAs(peso()).equals(tenPesos()));
		assertTrue(((MeasureBag)tenMeters().plus(tenPesos()).plus((new FloatNumber(10.01)))).atSameBaseUnitAs(NullUnit.newInstance()).equals(new Measure(new FloatNumber(10.01),NullUnit.newInstance())));
	}
	
	public void testAddFractionWithMeasure() {
		assertTrue(((MeasureBag)(new Fraction(1,3)).plus(tenMeters()).plus(tenPesos())).atSameBaseUnitAs(meter()).equals(tenMeters()));
		assertTrue(((MeasureBag)(new Fraction(1,3)).plus(tenMeters()).plus(tenPesos())).atSameBaseUnitAs(peso()).equals(tenPesos()));
		assertTrue(((MeasureBag)(new Fraction(1,3)).plus(tenMeters()).plus(tenPesos())).atSameBaseUnitAs(NullUnit.newInstance()).equals(new Measure(new Fraction(1,3),NullUnit.newInstance())));
	}
	
	public void testMeasureBagWithFraction() {
		assertTrue(((MeasureBag)tenMeters().plus(tenPesos()).plus(new Fraction(1,3))).atSameBaseUnitAs(meter()).equals(tenMeters()));
		assertTrue(((MeasureBag)tenMeters().plus(tenPesos()).plus(new Fraction(1,3))).atSameBaseUnitAs(peso()).equals(tenPesos()));
		assertTrue(((MeasureBag)tenMeters().plus(tenPesos()).plus(new Fraction(1,3))).atSameBaseUnitAs(NullUnit.newInstance()).equals(new Measure(new Fraction(1,3),NullUnit.newInstance())));
	}
	
	public void testAddSmallIntegerWithMeasure() {
		assertTrue(((MeasureBag)(new SmallInteger(2)).plus(tenMeters()).plus(tenPesos())).atSameBaseUnitAs(meter()).equals(tenMeters()));
		assertTrue(((MeasureBag)(new SmallInteger(2)).plus(tenMeters()).plus(tenPesos())).atSameBaseUnitAs(peso()).equals(tenPesos()));
		assertTrue(((MeasureBag)(new SmallInteger(2)).plus(tenMeters()).plus(tenPesos())).atSameBaseUnitAs(NullUnit.newInstance()).equals(new Measure(new SmallInteger(2),NullUnit.newInstance())));
	}
	
	public void testAddMeasureBagWithSmallInteger() {
		assertTrue(((MeasureBag)tenMeters().plus(tenPesos()).plus(new SmallInteger(2))).atSameBaseUnitAs(meter()).equals(tenMeters()));
		assertTrue(((MeasureBag)tenMeters().plus(tenPesos()).plus(new SmallInteger(2))).atSameBaseUnitAs(peso()).equals(tenPesos()));
		assertTrue(((MeasureBag)tenMeters().plus(tenPesos()).plus(new SmallInteger(2))).atSameBaseUnitAs(NullUnit.newInstance()).equals(new Measure(new SmallInteger(2),NullUnit.newInstance())));
	}
	
	public void testAddLargeIntegerWithMeasure() {
		assertTrue(((MeasureBag)(new LargeInteger(2)).plus(tenMeters()).plus(tenPesos())).atSameBaseUnitAs(meter()).equals(tenMeters()));
		assertTrue(((MeasureBag)(new LargeInteger(2)).plus(tenMeters()).plus(tenPesos())).atSameBaseUnitAs(peso()).equals(tenPesos()));
		assertTrue(((MeasureBag)(new LargeInteger(2)).plus(tenMeters()).plus(tenPesos())).atSameBaseUnitAs(NullUnit.newInstance()).equals(new Measure(new LargeInteger(2),NullUnit.newInstance())));
	}
	
	public void testAddMeasureBagWithLargeInteger() {
		assertTrue(((MeasureBag)tenMeters().plus(tenPesos()).plus(new LargeInteger(2))).atSameBaseUnitAs(meter()).equals(tenMeters()));
		assertTrue(((MeasureBag)tenMeters().plus(tenPesos()).plus(new LargeInteger(2))).atSameBaseUnitAs(peso()).equals(tenPesos()));
		assertTrue(((MeasureBag)tenMeters().plus(tenPesos()).plus(new LargeInteger(2))).atSameBaseUnitAs(NullUnit.newInstance()).equals(new Measure(new LargeInteger(2),NullUnit.newInstance())));
	}
		
	public void testAddImmutability() {
		MeasureBag a10PesosPlus20Dollars = (MeasureBag)tenPesos().plus(twentyDollars()); 
		a10PesosPlus20Dollars = (MeasureBag)tenPesos().plus(twentyDollars());
		assertTrue(a10PesosPlus20Dollars.equals(tenPesos().plus(twentyDollars())));
	}
	
	public void testAddNothing() {
		assertTrue(zeroPesos().plus(twentyDollars()).equals(twentyDollars()));
		assertTrue(zeroPesos().plus(zeroDollars()).equals(zeroPesos()));
		assertTrue(zeroPesos().plus(zeroDollars()).equals(zeroDollars()));
		
	}
	
	/**
	 * Test Accessing
	 */

	public void testDenominator() {
		assertTrue(tenMeters().plus(twentyDollars()).denominator().equals(new SmallInteger(1)));
	}

	public void testNumerator() {
		assertTrue(tenMeters().plus(twentyDollars()).numerator().equals(tenMeters().plus(twentyDollars())));
	}

	public void testMeasures() {
		MeasureBag aMeasureBag = (MeasureBag)MeasureBag.withWith(tenPesos(), twentyDollars());
		assertTrue(aMeasureBag.measures().size() == 2);
		assertTrue(aMeasureBag.measures().contains(tenPesos()));
		assertTrue(aMeasureBag.measures().contains(twentyDollars()));
		assertFalse(aMeasureBag.measures().contains(new SmallInteger(0)));
	}
	
	public void testNumberOfMeasures() {
		MeasureBag aMeasureBag = (MeasureBag)MeasureBag.withWith(tenPesos(), twentyDollars());
		assertTrue(aMeasureBag.measures().size() == 2);
		assertTrue(aMeasureBag.numberOfMeasures() == aMeasureBag.measures().size());
	}
	
	public void testMeasuresImmutability() {
		MeasureBag aMeasureBag = (MeasureBag)MeasureBag.withWith(tenPesos(), twentyDollars());
		Collection measures = aMeasureBag.measures();
		assertTrue(aMeasureBag.measures().size() == 2);
		assertTrue(measures.size() == 2);
		measures.add(tenDollars());
		assertTrue(aMeasureBag.measures().size() == 2);
		assertTrue(measures.size() == 3);
		assertTrue(aMeasureBag.measures().contains(tenPesos()));
		assertTrue(aMeasureBag.measures().contains(twentyDollars()));
		assertFalse(aMeasureBag.measures().contains(new SmallInteger(0)));
	}
	
	public void testAtSameBaseUnitAsWithBaseUnits() {
		MeasureBag aMeasureBag = (MeasureBag)MeasureBag.withWith(tenPesos(), twentyDollars());
		assertTrue(aMeasureBag.atSameBaseUnitAs(peso()).equals(tenPesos()));
		assertTrue(aMeasureBag.atSameBaseUnitAs(dollar()).equals(twentyDollars()));
		assertTrue(aMeasureBag.atSameBaseUnitAs(euro()).equals(NullMeasure.newInstance()));
	}
	
	public void testAtSameBaseUnitAsWithDerivedUnits() {
		MeasureBag aMeasureBag = (MeasureBag)MeasureBag.withWith(tenCents(), oneQuarter());
		
		assertTrue(aMeasureBag.atSameBaseUnitAs(peso()).equals(tenCents()));
		assertTrue(aMeasureBag.atSameBaseUnitAs(cent()).equals(tenCents()));
		assertTrue(aMeasureBag.atSameBaseUnitAs(peso()).equals(tenCents().convertToBaseUnit()));
		assertTrue(aMeasureBag.atSameBaseUnitAs(cent()).equals(tenCents().convertToBaseUnit()));
		
		assertTrue(aMeasureBag.atSameBaseUnitAs(dollar()).equals(oneQuarter()));
		assertTrue(aMeasureBag.atSameBaseUnitAs(quarter()).equals(oneQuarter()));
		assertTrue(aMeasureBag.atSameBaseUnitAs(dollar()).equals(oneQuarter().convertToBaseUnit()));
		assertTrue(aMeasureBag.atSameBaseUnitAs(quarter()).equals(oneQuarter().convertToBaseUnit()));
	}
	
	/**
	 * Test
	 */
	public void testConvertAmountToBaseUnit() {
		Exception anException = null;
		try {
			((MeasureBag)tenPesos().plus(tenDollars())).convertAmountToBaseUnit();
		} catch (UndefinedUnitForMeasureBagException ex) {
			anException = ex;
			assertTrue(ex.measureBag().equals(tenPesos().plus(tenDollars())));
		}
		if (anException == null) {
			fail();
		}
	}
	
	
	/**
	 * Test Instance Creation
	 */
	
	public void testCreationWithMeasures() {
		ArrayList measures = new ArrayList();
		measures.add(tenPesos());
		measures.add(twentyDollars());
		measures.add(tenEuros());
		MeasureBag aMeasureBag = new MeasureBag(measures);
		
		assertTrue(aMeasureBag.numberOfMeasures() == 3);
		assertTrue(aMeasureBag.atSameBaseUnitAs(peso()).equals(tenPesos()));
		assertTrue(aMeasureBag.atSameBaseUnitAs(dollar()).equals(twentyDollars()));
		assertTrue(aMeasureBag.atSameBaseUnitAs(euro()).equals(tenEuros()));
	}
	
	/**
	 * Immutability
	 */
	public void testCreationWithMeasuresCopiesCollection() {
		/** 
		 * This test verifies that when bag is created using the private 
		 * message  #measures:,	the collection that goes as collaborating is copied"
		 */
		ArrayList measures = new ArrayList();
		measures.add(tenPesos());
		measures.add(twentyDollars());
		measures.add(tenEuros());
		MeasureBag aMeasureBag = new MeasureBag(measures);
		
		measures.add(oneMeter());
		assertTrue(aMeasureBag.numberOfMeasures() == 3);
		assertTrue(aMeasureBag.atSameBaseUnitAs(peso()).equals(tenPesos()));
		assertTrue(aMeasureBag.atSameBaseUnitAs(dollar()).equals(twentyDollars()));
		assertTrue(aMeasureBag.atSameBaseUnitAs(euro()).equals(tenEuros()));
	}
	
	public void testCreationWithWithBaseUnits() {
		MeasureBag aMeasureBag = (MeasureBag)MeasureBag.withWith(tenPesos(),twentyDollars());
		
		assertTrue(aMeasureBag.numberOfMeasures() == 2);
		assertTrue(aMeasureBag.atSameBaseUnitAs(peso()).equals(tenPesos()));
		assertTrue(aMeasureBag.atSameBaseUnitAs(dollar()).equals(twentyDollars()));
	}

	public void testCreationWithWithDerivedUnits() {
		MeasureBag aMeasureBag = (MeasureBag)MeasureBag.withWith(tenCents(),oneQuarter());
		
		assertTrue(aMeasureBag.numberOfMeasures() == 2);
		assertTrue(aMeasureBag.atSameBaseUnitAs(cent()).amount().equals(new Fraction(10,100)));
		assertTrue(aMeasureBag.atSameBaseUnitAs(peso()).amount().equals(new Fraction(10,100)));
		assertTrue(aMeasureBag.atSameBaseUnitAs(quarter()).amount().equals(new Fraction(1,4)));
		assertTrue(aMeasureBag.atSameBaseUnitAs(dollar()).amount().equals(new Fraction(1,4)));
	}
	
	/**
	 * Test Multiplication
	 */

	public void testMultiplication() {
		assertTrue(tenPesos().plus(tenDollars()).multiplyBy(new SmallInteger(2)).equals(twentyPesos().plus(twentyDollars())));
		assertTrue(tenPesos().plus(tenDollars()).plus(tenEuros()).multiplyBy(new SmallInteger(2)).equals(twentyPesos().plus(twentyDollars()).plus(twentyEuros())));
	}

	public void testMultiplyByMeasure() {
		assertTrue((tenPesos().plus(twentyDollars())).multiplyBy(tenPesos()).equals((tenPesos().multiplyBy(tenPesos())).plus(tenPesos().multiplyBy(twentyDollars()))));
	}

	public void testMultiplyByZero() {
		assertTrue((tenPesos().plus(twentyDollars())).multiplyBy(new SmallInteger(0)).isNothing());
		assertTrue((tenPesos().plus(twentyDollars())).multiplyBy(new SmallInteger(0)).plus(tenPesos()).equals(tenPesos()));
		assertTrue((tenPesos().plus(twentyDollars())).multiplyBy(new SmallInteger(0)).minus(tenPesos()).equals(tenPesos().negated()));
	}
	
	public void testMultiplyByCommutativity() {
		assertTrue((tenPesos().plus(twentyDollars()).multiplyBy(new Fraction(25,10))).equals((new Fraction(25,10)).multiplyBy(tenPesos().plus(twentyDollars()))));
		assertTrue((tenPesos().multiplyBy(tenPesos().plus(twentyDollars()))).equals(tenPesos().plus(twentyDollars()).multiplyBy(tenPesos())));
	}
	
	public void testMultiplyDistributivity() {
		assertTrue((tenPesos().plus(twentyDollars()).multiplyBy(new SmallInteger(2)).equals((tenPesos().multiplyBy(new SmallInteger(2))).plus(twentyDollars().multiplyBy(new SmallInteger(2))))));
		assertTrue((tenPesos().plus(twentyDollars()).multiplyBy(new SmallInteger(2)).equals((tenPesos().plus(twentyDollars())).plus(tenPesos().plus(twentyDollars())))));
		
		assertTrue((tenPesos().plus(twentyDollars())).plus(tenPesos().plus(twentyDollars())).multiplyBy(new SmallInteger(2)).equals((tenPesos().plus(twentyDollars()).multiplyBy(new SmallInteger(2))).plus(tenPesos().plus(twentyDollars()).multiplyBy(new SmallInteger(2)))));
		assertTrue((tenPesos().plus(tenDollars())).multiplyBy(tenPesos().plus(tenDollars())).equals((tenPesos().plus(tenDollars())).multiplyBy(tenPesos()).plus((tenPesos().plus(tenDollars())).multiplyBy(tenDollars()))));
	}
	
	public void testMultiplyFloatByMeasureBag() {
		assertTrue((new FloatNumber(10.01f).multiplyBy(tenPesos().plus(tenMeters()))).equals((tenPesos().multiplyBy(new FloatNumber(10.01f))).plus(tenMeters().multiplyBy(new FloatNumber(10.01f)))));
	}
	
	public void testMultiplyFractionByMeasureBag() {
		assertTrue((new Fraction(1,3).multiplyBy(tenPesos().plus(tenMeters()))).equals((tenPesos().multiplyBy(new Fraction(1,3))).plus(tenMeters().multiplyBy(new Fraction(1,3)))));
	}
	
	public void testMultiplySmallIntegerByMeasureBag() {
		assertTrue((new SmallInteger(3).multiplyBy(tenPesos().plus(tenMeters()))).equals((tenPesos().multiplyBy(new SmallInteger(3))).plus(tenMeters().multiplyBy(new SmallInteger(3)))));
	}
	
	public void testMultiplyLargeIntegerByMeasureBag() {
		assertTrue((new LargeInteger(3).multiplyBy(tenPesos().plus(tenMeters()))).equals((tenPesos().multiplyBy(new LargeInteger(3))).plus(tenMeters().multiplyBy(new LargeInteger(3)))));
	}
	
	public void testMultiplyMeasureByMeasureBag() {
		assertTrue(tenPesos().multiplyBy(tenPesos().plus(tenDollars())).equals((tenPesos().multiplyBy(tenPesos())).plus(tenPesos().multiplyBy(tenDollars()))));
	}

	/**
	 * Division Testing
	 */
	public void testDivideByMeasure() {
		assertTrue((tenPesos().plus(twentyDollars())).divideBy(tenPesos()).equals(twentyDollars().divideBy(tenPesos()).plus(new SmallInteger(1))));
	}
		
	public void testDivision() {
		assertTrue((tenPesos().plus(twentyDollars())).divideBy(new SmallInteger(10)).equals(onePeso().plus(twoDollars())));
	
		Exception anException = null;
		try {
			(tenPesos().plus(twentyDollars())).divideBy(new SmallInteger(0));
		} catch (DivisionByZeroException ex) {
			anException = ex;
		}
		if (anException == null) {
			fail();
		}
	}
	
	public void testDivisionDistributivity() {
		assertTrue((tenPesos().plus(twentyDollars()).divideBy(new SmallInteger(2)).equals((tenPesos().divideBy(new SmallInteger(2))).plus(twentyDollars().divideBy(new SmallInteger(2))))));
		assertTrue(tenPesos().plus(twentyDollars()).divideBy(new SmallInteger(2)).equals((fivePesos().plus(tenDollars()))));
		
		assertTrue((tenPesos().plus(twentyDollars())).plus(tenPesos().plus(twentyDollars())).divideBy(new SmallInteger(2)).equals((tenPesos().plus(twentyDollars()).divideBy(new SmallInteger(2))).plus(tenPesos().plus(twentyDollars()).divideBy(new SmallInteger(2)))));
		assertTrue((tenPesos().plus(tenDollars())).divideBy(tenPesos().plus(tenDollars())).equals((tenPesos().plus(tenDollars())).divideBy(tenPesos()).plus((tenPesos().plus(tenDollars())).divideBy(tenDollars()))));
	}
	
	public void testDivideFloatByMeasureBag() {
		assertTrue((new FloatNumber(10.01f).divideBy(tenPesos().plus(tenMeters()))).equals(((new FloatNumber(10.01f)).divideBy(tenPesos())).plus((new FloatNumber(10.01f)).divideBy(tenMeters()))));
	}
	
	public void testDivideFractionByMeasureBag() {
		assertTrue((new Fraction(1,3).divideBy(tenPesos().plus(tenMeters()))).equals(((new Fraction(1,3)).divideBy(tenPesos())).plus((new Fraction(1,3)).divideBy(tenMeters()))));
	}
	
	public void testDivideSmallIntegerByMeasureBag() {
		assertTrue((new SmallInteger(3).divideBy(tenPesos().plus(tenMeters()))).equals(((new SmallInteger(3)).divideBy(tenPesos())).plus((new SmallInteger(3)).divideBy(tenMeters()))));
	}
	
	public void testDivideLargeIntegerByMeasureBag() {
		assertTrue((new LargeInteger(3).divideBy(tenPesos().plus(tenMeters()))).equals(((new LargeInteger(3)).divideBy(tenPesos())).plus((new LargeInteger(3)).divideBy(tenMeters()))));
	}
	
	public void testDivideMeasureByMeasureBag() {
		/**
		 * TODO: this one fails due to the duality of measures and numbers not yet implemented in this model.
		 */
		assertTrue(tenPesos().divideBy(tenPesos().plus(tenDollars())).equals((tenPesos().divideBy(tenPesos())).plus(tenPesos().divideBy(tenDollars()))));
	}
	
	public void testZeroDivision() {
		assertTrue((zeroPesos().plus(zeroDollars())).divideBy(new SmallInteger(1)).equals(zeroPesos()));
		assertTrue((zeroPesos().plus(zeroDollars())).divideBy(new SmallInteger(1)).equals(zeroDollars()));
	}
	
	/**
	 * Test subtract
	 */
	
	public void testNegated() {
		assertTrue((minusTenPesos().plus(minusTenDollars())).equals(tenPesos().plus(tenDollars()).negated()));
	}
	
	public void testNegatedDistribution() {
		assertTrue((tenPesos().negated().plus(tenDollars().negated())).equals((tenPesos().plus(tenDollars())).negated()));
		assertTrue((tenPesos().plus(twentyDollars())).negated().negated().equals((tenPesos().plus(twentyDollars()))));
	}
	
	public void testNegatedZeroBag() {
		assertTrue((tenPesos().plus(tenDollars())).minus(tenPesos().plus(tenDollars())).negated().equals(zeroPesos()));
		assertTrue((tenPesos().plus(tenDollars())).minus(tenPesos().plus(tenDollars())).negated().equals(zeroDollars()));
	}
	
	public void testSubtract() {
		MeasureBag aMeasureBag = (MeasureBag)tenPesos().minus(twentyDollars());
		assertTrue(aMeasureBag.numberOfMeasures() == 2);
		assertTrue(aMeasureBag.atSameBaseUnitAs(peso()).equals(tenPesos()));
		assertTrue(aMeasureBag.atSameBaseUnitAs(dollar()).equals(twentyDollars().negated()));
			
		aMeasureBag = (MeasureBag)tenPesos().minus(twentyDollars()).minus(tenEuros());
		assertTrue(aMeasureBag.numberOfMeasures() == 3);
		assertTrue(aMeasureBag.atSameBaseUnitAs(peso()).equals(tenPesos()));
		assertTrue(aMeasureBag.atSameBaseUnitAs(dollar()).equals(twentyDollars().negated()));
		assertTrue(aMeasureBag.atSameBaseUnitAs(euro()).equals(tenEuros().negated()));
	}
	
	public void testSubtractBags() {
		MeasureBag aMeasureBag = (MeasureBag)(tenPesos().plus(twentyDollars())).minus(tenEuros().plus(tenPesos()));
		assertTrue(aMeasureBag.numberOfMeasures() == 2);
		assertTrue(aMeasureBag.atSameBaseUnitAs(peso()).equals(zeroPesos()));
		assertTrue(aMeasureBag.atSameBaseUnitAs(dollar()).equals(twentyDollars()));
		assertTrue(aMeasureBag.atSameBaseUnitAs(euro()).equals(tenEuros().negated()));
			
		aMeasureBag = (MeasureBag)(tenPesos().plus(twentyDollars())).minus(tenEuros());
		assertTrue(aMeasureBag.numberOfMeasures() == 3);
		assertTrue(aMeasureBag.atSameBaseUnitAs(peso()).equals(tenPesos()));
		assertTrue(aMeasureBag.atSameBaseUnitAs(dollar()).equals(twentyDollars()));
		assertTrue(aMeasureBag.atSameBaseUnitAs(euro()).equals(tenEuros().negated()));
	}
	
	public void testSubtractAssociativity() {
		assertTrue(tenMeters().minus(oneMeter()).minus(oneMeter()).equals((tenMeters().minus(oneMeter())).minus(oneMeter())));
		assertTrue((tenMeters().minus(oneMeter())).minus(oneMeter()).equals(tenMeters().minus(oneMeter().plus(oneMeter()))));
	}
	
	public void testSubtractEquality() {
		assertTrue(tenPesos().minus(twentyDollars()).equals(tenPesos().minus(twentyDollars())));
		assertTrue(tenPesos().minus(twentyDollars()).minus(tenEuros()).equals(tenPesos().minus(twentyDollars()).minus(tenEuros())));
	}
	
	public void testSubtractExistingUnit() {
		MeasureBag aMeasureBag = (MeasureBag)tenPesos().minus(twentyDollars());
		ArithmeticObject aMeasure = aMeasureBag.minus(tenPesos());
		assertTrue(aMeasure.equals(twentyDollars().negated()));
	}
	
	public void testSubtractFloatWithMeasure() {
		assertTrue(((MeasureBag)(new FloatNumber(10.01)).minus(tenMeters().plus(tenPesos()))).atSameBaseUnitAs(meter()).equals(tenMeters().negated()));
		assertTrue(((MeasureBag)(new FloatNumber(10.01)).minus(tenMeters().plus(tenPesos()))).atSameBaseUnitAs(peso()).equals(tenPesos().negated()));
		assertTrue(((MeasureBag)(new FloatNumber(10.01)).minus(tenMeters().plus(tenPesos()))).atSameBaseUnitAs(NullUnit.newInstance()).equals(new Measure(new FloatNumber(10.01),NullUnit.newInstance())));
	}
	
	public void testSubtractMeasureBagWithFloat() {
		assertTrue(((MeasureBag)tenMeters().plus(tenPesos()).minus(new FloatNumber(10.01))).atSameBaseUnitAs(meter()).equals(tenMeters()));
		assertTrue(((MeasureBag)tenMeters().plus(tenPesos()).minus(new FloatNumber(10.01))).atSameBaseUnitAs(peso()).equals(tenPesos()));
		assertTrue(((MeasureBag)tenMeters().plus(tenPesos()).minus(new FloatNumber(10.01))).atSameBaseUnitAs(NullUnit.newInstance()).equals(new Measure(new FloatNumber(-10.01),NullUnit.newInstance())));
	}
	
	public void testSubtractFractionWithMeasure() {
		assertTrue(((MeasureBag)(new Fraction(1,3)).minus(tenMeters().plus(tenPesos()))).atSameBaseUnitAs(meter()).equals(tenMeters().negated()));
		assertTrue(((MeasureBag)(new Fraction(1,3)).minus(tenMeters().plus(tenPesos()))).atSameBaseUnitAs(peso()).equals(tenPesos().negated()));
		assertTrue(((MeasureBag)(new Fraction(1,3)).minus(tenMeters().plus(tenPesos()))).atSameBaseUnitAs(NullUnit.newInstance()).equals(new Measure(new Fraction(1,3),NullUnit.newInstance())));
	}
	
	public void testSubtractMeasureBagWithFraction() {
		assertTrue(((MeasureBag)tenMeters().plus(tenPesos()).minus(new Fraction(1,3))).atSameBaseUnitAs(meter()).equals(tenMeters()));
		assertTrue(((MeasureBag)tenMeters().plus(tenPesos()).minus(new Fraction(1,3))).atSameBaseUnitAs(peso()).equals(tenPesos()));
		assertTrue(((MeasureBag)tenMeters().plus(tenPesos()).minus(new Fraction(1,3))).atSameBaseUnitAs(NullUnit.newInstance()).equals(new Measure(new Fraction(-1,3),NullUnit.newInstance())));
	}
	
	public void testSubtractSmallIntegerWithMeasure() {
		assertTrue(((MeasureBag)(new SmallInteger(2)).minus(tenMeters().plus(tenPesos()))).atSameBaseUnitAs(meter()).equals(tenMeters().negated()));
		assertTrue(((MeasureBag)(new SmallInteger(2)).minus(tenMeters().plus(tenPesos()))).atSameBaseUnitAs(peso()).equals(tenPesos().negated()));
		assertTrue(((MeasureBag)(new SmallInteger(2)).minus(tenMeters().plus(tenPesos()))).atSameBaseUnitAs(NullUnit.newInstance()).equals(new Measure(new SmallInteger(2),NullUnit.newInstance())));
	}
	
	public void testSubtractMeasureBagWithSmallInteger() {
		assertTrue(((MeasureBag)tenMeters().plus(tenPesos()).minus(new SmallInteger(2))).atSameBaseUnitAs(meter()).equals(tenMeters()));
		assertTrue(((MeasureBag)tenMeters().plus(tenPesos()).minus(new SmallInteger(2))).atSameBaseUnitAs(peso()).equals(tenPesos()));
		assertTrue(((MeasureBag)tenMeters().plus(tenPesos()).minus(new SmallInteger(2))).atSameBaseUnitAs(NullUnit.newInstance()).equals(new Measure(new SmallInteger(-2),NullUnit.newInstance())));
	}
	
	public void testSubtractLargeIntegerWithMeasure() {
		assertTrue(((MeasureBag)(new LargeInteger(2)).minus(tenMeters().plus(tenPesos()))).atSameBaseUnitAs(meter()).equals(tenMeters().negated()));
		assertTrue(((MeasureBag)(new LargeInteger(2)).minus(tenMeters().plus(tenPesos()))).atSameBaseUnitAs(peso()).equals(tenPesos().negated()));
		assertTrue(((MeasureBag)(new LargeInteger(2)).minus(tenMeters().plus(tenPesos()))).atSameBaseUnitAs(NullUnit.newInstance()).equals(new Measure(new LargeInteger(2),NullUnit.newInstance())));
	}
	
	public void testSubtractMeasureWithLargeInteger() {
		assertTrue(((MeasureBag)tenMeters().plus(tenPesos()).minus(new LargeInteger(2))).atSameBaseUnitAs(meter()).equals(tenMeters()));
		assertTrue(((MeasureBag)tenMeters().plus(tenPesos()).minus(new LargeInteger(2))).atSameBaseUnitAs(peso()).equals(tenPesos()));
		assertTrue(((MeasureBag)tenMeters().plus(tenPesos()).minus(new LargeInteger(2))).atSameBaseUnitAs(NullUnit.newInstance()).equals(new Measure(new LargeInteger(-2),NullUnit.newInstance())));
	}
	
	public void testSubtractImmutability() {
		MeasureBag aMeasureBag = (MeasureBag)(tenPesos().plus(twentyDollars())).plus(tenEuros()); 
		aMeasureBag.minus(twentyDollars());
		assertTrue(aMeasureBag.equals(tenPesos().plus(twentyDollars()).plus(tenEuros())));
	}
	
	public void testSubtractNothing() {
		assertTrue(zeroPesos().minus(twentyDollars()).equals(twentyDollars().negated()));
		assertTrue(zeroPesos().minus(zeroDollars()).equals(zeroPesos().negated()));
		assertTrue(zeroPesos().minus(zeroDollars()).equals(zeroDollars()));
	}
	
	/**
	 * Test truncated
	 */
	
	public void testTruncated() {
		assertTrue(peso().with(new FloatNumber(10.01f)).plus(dollar().with(new FloatNumber(20.5))).truncated().equals(tenPesos().plus(twentyDollars())));
	}
	
	public void testInvalidTruncated() {
		Exception anException = null;
		try {
			(new SmallInteger(3)).divideBy(tenPesos().plus(tenDollars())).truncated();
		} catch (InvalidUnaryOperationException ex) {
			anException = ex;
		}
		if (anException == null) {
			fail();
		}
	}
	
	
	/**
	 * Test Testing
	 */
	
	public void testIsNothing() {
		assertTrue((zeroPesos().plus(zeroDollars())).isNothing());
		assertTrue((tenPesos().plus(tenDollars())).minus(tenPesos().plus(tenDollars())).isNothing());
	}
	
	public void testIsMeasureBag() {
		assertTrue(peso().with(new FloatNumber(10.01f)).plus(dollar().with(new FloatNumber(20.5f))).isMeasureBag());
	}
	
	public void testSameUnitAs() {
		Exception anException = null;
		try {
			tenPesos().plus(tenDollars()).sameUnitAs(tenPesos().plus(tenDollars()));
		} catch (UndefinedUnitForMeasureBagException ex) {
			anException = ex;
		}
		if (anException == null) {
			fail();
		}
		
		anException = null;
		try {
			tenPesos().plus(tenDollars()).sameUnitAs(new SmallInteger(1));
		} catch (UndefinedUnitForMeasureBagException ex) {
			anException = ex;
		}
		if (anException == null) {
			fail();
		}
	}
	
	/**
	 * Test Magnitude protocol
	 */
	public void testLessThan() {
		Exception anException = null;
		try {
			tenPesos().plus(tenDollars()).lessThan(tenPesos().plus(tenDollars()));
		} catch (InvalidBinaryOperationException ex) {
			anException = ex;
			assertTrue(ex.firstFactor().equals(tenPesos().plus(tenDollars())));
			assertTrue(ex.secondFactor().equals(tenPesos().plus(tenDollars())));
		}
		if (anException == null) {
			fail();
		}
	}
	
	public void testGreaterThan() {
		Exception anException = null;
		try {
			tenPesos().plus(tenDollars()).greaterThan(tenPesos().plus(tenDollars()));
		} catch (InvalidBinaryOperationException ex) {
			anException = ex;
			assertTrue(ex.firstFactor().equals(tenPesos().plus(tenDollars())));
			assertTrue(ex.secondFactor().equals(tenPesos().plus(tenDollars())));
		}
		if (anException == null) {
			fail();
		}
	}
	
	public void testLessThanOrEqual() {
		Exception anException = null;
		try {
			tenPesos().plus(tenDollars()).lessThanOrEqualTo(tenPesos().plus(tenDollars()));
		} catch (InvalidBinaryOperationException ex) {
			anException = ex;
			assertTrue(ex.firstFactor().equals(tenPesos().plus(tenDollars())));
			assertTrue(ex.secondFactor().equals(tenPesos().plus(tenDollars())));
		}
		if (anException == null) {
			fail();
		}
	}
	
	public void testGreaterThanOrEqual() {
		Exception anException = null;
		try {
			tenPesos().plus(tenDollars()).greaterThanOrEqualTo(tenPesos().plus(tenDollars()));
		} catch (InvalidBinaryOperationException ex) {
			anException = ex;
			assertTrue(ex.firstFactor().equals(tenPesos().plus(tenDollars())));
			assertTrue(ex.secondFactor().equals(tenPesos().plus(tenDollars())));
		}
		if (anException == null) {
			fail();
		}
	}
	
	public void testLessThanMeasureInversion() {
		Exception anException = null;
		try {
			tenPesos().plus(tenDollars()).lessThan(tenPesos().plus(tenDollars()));
		} catch (InvalidBinaryOperationException ex) {
			anException = ex;
			assertTrue(ex.firstFactor().equals(tenPesos().plus(tenDollars())));
			assertTrue(ex.secondFactor().equals(tenPesos().plus(tenDollars())));
		}
		if (anException == null) {
			fail();
		}
	}
	
	public void testGreaterThanMeasureInversion() {
		Exception anException = null;
		try {
			tenPesos().greaterThan(tenPesos().plus(tenDollars()));
		} catch (InvalidBinaryOperationException ex) {
			anException = ex;
			assertTrue(ex.firstFactor().equals(tenPesos()));
			assertTrue(ex.secondFactor().equals(tenPesos().plus(tenDollars())));
		}
		if (anException == null) {
			fail();
		}
	}
	
	public void testLessThanOrEqualMeasureInversion() {
		Exception anException = null;
		try {
			tenPesos().lessThanOrEqualTo(tenPesos().plus(tenDollars()));
		} catch (InvalidBinaryOperationException ex) {
			anException = ex;
			assertTrue(ex.firstFactor().equals(tenPesos()));
			assertTrue(ex.secondFactor().equals(tenPesos().plus(tenDollars())));
		}
		if (anException == null) {
			fail();
		}
	}
	
	public void testGreaterThanOrEqualMeasureInversion() {
		Exception anException = null;
		try {
			tenPesos().greaterThanOrEqualTo(tenPesos().plus(tenDollars()));
		} catch (InvalidBinaryOperationException ex) {
			anException = ex;
			assertTrue(ex.firstFactor().equals(tenPesos()));
			assertTrue(ex.secondFactor().equals(tenPesos().plus(tenDollars())));
		}
		if (anException == null) {
			fail();
		}
	}
	
	public void testLessThanNumberInversion() {
		Exception anException = null;
		try {
			(new SmallInteger(1)).lessThan(tenPesos().plus(tenDollars()));
		} catch (InvalidBinaryOperationException ex) {
			anException = ex;
			assertTrue(ex.firstFactor().equals(new SmallInteger(1)));
			assertTrue(ex.secondFactor().equals(tenPesos().plus(tenDollars())));
		}
		if (anException == null) {
			fail();
		}
	}
	
	public void testGreaterThanNumberInversion() {
		Exception anException = null;
		try {
			(new SmallInteger(1)).greaterThan(tenPesos().plus(tenDollars()));
		} catch (InvalidBinaryOperationException ex) {
			anException = ex;
			assertTrue(ex.firstFactor().equals(new SmallInteger(1)));
			assertTrue(ex.secondFactor().equals(tenPesos().plus(tenDollars())));
		}
		if (anException == null) {
			fail();
		}
	}
	
	public void testLessThanOrEqualNumberInversion() {
		Exception anException = null;
		try {
			(new SmallInteger(1)).lessThanOrEqualTo(tenPesos().plus(tenDollars()));
		} catch (InvalidBinaryOperationException ex) {
			anException = ex;
			assertTrue(ex.firstFactor().equals(new SmallInteger(1)));
			assertTrue(ex.secondFactor().equals(tenPesos().plus(tenDollars())));
		}
		if (anException == null) {
			fail();
		}
	}
	
	public void testGreaterThanOrEqualNumberInversion() {
		Exception anException = null;
		try {
			(new SmallInteger(1)).greaterThanOrEqualTo(tenPesos().plus(tenDollars()));
		} catch (InvalidBinaryOperationException ex) {
			anException = ex;
			assertTrue(ex.firstFactor().equals(new SmallInteger(1)));
			assertTrue(ex.secondFactor().equals(tenPesos().plus(tenDollars())));
		}
		if (anException == null) {
			fail();
		}
	}
	
	/**
	 * Test printing
	 */
	public void testPrinting() {
		assertTrue(tenPesos().plus(tenDollars()).toString().equals("10 pesos + 10 dollars"));
		assertTrue(tenPesos().plus(oneDollar()).toString().equals("10 pesos + 1 dollar"));
	}
		
}

