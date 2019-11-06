package ca.ciccc.wmad.kaden.assignment.n3;

import ca.ciccc.wmad.kaden.assignment.n3.view.AGView;

import javax.swing.*;

public class AnagramGenerator {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AGView agView = new AGView();
            agView.setVisible(true);
        });
    }
}
