/**
 * Source: Topcoder - TCO16 Algorithm Round 1B
 * Category: Easy
 * Points: 250
 * Passes System Test: true
 * @author Jose Taira
 */
public class ExploringNumbers {
    public int numberOfSteps(int n) {

        int i = n;
        int currValue = n;
        while(i > 0 && !isPrime(currValue)) {
            if(currValue == 1) {
                return -1;
            }
            int temp = currValue;
            int t = 0;
            while(temp > 0) {
                System.out.println(temp % 10);
                t += Math.pow(temp % 10, 2);
                temp /= 10;
            }

            currValue = t;
            i--;
        }
        if(i == 0 && !isPrime(currValue)) {
            return -1;
        } else {
            return n - i + 1;
        }
    }

    private boolean isPrime(int n) {
        if(n == 1) {
            return false;
        }
        double threshold = Math.sqrt(n);
        for(int i = 2; i <= threshold; i++) {
            if(n % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(new ExploringNumbers().numberOfSteps(57));
    }
}
