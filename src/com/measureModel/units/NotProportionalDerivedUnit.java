package com.measureModel.units;

import com.developmentExceptions.exceptions.ShouldImplementException;
import com.measureModel.arithmeticObjects.ArithmeticObject;
import com.measureModel.measurements.Measure;
import com.measureModel.measurements.MeasureBag;
import com.measureModel.numbers.ExtendedNumber;
import com.measureModel.units.conversionFunctions.UnitConversionFunction;

public class NotProportionalDerivedUnit extends DerivedUnit {

	private String nameForOne;
	
	private String nameForMany;
	
	private String sign;
	
	private UnitBehavior baseUnit;
	
	private UnitConversionFunction conversionFunction;
	
	private UnitConversionFunction reciprocalConversionFunction;
	
	// Class Methods --------------------------------------
	public NotProportionalDerivedUnit(UnitBehavior aBaseUnit,
									  UnitConversionFunction aUnitConversionFunction,
									  UnitConversionFunction aReciprocalConversionFunction, 
									  String aName) {
		baseUnit = aBaseUnit;
		conversionFunction = aUnitConversionFunction;
		reciprocalConversionFunction = aReciprocalConversionFunction;
		nameForOne = aName;
		nameForMany = nameForManyFrom(aName);
		sign = defaultSign();
	}

	public NotProportionalDerivedUnit(UnitBehavior aBaseUnit,
			  UnitConversionFunction aUnitConversionFunction,
			  UnitConversionFunction aReciprocalConversionFunction, 
			  String aName,
			  String aNameForMany,
			  String aSign) {
		baseUnit = aBaseUnit;
		conversionFunction = aUnitConversionFunction;
		reciprocalConversionFunction = aReciprocalConversionFunction;
		nameForOne = aName;
		nameForMany = aNameForMany;
		sign = aSign;
	}
	
	//	Instance Methods -----------------------------------
	
	public UnitBehavior baseUnit() {
		return baseUnit;
	}

	public String nameForMany() {
		return nameForMany;
	}

	public String nameForOne() {
		return nameForOne; 
	}

	public ExtendedNumber convertAmountToBaseUnit(ExtendedNumber aNumber) {
		return conversionFunction.value(aNumber);
	}

	public ExtendedNumber convertAmountFromBaseUnit(ExtendedNumber aNumber) {
		return reciprocalConversionFunction.value(aNumber);
	}
	
	public Measure nullMeasure() {
		return new Measure(baseUnit().nothingAmount(),(UnitBehavior)baseUnit());
	}

	public String sign() {
		return sign;
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
