package edu.mitrofanov.codejam.r0_qual_2008.task_c;

import java.io.File;
import java.util.Scanner;

import edu.mitrofanov.codejam.utils.FileUtils;

public class Main {
	public final static String INPUT_FILE = "resources/C-test.in";
	public final static String OUTPUT_FILE = "resources/C-test.out";

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new File(INPUT_FILE));
		int N = Integer.parseInt(sc.nextLine());
		
		String[] output = new String[N];
		for (int i =0; i< N; i++) {
			double f, R, t, r, g;
			String[] input = sc.nextLine().split(" ");
			f = Double.parseDouble(input[0]);
			R = Double.parseDouble(input[1]);
			t = Double.parseDouble(input[2]);
			r = Double.parseDouble(input[3]);
			g = Double.parseDouble(input[4]);
			output[i] = "Case #" + (i+1) + ": " 
						+ TaskResolver.resolveOneCase(f, R, t, r, g);
		}
		FileUtils.writeStringArrayToFile(output, OUTPUT_FILE);
	}
	
}
