package controllers;

import models.NumberModel;
import views.NumberView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class NumberController {
    private NumberModel model;
    private NumberView view;

    public NumberController(NumberModel model, NumberView view) {
        this.model = model;
        this.view = view;

        // Add listeners for filter, save, and load buttons
        view.addFilterButtonListener(e -> filterNumbers());
        view.addSaveButtonListener(e -> saveNumbers());
        view.addLoadButtonListener(e -> loadNumbers());
        view.addBrowseButtonListener(e -> browseDirectory());
    }

    private void filterNumbers() {
        int number = view.getNumberInput();
        if (number <= 0) {
            JOptionPane.showMessageDialog(view, "Please enter a natural number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean even = view.isEvenSelected();
        List<Integer> filteredNumbers = model.filterNumbers(number, even);
        view.displayNumbers(filteredNumbers);
    }

    private void saveNumbers() {
        List<Integer> filteredNumbers = model.filterNumbers(view.getNumberInput(), view.isEvenSelected());
        String filename = JOptionPane.showInputDialog(view, "Enter file name to save:");
        if (filename != null && !filename.isEmpty()) {
            model.saveNumbersToFile(filteredNumbers, filename);
            JOptionPane.showMessageDialog(view, "Numbers saved to file: " + filename);
        }
    }

    private void loadNumbers() {
        String filename = JOptionPane.showInputDialog(view, "Enter file name to load:");
        if (filename != null && !filename.isEmpty()) {
            List<Integer> loadedNumbers = model.loadNumbersFromFile(filename);
            view.displayNumbers(loadedNumbers);
        }
    }

    private void browseDirectory() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showOpenDialog(view);
        if (result == JFileChooser.APPROVE_OPTION) {
            String directoryPath = fileChooser.getSelectedFile().getAbsolutePath();
            List<String> files = model.listFilesInDirectoryRecursive(directoryPath);
            StringBuilder fileList = new StringBuilder();
            for (String file : files) {
                fileList.append(file).append("\n");
            }
            view.displayDirectoryContent(fileList.toString());
        }
    }

    public static void main(String[] args) {
        NumberModel model = new NumberModel();
        NumberView view = new NumberView();
        NumberController controller = new NumberController(model, view);
    }
}
