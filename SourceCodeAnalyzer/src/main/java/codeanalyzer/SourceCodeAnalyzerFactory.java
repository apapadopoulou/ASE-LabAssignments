package codeanalyzer;

/**
 * A factory for the SourceCodeAnalyzer interface.
 * @author apapadopoulou
 *
 */
public class SourceCodeAnalyzerFactory {

	/**
	 * Creates a SourceCodeAnalyzer, according to the type of the path location.
	 * The file reader type is passed as an argument in the corresponding constructor,
	 * except from the null case, where the file reader type is redundant.
	 * @param analyzerType the type of the source code analyzer
	 * @param fileReaderType the path of the file
	 * @throws IllegalArgumentException
	 */
	public SourceCodeAnalyzer createSourceCodeAnalyzer(String analyzerType, String fileReaderType) {
		SourceFileReaderFactory factory = new SourceFileReaderFactory();
		SourceFileReader fileReader = factory.createSourceFileReader(fileReaderType);
		SourceCodeAnalyzer analyzer;
		if (analyzerType.equals("regex")) {
			analyzer = new RegexAnalyzer(fileReader);
		} else if (analyzerType.equals("strcomp")) {
			analyzer = new StrcompAnalyzer(fileReader);
		} else {
			analyzer = new NullAnalyzer();
		}
		return analyzer;
	}
	
}
