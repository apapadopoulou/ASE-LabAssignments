package codeanalyzer;

import org.junit.Assert;

public class MetricsExporterFactoryTest {
	
	MetricsExporterFactory factory = new MetricsExporterFactory();
	
	public void createMetricsExporter_testCsv() {
		MetricsExporter mex = new CSVExporter();
		Assert.assertTrue(factory.createMetricsExporter("csv") instanceof CSVExporter);
	}
	
	public void createMetricsExporter_testJson() {
		MetricsExporter mex = new JSONExporter();
		Assert.assertTrue(factory.createMetricsExporter("json") instanceof JSONExporter);
	}

}
