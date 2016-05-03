/**
 * Source: TopCoder - SRM 683
 * Difficulty: Easy
 * Division: 2
 * Points: 500
 * Passes System Test: true
 * @author Jose Taira
 */
public class MoveStonesEasy {
    public int get(int[] a, int[] b) {
        int moves = 0;
        for(int i = 0; i < a.length - 1; i++) {
            int diff = b[i] - a[i];

            for(int j = i + 1; j < b.length; j++) {
                if(diff > 0) {
                    int add = Math.min(a[j], diff);
                    a[i] += add;
                    a[j] -= add;
                    diff -= add;
                    moves += add * (j - i);
                } else {
                    diff = -1 * diff;
                    a[i] -= diff;
                    a[j] += diff;
                    moves += diff;
                    diff = 0;
                }
            }

            if(diff != 0) {
                return -1;
            }
        }

        if(a[a.length - 1] != b[b.length-1]) {
            return -1;
        }
        return moves;
    }
}
