package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class NumberView extends JFrame {
    private JTextArea textArea;
    private JRadioButton evenRadioButton;
    private JRadioButton oddRadioButton;
    private JButton filterButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton browseButton;
    private JTextField numberInputField;

    public NumberView() {
        setTitle("Number Filter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(400, 500);
        textArea = new JTextArea(15, 25);
        JScrollPane scrollPane = new JScrollPane(textArea);

        evenRadioButton = new JRadioButton("Even");
        oddRadioButton = new JRadioButton("Odd");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(evenRadioButton);
        buttonGroup.add(oddRadioButton);

        filterButton = new JButton("Filter");
        saveButton = new JButton("Save");
        loadButton = new JButton("Load");
        browseButton = new JButton("Browse");

        numberInputField = new JTextField(10);

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Enter a natural number: "));
        inputPanel.add(numberInputField);

        JPanel filterPanel = new JPanel();
        filterPanel.add(evenRadioButton);
        filterPanel.add(oddRadioButton);
        filterPanel.add(filterButton);
        filterPanel.add(saveButton);
        filterPanel.add(loadButton);
        filterPanel.add(browseButton);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(filterPanel, BorderLayout.SOUTH);

        add(panel);

        setVisible(true);
    }

    public int getNumberInput() {
        try {
            return Integer.parseInt(numberInputField.getText());
        } catch (NumberFormatException e) {
            return -1; // Return -1 if input is not a natural number
        }
    }

    public boolean isEvenSelected() {
        return evenRadioButton.isSelected();
    }

    public void displayNumbers(List<Integer> numbers) {
        StringBuilder sb = new StringBuilder();
        for (int num : numbers) {
            sb.append(num).append("\n");
        }
        textArea.setText(sb.toString());
    }

    public void displayDirectoryContent(String content) {
        textArea.setText(content);
    }

    public void addFilterButtonListener(ActionListener listener) {
        filterButton.addActionListener(listener);
    }

    public void addSaveButtonListener(ActionListener listener) {
        saveButton.addActionListener(listener);
    }

    public void addLoadButtonListener(ActionListener listener) {
        loadButton.addActionListener(listener);
    }

    public void addBrowseButtonListener(ActionListener listener) {
        browseButton.addActionListener(listener);
    }
}
