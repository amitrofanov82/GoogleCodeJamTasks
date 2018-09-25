import java.util.BitSet;
import java.util.Set;

import edu.mitrofanov.codejam.utils.PrimeNumberUtils;


public class PrimeUtilsTest {
	
	public static void main(String[] args) {
		primeFactorsTest();
	}

	/** all primes less than N. About 5 seconds for N=2*10^9 **/
	public static void eratosthenesSieveTest() {
		int N = 2000000000;
		BitSet allPrimes = PrimeNumberUtils.eratosthenesSieve(N);
		int counter = 0;
		for (int i = 2; i<=N; i++){
			if (!allPrimes.get(i)){
				//System.out.println(i);
				counter++;
			}
		}
		System.out.println(counter);
	}
	
	public static void primeFactorsTest(){
		PrimeNumberUtils.initializeGlobalPrimeSetAndSieve(2000000100);
		System.out.println("initialized");
		for (int i = 0; i<10; i++) {
			Set s = PrimeNumberUtils.getPrimeFactorSet(2000000000 + i, 0);
			System.out.println(s);
		}

	}
}
