package ca.ciccc.wmad.kaden.ripper;

import java.util.Arrays;

public class Main {

    private static final int MIN_LENGTH = 3, MAX_LENGTH = 4;
    private static final boolean USE_NUMBER = true, USE_UPPER_CASE = true, USE_LOWER_CASE = true, USE_SPECIAL_CASE = false;

    public static void main(String[] args) {
        PasswordGenerator passwordGenerator
                = new PasswordGenerator(MIN_LENGTH, MAX_LENGTH, USE_NUMBER, USE_UPPER_CASE, USE_LOWER_CASE, USE_SPECIAL_CASE);
        char[] generatedPassword = passwordGenerator.generatePassword();
        Ripper ripper = new Ripper(MIN_LENGTH, MAX_LENGTH, USE_NUMBER, USE_UPPER_CASE, USE_LOWER_CASE, USE_SPECIAL_CASE);

        System.out.print("Generated Password: " + new String(generatedPassword));
        System.out.println("Ripped Password: " + new String(ripper.rip(generatedPassword)));
    }
}
