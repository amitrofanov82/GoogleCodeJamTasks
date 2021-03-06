package edu.mitrofanov.codejam.r1a_2008.task_b;

import java.util.ArrayList;
import java.util.List;

import edu.mitrofanov.codejam.utils.ArrayUtils;
import edu.mitrofanov.codejam.utils.PrintUtils;
import edu.mitrofanov.codejam.utils.StringUtils;

/*
N, the number of milkshake flavors.
M, the number of customers.

 */
public class TaskResolver {
	
	static class Milkshake {
		int shakeNum;
		boolean malted;
		public Milkshake(String num, String malt){
			shakeNum = Integer.parseInt(num);
			malted = malt.equals("1");
		}
		
		@Override
		public String toString() {
			return "[" + shakeNum + " " + (malted ? "MALTED" : "UNMALTED") + "]"; 
		}
		
	}
	
	static class Customer{
		int custNumber;
		Milkshake[] favoriteShakes = null;
		
		@Override
		public String toString() {
			return "Customer " + custNumber + ": " + StringUtils.arrayToString(favoriteShakes) + "\r\n"; 
		}
	}
	
	
	public static String resolveOneCase(int N, String[][] custMilkshakes) {
		int[][] dataMatrix = ArrayUtils.createIntArrayWithDefault(custMilkshakes.length+2, N+1, -1);
		int[] result = new int[N];
		for (int i = 0; i < result.length; i++) {
			result[i] = -1;
		}
		
		for (int i = 0; i < N; i++) {
			dataMatrix[0][i] = i;
		}
		for (int i = 0; i < custMilkshakes.length; i++) {
			for (int j = 0; j < custMilkshakes[i].length; j++) {
				String[] shakeData = custMilkshakes[i][j].split(" ");
				int shakeNum = Integer.parseInt(shakeData[0])-1;
				int shakeType = Integer.parseInt(shakeData[1]);
				if (dataMatrix[i+1][shakeNum] != -1) throw new RuntimeException("same flavour");
				dataMatrix[i+1][shakeNum] = shakeType;
				dataMatrix[i+1][N] = i+1;
			}
		}
		//System.out.println("init matrix:");
		//PrintUtils.printlnIntArray2D(dataMatrix);
		//System.out.println("-");
		/*int deletedCustomers = 0;
		for (int i = 0; i < custMilkshakes.length; i++) {
			if (custMilkshakes[i].length == 1) {
				int shakeNum = Integer.parseInt(custMilkshakes[i][0].split(" ")[0]) - 1;
				int shakeType = Integer.parseInt(custMilkshakes[i][0].split(" ")[1]);
				if (result[shakeNum] == -1){
					result[shakeNum] = shakeType;
				} else if (result[shakeNum] != shakeType) {
					System.out.println("impossible first stage");
					return "IMPOSSIBLE";
				}
				dataMatrix = ArrayUtils.deleteRowFromMatrix(dataMatrix, i+1-deletedCustomers);
				deletedCustomers++;
				int colToDelete = findColByHeader(dataMatrix,shakeNum);
				if (colToDelete != -1) {
					dataMatrix = ArrayUtils.deleteColumnFromMatrix(dataMatrix, colToDelete);
				}
			}
		}
		PrintUtils.printlnIntArray2D(dataMatrix);
		System.out.println("- initial delete finished");*/
		//delete all customers with single choice:
		boolean singleValuesFinished = false;
EXTERNAL:
		while (!singleValuesFinished){
			for (int i = 1; i <= dataMatrix.length-2; i++) {
				int colToDelete = singleValueColumn(dataMatrix[i]);
				if (colToDelete !=-1) {
					for (int j = dataMatrix.length-2; j>i;j--) {
						int col2ToDelete = singleValueColumn(dataMatrix[j]);
						if (col2ToDelete == colToDelete){
							if (dataMatrix[j][col2ToDelete] != dataMatrix[i][colToDelete]) {
								System.out.println("impossible2");
								return "IMPOSSIBLE";
							} else {
								dataMatrix = ArrayUtils.deleteRowFromMatrix(dataMatrix, j);
							}
						} else if (dataMatrix[j][colToDelete] == dataMatrix[i][colToDelete]) {
							dataMatrix = ArrayUtils.deleteRowFromMatrix(dataMatrix, j);
						}
					}
					result[dataMatrix[0][colToDelete]] = dataMatrix[i][colToDelete];
					dataMatrix = ArrayUtils.deleteRowFromMatrix(dataMatrix, i);
					for (int j = i-1; j>=1;j--) {
						if (dataMatrix[j][colToDelete] == result[dataMatrix[0][colToDelete]]) {
							dataMatrix = ArrayUtils.deleteRowFromMatrix(dataMatrix, j);
						}
						
					}
					dataMatrix = ArrayUtils.deleteColumnFromMatrix(dataMatrix, colToDelete);
					//System.out.println("1 single value delete done:");
					//PrintUtils.printlnIntArray2D(dataMatrix);
					continue EXTERNAL;
				}
			}
			singleValuesFinished = true;
		}
		System.out.println("All single value deletes done:");
		PrintUtils.printlnIntArray2D(dataMatrix);
		System.out.println("-");		
		
		if (dataMatrix.length == 2) {
			replaceRestWith0(result);
			return resultToString(result);
		}
		
		
		//recursive delete of all definite columns:
		
		int nextColToDelete = -1;
		while ((nextColToDelete = markDefiniteColumns(dataMatrix)) !=-1) {
			//PrintUtils.printlnIntArray2D(dataMatrix);
			//System.out.println("nextColToDelete =" + nextColToDelete);
			result[dataMatrix[0][nextColToDelete]] = dataMatrix[dataMatrix.length-1][nextColToDelete];
			dataMatrix = deleteColAndAllCustomers(dataMatrix, nextColToDelete);		
			if (dataMatrix.length == 2) {
				replaceRestWith0(result);
				return resultToString(result);
			}
		}
		System.out.println("final matrix:");
		PrintUtils.printlnIntArray2D(dataMatrix);
		System.out.println("-");			
		
		if (dataMatrix[0].length != 2) {
			replaceRestWith0(result);
		} else {
			return "IMPOSSIBLE";
		}
		
		System.out.println(resultToString(result));
		return resultToString(result);
	}
	
	
	private static int[][] deleteColAndAllCustomers(int[][] dataMatrix, int nextColToDelete) {
		List<Integer> rowsToDelete = new ArrayList<>();
		for (int i = 1; i <= dataMatrix.length-2; i++) {
			if (dataMatrix[i][nextColToDelete] != -1 ){
				rowsToDelete.add(i);
			}
		}
		dataMatrix = ArrayUtils.deleteColumnFromMatrix(dataMatrix, nextColToDelete);
		while (!rowsToDelete.isEmpty()){
			int row = rowsToDelete.get(rowsToDelete.size()-1);
			rowsToDelete.remove(rowsToDelete.size()-1);
			dataMatrix = ArrayUtils.deleteRowFromMatrix(dataMatrix, row);
		}
		return dataMatrix;
	}




	private static int markDefiniteColumns(int[][] dataMatrix) {
		int lastColWithDefinitSum =-1;
		for (int i = 0; i < dataMatrix[0].length-1; i++) {
			int logicSum = -2;
			for (int j = 1; j<=dataMatrix.length-2; j++) {
				if (dataMatrix[j][i] != -1) {
					if (logicSum == -2) {
						logicSum = dataMatrix[j][i]; 
					} else if (dataMatrix[j][i] != logicSum) {
						logicSum = -1;
						break;
					}
						 
				}
			}
			if (logicSum == -2) {
				logicSum = 0;
			}
			dataMatrix[dataMatrix.length-1][i] = logicSum;
			if (logicSum !=-1) {
				lastColWithDefinitSum =i;
			}
		}
		return lastColWithDefinitSum;
	}

	private static String resultToString(int[] result) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < result.length; i++) {
			sb.append(result[i] + " ");
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}


	private static void replaceRestWith0(int[] result) {
		for (int i = 0; i < result.length; i++) {
			if (result[i] == -1) result[i] = 0;
		}
	}


	private static int singleValueColumn(int[] row) {
		int counter = 0;
		int lastIdxWithValue = -1;
		for (int i = 0; i < row.length-1; i++) {
			if (row[i] != -1) {
				counter++;
				if (counter == 2) return -1;
				lastIdxWithValue = i;
			}
		}
		return lastIdxWithValue;
	}



	@SuppressWarnings("unused") 
	private static int findColByHeader(int[][] dataMatrix, int shakeNum) {
		for (int i = 0; i < dataMatrix[0].length-1; i++) {
			if (dataMatrix[0][i] == shakeNum){
				return i;
			}
		}
		return -1;
	}


	public static String resolveOneCaseOld(int N, String[][] custMilkshakes) {
		Customer[] customers = new Customer[custMilkshakes.length];
		for (int i = 0; i < custMilkshakes.length; i++) {
			customers[i] = new Customer();
			customers[i].favoriteShakes = new Milkshake[custMilkshakes[i].length];
			for (int j = 0; j < custMilkshakes[i].length; j++) {
				String[] shakeData = custMilkshakes[i][j].split(" ");
				customers[i].favoriteShakes[j] = new Milkshake(shakeData[0], shakeData[1]);
				customers[i].custNumber = i;
			}
		}
		
		
		return "IMPOSSIBLE";
	}

	

}

