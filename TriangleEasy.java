/**
 * Source: TopCoder - SRM 693
 * Difficulty: Easy
 * Division: 2
 * Points: 250
 * Passes System Test: true
 * @author Jose Taira
 */
public class TriangleEasy {

    public int find(int n, int[] x, int[] y) {
        int max = 0;

        for(int i = 0; i < x.length; i++) {
            if(max == 0) {
                max = 1;
            }
            for(int j = i + 1; j < x.length; j++) {
                if((x[i] == x[j] || x[i] == y[j] || y[i] == x[j] || y[i] == y[j])) {
                    if(max == 1) {
                        max = 2;
                    }

                    int a;
                    int b;
                    if(x[i] == x[j]) {
                        a = y[i];
                        b = y[j];
                    } else if(x[i] == y[j]) {
                        a = y[i];
                        b = x[j];
                    } else if(y[i] == x[j]) {
                        a = x[i];
                        b = y[j];
                    } else {
                        a = x[i];
                        b = x[j];
                    }

                    for(int k = j + 1; k < x.length; k++) {
                        if((a == x[k] && b == y[k]) || (b == x[k] && a == y[k])) {
                            max = 3;
                            break;
                        }
                    }
                }

            }
        }
        return 3 - max;
    }
}
