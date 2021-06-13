## <a name="table-of-contents"></a>Table of contents
- [Source Code Analyzer Repository](#source-code-analyzer-repository)
	- [Build Instructions](#build-instructions)
	- [Step-by-Step Modifications](#step-by-step-modifications)
		- [Step 1: Modification of `MetricsExporter` class using the Strategy pattern](#step-1-modification-of-metricsexporter-class-using-the-strategy-pattern)
		- [Step 2: Implementation of the Factory pattern with a `MetricsExporterFactory` class](#step-2-implementation-of-the-factory-pattern-with-a-metricsexporterfactory-class)
		- [Step 3: Implementation of the Null Object pattern on the `MetricsExporter` interface](#step-3-implementation-of-the-null-object-pattern-on-the-metricsexporter-interface)
		- [Step 4: Apply the Strategy, Factory and Null Object patterns on the `SourceFileReader` class](#step-4-apply-the-strategy-factory-and-null-object-patterns-on-the-sourcefilereader-class)

# Source Code Analyzer Repository

This repository is used to practice the implementation of design patterns, in the context of the 4th Lab Assignment of the Applied Software Engineering course.

The code refers to a software system that reads a Java source code file that is stored locally or on the web, calculates the Lines of Code
(LOC), Number of Classes (NOC) and Number of Methods (NOM) metrics, and finally, exports these metrics to an output file.

The purpose of the assignment is to modify the code and implement some design patterns, in order to improve the design quality of the system.

---

## Build Instructions

1. Build the executable Java application with: 
   
	`mvn package jacoco:report`

2. Run the executable by executing
   
	`java –jar “jar-with-dependencies” arg0 arg1 arg2 arg3 arg4`

	were args translate to: 	

	- arg0 = “JavaSourceCodeInputFile” (e.g., src/test/resources/TestClass.java)
	- arg1 = “sourceCodeAnalyzerType” [regex|strcomp]
	- arg2 = “SourceCodeLocationType” [local|web]
	- arg3 = “OutputFilePath” (e.g., ../output_metrics_file)
	- arg4 = “OutputFileType” [csv|json]
  
	Example: 

	`java –jar ./target/sourcecodeanalyzer-0.0.1-SNAPSHOT-jar-with-dependencies.jar ./src/test/resources/TestClass.java regex local metrics_results csv`
	
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

### Step 3: Implementation of the Null Object pattern on the `MetricsExporter` interface

A null exporter was created, to be used in the case where the output file type is incorrect.

The `NullExporter` class implements the `MetricsExporter` interface by printing an error message in the `writeFile` method body. The `MetricsExporterFactory` class creates a null exporter whenever the output file type is unknown. 

**Pros:** The null case is handled properly and there is no need to handle the exceptions in the factory or the client class. The overall code is simplified and the client class is not required to handle exceptions.

**Cons:** The pattern should not be used to hide code errors.

### Step 4: Apply the Strategy, Factory and Null Object patterns on the `SourceFileReader` class

To begin with, the `SourceFileReader` class was replaced by a `SourceFileReader` interface. The methods `readFileIntoList` and `readFileIntoString` are grouped by the interface and their bodies are implemented accordingly in the classes that implement the interface. The `type` field of the `SourceFileReader` class is redundant, as the suitable class will be determined in the `SourceFileReaderFactory` class that will be created later on.

The `WebFileReader` class implements the `SourceFileReader` interface and represents the case where the file that contains the source code is located on the web. The `LocalFileReader` class implements the `SourceFileReader` interface and represents the case where the file that contains the source code is located locally.

In addition to the above classes, a `NullFileReader` class also implements the `SourceFileReader` interface by covering the case where the file path is invalid. The class was implemented with the logic to handle the null case effectively, so the return values of the  `readFileIntoList` and `readFileIntoString` methods are an empty list and an empty string correspondingly. This way, the system handles the null case by letting the program terminate without interruptions. 

As mentioned above, the `SourceFileReaderFactory` class is responsible for determining the type of the source file reader class that should be used. This happens in an `if` block.

Finally, the `SourceCodeAnalyzer` class now communicates with the `SourceFileReaderFactory` class, to select the appropriate reader.

**Pros:** The structure of the system is now improved, as new readers can be easily added by implementing the `SourceFileReader` interface. The use of the Factory pattern provides an easy interface that is used to determine the type of reader that will be used, while the use of the Null Object pattern contributes to a more easy andling of null cases.