package math;

import io.FileIO;

import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ArrayOperationsTest {
	
	ArrayOperations ao = new ArrayOperations();

	@Test
	public void test_findPrimesInFile_Mocking() {
		FileIO fio = mock(FileIO.class);
		MyMath mm = mock(MyMath.class);
		
		String filePath = "src/test/resources/numbers.txt";

		when(fio.readFile(filePath)).thenReturn(new int [] {4, 6, 2, 3, 9, 15, 23});

		when(mm.isPrime(4)).thenReturn(false);
		when(mm.isPrime(6)).thenReturn(false);
		when(mm.isPrime(2)).thenReturn(true);
		when(mm.isPrime(3)).thenReturn(true);
		when(mm.isPrime(9)).thenReturn(false);
		when(mm.isPrime(15)).thenReturn(false);
		when(mm.isPrime(23)).thenReturn(true);

		Assert.assertArrayEquals(new int [] {2, 3, 23}, ao.findPrimesInFile(fio, filePath, mm));
	}

}
