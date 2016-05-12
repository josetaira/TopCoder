/**
 * Source: Topcoder - TCO '03 Semifinals 4
 * Category: Easy
 * Points: 250
 * Passes System Test: true
 * @author Jose Taira
 */
public class AvoidRoads {
    public long numWays(int width, int height, String[] bad) {
        long[][] ways = new long[width + 1][height + 1];

        // Max of around 100MB
        boolean[][][][] bads = new boolean[width + 1][height + 1][width + 1][height + 1];
        for(int i = 0; i < bad.length; i++) {
            String[] coord = bad[i].split(" ");
            int xa = Integer.valueOf(coord[0]);
            int yb = Integer.valueOf(coord[1]);
            int xc = Integer.valueOf(coord[2]);
            int yd = Integer.valueOf(coord[3]);
            bads[xa][yb][xc][yd] = true;
            bads[xc][yd][xa][yb] = true;
        }

        for(int x = 0; x <= width; x++) {
            for(int y = 0; y <= height; y++) {

                if(x == 0 && y == 0) {
                    ways[x][y] = 1;
                    continue;
                }

                if (x > 0 && !bads[x - 1][y][x][y]) {
                    ways[x][y] += ways[x - 1][y];
                }

                if (y > 0 && !bads[x][y - 1][x][y]) {
                    ways[x][y] += ways[x][y - 1];
                }
                //System.out.println("ways[" + x + "][" + y + "] = " + ways[x][y]);
            }
        }
        return ways[width][height];
    }
/*
    public static void main(String[] args) {
        System.out.println("2 : " + new AvoidRoads().numWays(1, 1, new String[]{}));
        System.out.println("0 : " + new AvoidRoads().numWays(2, 2, new String[]{"0 0 1 0", "1 2 2 2", "1 1 2 1"}));
        System.out.println("6406484391866534976 : " + new AvoidRoads().numWays(35, 31, new String[]{}));
    }
*/
}
