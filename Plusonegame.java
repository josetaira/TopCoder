/**
 * Source: TopCoder - SRM 691
 * Difficulty: Easy
 * Division: 2
 * Points: 250
 * Passes System Test: true
 * @author Jose Taira
 */
public class Plusonegame {
	public String getorder(String s) {
		int[] chars = new int[11];
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == '+') {
				chars[10]++;
			} else {
				chars[s.charAt(i) - '0']++;
			}
		}

		for(int i = 0; i < 10; i++) {
			while(chars[i] > 0) {
				sb.append(i);
				chars[i]--;
			}

			if(chars[10] > 0) {
				sb.append('+');
				chars[10]--;
			}
		}
		while(chars[10] > 0) {
			sb.append('+');
			chars[10]--;
		}

		return sb.toString();
	}
}