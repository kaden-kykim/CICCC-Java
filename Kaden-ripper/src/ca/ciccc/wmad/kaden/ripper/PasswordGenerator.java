package ca.ciccc.wmad.kaden.ripper;

public class PasswordGenerator {

    public static final String STR_NUMBER = "1234567890",
            STR_UPPER_CASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ",
            STR_LOWER_CASE_LETTERS = STR_UPPER_CASE_LETTERS.toLowerCase(),
            STR_SPECIAL_CASES = "~!@#$%^&*()_+-=[]\\{}|,./<>?";

    private int minLength, maxLength;
    private boolean useNumber;
    private boolean useUpperCaseLetter;
    private boolean useLowerCaseLetter;
    private boolean useSpecialCase;

    public PasswordGenerator(int minLength, int maxLength,
                             boolean useNumber,
                             boolean useUpperCaseLetter,
                             boolean useLowerCaseLetter,
                             boolean useSpecialCase) {
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.useNumber = useNumber;
        this.useUpperCaseLetter = useUpperCaseLetter;
        this.useLowerCaseLetter = useLowerCaseLetter;
        this.useSpecialCase = useSpecialCase;
    }

    public char[] generatePassword() {
        if (!useNumber && !useUpperCaseLetter && !useLowerCaseLetter && !useSpecialCase) {
            System.out.println("At least one option should be true. Just return the string: password ");
            return "password".toCharArray();
        }
        int length = (int) Math.round(Math.random() * (maxLength - minLength) + minLength);
        char[] password = new char[length];
        String possibleLetters = "";
        if (useNumber) {
            possibleLetters += STR_NUMBER;
        }
        if (useUpperCaseLetter) {
            possibleLetters += STR_UPPER_CASE_LETTERS;
        }
        if (useLowerCaseLetter) {
            possibleLetters += STR_LOWER_CASE_LETTERS;
        }
        if (useSpecialCase) {
            possibleLetters += STR_SPECIAL_CASES;
        }

        int strLength = possibleLetters.length();

        for (int i = 0; i < length; ++i) {
            password[i] = possibleLetters.charAt((int) (Math.random() * strLength));
        }

        return password;
    }
}
