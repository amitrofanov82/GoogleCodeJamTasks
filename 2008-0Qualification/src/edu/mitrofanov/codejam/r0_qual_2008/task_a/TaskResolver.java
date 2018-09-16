package edu.mitrofanov.codejam.r0_qual_2008.task_a;

public class TaskResolver {

	public static String resolveOneCase(String[] engines, String[] queries) {
		int position = -1; 
		int switches = -1;
		while(position < queries.length) {
			position = goAsFarAsYouCan(engines, queries, position);
			switches++;
		}
		return String.valueOf(switches);
	}

	private static int goAsFarAsYouCan(String[] engines, String[] queries, int position) {
		int result = position;
		for (String engine : engines){
			int newPosition = position+1;
			while (newPosition < queries.length && !queries[newPosition].equals(engine)){
				newPosition++;
			}
			if (newPosition == queries.length) return newPosition;
			if (newPosition-1 > result) result = newPosition-1;
		}
		return result;
	}

}
