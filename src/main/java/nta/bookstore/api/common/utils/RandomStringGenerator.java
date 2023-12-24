package nta.bookstore.api.common.utils;

public class RandomStringGenerator {
    private static final String UPPER_CASE_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER_CASE_ALPHABET = "abcdefghijklmnopqrstuvxyz";
    private static final String NUMBERS = "0123456789";

    // choose a Character random from this String
    private static final String ALPHABET = UPPER_CASE_ALPHABET + NUMBERS + LOWER_CASE_ALPHABET;

    public static String getAlphaNumericString(int n) {
        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index = (int) (ALPHABET.length() * Math.random());

            // add Character one by one in end of sb
            sb.append(ALPHABET.charAt(index));
        }

        return sb.toString();
    }
}
