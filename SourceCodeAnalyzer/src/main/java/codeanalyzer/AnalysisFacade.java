package codeanalyzer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * A facade for the code analysis system. 
 * @author apapadopoulou
 *
 */
public class AnalysisFacade {

	/**
	 * Performs the operations of the system, 
	 * in the context of code analysis.
	 * @param sourceCodeAnalyzerType the type of the source code analyzer
	 * @param sourceFileLocation the location of the source code file
	 * @param filepath the path of the file
	 * @param outputFileType the type of the output file
	 * @param outputFilePath the path of the output file
	 * @throws IOException
	 */	
	public void analyzeCode(String sourceCodeAnalyzerType, String sourceFileLocation, 
			String filepath, String outputFileType, String outputFilePath) throws IOException {
		SourceCodeAnalyzerFactory factory = new SourceCodeAnalyzerFactory();
		SourceCodeAnalyzer analyzer = factory.createSourceCodeAnalyzer(sourceCodeAnalyzerType, sourceFileLocation);
		int loc = analyzer.calculateLOC(filepath);
		int nom = analyzer.calculateNOM(filepath);
		int noc = analyzer.calculateNOC(filepath);
		
		Map<String, Integer> metrics = new HashMap<>();
		metrics.put("loc",loc);
		metrics.put("nom",nom);
		metrics.put("noc",noc);
		
		MetricsExporterFactory f = new MetricsExporterFactory();
		MetricsExporter exporter = f.createMetricsExporter(outputFileType);
		exporter.writeFile(metrics, outputFilePath);
	}
	
}
