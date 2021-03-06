package edu.mitrofanov.codejam.utils;

public class PrintUtils {
	
	public static void printlnArray(Object[] array) {
		System.out.print("[");
		for (int i = 0; i < array.length; i++) {
			System.out.print("\t" + array[i].toString() + (i != array.length -1 ? " | " : ""));
		}
		System.out.println("]");
	}
	
	public static void printlnIntArray(int[] array) {
		System.out.print("[");
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + (i != array.length -1 ? " \t| " : ""));
		}
		System.out.println("\t]");
	}
	
	public static void printlnIntArray(long[] array) {
		System.out.print("[");
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + (i != array.length -1 ? " \t| " : ""));
		}
		System.out.println("\t]");
	}
	
	public static void printlnIntArray2D(int[][] array) {
		for (int i = 0; i < array.length; i++) {
			printlnIntArray(array[i]);
		}
		System.out.println();
	}
	
	
	
	
	
}
