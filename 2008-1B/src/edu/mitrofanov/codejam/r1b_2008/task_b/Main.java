package edu.mitrofanov.codejam.r1b_2008.task_b;

import java.io.File;
import java.util.Scanner;

import edu.mitrofanov.codejam.utils.FileUtils;

public class Main {
	//public final static String INPUT_FILE = "resources/B-test.in";
	//public final static String OUTPUT_FILE = "resources/B-test.out";
	//public final static String INPUT_FILE = "resources/B-small-practice.in";
	//public final static String OUTPUT_FILE = "resources/B-small-practice.out";
	public final static String INPUT_FILE = "resources/B-large-practice.in";
	public final static String OUTPUT_FILE = "resources/B-large-practice.out";

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new File(INPUT_FILE));
		int T = Integer.parseInt(sc.nextLine());
		String[] output = new String[T];
		
		for (int i =0; i< T; i++) {
			String line = sc.nextLine();
			String[] input = line.split(" ");
			output[i] = "Case #" + (i+1) + ": " + TaskResolver.resolveOneCase(
						Long.parseLong(input[0]),
						Long.parseLong(input[1]),
						Long.parseLong(input[2])
					);
			System.out.println(output[i]);
		}
		FileUtils.writeStringArrayToFile(output, OUTPUT_FILE);
	}
	
	
	
}
