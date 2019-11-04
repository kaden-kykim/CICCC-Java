package ca.ciccc.wmad.kaden.pong.model;

import ca.ciccc.wmad.kaden.pong.presenter.PongPresenter;

import java.awt.geom.Point2D;

import static ca.ciccc.wmad.kaden.pong.contract.PongContract.CALC_PADDLE_HEIGHT;

public class PongAI extends Thread {

    private PongPresenter presenter;
    private PongBoard pongBoard;

    private volatile double ballY;
    private volatile int difficulty;
    private volatile boolean pause;

    public PongAI(PongPresenter presenter, PongBoard pongBoard) {
        this.presenter = presenter;
        this.pongBoard = pongBoard;
        this.difficulty = presenter.getDifficulty();
        this.pause = false;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                double comY = pongBoard.getPaddlePosition(false).getY();
                double delta = (comY + CALC_PADDLE_HEIGHT / 2) - ballY;
                boolean isNegative = (delta < 0);
                delta = Math.abs(delta);
                while (delta > 5) {
                    while (pause) {
                        sleep(10);
                    }
                    int difficult = difficulty + 1;
                    comY += (isNegative) ? difficult : -difficult;
                    presenter.setPaddlePosition(false, comY);
                    delta -= difficult;
                    sleep(2);
                }
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public void pause(boolean pause) {
        this.pause = pause;
    }

    public void setBallPositionY(double ballY) {
        this.ballY = ballY;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}
