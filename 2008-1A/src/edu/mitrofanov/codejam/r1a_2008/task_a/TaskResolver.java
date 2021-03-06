package edu.mitrofanov.codejam.r1a_2008.task_a;

import java.math.BigDecimal;
import java.util.Arrays;

import edu.mitrofanov.codejam.utils.PrintUtils;

public class TaskResolver {

	public static String resolveOneCase(int n, String[] xV, String[] yV) {
		System.out.println("" + n);
		PrintUtils.printlnArray(xV);
		PrintUtils.printlnArray(yV);
		 
		long[] xVint = new long[xV.length];
		long[] yVint = new long[xV.length];
		for (int i = 0; i < xVint.length; i++) {
			xVint[i] = Long.parseLong(xV[i]);
		}
		for (int i = 0; i < yVint.length; i++) {
			yVint[i] = Long.parseLong(yV[i]);
		}
		Arrays.sort(xVint);
		Arrays.sort(yVint);
		
		BigDecimal minProduct = new BigDecimal(0);
		for (int i = 0; i < xVint.length; i++) {
			BigDecimal temp = new BigDecimal(xVint[i] * yVint[n-1-i] + 0.0);
			minProduct = minProduct.add(temp);
			//minProduct.add(xVint[i] * yVint[n-1-i]);
			//minProduct+= xVint[i] * yVint[n-1-i];
		}
		
		
		System.out.println(minProduct.toEngineeringString());
		System.out.println("------------------");
		return "" + minProduct;
	}

}
