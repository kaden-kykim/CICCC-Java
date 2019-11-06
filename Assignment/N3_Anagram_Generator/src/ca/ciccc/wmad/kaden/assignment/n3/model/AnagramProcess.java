package ca.ciccc.wmad.kaden.assignment.n3.model;

import ca.ciccc.wmad.kaden.assignment.n3.model.dic.EngDictionary;
import ca.ciccc.wmad.kaden.assignment.n3.presenter.AGPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AnagramProcess {

    private static final int BUFFER_UPPER_SIZE = 100000, BUFFER_LOWER_SIZE = 10000;
    private static final int MIN_WORD_LENGTH = 1;

    private final String originalString;
    private final long numOfCombination;

    private AGPresenter presenter;

    private AGThreadPool agThreadPool;
    private Set<Map.Entry<Character, Integer>> characterSet;
    private ArrayList<String> combinations;

    private int progressCount;
    private int originalLength;

    public AnagramProcess(AGPresenter presenter, String originalString) {
        this.presenter = presenter;
        this.agThreadPool = new AGThreadPool(agThreadPoolCallback);
        this.originalString = originalString;
        this.originalLength = 0;
        this.numOfCombination = calcNumberOfCombination();
    }

    public void generateAnagrams() {
        while (combinations == null || combinations.size() <= 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                return;
            }
        }
        while (!Thread.currentThread().isInterrupted()) {
            if (!agThreadPool.isFull()) {
                agThreadPool.addAGThread(combinations.remove(0));
                presenter.setProgress(++progressCount);
            }

            if (combinations.size() <= 0) {
                break;
            }
        }
        agThreadPool.stop();
    }

    public void generateCombinations() {
        combinations = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        progressCount = 0;
        combineCharacters(stringBuilder);
    }

    private void combineCharacters(StringBuilder stringBuilder) {
        for (Map.Entry<Character, Integer> iterator : characterSet) {
            if (iterator.getValue() != 0) {
                stringBuilder.append(iterator.getKey());
                if (stringBuilder.length() == originalLength) {
                    combinations.add(stringBuilder.toString());
                    if (combinations.size() >= BUFFER_UPPER_SIZE) {
                        while (!Thread.currentThread().isInterrupted()) {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                break;
                            }
                            if (combinations.size() < BUFFER_LOWER_SIZE) {
                                break;
                            }
                        }
                    }
                } else {
                    iterator.setValue(iterator.getValue() - 1);
                    combineCharacters(stringBuilder);
                    iterator.setValue(iterator.getValue() + 1);
                }
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            }

            if (Thread.currentThread().isInterrupted()) {
                return;
            }
        }
    }

    public long getNumOfCombination() {
        return numOfCombination;
    }

    private AGThreadPool.Callback agThreadPoolCallback = str -> {
        ArrayList<String> history = new ArrayList<>();
        if (str == null) {
            return;
        }
        for (int separator = 0; separator < str.length(); ++separator) {
            checkSubStringExist(separator, str, history);
            if (Thread.currentThread().isInterrupted()) {
                break;
            }
        }
    };

    private void checkSubStringExist(int remainSeparator, String subString, ArrayList<String> history) {
        if (remainSeparator < 0 || remainSeparator >= subString.length() / 2) {
            return;
        }

        if (remainSeparator == 0) {
            if (EngDictionary.getInstance().existWord(subString)) {
                StringBuilder stringBuilder = new StringBuilder();
                for (String histString : history) {
                    stringBuilder.append(histString).append(" ");
                }
                stringBuilder.append(subString);
                presenter.addString(stringBuilder.toString());
            }
        } else {
            for (int i = MIN_WORD_LENGTH; i <= (subString.length() - remainSeparator) / MIN_WORD_LENGTH; ++i) {
                String preStr = subString.substring(0, i);
                if (EngDictionary.getInstance().existWord(preStr)) {
                    history.add(preStr);
                    checkSubStringExist(remainSeparator - 1, subString.substring(i), history);
                    history.remove(preStr);
                }

                if (Thread.currentThread().isInterrupted()) {
                    return;
                }
            }
        }
    }

    private long calcNumberOfCombination() {
        HashMap<Character, Integer> map = new HashMap<>();
        String string = originalString.toLowerCase();
        for (int i = 0; i < string.length(); ++i) {
            char ch = string.charAt(i);
            if (checkAcceptCharacter(ch)) {
                if (map.containsKey(ch)) {
                    map.replace(ch, map.get(ch) + 1);
                } else {
                    map.put(ch, 1);
                }
                originalLength++;
            }
        }
        long number = factorial(originalLength);
        characterSet = map.entrySet();

        Integer[] values = map.values().toArray(new Integer[0]);
        for (Integer value : values) {
            number /= factorial(value);
        }
        return number;
    }

    private long factorial(int value) {
        long retVal = 1;
        for (int i = 2; i <= value; ++i) {
            retVal *= i;
        }
        return retVal;
    }

    private boolean checkAcceptCharacter(char ch) {
        return ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'));
    }
}
