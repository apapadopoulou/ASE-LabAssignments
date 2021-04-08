package gradeshistogram;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
* The HistogramGenerator class is used to read a file of grades and generate a histogram,
* based on the grade frequency.
*
* @author  Angeliki Papadopoulou
* @version 1.0
* @since   2021-04-06 
*/

public class HistogramGenerator {
	
	/***
	 * Receives the path of the file that contains the grades.
	 * Then, the grades are stored as integers in an ArrayList.
	 * Finally, the ArrayList containing the grades is returned.
	 * 
	 * @param filePath The path of the file that contains the grades.
	 */
	
	public static ArrayList<Integer> createGradesArray(String filePath) {
		ArrayList<Integer> gradesArray = new ArrayList<Integer>();
		try {
			File file = new File(filePath);
		    Scanner scanner = new Scanner(file);
		    while (scanner.hasNextLine()) {
		    	String grade = scanner.nextLine();
		        gradesArray.add(Integer.parseInt(grade));
		    }
		    scanner.close();
		    } catch (FileNotFoundException e) {
		    	System.out.println("An error occurred while reading the file.");
		    	e.printStackTrace();
		    }
		return gradesArray;
	}
	
	/***
	 * Receives an ArrayList of type Integer that contains all of the grades
	 * and then calculates the frequency of each grade. The calculation is done in 
	 * a simple way, as the value of the corresponding cell of an integer array 
	 * is updated every time the grade is noted in the ArrayList. Finally, it returns
	 * an array of int type that contains the frequency of each grade.
	 * 
	 * @param grades The ArrayList containing the grades
	 */
	
	public static int[] countFrequencies(ArrayList<Integer> grades) {
		int [] gradeFrequencies = new int [11];
		for (int grade : grades) {
			gradeFrequencies[grade]++;
		}
		return gradeFrequencies;
	}
	
	/***
	 * Receives a single dimension Integer array that contains the grade frequencies. 
	 * From this array the data set
	 * that will be used for the visualization is generated. Finally, The chart
	 * is generated with the use of the aforementioned data set and then
	 * presented in the screen.
	 * 
	 * @param gradeFrequencies Single dimension integer array that contains the grade frequencies
	 */
	
	public static void createHistogram(int [] gradeFrequencies) {
		XYSeriesCollection dataset = new XYSeriesCollection();
		XYSeries data = new XYSeries("random values");
		for (int i = 0; i < gradeFrequencies.length; i++) {
			data.add(i, gradeFrequencies[i]);
		}
		dataset.addSeries(data);
		boolean legend = false;
		boolean tooltips = false;
		boolean urls = false;
		JFreeChart chart = ChartFactory.createXYLineChart("Grades Histogram", "Grade", "Frequency", dataset,
				PlotOrientation.VERTICAL, legend, tooltips, urls);
		ChartFrame frame = new ChartFrame("First", chart);
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String args[]) {
		ArrayList<Integer> gradesArray = createGradesArray(args[0]);
		int [] frequenciesArray = countFrequencies(gradesArray);
		createHistogram(frequenciesArray);
	}

}
