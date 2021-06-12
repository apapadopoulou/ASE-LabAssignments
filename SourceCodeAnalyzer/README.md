## <a name="table-of-contents"></a>Table of contents
- [Source Code Analyzer Repository](#source-code-analyzer-repository)
	- [Build Instructions](#build-instructions)
	- [Step-by-Step Modifications](#step-by-step-modifications)
		- [Step 1: Modification of `MetricsExporter` class using the Strategy pattern](#step-1-modification-of-metricsexporter-class-using-the-strategy-pattern)
		- [Step 2: Implementation of the Factory pattern with a `MetricsExporterFactory` class](#step-2-implementation-of-the-factory-pattern-with-a-metricsexporterfactory-class)

# Source Code Analyzer Repository

This repository is used to practice the implementation of design patterns, in the context of the 4th Lab Assignment of the Applied Software Engineering course.

The code refers to a software system that reads a Java source code file that is stored locally or on the web, calculates the Lines of Code
(LOC), Number of Classes (NOC) and Number of Methods (NOM) metrics, and finally, exports these metrics to an output file.

The purpose of the assignment is to modify the code and implement some design patterns, in order to improve the design quality of the system.

---

## Build Instructions

1. Build the executable Java application with: 
	mvn package jacoco:report

2. Run the executable by executing
	java –jar “jar-with-dependencies” arg0 arg1 arg2 arg3 arg4
were args translate to: 	
	arg0 = “JavaSourceCodeInputFile” (e.g., src/test/resources/TestClass.java)
	arg1 = “sourceCodeAnalyzerType” [regex|strcomp]
	arg2 = “SourceCodeLocationType” [local|web]
	arg3 = “OutputFilePath” (e.g., ../output_metrics_file)
	arg4 = “OutputFileType” [csv|json]
example: 
	java –jar ./target/sourcecodeanalyzer-0.0.1-SNAPSHOT-jar-with-dependencies.jar ./src/test/resources/TestClass.java regex local metrics_results csv
	
---

## Step-by-Step Modifications

Below there is the explanation of every modification that was made in the source code.

### Step 1: Modification of `MetricsExporter` class using the Strategy pattern

The Strategy pattern was applied to the `MetricsExporter ` class, in order to separate the behavior of the exporter according to the input file type and make it more extensible to support new export file types in the future. This pattern is suitable for our case because we want to achieve easy integration of new functionality, according to the file type, as well as make the exporter extensible to new file types.

The `MetricsExporter` class was replaced by a `MetricsExporter` interface, that contains the `writeFile` method. 

The classes that were introduced to implement the interface are `CSVExporter` and the `JSONExporter`. Each class contains the body of the `writeFile` method, which is different for every output file type. Since the method that should be executed depends on the type of the output file, the type check is transferred on the `DemoClient` class. There, the type of the file is determined using an `if` statement and the appropriate class object is created. The object is then used to execute the right `writeFile` method.

**Pros:** The exporters are extensible and new file types can be supported by the creation of a new class that implements the `MetricsExporter` interface. Also, new functionality according to the file type can be intriduced easily.

**Cons:** The checks for the type of exporter that should be used have been moved to the `DemoClient` class, which results in high-coupling between this class and the `MetricsExporter` implementations.

### Step 2: Implementation of the Factory pattern with a `MetricsExporterFactory` class

The Factory pattern is selected to improve the design of the system by hiding the instantiation logic from the `DemoClient` class.

Firstly, a `MetricsExporterFactory` class is created to be used, whenever there is a case where we need to determine which type of exporter we will use. The class contains the `createMetricsExporter` method that checks the type of the output file in an `if` statement and created the appropriate exporter implementation of the `MetricsExporter` interface.

The `DemoClient` class now only contains code for the instantiation of the `MetricsExporterFactory` object, the `createMetricsExporter` method call and the export of the metrics.

**Pros:** The instantiation logic is hidden from the `DemoClient` class, as an interface is provided to separate the logic from the client. In addition, the `DemoClient` class will not be affected in a future extension of the `MetricsExporter` implementations.