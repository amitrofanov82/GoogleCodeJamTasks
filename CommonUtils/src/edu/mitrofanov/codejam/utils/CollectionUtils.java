package edu.mitrofanov.codejam.utils;

import java.util.List;

public class CollectionUtils {
	
	public static <T extends Comparable<? super T>> int binarySearch(List<T> sortedList, T value,
			int low, int high) {
		int result= -1;
		int l = low, r= high;
		
		while (l<=r){
			int m = (low + high) >>> 1;
			int cmp = sortedList.get(m).compareTo(value);
			if (cmp == 0) {
				result = m;
				break;
			}
			if (cmp > 0) {
				r=m-1;
			} else {
				l=m+1;
			}
		}
		
		return result;
	}
	
	
}
