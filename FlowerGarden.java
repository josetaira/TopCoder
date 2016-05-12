/**
 * Source: Topcoder - 2004 TCCC Online Round 1 - Division I, Level Two
 * Category: Medium
 * Points: 500
 * Passes System Test: true
 * @author Jose Taira
 */
public class FlowerGarden {

    public int[] getOrdering(int[] height, int[] bloom, int[] wilt) {
        boolean swapped = true;
        int j = 0;
        while(swapped) {
            swapped = false;
            for(int i = 0; i < height.length - j - 1; i++) {
                if((height[i] < height[i + 1] && !(bloom[i] <= wilt[i + 1] && bloom[i + 1] <= wilt[i])) ||
                   (height[i] > height[i + 1] && (bloom[i] <= wilt[i + 1] && bloom[i + 1] <= wilt[i]))) {
                    // swap:
                    int temp = height[i + 1];
                    height[i + 1] = height[i];
                    height[i] = temp;

                    temp = bloom[i + 1];
                    bloom[i + 1] = bloom[i];
                    bloom[i] = temp;

                    temp = wilt[i + 1];
                    wilt[i + 1] = wilt[i];
                    wilt[i] = temp;

                    swapped = true;
                }
            }
            j++;
        }
        return height;
    }
}
