package com.measureModel.measurements.exceptions;

import com.developmentExceptions.exceptions.*;
import com.measureModel.measurements.MeasureBag;

public class UndefinedAmountForMeasureBagException extends RuntimeException {

	private MeasureBag measureBag;
	
	public UndefinedAmountForMeasureBagException(MeasureBag aMeasureBag) {
		measureBag = aMeasureBag;
	}
	
}
