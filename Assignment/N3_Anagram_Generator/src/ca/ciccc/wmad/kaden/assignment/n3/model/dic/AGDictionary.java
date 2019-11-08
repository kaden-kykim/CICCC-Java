package ca.ciccc.wmad.kaden.assignment.n3.model.dic;

public class AGDictionary implements IDictionary {

    private static AGDictionary instance = null;
    private static IDictionary dictionary;

    private AGDictionary() {
        dictionary = (EngDictionary.isUsable()) ? EngDictionary.getInstance() : FakeDictionary.getInstance();
        System.out.println("Using " + (EngDictionary.isUsable() ? "English" : "Fake") + " Dictionary");
    }

    public static AGDictionary getInstance() {
        if (instance == null) {
            instance = new AGDictionary();
        }
        return instance;
    }


    @Override
    public boolean existWord(String word) {
        return dictionary.existWord(word);
    }
}
