/**
 * Source: TopCoder - SRM 258
 * Difficulty: Easy
 * Division: 1 and 2
 * Points: 250[DIV1], 500[DIV2]
 * Passes System Test: true
 * @author Jose Taira
 */
public class AutoLoan {
    public double interestRate(double price, double mp, int term) {
        double top = 100.0;
        double bot = 0.0;
        double interest = 0.0;
        double remain = -1.0;

        while((Math.abs(top - bot) / top > 1e-9) || remain < 0) {
            interest = (bot + top) / 2.0;
            remain = remainAtInterest(price, mp, term, interest);

            if(remain == 0) {
                break;
            }
            if(remain > 0) {
                top = interest;
            } else {
                bot = interest;
            }
        }

        return interest * 12 * 100;
    }

    private double remainAtInterest(double price, double mp, int term, double interest) {
        for(int i = 0; i < term; i++) {
            price = price - mp + (price * interest);
        }
        return price;
    }

}
