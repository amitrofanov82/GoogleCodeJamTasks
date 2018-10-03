package edu.mitrofanov.codejam.r1c_2008.task_a;

import java.util.Arrays;

public class TaskResolver {

	public static String resolveOneCase(int P, int K, int L, int[] frequency) {
		Arrays.sort(frequency);
		long result = 0;
		int multiplier;
		for (int letter = 0; letter < L; letter++){
			multiplier = (letter / K ) +1;
			result += frequency[frequency.length - 1 - letter] * (multiplier);

		}
		return "" + result;
	}

}

