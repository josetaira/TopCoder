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
        }

        if(Ln < R && L < Rn) {
            c += getCount(L, Ln);
            // Handle OFF-BY-ONE
            c += getCount(Rn + 1, R);
        } else {
            c += getCount(L, R);
        }

        return c;
    }

    private long getCount(long from, long to) {
        int c = 0;

        for (long i = from; i <= to; i++) {
            if (isDoubleNum(i)) {
                c++;
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
