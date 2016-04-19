public class Cannibals {
    public int minCrossings(int M, int C, int R) {
        int crossings = 0;

        Side startSide = new Side(M, C);
        Side endSide = new Side(0, 0);

        int prev = Integer.MAX_VALUE;
        while(startSide.count() > 0 && prev != startSide.count()) {
            prev = startSide.count();

            // Have people ride the boat to endSide
            Trip going = new Trip(0, 0, R);
            ride(startSide, going);

            if(startSide.count() > 0) {
                // return trip
                Trip returning = new Trip(0, 0, R);
                ride(endSide, returning);
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
            return this.M + addM >= this.C + addC;
        }
    }

    public class Trip {
        public int M = 0;
        public int C = 0;
        public final int R;

        public Trip(int M, int C, int R) {
            this.M = M;
            this.C = C;
            this.R = R;
        }

        public boolean isValid(int addM, int addC) {
            if(!(this.R >= this.C + addC + this.M + addM)) {
                return false;
            }

            if(this.M + addM < this.C + addC) {
                return false;
            }

            return true;
        }
    }
}
