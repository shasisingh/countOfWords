package com.test.shashi.countofWord;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * TODO Class description
 *
 * @author shasisingh
 * @since 23/11/2019
 */
public class CountOfUniqueWord {

    private static int MIN_WORD_LENGTH = 2;

    private static String FILE_PATH = "/Users/shasisingh/Desktop/swagger.yml";

    public static void main(String[] args) {

        /*
            Function to read number of word from given files.
            File size can't have any limits.

         */

        LocalDateTime startDateTime = LocalDateTime.now();


        List<String> sortedListOfWords = getSortedListOfWords(splitStrings(readAllFileDataToString(FILE_PATH)));

        Map<String, Integer> countOfUniqueWords = getCountOfWords(MIN_WORD_LENGTH, sortedListOfWords);

        System.out.println("Total unique word Count:" + countOfUniqueWords.size());

        System.out.println("***************************************");
        displayAllUniqueOccurrencesCount(countOfUniqueWords);
        System.out.println("***************************************");



        System.out.println("Total Word:" + getSumOfAllWords(countOfUniqueWords));
        System.out.println("***************************************");

        System.out.println(startDateTime);
        System.out.println(LocalDateTime.now());

    }

    private static String[] splitStrings(String readAllFileDataToString) {
        return readAllFileDataToString.split("[-.\n\r;: ,'/?|&)(]");
    }


    private static void displayAllUniqueOccurrencesCount(Map<String, Integer> countOfUniqueWords) {
        for (Map.Entry<String, Integer> entry : countOfUniqueWords.entrySet()) {
            System.out.println("Total Occurrences of " + entry.getKey().toLowerCase() + ": " + entry.getValue());
        }
    }

    private static int getSumOfAllWords(Map<String, Integer> countOfUniqueWords) {
        return countOfUniqueWords.values().stream().mapToInt(Integer::intValue).sum();

    }

    private static Map<String, Integer> getCountOfWords(int minWordLength, List<String> sortedString) {
        Map<String, Integer> statistics = new HashMap<>();

        for (String word : sortedString) {
            String wordToBeSearch = word.trim().toLowerCase();

            if (wordToBeSearch.length() >= minWordLength && !wordToBeSearch.equalsIgnoreCase("")) {
                if (!statistics.containsKey(wordToBeSearch)) {
                    statistics.put(wordToBeSearch, 1);
                } else {
                    Integer existingValue = statistics.get(wordToBeSearch);
                    statistics.put(wordToBeSearch, existingValue + 1);
                }
            }
        }
        return statistics;
    }

    static List<String> getSortedListOfWords(String[] splitWords) {
        return Arrays.stream(splitWords).sorted().collect(Collectors.toList());
    }

    static String readAllFileDataToString(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                contentBuilder.append(currentLine).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }
}



