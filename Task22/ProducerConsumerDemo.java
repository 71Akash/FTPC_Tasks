import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumerDemo {
    private static final String STOP = "<STOP>";

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> bookQueue = new LinkedBlockingQueue<>();

        Thread producer = new Thread(() -> {
            String[] titles = {"Clean Code", "Effective Java", "The Pragmatic Programmer"};
            try {
                for (String title : titles) {
                    bookQueue.put(title);
                    System.out.println("Produced: " + title);
                }
                bookQueue.put(STOP);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "book-producer");

        Thread consumer = new Thread(() -> {
            try {
                while (true) {
                    String title = bookQueue.take();
                    if (STOP.equals(title)) break;
                    System.out.println("Consumed: " + title);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "book-consumer");

        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        System.out.println("Producer-consumer demo finished.");
    }
}
