package math;

/**
* The MyMath class provides an operation
* that serves as hands-on practice on Unit Testing.
*
* @author  apapadopoulou
* @version 1.0
* @since   2021-04-27 
*/
public class MyMath {
	
	/**
	 * Performs the arithmetic operation of calculating a number's factorial.
	 * @param n the number that will be used to calculate the factorial
	 * @return the factorial of number n
	 * @exception IllegalArgumentException if n does not belong in range [0, 12]
	 */
	public int factorial(int n) {
		if (n < 0 || n > 12) {
			throw new IllegalArgumentException("Invalid input!");
		}
		if (n == 0) {
			return 1;
		} else {
			return n * factorial(n - 1);
		}
	}
	
}
