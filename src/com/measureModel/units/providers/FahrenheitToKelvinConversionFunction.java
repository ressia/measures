package com.measureModel.units.providers;

import com.measureModel.numbers.ExtendedNumber;
import com.measureModel.numbers.Fraction;
import com.measureModel.numbers.SmallInteger;
import com.measureModel.units.conversionFunctions.UnitConversionFunction;

public class FahrenheitToKelvinConversionFunction implements UnitConversionFunction {

	public ExtendedNumber value(ExtendedNumber fahrenheit) {
		return (ExtendedNumber) fahrenheit.minus(new Fraction(27315, 100)).multiplyBy(
				new Fraction(9, 5)).plus(new SmallInteger(32));
	}

}
