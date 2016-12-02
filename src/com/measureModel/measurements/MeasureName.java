package com.measureModel.measurements;


public class MeasureName {

	String name;

	public MeasureName(String aName) {
		name = aName;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return getName();
	}

	public boolean equals(Object anObject) {
		
		if (!(anObject instanceof MeasureName)) {
			return false;
		}
		
		return ((MeasureName)anObject).getName().equals(name);
	}

}
