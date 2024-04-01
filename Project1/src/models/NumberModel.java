package models;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NumberModel {
    public List<Integer> filterNumbers(int number, boolean even) {
        if (number < 1) {
            throw new IllegalArgumentException("Input number must be a positive integer.");
        }

        return IntStream.rangeClosed(1, number)
                .filter(n -> even ? n % 2 == 0 : n % 2 != 0)
                .boxed()
                .collect(Collectors.toList());
    }

    public void saveNumbersToFile(List<Integer> numbers, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (int num : numbers) {
                writer.println(num);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Integer> loadNumbersFromFile(String filename) {
        List<Integer> numbers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                numbers.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numbers;
    }

    public List<String> listFilesInDirectoryRecursive(String directoryPath) {
        List<String> fileNames = new ArrayList<>();
        listFilesInDirectoryRecursiveHelper(new File(directoryPath), fileNames);
        return fileNames;
    }

    private void listFilesInDirectoryRecursiveHelper(File directory, List<String> fileNames) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    listFilesInDirectoryRecursiveHelper(file, fileNames);
                } else {
                    fileNames.add(file.getAbsolutePath());
                }
            }
        }
    }
}
