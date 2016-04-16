/**
 * Source: TopCoder - SRM 688
 * Difficulty: Medium
 * Division: 2
 * Points: 500
 * Passes System Test: true
 * @author Jose Taira
 */
public class ParenthesesDiv2Medium {

    public int[] correct(String s) {
        java.util.List<Integer> l = new java.util.ArrayList<Integer>();

        int cd = 0;
        for(int i = 0; i < s.length(); i++) {
            char iChar = s.charAt(i);
            if(iChar == ')') {
                if(cd == 0) {
                    l.add(i);
                    cd++;
                } else {
                    cd--;
                }
            } else if(iChar == '(') {
                if(s.length() - 1 - cd - i <= 0) {
                    l.add(i);
                    cd--;
                } else {
                    cd++;
                }
            }
        }
        //System.out.println(l);

        return toIntArray(l);
    }

    private int[] toIntArray(java.util.List<Integer> l) {
        int[] ina = new int[l.size()];
        for(int i = 0; i < ina.length; i++) ina[i] = l.get(i); return ina;
    }
    
}
