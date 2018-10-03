package edu.mitrofanov.codejam.utils;

import java.util.Collection;
import java.util.Iterator;

public class StringUtils {
	
	public static String arrayToString(Object[] array) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < array.length; i++) {
			sb.append(array[i].toString() + " | ");
		}
		sb.append("]");
		return sb.toString();
	}
	
	public static void printlnCollection(Collection<?> array) {
		System.out.print("[");
		Iterator<?> it = array.iterator();
		while (it.hasNext()) {
			System.out.print(it.next().toString() + "|");
		}
		System.out.println("]");
	}
	
}
