package ca.ciccc.wmad.kaden.assignment.n3.model.dic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class EngDictionary implements IDictionary {

    private static HashSet<String> words = new HashSet<>();
    static {
        try {
            Scanner scanner = new Scanner(new File("./res/eng.dict"));
            while (scanner.hasNextLine()) {
                words.add(scanner.nextLine().toLowerCase().replace(" ", ""));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Dictionary file not found");
        }
    }

    public static EngDictionary dictionary;

    private EngDictionary() {
    }

    public static EngDictionary getInstance() {
        if (dictionary == null) {
            dictionary = new EngDictionary();
        }
        return dictionary;
    }

    @Override
    public boolean existWord(String word) {
        return words.contains(word);
    }
}
