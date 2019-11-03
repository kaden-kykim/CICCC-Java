package ca.ciccc.wmad.kaden.pong.view;

import javax.swing.*;
import java.awt.*;

public class ControlPane extends JPanel {

    private PongView pongView;

    public ControlPane(PongView pongView, int height) {
        this.pongView = pongView;

        this.setPreferredSize(new Dimension(0, height));
        this.setBackground(Color.gray);

        JButton btnStartGame = new JButton("Start Game");
        btnStartGame.addActionListener(e -> pongView.userStartGame());
        this.add(btnStartGame);

        JButton btnResetGame = new JButton("Reset Game");
        btnResetGame.addActionListener(e -> pongView.userResetGame());
        this.add(btnResetGame);
    }
}
