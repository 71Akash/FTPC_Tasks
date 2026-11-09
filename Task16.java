import java.util.Scanner;

interface Payment {
    void pay(double amount);
}

class CreditCard implements Payment {
    public void pay(double amount) {
        double charge = amount * 0.02;
        System.out.printf("Paid $%.2f using Credit Card. Charge: $%.2f%n", amount, charge);
    }
}

class UPI implements Payment {
    public void pay(double amount) {
        double charge = amount * 0.01;
        System.out.printf("Paid $%.2f using UPI. Charge: $%.2f%n", amount, charge);
    }
}

class NetBanking implements Payment {
    public void pay(double amount) {
        double charge = amount * 0.015;
        System.out.printf("Paid $%.2f using Net Banking. Charge: $%.2f%n", amount, charge);
    }
}

public class Task16 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose a payment mode:");
        System.out.println("1. Credit Card (2% charge)");
        System.out.println("2. UPI (1% charge)");
        System.out.println("3. Net Banking (1.5% charge)");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        System.out.print("Enter payment amount: ");
        double amount = scanner.nextDouble();
        if (amount <= 0) {
            System.out.println("Payment amount must be greater than zero.");
            scanner.close();
            return;
        }

        Payment payment;
        switch (choice) {
            case 1:
                payment = new CreditCard();
                break;
            case 2:
                payment = new UPI();
                break;
            case 3:
                payment = new NetBanking();
                break;
            default:
                System.out.println("Invalid payment mode.");
                scanner.close();
                return;
        }

        payment.pay(amount);
        scanner.close();
    }
}
