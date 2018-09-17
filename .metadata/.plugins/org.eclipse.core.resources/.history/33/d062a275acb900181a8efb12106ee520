package edu.mitrofanov.codejam.r0_qual_2008.task_b;

import java.io.File;
import java.util.Scanner;

import edu.mitrofanov.codejam.utils.FileUtils;

public class Main {
	public final static String INPUT_FILE = "resources/B-large-practice.in";
	public final static String OUTPUT_FILE = "resources/B-large-practice.out";

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new File(INPUT_FILE));
		int N = Integer.parseInt(sc.nextLine());
		
		String[] output = new String[N];
		for (int i =0; i< N; i++) {
			int T = Integer.parseInt(sc.nextLine());
			int NA = sc.nextInt();
			int NB = Integer.parseInt(sc.nextLine().trim());
			String[] aSchedule = new String[NA];
			String[] bSchedule = new String[NB];
			for (int j =0; j<aSchedule.length; j++) {
				aSchedule[j] = sc.nextLine();
			}
			for (int j =0; j<bSchedule.length; j++) {
				bSchedule[j] = sc.nextLine();
			}
			output[i] = "Case #" + (i+1) + ": " + TaskResolver.resolveOneCase(T, aSchedule, bSchedule);
		}
		FileUtils.writeStringArrayToFile(output, OUTPUT_FILE);
	}
	
}
