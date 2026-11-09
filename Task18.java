import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Task18 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a paragraph:");
        String paragraph = scanner.nextLine().toLowerCase();

        String cleanedParagraph = paragraph.replaceAll("[^\\p{L}\\p{N}]+", " ").trim();
        HashMap<String, Integer> wordCounts = new HashMap<>();

        if (!cleanedParagraph.isEmpty()) {
            for (String word : cleanedParagraph.split("\\s+")) {
                wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
            }
        }

        ArrayList<Map.Entry<String, Integer>> sortedWords = new ArrayList<>(wordCounts.entrySet());
        sortedWords.sort(Comparator.<Map.Entry<String, Integer>>comparingInt(Map.Entry::getValue)
                .reversed()
                .thenComparing(Map.Entry::getKey));

        if (sortedWords.isEmpty()) {
            System.out.println("No words found.");
        } else {
            System.out.println("Word counts (highest first):");
            for (Map.Entry<String, Integer> entry : sortedWords) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
            Map.Entry<String, Integer> mostFrequent = sortedWords.get(0);
            System.out.println("Most frequent word: " + mostFrequent.getKey()
                    + " (" + mostFrequent.getValue() + " times)");
        }

        scanner.close();
    }
}
