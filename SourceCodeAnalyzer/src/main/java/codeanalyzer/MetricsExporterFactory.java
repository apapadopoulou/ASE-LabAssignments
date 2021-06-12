package codeanalyzer;

/**
 * A factory for the MetricsExporter interface that 
 * was created to implement the Strategy pattern.
 * @author apapadopoulou
 *
 */
public class MetricsExporterFactory {
	
	/**
	 * Creates a MetricsExporter, according to the type of the output file. 
	 * @param filepath the path of the file
	 * @throws IllegalArgumentException
	 */
	public MetricsExporter createMetricsExporter(String fileType) {
		MetricsExporter exporter;
		if (fileType.equals("csv")) {
			exporter = new CSVExporter();
		} else if (fileType.equals("json")) {
			exporter = new JSONExporter();
		} else {
			throw new IllegalArgumentException("Unknown type : " + fileType);
		}
		return exporter;
	}

}
