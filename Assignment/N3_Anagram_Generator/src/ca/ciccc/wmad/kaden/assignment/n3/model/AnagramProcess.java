package ca.ciccc.wmad.kaden.assignment.n3.model;

import ca.ciccc.wmad.kaden.assignment.n3.model.dic.AGDictionary;
import ca.ciccc.wmad.kaden.assignment.n3.model.setting.AGSetting;
import ca.ciccc.wmad.kaden.assignment.n3.presenter.AGPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AnagramProcess {

    private static final int BUFFER_UPPER_SIZE = 100000, BUFFER_LOWER_SIZE = 25000;
    private static final char DIFF_UPPER_LOWER = 'a' - 'A';

    private final String lowerString;
    private final long numOfCombination;

    private AGPresenter presenter;
    private AGThreadPool agThreadPool;
    private Set<Map.Entry<Character, Integer>> characterSet;
    private ArrayList<String> combinations;

    private int progressCount;
    private int originalLength;
    private int minWordLength, maxWordLength;
    private boolean allowRepeatWord;

    public AnagramProcess(AGPresenter presenter, String originalString) {
        this.presenter = presenter;
        this.agThreadPool = new AGThreadPool(agThreadPoolCallback);
        this.lowerString = originalString.toLowerCase();
        this.originalLength = 0;
        this.numOfCombination = calcNumberOfCombination();
        this.minWordLength = AGSetting.getInstance().getMinWordLength();
        this.maxWordLength = AGSetting.getInstance().getMaxWordLength();
        this.allowRepeatWord = AGSetting.getInstance().allowRepeat();
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
        progressCount = 0;
    }

    public void generateCombinations() {
        combinations = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        progressCount = 0;
        combineCharacters(stringBuilder);
    }

    private boolean combineCharacters(StringBuilder stringBuilder) {
        int index = 0;
        for (Map.Entry<Character, Integer> iterator : characterSet) {
            if (iterator.getValue() != 0) {
                stringBuilder.append(iterator.getKey());
                if (stringBuilder.length() == originalLength) {
                    combinations.add(stringBuilder.toString());
                } else {
                    iterator.setValue(iterator.getValue() - 1);
                    if (!combineCharacters(stringBuilder)) {
                        return false;
                    }
                    iterator.setValue(iterator.getValue() + 1);
                }
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            }

            if (combinations.size() >= BUFFER_UPPER_SIZE) {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        Thread.sleep(10);
                        if (combinations.size() < BUFFER_LOWER_SIZE) {
                            break;
                        }
                    } catch (InterruptedException e) {
                        return false;
                    }
                }
            }
            if (Thread.currentThread().isInterrupted()) {
                return false;
            }
        }
        return true;
    }

    public long getNumOfCombination() {
        return numOfCombination;
    }

    private AGThreadPool.Callback agThreadPoolCallback = str -> {
        ArrayList<String> history = new ArrayList<>();
        if (str == null) {
            return;
        }
        for (int separator = 0; separator < str.length() / minWordLength; ++separator) {
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
            if (AGDictionary.getInstance().existWord(subString)) {
                StringBuilder stringBuilder = new StringBuilder();
                for (String histString : history) {
                    stringBuilder.append(histString).append(" ");
                }
                stringBuilder.append(subString);
                if (!lowerString.contentEquals(stringBuilder)) {
                    presenter.addString(toCapitalize(stringBuilder).toString());
                }
            }
        } else {
            int index = subString.length() - (remainSeparator * minWordLength);
            for (int i = minWordLength; (i < index) && (i <= maxWordLength); ++i) {
                String preStr = subString.substring(0, i);
                if (AGDictionary.getInstance().existWord(preStr)) {
                    if (allowRepeatWord && !checkExistInHistory(preStr, history)) {
                        history.add(preStr);
                        checkSubStringExist(remainSeparator - 1, subString.substring(i), history);
                        history.remove(preStr);
                    }
                }

                if (Thread.currentThread().isInterrupted()) {
                    return;
                }
            }
        }
    }

    private boolean checkExistInHistory(String string, ArrayList<String> history) {
        for (String hist : history) {
            if (hist.equals(string)) {
                return true;
            }
        }
        return false;
    }

    private long calcNumberOfCombination() {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < lowerString.length(); ++i) {
            char ch = lowerString.charAt(i);
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

    private StringBuilder toCapitalize(StringBuilder stringBuilder) {
        stringBuilder.setCharAt(0, (char) (stringBuilder.charAt(0) - DIFF_UPPER_LOWER));
        return stringBuilder;
    }

    private boolean checkAcceptCharacter(char ch) {
        return ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'));
    }
}
