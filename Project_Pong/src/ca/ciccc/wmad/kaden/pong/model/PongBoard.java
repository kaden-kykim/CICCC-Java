package ca.ciccc.wmad.kaden.pong.model;

import ca.ciccc.wmad.kaden.pong.contract.PongUtilities;
import ca.ciccc.wmad.kaden.pong.presenter.PongPresenter;

import java.awt.geom.Point2D;

import static ca.ciccc.wmad.kaden.pong.contract.PongContract.*;

public class PongBoard {

    private static final double EDGE_LIMIT = CALC_BALL_DIAMETER / (2 * Math.sqrt(2.0));

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
        double nextPosX = nextPos.getX(), nextPosY = nextPos.getY();
        if (nextPosY < 0 || nextPosY > CALC_BOARD_HEIGHT - CALC_BALL_DIAMETER) {
            pongBall.toggleSlope();
        }

        if ((nextPosX < CALC_COM_PADDLE_POS_X + CALC_PADDLE_WIDTH) && (nextPosX > CALC_COM_PADDLE_POS_X)) {
            checkToggleX(comPaddle, nextPosX, nextPosY);
        }

        double checkVal = nextPosX + CALC_BALL_DIAMETER;
        if ((checkVal > CALC_PLAYER_PADDLE_POS_X) && (checkVal < CALC_PLAYER_PADDLE_POS_X + CALC_PADDLE_WIDTH)) {
            checkToggleX(playerPaddle, nextPosX, nextPosY);
        }

        if ((int) (nextPos.getX() / 100) == 10) {
            if (pongBall.isToggleBuffer()) {
                pongBall.setToggleBuffer(false);
            }
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

    private void checkToggleX(PongPaddle paddle, double nextX, double nextY) {
        double lower = paddle.getPosition().getY() - EDGE_LIMIT;
        double upper = paddle.getPosition().getY() + CALC_PADDLE_HEIGHT + EDGE_LIMIT;
        if ((nextY > lower) && (nextY < upper)) {
            if (PongUtilities.checkIntersects(nextX, nextY, paddle.getRectangle2D()) && !pongBall.isToggleBuffer()) {
                pongBall.toggleSlope();
                pongBall.toggleXIncreasing();
            }
        } else {
            if (PongUtilities.checkIntersects(nextX, nextY, paddle.getRectangle2D())) {
                pongBall.toggleSlope();
            }
        }
    }
}
