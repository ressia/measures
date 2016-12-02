package com.measureModel.units;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.developmentExceptions.exceptions.ShouldImplementException;
import com.measureModel.arithmeticObjects.ArithmeticObject;
import com.measureModel.measurements.Measure;
import com.measureModel.measurements.MeasureBag;
import com.measureModel.numbers.ExtendedNumber;

public class MultipliedUnit extends CompoundUnit {

	// Instance Variables -------------------------------
	
	private Collection factors;
	
	// Class methods ---------------------------------------------------------------
	private MultipliedUnit() {
	}
	
	public static ArithmeticObject simpleUnitSimpleUnit(UnitBehavior aSimpleUnit, UnitBehavior anotherSimpleUnit) {
		Collection aCollection = new ArrayList();
		aCollection.add(aSimpleUnit);
		aCollection.add(anotherSimpleUnit);
		return (new MultipliedUnit()).intilializeFactors(aCollection);
	}
	
	public static UnitBehavior factors(Collection aCollection) {
		if (aCollection.isEmpty() ) {return NullUnit.newInstance();}
		if (aCollection.size() == 1 ) {return (UnitBehavior)aCollection.iterator().next();}
		return new MultipliedUnit().intilializeFactors(aCollection);
	}
	
	public static ArithmeticObject dividedUnitDividedUnit(CompoundUnit aDividedUnit,CompoundUnit anotherDividedUnit) {
		ArithmeticObject aNumerator = aDividedUnit.numerator().multiplyBy(anotherDividedUnit.numerator());
		ArithmeticObject aDenominator = aDividedUnit.denominator().multiplyBy(anotherDividedUnit.denominator());
		return aNumerator.divideBy(aDenominator);
	}
	
	public static ArithmeticObject dividedUnitSimpleUnit(CompoundUnit aDividedUnit,SimpleUnit aSimpleUnit) {
		return simpleUnitDividedUnit(aSimpleUnit, aDividedUnit);
	}
	
	public static ArithmeticObject simpleUnitDividedUnit(SimpleUnit aSimpleUnit, CompoundUnit aDividedUnit) {
		return aSimpleUnit.multiplyBy(aDividedUnit.numerator()).divideBy(aDividedUnit.denominator());
	}

	public static ArithmeticObject simpleUnitMultipliedUnit(SimpleUnit aSimpleUnit,MultipliedUnit aMultipliedUnit) {
		Collection updatedFactors = new ArrayList(aMultipliedUnit.factors());
		updatedFactors.add(aSimpleUnit);
		return MultipliedUnit.factors(updatedFactors);
	}

	public static ArithmeticObject multipliedUnitDividedUnit(MultipliedUnit aMultipliedUnit, CompoundUnit aDividedUnit) {
		return aMultipliedUnit.multiplyBy(aDividedUnit.numerator()).divideBy(aDividedUnit.denominator());
	}

	public static ArithmeticObject multipliedUnitMultipliedUnit(MultipliedUnit aMultipliedUnit, MultipliedUnit anotherMultipliedUnit) {
		Collection aCollection = aMultipliedUnit.factors();
		aCollection.addAll(anotherMultipliedUnit.factors());
		return (new MultipliedUnit()).intilializeFactors(aCollection);
	}
	
	// Instance Methods ------------------------------------------------------------

	private UnitBehavior intilializeFactors(Collection aCollection) {
		factors = aCollection;
		return this;
	}
	
	public ArithmeticObject divideByForUnit(UnitBehavior aUnit) {
		return aUnit.divideByMultipliedUnit(this);
	}

	public ArithmeticObject multiplyByForUnit(UnitBehavior aUnit) {
		return aUnit.multiplyByForMultipliedUnit(this);
	}
	
	public ArithmeticObject divideByMultipliedUnit(MultipliedUnit aMultipliedUnit) {
		return DividedUnit.multipliedUnitMultipliedUnit(this,aMultipliedUnit);
	}

	public ArithmeticObject divideBySimpleUnit(SimpleUnit aSimpleUnit) {
		return DividedUnit.multipliedUnitSimpleUnit(this, aSimpleUnit);
	}

	public ArithmeticObject multiplyBySimpleUnit(SimpleUnit aSimpleUnit) {
		return MultipliedUnit.simpleUnitMultipliedUnit(aSimpleUnit,this);
	}

	public Collection factors() {
		return new ArrayList(factors);
	}
	
	public int factorsSize() {
		return factors.size();
	}
	
	public boolean equals(Object anObject) {
		return (this.getClass() == anObject.getClass()) &&
				this.hasSamefactorsAs((MultipliedUnit)anObject);	
	}
	
	private boolean hasSamefactorsAs(MultipliedUnit aMultipliedUnit) {
		Collection factorsIntersection = aMultipliedUnit.factors();
		int size = factors.size();
		Iterator anIterator = factors.iterator();
		for(int i=0;i<size;i++) {
			UnitBehavior aFactor = (UnitBehavior)anIterator.next();
			factorsIntersection.remove(aFactor);
		}
		return factorsIntersection.isEmpty();
	}

	public int hashCode() {
		int hashNumber = 0;
		for(Object aObject : factors) {
			UnitBehavior aFactor = (UnitBehavior)aObject;
			hashNumber += aFactor.hashCode();
		}
		return hashNumber;
	}

	public UnitBehavior baseUnit() {
		return this;
	}

	public String nameForMany() {
		StringBuffer answer = new StringBuffer();
		for (Object anObject : factors) {
			UnitBehavior aFactor = (UnitBehavior)anObject;
			answer.append(aFactor.nameForMany());
			answer.append(".");
		}
		return answer.substring(0,answer.length()-1);
	}

	public String nameForOne() {
		StringBuffer answer = new StringBuffer();
		for (Object anObject : factors) {
			UnitBehavior aFactor = (UnitBehavior)anObject;
			answer.append(aFactor.nameForOne());
			answer.append(".");
		}
		return answer.substring(0,answer.length()-1);
	}

	public ArithmeticObject divideByDividedUnit(DividedUnit aDividedUnit) {
		return DividedUnit.multipliedUnitDividedUnit(this,aDividedUnit);
	}

	public ArithmeticObject multiplyByDividedUnit(CompoundUnit aDividedUnit) {
		return MultipliedUnit.multipliedUnitDividedUnit(this,aDividedUnit);
	}

	public ArithmeticObject multiplyByForMultipliedUnit(MultipliedUnit aMultipliedUnit) {
		return MultipliedUnit.multipliedUnitMultipliedUnit(this,aMultipliedUnit);
	}

	public String sign() {
		StringBuffer answer = new StringBuffer();
		for (Object anObject : factors) {
			UnitBehavior aFactor = (UnitBehavior)anObject;
			answer.append(aFactor.sign());
			answer.append(".");
		}
		return answer.substring(0,answer.length()-1);
	}

	public ArithmeticObject divideByForNumber(ExtendedNumber aNumber) {
		return DividedUnit.nullUnitMultipliedUnit((NullUnit)aNumber.unit(), this).with(aNumber);
	}

	public ArithmeticObject multiplyBy(ArithmeticObject anObject) {
		return anObject.multiplyByForUnit(this);
	}

	public ArithmeticObject multiplyByForNumber(ExtendedNumber aNumber) {
		return this.with(aNumber);
	}
	
	public String toString() {
		StringBuffer answer = new StringBuffer();
		for (Object anObject : factors) {
			UnitBehavior aFactor = (UnitBehavior)anObject;
			answer.append(aFactor.toString());
			answer.append(".");
		}
		return answer.substring(0,answer.length()-1);
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
