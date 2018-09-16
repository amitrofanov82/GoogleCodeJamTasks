package edu.mitrofanov.codejam.r0_qual_2008.task_a;

import java.io.File;
import java.util.Scanner;

import edu.mitrofanov.codejam.utils.FileUtils;

public class Main {
	public final static String INPUT_FILE = "resources/A-large-practice.in";
	public final static String OUTPUT_FILE = "resources/A-large-practice.out";

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new File(INPUT_FILE));
		int N = Integer.parseInt(sc.nextLine());
		
		String[] output = new String[N];
		for (int i =0; i< N; i++) {
			int engNum = Integer.parseInt(sc.nextLine());
			String[] engines = new String[engNum];
			for (int j =0; j< engNum; j++) {
				engines[j] = sc.nextLine();
			}
			int queryNum = Integer.parseInt(sc.nextLine());
			String[] queries = new String[queryNum];
			for (int j =0; j < queryNum; j++) {
				queries[j] = sc.nextLine();
			}
			output[i] = "Case #" + (i+1) + ": " + TaskResolver.resolveOneCase(engines, queries);
		}
		FileUtils.writeStringArrayToFile(output, OUTPUT_FILE);
	}
	
}
