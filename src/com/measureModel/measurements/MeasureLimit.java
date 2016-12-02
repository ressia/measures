package com.measureModel.measurements;

public class MeasureLimit {
	
	private double min;
	private double avg;
	private double max;
	
	public MeasureLimit(double min, double avg, double max) {
		this.min = min;
		this.avg = avg;
		this.max = max;
	}

	public double getMin() {
		return min;
	}

	public double getAvg() {
		return avg;
	}

	public double getMax() {
		return max;
	}
}
