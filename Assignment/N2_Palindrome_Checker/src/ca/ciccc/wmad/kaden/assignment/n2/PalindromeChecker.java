package ca.ciccc.wmad.kaden.assignment.n2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PalindromeChecker extends JFrame {

    private static final Dimension CONTENT_SIZE = new Dimension(640, 480);
    private static final int CONTROL_PANEL_HEIGHT = 85;
    private static final int DIFF_UPPER_LOWER = 'a' - 'A';

    private JButton btnCheck, btnGenerate;
    private JTextField textFieldInput;
    private JTextArea textAreaOutput;

    public PalindromeChecker() {
        initializeUI();
    }

    private ActionListener palindromeActionListener = e -> {
        String inputString = textFieldInput.getText();
        if (inputString == null || "".equals(inputString)) {
            JOptionPane.showMessageDialog(this, "Please, input text first",
                    "Input error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (e.getSource() == btnCheck) {
            textAreaOutput.append("[Check Palindrome]\n    \"" + inputString + "\" is "
                    + ((checkPalindrome(inputString)) ? "" : "NOT ") + "a palindrome.\n");
        } else if (e.getSource() == btnGenerate) {
            textAreaOutput.append("[Generate Palindrome]\n    \"" + inputString + "\" => \""
                    + generatePalindrome(inputString, true) + "\"\n");
        }
        textFieldInput.setText("");
    };

    private boolean checkPalindrome(String inputString) {
        inputString = inputString.toLowerCase();
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < inputString.length(); ++i) {
            char ch = inputString.charAt(i);
            if (checkAcceptCharacter(ch)) {
                buf.append(ch);
            }
        }

        for (int i = 0; i < buf.length() / 2; ++i) {
            if (buf.charAt(i) != buf.charAt(buf.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    private String generatePalindrome(String inputString, boolean resultLengthInOdd) {
        StringBuilder buf = new StringBuilder(inputString);
        int startIndex = inputString.length() - ((resultLengthInOdd) ? 2 : 1);
        for (int i = startIndex; i >= 0; --i) {
            char ch = inputString.charAt(i);
            if (checkAcceptCharacter(ch) || ch == ' ') {
                if (checkCapitalLetter(ch)) {
                    ch += DIFF_UPPER_LOWER;
                }
                buf.append(ch);
            }
        }
        return buf.toString();
    }

    private boolean checkAcceptCharacter(char ch) {
        return ((ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9') || checkCapitalLetter(ch));
    }

    private boolean checkCapitalLetter(char ch) {
        return ch >= 'A' && ch <= 'Z';
    }

    private void initializeUI() {
        textFieldInput = new JTextField();
        JPanel ctrlInputPane = new JPanel(new BorderLayout());
        ctrlInputPane.add(new JLabel("Input: "), BorderLayout.LINE_START);
        ctrlInputPane.add(textFieldInput, BorderLayout.CENTER);

        btnCheck = new JButton("Check");
        btnCheck.addActionListener(palindromeActionListener);
        btnGenerate = new JButton("Generate");
        btnGenerate.addActionListener(palindromeActionListener);
        JPanel ctrlBtnsPane = new JPanel();
        ctrlBtnsPane.add(btnCheck);
        ctrlBtnsPane.add(btnGenerate);

        JPanel controlPane = new JPanel(new GridLayout(2, 1));
        controlPane.setPreferredSize(new Dimension(0, CONTROL_PANEL_HEIGHT));
        controlPane.setBorder(BorderFactory.createTitledBorder("Palindrome Check / Generate"));
        controlPane.add(ctrlInputPane);
        controlPane.add(ctrlBtnsPane);

        textAreaOutput = new JTextArea();
        textAreaOutput.setEditable(false);
        textAreaOutput.setFont(new Font("monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(textAreaOutput);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Palindrome Output"));

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setPreferredSize(CONTENT_SIZE);
        contentPane.add(controlPane, BorderLayout.PAGE_START);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        this.setTitle("Palindrome Checker");
        this.setContentPane(contentPane);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        PalindromeChecker palindromeChecker = new PalindromeChecker();
        palindromeChecker.setVisible(true);
    }
}
