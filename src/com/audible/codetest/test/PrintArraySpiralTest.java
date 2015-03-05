package com.audible.codetest.test;

import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.audible.codetest.PrintArraySpiral;

public class PrintArraySpiralTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
	}

	@After
	public void cleanUpStreams() {
		System.setOut(null);
		System.setErr(null);
	}

	@Test
	public void testPrintArraySpiral() {
		int[][] inputArray = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		PrintArraySpiral.printArraySpiral(inputArray);
		Assert.assertEquals("1 2 3 6 9 8 7 4 5 ", outContent.toString());
	}

	@Test
	public void testCreate2DArrayFromFile() {
		String fileName = "src/test/input.txt";
		try {
			int[][] expected = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
			int[][] result = PrintArraySpiral.create2DArrayFromFile(fileName);
			Assert.assertEquals(expected, result);
			
		} catch (Exception e) {
			fail("InvalidIPAddressException: " + e.getMessage());
		}
	}

}
