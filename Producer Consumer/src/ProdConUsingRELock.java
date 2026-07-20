import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class SharedBuffer {
    private Queue<Integer> buffer;
    private int capacity;
    private ReentrantLock lock = new ReentrantLock();
    private Condition fullCondition = lock.newCondition();
    private Condition emptyCondition = lock.newCondition();

    public SharedBuffer(int capacity) {
        this.capacity = capacity;
        buffer = new LinkedList<>();
    }

    public void produce(int val) {
        lock.lock();
        try {
            while (buffer.size() == capacity) {
                fullCondition.await();
            }
            buffer.offer(val);
            System.out.println("Produced : " + val);
            emptyCondition.signal();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        } finally{
            lock.unlock();
        }
    }

    public synchronized void consume() {
        lock.lock();
        try {
            while (buffer.isEmpty()) {
                emptyCondition.await();
            }
            int val = buffer.poll();
            System.out.println("Consumer : " + val);
            fullCondition.signal(); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
public class ProdConUsingRELock {
    public void main() {
        SharedBuffer buffer = new SharedBuffer(3);
        Thread producer = new Thread(()->{
            for(int i = 0;i<10;i++){
                buffer.produce(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }
        });

        Thread consumer = new Thread(()->{
            for(int i = 0;i<10;i++){
                buffer.consume();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }
        });

        producer.start();
        consumer.start();
    }
}
