package math;

import io.FileIO;

import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ArrayOperationsTest {
	
	ArrayOperations ao = new ArrayOperations();

	@Test
	public void test_findPrimesInFileNormalCase_Mocking() {
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

	@Test(expected = IllegalArgumentException.class)
	public void test_findPrimesInFileThatDoesNotExist_Mocking() {
		FileIO fio = mock(FileIO.class);
		MyMath mm = mock(MyMath.class);
		
		String filePath = "src/test/resources/this_does_not_exist.txt";

		when(fio.readFile(filePath)).thenThrow(IllegalArgumentException.class);

		ao.findPrimesInFile(fio, filePath, mm);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_findPrimesInEmptyFile_Mocking() {
		FileIO fio = mock(FileIO.class);
		MyMath mm = mock(MyMath.class);
		
		String filePath = "src/test/resources/empty_file.txt";

		when(fio.readFile(filePath)).thenThrow(IllegalArgumentException.class);

		ao.findPrimesInFile(fio, filePath, mm);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_findPrimesInFileWithInvalidEntries_Mocking() {
		FileIO fio = mock(FileIO.class);
		MyMath mm = mock(MyMath.class);
		
		String filePath = "src/test/resources/invalid_entries.txt";

		when(fio.readFile(filePath)).thenReturn(new int [] {1, 2, 9});

		when(mm.isPrime(1)).thenThrow(IllegalArgumentException.class);
		when(mm.isPrime(2)).thenReturn(true);
		when(mm.isPrime(9)).thenReturn(false);

		ao.findPrimesInFile(fio, filePath, mm);
	}

	@Test
	public void test_findPrimesInFileAllPrime_Mocking() {
		FileIO fio = mock(FileIO.class);
		MyMath mm = mock(MyMath.class);
		
		String filePath = "src/test/resources/all_prime.txt";

		when(fio.readFile(filePath)).thenReturn(new int [] {2, 5, 83});

		when(mm.isPrime(2)).thenReturn(true);
		when(mm.isPrime(5)).thenReturn(true);
		when(mm.isPrime(83)).thenReturn(true);

		Assert.assertArrayEquals(new int [] {2, 5, 83}, ao.findPrimesInFile(fio, filePath, mm));
	}

	@Test
	public void test_findPrimesInFileNonePrime_Mocking() {
		FileIO fio = mock(FileIO.class);
		MyMath mm = mock(MyMath.class);
		
		String filePath = "src/test/resources/none_prime.txt";

		when(fio.readFile(filePath)).thenReturn(new int [] {4, 8, 27, 121});

		when(mm.isPrime(4)).thenReturn(false);
		when(mm.isPrime(8)).thenReturn(false);
		when(mm.isPrime(27)).thenReturn(false);
		when(mm.isPrime(121)).thenReturn(false);

		Assert.assertArrayEquals(new int [] {}, ao.findPrimesInFile(fio, filePath, mm));
	}

}
