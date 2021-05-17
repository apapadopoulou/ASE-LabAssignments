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
	
	@Test
	public void test_divide_normalInt_secondNumberNegative() {
		Assert.assertEquals(-4.0, ao.divide(8.0, -2.0), 0);
	}
	
	@Test
	public void test_divide_normalDouble_secondNumberNegative() {
		Assert.assertEquals(-0.9, ao.divide(2.7, -3.0), 1e-8);
	}
	
	@Test
	public void test_divide_normalInt_firstNumberNegative() {
		Assert.assertEquals(-2.0, ao.divide(-6.0, 3.0), 0);
	}
	
	@Test
	public void test_divide_normalDouble_firstNumberNegative() {
		Assert.assertEquals(-0.3, ao.divide(-0.6, 2.0), 1e-8);
	}
	
	@Test
	public void test_divide_normal_zeroNumerator_positive() {
		Assert.assertEquals(0.0, ao.divide(0.0, 2.0), 0);
	}
	
	@Test
	public void test_divide_normal_zeroNumerator_negative() {
		Assert.assertEquals(0.0, ao.divide(0.0, -2.0), 0);
	}
	
	@Test
	public void test_divide_large_int() {
		Assert.assertEquals(87.0, ao.divide(57333.0, 659.0), 0);
	}
	
	@Test
	public void test_divide_large_int_firstNegative() {
		Assert.assertEquals(-87.0, ao.divide(-57333.0, 659.0), 0);
	}
	
	@Test
	public void test_divide_large_int_secondNegative() {
		Assert.assertEquals(-87.0, ao.divide(57333.0, -659.0), 0);
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
	
	@Test
	public void test_multiply_large() {
		Assert.assertEquals(57333, ao.multiply(659, 87));
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
	public void test_multiply_firstBigNumber_RuleException() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("The product does not fit in an Integer variable");
		ao.multiply(Integer.MAX_VALUE, 3);
	}
	
	@Test
	public void test_multiply_secondBigNumber_RuleException() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("The product does not fit in an Integer variable");
		ao.multiply(3, Integer.MAX_VALUE);
	}


}
