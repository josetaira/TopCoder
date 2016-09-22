/**
 * Source: TopCoder - SRM 698
 * Difficulty: Easy
 * Division: 2
 * Points: 250
 * Passes System Test: true
 * @author Jose Taira
 */
public class Initials {
    public String getInitials(String name) {
        String[] acryonmParts = name.split(" ");
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < acryonmParts.length; i++) {
            sb.append(acryonmParts[i].charAt(0));
        }

        return sb.toString();
    }
}
