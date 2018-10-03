package edu.mitrofanov.codejam.r1c_2008.task_a;

import java.io.File;
import java.util.Scanner;

import edu.mitrofanov.codejam.utils.FileUtils;

public class Main {
	//public final static String INPUT_FILE = "resources/A-test.in";
	//public final static String OUTPUT_FILE = "resources/A-test.out";
	//public final static String INPUT_FILE = "resources/A-small-practice.in";
	//public final static String OUTPUT_FILE = "resources/A-small-practice.out";
	public final static String INPUT_FILE = "resources/A-large-practice.in";
	public final static String OUTPUT_FILE = "resources/A-large-practice.out";

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new File(INPUT_FILE));
		int T = Integer.parseInt(sc.nextLine());
		String[] output = new String[T];
		
		for (int i =0; i< T; i++) {
			String[] pks = sc.nextLine().split(" ");
			int p = Integer.parseInt(pks[0]);
			int k = Integer.parseInt(pks[1]);
			int l = Integer.parseInt(pks[2]);
			String[] frAsString =  sc.nextLine().split(" ");
			int[] frequency = new int[l];
			for (int j = 0; j < frequency.length; j++) {
				frequency[j] = Integer.parseInt(frAsString[j]);
			}
			output[i] = "Case #" + (i+1) + ": " + TaskResolver.resolveOneCase(p, k, l, frequency);
			System.out.println(output[i]);
		}
		FileUtils.writeStringArrayToFile(output, OUTPUT_FILE);
	}
	
	
	
}
