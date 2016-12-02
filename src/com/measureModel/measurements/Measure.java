package com.measureModel.measurements;

import java.util.ArrayList;

import com.developmentExceptions.exceptions.ShouldImplementException;
import com.measureModel.arithmeticObjects.ArithmeticObject;
import com.measureModel.measurements.exceptions.CanNotConvertMeasureException;
import com.measureModel.numbers.ExtendedNumber;
import com.measureModel.units.NullUnit;
import com.measureModel.units.UnitBehavior;

public class Measure extends ComparableMeasure {
	
	private ExtendedNumber amount;
	
	private UnitBehavior unit;
	
	public Measure(ExtendedNumber anAmount, UnitBehavior aUnit) {
		amount = anAmount;
		unit = aUnit;
	}
	
	public ExtendedNumber amount() {
		return amount;
	}

	public UnitBehavior unit() {
		return unit;
	}
	
	public boolean equals(Object anObject) {
		if ( !(anObject instanceof ArithmeticObject) ) {
			return false;
		}
		return ((ArithmeticObject)anObject).equalsMeasure(this);
	}
	
	public int hashCode() {
		return convertAmountToBaseUnit().hashCode();
	}

	public ArithmeticObject denominator() {
		return unit().denominatorOf(amount());
	}

	public ArithmeticObject divideBy(ArithmeticObject anObject) {
		return anObject.divideByForMeasure(this);
	}

	public ArithmeticObject minus(ArithmeticObject anArithmeticObject) {
		return anArithmeticObject.negated().plusMeasure(this);
	}

	public ArithmeticObject multiplyBy(ArithmeticObject anObject) {
		return anObject.multiplyByForMeasure(this);
	}

	public ArithmeticObject multiplyByForUnit(UnitBehavior aUnitBehavior) {
		return aUnitBehavior.multiplyByForMeasure(this);
	}

	public ArithmeticObject numerator() {
		return unit().numeratorOf(amount());
	}

	public ArithmeticObject plus(ArithmeticObject anObject) {
		return anObject.plusMeasure(this);
	}

	public UnitBehavior baseUnit() {
		return unit().baseUnit();
	}

	public ArithmeticObject divideByForNumber(ExtendedNumber aNumber) {
		return new Measure((ExtendedNumber)aNumber.divideBy(amount()),unit().reciprocal());
		
	}

	public ArithmeticObject multiplyByForNumber(ExtendedNumber aNumber) {
		return new Measure((ExtendedNumber)amount().multiplyBy(aNumber),unit());
	}

	public ArithmeticObject abs() {
		return new Measure((ExtendedNumber)amount.abs(),unit);
	}

	public Measure convertToBaseUnit() {
		return convertTo(baseUnit());
	}

	public Measure convertTo(UnitBehavior aTargetUnit) {
		if (unit.sameDomainAs(aTargetUnit)) {
			return convertInSameDomainTo(aTargetUnit);
		} else {
			throw new CanNotConvertMeasureException(this,aTargetUnit);
		}
	}

	private Measure convertInSameDomainTo(UnitBehavior aTargetUnit) {
		return aTargetUnit.convertFromBaseUnit(unit.convertToBaseUnit(this));
	}

	public boolean isNothing() {
		return convertToBaseUnit().amount().equals(baseUnit().nothingAmount());
	}

	public ArithmeticObject multiplyByForMeasure(Measure aMeasure) {
		return aMeasure.multiplyMeasure(this);
	}

	public ArithmeticObject multiplyMeasure(Measure aMeasure) {
		Measure leftFactor = this.convertToBaseUnit();
		Measure rightFactor = aMeasure.convertToBaseUnit();
		return leftFactor.unit().multiplyBy(rightFactor.unit()).with((ExtendedNumber)leftFactor.amount().multiplyBy(rightFactor.amount()));
	}

	public ArithmeticObject divideByForMeasure(Measure aMeasure) {
		Measure numerator = aMeasure.convertToBaseUnit();
		Measure denominator = this.convertToBaseUnit();
		return numerator.unit().divideBy(denominator.unit()).with((ExtendedNumber)numerator.amount().divideBy(denominator.amount()));
	}

	public boolean equalsMeasure(Measure aMeasure) {
		/**
		 * If the unit is the same one, verifies only the amount. If not, if it is of the same domain
		 *  it returns the amount and it compares. otherwise, it returns false."
		 */
		
		if (unit.equals(aMeasure.unit())) {
			return amount.equals(aMeasure.amount());
		} else if ( sameDomainAs(aMeasure) ) {
			return convertAmountToBaseUnit().equals(aMeasure.convertAmountToBaseUnit());
		} else {
			return isNothing() && aMeasure.isNothing();
		}
		
	}

	public ArithmeticObject plusMeasure(Measure aMeasure) {
		/**
		 * I delegate the responsibility to MeasureBag since this it verifies if the measures are of 
		 * the same domain. When causing that bag has this responsibility, the code that checks this 
		 * this in a single place and is not created bag for measures of the same domain. 
		 */
		return MeasureBag.withWith(aMeasure,this);
	}

	public MeasureBehavior createBagWith(ComparableMeasure aMeasure) {
		if (isNothing()) {
			return aMeasure;
		} else {
			return aMeasure.createBagWithMeasure(this);
		}
	}

	public MeasureBehavior createBagWithMeasure(ComparableMeasure aMeasure) {
		if (isNothing()) {
			return aMeasure;
		} else {
			ArrayList measures = new ArrayList();
			measures.add(this);
			measures.add(aMeasure);
			return new MeasureBag(measures);
		}
	}

	public MeasureBehavior addSameDomainMeasure(ComparableMeasure aComparableMeasure) {
		if (sameUnitAs(aComparableMeasure)) {
			return new Measure((ExtendedNumber)amount().plus(aComparableMeasure.amount()),unit());
		} else {
			return new Measure((ExtendedNumber)convertAmountToBaseUnit().plus(aComparableMeasure.convertAmountToBaseUnit()),baseUnit());
		}
	}

	public ArithmeticObject plusMeasureBag(MeasureBag aMeasureBag) {
		return aMeasureBag.plusMeasure(this);
	}

	public boolean equalsMeasureBag(MeasureBag aMeasureBag) {
		return aMeasureBag.equalsMeasure(this);
	}

	public ArithmeticObject negated() {
		return new Measure((ExtendedNumber)amount.negated(),unit);
	}

	public ArithmeticObject plusForNumber(ExtendedNumber aNumber) {
		return MeasureBag.withWith(this, new Measure(aNumber,NullUnit.newInstance()));
	}

	public ArithmeticObject plusMeasure(ArithmeticObject aMeasure) {
		return MeasureBag.withWith(this, (ComparableMeasure)aMeasure);
	}

	public ArithmeticObject divideByNumber(ExtendedNumber anExtendedNumber) {
		return new Measure((ExtendedNumber)amount().divideBy(anExtendedNumber),unit());
	}

	public boolean greaterThanOrEqualTo(ArithmeticObject anArithmeticObject) {
		return this.greaterThan(anArithmeticObject) || this.equals(anArithmeticObject); 
	}

	@Override
	public boolean greaterThanOrEqualToForNumber(ExtendedNumber anExtendedNumber) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	public boolean greaterThan(ArithmeticObject anArithmeticObject) {
		return anArithmeticObject.greaterThanForMeasure(this);
	}

	@Override
	public boolean greaterThanForNumber(ExtendedNumber anExtendedNumber) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
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
		return anArithmeticObject.lessThanForMeasure(this);
	}

	@Override
	public boolean lessThanForNumber(ExtendedNumber anExtendedNumber) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	public boolean greaterThanForMeasure(Measure aMeasure) {
		if (unit.equals(aMeasure.unit())) {
			return aMeasure.amount().greaterThan(amount);
		} else {
			return (aMeasure.convertTo(baseUnit())).amount().greaterThan(convertAmountToBaseUnit());
		}
	}

	public boolean lessThanForMeasure(Measure aMeasure) {
		if (unit.equals(aMeasure.unit())) {
			return aMeasure.amount().lessThan(amount);
		} else {
			return (aMeasure.convertTo(baseUnit())).amount().lessThan(convertAmountToBaseUnit());
		}
	}

	public ArithmeticObject multiplyByForMeasureBag(MeasureBag aMeasureBag) {
		return aMeasureBag.multiplyByForMeasure(this);
	}
	
	public ArithmeticObject divideByForMeasureBag(MeasureBag aMeasureBag) {
		return aMeasureBag.divideByForMeasure(this); 
	}

	
	public String toString() {
		return amount.toString() + " " +  unit.nameFor(amount).toString(); 
	}

	public ArithmeticObject truncated() {
		return new Measure((ExtendedNumber)amount.truncated(),unit);
	}
	
}
