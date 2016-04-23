/**
 * Source: TopCoder - SRM 689
 * Difficulty: Medium
 * Division: 2
 * Points: 500
 * Passes System Test: true
 * @author Jose Taira
 */
public class NonDeterministicSubstring {
    public int ways(String A, String B) {
        int c = 0;

        java.util.List<String> found = new java.util.ArrayList<String>();
        int ln = A.length() - B.length() + 1;
        for(int i = 0; i < ln; i++) {
            boolean valid = true;
            String curr = A.substring(i, i + B.length());
            for(int b = 0; b < B.length(); b++) {
                if(B.charAt(b) == '?') {
                    continue;
                }

                if(curr.charAt(b) != B.charAt(b)) {
                    valid = false;
                    break;
                }
            }
            if(valid && !found.contains(curr)) {
                found.add(curr);
                c++;
            }
        }

        return c;
    }
}
