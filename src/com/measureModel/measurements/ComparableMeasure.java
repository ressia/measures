package com.measureModel.measurements;

import java.util.ArrayList;
import java.util.Collection;

import com.measureModel.arithmeticObjects.ArithmeticObject;
import com.measureModel.numbers.ExtendedNumber;


public abstract class ComparableMeasure extends MeasureBehavior {
	
	public Collection measures() {
		ArrayList answer = new ArrayList();
		if (!isNothing()) {
			answer.add(this);
		}
		return answer;
	}
	
	public boolean sameDomainAs(ArithmeticObject anArithmeticObject) {
		return unit().sameDomainAs(anArithmeticObject.unit());
	}
	
	public abstract MeasureBehavior addSameDomainMeasure(ComparableMeasure aMeasure);

	public abstract MeasureBehavior createBagWith(ComparableMeasure aMeasure);

	public ExtendedNumber convertAmountToBaseUnit() {
		return unit().convertAmountToBaseUnit(amount());
	}

	public abstract MeasureBehavior createBagWithMeasure(ComparableMeasure aComparableMeasure);
	

	public ArithmeticObject max(ArithmeticObject anArithmeticObject) {
		if ( this.lessThan(anArithmeticObject)) {
			return anArithmeticObject;
		} else {
			return this; 
		}
	}
	
	public ArithmeticObject min(ArithmeticObject anArithmeticObject) {
		if ( this.greaterThan(anArithmeticObject)) {
			return anArithmeticObject;
		} else {
			return this; 
		}
	}
	


}
