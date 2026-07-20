import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProdConsumeUsingBlockingQueue {
    private BlockingQueue<Integer> buffer = new ArrayBlockingQueue<>(3);

    public void main() {

        for (int j = 0; j < 2; j++) {
            Thread producer = new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    try {
                        buffer.put(i);
                        System.out.println("Produced : " + i + " by " + Thread.currentThread().getName());
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        e.printStackTrace();
                    }
                }
            });
            producer.start();
        }

        for (int j = 0; j < 2; j++) {
            Thread consumer = new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    try {
                        int val = buffer.take();
                        System.out.println("Consumed : " + val + " by " + Thread.currentThread().getName());
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        e.printStackTrace();
                    }
                }
            });
            consumer.start();
        }
    }
}
