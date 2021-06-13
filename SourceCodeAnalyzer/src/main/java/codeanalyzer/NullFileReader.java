package codeanalyzer;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Handles the null case of the SourceFileReader interface.
 * @author apapadopoulou
 *
 */
public class NullFileReader implements SourceFileReader {

	/**
	 * Returns an empty List
	 * @param filepath the path of the file
	 * @throws IOException
	 */
	@Override
	public List<String> readFileIntoList(String filepath) throws IOException {
		System.err.print("Invalid path!");
		return Collections.emptyList();
	}
	
	/**
	 *Returns an empty String
	 * @param filepath the path of the file
	 * @throws IOException
	 */
	@Override
	public String readFileIntoString(String filepath) throws IOException {
		System.err.print("Invalid path!");
		return "";
	}
	
}
