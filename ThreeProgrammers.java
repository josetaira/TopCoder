import java.util.LinkedList;
import java.util.List;

/**
 * TCO16 Algorithm 1C
 * Difficult: Medium
 * Points: 500
 * Passed System Test: True
 * @author Jose Taira
 */
public class ThreeProgrammers {

    /**
     * A state indicates what the current string is, as well as the
     * remaining number of 'A', 'B', or 'C'
     *
     * Summary:
     * 1. Starting with base state:
     *    a. Create a new state appended with 'A' if there are still writable A's
     *    b. Create a new state appended with 'B' if there are still writable B's
     *       AND the previous character was not 'B'
     *    c. Create a new state appended with 'C' if there are still writable C's
     *       AND the previous two characters were not C's
     * 2. Repeat until the states in the state list have the length needed
     * 3. If at the end, there are no states in the list, return "impossible"
     *
     * @param h
     * @return
     */
    public String validCodeHistory(String h) {
        int A = 0;
        int B = 0;
        int C = 0;
        int ln = h.length();
        for(int i = 0; i < ln; i++) {
            if(h.charAt(i) == 'A') {
                A++;
            } else if(h.charAt(i) == 'B') {
                B++;
            } else {
                C++;
            }
        }

        LinkedList<State> states = new LinkedList<State>();
        State S = new State();
        S.A = A;
        S.B = B;
        S.C = C;

        states.add(S);
        int lns = 1;
        int stateNum = 0;
        while(ln > 0 && stateNum < ln) {
            for(int i = lns - 1; i >= 0; i--) {
                State cs = states.remove();
                char prev = 'X';
                char prev2 = 'X';

                if(stateNum >= 1) {
                    prev = cs.sb.charAt(stateNum - 1);
                }

                if(stateNum >= 2) {
                    prev2 = cs.sb.charAt(stateNum - 2);
                }

                if(cs.A > 0) {
                    State cp = cs.clone();
                    cp.sb.append('A');
                    cp.A--;
                    if(!hasSimilar(states, cp)) {
                        states.add(cp);
                    }
                }

                if(prev != 'B' && cs.B > 0) {
                    State cp = cs.clone();
                    cp.sb.append('B');
                    cp.B--;
                    if(!hasSimilar(states, cp)) {
                        states.add(cp);
                    }
                }
                if(prev != 'C' && prev2 != 'C' && cs.C > 0) {
                    State cp = cs.clone();
                    cp.sb.append('C');
                    cp.C--;
                    if(!hasSimilar(states, cp)) {
                        states.add(cp);
                    }
                }
            }
            lns = states.size();
            stateNum++;
        }

        if(stateNum == ln && states.size() > 0) {
            return states.get(0).sb.toString();
        }

        return "impossible";
    }

    private boolean hasSimilar(List<State> states, State base) {
        for(int i = states.size() - 1; i >= 0; i--) {
            int baseLn = base.sb.length();

            State curr = states.get(i);
            int currLn = curr.sb.length();
            boolean prev1 = true;
            if(currLn > 1) {
                prev1 = curr.sb.charAt(currLn - 1) == base.sb.charAt(baseLn - 1);
            }
            boolean prev2 = true;
            if(currLn > 2) {
                prev2 = ((curr.sb.charAt(currLn - 2)) != 'C' && (base.sb.charAt(currLn - 2)) != 'C')
                  || ((curr.sb.charAt(currLn - 2) == (base.sb.charAt(currLn - 2))));
            }
            if(currLn == baseLn
                    && curr.A == base.A
                    && curr.B == base.B
                    && curr.C == base.C
                    && prev1
                    && prev2) {
                return true;
            }
        }

        return false;
    }

    private class State implements Cloneable {
        public StringBuilder sb = new StringBuilder();
        public int A = 0;
        public int B = 0;
        public int C = 0;

        public State clone() {
            State other = new State();
            other.A = this.A;
            other.B = this.B;
            other.C = this.C;
            other.sb = new StringBuilder(this.sb);
            return other;
        }
    }
}
