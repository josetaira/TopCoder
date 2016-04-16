/**
 * TCO16 Algorithm 2B
 * Difficult: Easy
 * Points: 500
 * Passed System Test: True
 * @author Jose Taira
 */
public class ReplacingDigit {

    /**
     * Summary:
     * 1. Get the highest possible value we can replace with. Can't? We're done.
     * 2. Get the smallest digit at the highest place value
     * 3. Replace the smallest digit with the value from Step 1.
     * 4. Repeat Step 1.
     * @param A
     * @param D
     * @return
     */
    public int getMaximumStockWorth(int[] A, int[] D) {
        int total = 0;
        java.util.Arrays.sort(A);

        int exp = 6;

        while(exp >= 0) {
            int maxNewValue = popMaxNewReplacement(D);
            if(maxNewValue == -1) {
                // When this happens, there's no more "stickers" to use
                break;
            }

            int lowestDigit = 10;
            int targetIdx = -1;
            int pow = (int) Math.pow(10, exp);
            for (int i = A.length - 1; i >= 0; i--) {
                int shifted = A[i] / pow;
                if(shifted > 0 && (shifted % 10) < lowestDigit) {
                    lowestDigit = shifted % 10;
                    targetIdx = i;
                }
            }
            if(targetIdx != -1) {

                if(maxNewValue - lowestDigit > 0) {
                    // If there's a way to improve the current value, do so:
                    A[targetIdx] += (maxNewValue - lowestDigit) * pow;
                } else {
                    // Else, put back the value we took, since we're not using it.
                    D[maxNewValue - 1]++;
                    exp--;
                }
            } else {
                // Put back the largest value:
                D[maxNewValue - 1]++;
                exp--;
            }
        }

        for(int i = 0; i < A.length; i++) {
            total += A[i];
        }

        return total;
    }

    /**
     * Gets the maximum digit among the remaining values.
     *
     * If you don't use the digit taken here, you need to put it back
     *
     * @param D
     * @return
     */
    public int popMaxNewReplacement(int[] D) {
        int d = 9;
        while(d > 0 && D[d - 1] == 0) d--;
        if(d > 0) {
            D[d - 1]--;
            return d;
        }
        return -1;
    }

}
