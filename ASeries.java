/**
 * Source: Topcoder - TCCC '04 Qual. 2
 * Category: Medium
 * Division: 1
 * Points: 300
 * Passes System Test: true
 * @author Jose Taira
 */
public class ASeries {
    public int longest(int[] values) {
        java.util.Arrays.sort(values);

        int max = values[values.length - 1] - values[0];
        int maxCount = 1;
        for(int d = 0; d <= max; d++) {
            for(int s = 0; s < values.length - 1; s++) {
                int prev = s;
                int currCount = 1;

                for(int i = s + 1; i < values.length; i++) {
                    if(values[i] - values[prev] == d) {
                        prev = i;
                        currCount++;
                    } else if(values[i] - values[prev] > d) {
                        break;
                    }
                }

                if(maxCount < currCount) {
                    maxCount = currCount;
                }
            }
        }

        return maxCount;
    }
}
