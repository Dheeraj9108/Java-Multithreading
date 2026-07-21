import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SharedResource {
    
    private int data = 10;
    private final ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock(true);
    private final ReentrantReadWriteLock.ReadLock readLock = rwlock.readLock();
    private final ReentrantReadWriteLock.WriteLock writeLock = rwlock.writeLock();

    public int read(){
        try {
            readLock.lock();
            System.out.printf("%s started read \n", Thread.currentThread().getName());
            Thread.sleep(100);
            System.out.printf("%s finished read \n", Thread.currentThread().getName());
            return data;
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            return 0;
        } finally {
            readLock.unlock();
        }
    }

    public void write(int val){
        try {
            writeLock.lock();
            System.out.printf("%s started writing \n", Thread.currentThread().getName());
            Thread.sleep(100);
            data = val;
            System.out.printf("%s finished writing \n", Thread.currentThread().getName());
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        } finally {
            writeLock.unlock();
        }
    }

}
