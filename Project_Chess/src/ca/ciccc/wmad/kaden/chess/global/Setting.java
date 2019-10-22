package ca.ciccc.wmad.kaden.chess.global;

import ca.ciccc.wmad.kaden.chess.model.piece.Piece;

public class Setting {

    private static final Setting instance = new Setting();

    public static final int DIFFICULTY_EASY = 1, DIFFICULTY_HARD = 2;

    private static final boolean DEFAULT_VS_COMPUTER = true;
    private static final int DEFAULT_DIFFICULTY = DIFFICULTY_EASY;
    private static final boolean DEFAULT_FIRST = Piece.PLAYER1;
    private static final boolean DEFAULT_MOVE_HINT = true;
    private static final char DEFAULT_FILL_SQ_CHAR = '/';

    private boolean vsComputer;
    private int difficulty;
    private boolean first;
    private boolean moveHint;
    private char fillSqChar;

    private Setting() {
        this.vsComputer = DEFAULT_VS_COMPUTER;
        this.difficulty = DEFAULT_DIFFICULTY;
        this.first = DEFAULT_FIRST;
        this.moveHint = DEFAULT_MOVE_HINT;
        this.fillSqChar = DEFAULT_FILL_SQ_CHAR;
    }

    public static Setting getInstance() {
        return instance;
    }

    public boolean isVsComputer() {
        return vsComputer;
    }

    public void setVsComputer(boolean vsComputer) {
        this.vsComputer = vsComputer;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public boolean isMoveHint() {
        return moveHint;
    }

    public void setMoveHint(boolean moveHint) {
        this.moveHint = moveHint;
    }

    public char getFillSqChar() {
        return fillSqChar;
    }

    public void setFillSqChar(char fillSqChar) {
        this.fillSqChar = fillSqChar;
    }
}
