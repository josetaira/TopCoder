/**
 * Source: TopCoder - SRM 691
 * Difficulty: Medium
 * Division: 2
 * Points: 500
 * Passes System Test: true
 * @author Jose Taira
 */
public class Sunnygraphs2 {

    /**
     * Graphs will be made with 1 or more connected components.
     *
     * Each component can have a cycle (c), and/or a tail (t).
     *
     * If a component has a tail, total valid choices are:
     * = (2^t) x ((2^c) - 1)
     *
     * Get the product of the valid choices for each connected component (to ensure the result
     * will be connected, you must choose at least 1 from each connected component).
     *
     * However, if there's only 1 connected component at the start, add 1 to the total
     * because the subset {} is a valid choice.
     *
     *
     * @param a
     * @return long
     */
    public long count(int[] a) {
        boolean[] roots = new boolean[a.length];
        int[] total_ln = new int[a.length];
        int[] cycle_ln = new int[a.length];

        QuickUnion qu = new QuickUnion(a.length);

        for(int i = 0; i < a.length; i++) {
            qu.union(i, a[i]);
        }

        for(int i = 0; i < qu.vertx.length; i++) {
            boolean[] visited = new boolean[a.length];

            int c = i;
            while(!visited[c] && !roots[c]) {
                visited[c] = true;
                c = qu.vertx[c];
            }

            roots[c] = true;
        }



        for(int i = 0; i < qu.vertx.length; i++) {

            int c = i;
            while(!roots[c]) {
                c = qu.vertx[c];
            }

            total_ln[c]++;
        }

        int cc = 0;
        for(int i = 0; i < roots.length; i++) {
            if(roots[i]) {
                cc++;
                cycle_ln[i]++;

                int c = qu.vertx[i];
                while(c != i) {
                    c = qu.vertx[c];
                    cycle_ln[i]++;
                }
            }
        }

        long t = 0;
        for(int i = 0; i < total_ln.length; i++) {
            if(total_ln[i] > 0) {

                int tail = total_ln[i] - cycle_ln[i];
                long curr = (long)(Math.pow(2, cycle_ln[i]) - 1);
                if(tail != 0) {
                    curr *= (long)(Math.pow(2, tail));
                }

                if(t == 0) {
                    t = curr;
                } else {
                    t *= curr;
                }
            }
        }

        if(cc == 1) {
            t += 1;
        }

        return t;
    }

    private class QuickUnion {
        private int[] vertx;

        public QuickUnion(int ln) {
            this.vertx = new int[ln];

            for(int i = 0; i < ln; i++) {
                this.vertx[i] = i;
            }
        }

        private void union(int i, int j) {
            vertx[i] = j;
        }
    }
}
