package math;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;


public class ArithmeticOperationsTest {
	
	ArithmeticOperations ao = new ArithmeticOperations();

	@Test
	public void test_divide_normal_int() {
		Assert.assertEquals(3.0, ao.divide(6.0, 2.0), 0);
	}
	
	@Test
	public void test_divide_normal_double() {
		Assert.assertEquals(1.5, ao.divide(3.0, 2.0), 1e-8);
	}
	
	@Test(expected = ArithmeticException.class)
	public void test_divide_zero() {
		ao.divide(3, 0);
	}

	@Test
	public void test_multiply_normal() {
		Assert.assertEquals(6, ao.multiply(3, 2));
	}
	
	@Test
	public void test_multiply_firstZero() {
		Assert.assertEquals(0, ao.multiply(0, 2));
	}
	
	@Test
	public void test_multiply_secondZero() {
		Assert.assertEquals(0, ao.multiply(3, 0));
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void test_multiply_firstNumberNegative_RuleException() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("x & y should be >= 0");
		ao.multiply(-3, 3);
	}
	
	@Test
	public void test_multiply_secondNumberNegativenegative_RuleException() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("x & y should be >= 0");
		ao.multiply(3, -3);
	}
	
	@Test
	public void test_multiply_bothNumbersNegativenegative_RuleException() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("x & y should be >= 0");
		ao.multiply(-3, -3);
	}
	
	@Test
	public void test_multiply_bigNumbers_RuleException() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("The product does not fit in an Integer variable");
		ao.multiply(Integer.MAX_VALUE, 3);
	}


}
