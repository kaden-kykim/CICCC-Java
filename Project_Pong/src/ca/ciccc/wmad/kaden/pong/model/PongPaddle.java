package ca.ciccc.wmad.kaden.pong.model;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import static ca.ciccc.wmad.kaden.pong.contract.PongContract.CALC_PADDLE_HEIGHT;
import static ca.ciccc.wmad.kaden.pong.contract.PongContract.CALC_PADDLE_WIDTH;

public class PongPaddle {

    private Point2D position;

    public void setPosition(double x, double y) {
        position = new Point2D.Double(x, y);
    }

    public Point2D getPosition() {
        return position;
    }

    public Rectangle2D getRectangle2D() {
        return new Rectangle2D.Double(position.getX(), position.getY(), CALC_PADDLE_WIDTH, CALC_PADDLE_HEIGHT);
    }

}
