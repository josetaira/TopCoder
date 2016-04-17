import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Source: TopCoder - SRM 269
 * Difficulty: Easy
 * Division: 2
 * Points: 250
 * Passes System Test: true
 * @author Jose Taira
 */
public class AccessChanger {

    // Negative lookahead
    public static final Pattern REPLACEABLE = Pattern.compile("^(?:(?!//).)*(->)");
    public String[] convert(String[] program) {
        for(int i = 0; i < program.length; i++) {
            Matcher m = REPLACEABLE.matcher(program[i]);
            while (m.find()) {
                StringBuilder sb = new StringBuilder(program[i]);
                for (int j = m.groupCount(); j > 0; j--) {
                    sb.deleteCharAt(m.end(j) - 1);
                    sb.setCharAt(m.start(j), '.');
                }
                program[i] = sb.toString();
                m = REPLACEABLE.matcher(program[i]);
            }
        }

        return program;
    }

}


