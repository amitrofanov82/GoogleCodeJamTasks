package edu.mitrofanov.codejam.r1a_2008.task_c;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class TaskResolver {
	
	
	public static String resolveOneCase(int n) {
		/*MathContext mc = new MathContext(0, RoundingMode.FLOOR);
		
		BigDecimal base = new BigDecimal("5.2360679774997896964091736687313");
		BigDecimal answer = base;
		for (int i = 2; i <=n; i++) {
			BigDecimal newAnswer = base.pow(i, mc);
			//System.out.println("" + answer + " * " + base + " = " + newAnswer);
			answer = newAnswer;
		}
		System.out.println(n + " : " + base.pow(n, mc).remainder(new BigDecimal(1000), mc));*/

		
		int a0 = 1, b0=0;
		int an = a0;
		int bn = b0;
		
		for (int i = 1; i<=n; i++) {
			int prevA = an;
			int prevB = bn;
			an = (3*prevA  + 5*prevB)%1000;
			bn = ((3*prevB) + prevA)%1000;
			//System.out.println(i + " : " + base.pow(i, mc));
			//System.out.print("a" + i + "=" +an + ", " + "b" + i + "=" +bn + "  | ");
			//System.out.println("2*ai = " +(2*an));
		}

		System.out.println(n + ":" + (2*an - 1) % 1000);
		return String.format("%03d",  (2*an - 1) % 1000);
	}

	

	public static String resolveOneCaseSimple(int n) {
		MathContext mc = new MathContext(0, RoundingMode.FLOOR);
		
		BigDecimal base = new BigDecimal("5.2360679774997896964091736687313");
		BigDecimal answer = base;
		for (int i = 2; i <=n; i++) {
			BigDecimal newAnswer = base.pow(i, mc);
			//System.out.println("" + answer + " * " + base + " = " + newAnswer);
			answer = newAnswer;
		}
		
		System.out.println(n + " : " + base.pow(n, mc).remainder(new BigDecimal(1000), mc));
		
		return String.format("%03d", answer.remainder(new BigDecimal(1000), mc).longValue());
		//return String.format("%03d", result);
	}

}
