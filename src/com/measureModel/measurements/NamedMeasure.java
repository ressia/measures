package com.measureModel.measurements;


/**
 * @author Pablo Laciana (placiana@hexacta.com)
 *
 * Mar 11, 2008
 */
public class NamedMeasure {

	Measure measure;
	MeasureName name;
	
	
	public NamedMeasure(Measure aMeasure, MeasureName aMeasureName) {
		measure = aMeasure;
		name = aMeasureName;
	}
	
	
	public MeasureName name() {
		return name;
	}
	
	public Measure measure() {
		return measure;
	}

}
