package ca.ciccc.wmad.kaden.pong.presenter;

import ca.ciccc.wmad.kaden.pong.contract.PongContract;
import ca.ciccc.wmad.kaden.pong.model.PongBoard;
import ca.ciccc.wmad.kaden.pong.model.PongScore;

import java.awt.geom.Point2D;

import static ca.ciccc.wmad.kaden.pong.contract.PongContract.*;

public class PongPresenter implements PongContract.Presenter {

    private PongContract.View view;

    private PongBoard pongBoard;
    private PongScore pongScore;

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
    public void resetGame() {
        doesPlayerWinPrev = false;
        initGame();
        pongScore.initScores();
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

    // TODO delete
    private void gameThread() {
        final long NEXT_GAME_INTERVAL = 1500;
        new Thread(() -> {
            while (isPlaying) {
                try {
                    Point2D nextPos = pongBoard.updateBallPosition();
                    if (nextPos.getX() <= CALC_COM_PADDLE_POS_X - CALC_PADDLE_WIDTH - CALC_BALL_RADIUS * 2) {
                        pongScore.addPlayerScore();
                        doesPlayerWinPrev = true;
                        Thread.sleep(NEXT_GAME_INTERVAL);
                        initGame();
                    } else if (nextPos.getX() >= CALC_PLAYER_PADDLE_POS_X + CALC_PADDLE_WIDTH + CALC_BALL_RADIUS) {
                        pongScore.addComputerScore();
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
