package com.measureModel.measurements;

public class OverviewPyramidLimits {

	public MeasureLimit getHighLevelStructuringLimit() {
		return new MeasureLimit(6D, 15D, 20D);
	}
	
	public MeasureLimit getClassStructuringLimit() {
		return new MeasureLimit(12D, 16D, 20D);
	}
	
	public MeasureLimit getOperationStructuringLimit() {
		return new MeasureLimit(6D, 8D, 10D);
	}
	
	public MeasureLimit getInstrinsicOperationComplexityLimit() {
		return new MeasureLimit(0.17000000000000001D,0.20999999999999999D, 0.25D);
	}
	
	public MeasureLimit getCouplingIntensityLimit() {
		return new MeasureLimit(2D, 2.6000000000000001D,3.2000000000000002D);
	}
	
	public MeasureLimit getCouplingDispersionLimit() {
		return new MeasureLimit(0.56999999999999995D,0.63D, 0.68999999999999995D);
	}
	
	public MeasureLimit getAverageHierarchyHeightLimit() {
		return new MeasureLimit(0.10000000000000001D,0.20999999999999999D, 0.29999999999999999D);
	}
	
	public MeasureLimit getAverageNumberOfDerivedClassesLimit() {
		return new MeasureLimit(0.20000000000000001D,0.40999999999999998D, 0.59999999999999998D);
	}
	
}
