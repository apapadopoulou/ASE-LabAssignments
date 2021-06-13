## <a name="table-of-contents"></a>Table of contents
- [Source Code Analyzer Repository](#source-code-analyzer-repository)
	- [Build Instructions](#build-instructions)
	- [Solution Overview](#solution-overview)
	- [Step-by-Step Modifications](#step-by-step-modifications)
		- [Step 1: Modification of `MetricsExporter` class using the Strategy pattern](#step-1-modification-of-metricsexporter-class-using-the-strategy-pattern)
		- [Step 2: Implementation of the Factory pattern with a `MetricsExporterFactory` class](#step-2-implementation-of-the-factory-pattern-with-a-metricsexporterfactory-class)
		- [Step 3: Implementation of the Null Object pattern on the `MetricsExporter` interface](#step-3-implementation-of-the-null-object-pattern-on-the-metricsexporter-interface)
		- [Step 4: Apply the Strategy, Factory and Null Object patterns on the `SourceFileReader` class](#step-4-apply-the-strategy-factory-and-null-object-patterns-on-the-sourcefilereader-class)
		- [Step 5: Apply the Strategy, Factory and Null Object patterns on the `SourceCodeAnalyzer` class](#step-5-apply-the-strategy-factory-and-null-object-patterns-on-the-sourcecodeanalyzer-class)
		- [Step 6: Implement the Facade design pattern](#step-6-implement-the-facade-design-pattern)

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

## Solution Overview

The purpose of the fourth lad assignment is to use design patterns in order to improve the quality of the Source Code Analyzer system.

The system contains two packages that represent the code analyzer and the client that provides input to the system. In the initial version, a `DemoClient` class was used to communicate with the classes that perform the operations of code analysis and metrics export. A `SourceCodeAnalysis` class was used to perform the source code analysis and calculate the LOC, NOM and NOC metrics. In order to do the analysis, the class used a reader object fron the `SourceFileReader` class. Finally, the `MetricsExporter` class was used to export the metrics to an output file.

There are three primary dimension in the system, that are hard to be extended and dependent on each other:

1. The **source code analyzer type**. Currently, it can be either *regex* or *strcomp*.
2. The **file reader location type**. Currently, it can be either *web* or *local*.
3. The **metrics exporter type**. Currently, it can be either *csv* or *json*.

In order to improve the quality of the system design, the following design patterns were used:
1. The **Strategy** design pattern was used to make the dimensions that were mentioned above more extensible and independent from each other, as well as make the addition of dimension-specific functionality more easy.
2. The **Factory** design pattern was used in insatnces when it eas necessary to provide an interface, that would make the communication with the dimensions more simple.
3. The **Null Object** design pattern was used to extend the above-mentioned dimensions, in order to enable the handling of null cases.
4. The **Facade** design pattern was used to mask the complexity of the system and hide the implementation details from the client.

All of the code changes are documented below, in the [Step-by-Step Modifications](#step-by-step-modifications) unit, in a step-by-step format. The steps 1, 2, 3, 6 describe the implementation of one design pattern each, while the steps 4 and 5 describe changes that concern more than one design pattern, grouping the individual implementations into one step.

The class diagram of the final version of the system is the following.

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

**Pros:** The structure of the system is now improved, as new readers can be easily added by implementing the `SourceFileReader` interface. The use of the Factory pattern provides an easy interface that is used to determine the type of reader that will be used, while the use of the Null Object pattern contributes to a more easy handling of null cases.

### Step 5: Apply the Strategy, Factory and Null Object patterns on the `SourceCodeAnalyzer` class

The `SourceCodeAnalyzer` class was replaced by a `SourceCodeAnalyzer` interface. The interface groups the methods that calculate the metrics and is implemented by the `RegexAnalyzer` and `StrcompAnalyzer` classes. A `NullAnalyzer` class also implements the interface, according to the Null Object design pattern. This class represents the null case, where the input that was given as an analyzer type is invalid. All methods of this class return `-1`, as this was also done in the initial `SourceCodeAnalyzer` class, if the input about the analyzer tyoe was invalid.

A `SourceCodeAnalyzerFactory` class is also created to handle the creation of the source code analyzer. This class also communicates with the `SourceFileReaderFactory` class, in order to create the source file reader object that will be used to read the source code file.

All of the classes that implement the `SourceCodeAnalyzer` interface begin with a contructor that initializes a `fileReader` field with the appropriate file reader.

The code that creates the `SourceCodeAnalyzer` object is moved to the `DemoClient` class. There, code that creates a `SourceCodeAnalyzerFactory` class object that is used to create the appropriate source code analyzer object.

**Pros:** The structure of the system is slightly improved, as new readers can be easily added by implementing the `SourceCodeAnalyzer` interface. The use of the Factory pattern provides an easy interface that is used to determine the type of analyzer that will be used and the use of the Null Object pattern helps to handle the case where the input for the type of the source code analyzer is invalid.

**Cons:** The existence of the `fileReader` field means that the implementations of the `SourceCodeAnalyzer` and the `SourceFileReader` are not completely independent. In addition, more code is added to the `DemoClient` class, in order to create the `SourceCodeAnalyzerFactory` and the `SourceCodeAnalyzer` objects and handle the overall code analysis process.

### Step 6: Implement the Facade design pattern

The `DemoClient` class contains a lot of code that is used to handle the factories and the functionalities of the system. The Facade design pattern could improve the design of the system by hiding all of the implementation-specific information from the client.

To implement the Facade design pattern, an `AnalysisFacade` class is introduced. The class has an `analyzeCode` method, that performs all of the system operations during the code analysis process. The parameters of the method are the `sourceCodeAnalyzerType`, that determines the type of the code analyzer that will be uses, the `sourceFileLocation`, that determines if the source code file is located locally or on the web, the `filepath`, which is the actual path of the source code file, the `outputFileType`, which is the type of the output file and the `outputFilePath`, which represents the path of the output file.

Since all of the operations code has been moved in the `AnalysisFacade` class, the `DemoClient` class now only creates the facade object and calls the `analyzeCode` method, passing the main arguments as input.

**Pros:** The complexity of the system is hidden from the client class and the system is independent from the client.