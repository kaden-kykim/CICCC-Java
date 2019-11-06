package ca.ciccc.wmad.kaden.assignment.n3.view;

import ca.ciccc.wmad.kaden.assignment.n3.contract.AGContract;
import ca.ciccc.wmad.kaden.assignment.n3.presenter.AGPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

public class AGView extends JFrame implements AGContract.View {

    private static final Dimension CONTENT_SIZE = new Dimension(640, 480);
    private static final int CONTROL_PANEL_HEIGHT = 85;
    private static final int STATUS_PANEL_HEIGHT = 85;
    private static final String STATUS_DEFAULT_TEXT = "[STATUS] ";

    private AGContract.Presenter presenter;

    private JButton btnStop, btnGenerate;
    private JTextField textFieldInput;
    private JTextArea textAreaOutput;
    private JProgressBar progressBar;
    private JLabel labelStatus;

    private AGTask agPreTask, agTask;

    public AGView() {
        this.presenter = new AGPresenter(this);
        initializeUI();
    }

    @Override
    public void setTaskProgress(int progress) {
        if (agTask != null && !agTask.isCancelled() && !agTask.isDone()) {
            agTask.updateProgress(progress);
        }
    }

    @Override
    public void setStatusText(String text) {
        if (labelStatus != null) {
            labelStatus.setText(STATUS_DEFAULT_TEXT + text);
        }
    }

    @Override
    public void addOutput(String anagram) {
        textAreaOutput.append(anagram + "\n");
        textAreaOutput.setCaretPosition(textAreaOutput.getDocument().getLength());
    }

    private void initializeUI() {
        textFieldInput = new JTextField();
        JPanel ctrlInputPane = new JPanel(new BorderLayout());
        ctrlInputPane.add(new JLabel("Input: "), BorderLayout.LINE_START);
        ctrlInputPane.add(textFieldInput, BorderLayout.CENTER);

        btnGenerate = new JButton("Generate");
        btnGenerate.addActionListener(generateActionListener);
        btnStop = new JButton("Stop");
        btnStop.addActionListener(stopActionListener);
        btnStop.setEnabled(false);
        JPanel ctrlBtnsPane = new JPanel();
        ctrlBtnsPane.add(btnGenerate);
        ctrlBtnsPane.add(btnStop);

        JPanel controlPane = new JPanel(new GridLayout(2, 1));
        controlPane.setPreferredSize(new Dimension(0, CONTROL_PANEL_HEIGHT));
        controlPane.setBorder(BorderFactory.createTitledBorder("Anagram Generate"));
        controlPane.add(ctrlInputPane);
        controlPane.add(ctrlBtnsPane);

        textAreaOutput = new JTextArea();
        textAreaOutput.setEditable(false);
        textAreaOutput.setFont(new Font("monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(textAreaOutput);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Anagram Output"));

        labelStatus = new JLabel();
        setStatusText("Waiting for User Input...");
        progressBar = new JProgressBar(0, 100);
        JPanel statusPane = new JPanel(new GridLayout(2, 1));
        statusPane.setPreferredSize(new Dimension(0, STATUS_PANEL_HEIGHT));
        statusPane.setBorder(BorderFactory.createTitledBorder("Status"));
        statusPane.add(labelStatus);
        statusPane.add(progressBar);

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setPreferredSize(CONTENT_SIZE);
        contentPane.add(controlPane, BorderLayout.PAGE_START);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(statusPane, BorderLayout.PAGE_END);

        this.setTitle("Anagram Generator");
        this.setContentPane(contentPane);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private PropertyChangeListener progressListener = evt -> {
        if ("progress".equals(evt.getPropertyName())) {
            progressBar.setValue((Integer) evt.getNewValue());
        }
    };

    private ActionListener generateActionListener = e -> {
        progressStatus();
        String inputText = textFieldInput.getText();
        runPreAGProcess(inputText);
        runAGProcess();
    };

    private ActionListener stopActionListener = e -> {
        if (agPreTask != null) {
            agPreTask.cancel(true);
        }
        if (agTask != null) {
            agTask.cancel(true);
        }
    };

    private void runPreAGProcess(String inputText) {
        if (agPreTask != null) {
            agPreTask.cancel(true);
            agPreTask = null;
        }
        agPreTask = new AGTask(new AGTask.Callback() {
            @Override
            public void processTask() {
                presenter.processPreTask(inputText);
            }

            @Override
            public void taskDone() {
                System.out.println("Pre AG Process Done");
            }
        });
        agPreTask.execute();
    }

    private void runAGProcess() {
        if (agTask != null) {
            agTask.cancel(true);
            agTask = null;
        }
        agTask = new AGTask(new AGTask.Callback() {
            @Override
            public void processTask() {
                presenter.processTask();
            }

            @Override
            public void taskDone() {
                System.out.println("AG Process Done");
                readyStatus();
            }
        });
        agTask.addPropertyChangeListener(progressListener);
        agTask.execute();
    }

    private void progressStatus() {
        btnGenerate.setEnabled(false);
        textFieldInput.setEnabled(false);
        btnStop.setEnabled(true);
        textAreaOutput.setText("");
    }

    private void readyStatus() {
        btnGenerate.setEnabled(true);
        textFieldInput.setEnabled(true);
        btnStop.setEnabled(false);
        progressBar.setValue(progressBar.getMinimum());
    }
}
