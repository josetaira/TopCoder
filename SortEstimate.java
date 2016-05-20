/**
 * Source: TopCoder - SRM 230
 * Difficulty: Medium
 * Division: 1 and 2
 * Points: 300[DIV1], 600[DIV2]
 * Passes System Test: true
 * @author Jose Taira
 */
public class SortEstimate {
    public double howMany(int c, int time) {

        double top = Double.MAX_VALUE;
        double bot = 0;

        double mid = -1;

        while((top - bot) / top > 1e-9) {
            mid = (top + bot) / 2.0;

            double midTime = runAlgorithmTime(c, mid);
            if(midTime - (double)time > 0) {
                top = mid;
            } else {
                bot = mid;
            }
        }

        return mid;
    }

    private double runAlgorithmTime(int c, double n) {
        return c * n * (Math.log(n) / Math.log(2));
    }

}
