package math;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class ArithmeticOperationsTest {
	
	ArithmeticOperations ao = new ArithmeticOperations();

	@Test(expected = ArithmeticException.class)
	public void test_divide_zero() {
		ao.divide(3, 0);
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void test_multiply_firstNumberNegative_RuleException() {
		thrown.expect(IllegalArgumentException.class);
		ao.multiply(-3, 3);
	}
	
	@Test
	public void test_multiply_secondNumberNegativenegative_RuleException() {
		thrown.expect(IllegalArgumentException.class);
		ao.multiply(3, -3);
	}
	
	@Test
	public void test_multiply_bothNumbersNegativenegative_RuleException() {
		thrown.expect(IllegalArgumentException.class);
		ao.multiply(-3, -3);
	}

}
