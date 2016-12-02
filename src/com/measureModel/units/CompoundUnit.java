package com.measureModel.units;

import com.developmentExceptions.exceptions.*;
import com.measureModel.arithmeticObjects.ArithmeticObject;
import com.measureModel.measurements.Measure;
import com.measureModel.numbers.SmallInteger;
import com.measureModel.numbers.ExtendedNumber;

public abstract class CompoundUnit extends UnitBehavior {
	
	public ExtendedNumber nothingAmount() {
		return new SmallInteger(0);
	}

	public ExtendedNumber convertAmountToBaseUnit(ExtendedNumber aNumber) {
		return aNumber;
	}

	public Measure convertToBaseUnit(Measure aMeasure) {
		return aMeasure;
	}
	
	public Measure convertFromBaseUnit(Measure aMeasure) {
		return aMeasure;
	}

	public ArithmeticObject with(ExtendedNumber aNumber) {
		return new Measure(aNumber,this);
	}
}
