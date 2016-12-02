package com.measureModel.units.providers;

import com.measureModel.numbers.ExtendedNumber;
import com.measureModel.numbers.Fraction;
import com.measureModel.units.conversionFunctions.UnitConversionFunction;

public class CelsiusToKelvinConversionFunction implements UnitConversionFunction{

	public ExtendedNumber value(ExtendedNumber aNumber) {
		ExtendedNumber celsius = aNumber;
		return (ExtendedNumber)celsius.plus(new Fraction(5463,20));
	}

}
