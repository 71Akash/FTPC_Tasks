import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Task19 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("How many integers will you enter? ");
        int count = scanner.nextInt();
        if (count < 0) {
            System.out.println("The count cannot be negative.");
            scanner.close();
            return;
        }

        List<Integer> numbers = new ArrayList<>();
        System.out.println("Enter " + count + " integers (duplicates are allowed):");
        for (int i = 0; i < count; i++) {
            numbers.add(scanner.nextInt());
        }

        Set<Integer> hashSet = new HashSet<>(numbers);
        Set<Integer> linkedHashSet = new LinkedHashSet<>(numbers);
        Set<Integer> treeSet = new TreeSet<>(hashSet);

        System.out.println("Original list: " + numbers);
        System.out.println("Unique values (HashSet; order unspecified): " + hashSet);
        System.out.println("Unique values (LinkedHashSet; insertion order): " + linkedHashSet);
        System.out.println("Unique values (TreeSet; sorted order): " + treeSet);

        scanner.close();
    }
}
