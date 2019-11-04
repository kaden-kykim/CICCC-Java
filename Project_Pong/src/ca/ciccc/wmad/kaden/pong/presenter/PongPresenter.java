package ca.ciccc.wmad.kaden.pong.presenter;

import ca.ciccc.wmad.kaden.pong.contract.PongContract;
import ca.ciccc.wmad.kaden.pong.model.PongAI;
import ca.ciccc.wmad.kaden.pong.model.PongBoard;
import ca.ciccc.wmad.kaden.pong.model.PongProgress;
import ca.ciccc.wmad.kaden.pong.model.PongScore;

import static ca.ciccc.wmad.kaden.pong.contract.PongContract.*;

public class PongPresenter implements PongContract.Presenter {

    private static final int DIFFICULTY_EASY = 0, DIFFICULTY_NORMAL = 1, DIFFICULTY_HARD = 2;

    private PongContract.View view;

    private PongBoard pongBoard;
    private PongScore pongScore;
    private PongProgress pongProgress;
    private PongAI pongAI;

    private volatile int difficulty = DIFFICULTY_NORMAL;
    private boolean isPlaying = false, doesPlayerWinPrev = false, isPausing = false;

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
        if (!isPlaying || isPausing) {
            difficulty = ++difficulty % 3;
            view.setDifficultyText((difficulty == DIFFICULTY_EASY) ? " EASY "
                    : (difficulty == DIFFICULTY_HARD) ? " HARD " : "NORMAL");
            if (pongAI != null) {
                pongAI.setDifficulty(difficulty);
            }
        }
    }

    @Override
    public void resetGame() {
        doesPlayerWinPrev = false;
        threadStartStop(false);
        initGame();
        initScore();
        view.setGameControlText("Start");
        isPausing = false;
        isPlaying = false;
    }

    @Override
    public void controlGame() {
        if (!isPlaying) {
            isPlaying = true;
            threadStartStop(true);
            view.setGameControlText("Pause");
        } else {
            threadPauseResume();
        }
    }

    public void initGame() {
        initBall();
        setPaddlePosition(true, CALC_PADDLE_POS_Y);
        setPaddlePosition(false, CALC_PADDLE_POS_Y);
        if (isPlaying && pongAI != null) {
            pongAI.pause(false);
        }
    }

    public void addScore(boolean isPlayer) {
        if (pongAI != null) {
            pongAI.pause(true);
        }
        if ((isPlayer)) {
            pongScore.addPlayerScore();
        } else {
            pongScore.addComputerScore();
        }
        view.addScore(isPlayer);
        doesPlayerWinPrev = isPlayer;
    }

    public void setBallPosition(double x, double y) {
        view.setBallPosition(x, y);
        if (pongAI != null) {
            pongAI.setBallPositionY(y);
        }
    }

    public void setPaddlePosition(boolean isPlayer, double y) {
        pongBoard.setPaddlePosition(isPlayer, y);
        if (y >= 1 && y < CALC_BOARD_HEIGHT - CALC_PADDLE_HEIGHT) {
            view.setPaddlePosition(isPlayer, y);
        }
    }

    public int getDifficulty() {
        return difficulty;
    }

    private void initBall() {
        double x = (doesPlayerWinPrev) ? CALC_BOARD_WIDTH - CALC_INIT_GAP_POS_X : CALC_INIT_GAP_POS_X;
        double y = (doesPlayerWinPrev) ? CALC_BOARD_HEIGHT - CALC_INIT_GAP_POS_Y : CALC_INIT_GAP_POS_Y;
        pongBoard.initBall(doesPlayerWinPrev, x, y);
        setBallPosition(x, y);
    }

    private void initScore() {
        pongScore.initScores();
        view.initScore();
    }

    private void threadStartStop(boolean willStart) {
        if (willStart) {
            if (pongProgress != null && pongProgress.isAlive()) {
                pongProgress.interrupt();
                pongProgress = null;
            }
            pongProgress = new PongProgress(this, pongBoard);
            pongProgress.start();

            if (pongAI != null && pongAI.isAlive()) {
                pongAI.interrupt();
                pongAI = null;
            }
            pongAI = new PongAI(this, pongBoard);
            pongAI.start();
        } else {
            if (pongProgress != null) {
                pongProgress.interrupt();
            }
            if (pongAI != null) {
                pongAI.interrupt();
            }
        }
    }

    private void threadPauseResume() {
        if (isPausing) {
            pongProgress.pause(isPausing = false);
            pongAI.pause(isPausing);
            view.setGameControlText("Pause");
        } else {
            view.setGameControlText("Resume");
            pongProgress.pause(isPausing = true);
            pongAI.pause(isPausing);
        }
    }
}
