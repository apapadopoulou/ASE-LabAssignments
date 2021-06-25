package codeanalyzer;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NullExporterTest {

	NullExporter ne = new NullExporter();
	
	private final ByteArrayOutputStream err = new ByteArrayOutputStream();
	private final PrintStream originalErr = System.err;
	
	@Before
	public void setStream() {
	    System.setErr(new PrintStream(err));
	}

	@After
	public void restoreInitialStream() {
	    System.setErr(originalErr);
	}
	
	@Test
	public void testWriteFile() {
		Map<String, Integer> metrics = new HashMap<>();
		metrics.put("loc",30);
		metrics.put("nom",5);
		metrics.put("noc",2);
		String filepath = "src/test/resources/output_metrics";
		ne.writeFile(metrics, filepath);
		Assert.assertEquals("Unknown output file type!", err.toString().trim());
	}
	
}
