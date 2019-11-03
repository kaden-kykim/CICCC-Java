package ca.ciccc.wmad.kaden.pong.model;

import java.awt.geom.Point2D;

public class PongPaddle {

    private Point2D position;

    public void setPosition(double x, double y) {
        position = new Point2D.Double(x, y);
    }

    public Point2D getPosition() {
        return position;
    }

}
