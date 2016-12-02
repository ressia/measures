package com.measureModel.measurements;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.developmentExceptions.exceptions.ShouldImplementException;
import com.measureModel.arithmeticObjects.ArithmeticObject;
import com.measureModel.measurements.exceptions.CanNotConvertMeasureException;
import com.measureModel.numbers.ExtendedNumber;
import com.measureModel.numbers.SmallInteger;
import com.measureModel.units.NullUnit;
import com.measureModel.units.UnitBehavior;

public class NullMeasure extends ComparableMeasure {
	
	private static NullMeasure uniqueInstance = new NullMeasure();
	
	public static NullMeasure newInstance() {
		return uniqueInstance;
	}
	
	public ExtendedNumber amount() {
		return new SmallInteger(0);
	}

	public UnitBehavior unit() {
		return NullUnit.newInstance();
	}

	public ArithmeticObject abs() {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	public UnitBehavior baseUnit() {
		return NullUnit.newInstance();
	}

	public ArithmeticObject denominator() {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	public ArithmeticObject divideBy(ArithmeticObject anObject) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	public ArithmeticObject divideByForMeasure(Measure measure) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	public ArithmeticObject divideByForNumber(ExtendedNumber number) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	public ArithmeticObject divideByForUnit(UnitBehavior unitBehavior) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	public boolean equalsMeasure(Measure measure) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	public boolean isNothing() {
		return true;
	}

	public ArithmeticObject minus(ArithmeticObject anObject) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	public ArithmeticObject minusForNumber(ExtendedNumber number) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	public ArithmeticObject multiplyBy(ArithmeticObject anObject) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	public ArithmeticObject multiplyByForMeasure(Measure measure) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	public ArithmeticObject multiplyByForNumber(ExtendedNumber number) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	public ArithmeticObject multiplyByForUnit(UnitBehavior unitBehavior) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	public ArithmeticObject numerator() {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	public ArithmeticObject plus(ArithmeticObject anObject) {
		return anObject;
	}

	public ArithmeticObject plusForNumber(ExtendedNumber number) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	public ArithmeticObject plusMeasure(Measure measure) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	public ArithmeticObject plusMeasureBag(MeasureBag measureBag) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	public ArithmeticObject reciprocal() {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	public boolean sameUnitAs(ArithmeticObject anArithmeticObject) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	public ArithmeticObject with(ExtendedNumber number) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}
	
	public ArithmeticObject negated() {
		return this;
	}

	public boolean equals(Object anObject) {
		if ( !(anObject instanceof ArithmeticObject) ) {
			return false;
		}
		return ((ArithmeticObject)anObject).isNothing();
	}

	@Override
	public MeasureBehavior addSameDomainMeasure(ComparableMeasure measure) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	@Override
	public MeasureBehavior createBagWith(ComparableMeasure measure) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	@Override
	public MeasureBehavior createBagWithMeasure(ComparableMeasure comparableMeasure) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	@Override
	public ArithmeticObject convertToBaseUnit() {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	@Override
	public boolean equalsMeasureBag(MeasureBag measureBag) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	@Override
	public ArithmeticObject divideByForMeasureBag(MeasureBag measureBag) {
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
	public boolean lessThanOrEqualTo(ArithmeticObject anArithmeticObject) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	@Override
	public ArithmeticObject multiplyByForMeasureBag(MeasureBag measureBag) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	@Override
	public ArithmeticObject plusMeasure(ArithmeticObject measure) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	@Override
	public ArithmeticObject truncated() {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}
		
}
