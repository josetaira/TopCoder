/**
 * Source: Topcoder - SRM253
 * Category: Hard
 * Points: 1000
 * Passes System Test: true
 * @author Jose Taira
 */
public class ABCPath {
    public static final char BASE_CHAR = 'A';

    public int length(String[] grid) {
        int max = 0;
        for(int i = 0; i < grid.length; i++) {
            String currStr = grid[i];
            for(int j = 0; j < currStr.length(); j++) {
                if(BASE_CHAR == currStr.charAt(j)) {
                    int count = 1 + countAdjacentNext(grid, j, i, BASE_CHAR);
                    if(max < count) {
                        max = count;
                    }
                }
            }
        }

        return max;
    }

    private int countAdjacentNext(String[] grid, int strIdx, int gridIdx, char base) {
        char nextStr = (char) (base + 1);

        int[] directions = new int[]{0, 0, 0, 0, 0, 0, 0, 0};

        // try traverse up:
        if(gridIdx > 0 && nextStr == (grid[gridIdx - 1].charAt(strIdx))) {
            directions[0] = 1 + countAdjacentNext(grid, strIdx, gridIdx - 1, nextStr);
        }

        // try traverse upLeft:
        if(gridIdx > 0 && strIdx > 0 && nextStr == (grid[gridIdx - 1].charAt(strIdx - 1))) {
            directions[1] = 1 + countAdjacentNext(grid, strIdx - 1, gridIdx - 1, nextStr);
        }

        // try traverse upRight:
        if(gridIdx > 0 && strIdx + 1 < grid[gridIdx].length() && nextStr == (grid[gridIdx - 1].charAt(strIdx + 1))) {
            directions[2] = 1 + countAdjacentNext(grid, strIdx + 1, gridIdx - 1, nextStr);
        }

        // try traverse left:
        if(strIdx > 0 && nextStr == (grid[gridIdx].charAt(strIdx - 1))) {
            directions[3] = 1 + countAdjacentNext(grid, strIdx - 1, gridIdx, nextStr);
        }

        // try traverse right:
        if(strIdx + 1 < grid[gridIdx].length() && nextStr == (grid[gridIdx].charAt(strIdx + 1))) {
            directions[4] = 1 + countAdjacentNext(grid, strIdx + 1, gridIdx, nextStr);
        }

        // try traverse down:
        if(gridIdx + 1 < grid.length && nextStr == (grid[gridIdx + 1].charAt(strIdx))) {
            directions[5] = 1 + countAdjacentNext(grid, strIdx, gridIdx + 1, nextStr);
        }

        // try traverse downLeft:
        if(gridIdx + 1 < grid.length && strIdx > 0 && nextStr == (grid[gridIdx + 1].charAt(strIdx - 1))) {
            directions[6] = 1 + countAdjacentNext(grid, strIdx - 1, gridIdx + 1, nextStr);
        }

        // try traverse downRight:
        if(gridIdx + 1 < grid.length && strIdx + 1 < grid[gridIdx].length() && nextStr == (grid[gridIdx + 1].charAt(strIdx + 1))) {
            directions[7] = 1 + countAdjacentNext(grid, strIdx + 1, gridIdx + 1, nextStr);
        }

        int max = 0;
        for(int i = 0; i < directions.length; i++) {
            if(max < directions[i]) {
                max = directions[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println("26 : " + new ABCPath().length(new String[]{ "AMNOPA", "ALEFQR", "KDABGS", "AJCHUT", "AAIWVA", "AZYXAA" }));
    }
}