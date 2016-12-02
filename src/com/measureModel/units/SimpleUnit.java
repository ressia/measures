package com.measureModel.units;

import com.measureModel.arithmeticObjects.ArithmeticObject;
import com.measureModel.measurements.Measure;
import com.measureModel.numbers.ExtendedNumber;

public abstract class SimpleUnit extends UnitBehavior {

	public ArithmeticObject multiplySimpleUnit(UnitBehavior aSimpleUnit) {
		return MultipliedUnit.simpleUnitSimpleUnit(this, aSimpleUnit);
	}
	
	public ArithmeticObject divideNullUnit(NullUnit aNullUnit) {
		return new DividedUnit(aNullUnit,this);
	}

	public ArithmeticObject divideByForUnit(UnitBehavior aUnitBehavior) {
		return aUnitBehavior.divideBySimpleUnit(this);
	}

	public ArithmeticObject divideBySimpleUnit(SimpleUnit aSimpleUnit) {
		return DividedUnit.simpleUnitSimpleUnit(this,aSimpleUnit);
	}
	
	public ArithmeticObject divideByDividedUnit(DividedUnit aDividedUnit) {
		return DividedUnit.simpleUnitDividedUnit(this, aDividedUnit);
	}

	public ArithmeticObject multiplyByDividedUnit(CompoundUnit aDividedUnit) {
		return MultipliedUnit.simpleUnitDividedUnit(this,aDividedUnit);
	}

	public ArithmeticObject multiplyBy(ArithmeticObject aNumberOrUnit) {
		return aNumberOrUnit.multiplyByForUnit(this);
	}

	public ArithmeticObject multiplyByForUnit(UnitBehavior aUnit) {
		return aUnit.multiplyBySimpleUnit(this);
	}

	public ArithmeticObject multiplyBySimpleUnit(SimpleUnit aSimpleUnit) {
		return MultipliedUnit.simpleUnitSimpleUnit(this,aSimpleUnit);
	}
	
	public ArithmeticObject multiplyByForMultipliedUnit(MultipliedUnit aMultipliedUnit) {
		return aMultipliedUnit.multiplyBySimpleUnit(this);
	}

	public ArithmeticObject divideByMultipliedUnit(MultipliedUnit aMultipliedUnit) {
		return DividedUnit.simpleUnitMultipliedUnit(this,aMultipliedUnit);
	}
	
	public Measure with(ExtendedNumber aNumber) {
		return new Measure(aNumber,this);
	}

	public ArithmeticObject divideByForNumber(ExtendedNumber aNumber) {
		return this.reciprocal().multiplyBy(aNumber);
	}

	public ArithmeticObject multiplyByForNumber(ExtendedNumber aNumber) {
		return this.multiplyBy(aNumber);
	}

}
