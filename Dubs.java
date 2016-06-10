/**
 * Source: TopCoder - SRM 692
 * Difficulty: Medium
 * Division: 2
 * Points: 500
 * Passes System Test: true
 * @author Jose Taira
 */
public class Dubs {
    public long count(long L, long R) {
        long c = 0;

        long Ln = (long) (Math.ceil(L / 100.0) * 100);
        long Rn = (long) (Math.floor(R / 100.0) * 100);

        if(Rn >= Ln) {
            c += ((Rn - Ln) / 100L) * 10;
            // The first and last hundreds will be double counted by the code below.
            // Just offset them from here.
            if(c > 0) {
                c -= 1;
            }
        }

        if(Ln < R && L < Rn) {
            // Handles off-by-one
            int sub = Ln == Rn ? 1 : 0;
            for (long i = L; i <= Ln - sub; i++) {
                if (isDoubleNum(i)) {
                    c++;
                }
            }

            for (long i = Rn; i <= R; i++) {
                if (isDoubleNum(i)) {
                    c++;
                }
            }
        } else {
            for (long i = L; i <= R; i++) {
                if (isDoubleNum(i)) {
                    c++;
                }
            }
        }

        return c;
    }

    private boolean isDoubleNum(long N) {
        int ones = (int)(N - ((N / 10) * 10));
        int tens = (int)(N - ((N / 100) * 100)) / 10;

        return ones == tens;
    }

}
