package codeanalyzer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NullFileReaderTest {
	
	private final ByteArrayOutputStream err = new ByteArrayOutputStream();
	private final PrintStream originalErr = System.err;
	
	SourceFileReader reader = new NullFileReader();

	@Before
	public void setStream() {
	    System.setErr(new PrintStream(err));
	}

	@After
	public void restoreInitialStream() {
	    System.setErr(originalErr);
	}
	
	@Test
	public void testReadFileIntoListNull() throws IOException {
		List<String> actualList = reader.readFileIntoList("any-filepath");
		Assert.assertTrue(actualList.isEmpty());
		Assert.assertEquals("Invalid path!", err.toString().trim());
	}
	
	@Test
	public void testReadFileIntoStringNull() throws IOException {
		String actualString = reader.readFileIntoString("any-filepath");
		Assert.assertEquals(actualString, "");
		Assert.assertEquals("Invalid path!", err.toString().trim());
	}
	
}
