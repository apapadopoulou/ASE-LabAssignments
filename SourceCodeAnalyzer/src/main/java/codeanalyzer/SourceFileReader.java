package codeanalyzer;

import java.io.IOException;
import java.util.List;

/**
 * Retrieves (reads) the contents of a given file.
 * The file can be stored locally or exist on the web.
 * @author apapadopoulou
 *
 */
public interface SourceFileReader {

	/**
	 * Reads a file and returns its content in a List
	 * @param filepath the path of the file
	 * @throws IOException
	 */
	public List<String> readFileIntoList(String filepath) throws IOException;
	
	/**
	 * Reads a file and returns its content in a single String
	 * @param filepath the path of the file
	 * @throws IOException
	 */
	public String readFileIntoString(String filepath) throws IOException;

}
