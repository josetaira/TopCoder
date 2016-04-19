import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Source: TopCoder - SRM 688
 * Difficulty: Hard
 * Division: 2
 * Points: 1000
 * Passes System Test: false
 * @author Jose Taira
 */
public class ParenthesesDiv2Hard {

    /**
     * Summary:
     * 0. Sort both L and R (pairwise disjoint ensures that the indices will still be aligned properly
     * 1. Get the substrings that L and R resolve to into a list. Then, get the list of needed corrections for each.
     * 2. For each correction, and each substring:
     *    - check if another correction exactly for the same substring does the reverse. If yes, swap those two
     *    then go to next correction. Increment swap count.
     *    - check if another correctly does the reverse for another substring does the reverse. If yes, swap those
     *    two then go to next correction. Increment swap count.
     *    - check if the needed correction can be found in the remaining strings that are not part of substrings.
     *    If a replacement is found, swap it with the current character and increment swap count. Else, return -1
     * 3. Return swap count
     * @param s
     * @param L
     * @param R
     * @return
     */
    public int minSwaps(String s, int[] L, int[] R) {
        if(s.length() <= 1) {
            return -1;
        }

        Arrays.sort(L);
        Arrays.sort(R);
        StringBuilder sb = new StringBuilder(s);

        int moves = 0;
        List<String> parts = new ArrayList<String>();
        List<List<Integer>> corrections = new ArrayList<List<Integer>>();
        for(int i = L.length - 1; i >= 0; i--) {
            String curr = s.substring(L[i], R[i] + 1);
            List<Integer> currCorrection = correct(curr);
            if(curr.length() % 2 == 1) {
                return -1;
            }
            if(currCorrection.size() > 0) {
                parts.add(curr);
                corrections.add(currCorrection);
            }
            sb.delete(L[i], R[i] + 1);
        }

        List<String> processed = new ArrayList<String>();
        for(int p = parts.size() - 1; p >= 0; p--) {
            List<Integer> currCorrection = corrections.remove(p);
            StringBuilder currPart = new StringBuilder(parts.remove(p));
            for(int c = 0; c < currCorrection.size(); c++) {
                boolean swapped = false;
                char neededChar = currPart.charAt(currCorrection.get(c)) == '(' ? ')' : '(';
                // Check if any of the other corrections in this list are the exact opposite:
                for(int oc = c + 1; oc < currCorrection.size(); oc++) {
                    if(currPart.charAt(currCorrection.get(oc)) == neededChar) {
                        swapped = true;
                        currPart.setCharAt(currCorrection.get(oc), currPart.charAt(currCorrection.get(c)));
                        currPart.setCharAt(currCorrection.get(c), neededChar);
                        currCorrection.remove(oc);
                        moves++;
                        break;
                    }
                }

                if(swapped) continue;

                for(int op = p - 1; op >= 0; op--) {
                    List<Integer> otherCorrection = corrections.get(op);
                    StringBuilder otherPart = new StringBuilder(parts.get(op));

                    for(int oc = otherCorrection.size() - 1; oc >= 0; oc--) {
                        if(otherPart.charAt(otherCorrection.get(oc)) == neededChar) {
                            otherPart.setCharAt(otherCorrection.get(oc), currPart.charAt(currCorrection.get(c)));
                            currPart.setCharAt(currCorrection.get(c), neededChar);
                            otherCorrection.remove(oc);
                            moves++;
                            swapped = true;
                            break;
                        }
                    }

                    if(swapped) break;
                }

                if(swapped) continue;

                for(int baseIdx = 0; baseIdx < sb.length(); baseIdx++) {
                    if(sb.charAt(baseIdx) == neededChar) {
                        sb.setCharAt(baseIdx, currPart.charAt(currCorrection.get(c)));
                        currPart.setCharAt(currCorrection.get(c), neededChar);
                        moves++;
                        swapped = true;
                        break;
                    }
                }

                if(!swapped) {
                    return -1;
                }
            }

            processed.add(0, currPart.toString());
        }

        return moves;
    }

    private List<Integer> correct(String s) {
        java.util.List<Integer> l = new java.util.ArrayList<Integer>();

        int cd = 0;
        for(int i = 0; i < s.length(); i++) {
            char iChar = s.charAt(i);
            if(iChar == ')') {
                if(cd == 0) {
                    l.add(i);
                    cd++;
                } else {
                    cd--;
                }
            } else if(iChar == '(') {
                if(s.length() - 1 - cd - i <= 0) {
                    l.add(i);
                    cd--;
                } else {
                    cd++;
                }
            }
        }

        return l;
    }
}
