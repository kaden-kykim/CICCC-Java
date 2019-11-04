package ca.ciccc.wmad.kaden.pong.model;

import ca.ciccc.wmad.kaden.pong.presenter.PongPresenter;

import java.awt.geom.Point2D;

import static ca.ciccc.wmad.kaden.pong.contract.PongContract.CALC_BALL_DIAMETER;
import static ca.ciccc.wmad.kaden.pong.contract.PongContract.CALC_BOARD_WIDTH;

public class PongProgress extends Thread {

    private static final long NEXT_GAME_INTERVAL = 1500;

    private PongPresenter presenter;
    private PongBoard pongBoard;

    private volatile boolean pause;

    public PongProgress(PongPresenter presenter, PongBoard pongBoard) {
        this.presenter = presenter;
        this.pongBoard = pongBoard;
        this.pause = false;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                Point2D nextPos = pongBoard.updateBallPosition();
                if (nextPos.getX() <= 0) {
                    presenter.addScore(true);
                    sleep(NEXT_GAME_INTERVAL);
                    presenter.initGame();
                } else if (nextPos.getX() >= CALC_BOARD_WIDTH - CALC_BALL_DIAMETER) {
                    presenter.addScore(false);
                    sleep(NEXT_GAME_INTERVAL);
                    presenter.initGame();
                } else {
                    presenter.setBallPosition(nextPos.getX(), nextPos.getY());
                }
                while (pause) {
                    sleep(10);
                }
                sleep(1);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public void pause(boolean pause) {
        this.pause = pause;
    }
}
