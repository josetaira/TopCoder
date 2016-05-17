/**
 * Source: Topcoder - TCO '03 Semifinals 4
 * Category: Easy
 * Points: 250
 * Passes System Test: true
 * @author Jose Taira
 */
public class AvoidRoads {

    /**
     * To go to [x,y] using exactly x + y steps,
     * you must come from [x-1, y] or [x, y-1] (assuming these are valid locations)
     */
    public long numWays(int width, int height, String[] bad) {
        long[][] ways = new long[width + 1][height + 1];

        java.util.List<BadDirection> BAD_DIRECTIONS = new java.util.ArrayList<BadDirection>();
        for(int i = 0; i < bad.length; i++) {
            String[] coord = bad[i].split(" ");
            int xa = Integer.valueOf(coord[0]);
            int yb = Integer.valueOf(coord[1]);
            int xc = Integer.valueOf(coord[2]);
            int yd = Integer.valueOf(coord[3]);
            BAD_DIRECTIONS.add(new BadDirection(xa, yb, xc, yd));
        }

        for(int x = 0; x <= width; x++) {
            for(int y = 0; y <= height; y++) {

                if(x == 0 && y == 0) {
                    ways[x][y] = 1;
                    continue;
                }

                if (x > 0 && !isBadDirection(BAD_DIRECTIONS, x - 1, y, x, y)) {
                    ways[x][y] += ways[x - 1][y];
                }

                if (y > 0 && !isBadDirection(BAD_DIRECTIONS, x, y - 1, x, y)) {
                    ways[x][y] += ways[x][y - 1];
                }
            }
        }
        return ways[width][height];
    }

    private boolean isBadDirection(java.util.List<BadDirection> badDirections, int xa, int yb, int xc, int yd) {
        for(int i = 0; i < badDirections.size(); i++) {
            if(badDirections.get(i).isBad(xa, yb, xc, yd)) {
                return true;
            }
        }
        return false;
    }

    public static class BadDirection {
        private int xa;
        private int yb;
        private int xc;
        private int yd;

        public BadDirection(int xa, int yb, int xc, int yd) {
            this.xa = xa;
            this.yb = yb;
            this.xc = xc;
            this.yd = yd;
        }

        private boolean isBad(int xa, int yb, int xc, int yd) {
            return (this.xa == xa && this.yb == yb && this.xc == xc && this.yd == yd)
                    || (this.xc == xa && this.yd == yb && this.xa == xc && this.yb == yd);
        }
    }

}
