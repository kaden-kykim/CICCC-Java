package ca.ciccc.wmad.kaden.pong.view;

import ca.ciccc.wmad.kaden.pong.contract.PongContract;
import ca.ciccc.wmad.kaden.pong.contract.PongUtilities;
import ca.ciccc.wmad.kaden.pong.presenter.PongPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Point2D;

public class PongView extends JFrame implements PongContract.View {

    private static final String TITLE = "PONG GAME";
    private static final Dimension
            INIT_CONTENT_SIZE = new Dimension(800, 450),
            MINIMUM_CONTENT_SIZE = new Dimension(640, 370);
    private static final int CONTROL_PANEL_HEIGHT = 50;

    private PongContract.Presenter presenter;
    private JPanel contentPane;
    private ControlPane controlPane;
    private GamePane gamePane;

    private Dimension currentFrameSize = INIT_CONTENT_SIZE;
    private int gapBetweenFrameAndContent = 0;

    public PongView() {
        initialize();
        this.presenter = new PongPresenter(this);
    }

    @Override
    public void setBallPosition(double x, double y) {
        gamePane.setBallPosition(new Point2D.Double(x, y));
    }

    @Override
    public void setPaddlePosition(boolean isPlayer, double y) {
        gamePane.setPaddlePosition(isPlayer, y);
    }

    @Override
    public void addScore(boolean isPlayer) {
        controlPane.addScore(isPlayer);
    }

    @Override
    public void initScore() {
        controlPane.initScores();
    }

    @Override
    public void setDifficultyText(String difficulty) {
        controlPane.setDifficulty(difficulty);
    }

    @Override
    public void setGameControlText(String gameControl) {
        controlPane.setControlGame(gameControl);
    }

    public void userMovePaddle(double diffHeightInPx) {
        presenter.checkUserPaddlePosition(PongUtilities.getCalcFromPixel(diffHeightInPx, currentFrameSize));
    }

    public void userResetGame() {
        presenter.resetGame();
    }

    public void userControlGame() {
        presenter.controlGame();
    }

    public void toggleDifficulty() {
        presenter.toggleDifficulty();
    }

    private void initialize() {
        this.setTitle(TITLE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setMinimumSize(MINIMUM_CONTENT_SIZE);

        contentPane = new JPanel(new BorderLayout());
        contentPane.setPreferredSize(INIT_CONTENT_SIZE);

        controlPane = new ControlPane(this, CONTROL_PANEL_HEIGHT);
        contentPane.add(controlPane, BorderLayout.PAGE_START);

        gamePane = new GamePane(this);
        contentPane.add(gamePane, BorderLayout.CENTER);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                currentFrameSize = e.getComponent().getSize();
                e.getComponent().setSize(currentFrameSize.width, currentFrameSize.width / 2 + CONTROL_PANEL_HEIGHT + gapBetweenFrameAndContent);
                gamePane.repaint();
            }
        });

        this.setContentPane(contentPane);
        this.pack();
        gapBetweenFrameAndContent = this.getSize().height - contentPane.getSize().height;
        gamePane.repaint();
    }
}
