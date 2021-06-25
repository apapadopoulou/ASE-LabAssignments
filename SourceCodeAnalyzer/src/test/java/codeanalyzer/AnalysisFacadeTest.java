package codeanalyzer;

import java.io.IOException;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.*;
import org.junit.Before;

public class AnalysisFacadeTest {

	private final static String TEST_CLASS_LOCAL = "src/test/resources/TestClass.java";
	private String outputFilepath = "src/test/resources/output_metrics";
	
	AnalysisFacade facade = new AnalysisFacade();
	
	@Before 
	public void initMocks() {
	       MockitoAnnotations.initMocks(this);
	   }
	
	@Mock
	SourceCodeAnalyzerFactory analyzerFactory;
	
	@Mock
	MetricsExporterFactory exporterFactory;
	
	@Test
	public void testCodeAnalysis() throws IOException {
		facade.analyzeCode("regex", "local", TEST_CLASS_LOCAL, "csv", outputFilepath);
		analyzerFactory.createSourceCodeAnalyzer("regex", "local");
		Mockito.verify(analyzerFactory).createSourceCodeAnalyzer("regex", "local");
		exporterFactory.createMetricsExporter("csv");
		Mockito.verify(exporterFactory).createMetricsExporter("csv");
	}
	
}
