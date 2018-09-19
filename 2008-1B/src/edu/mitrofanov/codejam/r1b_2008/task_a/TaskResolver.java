package edu.mitrofanov.codejam.r1b_2008.task_a;

public class TaskResolver {

	public static String resolveOneCase(long n, long a, long b, long c, long d, long x0, long y0, long m) {
		Point[] treePoints = getTrees( n, a, b, c, d, x0, y0, m);
		long result = 0; 
		for (int i = 0; i < treePoints.length-2; i++) {
			for (int j = i+1; j < treePoints.length-1; j++) {
				for (int k = j+1; k < treePoints.length; k++) {
					result+=checkTriangle(treePoints[i], treePoints[j], treePoints[k]);
				} 
			} 
		} 
		return "" + result;
	}

	
	
	
	
	
	
	private static Point[] getTrees(long n, long a, long b, long c, long d, long x0, long y0, long m) {
		Point[] result = new Point[(int)n];
		long X = x0;
		long Y = y0; 
		result[0] = new Point(X,Y);
		System.out.println(X + " " + Y);
		for (int i = 1; i<=n-1; i++) {
			X = (a * X + b) % m;
			Y = (c * Y + d) % m;
			System.out.println(X + " " + Y);
			result[i] = new Point(X,Y);
		}
		return result;
	}
	
	private static int checkTriangle(Point p1, Point p2, Point p3) {
		if ((p1.x + p2.x + p3.x) % 3 != 0 ) {
			return 0;
		}
		if ((p1.y + p2.y + p3.y) % 3 != 0 ) {
			return 0;
		}
		return 1;
	}
	
	static class Point {
		long x, y;
		
		Point (long x, long y){
			this.x = x;
			this.y = y;
		}
	}

}
