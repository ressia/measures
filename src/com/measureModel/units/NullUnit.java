package com.measureModel.units;

import com.developmentExceptions.exceptions.ShouldImplementException;
import com.measureModel.arithmeticObjects.ArithmeticObject;
import com.measureModel.arithmeticObjects.ArithmeticObject;
import com.measureModel.measurements.Measure;
import com.measureModel.measurements.MeasureBag;
import com.measureModel.numbers.ExtendedNumber;
import com.measureModel.numbers.SmallInteger;

public class NullUnit extends UnitBehavior {
	
	private static NullUnit uniqueInstance = new NullUnit();
	
	public static NullUnit newInstance() {
		return uniqueInstance;
	}
	
	public ArithmeticObject divideBy(ArithmeticObject aNumberOrUnit) {
		return aNumberOrUnit.divideByForUnit(this);
	}

	public ArithmeticObject multiplySimpleUnit(UnitBehavior unit) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArithmeticObject divideNullUnit(NullUnit aNullUnit) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArithmeticObject multiplyNullUnit(NullUnit aNullUnit) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArithmeticObject divideBySimpleUnit(SimpleUnit aSimpleUnit) {
		return new DividedUnit(this,aSimpleUnit);
	}

	public ArithmeticObject divideByForUnit(UnitBehavior aUnit) {
		return aUnit;
	}

	@Override
	public ArithmeticObject multiplyByDividedUnit(CompoundUnit aDividedUnit) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArithmeticObject multiplyBy(ArithmeticObject anObject) {
		return anObject;
	}

	public ArithmeticObject multiplyByForUnit(UnitBehavior aUnit) {
		return aUnit;
	}

	public ArithmeticObject with(ExtendedNumber aNumber) {
		return aNumber;
	}

	public UnitBehavior baseUnit() {
		return this;
	}

	public ExtendedNumber convertAmountToBaseUnit(ExtendedNumber aNumber) {
		return new SmallInteger(0);
	}

	public Measure convertToBaseUnit(Measure aMeasure) {
		return aMeasure;
	}

	public ArithmeticObject divideByDividedUnit(DividedUnit aDividedUnit) {
		return aDividedUnit.reciprocal();
	}

	public ArithmeticObject divideByMultipliedUnit(MultipliedUnit aMultipliedUnit) {
		return DividedUnit.nullUnitMultipliedUnit(this,aMultipliedUnit);
	}

	@Override
	public ArithmeticObject multiplyByForMultipliedUnit(MultipliedUnit aMultipliedUnit) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	public ArithmeticObject multiplyBySimpleUnit(SimpleUnit aSimpleUnit) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	public String nameForMany() {
		return "";
	}

	public String nameForOne() {
		return "";
	}

	public ExtendedNumber nothingAmount() {
		return new SmallInteger(0);
	}

	public String sign() {
		return NullUnit.defaultSign();
	}

	public ArithmeticObject divideByForNumber(ExtendedNumber aNumber) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	public ArithmeticObject multiplyByForNumber(ExtendedNumber aNumber) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	public ArithmeticObject minusForNumber(ExtendedNumber aNumber) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	public ArithmeticObject plusForNumber(ExtendedNumber aNumber) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	public Measure convertFromBaseUnit(Measure aMeasure) {
		return aMeasure;
	}

	public ArithmeticObject abs() {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	public ArithmeticObject divideByForMeasure(Measure measure) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	public boolean equalsMeasure(Measure measure) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	public boolean isNothing() {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	public ArithmeticObject minusForMeasureBag(MeasureBag measureBag) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	public ArithmeticObject multiplyByForMeasure(Measure measure) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	public ArithmeticObject negated() {
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

	public boolean sameUnitAs(ArithmeticObject anArithmeticObject) {
		// TODO Auto-generated method stub
		throw new ShouldImplementException();
		
	}

	public UnitBehavior unit() {
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
