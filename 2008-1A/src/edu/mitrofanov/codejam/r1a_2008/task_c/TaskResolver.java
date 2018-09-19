package edu.mitrofanov.codejam.r1a_2008.task_c;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class TaskResolver {
	
	

	

	public static String resolveOneCase(int n) {
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
