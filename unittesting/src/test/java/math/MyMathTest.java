package math;

import static org.junit.Assert.*;
import org.junit.Test;

public class MyMathTest {

	MyMath mm = new MyMath();
	
	@Test(expected = IllegalArgumentException.class)
	public void test_factorial_negativeNumber() {
		mm.factorial(-1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_factorial_largeNumber() {
		mm.factorial(13);
	}

}
