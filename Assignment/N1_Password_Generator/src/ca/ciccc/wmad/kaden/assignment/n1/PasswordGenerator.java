package ca.ciccc.wmad.kaden.assignment.n1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Random;

public class PasswordGenerator extends JFrame {

    private static final Dimension CONTENT_SIZE = new Dimension(320, 480);
    private static final int GEN_BTN_HEIGHT = 35;

    private static final int MIN_LENGTH = 2, MAX_LENGTH = 128, INIT_MIN_LENGTH = 4, INIT_MAX_LENGTH = 6;
    private static final int MIN_QUANTITY = 1, MAX_QUANTITY = 128;
    private static final int NUM_TYPES = 3, IDX_LOWER_TYPE = 0, IDX_UPPER_TYPE = 1, IDX_NUM_TYPE = 2;
    private static final int NUM_LENG_QTY = 3, IDX_MIN_LENG = 0, IDX_MAX_LENG = 1, IDX_QUANTITY = 2;
    private static final String[] STR_TYPES = {"abcdefghijklmnopqrstuvwxyz", "ABCDEFGHIJKLMNOPQRSTUVWXYZ", "1234567890"};

    private JComboBox<Integer>[] cBoxLengQty;
    private JCheckBox[] checkTypes;
    private JTextArea resultTextArea;

    private Random random;

    public PasswordGenerator() {
        initializeUI();
        random = new Random(System.currentTimeMillis());
    }

    private String[] generatePasswords(int quantity, int minLength, int maxLength, boolean[] includeTypes) {
        String[] generatedPasswords = new String[quantity];

        int numOfTypes = 0;
        for (boolean b : includeTypes) {
            if (b) numOfTypes++;
        }
        int[] mapTypes = new int[numOfTypes];
        for (int i = 0, j = 0; i < includeTypes.length; ++i) {
            if (includeTypes[i]) mapTypes[j++] = i;
        }

        for (int i = 0; i < generatedPasswords.length; ++i) {
            generatedPasswords[i] =
                    generatePassword(random.nextInt(maxLength - minLength) + minLength, mapTypes);
        }

        return generatedPasswords;
    }

    private String generatePassword(int length, int[] mapTypes) {
        int numOfTypes = mapTypes.length;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; ++i) {
            String type = STR_TYPES[mapTypes[random.nextInt(numOfTypes)]];
            stringBuilder.append(type.charAt(random.nextInt(type.length())));
        }
        return stringBuilder.toString();
    }

    private Integer[] generateNumberList(int lower, int upper) {
        Integer[] retVal = new Integer[upper - lower + 1];

        for (int i = 0; i < retVal.length; ++i) {
            retVal[i] = i + lower;
        }

        return retVal;
    }

    private void initializeUI() {
        checkTypes = new JCheckBox[NUM_TYPES];
        checkTypes[IDX_LOWER_TYPE] = new JCheckBox("Lower Cases", true);
        checkTypes[IDX_UPPER_TYPE] = new JCheckBox("Upper Cases");
        checkTypes[IDX_NUM_TYPE] = new JCheckBox("Numbers");

        JPanel ctrlTypePane = new JPanel(new GridLayout(NUM_TYPES, 1));
        ctrlTypePane.setBorder(BorderFactory.createTitledBorder("Include Type"));
        for (JCheckBox checkBox : checkTypes) {
            checkBox.addItemListener(typeItemListener);
            ctrlTypePane.add(checkBox);
        }

        JLabel[] labels = {
                new JLabel("Min Length(Inc.)"),
                new JLabel("Max Length(Inc.)"),
                new JLabel("# Password")};
        cBoxLengQty = new JComboBox[NUM_LENG_QTY];
        cBoxLengQty[IDX_MIN_LENG] = new JComboBox<>(generateNumberList(MIN_LENGTH, INIT_MAX_LENGTH));
        cBoxLengQty[IDX_MIN_LENG].setSelectedIndex(2);
        cBoxLengQty[IDX_MAX_LENG] = new JComboBox<>(generateNumberList(INIT_MIN_LENGTH, MAX_LENGTH));
        cBoxLengQty[IDX_MAX_LENG].setSelectedIndex(2);
        cBoxLengQty[IDX_QUANTITY] = new JComboBox<>(generateNumberList(MIN_QUANTITY, MAX_QUANTITY));

        JPanel ctrlLengQtyPane = new JPanel(new GridLayout(NUM_LENG_QTY * 2, 1));
        ctrlLengQtyPane.setBorder(BorderFactory.createTitledBorder("Length / # Output"));
        for (int i = 0; i < NUM_LENG_QTY; ++i) {
            ctrlLengQtyPane.add(labels[i]);
            cBoxLengQty[i].addActionListener(lengQtyActionListener);
            ctrlLengQtyPane.add(cBoxLengQty[i]);
        }

        JPanel settingPane = new JPanel(new GridLayout(1, 2));
        settingPane.add(ctrlTypePane);
        settingPane.add(ctrlLengQtyPane);

        JButton btnGenerate = new JButton("Generate!");
        btnGenerate.setPreferredSize(new Dimension(0, GEN_BTN_HEIGHT));
        btnGenerate.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 15));
        btnGenerate.addActionListener(generateActionListener);

        JPanel controlPane = new JPanel(new BorderLayout(5, 5));
        controlPane.add(settingPane, BorderLayout.CENTER);
        controlPane.add(btnGenerate, BorderLayout.PAGE_END);

        resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);
        resultTextArea.setFont(new Font("monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(resultTextArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Passwords List"));

        JPanel contentPane = new JPanel(new GridLayout(2, 1));
        contentPane.setPreferredSize(CONTENT_SIZE);
        contentPane.add(controlPane);
        contentPane.add(scrollPane);

        this.setTitle("Password Generator");
        this.setContentPane(contentPane);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private ActionListener generateActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean[] includeTypes = new boolean[NUM_TYPES];
            for (int i = 0; i < NUM_TYPES; ++i) {
                includeTypes[i] = checkTypes[i].isSelected();
            }
            String[] generatedPasswords = generatePasswords(
                    (int) cBoxLengQty[IDX_QUANTITY].getSelectedItem(),
                    (int) cBoxLengQty[IDX_MIN_LENG].getSelectedItem(),
                    (int) cBoxLengQty[IDX_MAX_LENG].getSelectedItem(), includeTypes);

            resultTextArea.setText("");
            for (int i = 0; i < generatedPasswords.length; ++i) {
                resultTextArea.append(String.format("%3d: %s\n", (i + 1), generatedPasswords[i]));
            }
        }
    };

    private ItemListener typeItemListener = e -> {
        if (e.getStateChange() == ItemEvent.DESELECTED) {
            boolean nothingChecked = true;
            for (JCheckBox checkbox : checkTypes) {
                if (checkbox.isSelected()) {
                    nothingChecked = false;
                    break;
                }
            }
            if (nothingChecked) {
                JOptionPane.showMessageDialog(this,
                        "Must select at least one type",
                        "Type select error", JOptionPane.WARNING_MESSAGE);
                ((JCheckBox) e.getItem()).setSelected(true);
            }
        }
    };

    private ActionListener lengQtyActionListener = e -> {
        JComboBox selected = (JComboBox) e.getSource();
        int item = (int) selected.getSelectedItem();
        if (selected == cBoxLengQty[IDX_MIN_LENG]) {
            Integer i = (Integer) cBoxLengQty[IDX_MAX_LENG].getSelectedItem();
            DefaultComboBoxModel<Integer> model = new DefaultComboBoxModel<>(generateNumberList(item, MAX_LENGTH));
            cBoxLengQty[IDX_MAX_LENG].setModel(model);
            cBoxLengQty[IDX_MAX_LENG].setSelectedItem(i);
        } else if (selected == cBoxLengQty[IDX_MAX_LENG]) {
            Integer i = (Integer) cBoxLengQty[IDX_MIN_LENG].getSelectedItem();
            DefaultComboBoxModel<Integer> model = new DefaultComboBoxModel<>(generateNumberList(MIN_LENGTH, item));
            cBoxLengQty[IDX_MIN_LENG].setModel(model);
            cBoxLengQty[IDX_MIN_LENG].setSelectedItem(i);
        }
    };

    public static void main(String[] args) {
        PasswordGenerator passwordGenerator = new PasswordGenerator();
        passwordGenerator.setVisible(true);
    }

}
