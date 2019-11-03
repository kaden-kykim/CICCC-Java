package ca.ciccc.wmad.kaden.pong.view;

import ca.ciccc.wmad.kaden.pong.contract.PongUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import static ca.ciccc.wmad.kaden.pong.contract.PongContract.*;

public class GamePane extends JPanel {

    private final static Cursor BLANK_CURSOR = Toolkit.getDefaultToolkit()
            .createCustomCursor(new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB),
                    new Point(0, 0), "Blank cursor");

    private PongView pongView;

    private Point prevMousePoint;
    private Point2D ballPosition;
    private double playerPaddlePosY = -1, comPaddlePosY = -1;

    public GamePane(PongView pongView) {
        this.pongView = pongView;

        this.addMouseListener(mouseAdapter);
        this.addMouseMotionListener(mouseMotionAdapter);

        this.setDoubleBuffered(true);
        this.setBackground(new Color(25, 25, 25));
        this.repaint();
    }

    public void setBallPosition(Point2D ballPosition) {
        this.ballPosition = ballPosition;
        this.repaint();
    }

    public void setPaddlePosition(boolean isPlayer, double y) {
        if (isPlayer) {
            playerPaddlePosY = y;
        } else {
            comPaddlePosY = y;
        }
        this.repaint();
    }

    private MouseAdapter mouseAdapter = new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            prevMousePoint = e.getPoint();
            setCursor(BLANK_CURSOR);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            setCursor(Cursor.getDefaultCursor());
            prevMousePoint = null;
        }
    };

    private MouseMotionAdapter mouseMotionAdapter = new MouseMotionAdapter() {
        @Override
        public void mouseDragged(MouseEvent e) {
            Point p = e.getPoint();
            if (p.getY() > 0 && p.getY() < getSize().height) {
                setCursor(BLANK_CURSOR);
            }

            if (prevMousePoint != null) {
                pongView.userMovePaddle(e.getPoint().getY() - prevMousePoint.getY());
                prevMousePoint = p;
            }
        }
    };

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawVerticalLine(g2d);
        drawBall(g2d);
        drawPaddle(g2d);
    }

    private void drawVerticalLine(Graphics2D g) {
        Dimension d = getSize();
        double halfWidth = d.width / 2.0;
        double gapHeight = d.height * 0.1;
        DrawUtility.drawLine(g, halfWidth, gapHeight, halfWidth, d.height - gapHeight,
                new Color(255, 255, 255, 50), 2.f);
    }

    private void drawBall(Graphics2D g) {
        double ballRadius = PongUtilities.getPixelFromCalc(CALC_BALL_RADIUS, getSize());
        if (ballPosition != null) {
            DrawUtility.drawFilledCircle(g, new Point2D.Double(
                            PongUtilities.getPixelFromCalc(ballPosition.getX(), getSize()),
                            PongUtilities.getPixelFromCalc(ballPosition.getY(), getSize())),
                    ballRadius, Color.WHITE);
        }
    }

    private void drawPaddle(Graphics2D g) {
        double paddleWidth = PongUtilities.getPixelFromCalc(CALC_PADDLE_WIDTH, getSize());
        double paddleHeight = PongUtilities.getPixelFromCalc(CALC_PADDLE_HEIGHT, getSize());
        if (playerPaddlePosY > 0) {
            DrawUtility.drawFilledRect(g,
                    PongUtilities.getPixelFromCalc(CALC_PLAYER_PADDLE_POS_X, getSize()),
                    PongUtilities.getPixelFromCalc(playerPaddlePosY, getSize()),
                    paddleWidth, paddleHeight, Color.WHITE);
        }
        if (comPaddlePosY > 0) {
            DrawUtility.drawFilledRect(g,
                    PongUtilities.getPixelFromCalc(CALC_COM_PADDLE_POS_X - CALC_PADDLE_WIDTH, getSize()),
                    PongUtilities.getPixelFromCalc(comPaddlePosY, getSize()),
                    paddleWidth, paddleHeight, Color.WHITE);
        }
    }
}

