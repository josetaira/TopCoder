import java.util.HashSet;
import java.util.Set;

/**
 * Source: TopCoder - SRM 694
 * Difficulty: Hard
 * Division: 2
 * Points: 500
 * Passes System Test: true
 * @author Jose Taira
 */
public class DistinguishableSetDiv2 {
    public int count(String[] answer) {
        int ln = answer[0].length();
        long lim = 1 << ln;
        int dist = 0;

        // Brute force through all 2^M possibilities of choices:
        for(long i = 1; i < lim; i++) {
            boolean valid = true;
            Set<String> existing = new HashSet<String>();

            // Construct the current answer set for person p:
            for(int p = 0; p < answer.length && valid; p++) {
                StringBuilder sb = new StringBuilder();

                long currSubset = i;
                for(int r = 0; r < ln; r++) {
                    if((currSubset & (1 << r)) > 0) {
                        sb.append(answer[p].charAt(r));
                    }
                }

                // Becomes false if the item being added already exists
                // in the HashSet
                valid = existing.add(sb.toString());
            }

            if(valid) {
                dist++;
            }
        }
        return dist;
    }
}
