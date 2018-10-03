package edu.mitrofanov.codejam.r1b_2008.task_b;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import edu.mitrofanov.codejam.utils.PrimeNumberUtils;

public class TaskResolver {
	
	static {
		PrimeNumberUtils.initializeGlobalPrimeSetAndSieve(1000002);
	}

	public static String resolveOneCase(long a, long b, long p) {
		long startTime = System.currentTimeMillis();
 		List<Integer> primes = PrimeNumberUtils.orderedPrimes((int)(b-a+1), p>2000000 ? 2000000 : (int) p);
		List<TreeSet<Long>> sets = new ArrayList<>(primes.size());
		for (int i = 0; i < primes.size(); i++) {
			int prime = primes.get(i);
			//prime*k>a; k>a/prime; kmin=ceil(a/prime):
			long from = prime * (a/prime);
			from = from < a ? from + prime : from;
			TreeSet<Long> currentSet = new TreeSet<>();
			for (long numWithPrime = from; numWithPrime <=b; numWithPrime+=prime) {
				currentSet.add(numWithPrime);
			}
			if (currentSet.size() >1 ) {
				sets.add(currentSet);
			}
			
		}
		System.out.println("phase1 finish");
		long phase1Time = System.currentTimeMillis();
		//all sets are mapped to appropriate integer keys. We can merge all that contain same numbers:
		System.out.println("sets: " + sets.size());
		//System.out.println("set0 size: " + sets.get(0).size());
		for (int i=0; i<sets.size(); i++){
			List<Integer> setsToMerge = new ArrayList<>();
			for (int j=i+1; j<sets.size(); j++){
				boolean merge = !disjoint(sets.get(i), sets.get(j));
				if (merge) {
					setsToMerge.add(j);
				}
				
			}
			if (!setsToMerge.isEmpty()) {
				mergeSets(sets, setsToMerge, i);
				i--;
			}
		}
		System.out.println("phase2 phinish");
		long phase2Time = System.currentTimeMillis();
		//calculate how many numbers are not in any merged sets:
		int mergedSetsSize = 0;
		for (TreeSet<Long> treeSet : sets) {
			mergedSetsSize+=treeSet.size();
		}
		long phase3Time = System.currentTimeMillis();
		System.out.println("phase1-start: " + (phase1Time -startTime));
		System.out.println("phase2-phase1: " + (phase2Time -phase1Time));
		System.out.println("phase3-phase2: " + (phase3Time -phase2Time));
		return "" + (sets.size() + (b-a+1 - mergedSetsSize));
	}

	private static boolean disjoint(Set<Long> treeSet1, Set<Long> treeSet2) {
		Set<Long> smallTree = treeSet1.size() > treeSet2.size() ? treeSet2 : treeSet1;
		Set<Long> bigTree = treeSet1.size() > treeSet2.size() ? treeSet1 : treeSet2;
		for (Long num : smallTree) {
			if (bigTree.contains(num)) {
				return false;
			}
		}
		return true;
	}
	
	private static void mergeSets(List<TreeSet<Long>> sets, List<Integer> setsToMerge, Integer mergeInto) { //assert setsToMerge is sorted;
		Set<Long> mergedSet =  sets.get(mergeInto);
		int delCounter = 0;
		for (int i = 0; i < setsToMerge.size(); i++) {
			int currentIdx = setsToMerge.get(i) - delCounter;
			mergedSet.addAll(sets.get(currentIdx));
			sets.remove(currentIdx);
			delCounter++;
		}
	}

}
