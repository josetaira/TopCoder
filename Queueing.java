/**
 * TopCoder - SRM 687
 * Difficulty: Hard
 * Points: 1000
 * Passes System Test: true
 * @author Jose Taira
 */
public class Queueing {

  public double probFirst(int len1, int len2, int p1, int p2) {
    double[][] probs = new double[1000][1000];

    double prob1 = 1.0 / p1;
    double prob2 = 1.0 / p2;

    double notProb1 = 1.0 - prob1;
    double notProb2 = 1.0 - prob2;

    // L1 = length of first line
    // L2 = length of second line
    // Each second can affect the state, but it doesn't necessarily do so.
    // For the case where state is not affected as time passes, distribute the probability of that
    // among the other possibilities that change the state.
    // P(T(L1) < T(L2)) = (P(T(L1 - 1) < T(L2)) + P(T(L1) < T(L2 - 1)) + P(T(L1 - 1) < T(L2 - 1))) / P(T(L1) < T(L2))
    for(int L1 = 0; L1 <= len1; L1++) {
      for(int L2 = 0; L2 <= len2; L2++) {
        if(L2 == 0) {
          // Can't win if L2 is already 0
          probs[L1][L2] = 0;
        } else if(L1 == 0) {
          // Always win if already finished and L2 > 0
          probs[L1][L2] = 1;
        } else {
          probs[L1][L2] = probs[L1 - 1][L2] * prob1 * notProb2; // Change state where Line 1 reduces by 1, Line 2 doesn't
          probs[L1][L2] += probs[L1][L2 - 1] * notProb1 * prob2; // Change state where Line 1 reduces by 1, Line 2 doesn't
          probs[L1][L2] += probs[L1-1][L2-1] * prob1 * prob2; // Change state where both lines reduce by 1 customer
          probs[L1][L2] /= (1.0 - notProb1 * notProb2); // Another possibility that doesn't change the state is
        }
      }
    }

    return probs[len1][len2];
  }
}
