package io;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class FileIOTest {

	FileIO fio = new FileIO();
	public static String resourcesPath = "src/test/resources/";
	
	@Test
	public void test_readFile_normal() {
		String filePath = resourcesPath + "valid_numbers.txt";
		int [] expectedNumbers = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		Assert.assertArrayEquals(expectedNumbers, fio.readFile(filePath));
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void test_readFile_doesNotExist() {
		String filePath = resourcesPath + "this_does_not_exist.txt";
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Input file does not exist");
		fio.readFile(filePath);
	}

	@Test
	public void test_readFile_empty() {
		String filePath = resourcesPath + "empty_file.txt";
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Given file is empty");
		fio.readFile(filePath);
	}
	
	@Test
	public void testReadFileContainsInvalidEntries() {
		String filePath = resourcesPath + "invalid_entries.txt";
		int [] expectedOutput = {1, 2, 9};
		Assert.assertArrayEquals(expectedOutput, fio.readFile(filePath));
	}
	
}
