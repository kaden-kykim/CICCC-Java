package ca.ciccc.wmad.kaden.assignment.n3.model.dic;

import java.util.HashMap;
import java.util.Random;

public class FakeDictionary implements IDictionary {

    private static final int LONGEST_WORD_LENGTH = 25;

    private static FakeDictionary dictionary;

    private static HashMap<String, Boolean> fakeDic;

    static {
        fakeDic = new HashMap<>();
        for (char c = 'a'; c <= 'z'; ++c) {
            fakeDic.put(String.format("%c", c), true);
        }
    }

    private FakeDictionary() {
    }

    static FakeDictionary getInstance() {
        if (dictionary == null) {
            dictionary = new FakeDictionary();
        }
        return dictionary;
    }

    @Override
    public boolean existWord(String word) {
        if (fakeDic.containsKey(word)) {
            return fakeDic.get(word);
        } else {
            return putFakeWord(word);
        }
    }

    private boolean putFakeWord(String word) {
        Random random = new Random();
        boolean exist = (random.nextInt(LONGEST_WORD_LENGTH) < word.length());
        fakeDic.put(word, exist);
        return exist;
    }
}
