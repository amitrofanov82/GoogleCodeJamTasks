package edu.mitrofanov.codejam.utils;

public class ArrayUtils {
	
	public static int[][] createIntArrayWithDefault(int m, int n, int value) {
		int[][] result = new int[m][n];
		for(int[] row : result) {
			for (int i =0; i< row.length; i++) {
				row[i] = value;
			}
		}
		return result;
	}

	public static int[][] deleteColumnFromMatrix(int[][] matrix, int column) {
		int[][] result = new int[matrix.length][matrix[0].length -1];
		for(int i = 0; i < matrix.length; i++) {
			for (int j =0; j< matrix[i].length; j++) {
				if (j < column) {
					result[i][j] = matrix[i][j];
				} else if (j > column ) {
					result[i][j-1] = matrix[i][j];
				}
			}
		}
		return result;
	}
	
	public static int[][] deleteRowFromMatrix(int[][] matrix, int row) {
		int[][] result = new int[matrix.length-1][];
		for(int i = 0; i < matrix.length; i++) {
			if (i < row) {
				result[i] = matrix[i];
			} else if (i > row ) {
				result[i-1] = matrix[i];
			}
			
		}
		return result;
	}
	
	
}
