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

    public int getPlayerScore() {
        return scores[SCORE_PLAYER];
    }

    public int getComputerScore() {
        return scores[SCORE_COMPUTER];
    }

    public void addPlayerScore() {
        ++scores[SCORE_PLAYER];
        System.out.println(String.format("Computer %d : %d Player", scores[SCORE_COMPUTER], scores[SCORE_PLAYER]));
    }

    public void addComputerScore() {
        ++scores[SCORE_COMPUTER];
        System.out.println(String.format("Computer %d : %d Player", scores[SCORE_COMPUTER], scores[SCORE_PLAYER]));
    }
}
