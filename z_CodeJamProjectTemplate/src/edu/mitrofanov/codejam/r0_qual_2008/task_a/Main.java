package edu.mitrofanov.codejam.r0_qual_2008.task_a;

import edu.mitrofanov.codejam.utils.FileUtils;

public class Main {
	public final static String INPUT_FILE = "resources/taskA.in";
	public final static String OUTPUT_FILE = "resources/taskA.out";

	public static void main(String[] args) throws Exception {
		String[] input = FileUtils.readFileAsStringArray(INPUT_FILE);
		String[] output = new String[input.length-1];
		for (int i =1; i< input.length; i++) {
			output[i-1] = "Case #" + i + ": " + TaskResolver.resolveOneCase(input[i]);
		}
		FileUtils.writeStringArrayToFile(output, OUTPUT_FILE);
		
		
	}
	
}
