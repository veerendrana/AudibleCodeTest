package com.audible.codetest;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PrintArraySpiral {

	private static Scanner input;
	private static int rows = 0;
	private static int cols = 0;
	private static String fileName = "";
	private static int[][] inputArray;

	public static void main(String[] args) {
		input = new Scanner(System.in);
		do {
			// Asks the user how he wants to input the Matrix
			// on number input.
			System.out.println("Please enter the number for your choice: ");
			System.out.println("1. Read matrix from the console.");
			System.out.println("2. Read matrix from the text file.");
			System.out.println("0. Exits the program");

			int num = input.nextInt();
			input.nextLine();
			switch (num) {
			case 0:
				System.exit(0);
				break;
			case 1:
				System.out.println("Enter the number of rows (m): ");
				try {
					rows = input.nextInt();
					System.out.println("Enter the number of cols (n): ");
					cols = input.nextInt();
					inputArray = new int[rows][cols]; // initialze array
					System.out.println("Enter the data for (" + rows + "*"
							+ cols + ") Array: ");
					for (int i = 0; i < rows; i++) {
						for (int j = 0; j < cols; j++) {
							System.out.println("Enter the matrix elemnt [" + i
									+ "][" + j + "] : ");
							inputArray[i][j] = input.nextInt();
						}
					}

					System.out.println("The Input Array you entered: ");
					for (int i = 0; i < rows; i++) {
						for (int j = 0; j < cols; j++) {
							System.out.print(inputArray[i][j] + " ");
						}
						System.out.print("\n");
					}

					System.out.println("Output in Spiral order: ");
					printArraySpiral(inputArray);
					System.out.println("");
					System.out.println("");
				} catch (Exception e) {
					System.out.println("Only numbers are allowed as input");
					input.nextLine();
				}

				break;
			case 2:
				System.out.println("Enter the text file path: ");
				fileName = input.nextLine();
				System.out.println("File :" + fileName);
				try {
					inputArray = create2DArrayFromFile(fileName);

					System.out.println("Output in Spiral order: ");
					printArraySpiral(inputArray);
					System.out.println("");
					System.out.println("");

				} catch (Exception e) {
					System.out
							.println("Exception occured while reading the input text file: "
									+ e.getMessage());
				}
				break;
			default:
				break;
			}

		} while (true);
	}

	/**
	 * Method to print the Array in spiral order.
	 * 
	 * @param inputArray
	 */
	public static void printArraySpiral(int[][] inputArray) {

		int ur = 0; // Upper row
		int rc = cols - 1; // Right column col-1
		int lr = rows - 1; // Lower row row-1
		int lc = 0; // Left column
		while (ur <= lr && rc >= lc) {
			for (int i = lc; i < rc; i++) {
				System.out.print(inputArray[ur][i] + " ");
			}
			for (int j = ur; j <= lr; j++) {
				System.out.print(inputArray[j][rc] + " ");
			}
			if (ur < lr) {
				for (int k = rc - 1; k >= lc; k--) {
					System.out.print(inputArray[lr][k] + " ");
				}
			}
			if (rc > lc) {
				for (int l = lr - 1; l > ur; l--) {
					System.out.print(inputArray[l][lc] + " ");
				}
			}
			ur++;
			rc--;
			lr--;
			lc++;
		}
	}

	/**
	 * Method to create Array from file.
	 * 
	 * @param filename
	 * @return
	 * @throws Exception
	 */
	public static int[][] create2DArrayFromFile(String fileName)
			throws Exception {
		Scanner in = new Scanner(new File(fileName));
		List<String[]> lines = new ArrayList<>();
		while (in.hasNextLine()) {
			String line = in.nextLine().trim();
			String[] splitted = line.split(" ");
			cols = splitted.length;
			lines.add(splitted);
		}

		String[][] result = new String[lines.size()][];
		for (int i = 0; i < result.length; i++) {
			result[i] = lines.get(i);
		}
		rows = lines.size();
		int[][] resultArray = new int[lines.size()][cols];

		for (int i = 0; i < result.length; i++) {
			String[] j = result[i];
			for (int j2 = 0; j2 < j.length; j2++) {
				resultArray[i][j2] = Integer.parseInt(j[j2]);
			}
		}
		System.out.println("Input array from text file: "
				+ Arrays.deepToString(resultArray));

		in.close();
		return resultArray;
	}

}