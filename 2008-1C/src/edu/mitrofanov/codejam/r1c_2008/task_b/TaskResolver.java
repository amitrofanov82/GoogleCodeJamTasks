package edu.mitrofanov.codejam.r1c_2008.task_b;


public class TaskResolver {

	public static String resolveOneCase(String caseInput) {
		long result = 0;
		
		if (caseInput.length()== 1) {
			int number = caseInput.charAt(0) - '0'; 
			if (number % 2 == 0 || number % 3 == 0 || number % 5 == 0 || number % 7 == 0) {
				result ++;
			}
			return "" + result;
		}
		
		char[] inputchars = caseInput.toCharArray();
		long combinations = ((Double) Math.pow(3, caseInput.length()-1)).longValue();
		
		for (long combi = 0; combi < combinations; combi++) {
			String s = String.format("%"+ (caseInput.length()-1) +"s", Long.toString(combi, 3)).replace(' ', '0');
			char[] partition = s.toCharArray();
			long[] sumElements = splitStringNumber(inputchars, partition); 
			long sum = 0;
			for (long el : sumElements) {
				sum+=el;
			}
			
			if (sum % 2 == 0 || sum % 3 == 0 || sum % 5 == 0 || sum % 7 == 0) {
				result ++;
			}
			
		}
		 
		return "" + result;
	}

	private static long[] splitStringNumber(char[] caseInput, char[] partition) {
		int merges = 0;
		for (char c : partition) {
			if (c == '0') merges++;
		}
		
		long[] result = new long[caseInput.length - merges];
		
		int prevSign = 1;
		long currentNum = caseInput[0] - '0';
		int resultIdx=0;
		for (int i=1; i<= caseInput.length - 1; i++) {
			if (partition[i-1] == '0') {
				currentNum = (currentNum*10 + (caseInput[i] - '0'));
			} else {
				result[resultIdx++] = (prevSign * currentNum) % 210;
				currentNum = caseInput[i] - '0';
				prevSign = partition[i-1] == '1' ? 1 : -1;
			}
		}
		result[resultIdx] = (prevSign * currentNum) % 210;
		return result;
	}

}
