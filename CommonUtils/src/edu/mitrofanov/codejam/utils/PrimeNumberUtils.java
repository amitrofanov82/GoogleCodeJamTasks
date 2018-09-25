package edu.mitrofanov.codejam.utils;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class PrimeNumberUtils {
	
	public static List<Integer> preInitializedOrderedPrimeSet = null;
	public static BitSet preInitializedEratosthenesSieve = null;
	
	/** call this method to improve further performance if multiple operations expected */
	public static void initializeGlobalPrimeSetAndSieve(int N) {
		preInitializedEratosthenesSieve = eratosthenesSieve(N);
		preInitializedOrderedPrimeSet = orderedPrimes(N, 2);
	};
	
	public static boolean isPrime(long number) {
		if (preInitializedEratosthenesSieve !=null 
				&& preInitializedEratosthenesSieve.size() >= number+1){
			return !preInitializedEratosthenesSieve.get((int) number);
		}
		if (number == 1){
			return false;
		}
		if (number == 2){
			return true;
		}
		if (number % 2 == 0){
			return false;
		}
		long limit = ((long) Math.sqrt(number)); //k*k<=n. Then +1 is not needed
		for (int i=3; i<=limit; i+=2){
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}

	/** false is prime */
	public static BitSet eratosthenesSieve(int upToNumber){
		BitSet result = new BitSet(upToNumber+1);
		result.set(0); result.set(1); //set true = cross-out
		for (int i = 4; i<=upToNumber; i+=2){
			result.set(i);
		}
		long limit = ((long) Math.sqrt(upToNumber)); //k*k<=n. Then +1 is not needed
		for (int i = 3; i<=limit; i+=2) {
			if (!result.get(i)){ //not crossed out yet
				// cross out every i*j starting from i*i:
				for(int j=i*i; j<=upToNumber; j+=i){
					result.set(j);
				}
			}
		}
		
		return result;
	}
	
	public static List<Integer> orderedPrimes(int upToNumber, int ignoreLessThan) {
		List<Integer> primes = new ArrayList<>(upToNumber/18);
		BitSet allPrimes;
		if(preInitializedEratosthenesSieve !=null
				&& preInitializedEratosthenesSieve.size() >= upToNumber + 1){
			allPrimes = preInitializedEratosthenesSieve;
		} else {
			allPrimes = PrimeNumberUtils.eratosthenesSieve(upToNumber);
		}
		if (ignoreLessThan < 2) {
			ignoreLessThan = 2;
		}
		for (int i = ignoreLessThan; i<=upToNumber; i++){
			if (!allPrimes.get(i)){
				primes.add(i);
			}
		}
		return primes;
	}
	
	/** without prime factor multiplicity */
	public static Set<Integer> getPrimeFactorSet(int n, int ignoreGreaterThan) {
		if (ignoreGreaterThan<=0) {
			ignoreGreaterThan = n;
		}
		Set<Integer> primeFactors = new TreeSet<>();
		if (isPrime(n)) {
			return primeFactors;
		}
		List<Integer> primesList;
		if (preInitializedOrderedPrimeSet != null 
				&& preInitializedOrderedPrimeSet.get(preInitializedOrderedPrimeSet.size() -1) > n/2) {
			primesList = preInitializedOrderedPrimeSet;
		} else {
			primesList = orderedPrimes(n/2, 0);
		}
		int counter = 0;
		int primeListSize = primesList.size();
		int currentPrime;
		while ((counter < primeListSize) && (currentPrime = primesList.get(counter++)) <= n/2 
				&& currentPrime <= ignoreGreaterThan){
			if (n % currentPrime == 0) {
				primeFactors.add(currentPrime);
			}
			
		}
		return primeFactors;
	}
}
