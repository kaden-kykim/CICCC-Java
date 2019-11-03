package ca.ciccc.wmad.kaden.pong.view;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

class DrawUtility {

    static void drawLine(Graphics2D g, double srcX, double srcY, double dstX, double dstY, Color color, float stroke) {
        Line2D line = new Line2D.Double(srcX, srcY, dstX, dstY);
        g.setColor(color);
        g.setStroke(new BasicStroke(stroke));
        g.draw(line);
    }

    static void drawFilledCircle(Graphics2D g, Point2D position, double diameter, Color color) {
        Ellipse2D.Double circle = new Ellipse2D.Double(position.getX(), position.getY(), diameter, diameter);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(color);
        g.fill(circle);
    }

    static void drawFilledRect(Graphics2D g, double x, double y, double width, double height, Color color) {
        Rectangle2D rect = new Rectangle2D.Double(x, y, width, height);
        g.setColor(color);
        g.fill(rect);
    }
}
