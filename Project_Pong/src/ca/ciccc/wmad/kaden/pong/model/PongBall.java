package ca.ciccc.wmad.kaden.pong.model;

import java.awt.geom.Point2D;

public class PongBall {

    private static final double MAX_SPEED = 2.0, MIN_SPEED = 0.4, INIT_SPEED = 0.8, SPEED_STEP = 0.1;
    private static final double INIT_SLOPE = -1.0;

    private Point2D position;
    private double slope, yIntercept;
    private double speed;
    private boolean isXIncreasing;
    private boolean toggleBuffer = false;

    public Point2D getNextPosition() {
        double deltaX = speed * Math.cos(Math.atan2(slope, 1));
        double nextX = position.getX() + ((isXIncreasing) ? deltaX : -deltaX);
        double nextY = slope * nextX + yIntercept;
        return new Point2D.Double(nextX, nextY);
    }

    public void setXIncreasing(boolean isXIncreasing) {
        this.isXIncreasing = isXIncreasing;
    }

    public void toggleXIncreasing() {
        this.isXIncreasing = !this.isXIncreasing;
        toggleBuffer = true;
    }

    public void initSpeed() {
        speed = INIT_SPEED;
    }

    public void increaseSpeed() {
        if (speed < MAX_SPEED) {
            speed += SPEED_STEP;
        }
    }

    public void decreaseSpeed() {
        if (speed > MIN_SPEED) {
            speed -= SPEED_STEP;
        }
    }

    public void setPosition(double x, double y) {
        this.position = new Point2D.Double(x, y);
    }

    public Point2D getPosition() {
        return position;
    }

    public boolean isToggleBuffer() {
        return toggleBuffer;
    }

    public void setToggleBuffer(boolean toggleBuffer) {
        this.toggleBuffer = toggleBuffer;
    }

    public double getX() {
        return position.getX();
    }

    public double getY() {
        return position.getY();
    }

    public void initSlope() {
        setSlope(INIT_SLOPE);
    }

    public void setSlope(double slope) {
        this.slope = slope;
        this.yIntercept = position.getY() - slope * position.getX();
    }

    public void toggleSlope() {
        setSlope(-slope);
    }
}
