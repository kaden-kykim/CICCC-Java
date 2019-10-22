package ca.ciccc.wmad.kaden.ripper;

import java.util.Arrays;

import static ca.ciccc.wmad.kaden.ripper.PasswordGenerator.*;

public class Ripper {

    private int minLength, maxLength;
    private boolean useNumber;
    private boolean useUpperCaseLetter;
    private boolean useLowerCaseLetter;
    private boolean useSpecialCase;

    public Ripper(int minLength, int maxLength,
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

    public char[] rip(final char[] password) {
        String possibleString = "";
        if (useNumber) {
            possibleString += STR_NUMBER;
        }
        if (useUpperCaseLetter) {
            possibleString += STR_UPPER_CASE_LETTERS;
        }
        if (useLowerCaseLetter) {
            possibleString += STR_LOWER_CASE_LETTERS;
        }
        if (useSpecialCase) {
            possibleString += STR_SPECIAL_CASES;
        }

        for (int len = minLength; len <= maxLength; ++len) {
            StringBuffer stringBuffer = new StringBuffer();
            if (ripRecursion(stringBuffer, len, possibleString, password)) {
                return stringBuffer.toString().toCharArray();
            }
        }

        return null;
    }

    private boolean ripRecursion(StringBuffer stringBuffer, int length, String possibleString, final char[] password) {
        for (int i = 0; i < possibleString.length(); ++i) {
            stringBuffer.append(possibleString.charAt(i));
            if (stringBuffer.length() == length) {
                String string = stringBuffer.toString();
                System.out.println(string);
                if (Arrays.equals(password, string.toCharArray())) {
                    return true;
                }
            } else {
                if (ripRecursion(stringBuffer, length, possibleString, password)) {
                    return true;
                }
            }
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }
        return false;
    }

}
