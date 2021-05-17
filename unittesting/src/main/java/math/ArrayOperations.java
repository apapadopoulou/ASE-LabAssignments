package math;

import io.FileIO;
import java.util.ArrayList;

/**
* The ArrayOperations class provides methods
* that serve as hands-on practice on Unit Testing.
*
* @author  apapadopoulou
* @version 1.0
* @since   2021-05-05 
*/
public class ArrayOperations {

	/**
	 * Finds the prime numbers in a given file and returns them as an array.
	 * @param fileIo the FileIO class object
	 * @param filepath the path to where the file is located
	 * @param myMath the myMath class object
	 * @return the array containing the prime numbers
	 */
	public int[] findPrimesInFile(FileIO fileIo,
			String filepath, MyMath myMath) {
		int [] numbersArray = fileIo.readFile(filepath);
		ArrayList<Integer> primeNumbersArray = new ArrayList<Integer>();
		for (int number : numbersArray) {
			if (myMath.isPrime(number)) {
				primeNumbersArray.add(number);
			}
		}
		return primeNumbersArray.stream().mapToInt(i -> i).toArray();
	}
	
}
