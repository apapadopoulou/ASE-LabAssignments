package codeanalyzer;

/**
 * A factory for the SourceFileReader interface that 
 * was created to implement the Strategy pattern.
 * @author apapadopoulou
 *
 */
public class SourceFileReaderFactory {

	/**
	 * Creates a SourceFileReader, according to the type of the path location. 
	 * @param filepath the path of the file
	 * @throws IllegalArgumentException
	 */
	public SourceFileReader createSourceFileReader(String readerType) {
		SourceFileReader reader;
		if (readerType.equals("web")) {
			reader = new WebFileReader();
		} else if (readerType.equals("local")) {
			reader = new LocalFileReader();
		} else {
			reader = new NullFileReader();
		}
		return reader;
	}
	
}
