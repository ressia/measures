package com.measureModel.measurements;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import com.developmentExceptions.exceptions.ShouldImplementException;
import com.measureModel.arithmeticObjects.ArithmeticObject;
import com.measureModel.measurements.exceptions.InvalidBinaryOperationException;
import com.measureModel.measurements.exceptions.UndefinedAmountForMeasureBagException;
import com.measureModel.measurements.exceptions.UndefinedUnitForMeasureBagException;
import com.measureModel.numbers.ExtendedNumber;
import com.measureModel.numbers.SmallInteger;
import com.measureModel.numbers.exceptions.DivisionByZeroException;
import com.measureModel.units.BaseUnit;
import com.measureModel.units.NullUnit;
import com.measureModel.units.UnitBehavior;


public class MeasureBag extends MeasureBehavior {

	private Collection measures;
	
	/**
	 * Class Methods 
	 */
	
	public static MeasureBehavior withWith(ComparableMeasure aMeasure, ComparableMeasure anotherMeasure) {
		/**
		 * This message is not due to never use. A MeasureBag is created sending the messages #+ or #-.
		 * If the two measures are of the same domain, directly it gives back a canonized measure to the 
		 * base unit. If they are of different domains, adds the measures canonized.
		 * This comparison is here and not in Measure so that they are not possible to be created incorrect 
		 * bag.
		 * 
		 * DEFINITION: A MeasureBag always has its measures canonized. 
		 */
		
		if ( aMeasure.sameDomainAs(anotherMeasure)) {
			return aMeasure.addSameDomainMeasure(anotherMeasure);
		} else {
			return aMeasure.createBagWith(anotherMeasure);
		}
	}

	
	
	public MeasureBag(Collection mesuresColletion) {
		ArrayList newMeasures = new ArrayList();
		int size = mesuresColletion.size();
		Iterator anIterator = mesuresColletion.iterator();
		for(int i=0;i<size;i++) {
			ArithmeticObject aMeasure = (ArithmeticObject)anIterator.next();
			newMeasures.add(aMeasure.convertToBaseUnit());
		}
		measures = newMeasures;
	}

	/**
	 * Instance Methods
	 */
	
	public ExtendedNumber amount() {
		throw new UndefinedAmountForMeasureBagException(this);
	}
	
	public UnitBehavior unit() {
		throw new UndefinedUnitForMeasureBagException(this);
	}

	public ArithmeticObject abs() {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	public UnitBehavior baseUnit() {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	public ArithmeticObject denominator() {
		return new SmallInteger(1);
	}

	public ArithmeticObject divideBy(ArithmeticObject anArithmeticObject) {
		return anArithmeticObject.divideByForMeasureBag(this);
	}

	public ArithmeticObject divideByForMeasure(Measure aMeasure) {
		if (aMeasure.isNothing()) {
			return aMeasure;
		}
		ArrayList newMeasures = new ArrayList();
		int size = measures.size();
		Iterator anIterator = measures.iterator();
		for(int i=0;i<size;i++) {
			Measure aMeasureInBag = (Measure)anIterator.next();
			newMeasures.add(aMeasureInBag.divideBy(aMeasure));
		}
		return new MeasureBag(newMeasures);
	}

	public ArithmeticObject divideByForNumber(ExtendedNumber anExtendedNumber) {
		if (anExtendedNumber.isNothing()) {
			return anExtendedNumber;
		}
		ArrayList newMeasures = new ArrayList();
		int size = measures.size();
		Iterator anIterator = measures.iterator();
		for(int i=0;i<size;i++) {
			Measure aMeasure = (Measure)anIterator.next();
			newMeasures.add(anExtendedNumber.divideBy(aMeasure));
		}
		return new MeasureBag(newMeasures);
	}

	public ArithmeticObject divideByForUnit(UnitBehavior unitBehavior) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	/**
	 * TODO: REFACTOR
	 */
	public boolean equalsMeasure(Measure aMeasure) {
		int size = measures.size();
		Iterator anIterator = measures.iterator();
		for(int i=0;i<size;i++) {
			Measure aMeasureInBag = (Measure)anIterator.next();
			if (aMeasureInBag.sameDomainAs(aMeasure)) {
				if (!aMeasureInBag.equals(aMeasure)) {
					return false;
				}
			} else {
				if (!aMeasure.isNothing()) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean isNothing() {
		int size = measures.size();
		Iterator anIterator = measures.iterator();
		for(int i=0;i<size;i++) {
			Measure aMeasure = (Measure)anIterator.next();
			if (!aMeasure.isNothing()) {
				return false;
			}
		}
		return true;
	}



	public ArithmeticObject minusForNumber(ExtendedNumber number) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	public ArithmeticObject multiplyBy(ArithmeticObject anArithmeticObject) {
		return anArithmeticObject.multiplyByForMeasureBag(this);
	}

	/**
	 * TODO: is the same behavior as in multiplyByForNumber see if we can get numbers to behaive as measuress
	 */
	public ArithmeticObject multiplyByForMeasure(Measure aMeasure) {
		if (aMeasure.isNothing()) {
			return aMeasure;
		}
		ArrayList newMeasures = new ArrayList();
		int size = measures.size();
		Iterator anIterator = measures.iterator();
		for(int i=0;i<size;i++) {
			Measure aMeasureInBag = (Measure)anIterator.next();
			newMeasures.add(aMeasureInBag.multiplyBy(aMeasure));
		}
		return new MeasureBag(newMeasures);
	}

	public ArithmeticObject multiplyByForNumber(ExtendedNumber aNumber) {
		if (aNumber.isNothing()) {
			return aNumber;
		}
		ArrayList newMeasures = new ArrayList();
		int size = measures.size();
		Iterator anIterator = measures.iterator();
		for(int i=0;i<size;i++) {
			Measure aMeasure = (Measure)anIterator.next();
			newMeasures.add(aMeasure.multiplyBy(aNumber));
		}
		return new MeasureBag(newMeasures);
	}

	public ArithmeticObject multiplyByForUnit(UnitBehavior unitBehavior) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	public ArithmeticObject numerator() {
		return this;
	}

	public ArithmeticObject plus(ArithmeticObject anArithmeticObject) {
		return anArithmeticObject.plusMeasureBag(this);
	}
	
	public ArithmeticObject plusForNumber(ExtendedNumber aExtendedNumber) {
		return plusMeasure(new Measure(aExtendedNumber,NullUnit.newInstance()));
	}

	public ArithmeticObject plusMeasure(ArithmeticObject aMeasure) {
		ComparableMeasure aMeasureToAdd = (ComparableMeasure)aMeasure;
		MeasureBehavior sameBaseUnitMeasure = atSameBaseUnitAs(aMeasureToAdd.unit());
		Collection newMeasures = null;
		if (sameBaseUnitMeasure.isNothing()) {
			newMeasures = measures;
		} else {
			newMeasures = measuresWithDifferentBaseUnitTo(sameBaseUnitMeasure);
		}
		newMeasures.add(sameBaseUnitMeasure.plus(aMeasureToAdd));
		Collection nonNullMeasures = nonNothingMeasures(newMeasures);
		if (nonNullMeasures.size() == 1) {
			return (Measure)nonNullMeasures.iterator().next();
		} else {
			return new MeasureBag(nonNullMeasures);
		}
	}

	private Collection nonNothingMeasures(Collection measuresCollection) {
		ArrayList answer = new ArrayList();
		int size = measuresCollection.size();
		Iterator anIterator = measuresCollection.iterator();
		for(int i=0;i<size;i++) {
			ArithmeticObject aMeasure = (ArithmeticObject)anIterator.next();
			if(!aMeasure.isNothing()) {
				answer.add(aMeasure);
			}
		}
		return answer;
	}
	
	private Collection nonNothingMeasures() {
		return nonNothingMeasures(measures);
	}

	private Collection measuresWithDifferentBaseUnitTo(MeasureBehavior sameBaseUnitMeasure) {
		ArrayList answer = new ArrayList();
		int size = measures.size();
		Iterator anIterator = measures.iterator();
		for(int i=0;i<size;i++) {
			Measure aMeasure = (Measure)anIterator.next();
			if(!aMeasure.baseUnit().equals(sameBaseUnitMeasure.baseUnit())) {
				answer.add(aMeasure);
			}
		}
		return answer;
	}

	public MeasureBehavior atSameBaseUnitAs(UnitBehavior aUnit) {
		return atBaseUnit(aUnit.baseUnit());
	}

	private MeasureBehavior atBaseUnit(UnitBehavior aBaseUnit) {
		int size = measures.size();
		Iterator anIterator = measures.iterator();
		for(int i=0;i<size;i++) {
			Measure aMeasure = (Measure)anIterator.next();
			if(aMeasure.baseUnit().equals(aBaseUnit)) {
				return aMeasure;
			}
		}
		return NullMeasure.newInstance();
	}



	public ArithmeticObject reciprocal() {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	public ArithmeticObject with(ExtendedNumber number) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	public int numberOfMeasures() {
		return measures.size();
	}
	
	
	public boolean equals(Object anObject) {
		return anObject instanceof MeasureBehavior && 
				((MeasureBehavior) anObject).equalsMeasureBag(this);
	}

	public boolean equalsMeasureBag(MeasureBag aMeasureBag) {
		return this.minus(aMeasureBag).isNothing();
	}

	/**
	 * TODO: Refactor!!!!!!!!!!!!!!!!
	 */
	public ArithmeticObject plusMeasureBag(MeasureBag aMeasureBag) {
		HashMap measuresByUnit = new HashMap();
		int size = measures.size();
		Iterator anIterator = measures.iterator();
		for(int i=0;i<size;i++) {
			ArithmeticObject aMeasure = (ArithmeticObject)anIterator.next();
			measuresByUnit.put(aMeasure.baseUnit(),aMeasure);
		}
		
		size = aMeasureBag.measures().size();
		anIterator = aMeasureBag.measures().iterator();
		for(int i=0;i<size;i++) {
			ArithmeticObject aMeasure = (ArithmeticObject)anIterator.next();
			ArithmeticObject aMeasureToAdd = null;
			aMeasureToAdd = (ArithmeticObject)measuresByUnit.get(aMeasure.baseUnit());
			if ( aMeasureToAdd == null ) {
				aMeasureToAdd = NullMeasure.newInstance();
			}
			measuresByUnit.put(aMeasure.baseUnit(), aMeasureToAdd.plus(aMeasure));
		}
		
		Collection nonNullMeasures = nonNothingMeasures(measuresByUnit.values());
		if (nonNullMeasures.isEmpty()) {
			return NullMeasure.newInstance();
		} else {
			if (nonNullMeasures.size() == 1) {
				return (ArithmeticObject)nonNullMeasures.iterator().next();
			} else {
				return new MeasureBag(nonNullMeasures);
			}
		}
	}

	public ArithmeticObject negated() {
		ArrayList newMeasures = new ArrayList();
		int size = measures.size();
		Iterator anIterator = measures.iterator();
		for(int i=0;i<size;i++) {
			ArithmeticObject aMeasure = (ArithmeticObject)anIterator.next();
			newMeasures.add(aMeasure.negated());
		}
		return new MeasureBag(newMeasures);
	}

	public Collection measures() {
		return new ArrayList(measures);
	}

	public boolean greaterThan(ArithmeticObject anArithmeticObject) {
		throw new InvalidBinaryOperationException(this,anArithmeticObject);
	}

	public boolean greaterThanForMeasure(Measure aMeasure) {
		throw new InvalidBinaryOperationException(aMeasure,this);
	}

	public boolean greaterThanForNumber(ExtendedNumber anExtendedNumber) {
		throw new InvalidBinaryOperationException(anExtendedNumber,this);
	}

	public boolean greaterThanOrEqualTo(ArithmeticObject anArithmeticObject) {
		throw new InvalidBinaryOperationException(this,anArithmeticObject);
	}

	public boolean greaterThanOrEqualToForNumber(ExtendedNumber anExtendedNumber) {
		throw new InvalidBinaryOperationException(anExtendedNumber,this);
	}

	@Override
	public boolean isNegative() {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}



	@Override
	public boolean isPositive() {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	public boolean lessThan(ArithmeticObject anArithmeticObject) {
		throw new InvalidBinaryOperationException(this,anArithmeticObject);
	}

	public boolean lessThanForMeasure(Measure aMeasure) {
		throw new InvalidBinaryOperationException(aMeasure,this);
	}

	public boolean lessThanForNumber(ExtendedNumber anExtendedNumber) {
		throw new InvalidBinaryOperationException(anExtendedNumber,this);
	}

	public boolean lessThanOrEqualTo(ArithmeticObject anArithmeticObject) {
		throw new InvalidBinaryOperationException(this,anArithmeticObject);
	}

	public ArithmeticObject multiplyByForMeasureBag(MeasureBag aMeasureBag) {
		ArithmeticObject newMeasureBag = NullMeasure.newInstance();
		int size = measures.size();
		Iterator anIterator = measures.iterator();
		for(int i=0;i<size;i++) {
			Measure aMeasure = (Measure)anIterator.next();
			newMeasureBag = newMeasureBag.plus(aMeasureBag.multiplyByForMeasure(aMeasure));
		}
		return newMeasureBag;
	}

	public ArithmeticObject divideByNumber(ExtendedNumber anExtendedNumber) {
		if (anExtendedNumber.isNothing()) {
			throw new DivisionByZeroException();
		}
		ArrayList newMeasures = new ArrayList();
		int size = measures.size();
		Iterator anIterator = measures.iterator();
		for(int i=0;i<size;i++) {
			Measure aMeasure = (Measure)anIterator.next();
			newMeasures.add(aMeasure.divideBy(anExtendedNumber));
		}
		return new MeasureBag(newMeasures);
	}
	
	public ArithmeticObject divideByForMeasureBag(MeasureBag aMeasureBag) {
		ArithmeticObject newMeasureBag = NullMeasure.newInstance();
		int size = measures.size();
		Iterator anIterator = measures.iterator();
		for(int i=0;i<size;i++) {
			Measure aMeasure = (Measure)anIterator.next();
			newMeasureBag = newMeasureBag.plus(aMeasureBag.divideByForMeasure(aMeasure));
		}
		return newMeasureBag;
	}
	
	public boolean isMeasureBag() {
		return true;
	}
	
	public ExtendedNumber convertAmountToBaseUnit() {
		return unit().convertAmountToBaseUnit(amount());
	}



	@Override
	public ArithmeticObject convertToBaseUnit() {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}



	public ArithmeticObject truncated() {
		ArrayList newMeasures = new ArrayList();
		int size = measures.size();
		Iterator anIterator = measures.iterator();
		for(int i=0;i<size;i++) {
			ArithmeticObject aMeasure = (ArithmeticObject)anIterator.next();
			newMeasures.add(aMeasure.truncated());
		}
		return new MeasureBag(newMeasures);
	}
	
	public String toString() {
		StringBuffer aBuffer = new StringBuffer();
		int size = measures.size();
		Iterator anIterator = measures.iterator();
		for(int i=0;i<size;i++) {
			ArithmeticObject aMeasure = (ArithmeticObject)anIterator.next();
			aBuffer.append(aMeasure.toString());
			aBuffer.append(" + ");
		}
		return aBuffer.toString().substring(0, aBuffer.length() - 3); 
	}
	
}
