package ca.ciccc.wmad.kaden.assignment.n3.view.swing;

import ca.ciccc.wmad.kaden.assignment.n3.model.setting.AGSetting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static ca.ciccc.wmad.kaden.assignment.n3.model.setting.AGSetting.LENGTH_LOWER_BOUND;
import static ca.ciccc.wmad.kaden.assignment.n3.model.setting.AGSetting.LENGTH_UPPER_BOUND;

public class AGSettingDialog extends JDialog {

    private static final Dimension DIALOG_CONTENT_SIZE = new Dimension(400, 200);
    private static final int BUTTON_CONTROL_PANEL_HEIGHT = 65;
    private static final int IDX_WORD_MIN_LENGTH = 0, IDX_WORD_MAX_LENGTH = 1;
    private static final String[] STRINGS_PROCESS_SPEED_LIST
            = new String[]{"Stable", "Normal", "Fast", "Faster"};

    private JCheckBox checkBoxAllowRepeat;
    private JComboBox<String> comboBoxProcess;
    private JComboBox<Integer>[] comboBoxLength;

    public AGSettingDialog(AGView view) {
        initializeUI(view);
    }

    private ActionListener cancelActionListener = event -> {
        if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this,
                "Every changes will lost. Are you sure?", "CANCEL",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)) {
            this.dispose();
        }
    };

    private ActionListener applyActionListener = event -> {
        AGSetting.getInstance().setSettingSpeed(comboBoxProcess.getSelectedIndex() + 1);
        if (comboBoxLength[IDX_WORD_MIN_LENGTH] != null) {
            AGSetting.getInstance().setMinWordLength((Integer) comboBoxLength[IDX_WORD_MIN_LENGTH].getSelectedItem());
        }
        if (comboBoxLength[IDX_WORD_MAX_LENGTH] != null) {
            AGSetting.getInstance().setMaxWordLength((Integer) comboBoxLength[IDX_WORD_MAX_LENGTH].getSelectedItem());
        }
        AGSetting.getInstance().setAllowRepeat(checkBoxAllowRepeat.isSelected());

        this.dispose();
    };

    private ActionListener wordLengthActionListener = event -> {
        JComboBox selected = (JComboBox) event.getSource();
        int item = (int) selected.getSelectedItem();
        if (selected == comboBoxLength[IDX_WORD_MIN_LENGTH]) {
            Integer i = (Integer) comboBoxLength[IDX_WORD_MAX_LENGTH].getSelectedItem();
            DefaultComboBoxModel<Integer> model
                    = new DefaultComboBoxModel<>(generateNumberList(item + 1, LENGTH_UPPER_BOUND));
            comboBoxLength[IDX_WORD_MAX_LENGTH].setModel(model);
            comboBoxLength[IDX_WORD_MAX_LENGTH].setSelectedItem(i);
        } else if (selected == comboBoxLength[IDX_WORD_MAX_LENGTH]) {
            Integer i = (Integer) comboBoxLength[IDX_WORD_MIN_LENGTH].getSelectedItem();
            DefaultComboBoxModel<Integer> model
                    = new DefaultComboBoxModel<>(generateNumberList(LENGTH_LOWER_BOUND, item - 1));
            comboBoxLength[IDX_WORD_MIN_LENGTH].setModel(model);
            comboBoxLength[IDX_WORD_MIN_LENGTH].setSelectedItem(i);
        }
    };

    private void initializeUI(AGView view) {
        comboBoxLength = new JComboBox[2];
        comboBoxLength[IDX_WORD_MIN_LENGTH]
                = new JComboBox<>(generateNumberList(LENGTH_LOWER_BOUND,
                AGSetting.getInstance().getMaxWordLength() - 1));
        comboBoxLength[IDX_WORD_MAX_LENGTH]
                = new JComboBox<>(generateNumberList(
                AGSetting.getInstance().getMinWordLength() + 1, LENGTH_UPPER_BOUND));
        comboBoxLength[IDX_WORD_MIN_LENGTH].addActionListener(wordLengthActionListener);
        comboBoxLength[IDX_WORD_MAX_LENGTH].addActionListener(wordLengthActionListener);
        comboBoxLength[IDX_WORD_MIN_LENGTH].setSelectedItem(AGSetting.getInstance().getMinWordLength());
        comboBoxLength[IDX_WORD_MAX_LENGTH].setSelectedItem(AGSetting.getInstance().getMaxWordLength());

        JLabel[] labels = {new JLabel("Minimum Length(In.)"), new JLabel("Maximum Length (Ex.)")};
        JPanel leftCtrlPane = new JPanel(new GridLayout(4, 1));
        leftCtrlPane.setBorder(BorderFactory.createTitledBorder("Number of Letters"));
        for (int i = 0; i < 2; ++i) {
            leftCtrlPane.add(labels[i]);
            leftCtrlPane.add(comboBoxLength[i]);
        }

        comboBoxProcess = new JComboBox<>(STRINGS_PROCESS_SPEED_LIST);
        comboBoxProcess.setSelectedIndex(AGSetting.getInstance().getSettingSpeed() - 1);
        checkBoxAllowRepeat = new JCheckBox("Allow Repeated Words", AGSetting.getInstance().allowRepeat());
        JPanel rightCtrlPane = new JPanel(new GridLayout(4, 1));
        rightCtrlPane.setBorder(BorderFactory.createTitledBorder("Etc. Setting"));
        rightCtrlPane.add(new Label("Process Type"));
        rightCtrlPane.add(comboBoxProcess);
        rightCtrlPane.add(new Label(""));
        rightCtrlPane.add(checkBoxAllowRepeat);

        JPanel mainCtrlPane = new JPanel(new GridLayout(1, 2));
        mainCtrlPane.add(leftCtrlPane);
        mainCtrlPane.add(rightCtrlPane);

        JButton btnApply = new JButton("Apply");
        JButton btnCancel = new JButton("Cancel");
        btnApply.addActionListener(applyActionListener);
        btnCancel.addActionListener(cancelActionListener);
        JPanel ctrlBtnPane = new JPanel();
        ctrlBtnPane.setPreferredSize(new Dimension(0, BUTTON_CONTROL_PANEL_HEIGHT));
        ctrlBtnPane.add(btnApply);
        ctrlBtnPane.add(btnCancel);

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setPreferredSize(DIALOG_CONTENT_SIZE);
        contentPane.add(ctrlBtnPane, BorderLayout.PAGE_END);
        contentPane.add(mainCtrlPane, BorderLayout.CENTER);

        this.setTitle("Anagram Generator Setting");
        this.setContentPane(contentPane);
        this.pack();
        this.setModal(true);
        this.setResizable(false);
        this.setLocationRelativeTo(view);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private Integer[] generateNumberList(int lower, int upper) {
        Integer[] retVal = new Integer[upper - lower + 1];

        for (int i = 0; i < retVal.length; ++i) {
            retVal[i] = i + lower;
        }

        return retVal;
    }
}
