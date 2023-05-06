import java.util.Comparator;

public class AlienAlphabet {
    private String order;
    
    public AlienAlphabet(String alphabetOrder) {
        order = alphabetOrder;
    }

    public class AlienComparator implements Comparator<String> {
        public int compare(String word1, String word2) {
            int minLength = Math.min(word1.length(), word2.length());
            for(int i=0; i<minLength; i++) {
                int char1Rank = order.indexOf(word1.charAt(i));
                int char2Rank = order.indexOf(word2.charAt(i));
                if(char1Rank < char2Rank) {
                    return -1;
                } else if(char1Rank > char2Rank) {
                    return 1;
                }
            }

            return word1.length() - word2.length();
        }
    }
}
