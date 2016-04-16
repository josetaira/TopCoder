/**
 * 2016 TCO Algo 1A
 * Difficulty: Medium 
 * Points: 500
 * Passes System Test: true
 * @author Jose Taira
 */
public class EllysSocks {

    /**
     * Summary:
     * 0. Sort the array from low to high. This is needed to ensure that the pairings are optimal
     * 1. Given an upper bound distance (UBD) and lower bound distance (LBD),
     *    get their average distance (integer). This average will be the threshold.
     * 2. Check if the distances between consecutive pairings match are lower than the average distance:
     *    - If yes, a pair is made, increment iterator by 2.
     *    - If no, increment iterator by 1
     *    - Repeat until all possible optimal pairs are checked.
     * 3. Check if the minimum required pairs are met.
     *    - If yes, set UBD to current average distance. (decreases the mid for later iterations)
     *    - If no, set LBD to current average distance. (increases the mid for later interations)
     * 4. Repeat 1 while UBD > LBD + 1
     */
    public int getDifference(int[] S, int P) {
        java.util.Arrays.sort(S);
        int low = -1;
        // Maximum possible difference
        int high= 1000000000;
        
        while(low + 1 < high) {
        	int mid = (high + low) / 2;
        	
        	int pairs = 0;
        	for(int i = 0; i < S.length - 1;) {
        		if(S[i + 1] - S[i] <= mid) {
        			i += 2;
        			pairs++;
        		} else {
        			i++;
        		}
        	}
        	
        	// If we were able to produce enough pairs, then adjust the high value,
        	// so mid will go down.
        	if(pairs >= P) {
        		high = mid;
        	} else {
        		// Else, adjust the low value so mid will go up.
        		low = mid;
        	}
        }
        return high;
    }

}