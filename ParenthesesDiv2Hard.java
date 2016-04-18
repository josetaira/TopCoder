import java.util.ArrayList;
import java.util.List;

public class ParenthesesDiv2Hard {
    public int minSwaps(String s, int[] L, int[] R) {
        if(s.length() <= 1) {
            return -1;
        }

        StringBuilder sb = new StringBuilder(s);

        int moves = 0;
        List<String> parts = new ArrayList<String>();
        for(int i = L.length - 1; i >= 0; i--) {
            parts.add(s.substring(L[i], R[i]));
            sb.delete(L[i], R[i]);
        }

        System.out.println(parts);

        return moves;
    }

    public static void main(String[])
}
