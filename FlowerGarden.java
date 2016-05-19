/**
 * Source: Topcoder - 2004 TCCC Online Round 1 - Division I, Level Two
 * Category: Medium
 * Points: 500
 * Passes System Test: true
 * @author Jose Taira
 */
import java.util.ArrayList;
import java.util.List;

public class FlowerGarden {

    /**
     * For each iteration, look for the leftmost element you can place with the
     * highest height.
     * @param height
     * @param bloom
     * @param wilt
     * @return
     */
    public int[] getOrdering(int[] height, int[] bloom, int[] wilt) {
        List<Integer> handled = new ArrayList<>();

        for(int m = 0; m < height.length; m++) {
            int maxIdx = -1;
            for(int i = 0; i < height.length; i++) {
                if(handled.contains(i)) continue;
                boolean valid = true;
                for(int j = 0; j < height.length; j++) {
                    if(i == j) continue;

                    if(handled.contains(j)) continue;

                    if((height[i] > height[j] && (bloom[i] <= wilt[j] && bloom[j] <= wilt[i]))) {
                        valid = false;
                        break;
                    }
                }

                if(valid) {
                    if(maxIdx == -1) {
                        maxIdx = i;
                    } else if(height[maxIdx] < height[i]) {
                        maxIdx = i;
                    }
                }
            }
            if(maxIdx > -1) {
                handled.add(maxIdx);
            }
        }

        int[] ans = new int[height.length];
        for(int x = 0; x < height.length; x++) {
            ans[x] = height[handled.get(x)];
        }

        return ans;
    }

}

