package ca.ciccc.wmad.kaden.chess.global;

public class Status {

    public static final int STATUS_MENU = 0, STATUS_SETTING = 1, STATUS_GAME = 2;

    private static final Status instance = new Status();

    private int status;
    private boolean isGaming;

    private Status() {
        this.status = STATUS_MENU;
        this.isGaming = false;
    }

    public static Status getInstance() {
        return instance;
    }

    public boolean isGaming() {
        return isGaming;
    }

    public void setIsGaming(boolean isGaming) {
        this.isGaming = isGaming;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
