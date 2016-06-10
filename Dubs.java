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

        c += getCount(L, Math.min(Ln,R));
        // Handle OFF-BY-ONE
        c += getCount(Math.max(Ln, Rn) + 1, R);

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
        return N % 100 % 11 == 0;
    }

    public static void main(String[] args) {
        Dubs d = new Dubs();
        System.out.println("1 : " + d.count(10, 20));
        System.out.println("6 : " + d.count(49, 101));
        System.out.println("6 : " + d.count(50, 100));
        System.out.println("1 : " + d.count(10, 20));
    }
}
