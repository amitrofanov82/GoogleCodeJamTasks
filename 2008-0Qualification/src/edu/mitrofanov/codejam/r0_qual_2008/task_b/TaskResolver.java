package edu.mitrofanov.codejam.r0_qual_2008.task_b;

import java.util.TreeSet;

import edu.mitrofanov.codejam.utils.PrintUtils;
import edu.mitrofanov.codejam.utils.StringUtils;

public class TaskResolver {
	private final static int ARRIVAL = (int) (Math.random()*100);
	private final static int DEPARTURE = ARRIVAL + 1;

	public static String resolveOneCase(int T, String[] aSchedule, String[] bSchedule) {
		int aResult = 0;
		int bResult = 0;
		TreeSet<ScheduleItem> aStack = new TreeSet<>();
		TreeSet<ScheduleItem> bStack = new TreeSet<>();
		
		for (int i =0; i < aSchedule.length; i++){
			String dTimeAsString = aSchedule[i].split(" ")[0];
			String aTimeAsString = aSchedule[i].split(" ")[1];
			int dTime = stringTimeToMinutesPlusTurnover(dTimeAsString, 0);
			int aTime = stringTimeToMinutesPlusTurnover(aTimeAsString, T);
			aStack.add(new ScheduleItem(dTime, DEPARTURE));
			bStack.add(new ScheduleItem(aTime, ARRIVAL));
		}
		for (int i =0; i < bSchedule.length; i++){
			String dTimeAsString = bSchedule[i].split(" ")[0];
			String aTimeAsString = bSchedule[i].split(" ")[1];
			int dTime = stringTimeToMinutesPlusTurnover(dTimeAsString, 0);
			int aTime = stringTimeToMinutesPlusTurnover(aTimeAsString, T);
			aStack.add(new ScheduleItem(aTime, ARRIVAL));
			bStack.add(new ScheduleItem(dTime, DEPARTURE));
		}
		
		System.out.println("Turnover is: " + T);
		PrintUtils.printlnArray(aSchedule);
		PrintUtils.printlnArray(bSchedule);
		StringUtils.printlnCollection(aStack);
		StringUtils.printlnCollection(bStack);
		
		int aAvailableTrains = 0;
		int bAvailableTrains = 0;
		
		while (!aStack.isEmpty()) {
			ScheduleItem sItem = aStack.pollFirst();
			if (sItem.type == ARRIVAL) {
				aAvailableTrains++;
			} else if (aAvailableTrains > 0) {
				aAvailableTrains--;
			} else {
				aResult++;
			}
		}
		
		while (!bStack.isEmpty()) {
			ScheduleItem sItem = bStack.pollFirst();
			if (sItem.type == ARRIVAL) {
				bAvailableTrains++;
			} else if (bAvailableTrains > 0) {
				bAvailableTrains--;
			} else {
				bResult++;
			}
		}
		
		System.out.println(aResult + " " + bResult);
		System.out.println("--------------");
		return aResult + " " + bResult;
	}

	
	private static int stringTimeToMinutesPlusTurnover(String dTimeAsString, int T) {
		return Integer.parseInt(dTimeAsString.split(":")[0]) * 60 + Integer.parseInt(dTimeAsString.split(":")[1]) + T;
	}


	private static class ScheduleItem implements Comparable<ScheduleItem>{
		int time;
		int type;

		@Override
		public int compareTo(ScheduleItem o) {
			if (this.time == o.time && this.type == o.type) {
				return this.hashCode() - o.hashCode();
			}
			
			if (this.time == o.time) {
				return this.type - o.type;
			}
			return this.time - o.time;
		}
		
		ScheduleItem(int time, int type){
			this.time = time;
			this.type = type;
		}
		
		@Override
		public String toString() {
			return (type == ARRIVAL ? "Arrive at " : "Departure at ") + time + " = " + time/60 + ":" + time%60;
		}
	} 

}

