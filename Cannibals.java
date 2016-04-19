import java.util.ArrayList;
import java.util.List;

public class Cannibals {
    public int minCrossings(int M, int C, int R) {
        int crossings = 0;

        Side startSide = new Side(M, C);
        Side endSide = new Side(0, 0);

        int prev = Integer.MAX_VALUE;
        Trip t = new Trip(R);

        int[][] states = new int[M][C];

        List<Move> validMoves = new ArrayList<Move>();
        for(int c = 0; c < R; c++) {
            for(int m = 0; m < R; m++) {
                if(m + c >= 1 && (m == 0 || m >= c)) {
                    validMoves.add(new Move(m, c));
                }
            }
        }

        while(startSide.count() > 0 && prev != startSide.count()) {
            prev = startSide.count();

            int maxGoing = -1;
            int x = -1;
            int y = -1;

            for(int c = 0; c < startSide.C; c++) {
                for(int m = 0; m < startSide.M; m++) {
                    // Have people ride the boat to endSide
                    int z = 0;
                    if(t.isValid(m, c)
                       && startSide.isValid(-m, -c)
                       && endSide.isValid(m, c)
                       && m + c >= 1) {
                        z = m + c;
                    }

                    if(z > maxGoing) {
                        maxGoing = z;
                        x = m;
                        y = c;
                    }
                }
            }

            System.out.println(x + " x-y " + y);
            startSide.M -= x;
            startSide.C -= y;

            endSide.M += x;
            endSide.C += y;

            System.out.println("Going trip: ");
            System.out.println("Start Side: " + startSide);
            System.out.println("End Side: " + endSide);

            if(startSide.count() > 0) {
                int minReturning = Integer.MAX_VALUE;
                int xb = -1;
                int yb = -1;

                for(int m = 0; m < endSide.M; m++) {
                    for(int c = 0; c < endSide.C; c++) {
                        int z = Integer.MAX_VALUE;
                        if(t.isValid(m, c)
                          && startSide.isValid(m, c)
                          && endSide.isValid(-m, -c)
                          && (m + c >= 1)) {
                            z = m + c;
                        }

                        if(z < minReturning) {
                            minReturning = z;
                            xb = m;
                            yb = c;
                        }
                    }
                }

                System.out.println(xb + " xb-yb " + yb);
                startSide.M += xb;
                startSide.C += yb;

                endSide.M -= xb;
                endSide.C -= yb;

                System.out.println("Returning trip:");
                System.out.println("Start Side: " + startSide);
                System.out.println("End Side: " + endSide);
            }
        }

        if(startSide.count() > 0) {
            return -1;
        }

        return crossings;
    }

    public void ride(Side s, Trip t) {
        int M = 0;
        int C = 0;


    }

    public class Side {
        public int M = 0;
        public int C = 0;

        public Side(int M, int C) {
            this.M = M;
            this.C = C;
        }

        public int count() {
            return this.M + this.C;
        }

        public boolean isValid(int addM, int addC) {
            return (this.M + addM == 0) || (this.M + addM >= this.C + addC);
        }

        @Override
        public String toString() {
            return new StringBuilder("M - ")
                    .append(this.M)
                    .append(", C - ")
                    .append(this.C)
                    .toString();
        }
    }
    public class Move {
        public int M = 0;
        public int C = 0;

        public Move(int M, int C) {
            this.M = M;
            this.C = C;
        }
    }

    public class Trip {
        public final int R;

        public Trip(int R) {
            this.R = R;
        }

        public boolean isValid(int addM, int addC) {
            if(this.R < addC + addM) {
                return false;
            }

            if(addM > 0 && addM < addC) {
                return false;
            }

            return true;
        }
    }

    public static void main(String[] args) {
        System.out.println("5 : " + new Cannibals().minCrossings(3, 3, 2));
    }
}
