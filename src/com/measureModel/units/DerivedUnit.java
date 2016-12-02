package com.measureModel.units;

import com.measureModel.measurements.Measure;
import com.measureModel.numbers.ExtendedNumber;

public abstract class DerivedUnit extends SimpleUnit {

	public Measure convertToBaseUnit(Measure aMeasure) {
		return new Measure(convertAmountToBaseUnit(aMeasure.amount()),(UnitBehavior)baseUnit());
	}

	public Measure convertFromBaseUnit(Measure aMeasure) {
		return new Measure(convertAmountFromBaseUnit(aMeasure.amount()),this);
	}
	
	public ExtendedNumber nothingAmount() {
		return convertAmountFromBaseUnit(((UnitBehavior)baseUnit()).nothingAmount());
	}

	public abstract ExtendedNumber convertAmountFromBaseUnit(ExtendedNumber aNumber);


}
