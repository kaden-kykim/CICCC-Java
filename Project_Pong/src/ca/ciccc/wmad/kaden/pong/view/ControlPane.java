package ca.ciccc.wmad.kaden.pong.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ControlPane extends JPanel {

    private PongView pongView;
    private JLabel computerScore, playerScore;
    private JButton btnDifficulty;

    public ControlPane(PongView pongView, int height) {
        this.pongView = pongView;

        this.setPreferredSize(new Dimension(0, height));
        this.setBackground(Color.gray);

        computerScore = new JLabel("0");
        computerScore.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 20));
        computerScore.setForeground(Color.BLUE);
        this.add(computerScore);

        JButton btnStartGame = new JButton("Start Game");
        btnStartGame.addActionListener(e -> pongView.userStartGame());
        this.add(btnStartGame);

        btnDifficulty = new JButton("Difficulty [ NORMAL ]");
        btnDifficulty.addActionListener(e -> pongView.toggleDifficulty());
        this.add(btnDifficulty);

        JButton btnResetGame = new JButton("Reset Game");
        btnResetGame.addActionListener(e -> pongView.userResetGame());
        this.add(btnResetGame);

        playerScore = new JLabel("0");
        playerScore.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 20));
        playerScore.setForeground(Color.BLUE);
        this.add(playerScore);
    }

    public void addScore(boolean isPlayer) {
        JLabel score = ((isPlayer) ? playerScore : computerScore);
        score.setText(String.format("%d", Integer.parseInt(score.getText()) + 1));
    }

    public void initScores() {
        computerScore.setText("0");
        playerScore.setText("0");
    }

    public void setDifficulty(String difficulty) {
        btnDifficulty.setText("Difficulty [ " + difficulty + " ]");
    }
}
