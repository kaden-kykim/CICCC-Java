package ca.ciccc.wmad.kaden.pong.model;

public class PongScore {

    private static final int SCORE_PLAYER = 0, SCORE_COMPUTER = 1;

    private int[] scores;

    public PongScore() {
        scores = new int[2];
        initScores();
    }

    public void initScores() {
        scores[SCORE_PLAYER] = scores[SCORE_COMPUTER] = 0;
    }

    public void addPlayerScore() {
        ++scores[SCORE_PLAYER];
    }

    public void addComputerScore() {
        ++scores[SCORE_COMPUTER];
    }
}
