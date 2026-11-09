import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Task21 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the input text file name: ");
        String inputFile = scanner.nextLine();

        int lineCount = 0;
        int wordCount = 0;
        int characterCount = 0;
        String longestWord = "";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lineCount++;
                characterCount += line.length(); // Excludes line-ending characters.

                String trimmedLine = line.trim();
                if (!trimmedLine.isEmpty()) {
                    String[] words = trimmedLine.split("\\s+");
                    wordCount += words.length;
                    for (String word : words) {
                        if (word.length() > longestWord.length()) {
                            longestWord = word;
                        }
                    }
                }
            }

            try (FileWriter writer = new FileWriter("report.txt")) {
                writer.write("Text File Report\n");
                writer.write("Lines: " + lineCount + "\n");
                writer.write("Words: " + wordCount + "\n");
                writer.write("Characters (excluding line endings): " + characterCount + "\n");
                writer.write("Longest word: " + (longestWord.isEmpty() ? "None" : longestWord) + "\n");
            }

            System.out.println("Report written to report.txt.");
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found: " + inputFile);
        } catch (IOException e) {
            System.out.println("Error reading the input file or writing the report: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
