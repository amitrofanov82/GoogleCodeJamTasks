package edu.mitrofanov.codejam.r1b_2008.task_b;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class TaskResolverForSmallDataset {


	public static String resolveOneCase(long a, long b, long p) {
		LinkedList<Set<Long>> sets = new LinkedList<>(); 

		for (long i = a; i <= b; i++) {
			Set<Long> primeFactors = findPrimesFactors(i, p);
			//System.out.println("new prime factors: " + primeFactors);
			List<Integer> setsToMerge = new ArrayList<>();
			for (int setNum = 0; setNum<sets.size(); setNum++) {
				Set<Long> tempSet = new HashSet<>();
				tempSet.addAll(sets.get(setNum));
				//System.out.print(tempSet +  " X " + primeFactors + " = ");
				tempSet.retainAll(primeFactors);
				//System.out.println(tempSet);
				if (!tempSet.isEmpty()) {
					setsToMerge.add(setNum);
				}
			}
			//System.out.println("Current sets: " + sets);
			//System.out.println("Sets to merge: " + setsToMerge);
			if (setsToMerge.isEmpty()) {
				sets.add(primeFactors);
			} else {
				mergeSets(sets, setsToMerge, primeFactors);
			}
			//System.out.println("------");
			
		}
		
		
		return "" + sets.size();
	}
	
	private static void mergeSets(LinkedList<Set<Long>> sets, List<Integer> setsToMerge, Set<Long> primeFactors) { //assert setsToMerge is sorted;
		//System.out.println("before merge: " + sets);
		//System.out.println("merging sets: " + setsToMerge);
		Set mergedSet =  sets.get( setsToMerge.get(0) );
		int delCounter = 0;
		for (int i = 1; i < setsToMerge.size(); i++) {
			int currentIdx = setsToMerge.get(i) - delCounter;
			mergedSet.addAll(sets.get(currentIdx));
			sets.remove(currentIdx);
			delCounter++;
		}
		mergedSet.addAll(primeFactors);
		//System.out.println("merge results: " + sets);
	}


	private static Set<Long> findPrimesFactors(long number, long p) { //includes itself
		Set<Long> result = new TreeSet<>();
		for (long j = p; j <= number/2; j++) {
			if (number%j==0 && isPrime(j)) {
				result.add(j);
			}
		}
		if (result.isEmpty()) {
			result.add(number);
		}
		return result;
	}

	private static boolean isPrime(long number) {
		for (long i = 2; i <= number/2; i++) {
			if (number%i == 0) {
				return false;
			}
		}
		return true;
	}

	

}
