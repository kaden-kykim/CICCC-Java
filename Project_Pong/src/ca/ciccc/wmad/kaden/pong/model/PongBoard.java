package ca.ciccc.wmad.kaden.pong.model;

import ca.ciccc.wmad.kaden.pong.presenter.PongPresenter;

import java.awt.geom.Point2D;

import static ca.ciccc.wmad.kaden.pong.contract.PongContract.*;

public class PongBoard {

    private static final double EDGE_LIMIT = 0; //CALC_BALL_RADIUS / Math.sqrt(2.0);
    private static final double REFLECT_GAP = CALC_BALL_RADIUS / 4;

    private PongPresenter presenter;
    private PongBall pongBall;
    private PongPaddle playerPaddle, comPaddle;

    public PongBoard(PongPresenter presenter) {
        this.presenter = presenter;
        pongBall = new PongBall();
        playerPaddle = new PongPaddle();
        comPaddle = new PongPaddle();
    }

    public void initBall(boolean doesPlayerWinPrev, double x, double y) {
        pongBall.setPosition(x, y);
        pongBall.setXIncreasing(!doesPlayerWinPrev);
        pongBall.initSlope();
        pongBall.initSpeed();
    }

    public Point2D getBallPosition() {
        return pongBall.getPosition();
    }

    public Point2D updateBallPosition() {
        Point2D nextPos = pongBall.getNextPosition();
        double checkVal = nextPos.getY();
        if (checkVal < 0 || checkVal + CALC_BALL_RADIUS >= CALC_BOARD_HEIGHT) {
            pongBall.toggleSlope();
        }

        checkVal = nextPos.getX();
        if ((checkVal < CALC_COM_PADDLE_POS_X) && (checkVal > CALC_COM_PADDLE_POS_X - REFLECT_GAP)) {
            checkToggleX(comPaddle, nextPos.getY());
        }

        checkVal = nextPos.getX() + CALC_BALL_RADIUS;
        if ((checkVal > CALC_PLAYER_PADDLE_POS_X) && (checkVal < CALC_PLAYER_PADDLE_POS_X + REFLECT_GAP)) {
            checkToggleX(playerPaddle, nextPos.getY());
        }

        if ((int) (nextPos.getX() / 100) == 10) {
            pongBall.setToggleBuffer(false);
        }

        pongBall.setPosition(nextPos.getX(), nextPos.getY());
        return nextPos;
    }

    public void setPaddlePosition(boolean isPlayer, double y) {
        ((isPlayer) ? playerPaddle : comPaddle)
                .setPosition((isPlayer) ? CALC_PLAYER_PADDLE_POS_X : CALC_COM_PADDLE_POS_X, y);
    }

    public Point2D getPaddlePosition(boolean isPlayer) {
        return (isPlayer) ? playerPaddle.getPosition() : comPaddle.getPosition();
    }

    private void checkToggleX(PongPaddle paddle, double nextY) {
        double lower = paddle.getPosition().getY() - EDGE_LIMIT;
        double upper = paddle.getPosition().getY() + CALC_PADDLE_HEIGHT + EDGE_LIMIT;
        if ((nextY > lower) && (nextY < upper)) {
            if (!pongBall.isToggleBuffer()) {
                pongBall.toggleSlope();
                pongBall.toggleXIncreasing();
            }
        }
    }
}
