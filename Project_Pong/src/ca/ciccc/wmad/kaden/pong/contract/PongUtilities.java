package ca.ciccc.wmad.kaden.pong.contract;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import static ca.ciccc.wmad.kaden.pong.contract.PongContract.*;

public class PongUtilities {

    public static double getCalcFromPixel(double pixel, Dimension curDimension) {
        return pixel * CALC_BOARD_WIDTH / curDimension.width;
    }

    public static double getPixelFromCalc(double calc, Dimension curDimension) {
        return calc * curDimension.width / CALC_BOARD_WIDTH;
    }

    public static boolean checkIntersects(double ballX, double ballY, Rectangle2D rectangle2D) {
        Ellipse2D ellipse2D = new Ellipse2D.Double(ballX, ballY, CALC_BALL_DIAMETER, CALC_BALL_DIAMETER);
        return ellipse2D.intersects(rectangle2D);
    }
}
