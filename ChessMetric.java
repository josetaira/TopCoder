/**
 * Source: TopCoder - 2003 TCCC Round 4
 * Difficulty: Easy
 * Points: 250
 * Passes System Test: true
 * @author Jose Taira
 */
public class ChessMetric {

    public long howMany(int n, int[] start, int[] end, int m) {
        long[][] count = new long[n][n];

        // Initialize the first seed
        count[start[0]][start[1]] = 1;
        for(int iter = 0; iter < m; iter++) {
            long[][] prevResult = count;
            count = new long[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for(Move move : Move.values()) {
                        int targetX = i + move.x;
                        int targetY = j + move.y;

                        if(targetX >= 0 && targetX < n
                        && targetY >= 0 && targetY < n) {
                            count[targetX][targetY] += prevResult[i][j];
                        }
                    }
                }
            }
        }

        return count[end[0]][end[1]];
    }

    private enum Move {
        LEFT(-1, 0), RIGHT(1, 0), UP(0, 1), DOWN(0, -1),
        UP_LEFT(-1, 1), UP_RIGHT(1, 1), DOWN_LEFT(-1, -1), DOWN_RIGHT(1, -1),
        UUL(-1, 2), UUR(1, 2), ULL(-2, 1), URR(2, 1),
        DDL(-1, -2), DDR(1, -2), DLL(-2, -1), DRR(2, -1);

        public int x;
        public int y;

        Move(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        System.out.println(new ChessMetric().howMany(3, new int[]{0, 0}, new int[]{0, 0}, 2));
        System.out.println(new ChessMetric().howMany(3, new int[]{0, 0}, new int[]{2,2}, 1));
        System.out.println(new ChessMetric().howMany(100, new int[]{0, 0}, new int[]{0, 99}, 50));
    }
}
