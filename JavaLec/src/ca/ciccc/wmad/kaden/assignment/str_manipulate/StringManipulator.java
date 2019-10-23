package ca.ciccc.wmad.kaden.assignment.str_manipulate;

import java.util.Scanner;

public class StringManipulator {

    public static final int OPT_ONLY_NUMBER = 0, OPT_ONLY_CHARACTER = 1, OPT_ALL_LETTERS = 2;
    private static final int NUM_OF_ALPHABETS = 26, NUM_OF_NUMBERS = 10;
    private static final char[] ALPHABETS_UPPER, ALPHABETS_LOWER, NUMBERS;
    static {
        ALPHABETS_LOWER = generateAlphabets(true);
        ALPHABETS_UPPER = generateAlphabets(false);
        NUMBERS = generateNumbers();
    }

    public static void main(String[] args) {
        StringManipulator stringManipulator = new StringManipulator();

        // Question 1: Create a program that generates the alphabet from a to z
        char[] alphabets = generateAlphabets(true);
        System.out.println("Question 1's answer:\n\t" + new String(alphabets));

        // Question 2: Extend that program to create a 'word' that contains random characters
        int lengthOfWord = 5;
        char[] word = stringManipulator.getRandomizedWord(lengthOfWord, new char[][]{ALPHABETS_LOWER, ALPHABETS_UPPER});
        System.out.println("Question 2's answer:\n\t" + new String(word));

        // Question 3: Extend that program to create a 'word' that is between min length and max length in size
        word = stringManipulator.getRandomizedWord(4, 6, new char[][]{ALPHABETS_LOWER, ALPHABETS_UPPER});
        System.out.println("Question 3's answer:\n\t" + new String(word));

        // Question 4: Extend that program to include Numbers from 0 - 1 as well (Not mandatory?)
        lengthOfWord = 20;
        word = stringManipulator.getRandomizedWord(lengthOfWord, new char[][]{ALPHABETS_LOWER, ALPHABETS_UPPER, new char[]{'0', '1'}});
        System.out.println("Question 4's answer\n\t" + new String(word));

        // Question 5: Extend that program to only include letters/numbers from which we specify
        int includeOption = OPT_ONLY_NUMBER;
        lengthOfWord = 10;
        word = stringManipulator.getRandomizedWord(lengthOfWord, includeOption);
        System.out.println("Question 5's answer\n\t[Only Number] " + new String(word));
        includeOption = OPT_ONLY_CHARACTER;
        word = stringManipulator.getRandomizedWord(lengthOfWord, includeOption);
        System.out.println("\t[Only Character] " + new String(word));

        // Question 6: Create a program that tells if a word is a palindrome or not
        Scanner input = new Scanner(System.in);
        System.out.print("Question 6's input: ");
        String inputString = input.nextLine();
        System.out.println("Question 6's answer:\n\t" + inputString + " is " + (stringManipulator.isPalindrome(inputString) ? "" : "NOT ") + "a palindrome.");

        // Question 7: Create a program that 'reverses' the word
        System.out.print("Question 7's input: ");
        inputString = input.nextLine();
        System.out.println("Question 7's answer:\n\t" + inputString + " --> " + stringManipulator.reverse(inputString));

        input.close();
    }

    public String reverse(String str) {
        String reverse = "";
        for (int i = 0; i < str.length(); ++i) {
            reverse += str.charAt(str.length() - i - 1);
        }
        return reverse;
    }

    public boolean isPalindrome(String str) {
        for (int i = 0; i < str.length() / 2; ++i) {
            if (str.charAt(i) != str.charAt(str.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }

    public char[] getRandomizedWord(int ofLength, char[][] includeLetters) {
        char[] word = new char[ofLength];
        char[] allIncludeLetters = getAllIncludeLetters(includeLetters);
        for (int i = 0; i < ofLength; ++i) {
            word[i] = getRandomizedLetter(allIncludeLetters);
        }
        return word;
    }

    public char[] getRandomizedWord(int ofLength, int includeOption) {
        switch (includeOption) {
            case OPT_ALL_LETTERS:
                return getRandomizedWord(ofLength, new char[][]{ALPHABETS_UPPER, ALPHABETS_LOWER, NUMBERS});
            case OPT_ONLY_NUMBER:
                return getRandomizedWord(ofLength, new char[][]{NUMBERS});
            case OPT_ONLY_CHARACTER:
            default:
                return getRandomizedWord(ofLength, new char[][]{ALPHABETS_LOWER, ALPHABETS_UPPER});
        }
    }

    public char[] getRandomizedWord(int minLength, int maxLength, char[][] includeLetters) {
        int length = (int) Math.round(Math.random() * (maxLength - minLength) + minLength);
        return getRandomizedWord(length, includeLetters);
    }

    private char getRandomizedLetter(char[] allLetters) {
        return allLetters[(int) (Math.random() * allLetters.length)];
    }

    private char[] getAllIncludeLetters(char[][] includeLetters) {
        int lengthLetters = 0;
        for (char[] letters : includeLetters) {
            lengthLetters += letters.length;
        }
        char[] allLetters = new char[lengthLetters];
        int currentPosition = 0;
        for (char[] letters : includeLetters) {
            System.arraycopy(letters, 0, allLetters, currentPosition, letters.length);
            currentPosition += letters.length;
        }
        return allLetters;
    }

    public static char[] generateAlphabets(boolean isLower) {
        char[] alphabets = new char[NUM_OF_ALPHABETS];
        for (int i = 0; i < NUM_OF_ALPHABETS; ++i) {
            alphabets[i] = (char) (i + ((isLower) ? 'a' : 'A'));
        }
        return alphabets;
    }

    private static char[] generateNumbers() {
        char[] numbers = new char[NUM_OF_NUMBERS];
        for (int i = 0; i < NUM_OF_NUMBERS; ++i) {
            numbers[i] = (char) (i + '0');
        }
        return numbers;
    }

}
