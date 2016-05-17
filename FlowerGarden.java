/**
 * Source: Topcoder - 2004 TCCC Online Round 1 - Division I, Level Two
 * Category: Medium
 * Points: 500
 * Passes System Test: false
 * @author Jose Taira
 */
public class FlowerGarden {

    public int[] getOrdering(int[] height, int[] bloom, int[] wilt) {
        // 0 <= j < i < height.length
        for(int i = 1; i < height.length; i++) {
            int maxJ = -1;
            for(int j = i - 1; j >= 0; j--) {
                // It must swap if height[j] > height[i]
                // AND their scheds intersect
                if ((height[i] > height[j] && !(bloom[i] <= wilt[j] && bloom[j] <= wilt[i])) ||
                        (height[i] < height[j] && (bloom[i] <= wilt[j] && bloom[j] <= wilt[i]))) {
                    maxJ = j;
                } else if(height[i] > height[j] && (bloom[i] <= wilt[j] && bloom[j] <= wilt[i])){
                    break;
                }
            }

            if(maxJ > -1) {
                int tempH = height[i];
                int tempB = bloom[i];
                int tempW = wilt[i];

                System.out.println("j-" + maxJ + " and i-" + i);
                for(int k = i - 1; k >= maxJ; k--) {
                    height[k + 1] = height[k];
                    bloom[k + 1] = bloom[k];
                    wilt[k + 1] = wilt[k];
                }

                height[maxJ] = tempH;
                bloom[maxJ] = tempB;
                wilt[maxJ] = tempW;
            }

            printArr(height);
        }
        return height;
    }

    public static void printArr(int[] arr) {
        System.out.print("[");
        for(int i = 0; i < arr.length; i++) {
            if(i > 0) {
                System.out.print(", ");
            }
            System.out.print(arr[i]);
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        FlowerGarden fg = new FlowerGarden();

        //System.out.print("[1, 2, 3, 4, 5] >> ");
        printArr(fg.getOrdering(new int[]{5, 4, 3, 2, 1}, new int[]{1, 5, 10, 15, 20}, new int[]{5, 10, 15, 20, 25}));

        System.out.println("Expected: {2, 4, 6, 1, 3, 5} >>>");
        printArr(fg.getOrdering(
                new int[]{1, 2, 3, 4, 5, 6},
                new int[]{1, 3, 1, 3, 1, 3},
                new int[]{2, 4, 2, 4, 2, 4}));

    }
}
