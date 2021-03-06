package edu.mitrofanov.codejam.r0_qual_2008.task_c;

import java.text.DecimalFormat;

public class TaskResolver {
	final static int SOME_CONST = (int) (Math.random()*100);
	final static DecimalFormat df = new DecimalFormat("0.000000");
	
	public static String resolveOneCase(double f, double R, double t, double r, double g) {
		
		int killedQty = 0;
		int passedQty = 0;
		int outsideCircle = 0;
		double step = R/20000;
		for (double hPos = 0; hPos < R; hPos+=step) {
			for (double vPos = 0; vPos < R; vPos+=step) {
				if (!inCircle(hPos,vPos,R)) {
					outsideCircle++;
					continue;
				}
				if (killed(f, R, t, r, g, hPos, vPos)) {
					killedQty++;
				} else {
					passedQty++;
				}
			}
		}
		
		if (killedQty + passedQty == 0) {
			return "n/a";
		}
		System.out.println("killedQty =" + killedQty + ", passedQty = " + passedQty + ", outside = " + outsideCircle
				+ ", probability = " + df.format((double)killedQty/(killedQty + passedQty)));
		 
		
		return "" + df.format((double)killedQty/(killedQty + passedQty));
		
	}
	
	private static boolean inCircle(double hPos, double vPos, double R) {
		return hPos*hPos + vPos*vPos <= R*R;
	}

	private static boolean killed(double f, double R, double t, double r, double g, double hPos, double vPos) {
		
		if (centerInString(r, g, hPos) || centerInString(r, g, vPos)) {
			return true;
		}
		
		double leftStringLine = prevLine(r, g, hPos);
		double rightStringLine = nextLine(r, g, hPos);
		double upStringLine = nextLine(r, g, vPos);
		double downStringLine = prevLine(r, g, vPos);
		
		if ( hPos + f > rightStringLine
				|| hPos - f < leftStringLine
				|| vPos + f > upStringLine
				|| vPos - f < downStringLine) {
			return true;
		}
		
		if (killedByObod(hPos, vPos, R, t, f)) {
			return true;
		}
		
		return false;
	}

	private static double nextLine(double r, double g, double pos) {
		return prevLine(r,g,pos) + g;
	}
	
	private static double prevLine(double r, double g, double pos) {
		double d = 2*r;
		pos = pos - r;
		return Math.floor(pos/(g+d))*(g+d) + r;
	}

	private static boolean centerInString(double r, double g, double pos) {
		if (pos <= r) {return true;}
		double d = 2*r;
		pos = pos - r;
		pos = pos/(g+d) - Math.floor(pos/(g+d));
		if (pos >= g/(g+d)) {
			return true;
		}
		return false;
	}
	
	private static boolean killedByObod(double hPos, double vPos, double R, double t, double f) {
		if (hPos*hPos + vPos*vPos >= (R-t-f)*(R-t-f)){ //do not consider out of circle
			return true;
		}
		return false;
	}



}

