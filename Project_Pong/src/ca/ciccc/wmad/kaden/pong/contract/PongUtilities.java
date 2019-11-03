package ca.ciccc.wmad.kaden.pong.contract;

import java.awt.*;

import static ca.ciccc.wmad.kaden.pong.contract.PongContract.CALC_BOARD_WIDTH;

public class PongUtilities {

    public static double getCalcFromPixel(double pixel, Dimension curDimension) {
        return pixel * CALC_BOARD_WIDTH / curDimension.width;
    }

    public static double getPixelFromCalc(double calc, Dimension curDimension) {
        return calc * curDimension.width / CALC_BOARD_WIDTH;
    }
}
