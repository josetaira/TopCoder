/**
 * Source: TopCoder - SRM 688
 * Difficulty: Easy
 * Division: 2
 * Points: 250
 * Passes System Test: true
 * @author Jose Taira
 */
public class ParenthesesDiv2Easy {
    public int getDepth(String s) {
        int md = 0;
        int ln = s.length();
        int cd = 0;
        for(int i = 0; i < ln; i++) {
            if("(".equals(s.substring(i, i+1))) {
                cd++;
            } else if(")".equals(s.substring(i, i+1))) {
                cd--;
            }

            if(cd > md) md = cd;
        }
        return md;
    }
}
