import java.util.LinkedList;
import java.util.Queue;

class SharedBuffer {
    private Queue<Integer> buffer;
    private int capacity;

    public SharedBuffer(int capacity) {
        this.capacity = capacity;
        buffer = new LinkedList<>();
    }

    public synchronized void produce(int val) {
        try {
            while (buffer.size() == capacity) {
                wait();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
        buffer.offer(val);
        System.out.println("Produced : " + val);
        notifyAll(); // notifies to both (consumer and producer) dis advantage
    }

    public synchronized void consume() {
        try {
            while (buffer.isEmpty()) {
                wait();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
        int val = buffer.poll();
        System.out.println("Consumer : " + val);
        notifyAll(); // notifies to both (consumer and producer) dis advantage
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        // SharedBuffer buffer = new SharedBuffer(3);
        // Thread producer = new Thread(()->{
        //     for(int i = 0;i<10;i++){
        //         buffer.produce(i);
        //         try {
        //             Thread.sleep(1000);
        //         } catch (InterruptedException e) {
        //             Thread.currentThread().interrupt();
        //             e.printStackTrace();
        //         }
        //     }
        // });

        // Thread consumer = new Thread(()->{
        //     for(int i = 0;i<10;i++){
        //         buffer.consume();
        //         try {
        //             Thread.sleep(2000);
        //         } catch (InterruptedException e) {
        //             Thread.currentThread().interrupt();
        //             e.printStackTrace();
        //         }
        //     }
        // });

        // producer.start();
        // consumer.start();
        // ProdConUsingRELock pe = new ProdConUsingRELock();
        ProdConsumeUsingBlockingQueue pe = new ProdConsumeUsingBlockingQueue();
        pe.main();
    }
}
