package com.measureModel.measurements.exceptions;

import com.developmentExceptions.exceptions.*;
import com.measureModel.arithmeticObjects.ArithmeticObject;
import com.measureModel.measurements.MeasureBag;

public class InvalidBinaryOperationException extends RuntimeException {

	private ArithmeticObject firstFactor;
	private ArithmeticObject secondFactor;
	
	public InvalidBinaryOperationException(ArithmeticObject anArithmeticObject, ArithmeticObject anotherArithmeticObject) {
		firstFactor = anArithmeticObject;
		secondFactor = anotherArithmeticObject;
	}

	public ArithmeticObject firstFactor() {
		return firstFactor;
	}

	public ArithmeticObject secondFactor() {
		return secondFactor;
	}

}

