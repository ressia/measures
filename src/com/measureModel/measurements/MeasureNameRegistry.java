package com.measureModel.measurements;

public class MeasureNameRegistry {

	public static MeasureName linesOfCodeName() {
		return new MeasureName("linesOfCode");
	}

	public static MeasureName numberOfMethodsName() {
		return new MeasureName("numberOfMethods");
	}

	public static MeasureName numberOfClassesName() {
		return new MeasureName("numberOfClasses");
	}

	public static MeasureName lineCoverageName() {
		return new MeasureName("lineCoverage");
	}

	public static MeasureName branchCoverageName() {
		return new MeasureName("branchCoverage");
	}

	public static MeasureName AverageComplexityName() {
		return new MeasureName("averageCyclomaticComplexity");
	}

	public static MeasureName MaxComplexityName() {
		return new MeasureName("maxCyclomaticComplexity");
	}

	public static MeasureName numberOfNonBeanMethods() {
		return new MeasureName("numberOfNonBeanMethods");
	}

	public static MeasureName numberOfPackages() {
		return new MeasureName("numberOfPackages");
	}

	public static MeasureName numberOfTestClassesName() {
		return new MeasureName("numberOfTestClasses");
	}

	public static MeasureName numberOfTestMethodsName() {
		return new MeasureName("numberOfTestMethods");
	}

	public static MeasureName numberOfStatementsName() {
		return new MeasureName("numberOfStatements");
	}

	public static MeasureName numberOfClassMethods() {
		return new MeasureName("numberOfClassMethods");
	}

	public static MeasureName numberOfCalledClasses() {
		return new MeasureName("numberOfCalledClasses");
	}

	public static MeasureName averageNumberOfDerivedClasses() {
		return new MeasureName("averageNumberOfDerivedClasses");
	}

	public static MeasureName numberOfOperationCalls() {
		return new MeasureName("numberOfOperationCalls");
	}

	public static MeasureName averageHierarchyHeight() {
		return new MeasureName("averageHierarchyHeight");
	}
}
