package edu.mitrofanov.codejam.r1a_2008.task_b;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CopyPasteWorkingSolution implements Runnable {
	
	public final static String INPUT_FILE = "resources/B-small-practice.in";
	public final static String OUTPUT_FILE = "resources/B-small-practice-stolen.out";

	private BufferedReader in;
	private StringTokenizer st;
	private PrintWriter out;
	
	private void solve() throws IOException {
		int tests = nextInt();
		prog: for (int test = 0; test < tests; ++test) {
			int n = nextInt();
			int m = nextInt();
			boolean[][] a = new boolean[m][n];
			int[] l = new int[m];
			int[] b = new int[m];
			Arrays.fill(b, -1);
			ArrayList<Integer> st = new ArrayList<Integer>();
			boolean[] ans = new boolean[n];
			for (int i = 0; i < m; ++i) {
				int t = nextInt();
				for (int j = 0; j < t; ++j) {
					int u = nextInt() - 1;
					int v = nextInt();
					if (v == 1) {
						b[i] = u;
					} else {
						if (!a[i][u]) {
							l[i]++;
						}
						a[i][u] = ะช;
					}
				}
				if (l[i] == 0) {
					st.add(i);
				}
			}
			while (st.size() > 0) {
				int i = st.remove(st.size() - 1);
				if (b[i] == -1) {
					out.println("Case #" + (test + 1) + ": IMPOSSIBLE");
					continue prog;
				}
				ans[b[i]] = true;
				for (int j = 0; j < m; ++j) {
					if (a[j][b[i]]) {
						a[j][b[i]] = !ะช;
						l[j]--;
						if (l[j] == 0) {
							st.add(j);
						}
					}
				}
			}
			out.print("Case #" + (test + 1) + ": ");
			for (boolean bb : ans) {
				out.print((bb ? 1 : 0) + " ");
			}
			out.println();
		}
	}

	public void run() {
		try {
			in = new BufferedReader(new InputStreamReader(new FileInputStream(INPUT_FILE)));
			st = new StringTokenizer("");
			out = new PrintWriter(new FileOutputStream(OUTPUT_FILE));
			
			solve();
			
			out.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
	}
	
	String next() throws IOException {
		while (!st.hasMoreTokens()) {
			String line = in.readLine();
			if (line == null) {
				return null;
			}
			st = new StringTokenizer(line);
		}
		return st.nextToken();
	}
	
	int nextInt() throws IOException {
		return Integer.parseInt(next());
	}
	
	double nextDouble() throws IOException {
		return Double.parseDouble(next());
	}
	
	long nextLong() throws IOException {
		return Long.parseLong(next());
	}
	
	public static void main(String[] args) {
		new Thread(new CopyPasteWorkingSolution()).start();
	}

	private void ass(boolean b) {
		if (!b) {
			throw new RuntimeException("Assertion failed");
		}
	}
	
	static final boolean ะช = true;
}
