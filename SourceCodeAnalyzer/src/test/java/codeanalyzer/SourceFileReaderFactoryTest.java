package codeanalyzer;

import org.junit.Assert;
import org.junit.Test;

public class SourceFileReaderFactoryTest {
	
	SourceFileReaderFactory factory = new SourceFileReaderFactory();
	
	@Test
	public void createSourceFileReader_testWeb() {
		Assert.assertTrue(factory.createSourceFileReader("web") instanceof WebFileReader);
	}
	
	@Test
	public void createSourceFileReader_testLocal() {
		Assert.assertTrue(factory.createSourceFileReader("local") instanceof LocalFileReader);
	}
	
	@Test
	public void createSourceFileReader_testNull() {
		Assert.assertTrue(factory.createSourceFileReader("invalid") instanceof NullFileReader);
	}

}
