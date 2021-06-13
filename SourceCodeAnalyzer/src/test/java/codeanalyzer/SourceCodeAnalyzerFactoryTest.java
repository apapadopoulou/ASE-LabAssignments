package codeanalyzer;

import org.junit.Assert;
import org.junit.Test;

public class SourceCodeAnalyzerFactoryTest {

	SourceCodeAnalyzerFactory factory = new SourceCodeAnalyzerFactory();
	
	@Test
	public void createSourceCodeAnalyzer_testRegexLocal() {
		Assert.assertTrue(factory.createSourceCodeAnalyzer("regex", "local") instanceof RegexAnalyzer);
	}
	
	@Test
	public void createSourceCodeAnalyzer_testStrcompLocal() {
		Assert.assertTrue(factory.createSourceCodeAnalyzer("strcomp", "local") instanceof StrcompAnalyzer);
	}
	
	@Test
	public void createSourceCodeAnalyzer_testNullLocal() {
		Assert.assertTrue(factory.createSourceCodeAnalyzer("invalid", "local") instanceof NullAnalyzer);
	}
	
}
