import java.util.Scanner;

public class Task11 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of elements: ");
        int size = scanner.nextInt();
        if (size <= 0) {
            System.out.println("The array must contain at least one element.");
            return;
        }

        double[] numbers = new double[size];
        System.out.print("Enter " + size + " elements: ");
        double smallest = 0;
        double largest = 0;
        double secondLargest = 0;
        double sum = 0;
        boolean hasSecondLargest = false;

        for (int i = 0; i < size; i++) {
            double value = scanner.nextDouble();
            numbers[i] = value;
            sum += value;

            if (i == 0 || value < smallest) {
                smallest = value;
            }

            if (i == 0) {
                largest = value;
            } else if (value > largest) {
                secondLargest = largest;
                largest = value;
                hasSecondLargest = true;
            } else if (value < largest && (!hasSecondLargest || value > secondLargest)) {
                secondLargest = value;
                hasSecondLargest = true;
            }
        }

        System.out.println("Smallest element: " + smallest);
        System.out.println("Largest element: " + largest);
        System.out.println("Sum: " + sum);
        System.out.println("Average: " + sum / size);
        if (hasSecondLargest) {
            System.out.println("Second largest distinct element: " + secondLargest);
        } else {
            System.out.println("There is no second largest distinct element.");
        }

        System.out.print("Array in reverse order: ");
        for (int i = size - 1; i >= 0; i--) {
            System.out.print(numbers[i]);
            if (i > 0) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }
}
