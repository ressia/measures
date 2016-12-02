package com.measureModel.measurements.exceptions;

import com.developmentExceptions.exceptions.*;
import com.measureModel.measurements.Measure;
import com.measureModel.units.UnitBehavior;

public class CanNotConvertMeasureException extends RuntimeException {

	private Measure sourceMeasure;
	
	private UnitBehavior targetUnit;
	
	public CanNotConvertMeasureException(Measure aMeasure, UnitBehavior aTargetUnit){
		sourceMeasure = aMeasure;
		targetUnit = aTargetUnit; 
	}
	
	public String getMessage() {
		return "It is not possible to convert " + sourceMeasure + " to " + targetUnit;
	}

	public Measure sourceMeasure() {
		return sourceMeasure;
	}

	public UnitBehavior targetUnit() {
		return targetUnit;
	}
	
}
