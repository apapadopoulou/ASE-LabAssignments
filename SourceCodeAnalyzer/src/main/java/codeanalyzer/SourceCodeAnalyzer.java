package codeanalyzer;

import java.io.IOException;

/**
 * Analyzes the contents of a Java source code file 
 * and calculates the following metrics: loc = lines of code,
 * nom = number of methods, and noc=number of classes. 
 * @author apapadopoulou
 *
 */
public interface SourceCodeAnalyzer {
	
	/**
	 * Calculates the LOC metric.
	 * @param filepath the path of the file
	 * @throws IOException
	 */	
	public abstract int calculateLOC(String filepath) throws IOException;
	
	/**
	 * Calculates the NOM metric.
	 * @param filepath the path of the file
	 * @throws IOException
	 */	
	public abstract int calculateNOM(String filepath) throws IOException;
	
	/**
	 * Calculates the NOC metric.
	 * @param filepath the path of the file
	 * @throws IOException
	 */	
	public abstract int calculateNOC(String filepath) throws IOException;

}
