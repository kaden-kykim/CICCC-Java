package ca.ciccc.wmad.kaden.pong.view;

import javax.swing.*;
import java.awt.*;

public class ControlPane extends JPanel {

    private PongView pongView;
    private JLabel computerScore, playerScore;
    private JButton btnDifficulty, btnControlGame;

    public ControlPane(PongView pongView, int height) {
        this.pongView = pongView;

        this.setPreferredSize(new Dimension(0, height));
        this.setBackground(Color.gray);

        computerScore = new JLabel("0");
        computerScore.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 20));
        computerScore.setForeground(Color.BLUE);
        this.add(computerScore);

        btnControlGame = new JButton("Start Game ");
        btnControlGame.setFont(new Font("monospaced", Font.PLAIN, 12));
        btnControlGame.addActionListener(e -> pongView.userControlGame());
        this.add(btnControlGame);

        btnDifficulty = new JButton("Difficulty [ NORMAL ]");
        btnDifficulty.addActionListener(e -> pongView.toggleDifficulty());
        btnDifficulty.setFont(new Font("monospaced", Font.PLAIN, 12));
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

    public void setControlGame(String controlGame) {
        btnControlGame.setText(" " + controlGame + " Game" + ((controlGame.length() == 6) ? "" : " "));
    }
}
