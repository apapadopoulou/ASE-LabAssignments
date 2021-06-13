package codeanalyzer;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class LocalFileReaderTest {
	
	SourceFileReader reader = new LocalFileReader();
	
	private static List<String> expectedList;
	private static String expectedString;
	private final static String TEST_CLASS_LOCAL = "src/test/resources/TestClass.java";
	
	@BeforeClass
	public static void setUp() throws IOException {
		expectedList = Files.readAllLines(new File(TEST_CLASS_LOCAL).toPath(), Charset.defaultCharset());
		expectedString = String.join("\n", expectedList) + "\n"; // transforms a list into a String (with 'new line' as delimiter) 
	}
	
	@Test
	public void testReadFileIntoListLocal() throws IOException {
		List<String> actualList = reader.readFileIntoList(TEST_CLASS_LOCAL);
		String[] expecteds = expectedList.stream().toArray(String[]::new);
		String[] actuals = actualList.stream().toArray(String[]::new);
		assertArrayEquals(expecteds, actuals);
	}
	
	@Test
	public void testReadFileIntoStringLocal() throws IOException {
		String actuals = reader.readFileIntoString(TEST_CLASS_LOCAL);
		assertEquals(expectedString, actuals);
	}

}
