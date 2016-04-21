/**
 * Source: TopCoder - TCCC '03 Semifinals 3
 * Difficulty: Medium
 * Division: 1
 * Points: 300
 * Passes System Test: true
 * @author Jose Taira
 */
public class ZigZag {

    /*
     * Summary:
     * 0. Create two arrays `up` and `down` which will be the "score" (subsequence count) containers
     * 1. For each integer in seq, check which movement going to it (going down to it or going up to it)
     *    has the highest subsequence count, from all integers before it in seq
     * 2. The maximum score for all of this is returned.
     */
    public int longestZigZag(int[] seq) {
        int[] up = new int[seq.length];
        int[] down = new int[seq.length];

        int currMax = 1;
        up[0] = 1;
        down[0] = 1;
        for(int i = 1; i < seq.length; i++) {
            for(int j = i - 1; j >= 0; j--) {
                if(seq[i] - seq[j] > 0) {
                    up[i] = Math.max(down[j] + 1, up[i]);
                } else if(seq[i] - seq[j] < 0) {
                    down[i] = Math.max(up[j] + 1, down[i]);
                } else {
                    down[i] = Math.max(down[i], down[j]);
                    up[i] = Math.max(up[i], up[j]);
                }
            }

            int tempMax = Math.max(down[i], up[i]);
            currMax = Math.max(currMax, tempMax);
        }

        return currMax;
    }

}
