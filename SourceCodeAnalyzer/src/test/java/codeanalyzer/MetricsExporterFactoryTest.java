package codeanalyzer;

import org.junit.Assert;
import org.junit.Test;

public class MetricsExporterFactoryTest {
	
	MetricsExporterFactory factory = new MetricsExporterFactory();
	
	@Test
	public void createMetricsExporter_testCsv() {
		Assert.assertTrue(factory.createMetricsExporter("csv") instanceof CSVExporter);
	}
	
	@Test
	public void createMetricsExporter_testJson() {
		Assert.assertTrue(factory.createMetricsExporter("json") instanceof JSONExporter);
	}
	
	@Test
	public void createMetricsExporter_testNull() {
		Assert.assertTrue(factory.createMetricsExporter("invalid") instanceof NullExporter);
	}

}
