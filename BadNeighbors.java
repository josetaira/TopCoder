/**
 * Source: TopCoder - 2004 TCCC Online Round 4
 * Difficulty: Easy
 * Division: 1
 * Points: 250
 * Passes System Test: true
 * @author Jose Taira
 */
import java.util.*;
public class BadNeighbors {
	/**
	 * Start in {0, 1}
	 *
	 * An index N can only be entered from N-2 or N-1. If it enters from N-3,
	 * the value at N-1 could be added to increase it's value, so we simplify to
	 * only the two.
	 *
	 * From N-2, it means the current "neighbor" will be donation, since it's left
	 * adjacent didn't.
	 * From N-1, it means the left adjacent donated, so neighbor N will not.
	 *
	 * This is represented as:
	 * D[N][Start] = max(D[N-2][Start] + donations[N], D[N-1][Start])
	 *
	 *
	 *
	 * @param donations
	 * @return
	 */
	public int maxDonations(int[] donations) {

		int[][] maxDon = new int[donations.length][2];

		maxDon[0][0] = donations[0];
		maxDon[0][1] = 0;
		for(int i = 1; i < maxDon.length; i++) {
			for(int j = 0; j < 2; j++) {
				if(j == 1 && i == 1) {
					maxDon[1][1] = donations[i];
				} else {
					int curr = 0;
					if (i >= 2) {
						int add = 0;
						if(!(j == 0 && i == maxDon.length - 1)) {
							add = donations[i];
						}
						curr = maxDon[i - 2][j] + add;
					}
					maxDon[i][j] = Math.max(maxDon[i - 1][j], curr);
				}
			}
		}

		return Math.max(maxDon[maxDon.length - 1][0], maxDon[maxDon.length - 1][1]);
	}

	public static void main(String[] args) {
		System.out.println("16 : " + new BadNeighbors().maxDonations(new int[]{1, 2, 3, 4, 5, 1, 2, 3, 4, 5 }));

		System.out.println("2926 : " + new BadNeighbors().maxDonations(new int[]{ 94, 40, 49, 65, 21, 21, 106, 80, 92, 81, 679, 4, 61,
				6, 237, 12, 72, 74, 29, 95, 265, 35, 47, 1, 61, 397,
				52, 72, 37, 51, 1, 81, 45, 435, 7, 36, 57, 86, 81, 72 }));

	}
}
