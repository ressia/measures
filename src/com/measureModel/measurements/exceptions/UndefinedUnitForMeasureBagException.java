package com.measureModel.measurements.exceptions;

import com.developmentExceptions.exceptions.*;
import com.measureModel.measurements.MeasureBag;

public class UndefinedUnitForMeasureBagException extends RuntimeException {

	private MeasureBag measureBag;
	
	public UndefinedUnitForMeasureBagException(MeasureBag aMeasureBag) {
		measureBag = aMeasureBag;
	}

	public MeasureBag measureBag() {
		return measureBag;
	}
	
	
}
