package edu.mitrofanov.codejam.r1a_2008.task_a;

import edu.mitrofanov.codejam.utils.FileUtils;

public class Main {
	public final static String INPUT_FILE = "resources/A-large-practice.in";
	public final static String OUTPUT_FILE = "resources/A-large-practice.out";

	public static void main(String[] args) throws Exception {
		String[] input = FileUtils.readFileAsStringArray(INPUT_FILE);
		int cases = Integer.parseInt(input[0]);
		String[] output = new String[cases];
		for (int i =0; i < cases; i++) {
			int n = Integer.parseInt(input[i*3 + 1]);
			String[] xV = input[i*3 + 2].split(" ");
			String[] yV = input[i*3 + 3].split(" ");
			output[i] = "Case #" + (i+1) + ": " + TaskResolver.resolveOneCase(n, xV, yV);
		}
		FileUtils.writeStringArrayToFile(output, OUTPUT_FILE);
		
		
	}
	
}
