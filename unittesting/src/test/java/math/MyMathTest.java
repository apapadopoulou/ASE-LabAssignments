package math;

import org.junit.Test;
import org.junit.Assert;

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
	
	@Test(expected = IllegalArgumentException.class)
	public void test_isPrime_illegal() {
		mm.isPrime(1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_isPrime_negative() {
		mm.isPrime(-1);
	}
	
	@Test
	public void test_isPrime_border() {
		Assert.assertEquals(true, mm.isPrime(2));
	}
	
	@Test
	public void test_isPrime_normalPrime() {
		Assert.assertEquals(true, mm.isPrime(17));
	}
	
	@Test
	public void test_isPrime_normalNotPrime() {
		Assert.assertEquals(false, mm.isPrime(8));
	}
	
	@Test
	public void test_isPrime_largePrime() {
		Assert.assertEquals(true, mm.isPrime(2801));
	}
	
	@Test
	public void test_isPrime_largeNotPrime() {
		Assert.assertEquals(false, mm.isPrime(2000));
	}

}
