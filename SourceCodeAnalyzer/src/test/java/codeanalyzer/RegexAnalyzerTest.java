package codeanalyzer;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class RegexAnalyzerTest {
	
	private final static String TEST_CLASS = "src/test/resources/TestClass.java";
	
	SourceFileReader localReader = new LocalFileReader();
	SourceFileReader webReader = new WebFileReader();
	
	private SourceCodeAnalyzer localAnalyzer = new RegexAnalyzer(localReader);
	private SourceCodeAnalyzer webAnalyzer = new RegexAnalyzer(webReader);

	@Test
	public void testCalculateLOC_localAnalyzer() throws IOException {
		assertEquals(21, localAnalyzer.calculateLOC(TEST_CLASS));
	}
	
	@Test
	public void testCalculateRegexNOM_localAnalyzer() throws IOException {
		assertEquals(3, localAnalyzer.calculateNOM(TEST_CLASS));
	}
	
	@Test
	public void testCalculateNOC_localAnalyzer() throws IOException {
		assertEquals(3, localAnalyzer.calculateNOC(TEST_CLASS));
	}
	
}
