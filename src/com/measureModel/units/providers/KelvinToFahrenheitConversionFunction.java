package com.measureModel.units.providers;

import com.measureModel.numbers.ExtendedNumber;
import com.measureModel.numbers.Fraction;
import com.measureModel.numbers.SmallInteger;
import com.measureModel.units.conversionFunctions.UnitConversionFunction;

public class KelvinToFahrenheitConversionFunction implements UnitConversionFunction {

	public ExtendedNumber value(ExtendedNumber kelvin) {
		return (ExtendedNumber) kelvin.minus(new SmallInteger(32)).multiplyBy(
				new Fraction(5, 9)).plus(new Fraction(27315, 100));
	}

}
