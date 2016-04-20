import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Source: TopCoder - SRM 305
 * Difficulty: Hard
 * Division: 2
 * Points: 1000
 * Passes System Test: true
 * @author Jose Taira
 */
public class Cannibals {
    /**
     * Summary: Starting with the intial state, you can move to multiple states through your list of valid moves.
     * Using these valid moves, do a breadth-first search for the state (0,0) -> (M,C)
     *
     * 0. Create a list of valid moves
     * 1. Create a queue and add the initial state to it
     * 2. For each state in the queue, and each valid move:
     *      - check if the new state formed after the move is valid. If so, enqueue new state
     *      - check if the new state is not an already visited state for the current direction. If it is, skip.
     * 3. Repeat #2 until there are no more new items in the queue, or the state (0,0) is found.
     * 4. If state (0,0) is never found, return -1
     *
     * @param M
     * @param C
     * @param R
     * @return
     */
    public int minCrossings(int M, int C, int R) {

        Side startSide = new Side(M, C);
        List<Move> validMoves = new ArrayList<Move>();
        for(int c = 0; c <= R; c++) {
            for(int m = 0; m <= R; m++) {
                if(m + c >= 1 && m + c <= R && (m == 0 || m >= c)) {
                    validMoves.add(new Move(m, c));
                }
            }
        }

        int direction = -1;
        int level = 0;
        Queue<Side> sideStates = new LinkedList<Side>();
        sideStates.add(startSide);
        List<Side> visitedGoingStates = new ArrayList<Side>();
        List<Side> visitedEndingStates = new ArrayList<Side>();
        while(sideStates.size() > 0) {
            level++;

            List<Side> targetCache = direction == -1 ? visitedGoingStates : visitedEndingStates;
            List<Side> otherCache = direction == -1 ? visitedEndingStates : visitedGoingStates;

            List<Side> newStates = new ArrayList<Side>();
            for(int levelSize = sideStates.size(); levelSize > 0; levelSize--) {
                Side currSide = sideStates.remove();

                if(targetCache.contains(currSide)) {
                    continue;
                } else {
                    targetCache.add(currSide);
                }

                for (int i = 0; i < validMoves.size(); i++) {
                    Move move = validMoves.get(i);
                    Side newSideState = currSide.clone();
                    newSideState.add(direction * move.M, direction * move.C);


                    if(newSideState.isValid()) {
                        if(newSideState.count() == 0) {
                            return level;
                        }
                        if(!otherCache.contains(newSideState)) {
                            if(!newStates.contains(newSideState)) {
                                newStates.add(newSideState);
                            }
                        }
                    }
                }
            }

            sideStates.addAll(newStates);

            direction *= -1;
        }

        if(startSide.count() > 0) {
            return -1;
        }

        return level;
    }

    public class Side implements Cloneable {
        public int M = 0;
        public int C = 0;

        private final int initM;
        private final int initC;

        public Side(int M, int C) {
            this.M = M;
            this.C = C;

            this.initM = M;
            this.initC = C;
        }

        public int count() {
            return this.M + this.C;
        }

        public boolean isValid() {
            if(this.M > this.initM || this.M < 0) {
                return false;
            }

            if(this.C > this.initC || this.C < 0) {
                return false;
            }

            return ((this.M == 0)
                    || (this.M >= this.C))
                    && ((this.initM - this.M == 0) || (this.initM - this.M >= this.initC - this.C));
        }

        public void add(int addM, int addC) {
            this.M += addM;
            this.C += addC;
        }

        @Override
        protected Side clone() {
            Side otherSide = new Side(this.initM, this.initC);
            otherSide.M = this.M;
            otherSide.C = this.C;
            return otherSide;
        }

        @Override
        public String toString() {
            // (M,C):(otherM, otherC)
            return new StringBuilder("(").append(this.M).append(",")
                    .append(this.C).append("):(").append(this.initC - this.C)
                    .append(",").append(this.initM - this.M)
                    .append(this.initC - this.C).append(")")
                    .toString();
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Side) {
                Side otherSide = (Side) obj;
                if(this.M == otherSide.M
                        && this.C == otherSide.C
                        && this.initM == otherSide.initM
                        && this.initC == otherSide.initC) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public int hashCode() {
            int hash = 31 * this.initM;
            hash += 17 * this.initC;
            hash += 11 * this.M;
            hash += 7 * this.C;

            return hash;
        }
    }
    public class Move {
        public int M = 0;
        public int C = 0;

        public Move(int M, int C) {
            this.M = M;
            this.C = C;
        }

        @Override
        public String toString() {
            return new StringBuilder("<").append(this.M).append(",")
                    .append(this.C).append(">").toString();
        }
    }
}
