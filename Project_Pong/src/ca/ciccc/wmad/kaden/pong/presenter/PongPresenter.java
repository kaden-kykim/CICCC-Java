package ca.ciccc.wmad.kaden.pong.presenter;

import ca.ciccc.wmad.kaden.pong.contract.PongContract;
import ca.ciccc.wmad.kaden.pong.model.PongBoard;
import ca.ciccc.wmad.kaden.pong.model.PongScore;

import java.awt.geom.Point2D;

import static ca.ciccc.wmad.kaden.pong.contract.PongContract.*;

public class PongPresenter implements PongContract.Presenter {

    private static final int DIFFICULTY_EASY = 0, DIFFICULTY_NORMAL = 1, DIFFICULTY_HARD = 2;

    private PongContract.View view;

    private PongBoard pongBoard;
    private PongScore pongScore;

    private int difficulty = DIFFICULTY_NORMAL;
    private boolean isPlaying = false, doesPlayerWinPrev = false;

    public PongPresenter(PongContract.View view) {
        this.view = view;
        this.pongBoard = new PongBoard(this);
        this.pongScore = new PongScore();
        resetGame();
    }

    @Override
    public void checkUserPaddlePosition(double diffHeightInCalc) {
        double nextY = pongBoard.getPaddlePosition(true).getY() + diffHeightInCalc;
        if (nextY > 0 && nextY < CALC_PADDLE_MAX_POS_Y) {
            setPaddlePosition(true, nextY);
        }
    }

    @Override
    public void toggleDifficulty() {
        difficulty = ++difficulty % 3;
        switch (difficulty) {
            case DIFFICULTY_EASY:
                view.setDifficultyText("EASY");
                break;
            case DIFFICULTY_HARD:
                view.setDifficultyText("HARD");
                break;
            case DIFFICULTY_NORMAL:
                view.setDifficultyText("NORMAL");
        }
    }

    @Override
    public void resetGame() {
        doesPlayerWinPrev = false;
        initGame();
        initScore();
        isPlaying = false;
    }

    @Override
    public void startGame() {
        if (!isPlaying) {
            isPlaying = true;
            gameThread();
        }
    }

    private void setBallPosition(double x, double y) {
        view.setBallPosition(x, y);
    }

    private void initGame() {
        initBall();
        setPaddlePosition(true, CALC_PADDLE_POS_Y);
        setPaddlePosition(false, CALC_PADDLE_POS_Y);
    }

    private void initBall() {
        double x = (doesPlayerWinPrev) ? CALC_BOARD_WIDTH - CALC_INIT_GAP_POS_X : CALC_INIT_GAP_POS_X;
        double y = (doesPlayerWinPrev) ? CALC_BOARD_HEIGHT - CALC_INIT_GAP_POS_Y : CALC_INIT_GAP_POS_Y;
        pongBoard.initBall(doesPlayerWinPrev, x, y);
        setBallPosition(x, y);
    }

    private void setPaddlePosition(boolean isPlayer, double y) {
        pongBoard.setPaddlePosition(isPlayer, y);
        view.setPaddlePosition(isPlayer, y);
    }

    private void addScore(boolean isPlayer) {
        if ((isPlayer)) {
            pongScore.addPlayerScore();
        } else {
            pongScore.addComputerScore();
        }
        view.addScore(isPlayer);
    }

    private void initScore() {
        pongScore.initScores();
        view.initScore();
    }

    // TODO delete
    private void gameThread() {
        final long NEXT_GAME_INTERVAL = 1500;
        new Thread(() -> {
            while (isPlaying) {
                try {
                    Point2D nextPos = pongBoard.updateBallPosition();
                    if (nextPos.getX() <= 0) {
                        addScore(true);
                        doesPlayerWinPrev = true;
                        Thread.sleep(NEXT_GAME_INTERVAL);
                        initGame();
                    } else if (nextPos.getX() >= CALC_BOARD_WIDTH - CALC_BALL_DIAMETER) {
                        addScore(false);
                        doesPlayerWinPrev = false;
                        Thread.sleep(NEXT_GAME_INTERVAL);
                        initGame();
                    } else {
                        setBallPosition(nextPos.getX(), nextPos.getY());
                    }
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
