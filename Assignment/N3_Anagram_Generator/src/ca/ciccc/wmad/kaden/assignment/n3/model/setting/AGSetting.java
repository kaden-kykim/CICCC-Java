package ca.ciccc.wmad.kaden.assignment.n3.model.setting;

public class AGSetting {

    public static final int LENGTH_LOWER_BOUND = 1, LENGTH_UPPER_BOUND = 20;
    public static final int SETTING_SPEED_STABLE = 1,
            SETTING_SPEED_NORMAL = 2,
            SETTING_SPEED_FAST = 3,
            SETTING_SPEED_FASTER = 4;

    private static final int DEFAULT_SPEED = SETTING_SPEED_NORMAL;
    private static final int DEFAULT_MIN_WORD_LENGTH = 2;
    private static final int DEFAULT_MAX_WORD_LENGTH = 5;
    private static final boolean DEFAULT_ALLOW_REPEAT = true;

    private int minWordLength, maxWordLength;
    private int settingSpeed;
    private boolean allowRepeat;

    private static AGSetting instance = null;

    private AGSetting() {
        this.minWordLength = DEFAULT_MIN_WORD_LENGTH;
        this.maxWordLength = DEFAULT_MAX_WORD_LENGTH;
        this.settingSpeed = DEFAULT_SPEED;
        this.allowRepeat = DEFAULT_ALLOW_REPEAT;
    }

    public static AGSetting getInstance() {
        if (instance == null) {
            instance = new AGSetting();
        }
        return instance;
    }

    public int getMinWordLength() {
        return minWordLength;
    }

    public void setMinWordLength(int minWordLength) {
        this.minWordLength = minWordLength;
    }

    public int getMaxWordLength() {
        return maxWordLength;
    }

    public void setMaxWordLength(int maxWordLength) {
        this.maxWordLength = maxWordLength;
    }

    public int getSettingSpeed() {
        return settingSpeed;
    }

    public void setSettingSpeed(int settingSpeed) {
        this.settingSpeed = settingSpeed;
    }

    public boolean allowRepeat() {
        return allowRepeat;
    }

    public void setAllowRepeat(boolean allowRepeat) {
        this.allowRepeat = allowRepeat;
    }
}
