package ca.ciccc.wmad.kaden.assignment.n3.model.dic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class EngDictionary implements IDictionary {

    private static final boolean USABLE;
    public static EngDictionary dictionary;
    private static HashSet<String> words = new HashSet<>();

    static {
        boolean tmpUsable = false;
        try {
            Scanner scanner = new Scanner(new File("./res/eng.dict"));
            while (scanner.hasNextLine()) {
                words.add(scanner.nextLine().toLowerCase().replace(" ", ""));
            }
            tmpUsable = true;
        } catch (FileNotFoundException e) {
            System.out.println("Dictionary file not found");
        }
        USABLE = tmpUsable;
    }

    private EngDictionary() {
    }

    static EngDictionary getInstance() {
        if (dictionary == null) {
            dictionary = new EngDictionary();
        }
        return dictionary;
    }

    public static boolean isUsable() {
        return USABLE;
    }

    @Override
    public boolean existWord(String word) {
        return words.contains(word);
    }
}
