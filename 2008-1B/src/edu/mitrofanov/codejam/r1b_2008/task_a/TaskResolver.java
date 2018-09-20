package edu.mitrofanov.codejam.r1b_2008.task_a;

import edu.mitrofanov.codejam.utils.PrintUtils;

public class TaskResolver {
	/*private final static int M0_0 = 0;
	private final static int M0_1 = 1;
	private final static int M0_2 = 2;
	private final static int M1_0 = 3;
	private final static int M1_1 = 4;
	private final static int M1_2 = 5;
	private final static int M2_0 = 6;
	private final static int M2_1 = 7;
	private final static int M2_2 = 8;*/
	
	public static String resolveOneCase(long n, long a, long b, long c, long d, long x0, long y0, long m) {
		long[] treePointsData = getTreesData( n, a, b, c, d, x0, y0, m);
		long result = 0; 
		PrintUtils.printlnIntArray(treePointsData);
		System.out.println(n);
		/*possible cases when (x1+x2+x3)%3 = 0 (without permutations):
		 *  0 0 0  => M0_? * 3
		 *  1 1 1  => M1_? * 3
		 *  2 2 2  => M2_? * 3
		 *  0 1 2  => M0_? M1_? M2_?
		 *  
		 *  same for (y1+y2+y3)%3
		 */
		for (int i = 0; i<9; i++){
			for (int j = i; j<9; j++){
				for (int k = j; k<9; k++){
					int xkmod = k/3;
					int xjmod = j/3;
					int ximod = i/3;
					int ykmod = k%3;
					int yjmod = j%3;
					int yimod = i%3;
					
					if ((xkmod + xjmod + ximod)%3 == 0 &&  (ykmod + yjmod + yimod)%3 == 0) {
						System.out.println("indexes:" + i + " " + j + " " + k + ":");
						System.out.println("xmods:"  + ximod + " " + xjmod + " " + xkmod + ":");
						System.out.println("ymods:"  + yimod + " " + yjmod + " " + ykmod + ":");
						System.out.println("points:"+treePointsData[i] + " " + treePointsData[j] + " " + treePointsData[k]);
						if (i == j && j==k) {
							long z = treePointsData[i];
							if (z<3) {
								continue;
							}
							System.out.println("adding " + z*(z-1)*(z-2)/6);
							result+= z*(z-1)*(z-2)/6; 
						}  else {
							System.out.println("adding " + treePointsData[i]*treePointsData[j]*treePointsData[k]);
							result += treePointsData[i]*treePointsData[j]*treePointsData[k];
						}
					}
				}
			}
		}
		
		return "" + result;
	}

	private static long[] getTreesData(long n, long a, long b, long c, long d, long x0, long y0, long m) {
		long[] result = new long[9];
		long X = x0;
		long Y = y0; 
		result[pointToIndex(x0, y0)]++;
		for (int i = 1; i<=n-1; i++) {
			X = (a * X + b) % m;
			Y = (c * Y + d) % m;
			result[pointToIndex(X, Y)]++;
		}
		return result;
	}

	private static int pointToIndex(long x0, long y0){
		return (int) ((x0%3)*3 + y0%3);
	}

	
//-------------------
//for small case only:	
	public static String resolveOneCaseForSmall(long n, long a, long b, long c, long d, long x0, long y0, long m) {
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
