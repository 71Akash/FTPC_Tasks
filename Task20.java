class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}

public class Task20 {
    private static void demonstrateArrayIndexOutOfBoundsException() {
        System.out.println("\nArrayIndexOutOfBoundsException:");
        try {
            int[] numbers = {10, 20, 30};
            System.out.println(numbers[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Caught: " + e);
        } finally {
            System.out.println("Finally block executed.");
        }
    }

    private static void demonstrateNullPointerException() {
        System.out.println("\nNullPointerException:");
        try {
            String text = null;
            System.out.println(text.length());
        } catch (NullPointerException e) {
            System.out.println("Caught: " + e);
        } finally {
            System.out.println("Finally block executed.");
        }
    }

    private static void demonstrateNumberFormatException() {
        System.out.println("\nNumberFormatException:");
        try {
            int number = Integer.parseInt("not a number");
            System.out.println(number);
        } catch (NumberFormatException e) {
            System.out.println("Caught: " + e);
        } finally {
            System.out.println("Finally block executed.");
        }
    }

    private static void demonstrateArithmeticException() {
        System.out.println("\nArithmeticException:");
        try {
            int result = 10 / 0;
            System.out.println(result);
        } catch (ArithmeticException e) {
            System.out.println("Caught: " + e);
        } finally {
            System.out.println("Finally block executed.");
        }
    }

    private static void checkAge(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("Age must be at least 18.");
        }
        System.out.println("Age is valid.");
    }

    private static void demonstrateInvalidAgeException() {
        System.out.println("\nInvalidAgeException:");
        try {
            checkAge(16);
        } catch (InvalidAgeException e) {
            System.out.println("Caught: " + e.getMessage());
        } finally {
            System.out.println("Finally block executed.");
        }
    }

    public static void main(String[] args) {
        demonstrateArrayIndexOutOfBoundsException();
        demonstrateNullPointerException();
        demonstrateNumberFormatException();
        demonstrateArithmeticException();
        demonstrateInvalidAgeException();
    }
}
