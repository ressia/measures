package com.measureModel.arithmeticObjects;

import com.measureModel.measurements.Measure;
import com.measureModel.measurements.MeasureBag;
import com.measureModel.numbers.ExtendedNumber;
import com.measureModel.numbers.SmallInteger;
import com.measureModel.units.UnitBehavior;

/**
 * This class models an Arithmetic Object
 * +,-,*,/,=
 * @author jressia
 *
 */
public abstract class ArithmeticObject implements Magnitude {

	// arithmetic specific behavior ----------------
	public abstract ArithmeticObject plus(ArithmeticObject anArithmeticObject);
	
	public ArithmeticObject minus(ArithmeticObject anArithmeticObject) {
		return this.plus(anArithmeticObject.negated()); 
	}
	
	public ArithmeticObject times(ArithmeticObject anArithmeticObject) {
		return this.multiplyBy(anArithmeticObject);
	}
	
	public abstract ArithmeticObject multiplyBy(ArithmeticObject anArithmeticObject);
	
	public abstract ArithmeticObject divideBy(ArithmeticObject anArithmeticObject);
	
	// ----------------------------------------------
	
	// magnitude protocol --------------------------------
	
	public abstract boolean greaterThan(ArithmeticObject anArithmeticObject);

	public abstract boolean greaterThanForNumber(ExtendedNumber anExtendedNumber);
	
	public abstract boolean greaterThanForMeasure(Measure aMeasure);
	
	public abstract boolean greaterThanOrEqualTo(ArithmeticObject anArithmeticObject);
	
	public abstract boolean greaterThanOrEqualToForNumber(ExtendedNumber anExtendedNumber);
	
	public abstract boolean lessThan(ArithmeticObject anArithmeticObject);
	
	public abstract boolean lessThanForNumber(ExtendedNumber anExtendedNumber);
	
	public abstract boolean lessThanForMeasure(Measure aMeasure);
	
	public boolean lessThanOrEqualTo(ArithmeticObject anArithmeticObject) {
		return this.lessThan(anArithmeticObject) || this.equals(anArithmeticObject);
	}

	public boolean between(ArithmeticObject minimun, ArithmeticObject maximun) {
		return this.greaterThanOrEqualTo(minimun) && this.lessThanOrEqualTo(maximun);
	}
	
	public boolean betweenAndNotInclusive(ArithmeticObject minimun, ArithmeticObject maximun) {
		return this.greaterThanOrEqualTo(minimun) && this.lessThan(maximun);
	}
	
	public boolean notInclusiveBetween(ArithmeticObject minimun, ArithmeticObject maximun) {
		return this.greaterThan(minimun) && this.lessThan(maximun);
	}
	
	// ----------------------------------------------
	
	// yet to be seen ----------------
	public abstract ArithmeticObject numerator();
	
	public abstract ArithmeticObject denominator();
	
	public abstract ArithmeticObject with(ExtendedNumber aNumber);
	// -------------------------------------------------

	// Mixed with Unit for polymorphic behavior -----------------
	// Analyze if we should take this behavior out in another abstraction
	public abstract ArithmeticObject divideByForUnit(UnitBehavior aUnitBehavior);
	
	public abstract ArithmeticObject multiplyByForUnit(UnitBehavior aUnitBehavior);

	public abstract ArithmeticObject divideByForNumber(ExtendedNumber aNumber);

	public abstract ArithmeticObject multiplyByForNumber(ExtendedNumber aNumber);

	public abstract UnitBehavior baseUnit();

	public abstract ArithmeticObject reciprocal();

	//public abstract ArithmeticObject minusForNumber(ExtendedNumber aNumber);

	public abstract ArithmeticObject plusForNumber(ExtendedNumber aNumber);

	public abstract ArithmeticObject abs();
	
	public abstract boolean isNothing();

	public abstract ArithmeticObject plusMeasure(ArithmeticObject aMeasure);
	
	public abstract ArithmeticObject plusMeasureBag(MeasureBag measureBag);
	
	public abstract ArithmeticObject multiplyByForMeasure(Measure aMeasure);
	
	public abstract ArithmeticObject multiplyByForMeasureBag(MeasureBag measureBag);

	public abstract ArithmeticObject divideByForMeasure(Measure aMeasure);
	
	public abstract ArithmeticObject divideByForMeasureBag(MeasureBag aMeasureBag);

	public abstract boolean equalsMeasure(Measure aMeasure);
	
	public abstract UnitBehavior unit();
	
	public boolean sameUnitAs(ArithmeticObject anArithmeticObject) {
		return unit().equals(anArithmeticObject.unit());
	}

	public ArithmeticObject negated() {
		return (new SmallInteger(0)).minus(this);
	}

	public abstract ArithmeticObject amount();

	public abstract boolean isNegative();
	
	public abstract boolean isPositive();

	public boolean isMeasureBag() {
		return false;
	}

	public abstract ArithmeticObject convertToBaseUnit();

	public abstract ArithmeticObject truncated();

	
}
