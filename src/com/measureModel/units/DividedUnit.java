package com.measureModel.units;

import java.util.ArrayList;
import java.util.Collection;

import com.developmentExceptions.exceptions.ShouldImplementException;
import com.measureModel.arithmeticObjects.ArithmeticObject;
import com.measureModel.measurements.Measure;
import com.measureModel.measurements.MeasureBag;
import com.measureModel.numbers.ExtendedNumber;

public class DividedUnit extends CompoundUnit {

	private ArithmeticObject numerator;
	
	private ArithmeticObject denominator;
	
	// Class Methods ----------------------------------------------------------
	private DividedUnit() {
	}
	
	public DividedUnit(ArithmeticObject aNumerator, ArithmeticObject aDenominator) {
		initializeNumeratorDenominator(aNumerator, aDenominator);
	}
	
	public DividedUnit(UnitBehavior aSimpleUnit, UnitBehavior anotherSimpleUnit) {
		initializeNumeratorDenominator(aSimpleUnit, anotherSimpleUnit);
	}

	public DividedUnit(NullUnit aNullUnit, UnitBehavior aSimpleUnit) {
		initializeNumeratorDenominator(aNullUnit, aSimpleUnit);
	}
	
	public static ArithmeticObject simpleUnitSimpleUnit(SimpleUnit aSimpleUnit, SimpleUnit anotherSimpleUnit) {
		if(aSimpleUnit.equals(anotherSimpleUnit)) {
			return NullUnit.newInstance();
		} else {
			return new DividedUnit(aSimpleUnit,anotherSimpleUnit);
		}
	}
	
	public static ArithmeticObject simpleUnitDividedUnit(SimpleUnit aSimpleUnit, CompoundUnit aDividedUnit) {
		ArithmeticObject aNumerator = aSimpleUnit.multiplyBy(aDividedUnit.denominator());
		ArithmeticObject aDenominator = aDividedUnit.numerator();
		return aNumerator.divideBy(aDenominator); 
	}
	
	public static ArithmeticObject simpleUnitMultipliedUnit(SimpleUnit aSimpleUnit, MultipliedUnit aMultipliedUnit) {
		Collection denominatorFactors = aMultipliedUnit.factors();
		if (denominatorFactors.contains(aSimpleUnit)) {
			denominatorFactors.remove(aSimpleUnit);
		} else {
			return (new DividedUnit()).initializeNumeratorDenominator(aSimpleUnit, aMultipliedUnit);
		}
		return (new DividedUnit()).initializeNumeratorDenominator(NullUnit.newInstance(), MultipliedUnit.factors(denominatorFactors));
	}


	public static ArithmeticObject multipliedUnitMultipliedUnit(MultipliedUnit aMultipliedUnit,MultipliedUnit anotherMultipliedUnit) {
		Collection numeratorFactors = aMultipliedUnit.factors();
		Collection denominatorFactors = new ArrayList();
		
		for( Object anObject : anotherMultipliedUnit.factors()) {
			UnitBehavior aFactor = (UnitBehavior)anObject;
			if (numeratorFactors.contains(aFactor)) {
				numeratorFactors.remove(aFactor);
			} else {
				denominatorFactors.add(aFactor);
			}
		}
		UnitBehavior aNumerator = MultipliedUnit.factors(numeratorFactors);
		UnitBehavior aDenominator = MultipliedUnit.factors(denominatorFactors);
		
		if (aDenominator.equals(NullUnit.newInstance())) {
			return aNumerator;
		} else {
			return (new DividedUnit()).initializeNumeratorDenominator(aNumerator,aDenominator);
		}
	}
	
	public static ArithmeticObject multipliedUnitSimpleUnit(MultipliedUnit aMultipliedUnit, SimpleUnit aSimpleUnit) {
		Collection numeratorsFactors = aMultipliedUnit.factors();
		if ( numeratorsFactors.contains(aSimpleUnit) ) {
			numeratorsFactors.remove(aSimpleUnit);
			return MultipliedUnit.factors(numeratorsFactors);
		} else {
			return (new DividedUnit()).initializeNumeratorDenominator(aMultipliedUnit,aSimpleUnit);
		}
	}

	public DividedUnit(SimpleUnit aSimpleUnit, CompoundUnit aDividedUnit) {
		numerator = aSimpleUnit.multiplyBy(aDividedUnit.denominator());
		denominator = aDividedUnit.numerator();
	}
	
	public DividedUnit(CompoundUnit aDividedUnit, CompoundUnit anotherDividedUnit) {
		numerator = aDividedUnit.numerator().multiplyBy(anotherDividedUnit.denominator());
		denominator = aDividedUnit.denominator().multiplyBy(anotherDividedUnit.numerator());
	}

	public static ArithmeticObject dividedUnitDividedUnit(CompoundUnit aDividedUnit, DividedUnit anotherDividedUnit) {
		ArithmeticObject numerator = aDividedUnit.numerator().multiplyBy(anotherDividedUnit.denominator); 
		ArithmeticObject denominator = aDividedUnit.denominator().multiplyBy(anotherDividedUnit.numerator);
		return numerator.divideBy(denominator);
	}
	
	public static ArithmeticObject dividedUnitSimpleUnit(CompoundUnit aDividedUnit, SimpleUnit aSimpleUnit) {
		return aDividedUnit.numerator().divideBy(aDividedUnit.denominator().multiplyBy(aSimpleUnit));
	}

	public static ArithmeticObject dividedUnitMultipliedUnit(CompoundUnit aDividedUnit, MultipliedUnit aMultipliedUnit) {
		return aDividedUnit.numerator().divideBy(aDividedUnit.denominator().multiplyBy(aMultipliedUnit));
	}
	
	public static ArithmeticObject nullUnitMultipliedUnit(NullUnit aNullUnit, MultipliedUnit aMultipliedUnit) {
		return (new DividedUnit()).initializeNumeratorDenominator(aNullUnit, aMultipliedUnit);
	}
	
	public static ArithmeticObject multipliedUnitDividedUnit(MultipliedUnit aMultipliedUnit, DividedUnit aDividedUnit) {
		return aMultipliedUnit.multiplyBy(aDividedUnit.denominator()).divideBy(aDividedUnit.numerator());
	}
	
	// Instance Methods ----------------------------------------------------------

	private ArithmeticObject initializeNumeratorDenominator(ArithmeticObject aNumerator, ArithmeticObject aDenominator) {
		numerator = aNumerator;
		denominator = aDenominator;
		return this;
	}
	
	public ArithmeticObject numerator() {
		return numerator;
	}
	
	public ArithmeticObject denominator() {
		return denominator;
	}
	
	public boolean equals(Object anObject) {
		ArithmeticObject aNumberOrUnit = null;
		try {
			aNumberOrUnit = (ArithmeticObject)anObject;
		} catch (ClassCastException anException) {
			return false;
		}
		return numerator().equals(aNumberOrUnit.numerator()) &&
				denominator().equals(aNumberOrUnit.denominator());
	}
	
	public int hashCode() {
		return numerator().hashCode() + denominator().hashCode();
	}

	public ArithmeticObject multiplyBy(ArithmeticObject aNumberOrUnit) {
		return aNumberOrUnit.multiplyByForUnit(this);
	}
	
	public ArithmeticObject multiplyByForUnit(UnitBehavior aUnitBehavior) {
		return aUnitBehavior.multiplyByDividedUnit(this);
	}
	
	public ArithmeticObject multiplyByDividedUnit(CompoundUnit aDividedUnit) {
		return MultipliedUnit.dividedUnitDividedUnit(this, aDividedUnit);
	}

	public ArithmeticObject divideByForUnit(UnitBehavior aUnit) {
		return aUnit.divideByDividedUnit(this);
	}

	public ArithmeticObject divideByDividedUnit(DividedUnit aDividedUnit) {
		return DividedUnit.dividedUnitDividedUnit(this,aDividedUnit);
	}

	public UnitBehavior baseUnit() {
		return this;
	}

	public String sign() {
		return ((UnitBehavior)numerator).sign() + "/" + ((UnitBehavior)denominator).sign();
	}
	
	public String nameForMany() {
		return ((UnitBehavior)numerator).nameForMany() + "/" + ((UnitBehavior)denominator).nameForMany();
	}

	public String nameForOne() {
		return ((UnitBehavior)numerator).nameForOne() + "/" + ((UnitBehavior)denominator).nameForOne();
	}

	public ArithmeticObject divideByMultipliedUnit(MultipliedUnit aMultipliedUnit) {
		return DividedUnit.dividedUnitMultipliedUnit(this, aMultipliedUnit);
	}

	public ArithmeticObject divideBySimpleUnit(SimpleUnit aSimpleUnit) {
		return DividedUnit.dividedUnitSimpleUnit(this,aSimpleUnit);
	}

	public ArithmeticObject multiplyByForMultipliedUnit(MultipliedUnit aMultipliedUnit) {
		return aMultipliedUnit.multiplyByDividedUnit(this);
	}

	public ArithmeticObject multiplyBySimpleUnit(SimpleUnit aSimpleUnit) {
		return MultipliedUnit.dividedUnitSimpleUnit(this, aSimpleUnit);
	}

	public ArithmeticObject divideByForNumber(ExtendedNumber aNumber) {
		return aNumber.multiplyBy(this.reciprocal());
	}

	public ArithmeticObject multiplyByForNumber(ExtendedNumber aNumber) {
		return this.multiplyBy(aNumber);
	}

	@Override
	public ArithmeticObject abs() {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	@Override
	public ArithmeticObject amount() {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	@Override
	public ArithmeticObject convertToBaseUnit() {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	@Override
	public ArithmeticObject divideByForMeasureBag(MeasureBag measureBag) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	@Override
	public boolean equalsMeasure(Measure measure) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	@Override
	public boolean greaterThan(ArithmeticObject anArithmeticObject) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	@Override
	public boolean greaterThanForMeasure(Measure measure) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	@Override
	public boolean greaterThanForNumber(ExtendedNumber anExtendedNumber) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	@Override
	public boolean greaterThanOrEqualTo(ArithmeticObject anArithmeticObject) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	@Override
	public boolean greaterThanOrEqualToForNumber(ExtendedNumber anExtendedNumber) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	@Override
	public boolean isNegative() {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	@Override
	public boolean isNothing() {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	@Override
	public boolean isPositive() {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	@Override
	public boolean lessThan(ArithmeticObject anArithmeticObject) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	@Override
	public boolean lessThanForMeasure(Measure measure) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	@Override
	public boolean lessThanForNumber(ExtendedNumber anExtendedNumber) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	@Override
	public ArithmeticObject multiplyByForMeasureBag(MeasureBag measureBag) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	@Override
	public ArithmeticObject plusForNumber(ExtendedNumber number) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	@Override
	public ArithmeticObject plusMeasure(ArithmeticObject measure) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	@Override
	public ArithmeticObject plusMeasureBag(MeasureBag measureBag) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	@Override
	public ArithmeticObject truncated() {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	@Override
	public UnitBehavior unit() {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}



}
