/**
 * Source: Topcoder - SRM 238
 * Category: Easy
 * Points: 200
 * Passes System Test: true
 * @author Jose Taira
 */
public class PrintScheduler {

    public String getOutput(String[] threads, String[] slices) {
        StringBuilder sb = new StringBuilder();

        int[] curr = new int[threads.length];
        for(int i = 0; i < slices.length; i++) {
            String[] args = slices[i].split(" ");
            int limit = Integer.valueOf(args[1]);
            int threadIdx = Integer.valueOf(args[0]);
            while(limit > 0) {
                curr[threadIdx] = curr[threadIdx] % threads[threadIdx].length();
                sb.append(threads[threadIdx].charAt(curr[threadIdx]++));
                limit--;
            }
        }

        return sb.toString();
    }

}
